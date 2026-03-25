package service;

import model.*;
import model.book.Book;
import model.member.MemberRecord;
import model.person.Librarian;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryService {
    private final Library   library;
    private final Librarian activeLibrarian;

    public LibraryService(Library library, Librarian librarian) {
        this.library         = library;
        this.activeLibrarian = librarian;
    }

    public void lendBook(Scanner sc) {
        System.out.println("\n--- Kitap Ödünç Ver");
        int    bookId   = promptInt(sc, "Kitap ID    : ");
        String memberId = prompt(sc,    "Üye ID      : ");

        Book         book   = library.getBookById(bookId);
        MemberRecord member = library.getMemberById(memberId);

        if (book == null) {
            System.out.println("UYARI: Kitap bulunamadı. ID: " + bookId);
            return;
        }
        if (member == null) {
            System.out.println("UYARI: Üye bulunamadı. ID: " + memberId);
            return;
        }

        boolean success = activeLibrarian.borrowBook(book, member);
        if (!success) return;

        double fee = activeLibrarian.calculateFee(book, BorrowRecord.DEFAULT_BORROW_DAYS);
        BorrowRecord record = new BorrowRecord(book, member, fee);
        library.addBorrowRecord(record);

        Invoice invoice = activeLibrarian.createBill(member, book, BorrowRecord.DEFAULT_BORROW_DAYS);
        member.deductBalance(fee);
        invoice.markAsPaid();
        library.addInvoice(invoice);
        invoice.printInvoice();

        System.out.println("  Son iade tarihi: " + record.getDueDate());
    }

    public void receiveReturn(Scanner sc) {
        System.out.println("\n--- Kitap İade Al");
        int bookId = promptInt(sc, "Kitap ID: ");

        Book book = library.getBookById(bookId);
        if (book == null) {
            System.out.println("UYARI: Kitap bulunamadı.");
            return;
        }

        BorrowRecord record = library.getActiveBorrowRecord(bookId);
        if (record == null) {
            System.out.println("UYARI: Bu kitaba ait aktif ödünç kaydı bulunamadı.");
            return;
        }

        MemberRecord member = record.getMember();
        activeLibrarian.returnBook(book, member);
        record.markAsReturned();

        double fine = activeLibrarian.calculateFine(record);
        if (fine > 0) {
            System.out.printf("UYARI: Gecikme cezası: %.2f TL (%d gün geç)%n",
                    fine, record.getOverdueDays());
        }

        Invoice refundInvoice = new Invoice(member, book, record.getFee(), LocalDate.now());
        refundInvoice.setInvoiceType("İADE");
        member.addBalance(record.getFee() - fine);
        refundInvoice.markAsPaid();
        library.addInvoice(refundInvoice);
        refundInvoice.printInvoice();
    }

    public void showActiveLoans() {
        List<BorrowRecord> loans = library.getAllActiveLoans();
        if (loans.isEmpty()) { System.out.println("Aktif ödünç kaydı yok."); return; }
        System.out.println("\n--- AKTİF ÖDÜNÇLER (" + loans.size() + ")---");
        loans.forEach(r -> System.out.println(r));
    }

    public void showMemberHistory(Scanner sc) {
        String memberId = prompt(sc, "Üye ID: ");
        List<BorrowRecord> history = library.getMemberBorrowHistory(memberId);
        if (history.isEmpty()) { System.out.println("Bu üyeye ait kayıt yok."); return; }
        System.out.println("\n--- ÜYE ÖDÜNÇ GEÇMİŞİ (" + history.size() + ")---");
        history.forEach(r -> System.out.println(r));
    }

    public void showMemberInvoices(Scanner sc) {
        String memberId = prompt(sc, "Üye ID: ");
        library.getMemberInvoices(memberId).forEach(Invoice::printInvoice);
    }

    private String prompt(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private int promptInt(Scanner sc, String msg) {
        System.out.print(msg);
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
