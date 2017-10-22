package homeApp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RetailStore{
/*
RetailStore class should know about the storeName,
customers registered with the store and product details available in
the store.
RetailStore holds following responsibilities. Implement the
below mentioned responsibilities for RetailStore class

We are creating the values using the data types and id should start
from 0 that why we gave the value as 0 
*/
    String storeName;
    int currentCustomerID =0;
    int currentProductID =0;
  /*List Interface is the sub interface of Collection
   * We are creating the list in the name of class name and initialize
   * those values in the form of collection
   */
    List <Customer> customerList ;
    List <Product> productList;
/* RetailStore(String storeName):
The constructor initialize the storeName with the given input value.*/
    RetailStore(String rstoreName)
    {
        storeName = rstoreName;
        customerList = new ArrayList<Customer>();
        //creating the new array list of customer and product
        productList = new ArrayList<Product>();
        
    }
    
/* public int generateCustomerID()
The method generates the customerID by incrementing the value of previous customers ID by 1. The
value of Customer Id is initially set to zero. For the first customer added to
the system the ID should be 1,for the second customer,
ID should be 2 and so on. The method should return the ID generated for the customer.
*/
    public int generateCustomerID()
    {
        currentCustomerID = currentCustomerID + 1;
        return currentCustomerID;
    }
    
/*
public int generateProductID()
The method generates the productID by incrementing the value of previous products ID by 1. The
value of productId is initially set to zero. For the first product added to the
system the ID should be 1,for the second product , ID should be 2 and so
on.
The method should return the ID generated for the product.    */
    public int generateProductID()
    {
        
        currentProductID = currentProductID + 1;
        return currentProductID;
    }
    
/*
    public void addCustomer (String name,int contactNo)
The method creates a customer ID , with that it creates a customer object and
then it adds to the customerList of RetailStore.
Hint:To create the customerId,use generateCustomerID method.
*/
    public int addCustomer (String name,int contactNo)
    {
        int custID = generateCustomerID();
        Customer c = new Customer(custID, name, contactNo);
        customerList.add(c);
        return custID;
    }
/*
public void addProduct (String name,String status,double price)
-
The method creates a product ID , with that it creates a product object and then
it adds to the productList of RetailStore
Hint:To create the productId,use generateProductID method.
*/
    public void addProduct (String name,String status,double price, int quantity)
    {
        
        /* what is the status used for is not clear*/
        Product p;
        for(int i=0;i<quantity;i++)
        {
            p = new Product(generateProductID(),name,price);
            productList.add(p);
        }
        
    }
    
/*
    
 public int checkProductAvailability(String ProductName)
 The method returns the count of products which are in status
  Available   with the given name
*/
    public int checkProductAvailability(String ProductName)
    {
        
        Product p;
        int countAvailable = 0;
        for (int x=0; x<productList.size(); x++)
        {
            p = productList.get(x);
            
            if (p.productName == ProductName)
            {
                if (p.productStatus == "Available")
                {
                    countAvailable = countAvailable + 1;
                    
                }
            //checks whether the product is available or not and if it is available then returns the count
            }
        }
    
    return countAvailable;
    }
        
/*        
      public int bookProduct(int CustomerID,String ProductName, int
NumberOfProduct)
-
The method checks for the availability of the given
product in the productList of retail store .If the required number of products
are available in the store , then that many products are booked which means
that ,that many products are being added to the bookedProductList of customer.
  */
        public int bookProduct(int CustomerID,String ProductName,
        int NumberOfProduct)
        {
            int countAvailable=0;
            int countBook =0;
            Customer searchcust;
            Customer cust = null;
            
            
            Product p;
            int countBooked = 0;
            
            cust = getCustomer(CustomerID);
            
            if (cust == null)
            {
                /* customer not found. return -1 */
                countBook = -1;
                return countBook;
            }
            
            countAvailable = checkProductAvailability(ProductName);
            if (countAvailable == 0)
            {
                /* product not available*/
                countBook = 0;
            
            }
            else if (countAvailable >= NumberOfProduct)
            {
                /* product is in stock*/
                countBook = NumberOfProduct;
            }
            else
            {
                /* product is in stock but less that what is requested*/
                countBook = countAvailable;
            }
            
            if (countBook >0)
            {
                for (int x=0; (x<productList.size()) && (countBooked != countBook) ; x++)
                    {
                        p = productList.get(x);
                        
                        if (p.productName == ProductName)
                        {
                            if (p.productStatus == "Available")
                            {
                                p.productStatus = "Booked";
                                countBooked = countBooked + 1;
                            }
                        }
                    }
             }
        
        System.out.println("\n\n" + countBooked + " number of product " + ProductName + " has been booked");
        System.out.println("\n\n");
        return countBooked;
        }
        
/*       
public double calculateNetAmount(int customerID,double discount)
-
The method takes a customer ID and discount percentage as
input parameters and calculates and returns the
net amount that the customer need to pay for the entire booking he has made. Net amount=
sum of price of all products
-
discount applicable (note: discount is applied to
the sum of price of all products not on individual product price)
If the given customer is not available in the customer list,
then the method should return -1.
*/
    public double calculateNetAmount(int customerID,double discount)
    {
        
        double NetAmount = 0;
        
        Customer cust = getCustomer(customerID);
        Product prod;
        
        if (cust == null)
        {
            NetAmount = -1;
        }   
        else
        {
            System.out.println("\n\n");
            
            for (int x=0; x<cust.bookedProductList.size(); x++)
            {
                prod = cust.bookedProductList.get(x);
                System.out.println("Adding 1 product of " + prod.productName + " with price " + prod.ProductPrice + " for customer");
                NetAmount = NetAmount + prod.ProductPrice;
            }
            
            //system is final class
          //out is a static member of system class and type printStream
          //println is method of printStream class.ln means nextline
            System.out.println("\n\nNet Amount Before Discount :" + NetAmount);
            //prints net amount before discount
            NetAmount = (NetAmount * (100-discount))/100;
            //calculates the discount
            System.out.println("\n\nNet Amount After Discount :" + NetAmount);
          //prints net amount after discount
        }
        
        return NetAmount;
    //returns net amount
    }
    
    
    public Customer getCustomer(int customerID)
    {
        Customer cust=null;
        Customer searchcust;
        
        for (int x=0; x<customerList.size(); x++)
            {
                searchcust = customerList.get(x);
            
                if (searchcust.customerID == customerID)
                {
                    /* customer found*/
                    cust = searchcust;
                }
            }
            
        return cust;
    }
  
}
