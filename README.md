# JavaFX Physics Simulation Project

## Overview
This project demonstrates a physics simulation using JavaFX, including:
- Lenz's Law visualization.
- Interactive electromagnetic field simulation.

**Note:**  
This project **no longer includes a bundled JDK**.  
Users need to **install JDK 21+ manually**, but the project still includes JavaFX SDK.

---

## Project Structure
```
Project/
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
- **JDK 21 or higher**
  - Download from [Adoptium](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)
- **JavaFX SDK** (included in the project)

---

## Usage Instructions
### **1. Compile the Code**
Run the following command in the project root directory:
```bash
javac -d bin --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml src\LenzLawFXWithPointers.java
```

### **2. Run the Program**
After successful compilation, run the program with:
```bash
java --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml -Djava.library.path="javafx-sdk\bin" -cp bin LenzLawFXWithPointers
```

---

## Notes
- Ensure the project is extracted fully before running the commands.
- If you encounter any errors, ensure your system supports JavaFX graphics rendering.
- If running on a different platform (e.g., macOS or Linux), replace the JDK with a platform-specific version.

---

# JavaFX 物理仿真实验项目

## 项目概述
本项目基于 JavaFX 实现了一个物理仿真，包括：
- 楞次定律的可视化。
- 交互式电磁场仿真。

**注意：**  
该项目**不再包含 JDK**，但仍**包含 JavaFX SDK**，请用户**自行下载安装 JDK**。

---

## 项目结构
```
项目/
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
- **JDK 21 或更高版本**  
  - 下载：[Adoptium](https://adoptium.net/) 或 [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)
- **JavaFX SDK**（已包含在项目中）

---

## 使用说明
### **1. 编译代码**
在项目根目录下运行以下命令：
```bash
javac -d bin --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml src\LenzLawFXWithPointers.java
```

### **2. 运行程序**
编译成功后，运行以下命令启动程序：
```bash
java --module-path javafx-sdk\lib --add-modules javafx.controls,javafx.fxml -Djava.library.path="javafx-sdk\bin" -cp bin LenzLawFXWithPointers
```

---

## 注意事项
- 确保解压缩项目后再运行命令。
- 如果遇到错误，请确保系统支持 JavaFX 图形渲染。
- 如果在其他平台（如 macOS 或 Linux）运行，请替换 JDK 为对应平台的版本。


