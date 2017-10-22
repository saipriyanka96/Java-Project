package homeApp;
//Package is a grouping of related types providing access protection and name
import java.util.*;
public class Customer
{
//public keyword is used in the declaration of a class,method or 
//field;public classes,method and fields can be accessed by the 
//members of any class.
//class defines instance and class fields,methods and inner classes as
//well as specifying the interfaces the class implements and the 
//immediate superclass of the class
    /*Customer should know about his
    customerID ,customerName, contactNumber and the details of
    products booked by the customer*/
//We are creating the values using the data types and id should start
//from 0 that why we gave the value as 0 
    int customerID;
    String customerName;
    int contactNumber;
    List <Product>bookedProductList;
    
    /*The Constructor creates Customer object with the given id, name &
    contact no*/
    public Customer (int custID,String name,int contactNo)
    {
       customerID  = custID;
       customerName = name;
       contactNumber = contactNo;
       bookedProductList = new ArrayList<Product>();
    }
    
    
    /*
    Public void addBookedProduct(Product obj) :
    The method adds the product which is booked by the customer to
    his bookedProductList. */
    public void addBookedProduct(Product obj)
    {
        bookedProductList.add(obj);
    }
    
}
