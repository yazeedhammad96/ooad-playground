# OOAD Playground

A LeetCode-style platform for practicing Object-Oriented Analysis and Design (OOAD). Master OOP principles, SOLID design, and design patterns through hands-on coding challenges.

## 🎯 What is This?

This repository provides:
- **OOAD Practice Problems**: LeetCode-style questions focused on object-oriented design
- **Complete Solutions**: Well-structured implementations demonstrating best practices
- **Comprehensive Guide**: In-depth reference covering all OOAD concepts
- **Evaluation Criteria**: Score your solutions on Encapsulation, Inheritance, Polymorphism, Abstraction, SOLID principles, and Clean Code

## 📚 Contents

### Practice Problems

#### 1. Library Management System
**Difficulty**: Medium

Design a library management system with books, members, and borrowing operations.

**Requirements:**
- Books with ISBN, title, author, availability tracking
- Members with different membership types (Regular/Premium)
- Borrowing limits based on membership
- Search functionality

**Evaluation Criteria:**
- Encapsulation (0-10)
- Inheritance (0-10)
- Polymorphism (0-10)
- Abstraction (0-10)
- SOLID Principles (0-10)
- Clean Code & Readability (0-10)

**Solution Structure:**
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
Your comprehensive reference for mastering OOAD:
- **Four Pillars of OOP**: Encapsulation, Abstraction, Inheritance, Polymorphism
- **Object Relationships**: Composition, Aggregation, Association, Dependency
- **SOLID Principles**: With real-world examples
- **Design Patterns**: Creational, Structural, Behavioral
- **Anti-Patterns & Code Smells**: What to avoid and why
- **Best Practices**: DRY, YAGNI, Law of Demeter, Composition over Inheritance

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

## 🎯 How to Use This Repository

1. **Read the problem statement** in the Question.java file
2. **Design your solution** considering all OOAD principles
3. **Implement your code** with proper package structure
4. **Compare with the provided solution** and evaluate yourself
5. **Study the OOAD guide** to deepen your understanding

## 🏆 Learning Objectives

- Master OOP fundamentals through practice
- Apply SOLID principles in real-world scenarios
- Learn when to use composition vs inheritance
- Recognize and avoid common anti-patterns
- Write clean, maintainable, production-ready code

## 🔜 Coming Soon

More OOAD practice problems:
- Parking Lot System
- Hotel Reservation System
- E-commerce Shopping Cart
- Social Media Platform
- File System Design

## 🤖 Generate Your Own Questions

This repository includes a **prompt template** to generate new OOAD practice problems using AI assistants (ChatGPT, Claude, etc.).

**How to use:**
1. Read the prompt template in [OOAD_PROMPT.MD](OOAD_PROMPT.MD)
2. Copy and paste it to your AI assistant
3. Request a new OOAD problem (e.g., "Generate a Parking Lot System problem")
4. Implement your solution
5. Get evaluated on OOAD principles, SOLID, and clean code

**The prompt helps you:**
- Generate LeetCode-style OOAD questions with clear requirements
- Get structured feedback on your design (scored 0-10 on each principle)
- Learn from detailed evaluations and improvement suggestions
- Compare your solution with reference implementations

📝 [View the Prompt Template](OOAD_PROMPT.MD)

## 🤝 Contributing

Want to add more OOAD problems? PRs welcome!

## 📝 License

This project is for educational purposes.
