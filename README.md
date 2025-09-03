# Approximation Algorithms for NP-Hard Problem 📊🔢

This project explores **approximation strategies for an NP-hard problem** by implementing both **upper bound** and **lower bound** algorithms. The goal is to provide practical solutions when exact computation is infeasible due to exponential complexity.

Several **optimization and heuristic techniques** were implemented and compared, ranging from deterministic approaches to probabilistic metaheuristics.

---

## 🚀 Features
- **Lower and upper bound approximations** for the target NP-hard problem.
- Multiple algorithmic strategies implemented:
  - **Greedy Algorithm** – Fast baseline heuristic.
  - **Genetic Algorithm** – Evolutionary optimization.
  - **Simulated Annealing** – Probabilistic metaheuristic.
  - **Newton–Raphson** – Applied for numerical refinement.
  - **Layout Algorithm** – Custom heuristic for graph-based structures.
- **Graph-based modeling** with custom `Graph` and `Vertex` classes.
- **Benchmarking framework** for comparing solution quality and runtime.

---

## 📂 Project Structure
- `Graph.java` – Graph data structure representation.  
- `Vertex.java` – Node representation.  
- `LowerBoundAlgorithm.java` – Computes theoretical lower bound.  
- `UpperBoundAlgorithm.java` – Computes theoretical upper bound.  
- `GreedyAlgorithm.java` – Greedy heuristic solution.  
- `GeneticAlgorithm.java` – Evolutionary algorithm.  
- `SimulateAnnealing.java` – Simulated annealing optimization.  
- `NewtonRaphson.java` – Numerical optimization method.  
- `LayoutAlgorithm.java` – Custom approximation approach.  
- `Launch.java` – Entry point for running experiments.  

---

## 🛠️ Technologies Used
- **Java**  
- **Approximation Algorithms**  
- **Metaheuristics** (Genetic Algorithm, Simulated Annealing)  
- **Numerical Methods** (Newton–Raphson)  
- **Graph Theory**  

---

## 📌 Related Topics
- NP-hard problems  
- Approximation algorithms  
- Combinatorial optimization  
- Graph theory  
- Heuristics vs. metaheuristics  

---

## ▶️ Running the Project
1. Compile all `.java` files:
   ```bash
   javac *.java
