# The Complete OOAD Guide: From Principles to Practice

## Table of Contents
1. [The Four Pillars of OOP](#the-four-pillars-of-oop)
2. [Relationships Between Objects](#relationships-between-objects)
3. [SOLID Principles](#solid-principles)
4. [Common Design Patterns](#common-design-patterns)
5. [Design Principles & Best Practices](#design-principles--best-practices)
6. [Anti-Patterns & Code Smells](#anti-patterns--code-smells)

---

## The Four Pillars of OOP

### **Encapsulation**
Bundling data and methods that operate on that data within a single unit, hiding internal details.

```java
// BAD: Exposed internals
public class BankAccount {
    public double balance; // Anyone can modify!
}

// GOOD: Controlled access
public class BankAccount {
    private double balance; // Hidden
    
    public void deposit(double amount) {
        if (amount > 0) balance += amount; // Validation
    }
    
    public double getBalance() {
        return balance; // Controlled read
    }
}
```

**Benefits:**
- Data protection
- Flexibility to change implementation
- Easier maintenance

---

### **Abstraction**
Hiding complex implementation details, exposing only what's necessary.

```java
// Interface defines WHAT, not HOW
public interface PaymentProcessor {
    boolean processPayment(double amount);
}

// Implementations hide complexity
public class StripePayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Complex Stripe API calls hidden here
        return true;
    }
}

// User doesn't need to know HOW payment works
PaymentProcessor processor = new StripePayment();
processor.processPayment(100.0); // Simple!
```

**Key difference from Encapsulation:**
- **Encapsulation** = hiding data (implementation detail)
- **Abstraction** = hiding complexity (design level)

---

### **Inheritance**
Creating new classes based on existing ones, inheriting their properties and behaviors.

```java
// Base class
public abstract class Employee {
    protected String name;
    protected double baseSalary;
    
    public abstract double calculateSalary();
    
    public void clockIn() {
        System.out.println(name + " clocked in");
    }
}

// Derived classes
public class FullTimeEmployee extends Employee {
    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}

public class ContractEmployee extends Employee {
    private int hoursWorked;
    
    @Override
    public double calculateSalary() {
        return hoursWorked * baseSalary;
    }
}
```

**When to use:**
- True "is-a" relationship (ContractEmployee IS-A Employee)
- Shared behavior across subclasses
- Need to override specific behaviors

**When NOT to use:**
- Just for code reuse (use composition)
- Relationship is "has-a" or "uses-a"
- Behavior varies independently (use Strategy pattern)

---

### **Polymorphism**
Objects of different types responding to the same interface in different ways.

```java
// Same method call, different behaviors
List<Employee> employees = Arrays.asList(
    new FullTimeEmployee(),
    new ContractEmployee()
);

for (Employee emp : employees) {
    emp.calculateSalary(); // Different calculation for each type!
}
```

**Types:**
1. **Compile-time (Overloading)**: Same method name, different parameters
2. **Runtime (Overriding)**: Subclass provides specific implementation

---

## Relationships Between Objects

### **Association**
General relationship where objects are aware of each other.

```java
public class Teacher {
    private List<Student> students; // Teacher knows Students
}

public class Student {
    private List<Teacher> teachers; // Student knows Teachers
}
```

---

### **Aggregation (Weak "has-a")**
Container and contents have independent lifecycles.

```java
public class Department {
    private List<Professor> professors; // Shared reference
    
    public void addProfessor(Professor prof) {
        professors.add(prof); // Professor exists independently
    }
}
// If Department is deleted, Professors still exist
```

**Characteristics:**
- Hollow diamond in UML: ◇———
- Parts can exist without the whole
- Shared ownership possible

---

### **Composition (Strong "has-a")**
Container owns the parts; parts cannot exist without container.

```java
public class Car {
    private final Engine engine; // Car OWNS engine
    
    public Car() {
        this.engine = new Engine(); // Created with Car
    }
}
// If Car is destroyed, Engine is destroyed too
```

**Characteristics:**
- Filled diamond in UML: ◆———
- Parts cannot exist independently
- Exclusive ownership

---

### **Dependency**
One class uses another temporarily (weakest relationship).

```java
public class EmailService {
    public void sendEmail(EmailMessage message) {
        // Uses EmailMessage but doesn't store it
    }
}
```

---

### **Quick Decision Tree:**

```
Does ClassA use ClassB?
├─ Only as method parameter/local variable? → Dependency
└─ Stores reference to ClassB?
   ├─ Can ClassB exist without ClassA? → Aggregation
   └─ ClassB dies when ClassA dies? → Composition
```

---

## SOLID Principles

### **S - Single Responsibility Principle**
A class should have only ONE reason to change.

```java
// BAD: Multiple responsibilities
public class User {
    public void saveToDatabase() { }
    public void sendEmail() { }
    public void generateReport() { }
}

// GOOD: Separated concerns
public class User {
    private String name;
    private String email;
}

public class UserRepository {
    public void save(User user) { }
}

public class EmailService {
    public void sendEmail(User user) { }
}

public class ReportGenerator {
    public void generate(User user) { }
}
```

---

### **O - Open/Closed Principle**
Open for extension, closed for modification.

```java
// BAD: Must modify for new types
public class AreaCalculator {
    public double calculate(Object shape) {
        if (shape instanceof Circle) {
            // calculate circle
        } else if (shape instanceof Rectangle) {
            // calculate rectangle
        }
        // Must modify this method for new shapes!
    }
}

// GOOD: Extend without modifying
public interface Shape {
    double calculateArea();
}

public class Circle implements Shape {
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class Rectangle implements Shape {
    @Override
    public double calculateArea() {
        return width * height;
    }
}

// Add new shapes without changing existing code!
```

---

### **L - Liskov Substitution Principle**
Subtypes must be substitutable for their base types.

```java
// BAD: Violates LSP
public class Bird {
    public void fly() { }
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException(); // Breaks contract!
    }
}

// GOOD: Proper hierarchy
public abstract class Bird {
    public abstract void move();
}

public class FlyingBird extends Bird {
    @Override
    public void move() {
        fly();
    }
    
    private void fly() { }
}

public class Penguin extends Bird {
    @Override
    public void move() {
        swim();
    }
    
    private void swim() { }
}
```

---

### **I - Interface Segregation Principle**
Clients shouldn't depend on interfaces they don't use.

```java
// BAD: Fat interface
public interface Worker {
    void work();
    void eat();
    void sleep();
}

public class Robot implements Worker {
    public void work() { }
    public void eat() { } // Robot doesn't eat!
    public void sleep() { } // Robot doesn't sleep!
}

// GOOD: Segregated interfaces
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Sleepable {
    void sleep();
}

public class Human implements Workable, Eatable, Sleepable {
    // Implements all
}

public class Robot implements Workable {
    // Only implements what it needs
}
```

---

### **D - Dependency Inversion Principle**
Depend on abstractions, not concretions.

```java
// BAD: High-level depends on low-level
public class EmailNotification {
    public void send(String message) { }
}

public class UserService {
    private EmailNotification notification = new EmailNotification(); // Tight coupling!
}

// GOOD: Both depend on abstraction
public interface NotificationService {
    void send(String message);
}

public class EmailNotification implements NotificationService {
    @Override
    public void send(String message) { }
}

public class SMSNotification implements NotificationService {
    @Override
    public void send(String message) { }
}

public class UserService {
    private final NotificationService notification; // Depends on abstraction
    
    public UserService(NotificationService notification) {
        this.notification = notification; // Injected!
    }
}
```

---

## Common Design Patterns

### **Creational Patterns**

#### **Singleton**
Ensure only one instance exists.

```java
public class Database {
    private static Database instance;
    
    private Database() { } // Private constructor
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
```

#### **Factory**
Create objects without specifying exact class.

```java
public interface Animal {
    void speak();
}

public class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type) {
            case "dog": return new Dog();
            case "cat": return new Cat();
            default: throw new IllegalArgumentException();
        }
    }
}
```

---

### **Structural Patterns**

#### **Adapter**
Make incompatible interfaces work together.

```java
// Existing interface
public interface MediaPlayer {
    void play(String filename);
}

// New incompatible class
public class AdvancedPlayer {
    public void playMp4(String filename) { }
}

// Adapter
public class MediaAdapter implements MediaPlayer {
    private AdvancedPlayer advancedPlayer;
    
    @Override
    public void play(String filename) {
        advancedPlayer.playMp4(filename); // Adapts the call
    }
}
```

#### **Facade**
Simplified interface to complex subsystem.

```java
// Complex subsystems
class CPU { void start() { } }
class Memory { void load() { } }
class HardDrive { void read() { } }

// Facade
public class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public void startComputer() {
        cpu.start();
        memory.load();
        hardDrive.read();
    }
}
```

---

### **Behavioral Patterns**

#### **Strategy**
Define family of algorithms, make them interchangeable.

```java
public interface PaymentStrategy {
    void pay(double amount);
}

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) { }
}

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) { }
}

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(double amount) {
        paymentStrategy.pay(amount); // Delegates to strategy
    }
}
```

#### **Observer**
One-to-many dependency; when one changes, all dependents are notified.

```java
public interface Observer {
    void update(String message);
}

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

---

## Design Principles & Best Practices

### **Composition over Inheritance**
Prefer "has-a" over "is-a" for flexibility.

```java
// BAD: Inheritance for behavior
public class Bird {
    public void fly() { }
}

public class Duck extends Bird { } // What about penguins?

// GOOD: Composition
public interface FlyBehavior {
    void fly();
}

public class Bird {
    private FlyBehavior flyBehavior; // Flexible!
    
    public void performFly() {
        flyBehavior.fly();
    }
}
```

---

### **Program to Interfaces**
Depend on abstractions, not implementations.

```java
// BAD
ArrayList<String> list = new ArrayList<>();

// GOOD
List<String> list = new ArrayList<>(); // Can swap implementation easily
```

---

### **DRY (Don't Repeat Yourself)**
Every piece of knowledge should have a single representation.

```java
// BAD
public double calculateTotalWithTax(double price) {
    return price * 1.08;
}

public double calculateDiscountedPriceWithTax(double price, double discount) {
    return (price - discount) * 1.08; // Tax rate duplicated!
}

// GOOD
private static final double TAX_RATE = 1.08;

public double calculateTotalWithTax(double price) {
    return price * TAX_RATE;
}

public double calculateDiscountedPriceWithTax(double price, double discount) {
    return calculateTotalWithTax(price - discount);
}
```

---

### **YAGNI (You Aren't Gonna Need It)**
Don't add functionality until it's necessary.

```java
// BAD: Over-engineering
public class User {
    private String name;
    private String email;
    private String phone; // Not needed yet
    private Address address; // Not needed yet
    private List<Order> orders; // Not needed yet
    // ... 20 more fields "just in case"
}

// GOOD: Start simple
public class User {
    private String name;
    private String email;
}
// Add more when actually needed
```

---

### **Law of Demeter (Principle of Least Knowledge)**
Only talk to immediate friends.

```java
// BAD: Chain of calls
customer.getWallet().getMoney().getAmount();

// GOOD: Ask, don't dig
customer.getPaymentAmount();
```

---

## Anti-Patterns & Code Smells

### **God Object (Blob)**
One class does everything.

```java
// BAD: God object
public class OrderManager {
    public void createOrder() { }
    public void processPayment() { }
    public void sendEmail() { }
    public void updateInventory() { }
    public void generateInvoice() { }
    public void calculateShipping() { }
    public void validateAddress() { }
    // ... 50 more methods
}

// GOOD: Separated responsibilities
public class OrderService { }
public class PaymentProcessor { }
public class EmailService { }
public class InventoryManager { }
```

**Why it's bad:**
- Hard to maintain and test
- High coupling
- Violates SRP

---

### **Primitive Obsession**
Using primitives instead of small objects.

```java
// BAD: Primitives everywhere
public class User {
    private String email; // No validation
    private String phone; // No formatting
    private int zipCode; // Can be negative!
}

public void sendEmail(String email) {
    // Email validation logic scattered everywhere
}

// GOOD: Value objects
public class Email {
    private final String value;
    
    public Email(String value) {
        if (!isValid(value)) throw new IllegalArgumentException();
        this.value = value;
    }
    
    private boolean isValid(String email) {
        return email.contains("@");
    }
}

public class User {
    private Email email; // Guaranteed valid
    private PhoneNumber phone;
    private ZipCode zipCode;
}
```

**Why it's bad:**
- Validation logic duplicated
- No type safety
- Easy to pass wrong values

---

### **Anemic Domain Model**
Objects with only getters/setters, no behavior.

```java
// BAD: Anemic model
public class Order {
    private double total;
    private String status;
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

public class OrderService {
    public void processOrder(Order order) {
        order.setStatus("PROCESSING");
        double tax = order.getTotal() * 0.08;
        order.setTotal(order.getTotal() + tax);
        // Business logic outside the domain object!
    }
}

// GOOD: Rich domain model
public class Order {
    private Money total;
    private OrderStatus status;
    
    public void process() {
        this.status = OrderStatus.PROCESSING;
        this.total = total.addTax(0.08);
    }
    
    public void cancel() {
        if (status == OrderStatus.SHIPPED) {
            throw new IllegalStateException("Cannot cancel shipped order");
        }
        this.status = OrderStatus.CANCELLED;
    }
}
```

**Why it's bad:**
- Business logic scattered
- Objects are just data containers
- Violates encapsulation

---

### **Shotgun Surgery**
One change requires modifications in many classes.

```java
// BAD: Tax rate hardcoded everywhere
public class OrderCalculator {
    public double calculate(double price) {
        return price * 1.08; // Tax rate
    }
}

public class InvoiceGenerator {
    public double getTotal(double subtotal) {
        return subtotal * 1.08; // Same tax rate duplicated
    }
}

public class RefundProcessor {
    public double calculateRefund(double amount) {
        return amount / 1.08; // Tax rate again!
    }
}
// Changing tax rate requires editing 20+ files!

// GOOD: Centralized
public class TaxCalculator {
    private static final double TAX_RATE = 1.08;
    
    public static double applyTax(double amount) {
        return amount * TAX_RATE;
    }
    
    public static double removeTax(double amount) {
        return amount / TAX_RATE;
    }
}
```

**Why it's bad:**
- High maintenance cost
- Easy to miss changes
- Violates DRY

---

### **Feature Envy**
Method uses another class's data more than its own.

```java
// BAD: Feature envy
public class ShoppingCart {
    public double calculateTotal(Customer customer) {
        double discount = 0;
        if (customer.isPremium()) {
            discount = customer.getDiscountRate();
        }
        if (customer.getLoyaltyPoints() > 1000) {
            discount += 0.05;
        }
        // Using customer data more than cart data!
        return total * (1 - discount);
    }
}

// GOOD: Move behavior to the right place
public class Customer {
    public double getDiscountRate() {
        double discount = isPremium() ? premiumDiscount : 0;
        if (loyaltyPoints > 1000) discount += 0.05;
        return discount;
    }
}

public class ShoppingCart {
    public double calculateTotal(Customer customer) {
        return total * (1 - customer.getDiscountRate());
    }
}
```

**Why it's bad:**
- Wrong responsibility placement
- Tight coupling
- Violates encapsulation

---

### **Magic Numbers/Strings**
Unexplained literals in code.

```java
// BAD: Magic values
public class OrderProcessor {
    public void process(Order order) {
        if (order.getStatus() == 3) { // What is 3?
            order.setDiscount(0.15); // Why 0.15?
        }
        if (order.getTotal() > 100) { // Why 100?
            order.setShipping(0); // Free shipping?
        }
    }
}

// GOOD: Named constants
public class OrderProcessor {
    private static final int STATUS_PREMIUM = 3;
    private static final double PREMIUM_DISCOUNT = 0.15;
    private static final double FREE_SHIPPING_THRESHOLD = 100.0;
    private static final double FREE_SHIPPING_COST = 0.0;
    
    public void process(Order order) {
        if (order.getStatus() == STATUS_PREMIUM) {
            order.setDiscount(PREMIUM_DISCOUNT);
        }
        if (order.getTotal() > FREE_SHIPPING_THRESHOLD) {
            order.setShipping(FREE_SHIPPING_COST);
        }
    }
}
```

**Why it's bad:**
- Unclear intent
- Hard to maintain
- Easy to make mistakes

---

### **Long Parameter List**
Methods with too many parameters.

```java
// BAD: Too many parameters
public void createUser(
    String firstName,
    String lastName,
    String email,
    String phone,
    String address,
    String city,
    String state,
    String zipCode,
    boolean isPremium
) { }

// GOOD: Parameter object
public class UserRegistration {
    private String firstName;
    private String lastName;
    private Email email;
    private Address address;
    private boolean isPremium;
}

public void createUser(UserRegistration registration) { }
```

**Why it's bad:**
- Hard to remember order
- Easy to pass wrong values
- Difficult to test

---

### **Circular Dependencies**
Classes depend on each other.

```java
// BAD: Circular dependency
public class Order {
    private Customer customer;
    
    public void process() {
        customer.updateOrderHistory(this);
    }
}

public class Customer {
    private List<Order> orders;
    
    public void placeOrder(Order order) {
        order.process(); // Calls back to customer!
    }
}

// GOOD: Break the cycle
public class OrderService {
    public void processOrder(Order order, Customer customer) {
        order.process();
        customer.addToHistory(order);
    }
}
```

**Why it's bad:**
- Hard to test
- Tight coupling
- Difficult to understand flow

---

### **Inappropriate Intimacy**
Classes know too much about each other's internals.

```java
// BAD: Too intimate
public class Order {
    public List<Item> items; // Public!
}

public class OrderValidator {
    public boolean validate(Order order) {
        for (Item item : order.items) { // Direct access
            if (item.price < 0) return false;
        }
        return true;
    }
}

// GOOD: Proper encapsulation
public class Order {
    private List<Item> items;
    
    public boolean isValid() {
        return items.stream().allMatch(Item::isValid);
    }
}
```

**Why it's bad:**
- Breaks encapsulation
- Changes ripple through system
- Hard to refactor

---

### **Refused Bequest**
Subclass doesn't use inherited methods.

```java
// BAD: Inheritance misuse
public class Stack extends ArrayList {
    public void push(Object item) {
        add(item);
    }
    
    public Object pop() {
        return remove(size() - 1);
    }
    
    // But Stack shouldn't have add(), get(), set() from ArrayList!
}

// GOOD: Composition
public class Stack {
    private List<Object> items = new ArrayList<>();
    
    public void push(Object item) {
        items.add(item);
    }
    
    public Object pop() {
        return items.remove(items.size() - 1);
    }
}
```

**Why it's bad:**
- Violates LSP
- Exposes unwanted methods
- Misleading interface

---

### **Speculative Generality**
Code for future needs that may never come.

```java
// BAD: Over-engineered for "future flexibility"
public abstract class AbstractBaseUserFactoryProviderImpl {
    protected abstract User createUserInstance();
    protected abstract void initializeUserDefaults();
    protected abstract void validateUserConstraints();
    // ... 20 more abstract methods for "flexibility"
}

// GOOD: Simple and sufficient
public class UserService {
    public User createUser(String name, String email) {
        return new User(name, email);
    }
}
```

**Why it's bad:**
- Unnecessary complexity
- Harder to understand
- Violates YAGNI

---

## Quick Reference Cheat Sheet

| Concept | Key Question | Example |
|---------|-------------|---------|
| **Encapsulation** | "Is data protected?" | Private fields, public methods |
| **Abstraction** | "Is complexity hidden?" | Interfaces, abstract classes |
| **Inheritance** | "Is it a true IS-A?" | Dog extends Animal |
| **Polymorphism** | "Same interface, different behavior?" | List<Animal> with Dog, Cat |
| **Composition** | "Does owner control lifecycle?" | Car has Engine |
| **Aggregation** | "Can parts exist independently?" | Department has Professors |
| **SRP** | "One reason to change?" | UserRepository only handles DB |
| **OCP** | "Extend without modifying?" | Add new Shape without changing calculator |
| **LSP** | "Can I swap subtype?" | Square shouldn't break Rectangle code |
| **ISP** | "Small, focused interfaces?" | Workable, Eatable separate |
| **DIP** | "Depend on abstraction?" | Inject NotificationService interface |

---

## Final Thoughts

**Good OO design is about:**
1. **Flexibility** - Easy to change and extend
2. **Maintainability** - Easy to understand and fix
3. **Reusability** - Components work in multiple contexts
4. **Testability** - Easy to test in isolation

**Remember:** These are guidelines, not laws. Sometimes breaking a principle makes sense for simplicity. The goal is writing code that humans can understand and maintain, not perfect theoretical purity.
