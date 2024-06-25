# Sliding Puzzle Pathfinding

## Overview

This project implements a pathfinding solution for a sliding puzzle commonly found in video games. The puzzle involves navigating from a start ('S') to a finish ('F') position on a grid, where the player slides until obstructed by walls or rocks ('0') on a frictionless ice floor.

### Tasks

- **Task 1 (Setup)**: Set up the project environment.
- **Task 2 (Data Structure)**: Implement a data structure to represent the map grid.
- **Task 3 (Map Parser)**: Create a parser to read map data from input files.
- **Task 4 (Algorithm)**: Implement a pathfinding algorithm to find the shortest path from start to finish.

## File Structure

- **src/**: Contains the source code files.
- **input/**: Directory for input map files.
- **README.md**: Project documentation.
- **LICENSE**: License information.

## Implementation Details

### Data Structure

The map is represented using a 2D array in Java. Each cell can contain '.', '0', 'S', 'F', or '@' (for the player's current position).

### Map Parser

A parser reads the map from an input file (`input/map.txt`), determines its dimensions, and locates 'S', 'F', and '0' positions.

### Pathfinding Algorithm

The algorithm uses BFS (Breadth-First Search) to find the shortest path from 'S' to 'F', accounting for sliding movement on ice until blocked.

## Example

For an input map file `input/map.txt`:
```
.....0...S
....0.....
0.....0..0
...0....0.
.F......0.
.0........
.......0..
.0.0..0..0
0.........
.00.....0.
```

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/DharshanSR/sliding-puzzle-pathfinding.git
