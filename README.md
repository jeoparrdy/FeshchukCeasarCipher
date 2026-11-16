# 🔐 Caesar Cipher

A Java console application for encrypting and decrypting text files using the **Caesar Cipher algorithm**.

The application supports both interactive CLI mode and command-line arguments, providing a simple interface for working with encrypted text files.

## ✨ Features

- 🔒 **Encryption** — encrypt text files using a specified shift key.
- 🔓 **Decryption** — decrypt previously encrypted files.
- 🔍 **Brute Force** — support for attempting decryption using different shift keys.
- 💻 **Interactive CLI** — menu-driven interface for selecting operations.
- ⚡ **Command-Line Arguments** — execute operations directly from the terminal.
- ✅ **Input Validation** — validate commands, file paths, and encryption keys.
- 📁 **File Management** — automatically generate output filenames without modifying the original files.

## 🚀 Getting Started

### Running the Application

The application supports two execution modes.

#### 1. Interactive CLI Mode

Launch the application without arguments:

```bash
java -jar caesar-cipher.jar
```

The interactive menu will appear:

```text
Welcome to Caesar Cipher

Please make your choice:
1. Encrypt file
2. Decrypt file
3. Brute force
4. Exit program
```

Select an operation and provide the required parameters.

#### 2. Command-Line Mode

Launch the application with arguments:

```bash
java -jar caesar-cipher.jar <COMMAND> <FILE_PATH> [KEY]
```

**Available commands:**

| Command | Description | Key |
|---------|-------------|-----|
| `ENCRYPT` | Encrypt a text file | Required |
| `DECRYPT` | Decrypt a text file | Required |
| `BRUT_FORCE` | Attempt decryption using different keys | Not required |

**Examples:**

Encrypt a file:

```bash
java -jar caesar-cipher.jar ENCRYPT input.txt 5
```

Decrypt a file:

```bash
java -jar caesar-cipher.jar DECRYPT "input[ENCRYPTED].txt" 5
```

Run brute-force decryption:

```bash
java -jar caesar-cipher.jar BRUT_FORCE "input[ENCRYPTED].txt"
```

> **Note:** Enclose filenames containing square brackets or spaces in quotes when necessary for your shell.

## 📂 Output File Naming

The application creates new output files in the same directory as the source file.

| Operation | Input File | Output File |
|-----------|------------|-------------|
| Encryption | `input.txt` | `input[ENCRYPTED].txt` |
| Decryption | `input[ENCRYPTED].txt` | `input[ENCRYPTED][DECRYPTED].txt` |
| Encryption | `document.txt` | `document[ENCRYPTED].txt` |
| Decryption | `document.txt` | `document[DECRYPTED].txt` |

The original file remains unchanged. Output files are not overwritten when the create-new write policy is enabled.

## 🏗️ Project Architecture

The application follows a modular architecture with separated responsibilities.

```text
Main
 ├── Menu (Interactive Mode)
 │    └── ArgumentsValidator
 │
 └── ArgumentsValidator (Command-Line Mode)
             │
             ▼
      Commands (Enum)
             │
      ┌──────┼──────────┐
      ▼      ▼          ▼
  Encrypter Decrypter BrutForcer
      │      │          │
      └──────┼──────────┘
             ▼
        FileService
             │
             ▼
       Text Files
```

## 🔐 How Caesar Cipher Works

The Caesar Cipher is a substitution cipher that shifts each letter by a fixed number of positions in the alphabet.

For example, using a shift key of `3`:

**Original:**

```text
HELLO WORLD
```

**Encrypted:**

```text
KHOOR ZRUOG
```

**Decrypted:**

```text
HELLO WORLD
```

The shift key is normalized using:

```java
int normalizedKey = Math.floorMod(key, 26);
```

This ensures that the effective shift remains within the range `0–25`, including when negative keys are provided.

## 🛡️ Input Validation

The application validates user input before executing operations.

Validation includes:

- Supported command names
- Correct number and order of arguments
- File existence and readability
- Valid integer encryption keys
- Command-specific key requirements

Invalid input produces an error message rather than executing an operation with incorrect parameters.
