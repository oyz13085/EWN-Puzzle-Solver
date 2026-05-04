# EWN Puzzle Solver 🎯

A Java implementation of a deterministic puzzle solver for **EinStein würfelt nicht!** (EWN) — a board game reimagined without randomness.

Built for WIX1002 Fundamentals of Programming, Universiti Malaya.

---

## The Concept

In 1926, Einstein said: *"I am convinced that He does not play dice."*

This project removes all chance from the EWN board game by replacing dice rolls with fixed, pre-determined sequences — turning a probability game into a pure logic and pathfinding challenge.

**Goal:** Navigate a target piece across a 10×10 grid to Square 0 within a move and time limit.

---

## Game Modes

| Mode | Description |
|---|---|
| Human Player | Manual move selection via terminal |
| Random Player | Moves selected randomly |
| AI Player | Automated solver using **Beam Search** |

---

## AI Strategy — Beam Search

The AI player implements **Beam Search**, a heuristic pathfinding algorithm that keeps only the top *k* most promising paths at each step.

**Scoring function:**
```
Score = (D × 100) + E
```
- **D** — Chebyshev Distance: `max(Δx, Δy)` from target piece to Square 0 (accounts for diagonal moves)
- **E** — Enemy Count: penalty for remaining obstacle pieces blocking the path

**Safety trigger:** Any simulated state where the target piece is captured is immediately discarded.

### Why Beam Search?

| Feature | Greedy | Beam Search | BFS |
|---|---|---|---|
| Search Width | 1 (best only) | k (top k) | ∞ (all moves) |
| Memory Usage | Very Low | Low–Moderate | Extremely High |
| Speed | Fastest | Fast | Slowest |
| Optimality | Rarely optimal | Likely sub-optimal | Guaranteed optimal |

Given the strict move and time limits, Beam Search offers the best balance of speed and solution quality.

---

## Results

| Level | Move Limit | AI Result |
|---|---|---|
| Level 1 | 6 moves | ✅ Solved within 6 moves |
| Level 2 | 10 moves | ✅ Solved within 10 moves |
| Level 3 | 10 moves | ✅ Solved within 10 moves |
| Level 4 | 15 moves | ✅ Solved within 15 moves |

All levels solved within the 15-second time constraint.

---

## How to Run

```bash
# Navigate to project folder
cd EWN-Puzzle-main

# Compile all Java files
javac *.java

# Run the game
java GameMain

# Optional: visual GUI
java -jar EWN_GUI.jar
```

---

## Class Structure

| Class | Responsibility |
|---|---|
| `GameMain` | Entry point, user input, game loop |
| `GameLoader` | Reads level config from external files |
| `GameState` | Move validation, win condition checking |
| `Player` (abstract) | Shared move logging logic |
| `HumanPlayer` | Manual move selection |
| `RandomPlayer` | Random move selection |
| `AIPlayer` | Beam Search solver |

---

## Built With

- Java (standard library only)
- Beam Search pathfinding algorithm
- Chebyshev Distance heuristic
