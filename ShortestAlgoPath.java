import java.util.LinkedList; // Used to create a linked list implementation of the List interface. LinkedList provides efficient insertion and deletion operations.
import java.util.Queue; // Used to create a queue data structure. A Queue is typically used to store elements in a FIFO (First-In-First-Out) manner.


public class ShortestAlgoPath {
    // Inner class representing coordinates in the maze
    class Coordinate implements Comparable<Coordinate> {
        int row;
        int col;
        int distanceFromStart;
        String path; // Path to reach this coordinate
        Queue<int[]> queue = new LinkedList<>(); // Queue to track previous coordinates

        // Constructor to initialize Coordinate object
        Coordinate(int row, int col, int distanceFromStart, String path, int[] previousCoordinate) {
            this.row = row;
            this.col = col;
            this.distanceFromStart = distanceFromStart;
            this.path = path + " (" + (col + 1) + ", " + (row + 1) + ")\n"; // Path includes current coordinate
            this.queue.add(previousCoordinate); // Add the previous coordinate to the queue
        }

        // Compare coordinates based on distance from start
        @Override
        public int compareTo(Coordinate coordinate) {
            return this.distanceFromStart == coordinate.distanceFromStart ? this.path.compareTo(coordinate.path) : this.distanceFromStart - coordinate.distanceFromStart;
        }

        // Convert Coordinate object to String representation
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("START: ");
            String[] directions = path.split("\n"); // Split the path into individual directions
            for (String direction : directions) {
                if (!direction.isEmpty()) { // Skip empty lines
                    sb.append("MOVE ");
                    sb.append(direction.trim());
                    sb.append("\n");
                }
            }
            sb.append("FINISH");
            return sb.toString();
        }
    }

    // Method to find the shortest distance in the maze using a modified Breadth-First Search(BFS) algorithm
    public String shortestDistance(int[][] maze, int[] start, int[] end) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(start[0], start[1], 0, "", new int[]{})); // Add the start coordinate to the queue

        // Define directions and corresponding deltas
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll(); // Get the next coordinate from the queue

            // Check if we have reached the end coordinate
            if (current.row == end[0] && current.col == end[1]) {
                return current.toString(); // Return the path to reach the end coordinate
            }

            // Explore each direction
            for (int i = 0; i < deltas.length; i++) {
                int newRow = current.row;
                int newCol = current.col;
                int newDistance = current.distanceFromStart;
                String newPath = current.path;

                // Explore directions until a wall is hit or the end is reached
                while (newRow >= 0 && newRow < rows && newCol >= 0
                        && newCol < cols && maze[newRow][newCol] == 0
                        && (newRow != end[0] || newCol != end[1])) {
                    newRow += deltas[i][0];
                    newCol += deltas[i][1];
                    newDistance += 1;
                }
                // If the goal is not found, roll back one step
                if (newRow != end[0] || newCol != end[1]) {
                    newRow -= deltas[i][0];
                    newCol -= deltas[i][1];
                    newDistance -= 1;
                }

                // Check if the new coordinate has not been visited
                if (!visited[newRow][newCol]) {
                    visited[current.row][current.col] = true;
                    // Add the new coordinate to the queue
                    queue.offer(new Coordinate(newRow, newCol, newDistance, newPath + directions[i], new int[]{newRow, newCol}));
                }
            }
        }

        return "No path found!"; // If no path is found to reach the end coordinate
    }
}
