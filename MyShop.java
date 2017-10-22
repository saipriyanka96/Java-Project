package homeApp;
import java.util.*;
public class MyShop{
        
    public static RetailStore store;
    
     public static void main(String []args){
        boolean bOpenShop = true;
 /* if the shop is open then it will show the shop memu other
  *wise it will show as it is closed 
  */
        System.out.println("Welcome to Spencer Home Appliances SHOP");
        System.out.println("----------------------------");
        
       /*if shop is closed
       returns the value it is closed
        */
        if (!bOpenShop){
            System.out.println("-SHOP IS CLOSED-");
            return;
        }
        
        /*Here open the store*/
        store = new RetailStore("Spencer Home Appliances SHOP");
        
        /* stock the products in the store*/
        store.addProduct("LG Television T101","",200,4);
        store.addProduct("LG Refrigerator R601","",300,4);
        store.addProduct("LG Micro Oven M701","",400,4);
        store.addProduct("Sony 24 inch TV","",500,20);
        
        /* open the shop*/
        openShop();
        
     }
     
    /* display main menu*/
    public static void openShop()
 //static is used for memory management
//void is used to define the return type of the method
//String[] args is actually an array of java.lang.String type and it's name is args here
			
    {
        String custName = "";//creating the customer name with an empty value
        String prodName = "";//creating the customer id with an empty value
        int prodQuantityNeeded=0;
     //when we didn't give any value this then will be equal to 0
        int prodQuantityAvailable=0;
        int custContact =0;
        int confirmBooking=0;
        Scanner reader = new Scanner(System.in); 
        // Reading from System.in
        
        List<String> menuList  = new ArrayList<String>();
        //Adding the products and these are the list of elements we have
        //and we created using array list
        menuList.add("LG Television T101");
        menuList.add("LG Refrigerator R601");
        menuList.add("LG Micro Oven M701");
        menuList.add("LG Micro Oven M701");
        menuList.add("Samsung Galaxy Phone");
        
        int n=-1;
        //giving value is equal to -1 and here if we give the value -1
        //or 0 then it will show again the display options;
        while (n != 6)
       //if the value is not equal to 6 then it will clear screen
        {
            clearScreen();
        
            /* display menu options */
            
            System.out.println("\n\nPLEASE ENTER THE PRODUCT YOU WANT TO BOOK");
            System.out.println("\n\n");
            
            System.out.println("1. " + menuList.get(0) + " (" + store.checkProductAvailability(menuList.get(0)) + " )");
            System.out.println("2. " + menuList.get(1)+ " (" + store.checkProductAvailability(menuList.get(1)) + " )");
            System.out.println("3. " + menuList.get(2)+ " (" + store.checkProductAvailability(menuList.get(2)) + " )");
            System.out.println("4. " + menuList.get(3)+ " (" + store.checkProductAvailability(menuList.get(3)) + " )");
            System.out.println("5. " + menuList.get(4)+ " (" + store.checkProductAvailability(menuList.get(4)) + " )");/* a non-existing product for testing*/
            System.out.println("6. CLOSE SHOP");
            System.out.println("\n\nPlease select product : ");
            n = reader.nextInt();
            
            if (n==6){
            	//if n=6 then it will say print statement and end of choices
                System.out.println("\n\n THANK YOU. GOOD BYE.");
                return;
            }
            
            if (n >=1 && n<=5)
          //if the options are taken between 1 to 5 then we need to take the customer details
            {
                prodName = (String)menuList.get(n-1);
                System.out.println("\n\nProduct: " + prodName);
                System.out.println("\n\nProvide Customer Name: ");
                
                custName = readString();//it will read the string value
                
                System.out.println("\n\nProvide Customer Contact: ");
                
                custContact = readInteger();//reads the integer value
                
                System.out.println("\n\nProvide Quantity: ");
                prodQuantityNeeded = readInteger();
                
                int customerID = store.addCustomer(custName,custContact);
                //when the id is given then we need add and show the name and contact of the customer
                
                /*print order*/
                clearScreen();
                System.out.println("\n\n\n\n\n\nDear customer " + custName );
                System.out.println("Your phone number is " + custContact);
                System.out.println("Your ordered " + prodQuantityNeeded + " of " + prodName );
                //checks the availability of the customer
                prodQuantityAvailable =
                                store.checkProductAvailability(menuList.get(n-1));
                
                System.out.println("\n\nAvailable Quantity: " + prodQuantityAvailable);
                
 /*once the product availability is checked then we see the product quantity which starts from 0 and products 
  * customer asked. We need to verify whether the no.of products availability is greater then needed.
  * if it is we have the customer id and add them in booked list              
  */
                Product p;
                for (int i=0,TotalProdAddedToCustList=0;i<store.productList.size();i++)
                {
                    p= store.productList.get(i);
                    if (p.productName == prodName)
                    {
                        TotalProdAddedToCustList++;
                        if (TotalProdAddedToCustList <= prodQuantityAvailable && TotalProdAddedToCustList<=prodQuantityNeeded )
                            (store.getCustomer(customerID)).bookedProductList.add(p);
                    }
                }
                //here we will calculate the net amount of the product
                double netAmnt = store.calculateNetAmount(customerID,10);
                
                /*System.out.println("\n\nNet Amount:" + netAmnt);*/
                System.out.println("\n\nConfirm Booking: 1=Yes,0=No:");
             //confirmation will be don with reading integer   
                confirmBooking = readInteger();
                if (confirmBooking == 1)
                	//if booking is equal to 1 then need is equal to availability then id,product name and availability 
                	//should be checked else only values will be printed
                {
                    if (prodQuantityNeeded >= prodQuantityAvailable)
                        store.bookProduct(customerID,prodName,prodQuantityAvailable);
                    else
                        store.bookProduct(customerID,prodName,prodQuantityNeeded);
//once the booking is completed it will print print statements
                    System.out.print("\n\nThank you for booking");
                    System.out.print("\n\nContinue");
                    
                    String s = reader.nextLine();
                    //reads the next line
                }
                if (confirmBooking == 0)
                	//if we are not booking also we will have same statements
                {
                    System.out.print("\n\nThank you. Visit Again");
                    System.out.print("\n\nContinue");
                    String s = reader.nextLine();
                }
                
                n=-1; //reinitialize
                
            } //menu option selected n>=1 and n<=5
        }//back to shop
    }//end openShop
    
    
    /* clears the console*/
    public static void clearScreen() {  
            System.out.print("\033[H\033[2J");  
            System.out.print("\f");  
            System.out.flush();  
    }//end clearScreen

    /* read the string*/
    public static String readString() {  
                String str="";
                Scanner reader = new Scanner(System.in);  
                // Reading from System.in
                boolean breadLine = false;
                while (breadLine==false) {
                       str = reader.nextLine();
                       if (str.length() >0)
                            breadLine = true;
                }
                return str;
        }
    
 // add a class variable for the
    // readInteger objects instantiated
    /* read the integer*/
    public static int readInteger() {  
                int number=-1;
               //initializing the values with -1 
                Scanner reader = new Scanner(System.in); 
//Java Scanner class is widely used to parse text for string
       //and primitive types using regular expression.               
                // Reading from System.in
                boolean breadLine = false;
  //booleans gives the value of true or false
                while (breadLine==false) {
                       number  = reader.nextInt();
/*Scans the next token of the input as an int. 

An invocation of this method of the form nextInt() 
behaves in exactly the same way as the invocation nextInt
(radix), where radix is the default radix of this scanner.*/
//if statement tests the condition. It executes the if block 
//if condition is true.
                       if (number>=0)
  //if number is greater than equal to 0 then the line is true
                            breadLine = true;
                }
                return number;
                //returns the value
        }
}
