<div align="center">

# 🛠️ Gradle Commands Cheat Sheet 🚀

*A comprehensive guide to Gradle commands for Kotlin/Java projects*

![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

</div>

## 📋 Table of Contents
- [🏗️ Build & Run](#-build--run)
- [🧪 Testing](#-testing)
- [📦 Packaging](#-packaging)
- [📦 Dependency Management](#-dependency-management)
- [🔍 Code Quality](#-code-quality)
- [📚 Documentation](#-documentation)
- [💻 IDE Integration](#-ide-integration)
- [⚡ Advanced Commands](#-advanced-commands)
- [💡 Tips & Tricks](#-tips--tricks)

---

## 🏗️ Build & Run

### 🧹 Clean Build
```bash
./gradlew clean
```
> 🗑️ Removes the `build` directory, including all built artifacts.

### 🔨 Build Project
```bash
./gradlew build
```
> 🏭 Compiles, tests, and packages your project.

### 🚀 Run Application
```bash
# Run the main application
./gradlew bootRun

# Run with specific Spring profile
./gradlew bootRun --args='--spring.profiles.active=dev'
```
> ⚡ Starts your Spring Boot application.

---

## 🧪 Testing

### 🧪 Run All Tests
```bash
./gradlew test
```

### 🎯 Run Specific Tests
```bash
# Run a specific test class
./gradlew test --tests com.example.YourTestClass

# Run a specific test method
./gradlew test --tests com.example.YourTestClass.yourTestMethod
```

### 📊 Test Report
```bash
# Generate test reports
./gradlew test
# View report at: build/reports/tests/test/index.html
```

---

## 📦 Packaging

### 📦 Create Executable JAR
```bash
./gradlew bootJar
```
> 📁 Output: `build/libs/*.jar`

### 📦 Create Distribution
```bash
./gradlew assembleDist
```
> 📁 Creates a distribution in `build/distributions/`

---

## 📦 Dependency Management

### 📋 List Dependencies
```bash
# List all dependencies
./gradlew dependencies

# List dependencies for a specific configuration
./gradlew dependencies --configuration compileClasspath
```

### 🔄 Refresh Dependencies
```bash
./gradlew --refresh-dependencies build
```
> ♻️ Forces a refresh of all project dependencies.

---

## 🔍 Code Quality

### 🎨 Checkstyle
```bash
./gradlew checkstyleMain checkstyleTest
# Report: build/reports/checkstyle/
```

### 🐛 SpotBugs
```bash
./gradlew spotbugsMain spotbugsTest
# Report: build/reports/spotbugs/
```

### 📊 PMD
```bash
./gradlew pmdMain pmdTest
# Report: build/reports/pmd/
```

---

## 📚 Documentation

### 📝 Generate Javadoc
```bash
./gradlew javadoc
# Output: build/docs/javadoc/
```

### 📖 Generate KDoc (Kotlin)
```bash
./gradlew dokkaHtml
# Output: build/dokka/
```

---

## 💻 IDE Integration

### 🛠️ Generate IDE Files
```bash
# For IntelliJ IDEA
./gradlew idea

# For Eclipse
./gradlew eclipse
```

---

## ⚡ Advanced Commands

### 👟 Dry Run
```bash
./gradlew build --dry-run
```
> 👀 Shows which tasks would be executed without running them.

### 🔍 Build Scan
```bash
./gradlew build --scan
```
> 📊 Generates a detailed build scan at [scans.gradle.com](https://scans.gradle.com)

### 📜 List All Tasks
```bash
./gradlew tasks --all
```

---

## 💡 Tips & Tricks

### 🏃‍♂️ Quick Commands
```bash
# Skip tests during build
./gradlew build -x test

# Run with info/debug logging
./gradlew build --info
./gradlew build --debug

# Run specific task only
./gradlew :module:taskName
```

### 🖥️ Windows Users
```bash
# Use .bat extension on Windows
gradlew.bat build
```

### 🐳 Docker Support
```bash
# Build a Docker image (if using Spring Boot 2.3+)
./gradlew bootBuildImage
```

<div align="center" style="margin-top: 2rem;">
  <p>✨ Happy Coding! ✨</p>
  <p>Made with ❤️ for developers</p>
</div>
