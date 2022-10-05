package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import org.junit.Test;

import com.password4j.Argon2Function;
import com.password4j.types.Argon2;

import model.Customer;

public class CustomerTest {
    private int memory = 2048;
    private int iterations = 10;
    private int parallelism = 1;
    private int outputLength = 128;
    private Argon2 variant = Argon2.I;

    private Argon2Function argon2 = Argon2Function.getInstance(memory, iterations, parallelism, outputLength, variant);

    @Test
    public void testGetName() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        System.out.println("Test Get Name");
        Customer customer = new Customer(1, "A", "a@gmail.com",
                "0123456789", date, true, "password");
        assertEquals("A", customer.getName());
    }

    @Test
    public void testSetAndGetName() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        System.out.println("Test Set and Get Name");
        Customer customer = new Customer(1, "A", "a@gmail.com",
                "0123456789", date, true, "password");
        customer.setName("B");
        assertEquals("B", customer.getName());
    }

    @Test
    public void testVerifyPasswordTrue() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        System.out.println("Test Verify Password");
        Customer customer = new Customer(1, "A", "a@gmail.com",
                "0123456789", date, true, "password", argon2);
        assertTrue("Password match!", customer.verifyPassword("password"));
    }

    @Test
    public void testVerifyPasswordFalse() {
        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);
        System.out.println("Test Verify Password");
        Customer customer = new Customer(1, "A", "a@gmail.com",
                "0123456789", date, true, "password", argon2);
        assertTrue("Password not match!", !customer.verifyPassword("hello"));
    }

}
