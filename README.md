# JavaFX Physics Simulation Project

## Overview
This project demonstrates a physics simulation using JavaFX, including:
- Lenz's Law visualization.
- Interactive electromagnetic field simulation.

**Note:**  
This project **no longer includes a bundled JDK and JavaFX SDK**.  
Users need to **install JDK 21+ and JavaFX SDK manually** before running the program.

---

## Project Structure
```
Project/
├── src/                           # Source code
│   ├── LenzLawFXWithPointers.java
│   └── TestFX.java
├── bin/                           # Compiled class files
├── README.md                      # This file
```
---

## Requirements
- **JDK 21 or higher**  
  - Download from [Adoptium](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)
- **JavaFX SDK**  
  - Download from [GluonHQ](https://gluonhq.com/products/javafx/)

---

## Usage Instructions

### **1. Set up JavaFX**
Before compiling, you must **download and configure JavaFX SDK**:
1. Download JavaFX from [GluonHQ](https://gluonhq.com/products/javafx/).
2. Extract it and note the path (e.g., `C:\javafx-sdk` on Windows or `/usr/local/javafx-sdk` on Linux/macOS).

### **2. Compile the Code**
Run the following command in the project root directory:
```bash
javac -d bin --module-path "path_to_javafx/lib" --add-modules javafx.controls,javafx.fxml src/LenzLawFXWithPointers.java
```
(Replace `"path_to_javafx/lib"` with your actual JavaFX SDK path.)

### **3. Run the Program**
```bash
java --module-path "path_to_javafx/lib" --add-modules javafx.controls,javafx.fxml -cp bin LenzLawFXWithPointers
```

---

## Notes
- Ensure that **JavaFX SDK is properly configured** before running the program.
- If you encounter errors, check if your system supports JavaFX rendering.
- If using **macOS or Linux**, adjust the JavaFX path accordingly.

---

# JavaFX 物理仿真实验项目

## 项目概述
本项目基于 JavaFX 实现了一个物理仿真，包括：
- 楞次定律的可视化。
- 交互式电磁场仿真。

**注意：**  
该项目**不再包含 JDK 和 JavaFX SDK**，请用户**自行下载安装**。

---

## 项目结构
```
项目/
├── src/                           # 源代码
│   ├── LenzLawFXWithPointers.java
│   └── TestFX.java
├── bin/                           # 编译后的 class 文件
├── README.md                      # 本文件
```
---

## 环境要求
- **JDK 21 或更高版本**  
  - 下载：[Adoptium](https://adoptium.net/) 或 [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)
- **JavaFX SDK**  
  - 下载：[GluonHQ](https://gluonhq.com/products/javafx/)

---

## 使用说明

### **1. 配置 JavaFX**
1. 从 [GluonHQ](https://gluonhq.com/products/javafx/) 下载 JavaFX SDK 并解压。
2. 记住解压路径（例如 Windows: `C:\javafx-sdk`，Linux/macOS: `/usr/local/javafx-sdk`）。

### **2. 编译代码**
在项目根目录运行：
```bash
javac -d bin --module-path "path_to_javafx/lib" --add-modules javafx.controls,javafx.fxml src/LenzLawFXWithPointers.java
```
(将 `"path_to_javafx/lib"` 替换为你 JavaFX SDK 的路径。)

### **3. 运行程序**
```bash
java --module-path "path_to_javafx/lib" --add-modules javafx.controls,javafx.fxml -cp bin LenzLawFXWithPointers
```

---

## 注意事项
- 确保 **JavaFX SDK 已正确配置**，否则程序无法运行。
- 若遇到错误，请检查 **系统是否支持 JavaFX 图形渲染**。
- **macOS/Linux 用户** 需要根据自己的系统修改 JavaFX 路径。


