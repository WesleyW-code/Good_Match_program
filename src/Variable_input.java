import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.util.*;
import java.lang.*;
import java.io.*;


public class Variable_input {
    // Importing necessary modules

    // This is my main page where the code will run.


    public static void goodMatcher(String inputLoc) {

            // Creating an arraylist for all the male names.
            List<String> Males = new ArrayList<String>();

            // Creating an arraylist for all the female names.
            List<String> Females = new ArrayList<String>();

            // Creating an arraylist for input errors.
            List<String> Errors = new ArrayList<String>();

            try {
                // Reading through the csv file
                Scanner read_file = new Scanner(new File(inputLoc));

                // While there is still data it will do the following:
                System.out.println("Reading in the input data...");
                while (read_file.hasNext()) {

                    // Reading the data line by line
                    String line = read_file.nextLine();

                    // Splitting the data by comma so we have the names and genders separate
                    String line_info[] = line.split(", ");

                    // If there is non- alphabetical characters this error will show.
                    if (Functions.allLetters(line_info[0])== false) {
                        Errors.add(line + " - Error: this has non-alphabetical characters in it!");
                    }
                    else {
                        // If the gender is female , it will add to the female arraylist
                        if (Objects.equals(line_info[1].toLowerCase(), "f")) {
                            String name = line_info[0];

                            // This will not add any duplicate names by checking if the name already exists in the arraylist.
                            if (!Females.contains(name)) {
                                Females.add(name);
                            }
                        }

                        // If the gender is male , it will add to the male arraylist
                        else if (Objects.equals(line_info[1].toLowerCase(), "m")) {
                            String name = line_info[0];

                            // This will not add any duplicate names by checking if the name already exists in the arraylist.
                            if (!Males.contains(name)) {
                                Males.add(name);
                            }
                        }

                        // This will go into the log if the gender is not female or male.
                        else {
                            Errors.add(line + " - Error: Invalid gender character!");
                        }
                    }
                }

                // This will print out an error message if any input errors have been found and direct you to the log.txt file.
                if (Errors.size()>0) {
                    System.out.println("Errors detected! Please check Log.txt");
                }
                else System.out.println("No errors detected in the input dataset!");

                // This will write to my error log file.
                PrintWriter log_file = new PrintWriter(new FileWriter("src//Log.txt",false));
                for (int i = 0; i < Errors.size(); i++) {
                    // Writing the line to the text file.
                    log_file.println(Errors.get(i));
                }
                log_file.close();

                // If all the names have errors in it, the code will not run.
                if (Males.size()== 0 | Females.size() == 0) {
                    System.out.println("Error - Too many invalid names! Could not complete.");
                }

                else {
                    // Sorting the arraylists so that the names are sorted in the 2D array, then i will sort it according to the match values.
                    Collections.sort(Males);
                    Collections.sort(Females);

                    System.out.println("Calculating the match percentages...");
                    // Creating a 2D array to store the results.
                    String Results[][] = new String[Males.size() * Females.size()][3];

                    // To store an integer version of the good match percentage.
                    int percentages[] = new int[Males.size() * Females.size()];

                    // Creating a counter variable for the row number.
                    int n = 0;

                    // The first for loop will iterate through the male names.
                    for (int i = 0; i < Males.size(); i++) {

                        // The nested for loop will go through the female names and get the good match percentage.
                        for (int j = 0; j < Females.size(); j++) {

                            // Adding all the male names to the 2D array.
                            Results[n][0] = Males.get(i);

                            // Adding all female names to the 2D array.
                            Results[n][1] = Females.get(j);

                            // Adding all the results to the 2D array (as a string).
                            Results[n][2] = String.valueOf(Functions.goodMatch(Males.get(i), Females.get(j)));

                            // Adding all the integer results to the percentages array (as an int so we can sort).
                            percentages[n] = Functions.goodMatch(Males.get(i), Females.get(j));
                            n++;
                        }
                    }

                    // This is going to be the sorted 2D array for processing to the text file.
                    String ResultsSorted[][] = new String[Males.size() * Females.size()][3];

                    // This for loop will sort my new 2D array correctly.
                    for (int i = 0; i < Males.size() * Females.size(); i++) {

                        // This uses my WhereMax function to get the first location of the maximum value.
                        int currentMax = Functions.WhereMax(percentages);

                        // This takes the row at the location of current max and puts it into our sorted results.
                        ResultsSorted[i] = Results[currentMax];

                        // This prevents double counting of rows. Allows us to find the next highest value!
                        percentages[currentMax] = 0;
                    }

                    // Adding info to the output text file!
                    // This will overwrite the text file each time with the new results.
                    PrintWriter output_file = new PrintWriter(new FileWriter("src//Output.txt", false));

                    // This for loop will go through my Sorted 2D array list and add everything correctly to the text file.
                    for (int i = 0; i < percentages.length; i++) {

                        // Constructing the string to add to the text file.
                        String currentLine = ResultsSorted[i][0] + " matches " + ResultsSorted[i][1] + " " + ResultsSorted[i][2] + "%";

                        // This will add good match if the percentage is equal or greater then 80.
                        if (Integer.parseInt(ResultsSorted[i][2]) >= 80) {
                            currentLine += ", good match!";
                        }

                        // Writing the line to the text file.
                        output_file.println(currentLine);
                    }
                    // Closing the text file.
                    output_file.close();
                    System.out.println("All correctly formatted names successfully matched. Please check Output.txt!");
                }

                // This will catch if the CSV file does not exist.
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                // In-case the output text file doesn't exist.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public static void main(String[] args) {
        System.out.println("Welcome to name matcher");
        Scanner user = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please enter the file location (eg src//Names.csv):");
        String userPath = user.nextLine();
        userPath = userPath.replace("\\", "//");
        goodMatcher(userPath);

    }
}


