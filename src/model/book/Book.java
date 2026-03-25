package model.book;

import interfaces.Displayable;
import java.time.LocalDate;

public class Book implements Displayable {
    private static int idCounter = 1000;

    private int         bookId;
    private String      authorName;
    private String      name;
    private double      price;
    private BookStatus  status;
    private String      edition;
    private LocalDate   dateOfPurchase;
    private String      isbn;
    private String      publisher;
    private int         pageCount;

    public Book(String authorName, String name, double price,
                String edition, String isbn, String publisher, int pageCount) {
        this.bookId        = ++idCounter;
        this.authorName    = authorName;
        this.name          = name;
        this.price         = price;
        this.status        = BookStatus.AVAILABLE;
        this.edition       = edition;
        this.isbn          = isbn;
        this.publisher     = publisher;
        this.pageCount     = pageCount;
        this.dateOfPurchase = LocalDate.now();
    }

    public String getCategory() { return "Genel Kitap"; }

    public String getTitle()    { return name; }
    public String getAuthor()   { return authorName; }

    public void changeOwner(String newOwner) {

        System.out.println("Sahiplik değiştirildi → " + newOwner);
    }

    @Override
    public void display() {
        System.out.println("--- KITAP BILGISI ---");
        System.out.println("ID        : " + bookId);
        System.out.println("Kategori  : " + getCategory());
        System.out.println("Kitap Adi : " + name);
        System.out.println("Yazar     : " + authorName);
        System.out.println("Fiyat     : " + String.format("%.2f TL", price));
        System.out.println("Durum     : " + status);
        System.out.println("Baski     : " + edition);
        System.out.println("ISBN      : " + isbn);
        System.out.println("Yayinevi  : " + publisher);
        System.out.println("Sayfa     : " + pageCount);
        System.out.println("Alim Tar. : " + dateOfPurchase);
    }

    @Override
    public String getSummary() {
        return String.format("[%d] %-35s | %-20s | %s | %.2f TL",
                bookId, name, authorName, status, price);
    }

    @Override
    public String toString() { return getSummary(); }

    public int        getBookId()               { return bookId; }
    public String     getAuthorName()           { return authorName; }
    public void       setAuthorName(String a)   { this.authorName = a; }
    public String     getName()                 { return name; }
    public void       setName(String n)         { this.name = n; }
    public double     getPrice()                { return price; }
    public void       setPrice(double p)        { this.price = p; }
    public BookStatus getStatus()               { return status; }
    public void       setStatus(BookStatus s)   { this.status = s; }
    public String     getEdition()              { return edition; }
    public void       setEdition(String e)      { this.edition = e; }
    public LocalDate  getDateOfPurchase()       { return dateOfPurchase; }
    public String     getIsbn()                 { return isbn; }
    public void       setIsbn(String isbn)      { this.isbn = isbn; }
    public String     getPublisher()            { return publisher; }
    public void       setPublisher(String p)    { this.publisher = p; }
    public int        getPageCount()            { return pageCount; }
    public void       setPageCount(int p)       { this.pageCount = p; }
}
