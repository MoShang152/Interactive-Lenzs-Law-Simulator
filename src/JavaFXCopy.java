import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JavaFXCopy {

    public static void copyFolder(File source, File destination) throws IOException {
        if (!source.exists()) {
            throw new IOException("Source folder does not exist: " + source.getAbsolutePath());
        }
        if (!destination.exists()) {
            destination.mkdirs(); // 创建目标文件夹
        }

        for (File file : source.listFiles()) {
            File destFile = new File(destination, file.getName());
            if (file.isDirectory()) {
                copyFolder(file, destFile); // 递归复制文件夹
            } else {
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static void main(String[] args) {
        // JavaFX SDK 的源路径
        File sourceFolder = new File("C:\\Users\\TOM T\\Desktop\\大三课业\\毕设\\openjfx-23.0.2_windows-x64_bin-sdk\\javafx-sdk-23.0.2");
        // 项目中存放 JavaFX SDK 的目标路径
        File destinationFolder = new File("C:\\Users\\TOM T\\Desktop\\大三课业\\毕设\\项目\\javafx-sdk");

        try {
            copyFolder(sourceFolder, destinationFolder);
            System.out.println("JavaFX SDK copied successfully!");
        } catch (IOException e) {
            System.err.println("Error during JavaFX SDK copy: " + e.getMessage());
        }
    }
}
