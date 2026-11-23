package model;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int publishedYear;
    private int quantity;

    public Book(String isbn, String title, String author, String category, int publishedYear, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishedYear = publishedYear;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public int getQuantity() {
        return quantity;
    }
}
