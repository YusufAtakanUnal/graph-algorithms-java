```
#  Graph Algorithms - Landmark Navigation

This Java project implements two different graph problems involving city landmarks using custom-built graph structures and Breadth-First Search (BFS).

---

##  Project Structure

graph-algorithms-java/
├── Q1/               → Adds edges without decreasing X–Y distance
│   └── Graph.java
├── Q2/               → Finds minimum moves to visit all landmarks
│   └── Graph.java
├── README.md

Each part is self-contained and uses its own BFS-based Graph implementation.

---

##  How to Run

To run Question 1:
> javac Q1/Graph.java  
> java Q1.Graph

To run Question 2:
> javac Q2/Graph.java  
> java Q2.Graph

Make sure you have Java 8+ installed.

---

##  Question 1: Safe Road Additions

**Goal:**  
Identify which new edges can be added between unconnected landmarks **without decreasing** the shortest distance between landmarks X and Y.

**Input:**  
- Number of landmarks (nodes) and existing roads (edges)  
- Undirected edges  
- Start node (X) and target node (Y)

**Output:**  
- Count and list of valid new edges that do not reduce the X–Y distance

**Approach:**  
- BFS from X to Y  
- Try each unconnected pair  
- Temporarily add the edge  
- If distance is unaffected → it's a valid new edge  
- Rollback after check

---

##  Question 2: Minimum Visit Time

**Goal:**  
Visit all landmarks in the fewest number of moves, starting from **any** landmark.

**Input:**  
- List of landmarks and paths  
- Undirected graph

**Output:**  
- Minimum number of moves  
- Visit order  
- If impossible → print `-1`

**Approach:**  
- Try BFS from each node  
- Track reachable landmarks  
- Count steps to visit all nodes  
- Return the shortest one

---

##  Technologies

- Java (no external libraries)
- Custom Graph + Adjacency List
- Breadth-First Search (BFS)
- Path reconstruction from BFS tree

---

##  Highlights

- Same class name (`Graph.java`) used for both solutions, kept isolated
- Implemented from scratch with no built-in Graph APIs
- Practical BFS applications on undirected graphs
- Input/output formats match real problem constraints
```
