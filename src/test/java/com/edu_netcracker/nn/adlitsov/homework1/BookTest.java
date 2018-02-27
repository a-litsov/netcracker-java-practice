package com.edu_netcracker.nn.adlitsov.homework1;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class BookTest {
    public static final int MAX_PRICE = 1_000;

    @Test
    void toStringShouldWorkProperlyForSingleAuthor() {
        Random rnd = new Random();
        Book.Author[] authors = {new Book.Author("Kay Horstmann", "horstmann@gmail.com", 'm')};
        final double price = rnd.nextDouble() * MAX_PRICE;
        final int qty = rnd.nextInt(Integer.MAX_VALUE);

        Book book = new Book("Super Puper Book", authors, price, qty);
        String expectedStr = "Book[name=Super Puper Book, authors={Author[name=Kay Horstmann, email=horstmann@gmail.com, " +
                "gender=m]}, price=" + price + ", qty=" + qty + "]";

        assertEquals(expectedStr, book.toString(), "Book string representation must be: " + expectedStr);
    }

    @Test
    void toStringShouldWorkProperlyForSeveralAuthors() {
        Random rnd = new Random();
        Book.Author[] authors = {
                new Book.Author("Kay Horstmann", "horstmann@gmail.com", 'm'),
                new Book.Author("Bruce Eckel", "eckel@gmail.com", 'm')
        };
        final double price = rnd.nextDouble() * MAX_PRICE;
        final int qty = rnd.nextInt(Integer.MAX_VALUE);

        Book book = new Book("New Brilliant Book", authors, price, qty);
        String expectedStr = "Book[name=New Brilliant Book, authors={Author[name=Kay Horstmann, email=horstmann@gmail.com, " +
                "gender=m], Author[name=Bruce Eckel, email=eckel@gmail.com, gender=m]}, price=" + price + ", qty=" + qty + "]";

        assertEquals(expectedStr, book.toString(), "Book string representation must be: " + expectedStr);
    }

    @Test
    void cannotCreateBookWithoutAuthors() {
        assertThrows(IllegalArgumentException.class, () -> new Book("", new Book.Author[]{}, 1));
    }

    @Test
    void cannotCreateBookWithNullAuthors() {
        Book.Author[] authors = {
                new Book.Author("Kay Horstmann", "horstmann@gmail.com", 'm'),
                new Book.Author("Bruce Eckel", "eckel@gmail.com", 'm'),
                null
        };

        assertThrows(IllegalArgumentException.class, () -> new Book("", authors, 1));
    }
}
