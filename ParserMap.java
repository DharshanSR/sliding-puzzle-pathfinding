import java.io.File; // Used to manipulate files and directories.
import java.io.FileNotFoundException; // Used to handle the FileNotFoundException that may occur when attempting to read a file.
import java.nio.charset.StandardCharsets; // Used to specify the character encoding when reading the file.
import java.nio.file.Files; // Used to perform file I/O operations such as reading all lines from a file.
import java.util.ArrayList; // Used to create an ArrayList to store the lines read from the file.
import java.util.List; // Used to create a List interface to store the lines read from the file.


public class ParserMap {
    // List to store lines read from the file
    private List<String> lines = new ArrayList<>();
    // Coordinates for the start and end points in the maze
    private int[] startCoordinate;
    private int[] endCoordinate;
    // 2D array representing the maze
    private int[][] maze;

    // Method to read and parse the input file
    public void readFile(String filePath) throws Exception {
        // Create a File object from the file path
        File file = new File(filePath);
        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            throw new FileNotFoundException("File does not exist or is empty");
        }
        // Read all lines from the file and store them in the list
        lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        // Parse the lines to load values into the maze
        loadValues();
    }

    // Method to parse lines and load values into the maze
    void loadValues() {
        // Get the number of rows and columns in the maze
        int rows = lines.size();
        int cols = lines.get(0).length();

        // Initialize the maze array
        maze = new int[rows][cols];

        // Loop through each line of the input
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i).trim();
            // Loop through each character of the line
            for (int j = 0; j < cols; j++) {
                char ch = line.charAt(j);

                // Check if the character represents the start point 'S'
                if (ch == 'S') {
                    startCoordinate = new int[]{i, j};
                    maze[i][j] = 0; // Set start point value in maze
                }

                // Check if the character represents the end point 'F'
                else if (ch == 'F') {
                    endCoordinate = new int[]{i, j};
                    maze[i][j] = 0; // Set end point value in maze
                }

                // If it's not a start or end point, set the maze value based on the character
                else {
                    maze[i][j] = (ch == '0' ? 1 : 0); // '0' represents a blocked cell
                }
            }
        }
    }

    // Getter method for the maze array
    public int[][] getMaze() {
        return maze;
    }

    // Getter method for the start coordinate
    public int[] getStartCoordinate() {
        return startCoordinate;
    }

    // Getter method for the end coordinate
    public int[] getEndCoordinate() {
        return endCoordinate;
    }
}
