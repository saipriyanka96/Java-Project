package homeApp;
public class Product{
    int productID;
    String productName;
    String productStatus;
    double ProductPrice;
/*Product(int productID,String productName, double ProductPrice)
The constructor initializes the value of productID, name & price with
the given values .Initially the status of the product is set to Available*/
    Product(int prodID,String prodName, double ProdPrice)
    {
         productID = prodID;
         productName= prodName;
         ProductPrice = ProdPrice;
         productStatus = "Available"; /*set it to Available*/
    }
}