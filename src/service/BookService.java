package service;

import model.Library;
import model.book.*;
import model.person.Author;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private final Library library;

    public BookService(Library library) {
        this.library = library;
    }

    public Book addGeneralBook(Scanner sc) {
        System.out.println("\n--- Yeni Kitap Ekle");
        String author    = prompt(sc, "Yazar Adı     : ");
        String name      = prompt(sc, "Kitap Adı     : ");
        double price     = promptDouble(sc, "Fiyat (TL)    : ");
        String edition   = prompt(sc, "Baskı No      : ");
        String isbn      = prompt(sc, "ISBN          : ");
        String publisher = prompt(sc, "Yayınevi      : ");
        int    pages     = promptInt(sc, "Sayfa Sayısı  : ");

        Book book = new Book(author, name, price, edition, isbn, publisher, pages);
        if (library.addBook(book)) {
            updateAuthorCatalog(author, book);
            System.out.println("OK: Kitap eklendi. ID: " + book.getBookId());
        }
        return book;
    }

    public Book addJournal(Scanner sc) {
        System.out.println("\n--- Yeni Dergi Ekle");
        String author    = prompt(sc, "Editör/Yazar  : ");
        String name      = prompt(sc, "Dergi Adı     : ");
        double price     = promptDouble(sc, "Fiyat (TL)    : ");
        String edition   = prompt(sc, "Baskı         : ");
        String isbn      = prompt(sc, "ISBN          : ");
        String publisher = prompt(sc, "Yayınevi      : ");
        int    pages     = promptInt(sc, "Sayfa Sayısı  : ");
        String field     = prompt(sc, "Konu Alanı    : ");
        int    issue     = promptInt(sc, "Sayı No       : ");
        String issn      = prompt(sc, "ISSN          : ");

        Journals j = new Journals(author, name, price, edition, isbn, publisher, pages, field, issue, issn);
        if (library.addBook(j)) {
            System.out.println("OK: Dergi eklendi. ID: " + j.getBookId());
        }
        return j;
    }

    public Book addStudyBook(Scanner sc) {
        System.out.println("\n--- Yeni Ders Kitabı Ekle ");
        String author    = prompt(sc, "Yazar Adı     : ");
        String name      = prompt(sc, "Kitap Adı     : ");
        double price     = promptDouble(sc, "Fiyat (TL)    : ");
        String edition   = prompt(sc, "Baskı         : ");
        String isbn      = prompt(sc, "ISBN          : ");
        String publisher = prompt(sc, "Yayınevi      : ");
        int    pages     = promptInt(sc, "Sayfa Sayısı  : ");
        String subject   = prompt(sc, "Ders/Konu     : ");
        String level     = prompt(sc, "Seviye        : ");
        System.out.print("Cevap Anahtarı var mı? (e/h): ");
        boolean hasKey   = sc.nextLine().trim().equalsIgnoreCase("e");

        StudyBooks sb = new StudyBooks(author, name, price, edition, isbn, publisher, pages, subject, level, hasKey);
        if (library.addBook(sb)) {
            updateAuthorCatalog(author, sb);
            System.out.println("OK: Ders kitabı eklendi. ID: " + sb.getBookId());
        }
        return sb;
    }

    public Book addMagazine(Scanner sc) {
        System.out.println("\n--- Yeni Magazin Ekle");
        String author    = prompt(sc, "Editör        : ");
        String name      = prompt(sc, "Magazin Adı   : ");
        double price     = promptDouble(sc, "Fiyat (TL)    : ");
        String edition   = prompt(sc, "Sayı          : ");
        String isbn      = prompt(sc, "ISBN          : ");
        String publisher = prompt(sc, "Yayınevi      : ");
        int    pages     = promptInt(sc, "Sayfa Sayısı  : ");
        String freq      = prompt(sc, "Periyot       : ");
        int    monthYear = promptInt(sc, "Ay/Yıl (MMYYYY): ");
        String genre     = prompt(sc, "Tür/Konu      : ");

        Magazines m = new Magazines(author, name, price, edition, isbn, publisher, pages, freq, monthYear, genre);
        if (library.addBook(m)) {
            System.out.println("OK: Magazin eklendi. ID: " + m.getBookId());
        }
        return m;
    }

    public void updateBook(Scanner sc) {
        System.out.print("Güncellenecek kitap ID: ");
        int id = promptInt(sc, "");
        Book book = library.getBookById(id);
        if (book == null) { System.out.println("UYARI: Kitap bulunamadı."); return; }

        System.out.println("Mevcut bilgiler:");
        book.display();
        System.out.println("\nGüncellenecek alan:");
        System.out.println("1) Kitap Adı   2) Yazar   3) Fiyat   4) Baskı   5) Sayfa Sayısı");
        int choice = promptInt(sc, "Seçim: ");
        switch (choice) {
            case 1 -> book.setName(prompt(sc, "Yeni İsim: "));
            case 2 -> book.setAuthorName(prompt(sc, "Yeni Yazar: "));
            case 3 -> book.setPrice(promptDouble(sc, "Yeni Fiyat: "));
            case 4 -> book.setEdition(prompt(sc, "Yeni Baskı: "));
            case 5 -> book.setPageCount(promptInt(sc, "Yeni Sayfa Sayısı: "));
            default -> System.out.println("Geçersiz seçim.");
        }
        System.out.println("OK: Kitap güncellendi.");
    }

    public void deleteBook(Scanner sc) {
        int id = promptInt(sc, "Silinecek kitap ID: ");
        Book book = library.getBookById(id);
        if (book == null) { System.out.println("UYARI: Kitap bulunamadı."); return; }
        if (book.getStatus() == BookStatus.BORROWED) {
            System.out.println("UYARI: Bu kitap ödünçte, silinemez.");
            return;
        }
        library.removeBook(id);
        System.out.println("OK: Kitap silindi: " + book.getName());
    }

    public void listAllBooks() {
        Collection<Book> books = library.getAllBooks();
        if (books.isEmpty()) { System.out.println("Sistemde kitap yok."); return; }
        System.out.println("\n--- TÜM KİTAPLAR (" + books.size() + " adet)---");
        books.forEach(b -> System.out.println(b.getSummary()));
    }

    public void listByCategory(Scanner sc) {
        System.out.println("Kategoriler: Genel Kitap | Dergi / Jurnal | Ders/Çalışma Kitabı | Magazin");
        String cat = prompt(sc, "Kategori girin: ");
        List<Book> books = library.getBooksByCategory(cat);
        if (books.isEmpty()) { System.out.println("Bu kategoride kitap yok."); return; }
        System.out.println("\n--- " + cat.toUpperCase() + " (" + books.size() + " adet)---");
        books.forEach(b -> System.out.println(b.getSummary()));
    }

    public void listByAuthor(Scanner sc) {
        String author = prompt(sc, "Yazar adı: ");
        List<Book> books = library.searchByAuthor(author);
        if (books.isEmpty()) { System.out.println("Bu yazara ait kitap bulunamadı."); return; }
        System.out.println("\n--- " + author.toUpperCase() + " - ESERLERİ (" + books.size() + ")---");
        books.forEach(b -> System.out.println(b.getSummary()));
    }

    public void searchBook(Scanner sc) {
        System.out.println("Arama yöntemi: 1) ID  2) İsim  3) Yazar");
        int method = promptInt(sc, "Seçim: ");
        switch (method) {
            case 1 -> {
                int id = promptInt(sc, "Kitap ID: ");
                Book b = library.getBookById(id);
                if (b != null) b.display();
                else System.out.println("UYARI: Kitap bulunamadı.");
            }
            case 2 -> {
                String name = prompt(sc, "Kitap adı: ");
                library.searchByName(name).forEach(Book::display);
            }
            case 3 -> {
                String auth = prompt(sc, "Yazar adı: ");
                library.searchByAuthor(auth).forEach(Book::display);
            }
            default -> System.out.println("Geçersiz seçim.");
        }
    }

    private void updateAuthorCatalog(String authorName, Book book) {
        library.getAuthors().stream()
                .filter(a -> a.getName().equalsIgnoreCase(authorName))
                .findFirst()
                .ifPresent(a -> a.addBook(book));
    }

    private String prompt(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    private int promptInt(Scanner sc, String message) {
        System.out.print(message);
        try { int v = Integer.parseInt(sc.nextLine().trim()); return v; }
        catch (NumberFormatException e) { return 0; }
    }

    private double promptDouble(Scanner sc, String message) {
        System.out.print(message);
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }
}
