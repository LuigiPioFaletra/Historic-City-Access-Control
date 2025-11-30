# Java Project – Historic City Access Control

This repository contains the project developed for the **Software Engineering / Programming** university exam.  
The goal of the project is to design and implement an **application for controlling vehicle access** to the historic centers of cities, allowing only **eco-friendly vehicles**.

---

## Repository Structure

```
main_repository/
│
├── implementation/
│ ├── Autobus.java
│ ├── Automobile.java
│ ├── Camion.java
│ ├── Ciclomotore.java
│ ├── Client.java
│ ├── Controlli.java
│ ├── ControlloAccessi.java
│ ├── Motociclo.java
│ ├── Proprietario.java
│ ├── Server.java
│ ├── Targa.java
│ ├── Triciclo.java
│ ├── Veicolo.java
│ └── VeicoloEcologico.java
│
├── description.pdf
├── LICENSE
└── README.md
```

---

## Project Objective

The aim of this project is to design a **system that controls vehicle access** to the historic center of a city, allowing only vehicles that comply with eco-friendly standards. Key features include:

- Management of multiple **vehicle types**: car, van, bus, motorcycle, moped, and tricycle  
- Tracking of **vehicle attributes** such as license plate, brand, fuel type, engine capacity, and eco-friendly category  
- **Owner information management** for issuing fines if vehicles do not comply  
- Enforcement of **eco-criteria**, varying according to vehicle type and fuel  
- **Concurrent management** of access for multiple cities via a client-server architecture  

---

## Conceptual & Logical Design

The design uses **object-oriented programming** with inheritance and interfaces:

- **Superclass `Veicolo`**: contains common attributes for all vehicle types  
- **Subclasses**: `Autobus`, `Automobile`, `Camion`, `Ciclomotore`, `Motociclo`, `Triciclo`  
- **Interface `VeicoloEcologico`**: enforces methods to classify vehicles and calculate fines  

Other supporting classes include:

- `Targa` – holds license plate and owner information  
- `Proprietario` – owner details including name, city, license type, and fines  
- `ControlloAccessi` – determines if a vehicle can access the historic center  
- `Client` & `Server` – implement concurrent, multi-city access control  
- `Controlli` – validates all input data for correctness  

---

## Physical Design & Implementation

The project has been implemented in **Java** and includes:

- Definition of all classes and their methods  
- Implementation of inheritance, interfaces, and encapsulation  
- Fines calculation based on eco-classification  
- Concurrent client-server architecture for multi-city access control  
- File-based data storage for vehicles and access logs  

### How to run the application

1. Compile all Java files:
```bash
javac *.java
```

2. Start the server:
```bash
java Server
```

3. Start one or more clients in separate terminals:
```bash
java Client
```

4. Follow the on-screen prompts to insert vehicle and owner information and check access compliance.

---

## Classes Overview

- **Autobus** – number of floors, classification, fine calculation
- **Automobile** – number of doors, classification, fine calculation
- **Camion** – cargo weight, classification, fine calculation
- **Ciclomotore** – max speed, classification, fine calculation
- **Motociclo** – number of gears, classification, fine calculation
- **Triciclo** – type of gearbox, classification, fine calculation
- **Veicolo** – base class for shared vehicle attributes
- **VeicoloEcologico** – interface for ecological classification
- **Targa** – license plate and owner data
- **Proprietario** – vehicle owner data and fines
- **Client / Server** – multi-threaded client-server implementation
- **Controlli** – input validation
- **ControlloAccessi** – access verification

---

## Testing

The application has been tested to ensure:
- Correct classification of vehicles according to eco-criteria
- Accurate fine calculation based on fuel type and category
- Proper functioning of concurrent client-server architecture
- Validation of input data and license plate formats

---

## Execution

Once the server and client are running, the system can:
- Accept vehicle entries
- Determine access eligibility
- Record results in text files per city
- Issue fines if necessary

---

### License

This project is licensed under the terms of the MIT license. You can find the full license in the `LICENSE` file.
