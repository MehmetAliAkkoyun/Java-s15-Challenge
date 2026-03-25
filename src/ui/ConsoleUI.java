package ui;

import model.Library;
import model.person.Librarian;
import service.BookService;
import service.LibraryService;
import service.MemberService;

import java.util.Scanner;

public class ConsoleUI {
    private final Library        library;
    private final BookService    bookService;
    private final MemberService  memberService;
    private final LibraryService libraryService;
    private final Scanner        sc;

    public ConsoleUI(Library library) {
        this.library = library;

        Librarian defaultLib = library.getLibrarians().isEmpty()
                ? new Librarian("Sistem", "sys@lib.com", "", "SYS", "", "Genel")
                : library.getLibrarians().get(0);

        this.bookService    = new BookService(library);
        this.memberService  = new MemberService(library);
        this.libraryService = new LibraryService(library, defaultLib);
        this.sc             = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Kutüphane Otomasyon Sistemine Hosgeldiniz");
        library.showStats();

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1"  -> bookMenu();
                case "2"  -> memberMenu();
                case "3"  -> lendingMenu();
                case "4"  -> library.showStats();
                case "0"  -> { running = false; System.out.println("Gule gule!"); }
                default   -> System.out.println("Gecersiz secim.");
            }
        }
        sc.close();
    }

    private void printMainMenu() {
        System.out.println("\n--- ANA MENU ---");
        System.out.println("1) Kitap Islemleri");
        System.out.println("2) Uye Islemleri");
        System.out.println("3) Odunc / Iade Islemleri");
        System.out.println("4) Kutuphane Istatistikleri");
        System.out.println("0) Cikis");
        System.out.print("Seciminiz: ");
    }

    private void bookMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- KITAP ISLEMLERI ---");
            System.out.println("1) Genel Kitap Ekle");
            System.out.println("2) Ders Kitabi Ekle");
            System.out.println("3) Dergi Ekle");
            System.out.println("4) Magazin Ekle");
            System.out.println("5) Kitap Ara (ID / Isim / Yazar)");
            System.out.println("6) Kitap Bilgisi Guncelle");
            System.out.println("7) Kitap Sil");
            System.out.println("8) Tum Kitaplari Listele");
            System.out.println("9) Kategoriye Gore Listele");
            System.out.println("10) Yazara Gore Listele");
            System.out.println("0) Ana Menuye Don");
            System.out.print("Secim: ");

            switch (sc.nextLine().trim()) {
                case "1"  -> bookService.addGeneralBook(sc);
                case "2"  -> bookService.addStudyBook(sc);
                case "3"  -> bookService.addJournal(sc);
                case "4"  -> bookService.addMagazine(sc);
                case "5"  -> bookService.searchBook(sc);
                case "6"  -> bookService.updateBook(sc);
                case "7"  -> bookService.deleteBook(sc);
                case "8"  -> bookService.listAllBooks();
                case "9"  -> bookService.listByCategory(sc);
                case "10" -> bookService.listByAuthor(sc);
                case "0"  -> back = true;
                default   -> System.out.println("UYARI: Geçersiz seçim.");
            }
        }
    }

    private void memberMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- UYE ISLEMLERI ---");
            System.out.println("1) Ogrenci Uye Kaydi");
            System.out.println("2) Akademisyen Uye Kaydi");
            System.out.println("3) Tum Uyeleri Listele");
            System.out.println("4) Uye Detayi Goster");
            System.out.println("5) Uye Bakiye Yukle");
            System.out.println("0) Ana Menuye Don");
            System.out.print("Secim: ");

            switch (sc.nextLine().trim()) {
                case "1"  -> memberService.registerStudent(sc);
                case "2"  -> memberService.registerFaculty(sc);
                case "3"  -> memberService.listAllMembers();
                case "4"  -> memberService.showMemberDetail(sc);
                case "5"  -> memberService.addBalance(sc);
                case "0"  -> back = true;
                default   -> System.out.println("UYARI: Geçersiz seçim.");
            }
        }
    }

    private void lendingMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ODUNC / IADE ISLEMLERI ---");
            System.out.println("1) Kitap Odunc Ver");
            System.out.println("2) Kitap Iade Al");
            System.out.println("3) Aktif Odunc Listesi");
            System.out.println("4) Uye Odunc Gecmisi");
            System.out.println("5) Uye Fatura Gecmisi");
            System.out.println("0) Ana Menuye Don");
            System.out.print("Secim: ");

            switch (sc.nextLine().trim()) {
                case "1"  -> libraryService.lendBook(sc);
                case "2"  -> libraryService.receiveReturn(sc);
                case "3"  -> libraryService.showActiveLoans();
                case "4"  -> libraryService.showMemberHistory(sc);
                case "5"  -> libraryService.showMemberInvoices(sc);
                case "0"  -> back = true;
                default   -> System.out.println("UYARI: Geçersiz seçim.");
            }
        }
    }

}
