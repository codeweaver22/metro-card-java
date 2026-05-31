# Metro Card Challenge

## Overview

This project is a Java command-line application that simulates a metro card ticketing system.

The application processes commands from an input file and generates station-wise collection summaries, including passenger counts, discounts applied, and recharge fees collected.

The solution is implemented using object-oriented design principles with a focus on maintainability, testability, and clear separation of concerns.

---

## Features

* Metro card balance management
* Passenger fare calculation
* Return journey discount handling
* Automatic recharge with service fee
* Station-wise collection tracking
* Passenger type summary generation
* Command-line execution
* Unit test coverage for core business logic

---

## Domain Model

### MetroCard

Represents a metro card and maintains:

* Card ID
* Available balance
* Return journey status

### StationSummary

Maintains station-level statistics:

* Total collection
* Total discount provided
* Passenger counts by passenger type

### MetroSummary

Aggregates station summaries for all stations.

### PassengerType

Supported passenger categories:

* ADULT
* SENIOR_CITIZEN
* KID

### Station

Supported stations:

* CENTRAL
* AIRPORT

---

## Project Structure

```text
src/main/java
├── entity
│   ├── MetroCard
│   ├── MetroSummary
│   ├── PassengerType
│   ├── Station
│   └── StationSummary
│
├── service
│   ├── BalanceService
│   ├── MetroCardProcessor
│   └── OutputService
│
└── util
    ├── DiscountCalculator
    └── ServiceFeeCalculator
```

---

## Design Decisions

### State + Transitions + Rules

The solution was designed around:

* State

  * Metro card balances
  * Journey status
  * Station summaries

* Transitions

  * BALANCE
  * CHECK_IN
  * PRINT_SUMMARY

* Rules

  * Fare calculation
  * Return journey discount
  * Recharge service fee
  * Passenger counting

This approach keeps business rules explicit and easy to maintain.

### Testability

Output generation was separated from console printing to simplify unit testing.

### Enums for Domain Concepts

Enums are used for:

* PassengerType
* Station

This provides type safety and avoids string-based errors.

### computeIfAbsent for Aggregates

Station summaries are created lazily using `computeIfAbsent()` to ensure consistent aggregate management.

---

## Assumptions

* Card IDs are unique.
* Input commands are valid.
* Passenger fares are fixed.
* Return journeys receive a 50% discount.
* Recharge service fee is applied according to challenge requirements.

---

## Building the Project

```bash
mvn clean package
```

---

## Running the Application

```bash
java -jar target/metro-card.jar <input-file>
```

Example:

```bash
java -jar target/metro-card.jar sample_input.txt
```

---

## Running Tests

```bash
mvn test
```

---

## Sample Input

```text
BALANCE MC1 600
BALANCE MC2 500
CHECK_IN MC1 ADULT CENTRAL
CHECK_IN MC2 SENIOR_CITIZEN AIRPORT
PRINT_SUMMARY
```

---

## Technologies Used

* Java 21
* Maven
* JUnit 5
* IntelliJ IDEA

---

## Learning Outcomes

This exercise helped practice:

* Object-oriented design
* Domain modeling
* State management
* Command processing
* Unit testing
* Refactoring for testability
* Git and GitHub workflows

```
```
