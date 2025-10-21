# Project #2: Massive Motion

## Project Description
**Massive Motion** is a Java program that simulates the motion of celestial bodies (like stars and comets) across a screen using different custom list data structure implementations.  
The simulation reads data from a property file that defines object attributes and window properties, creates celestial objects accordingly, and animates them on a graphical interface.

---

## Files and Classes

### **1. MassiveMotion.txt**
A property file containing attributes for celestial body objects and canvas properties, including:
- Body size, velocity, and initial position
- `JFrame` window size and settings
- The list implementation type to use (e.g., `ArrayList`, `SingleLinkedList`, etc.)

---

### **2. MassiveMotion.java**
This file:
- Reads data from **MassiveMotion.txt**
- Creates `Celestial` objects (from `Celestial.java`)
- Adds them to a chosen **List** implementation (based on the property file)
- Implements animation and rendering logic using:
  - `ActionListener`
  - `paintComponent()`
  - `actionPerformed()`

#### **actionPerformed()**
- Iterates through the list of celestial bodies  
- Updates their positions using the `move()` method from `Celestial.java`
- Removes any bodies that move off-screen (for memory efficiency)

#### **paintComponent()**
- Loops through all celestial bodies in their updated positions  
- Repaints them on the canvas  
- The **first object** in the list (the star) is drawn in **red**, while the remaining (comets) are drawn in **blue**

---

### **3. Node.java**
Custom `Node` class used to build both singly and doubly linked list implementations.

---

### **4. Celestial.java**
Defines a **Celestial Body** class that holds attributes such as:
- Position, velocity, size
- Movement logic (`move()` method)

Celestial Body objects are stored in a chosen **List** implementation and represented as circles on the `JFrame` canvas.

---

## List Implementations and Complexities

### **ArrayList**
| Method | Best Case | Worst Case |
|---------|------------|-------------|
| `add(element)` | O(1) | O(n) *(when grow is triggered)* |
| `add(index, element)` | O(1) | O(n) *(inserting at beginning shifts all elements)* |
| `get(index)` | O(1) | O(1) |
| `remove(index)` | O(1) | O(n) *(removing from start)* |
| `grow()` | — | O(n) |
| `size()` | O(1) | O(1) |

---

### **SingleLinkedList**
| Method | Best Case | Worst Case |
|---------|------------|-------------|
| `add(index, element)` | O(1) | O(n) |
| `add(element)` | O(1) | O(n) |
| `get(index)` | O(1) *(i=0)* | O(n) |
| `remove(index)` | O(1) | O(n) |
| `size()` | O(1) | O(1) |

---

### **DoublyLinkedList**
| Method | Best Case | Worst Case |
|---------|------------|-------------|
| `add(element)` | O(1) | O(n) |
| `add(index, element)` | O(1) | O(n) |
| `get(index)` | O(1) *(i=0)* | O(n) |
| `remove(index)` | O(1) | O(n) |
| `size()` | O(1) | O(1) |

---

### **DummyHeadLinkedList**
| Method | Best Case | Worst Case |
|---------|------------|-------------|
| `add(index, element)` | O(1) | O(n) |
| `add(element)` | O(1) | O(n) |
| `get(index)` | O(1) *(i=0)* | O(n) |
| `remove(index)` | O(1) | O(n) |
| `size()` | O(1) | O(1) |

---

## Summary
The **Massive Motion** project demonstrates:
- Implementation of multiple **List** data structures in Java
- Practical comparison of their **runtime complexities**
- Application of **object-oriented programming**, **GUI rendering**, and **event-driven animation**
- A visual simulation of celestial motion using custom data structures

---
## Demo

[![Watch the animation](https://img.youtube.com/vi/fPZXLzoQLBA/0.jpg)](https://youtu.be/fPZXLzoQLBA)
---

## Author
**Maili McGhee**  
Project #2 — *Massive Motion*
