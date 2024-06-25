import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String FILE_DIRECTORY = "src/inputs/"; // Directory where input files are located
    private static ParserMap parsedInputFile;

    public static void main(String[] args) {
        System.out.println("\nSliding Puzzles Application");

        processMultipleInputs(); // Start processing multiple inputs
    }

    private static void processMultipleInputs() {
        Scanner scanner = new Scanner(System.in);
        String inputFileName;
        boolean continueProcessing = true;

        while (continueProcessing) {
            System.out.println("\n1. Enter file name");
            System.out.println("2. Exit");
            System.out.println("Enter your option:");

            String userInput = scanner.nextLine().trim();

            switch (userInput) {
                case "1":
                    System.out.println("Enter the file name:");
                    inputFileName = scanner.nextLine().trim().toLowerCase();
                    processInput(inputFileName);
                    break;
                case "2":
                    continueProcessing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
    }

    private static void processInput(String fileName) {
        try {
            File file = new File(FILE_DIRECTORY + fileName);
            if (!file.exists()) {
                System.out.println("Error: File does not exist.");
                return;
            }
            parsedInputFile = new ParserMap();
            parsedInputFile.readFile(file.getAbsolutePath());
            parsedInputFile.loadValues();
            System.out.println("File loaded successfully.");

            // Start measuring runtime
            long startTime = System.nanoTime();

            calculateDistance();

            // End measuring runtime
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // Convert nanoseconds to milliseconds

            System.out.println("Runtime: " + duration + " milliseconds");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void calculateDistance() {
        ShortestAlgoPath solver = new ShortestAlgoPath();

        System.out.println("Finding the shortest distance...");
        System.out.println(solver.shortestDistance(parsedInputFile.getMaze(), parsedInputFile.getStartCoordinate(), parsedInputFile.getEndCoordinate()));
        System.out.println("----------------------------------------");
    }
}
