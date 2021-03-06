/*
 * Date: April 1, 2021
 * Name: Tyson Zheng & Morgan Sun
 * Teacher: Mr.Ho
 * Description: Luhn Algorithm Assignment, uses 5 methods that builds on the provided main. 
*/

// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray
import java.util.*;
import java.io.*;
// More packages may be imported in the space below

class customerSystem {
    public static void main(String[] args) {
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String enterCustomerOption, generateCustomerOption, exitCondition;
        String userInput = "";
        String infoData = "";
        String fileGen = "";
        String fileDecision = "";
        String filePath = "";
        int customerId = 1;
        int decision = 0;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below

        do {
            printMenu(); // Printing out the main menu
            userInput = reader.nextLine(); // User selection from the menu

            if (userInput.equals(enterCustomerOption)) {
                // Calling method enterCustomerInfo passing through the customer ID
                infoData = enterCustomerInfo(customerId);
                // Adding 1 to customer ID everytime there has been a successful entry of
                // customer info
                customerId++;
            } else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you
                // design the method return
                if (customerId == 2) {
                    fileDecision = returnFileName(fileGen, customerId);
                    System.out.println(
                            "Press 1 to create customer data in relative path\n2 to create file to specific path: ");
                    decision = reader.nextInt();
                    if (decision == 2) {
                        System.out.println("Enter the filepath you wish by typing the dir separated by '/'");
                        filePath = reader.next();
                    }
                }
                generateCustomerDataFile(infoData, fileDecision, decision, filePath);
            } else {
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition)); // Exits once the user types
        reader.close();
        System.out.println("Program Terminated");
    }

    public static void printMenu() {
        System.out.println("Customer and Sales System\n".concat("1. Enter Customer Information\n")
                .concat("2. Generate Customer data file\n").concat("3. Report on total Sales (Not done in this part)\n")
                .concat("4. Check for fraud in sales data (Not done in this part)\n").concat("9. Quit\n")
                .concat("Enter menu option (1-9)\n"));
    }

    /*
     * Method Name: enterCustomerInfo Description: The user is able to enter the
     * customer's information which then is concatenated into a single string to be
     * passed into a variable in main called info. The method will call on other
     * methods to validate postal code and credit card once the uer has inputted
     * values for those categories.
     *
     * @author: Tyson 
     * @parameter: customerIDNum (The ID assigned for every customer)
     * @return: concat (ID, First Name, Last Name, City Name, Credit Card, Postal
     * Code)
     */
    public static String enterCustomerInfo(int customerIdNum) {
        // Initializes Scanner
        Scanner reader = new Scanner(System.in);
        // Initializes variables for method
        String creditCard = "";
        String postalCode = "";
        // Sets loops to run
        boolean validCard = false;
        boolean validPostal = false;
        // Ask user for name and city which do not require checks
        System.out.print("Customer First Name: ");
        String firstName = reader.nextLine();
        System.out.print("Customer Last Name: ");
        String lastName = reader.nextLine();
        // Concatenates first name and last name to a full name for ease of programming
        String fullName = firstName + " " + lastName;
        System.out.print("Customer City: ");
        String cityName = reader.nextLine();
        // Validates credit card in a while loop
        // Calls the method validateCreditCard to check is the credit card is correct
        while (!validCard) {
            // Ask user for input
            System.out.print("Customer Credit Card: ");
            creditCard = reader.nextLine();
            // Validates the credit card by calling method
            validCard = validateCreditCard(creditCard);
            if (validCard == false) {
                // Continues loop
                System.out.println("Invalid Credit Card Information. Please enter again.");
            } else {
                // Breaks the loop by changing to true
                System.out.println("Valid Credit Card Information.");
                validCard = true;
            }
        }
        // Validates postal code in a while loop
        // Calls the method validatePostalCode to check if the postal code is in the CSV
        // file
        while (!validPostal) {
            // Ask user for input
            System.out.print("Customer Postal Code: ");
            postalCode = reader.nextLine();
            // Validates the credit card by calling method
            if (postalCode.length() < 3) {
                // Continues loop when the user inputted a postal code less than 3
                System.out.println("Invalid postal code, try again. ");
            } else if (validatePostalCode(postalCode) == true) {
                // Breaks the loop by changing to true
                System.out.println("Valid postal code ");
                validPostal = true;
            } else {
                // Continues loop
                System.out.println("Invalid postal code, try again. ");
            }
        }
        // Returns concatenated string of all the information
        String informationForGenerate = customerIdNum + ". " + fullName + "," + cityName + "," + creditCard + ","
                + postalCode;
        return informationForGenerate;

    }

    /*
     * Method Name: validatePostalCode Description: The method is called on by
     * enterCustomerInfo which will pass through the user's input for postal code.
     * This method will check from the postal codes provided, whether or not the
     * postal codes exist in the Postal Code CSV file It will be placed in a while
     * loop, reprompting for the postal code.
     * 
     * @author: Morgan
     * @parameter: postalCode (User input from enterCustomerInfo)
     * @return: boolean expression (True or False: if the postal code is valid or
     * not)
     */
    public static boolean validatePostalCode(String postalcode) {
        // Initialize BufferedReader
        BufferedReader reader;
        try {
            // Scans/Reads the postal_codes.csv file
            reader = new BufferedReader(new FileReader("postal_codes.csv"));
            // Gets the first 3 characters from postal_codes.csv
            String line = reader.readLine();
            String format = "";
            String pformat = postalcode.substring(0, 3);
            // Checks if the user input is a null value
            while (line != null) {
                // While loop through each line
                line = reader.readLine();
                if (line == null) {
                    break;
                    // Breaks if the value is null
                }
                // Checks if the user input is equal to the line in CSV
                format = line.substring(0, 3);
                if (pformat.equalsIgnoreCase(format)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
            // Catches exceptions and prints out the exception
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Method Name: validateCreditCard Description: The method is called on by
     * enterCustomerInfo which will pass through the user's input for creditCard
     * This method will check if the credit card number is a number greater than 9
     * and is a number following the Luhn Algorithm It will be placed in a while
     * loop, reprompting for the credit card number if the method return false.
     * 
     * @author: Tyson 
     * @parameter: creditCardNumber (User input from enterCustomerInfo)
     * @return: boolean expression (True or False: if the credit card number is
     * valid or not)
     */
    public static boolean validateCreditCard(String creditCardNumber) {
        // Ignores the whitespaces in the user input
        String creditCard = creditCardNumber.replaceAll("\\s", "");
        // Find the length of the credit card number
        int length = creditCard.length();
        // Checks if the length is over or equal to 9 which is one condition.
        if (length < 9) {
            // Returns false if the credit card number is under 9
            return false;
        }
        // Initializes reversedCard variable to store the reversed card numbers
        String reversedCard = "";
        for (int i = 0; i < length; i++) {
            // Gets the postion at the end
            int position = length - 1 - i;
            // Calls the last character
            char characterAtPosition = creditCard.charAt(position);
            // Concatenates the characters to a string reversedCard
            reversedCard += characterAtPosition;
        }
        // Initializes sum1
        int sum1 = 0;
        // Gets the odd numbers to do first sum calculation
        for (int i = 0; i < length; i += 2) {
            // Gets the character at odd number
            char oddNumber = reversedCard.charAt(i);
            // Changes the character into a integer to add to the sum1
            int oddNumberInteger = Character.getNumericValue(oddNumber);
            sum1 += oddNumberInteger;
        }
        // Initializes sum2
        int sum2 = 0;
        // Gets the even numbers to do the second sum calculation
        for (int j = 1; j < length; j += 2) {
            // Gets the character at the even number
            char evenNumber = reversedCard.charAt(j);
            // Converts to a integer
            int evenNumberInteger = Character.getNumericValue(evenNumber);
            // Multiplies the event number by 2
            int doubledEven = evenNumberInteger * 2;
            // Initializes variables
            int remainderValue = 0;
            int tenthDigit = 0;
            // Checks if doubled of the even is greater than 9
            if (doubledEven > 9) {
                // Calculates the remainder which is the first digit spot
                remainderValue = doubledEven % 10;
                // Calculates the tenth digit spot
                tenthDigit = doubledEven / 10;
                // Gets the partialSum by adding the tenth place digit and first place digit
                int partialSum = tenthDigit + remainderValue;
                // Adds to sum2
                sum2 += partialSum;
            } else {
                // Adds doubled of the even number when it is below 9
                sum2 += doubledEven;
            }
        }
        // Checks if the total sum is a multiple of 10
        if (((sum2 + sum1) % 10) == 0) {
            // Returns true
            return true;
        } else {
            // Returns false
            return false;
        }
    }

    /*
     * Method Name: generateCustomerDataFile Description: The method is called from
     * the main method when the user wants to the customer information to a CSV
     * file. This method uses FileWriter and inputs the variable concatn which was
     * the information from enterCustomerInfo It will open a seperate file with a
     * string in the format (ID, First Name, Last Name, City Name, Credit Card,
     * Postal Code)
     * 
     * @parameter: concatn (A concatenated string of the information from main)
     * @author: Morgan
     * @return: void
     */
    public static void generateCustomerDataFile(String concatn, String fileName, int decision, String filePath) {
        try {
            // Creates file for users
            if (decision == 1) {
                File customerData = new File(fileName + ".csv");
                FileWriter myWriter = new FileWriter(customerData, true);
                // Writes the information into the new file
                myWriter.write(concatn + "\n");
                // Closes files
                myWriter.close();
            } else {
                File customerData = new File(filePath, fileName + ".csv");
                FileWriter myWriter = new FileWriter(customerData, true);
                // Writes the information into the new file
                myWriter.write(concatn + "\n");
                // Closes files
                myWriter.close();
            }
            // Catches errors and prints an error message
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*
     * Method Name: returnFileName
     * Description: The method is called on by
     * main when user chooses 2 
     * This method prevents fileName being prompted everytime user selects 2
     * 
     * @parameter: fileName and id from main
     * @return: String fileName that shows the name of the file user wants to make
     * 
     */
    public static String returnFileName(String fileName, int id) {
        Scanner reader = new Scanner(System.in);
        // Gets the file name from user
        if (id == 2) {
            System.out.print("File Name: ");
            fileName = reader.nextLine();
        }
        return fileName;
    }
}
