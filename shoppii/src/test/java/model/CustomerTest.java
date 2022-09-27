package model;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import org.junit.Test;

import model.Customer;

public class CustomerTest {

    @Test
    public void testGetName() {
        long millis=System.currentTimeMillis();  
        Date date=new java.sql.Date(millis);  
        System.out.println("Test Get Name");
        Customer customer = new Customer("A", "a@gmail.com",
             "0123456789", date, true, "password");
        assertEquals("A", customer.getName());
    }

    @Test
    public void testSetAndGetName() {
        long millis=System.currentTimeMillis();  
        Date date=new java.sql.Date(millis);  
        System.out.println("Test Set and Get Name");
        Customer customer = new Customer("A", "a@gmail.com",
             "0123456789", date, true, "password");
        customer.setName("B");
        assertEquals("B", customer.getName());
    }
    
}
