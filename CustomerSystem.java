// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.*
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
        int customerID = 0;

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                info = enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile(info);
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
	Scanner reader = new Scanner(System.in);
	    
	String creditCard = "";
	String postalCode = "";
        
	boolean validCard = validateCreditCard(creditCard);
	boolean validPostal = validatePostalCode(postalCode); 
	
	System.out.print("Customer First Name: " );
	String firstName = reader.nextLine();
	System.out.print("Customer Last Name: ");
	String  lastName = reader.nextLine();
	System.out.print("Customer City: ");
       	String  cityName= reader.nextLine();
	
	while (!validCard){
		System.out.print("Customer Credit Card: ");
        	creditCard= reader.nextLine();
		validCard = validCreditCard(creditCard);
		if (validCard ==false){
			System.out.println("Invalid Credit Card Information. Please enter again.");
		}
		else{
			System.out.println("Valid Credit Card Information.");
			validCard = true;
		}
			
	}
	while(!validpostal) {
            System.out.print("Customer Postal Code: ");
            postalCode = reader.nextLine();
            boolean validp = validatePostalCode(postalCode);
            if(validp == true) {
                System.out.println("Valid Postal Code");
                validpostal = true;
            } else if(postalCode.length()<3) {
                System.out.println("Invalid postal code, try again: ");
                postalCode = reader.nextLine();
            } else {
                System.out.println("Invalid postal code, try again: ");
                postalCode = reader.nextLine();
            }
        }      
        String concat = fullName+"|"+cityName+"|"+creditCard+"|"+postalCode;
        
        reader.close();
        return concat;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
     public static boolean validatePostalCode(String postalcode){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("postal_codes.csv"));
            String line = reader.readLine();
            String format = "";
            String pformat = postalcode.substring(0, 3);
    
            while(line!=null) {
                line = reader.readLine();
                if(line == null) {
                    break;
                }
                format = line.substring(0, 3);
                if(pformat.equalsIgnoreCase(format)) {
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
        int sum1 = 0;  
        for (int i = 0; i < length; i+=2){
            char oddNumber = reversedCard.charAt(i);
            int oddNumberInteger = Character.getNumericValue(oddNumber);
            sum1 += oddNumberInteger;
        }
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
            }
            else{
                sum2 += doubledEven;
            }   
        }
        
        if (((sum2 + sum1)%10) ==0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void generateCustomerDataFile(String concatn){
        try {
            FileWriter myWriter = new FileWriter("customerDataFile.csv", true);
            myWriter.write(concatn+"\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
