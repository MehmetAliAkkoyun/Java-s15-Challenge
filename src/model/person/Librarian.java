package model.person;

import interfaces.Borrowable;
import model.book.Book;
import model.book.BookStatus;
import model.member.MemberRecord;
import model.BorrowRecord;
import model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Librarian extends Person implements Borrowable {
    private String staffId;
    private String password;
    private String department;

    public Librarian(String name, String email, String phone,
                     String staffId, String password, String department) {
        super(name, email, phone);
        this.staffId    = staffId;
        this.password   = password;
        this.department = department;
    }

    @Override
    public String whoYouAre() { return "Kütüphaneci"; }

    public Book searchBook(List<Book> allBooks, String keyword) {
        String kw = keyword.toLowerCase();
        return allBooks.stream()
                .filter(b -> String.valueOf(b.getBookId()).equals(keyword)
                          || b.getName().toLowerCase().contains(kw)
                          || b.getAuthorName().toLowerCase().contains(kw))
                .findFirst()
                .orElse(null);
    }

    public boolean verifyMember(MemberRecord member) {
        return member != null && member.getMemberId() != null && !member.getMemberId().isEmpty();
    }

    @Override
    public boolean borrowBook(Book book, MemberRecord member) {
        if (book == null || member == null) return false;
        if (book.getStatus() != BookStatus.AVAILABLE) {
            System.out.println("UYARI: Kitap şu anda mevcut değil: " + book.getName());
            return false;
        }
        if (!member.canBorrowMore()) {
            System.out.println("UYARI: Üye kitap limitine ulaştı: " + member.getName());
            return false;
        }
        book.setStatus(BookStatus.BORROWED);
        member.incrementBooksIssued();
        System.out.println("OK: Kitap başarıyla ödünç verildi.");
        return true;
    }

    @Override
    public boolean returnBook(Book book, MemberRecord member) {
        if (book == null || member == null) return false;
        book.setStatus(BookStatus.AVAILABLE);
        member.decrementBooksIssued();
        System.out.println("OK: Kitap başarıyla iade alındı.");
        return true;
    }

    @Override
    public double calculateFee(Book book, int days) {
        return book.getPrice() * 0.01 * days;   
    }

    public double calculateFine(BorrowRecord record) {
        long overdueDays = record.getOverdueDays();
        return overdueDays > 0 ? overdueDays * 2.0 : 0.0;  
    }

    public Invoice createBill(MemberRecord member, Book book, int borrowDays) {
        double fee = calculateFee(book, borrowDays);
        return new Invoice(member, book, fee, LocalDate.now());
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Personel ID : " + staffId);
        System.out.println("Departman   : " + department);
    }

    public String getStaffId()            { return staffId; }
    public String getDepartment()         { return department; }
    public void   setDepartment(String d) { this.department = d; }
}
