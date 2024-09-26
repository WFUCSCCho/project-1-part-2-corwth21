/**
 * @file: Parser.java
 * @description: This program implements utilizes the BST to read the given input file
 * and perform the required actions
 * @author: Tucker Corwen
 * @date: September 18, 2024
 */

import java.io.*;
import java.util.Scanner;

public class Parser {

    // Create a Binary Search Tree (BST) of Integer type
    private BST mybst = new BST<>();

    // Constructor that takes a filename as an argument
    // Clears the result.txt file and processes the input file
    public Parser(String filename) throws FileNotFoundException {
        clearFile();  // Clear the output file before writing new data
        PrintStream fileOut = new PrintStream(new FileOutputStream("result.txt", true)); // Set output to file
        System.setOut(fileOut); // Redirects System.out to the file "result.txt"
        process(new File(filename)); // Process the input file
    }

    // Method to clear the contents of the result.txt file
    private void clearFile() {
        try (FileWriter fw = new FileWriter("./result.txt", false)) {
            fw.write(""); // Overwrite the file with an empty string, effectively clearing it
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if an exception occurs
        }
    }

    // Process the input file by reading each line and performing corresponding operations
    // Removes redundant spaces and splits the input into commands
    public void process(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input); // Create a scanner to read the file
        boolean isFirstLine = true; // Flag to skip the first line

