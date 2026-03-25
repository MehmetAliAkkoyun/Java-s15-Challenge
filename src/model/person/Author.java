package model.person;

import model.book.Book;
import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private String nationality;
    private String biography;

    private List<Book> publishedBooks;

    public Author(String name, String email, String phone, String nationality) {
        super(name, email, phone);
        this.nationality = nationality;
        this.biography = "";
        this.publishedBooks = new ArrayList<>();
    }

    @Override
    public String whoYouAre() { return "Yazar"; }

    public void addBook(Book book) {
        if (!publishedBooks.contains(book)) {
            publishedBooks.add(book);
        }
    }

    public void removeBook(Book book) {
        publishedBooks.remove(book);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Uyruk   : " + nationality);
        System.out.println("Kitaplar: " + publishedBooks.size() + " eser");
        publishedBooks.forEach(b -> System.out.println("  - " + b.getName()));
    }

    public void showBooks() {
        if (publishedBooks.isEmpty()) {
            System.out.println("Bu yazara ait kayıtlı kitap bulunamadı.");
            return;
        }
        System.out.println("\n=== " + getName() + " - Eserleri ===");
        publishedBooks.forEach(Book::display);
    }

    public String getNationality()               { return nationality; }
    public void   setNationality(String n)       { this.nationality = n; }
    public String getBiography()                 { return biography; }
    public void   setBiography(String bio)       { this.biography = bio; }
    public List<Book> getPublishedBooks()        { return new ArrayList<>(publishedBooks); }
}
