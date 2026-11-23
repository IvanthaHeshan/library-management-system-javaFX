package Controllers.AddBook;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class AddBookController {

    private static final List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        bookList.add(book);
    }

    public boolean isValidIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        // Remove hyphens and spaces, and turn lowercase 'x' into 'X'
        String cleaned = isbn.replace("-", "").replace(" ","").toUpperCase();

        boolean isIsbn13 = cleaned.matches("(978|979)\\d{10}");
        boolean isIsbn10 = cleaned.matches("\\d{9}[\\dX]");

        return isIsbn13 || isIsbn10;
    }

    public boolean isValidYear(int year) {
        return year >= 1000 && year <= 2026;
    }

    public boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public static List<Book> getAllBooks() {
        return bookList;
    }

    public static int getTotalBookCount() {
        int total = 0;
        for (Book book : bookList) {
            total += book.getQuantity();
        }
        return total;
    }

}
