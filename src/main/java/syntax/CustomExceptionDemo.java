package syntax;

import java.util.HashMap;
import java.util.Map;

/**
 * Java Program to create custom exception and examples to show how to use
 * custom exception in Java.
 *
 * @author Javin Paul
 */

//http://javarevisited.blogspot.com/2014/06/how-to-create-custom-exception-in-java.html

public class CustomExceptionDemo {

    private static final Map<Integer, String> products = new HashMap<>();

    static {
        products.put(100, "Coke");
        products.put(101, "KitKat");
        products.put(102, "Bisuits");
        products.put(103, "Toast");
    }

    public static void main(String args[]) {
        CustomExceptionDemo t = new CustomExceptionDemo();
        t.getProduct(1000);
    }

    public String getProduct(int id) {
        if (products.get(id) == null) {
            throw new NoSuchProductException("No such product exists", id);
        }
        return products.get(id);
    }
}

class NoSuchProductException extends RuntimeException {

    private int productId;

    public NoSuchProductException() {
        super();
    }

    public NoSuchProductException(String message, int productId) {
        super(message);
        this.productId = productId;
    }

    public NoSuchProductException(String message, int productId, Throwable cause) {
        super(message, cause);
        this.productId = productId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " for productId :" + productId;
    }

    public int getProductId() {
        return productId;
    }
}