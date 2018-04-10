package mdsouza5.hw7and8crudoperation;

/**
 * Created by mdsouza on 4/5/18.
 */

public class Book {

    String bookName;
    String authorName;
    Integer bookRating;

    public Book() {
    }

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Book(String bookName, String authorName, Integer bookRating) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookRating = bookRating;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }
}
