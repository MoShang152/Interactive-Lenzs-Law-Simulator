# JavaFX Physics Simulation Project

## Overview
This project demonstrates a physics simulation using JavaFX, including:
- Lenz's Law visualization.
- Interactive electromagnetic field simulation.

It is designed to work independently with no external installations required. The project includes a bundled JDK and JavaFX SDK.

---

## Project Structure
```
Project/
├── jdk-21.0.6.7-hotspot/          # Bundled JDK
│   ├── bin/
│   ├── lib/
│   └── ...
├── javafx-sdk/                    # JavaFX SDK
│   ├── bin/                       # JavaFX native libraries (DLL files)
│   ├── lib/                       # JavaFX JAR files
│   └── ...
├── src/                           # Source code
│   ├── LenzLawFXWithPointers.java
│   └── TestFX.java
├── bin/                           # Compiled class files
├── README.md                      # This file
```

---

## Requirements
- **No external JDK or JavaFX installation required.**
- The project includes all dependencies.

---

## Usage Instructions
### **1. Compile the Code**
Run the following command in the project root directory:
```bash
jdk-21.0.6.7-hotspot\bin\javac -d bin --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml src\LenzLawFXWithPointers.java
```

### **2. Run the Program**
After successful compilation, run the program with:
```bash
jdk-21.0.6.7-hotspot\bin\java --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml -Djava.library.path="javafx-sdk\bin" -cp bin LenzLawFXWithPointers
```

---

## Notes
- Ensure the project is extracted fully before running the commands.
- If you encounter any errors, ensure your system supports JavaFX graphics rendering.
- If running on a different platform (e.g., macOS or Linux), replace the bundled JDK and JavaFX SDK with platform-specific versions.

---

# JavaFX 物理仿真实验项目

## 项目概述
本项目基于 JavaFX 实现了一个物理仿真，包括：
- 楞次定律的可视化。
- 交互式电磁场仿真。

该项目已集成 JDK 和 JavaFX SDK，能够独立运行，无需额外安装其他依赖。

---

## 项目结构
```
项目/
├── jdk-21.0.6.7-hotspot/          # 集成的 JDK
│   ├── bin/
│   ├── lib/
│   └── ...
├── javafx-sdk/                    # JavaFX SDK
│   ├── bin/                       # JavaFX 本地库（DLL 文件）
│   ├── lib/                       # JavaFX JAR 文件
│   └── ...
├── src/                           # 源代码
│   ├── LenzLawFXWithPointers.java
│   └── TestFX.java
├── bin/                           # 编译后的 class 文件
├── README.md                      # 本文件
```

---

## 环境要求
- **无需安装外部 JDK 或 JavaFX。**
- 项目自带所有依赖项。

---

## 使用说明
### **1. 编译代码**
在项目根目录下运行以下命令：
```bash
jdk-21.0.6.7-hotspot\bin\javac -d bin --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml src\LenzLawFXWithPointers.java
```

### **2. 运行程序**
编译成功后，运行以下命令启动程序：
```bash
jdk-21.0.6.7-hotspot\bin\java --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml -Djava.library.path="javafx-sdk\bin" -cp bin LenzLawFXWithPointers
```

---

## 注意事项
- 确保解压缩项目后再运行命令。
- 如果遇到错误，请确保系统支持 JavaFX 图形渲染。
- 如果在其他平台（如 macOS 或 Linux）运行，请替换项目中的 JDK 和 JavaFX SDK 为对应平台的版本。

