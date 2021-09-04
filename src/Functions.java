// This page has all my functions so that everything is neat.
public class Functions {

    // This function will take the two inputted names and give the match percentage.
    public static int goodMatch(String male, String fem) {

        // Creating the string to manipulate.
        String sentence = male + "matches" + fem;

        // Converting it to lowercase.
        sentence = sentence.toLowerCase();

        // Creating a blank string variable to add all the letter-counts together.
        String letterCount = "";

        // This while loop will iterate through the string and calculate each occurrence of a letter and then add it to the blank string variable.
        while(sentence.length()> 0){

            // Getting the first letter.
            char first_letter = sentence.charAt(0);

            // Creating a temp variable to subtract the length of the manipulated string.
            String temp = sentence;

            // Replacing all the letters that is being checked with nothing of the manipulated string.
            sentence = sentence.replaceAll(String.valueOf(first_letter),"");

            // The temp variable is equal to the original string.
            // This will count how many of those letters there were.
            int count = temp.length() - sentence.length();

            // Converting the integers to strings so that we can have a string of each letter total.
            Integer y = count;
            letterCount += y.toString();
        }
        // So the while loop executes correctly / to get into while loop the first time.
        String num_count = "123";

        // Setting the initial string to be checked.
        String iterations = letterCount;

        // Repeats the process until there is only two numbers left
        while(num_count.length()>2) {
            num_count = "";
            // Adds first and last number until there is one number left which it will add to the end.
            while (iterations.length() > 1) {

                // Getting the first number.
                char first_num = iterations.charAt(0);

                // Getting the last number.
                char last_num = iterations.charAt(iterations.length() - 1);

                // Adding the first and last number.
                int sum = Character.getNumericValue(first_num) + Character.getNumericValue(last_num);

                // Converting the result to a String.
                Integer y = sum;
                num_count += y.toString();

                // Creating a new string where the first and last number is removed so the process can repeat correctly.
                iterations = iterations.substring(1, iterations.length() - 1);

            }
            // Adds the last single number to the num_count.
            num_count+=iterations;

            // Resets the variable used in the above while loop, so that it repeats until two numbers are left.
            iterations = num_count;
        }
        return Integer.parseInt(num_count);

    }

    // This will give the location of the first maximum value in an array.
    public static int WhereMax(int Array []) {

        // This will store the index value of the max number the first time it occurs.
        int index = 0;

        // This stores the max value.
        int max = Array[0];
        for (int i = 1; i < Array.length ; i++) {

            // If the current value of the array is bigger then the max variable value , overwrite max with array[i].
            if (Array[i]>max) {
                max = Array[i];
                index = i;
            }
        }
        // Return the location of the first occurrence of the maximum number.
        return index;
    }

    // This will check if a given string has any not alphabetical characters.
    public static boolean allLetters(String word) {

        // If this boolean is false the word has non alphabetical characters in it.
        Boolean allLet = true;
        char chars [] = word.toCharArray();
        // looping through the word.
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                allLet = false;
            }
        }
        return allLet;
    }
}
