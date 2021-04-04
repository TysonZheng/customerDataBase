// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
import java.io.*;
// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
	String firstName = "";
        String cityName = "";
        String lastName = "";
        String creditCard ="";
        String postalCode ="";
        int customerID = 0;

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		// Any necessary variables may be added to this if section, but nowhere else in the code
		System.out.print("Customer First Name: " );
		firstName = reader.nextLine();
		System.out.print("Customer Last Name: ");
                lastName = reader.nextLine();
                System.out.print("Customer City: ");
                cityName= reader.nextLine();
                System.out.print("Customer Credit Card: ");
                creditCard= reader.nextLine();
                System.out.print("Customer Postal Code: ");
                postalCode= reader.nextLine();
                enterCustomerInfo(creditCard, postalCode);
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 

        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void enterCustomerInfo() {
        validateCreditCard(creditcard);
	validatePostalCode(postalCode); 
	 System.out.print("Customer First Name: " );
	String firstName = reader.nextLine();
	System.out.print("Customer Last Name: ");
	String  lastName = reader.nextLine();
                System.out.print("Customer City: ");
       	String  cityName= reader.nextLine();
        
	    System.out.print("Customer Credit Card: ");
        String  creditCard= reader.nextLine();
                System.out.print("Customer Postal Code: ");
        String  postalCode= reader.nextLine();
                
           
        if ((validCredit && validPostal) == true){
            customerIDNumber +=1;
            return customerIDNumber;
        } 
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validatePostalCode(){
     BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("postal_codes.csv"));
            String line = reader.readLine();
            String format = "";
    
            while(line!=null) {
                line = reader.readLine();
                if(line == null) {
                    break;
                }
                format = line.substring(0, 3);
                if(postalcode.equalsIgnoreCase(format)) {
                    reader.close();
                    return true;
                } 
            }
            reader.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean validateCreditCard(String creditCard){
        int length = creditCard.length();
        String reversedCard = "";
        for (int i =0; i < length; i++){
            int position = length - 1 - i; 
            char characterAtPosition = creditCard.charAt(position);
            reversedCard += characterAtPosition; 
        }
        System.out.println(reversedCard);
        int sum1 = 0;  
        for (int i = 0; i < length; i+=2){
            char oddNumber = reversedCard.charAt(i);
            int oddNumberInteger = Character.getNumericValue(oddNumber);
            sum1 += oddNumberInteger;
        }
        System.out.println(sum1);
        int sum2 = 0;
        for(int j = 1; j < length; j+=2){
            char evenNumber = reversedCard.charAt(j);
            int evenNumberInteger = Character.getNumericValue(evenNumber);
            int doubledEven = evenNumberInteger *2;
            int remainderValue =0;
            int tenthDigit=0;
            if (doubledEven > 9){
                remainderValue = doubledEven%10;  
                tenthDigit = doubledEven/10; 
                int partialSum = tenthDigit+remainderValue;
                sum2 += partialSum;
                System.out.println("Remainder: " + remainderValue);
                System.out.println("Tenth:" + tenthDigit);
                System.out.println("Partia: " + partialSum); 
            }
            else{
                sum2 += doubledEven;
            }   
        }
        System.out.println(sum2);
        if (((sum2 + sum1)%10) ==0){
            System.out.println("Valid Credit Card");
            return true;
        }
        else{
            System.out.println("Invalid Credit Card");
            return false;
        }
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(String name, String city, String postalCode, String credNum){
	    
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}
