# OOAD Playground

A collection of Object-Oriented Analysis and Design (OOAD) practice problems and solutions, demonstrating core OOP principles, SOLID principles, and design patterns.

## 📚 Contents

### Library Management System
A complete implementation showcasing:
- **Encapsulation**: Private fields with controlled access
- **Abstraction**: Interface-based design (MembershipType)
- **Polymorphism**: Strategy pattern for membership types
- **Composition**: Flexible membership behavior
- **SOLID Principles**: Clean, maintainable architecture

**Package Structure:**
```
org.learning.librarymanagement/
├── model/              # Domain entities
│   ├── Book.java
│   ├── Member.java
│   ├── MembershipType.java
│   ├── RegularMembership.java
│   └── PremiumMembership.java
└── service/            # Business logic
    └── Library.java
```

### OOAD Complete Guide
Comprehensive reference covering:
- Four Pillars of OOP
- Object Relationships (Composition, Aggregation, Association)
- SOLID Principles with examples
- Common Design Patterns
- Anti-Patterns & Code Smells
- Best Practices

📖 [Read the Complete Guide](OOAD-Complete-Guide.md)

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Build & Run
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.learning.librarymanagement.Question"
```

## 🎯 Learning Objectives

- Master OOP fundamentals
- Apply SOLID principles in real-world scenarios
- Understand when to use composition vs inheritance
- Recognize and avoid common anti-patterns
- Write clean, maintainable code

## 📝 License

This project is for educational purposes.