        while (scanner.hasNextLine()) { // Loop through each line in the file
            String line = scanner.nextLine().trim(); // Trim leading and trailing whitespace

            // Skip the first line
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            // Skip empty lines
            if (line.isEmpty()) continue;

            // Parse the line into commands separated by whitespace
            String[] inputs = line.split("\\s+", 2);

            // Call operate_BST method to handle the commands
            operate_BST(inputs);
        }

    }
    // Method to check if the string is numeric
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Operates on the BST based on the incoming command
    public void operate_BST(String[] inputs) {
        // Set the output file path
        String newFile = "./result.txt";
        String csvFile = "/Users/tuckercorwen/IdeaProjects/project-1-part-2-corwth21/src/nfl_combine.csv";
        String[] info = null;


        try {
            //Create a Scanner class that reads the data csv
          Scanner csvScanner = new Scanner(new File(csvFile));

        switch (inputs[0]) {
            //if the input command is insert, take the information in and insert a new candidate object
            case "insert" -> {

                while (csvScanner.hasNextLine()) {
                    String line = csvScanner.nextLine().trim();
                    if (line.isEmpty()) continue;

                    info = line.split(",", -1);

                    if (!info[1].equals(inputs[1])) {
                        continue;
                    }
                    break;

                }

                //Check csv data points, if missing create N/A or 0 entries
                float year = Float.parseFloat(info[0].isEmpty() ? "0" : info[0]);
                String name = info[1].isEmpty() ? "N/A" : info[1];
                String college = info[2].isEmpty() ? "N/A" : info[2];
                String position = info[3].isEmpty() ? "N/A" : info[3];

                float heightInches = 0;
                if (isNumeric(info[4])) {
                    Float.parseFloat(info[4].isEmpty() ? "0" : info[4]);
                }

                float weightLbs = Float.parseFloat(info[5].isEmpty() ? "0" : info[5]);
                float handSize = Float.parseFloat(info[6].isEmpty() ? "0" : info[6]);
                float armLength = Float.parseFloat(info[7].isEmpty() ? "0" : info[7]);
                float wonderlic = Float.parseFloat(info[8].isEmpty() ? "0" : info[8]);
                float fortyYard = Float.parseFloat(info[9].isEmpty() ? "0" : info[9]);
                float benchPress = Float.parseFloat(info[10].isEmpty() ? "0" : info[10]);
                float verticalLeap = Float.parseFloat(info[11].isEmpty() ? "0" : info[11]);
                float broadJump = Float.parseFloat(info[12].isEmpty() ? "0" : info[12]);
                float shuttle = Float.parseFloat(info[13].isEmpty() ? "0" : info[13]);
                float threeCone = Float.parseFloat(info[14].isEmpty() ? "0" : info[14]);
                float sixtyYardShuttle = Float.parseFloat(info[15].isEmpty() ? "0" : info[15]);

                //Create the object and set the values
                Candidate newCandidate = new Candidate(info);
                newCandidate.setYear(year);
                newCandidate.setName(name);
                newCandidate.setCollege(college);
                newCandidate.setPos(position);
                newCandidate.setHeight_in(heightInches);
                newCandidate.setWeight_lbs(weightLbs);
                newCandidate.setHand_size_in(handSize);
                newCandidate.setArm_length_in(armLength);
                newCandidate.setWonderlic(wonderlic);
                newCandidate.setForty_yard(fortyYard);
                newCandidate.setBench_press(benchPress);
                newCandidate.setVert_leap_in(verticalLeap);
                newCandidate.setBroad_jump_in(broadJump);
                newCandidate.setShuttle(shuttle);
                newCandidate.setThree_cone(threeCone);
                newCandidate.setSixty_yd_shuttle(sixtyYardShuttle);

                //Insert object and print to result file
                mybst.insert(newCandidate);
                writeToFile("insert " + inputs[1], newFile);

            }
            //In the case of print command print existing BST
            case "print" -> {
                mybst.print();
            }
            //In the case of remove command remove an object from the BST
            case "remove" ->{

                while (csvScanner.hasNextLine()) {
                    String line = csvScanner.nextLine().trim();
                    if (line.isEmpty()) continue;

                    info = line.split(",", -1);

                    if (!info[1].equals(inputs[1])) {
                        continue;
                    }
                    break;

                }

                //Check csv data points, if missing create N/A or 0 entries
                float year = Float.parseFloat(info[0].isEmpty() ? "0" : info[0]);
                String name = info[1].isEmpty() ? "N/A" : info[1];
                String college = info[2].isEmpty() ? "N/A" : info[2];
                String position = info[3].isEmpty() ? "N/A" : info[3];

                float heightInches = 0;
                if (isNumeric(info[4])) {
                    Float.parseFloat(info[4].isEmpty() ? "0" : info[4]);
                }


                float weightLbs = Float.parseFloat(info[5].isEmpty() ? "0" : info[5]);
                float handSize = Float.parseFloat(info[6].isEmpty() ? "0" : info[6]);
                float armLength = Float.parseFloat(info[7].isEmpty() ? "0" : info[7]);
                float wonderlic = Float.parseFloat(info[8].isEmpty() ? "0" : info[8]);
                float fortyYard = Float.parseFloat(info[9].isEmpty() ? "0" : info[9]);
                float benchPress = Float.parseFloat(info[10].isEmpty() ? "0" : info[10]);
                float verticalLeap = Float.parseFloat(info[11].isEmpty() ? "0" : info[11]);
                float broadJump = Float.parseFloat(info[12].isEmpty() ? "0" : info[12]);
                float shuttle = Float.parseFloat(info[13].isEmpty() ? "0" : info[13]);
                float threeCone = Float.parseFloat(info[14].isEmpty() ? "0" : info[14]);
                float sixtyYardShuttle = Float.parseFloat(info[15].isEmpty() ? "0" : info[15]);

                //Create the object and set the values
                Candidate newCandidate = new Candidate(info);
                newCandidate.setYear(year);
                newCandidate.setName(name);
                newCandidate.setCollege(college);
                newCandidate.setPos(position);
                newCandidate.setHeight_in(heightInches);
                newCandidate.setWeight_lbs(weightLbs);
                newCandidate.setHand_size_in(handSize);
                newCandidate.setArm_length_in(armLength);
                newCandidate.setWonderlic(wonderlic);
                newCandidate.setForty_yard(fortyYard);
                newCandidate.setBench_press(benchPress);
                newCandidate.setVert_leap_in(verticalLeap);
                newCandidate.setBroad_jump_in(broadJump);
                newCandidate.setShuttle(shuttle);
                newCandidate.setThree_cone(threeCone);
                newCandidate.setSixty_yd_shuttle(sixtyYardShuttle);

                //Remove the object if found or say failed if not found, print result
                if (mybst.remove(newCandidate) != null) {
                    writeToFile("remove " + inputs[1], newFile);
                } else {
                    writeToFile("remove " + inputs[1] + " failed", newFile);
                }
            }
            //Search for given object
            case "search" ->{

                while (csvScanner.hasNextLine()) {
                    String line = csvScanner.nextLine().trim();
                    if (line.isEmpty()) continue;

                    info = line.split(",", -1);

                    if (!info[1].equals(inputs[1])) {
                        continue;
                    }
                    break;

                }

                //Check csv data points, if missing create N/A or 0 entries
                float year = Float.parseFloat(info[0].isEmpty() ? "0" : info[0]);
                String name = info[1].isEmpty() ? "N/A" : info[1];
                String college = info[2].isEmpty() ? "N/A" : info[2];
                String position = info[3].isEmpty() ? "N/A" : info[3];

                float heightInches = 0;
                if (isNumeric(info[4])) {
                    Float.parseFloat(info[4].isEmpty() ? "0" : info[4]);
                }

                float weightLbs = Float.parseFloat(info[5].isEmpty() ? "0" : info[5]);
                float handSize = Float.parseFloat(info[6].isEmpty() ? "0" : info[6]);
                float armLength = Float.parseFloat(info[7].isEmpty() ? "0" : info[7]);
                float wonderlic = Float.parseFloat(info[8].isEmpty() ? "0" : info[8]);
                float fortyYard = Float.parseFloat(info[9].isEmpty() ? "0" : info[9]);
                float benchPress = Float.parseFloat(info[10].isEmpty() ? "0" : info[10]);
                float verticalLeap = Float.parseFloat(info[11].isEmpty() ? "0" : info[11]);
                float broadJump = Float.parseFloat(info[12].isEmpty() ? "0" : info[12]);
                float shuttle = Float.parseFloat(info[13].isEmpty() ? "0" : info[13]);
                float threeCone = Float.parseFloat(info[14].isEmpty() ? "0" : info[14]);
                float sixtyYardShuttle = Float.parseFloat(info[15].isEmpty() ? "0" : info[15]);

                //Create the object and set the values
                Candidate newCandidate = new Candidate(info);
                newCandidate.setYear(year);
                newCandidate.setName(name);
                newCandidate.setCollege(college);
                newCandidate.setPos(position);
                newCandidate.setHeight_in(heightInches);
                newCandidate.setWeight_lbs(weightLbs);
                newCandidate.setHand_size_in(handSize);
                newCandidate.setArm_length_in(armLength);
                newCandidate.setWonderlic(wonderlic);
                newCandidate.setForty_yard(fortyYard);
                newCandidate.setBench_press(benchPress);
                newCandidate.setVert_leap_in(verticalLeap);
                newCandidate.setBroad_jump_in(broadJump);
                newCandidate.setShuttle(shuttle);
                newCandidate.setThree_cone(threeCone);
                newCandidate.setSixty_yd_shuttle(sixtyYardShuttle);

                if (mybst.search(newCandidate) != null) {
                    writeToFile("found " + inputs[1], newFile);
                } else {
                    writeToFile("search failed", newFile);
                }

            }
            default -> writeToFile("Invalid Command", newFile);
        }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + csvFile);
        }



    }

    // Method to write content to a specified file
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content); // Write the content to the file
            writer.newLine(); // Add a newline after writing the content
        } catch (IOException e) {
            // Handle any IO exceptions during file writing
            System.out.println("An error occurred while writing to the file.");
        }
    }
}
