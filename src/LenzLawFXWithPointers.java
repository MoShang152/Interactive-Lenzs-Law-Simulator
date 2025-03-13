import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LenzLawFXWithPointers extends Application {
    // 磁铁参数（放大后的磁铁）
    private double magnetX = 50, magnetY; // 在 start() 中根据线圈位置初始化
    private double magnetWidth = 80, magnetHeight = 60;
    private double magnetSpeed = 0;
    private boolean running = true;
    private boolean dragging = false;
    private double offsetX, offsetY;
    
    // 线圈参数（居中于放大后的画布）
    private double coilX = 450, coilY = 300, coilRadius = 60;
    
    // 电子参数（电子数量及其角度）
    private final int electronCount = 12;
    private double[] electronAngles = new double[electronCount];
    private double electronSpeed = 0;  // 由磁通变化决定
    
    // 背景磁场指针参数
    private int spacing = 30;
    private int canvasWidth = 900, canvasHeight = 600;
    
    // 磁通量计算变量
    private double lastFlux = 0;
    private double fluxChange = 0;  // 每帧磁通量的变化量
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lenz's Law Simulation (JavaFX) 〜(￣▽￣)〜");
        
        // 将磁铁的初始位置设置为与线圈中心在同一垂直水平线上
        magnetY = coilY - magnetHeight / 2;
        
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // 初始化电子角度（均匀分布）
        for (int i = 0; i < electronCount; i++) {
            electronAngles[i] = i * (360.0 / electronCount);
        }
        
        // 创建控制面板
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button reverseButton = new Button("Reverse");
        Label speedLabel = new Label("Speed:");
        Slider speedSlider = new Slider(-5, 5, magnetSpeed);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1);
        
        startButton.setOnAction(e -> running = true);
        stopButton.setOnAction(e -> running = false);
        reverseButton.setOnAction(e -> magnetSpeed = -magnetSpeed);
        
        ChangeListener<Number> speedListener = (obs, oldVal, newVal) -> magnetSpeed = newVal.doubleValue();
        speedSlider.valueProperty().addListener(speedListener);
        
        HBox controls = new HBox(10, startButton, stopButton, reverseButton, speedLabel, speedSlider);
        root.setBottom(controls);
        root.setCenter(canvas);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // 鼠标拖拽磁铁
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getX() >= magnetX && e.getX() <= magnetX + magnetWidth &&
                e.getY() >= magnetY && e.getY() <= magnetY + magnetHeight) {
                dragging = true;
                offsetX = e.getX() - magnetX;
                offsetY = e.getY() - magnetY;
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (dragging) {
                magnetX = e.getX() - offsetX;
                magnetY = e.getY() - offsetY;
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> dragging = false);
        
        // 动画循环：只要 running 为 true 就一直调用 update()（无论是否拖动）
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (running) {
                    update();
                }
                draw(gc);
            }
        };
        timer.start();
    }
    
    private void update() {
        // 当没有拖动时自动更新磁铁运动
        if (!dragging) {
            magnetX += magnetSpeed;
            if (magnetX < 0 || magnetX + magnetWidth > canvasWidth) {
                magnetSpeed = -magnetSpeed;
            }
        }
        
        // 始终更新磁通量与电子角度（反映磁铁的当前位置）
        double currentFlux = calcFlux();
        fluxChange = currentFlux - lastFlux;
        // 根据磁通变化更新电子转动速度，放慢得多一些
        electronSpeed = -fluxChange * 0.05;
        for (int i = 0; i < electronCount; i++) {
            electronAngles[i] += electronSpeed;
        }
        lastFlux = currentFlux;
    }
    
    // 计算磁通量（非常简化：与磁铁中心到线圈中心距离的反平方成正比）
    private double calcFlux() {
        double magnetCenterX = magnetX + magnetWidth / 2;
        double magnetCenterY = magnetY + magnetHeight / 2;
        double dx = coilX - magnetCenterX;
        double dy = coilY - magnetCenterY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance < 1) distance = 1;
        return 40000 / (distance * distance);
    }
    
    private void draw(GraphicsContext gc) {
        // 清空画布
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        
        // 绘制背景磁场箭头（保持原来细细的样子）
        drawFieldPointers(gc);
        
        // 绘制线圈（局部加粗，仅用于线圈外形）
        gc.save();
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(6);
        gc.strokeOval(coilX - coilRadius, coilY - coilRadius, coilRadius * 2, coilRadius * 2);
        gc.restore();
        
        // 在线圈内部绘制电子
        drawElectrons(gc);
        
        // 绘制感应电流方向标注及箭头
        drawInducedCurrentArrow(gc);
        
        // 绘制磁铁：现实风格（左半部分为 N 极红色，右半部分为 S 极蓝色）
        double halfWidth = magnetWidth / 2;
        gc.setFill(Color.RED);
        gc.fillRect(magnetX, magnetY, halfWidth, magnetHeight);
        gc.setFill(Color.BLUE);
        gc.fillRect(magnetX + halfWidth, magnetY, halfWidth, magnetHeight);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(magnetX, magnetY, magnetWidth, magnetHeight);
        gc.setFill(Color.WHITE);
        gc.fillText("N", magnetX + halfWidth / 2 - 5, magnetY + magnetHeight / 2 + 5);
        gc.fillText("S", magnetX + halfWidth + halfWidth / 2 - 5, magnetY + magnetHeight / 2 + 5);
        
        // 绘制线圈对磁铁施加的力的方向箭头及标注
        drawForceArrow(gc);
        
        // 显示当前磁通量
        double flux = calcFlux();
        gc.setFill(Color.BLACK);
        gc.fillText(String.format("Flux: %.2f", flux), 10, 20);
    }
    
    // 绘制背景磁感线箭头（磁偶极子模型），保持原来细细的线宽
    private void drawFieldPointers(GraphicsContext gc) {
        gc.save();
        gc.setLineWidth(1);
        gc.setStroke(Color.DARKGRAY);
        double nPoleX = magnetX + magnetWidth / 4;
        double nPoleY = magnetY + magnetHeight / 2;
        double sPoleX = magnetX + (magnetWidth * 3 / 4);
        double sPoleY = magnetY + magnetHeight / 2;
        
        for (int x = spacing / 2; x < canvasWidth; x += spacing) {
            for (int y = spacing / 2; y < canvasHeight; y += spacing) {
                double dxN = x - nPoleX;
                double dyN = y - nPoleY;
                double rN = Math.sqrt(dxN * dxN + dyN * dyN);
                if (rN < 1) rN = 1;
                double fieldNX = dxN / (rN * rN * rN);
                double fieldNY = dyN / (rN * rN * rN);
                
                double dxS = x - sPoleX;
                double dyS = y - sPoleY;
                double rS = Math.sqrt(dxS * dxS + dyS * dyS);
                if (rS < 1) rS = 1;
                double fieldSX = dxS / (rS * rS * rS);
                double fieldSY = dyS / (rS * rS * rS);
                
                double Bx = fieldNX - fieldSX;
                double By = fieldNY - fieldSY;
                double angle = Math.atan2(By, Bx);
                
                double length = 10;
                double x2 = x + length * Math.cos(angle);
                double y2 = y + length * Math.sin(angle);
                gc.strokeLine(x, y, x2, y2);
                
                double arrowAngle = Math.toRadians(30);
                double arrowLength = 5;
                double leftX = x2 - arrowLength * Math.cos(angle - arrowAngle);
                double leftY = y2 - arrowLength * Math.sin(angle - arrowAngle);
                double rightX = x2 - arrowLength * Math.cos(angle + arrowAngle);
                double rightY = y2 - arrowLength * Math.sin(angle + arrowAngle);
                gc.strokeLine(x2, y2, leftX, leftY);
                gc.strokeLine(x2, y2, rightX, rightY);
            }
        }
        gc.restore();
    }
    
    // 绘制电子：在线圈内部，直径 8 并在中心绘制负号
    private void drawElectrons(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (int i = 0; i < electronCount; i++) {
            double angleRad = Math.toRadians(electronAngles[i]);
            double electronPathRadius = coilRadius - 3;
            double ex = coilX + electronPathRadius * Math.cos(angleRad);
            double ey = coilY + electronPathRadius * Math.sin(angleRad);
            
            gc.fillOval(ex - 4, ey - 4, 8, 8);
            gc.strokeOval(ex - 4, ey - 4, 8, 8);
            
            gc.setFill(Color.WHITE);
            gc.fillText("-", ex - 3, ey + 4);
            gc.setFill(Color.BLUE);
        }
    }
    
    // 绘制感应电流箭头及标注（显示常规电流方向，与电子运动方向相反）
    private void drawInducedCurrentArrow(GraphicsContext gc) {
        double currentDir = -electronSpeed; // 常规电流方向
        double arrowStartX = coilX;
        double arrowStartY = coilY - coilRadius;
        double arrowLength = 20;
        double arrowEndX, arrowEndY;
        gc.setStroke(Color.MAGENTA);
        gc.setLineWidth(4);  // 仅修改此处，使箭头更粗
        gc.setFill(Color.MAGENTA);
        gc.setFont(Font.font("System", FontWeight.BOLD, 16));
        if (currentDir > 0) {
            arrowEndX = arrowStartX - arrowLength;
            arrowEndY = arrowStartY;
            gc.fillText("Induced current: CCW", coilX - coilRadius - 40, coilY - coilRadius - 10);
        } else {
            arrowEndX = arrowStartX + arrowLength;
            arrowEndY = arrowStartY;
            gc.fillText("Induced current: CW", coilX + coilRadius + 10, coilY - coilRadius - 10);
        }
        gc.strokeLine(arrowStartX, arrowStartY, arrowEndX, arrowEndY);
        double arrowAngle = Math.toRadians(30);
        double arrowHeadLength = 5;
        double baseAngle = (currentDir > 0) ? Math.PI : 0;
        double leftX = arrowEndX - arrowHeadLength * Math.cos(baseAngle - arrowAngle);
        double leftY = arrowEndY - arrowHeadLength * Math.sin(baseAngle - arrowAngle);
        double rightX = arrowEndX - arrowHeadLength * Math.cos(baseAngle + arrowAngle);
        double rightY = arrowEndY - arrowHeadLength * Math.sin(baseAngle + arrowAngle);
        gc.strokeLine(arrowEndX, arrowEndY, leftX, leftY);
        gc.strokeLine(arrowEndX, arrowEndY, rightX, rightY);
    }
    
    // 绘制线圈对磁铁施加的力的箭头及标注（根据磁通变化判断吸引或排斥）
    private void drawForceArrow(GraphicsContext gc) {
        double magnetCenterX = magnetX + magnetWidth / 2;
        double magnetCenterY = magnetY + magnetHeight / 2;
        double dxForce = magnetCenterX - coilX;
        double dyForce = magnetCenterY - coilY;
        double forceAngle;
        if (fluxChange > 0) {
            forceAngle = Math.atan2(dyForce, dxForce);
        } else {
            forceAngle = Math.atan2(dyForce, dxForce) + Math.PI;
        }
        double arrowLength = 30;
        double arrowStartX = magnetCenterX;
        double arrowStartY = magnetCenterY;
        double arrowEndX = arrowStartX + arrowLength * Math.cos(forceAngle);
        double arrowEndY = arrowStartY + arrowLength * Math.sin(forceAngle);
        gc.setStroke(Color.ORANGE);
        gc.setLineWidth(4);  // 仅修改此处，使箭头更粗
        gc.strokeLine(arrowStartX, arrowStartY, arrowEndX, arrowEndY);
        double arrowAngle = Math.toRadians(30);
        double arrowHeadLength = 5;
        double leftX = arrowEndX - arrowHeadLength * Math.cos(forceAngle - arrowAngle);
        double leftY = arrowEndY - arrowHeadLength * Math.sin(forceAngle - arrowAngle);
        double rightX = arrowEndX - arrowHeadLength * Math.cos(forceAngle + arrowAngle);
        double rightY = arrowEndY - arrowHeadLength * Math.sin(forceAngle + arrowAngle);
        gc.strokeLine(arrowEndX, arrowEndY, leftX, leftY);
        gc.strokeLine(arrowEndX, arrowEndY, rightX, rightY);
        gc.setFont(Font.font("System", FontWeight.BOLD, 16));
        gc.setFill(Color.ORANGE);
        gc.fillText("Force on magnet", magnetCenterX + 10, magnetCenterY + 20);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
