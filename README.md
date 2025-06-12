# Veterinary Hotel Management System

**Object-Oriented Programming Project**
A terminal-based Veterinary Hotel Management System written in Java.

## 📋 Overview

This application allows users to manage a Veterinary Hotel, handling animals, employees, habitats, vaccines, and more. It is a text-based system designed for an Object-Oriented Programming course and is fully implemented in Java with a modular architecture.

> ⚠️ The interface and internal documentation are in Portuguese.

## ✅ Features

* **File Management** – Create, open, and save hotel state files.
* **Season Management** – Advance the current season in the simulation.
* **Global Satisfaction** – Calculate and display overall guest (animal) satisfaction.
* **Animal Management** – Add, update, and manage animal information.
* **Employee Management** – Manage hotel staff including handlers and veterinarians.
* **Habitat Management** – Create and organize habitats for animals.
* **Vaccine Management** – Manage vaccine stock and applications.
* **Consultations** – View reports of vaccinations and animal care history.
* **Search Functionality** – Search through animals, staff, and habitats.

## 🧾 Main Menu Example

Main Menu
1 - New File
2 - Open File
3 - Save File
4 - Next Season
5 - Calculate Global Satisfaction
6 - Manage Animals
7 - Manage Employees
8 - Manage Habitats
9 - Manage Vaccines
10 - Search
0 - Exit

Choose an option:


## 🧠 Architecture

### 📦 Modules

* **`hva-app`** – Terminal interface; handles user interactions and menu flow.
* **`hva-core`** – Core logic; handles creation, search, and business rules.
* **`po-uilib`** – External library for UI support; required for running the application.

## 📐 UML Design

Includes an initial **UML design** outlining class structures and relationships.
> ℹ️ It serves as a base and does **not** fully reflect the final system.

## 🚀 Getting Started

### 🔧 Compilation

Use `make` to compile the project:
```bash
make
🛠️ Set the Classpath
Set your CLASSPATH with the correct paths (replace /path_to_the_directory accordingly):

Bash

export CLASSPATH=/path_to_the_directory/po-uilib/po-uilib.jar:/path_to_the_directory/hva-core/hva-core.jar:/path_to_the_directory/hva-app/hva-app.jar
▶️ Run the App
Bash

java hva.app.App
💉 Requirements
To run the application, you must install the po-uilib library.

📦 Installation (openSUSE Tumbleweed)
Bash

zypper ar [https://download.opensuse.org/repositories/home:/d4vid:/po24/openSUSE_Tumbleweed/](https://download.opensuse.org/repositories/home:/d4vid:/po24/openSUSE_Tumbleweed/) PO24
zypper install po-uilib
Alternatively, you can install it manually from:

Repository Mirror 1
Repository Mirror 2
📚 Project Summary
This project showcases fundamental object-oriented design principles applied to a real-world scenario: managing a veterinary hotel. It includes modularization, file I/O, state management, and a menu-based terminal UI.
