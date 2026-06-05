# 🏡 Smart Home Controller

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit4-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Eclipse IDE](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)

A robust, interactive desktop application built in Java to simulate and manage a smart home ecosystem. This application allows users to seamlessly add, control, and monitor various smart devices (Lights, Thermostats, Air Conditioners, and Security Cameras) from a centralized dashboard.

This project was developed as a comprehensive implementation of core Software Engineering principles, specifically focusing on advanced programming paradigms.

---

## ✨ Core Features & Implementations

This project strictly adheres to professional software construction requirements:

- **🖱️ Advanced Event Handling**
  - Fully interactive Java Swing GUI.
  - Buttons, sliders, and forms dynamically trigger system responses.
  - Utilizes the Delegation Event Model for event-driven interactions.

- **🛡️ Exception Handling**
  - Robust error management using custom exceptions (e.g., `InvalidInputException`).
  - Gracefully handles invalid inputs and file operation errors.
  - Prevents runtime crashes using `try-catch-finally` blocks.

- **🏗️ Code Refactoring (Clean Architecture)**
  - Refactored from a monolithic structure into a modular architecture.
  - Applied the **Factory Design Pattern** (`DeviceFactory.java`) for device creation.
  - Reduced code duplication and improved maintainability.

- **✅ Automated Unit Testing**
  - Comprehensive testing powered by **JUnit 4**.
  - Covers both positive and negative test scenarios.
  - Ensures reliability and correctness of business logic.

- **💾 Data Persistence**
  - File-based storage using `devices.txt`.
  - Saves and restores device configurations and states across sessions.

---

## 📸 Application Showcase

### 1. Dashboard & Navigation

| Welcome Screen | Home Dashboard (All Devices) |
| :---: | :---: |
| <img src="GUI%20Screenshots/Welcome%20Screen.png" width="400"> | <img src="GUI%20Screenshots/Home%20Screen%20(All%20Device).png" width="400"> |

### 2. Device Controls & Features

| Light Control (Brightness Slider) | AC Control (Temperature Spinner) |
| :---: | :---: |
| <img src="GUI%20Screenshots/Device%20Detail%20Screen%20(Light%20On).png" width="400"> | <img src="GUI%20Screenshots/Device%20Detail%20Screen%20(AC%20On).png" width="400"> |

### 3. System Operations

| Adding a New Device | Exception Handling & Validation |
| :---: | :---: |
| <img src="GUI%20Screenshots/Add%20Device%20select-device-type.png" width="400"> | <img src="GUI%20Screenshots/Add%20Device%20set-device-name.png" width="400"> |

### 4. Automated Testing (JUnit)

![JUnit Test Pass](GUI%20Screenshots/JUnit-Test.png)

*Green bar indicating 100% successful execution of positive and negative test cases.*

---

## 🚀 Setup & Installation Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/smart-home-controller.git
```

### 2. Open in IDE

Import the project into **Eclipse IDE** (or any preferred Java IDE such as IntelliJ IDEA or NetBeans).

### 3. Configure Dependencies

Ensure **JUnit 4** is added to your project's Build Path for running automated tests.

### 4. Run the Application

- Locate `SmartHomeController.java` in the `src` directory.
- Right-click the file.
- Select **Run As → Java Application**.

### 5. Run the Unit Tests

- Locate `SmartDeviceTest.java`.
- Right-click the file.
- Select **Run As → JUnit Test**.

---

## 🛠️ Technologies Used

- Java
- Java Swing
- JUnit 4
- Eclipse IDE
- Object-Oriented Programming (OOP)
- Factory Design Pattern
- File Handling & Data Persistence

---

## 📂 Project Structure

```text
Smart-Home-Controller/
│
├── src/
│
├── GUI Screenshots/
│
├── devices.txt
│
└── README.md
```

---

## 🎯 Learning Outcomes

This project demonstrates practical implementation of:

- Object-Oriented Programming (OOP)
- Event-Driven Programming
- Exception Handling
- Design Patterns (Factory Pattern)
- Unit Testing with JUnit
- File Handling and Persistence
- Software Refactoring Techniques
- GUI Development using Java Swing

---

## 👨‍💻 Developer Information

**Developer:** Laheem Ayub

**Program:** BS Software Engineering (BSSE)

**Institution:** University of Central Punjab (UCP)

**Developed For:** Software Construction & Development (SCD) Lab

---

## 📄 License

This project is developed for academic and educational purposes.
