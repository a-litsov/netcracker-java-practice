package com.netcracker.adlitsov.homework1;

import java.util.Arrays;
import java.util.Random;

public class Book {
    private String name;
    private Author[] authors;
    private double price;
    private int qty;

    public Book(String name, Author[] authors, double price) {
        if (name == null) {
            throw new IllegalArgumentException("Book must have a name");
        }
        validateAuthors(authors);
        validatePrice(price);

        this.name = name;
        this.authors = Arrays.copyOf(authors, authors.length);
        this.price = price;
    }

    public Book(String name, Author[] authors, double price, int qty) {
        this(name, authors, price);

        validateQty(qty);
        this.qty = qty;
    }

    private void validateAuthors(Author[] authors) {
        if (authors == null || authors.length == 0) {
            throw new IllegalArgumentException("At least 1 author must be specified");
        }

        for (Author author : authors) {
            if (author == null) {
                throw new IllegalArgumentException("All author references in authors array must be not null");
            }
        }
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative number");
        }
    }

    private void validateQty(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Qty must be non-negative integral number");
        }
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return Arrays.copyOf(authors, authors.length);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        validateQty(qty);
        this.qty = qty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book[name=" + name + ", authors={");
        for (int i = 0; i < authors.length; i++) {
            sb.append(authors[i]).append((i < authors.length - 1) ? ", " : "}");
        }
        sb.append(", price=" + price + ", qty=" + qty + "]");

        return sb.toString();
    }

    public String getAuthorNames() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < authors.length; i++) {
            sb.append(authors[i].getName()).append((i < authors.length - 1) ? ", " : "");
        }
        return sb.toString();
    }

    public static class Author {
        private String name;
        private String email;
        private char gender;

        public Author(String name, String email, char gender) {
            if (name == null || email == null) {
                throw new IllegalArgumentException("Author must have name and e-mail");
            }
            this.name = name;
            this.email = email;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Author[name=" + name + ", email=" + email + ", gender=" + gender + "]";
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        Author[] authors = {
                new Author("Kay Horstmann", "horstmann@gmail.com", 'm'),
                new Author("Steve McConnell", "mcconnell@gmail.com", 'm'),
                new Author("Bruce Eckel", "eckel@gmail.com", 'm')
        };
        final int price = 200;
        final int qty = rnd.nextInt(Integer.MAX_VALUE);

        Book book = new Book("New Super Book", authors, price, qty);
        System.out.println(book);
        System.out.println(book.getAuthorNames());
    }
}
