package model;

import model.book.Book;
import model.member.MemberRecord;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowRecord {
    private static int recordCounter = 0;

    private int          recordId;
    private Book         book;
    private MemberRecord member;
    private LocalDate    borrowDate;
    private LocalDate    dueDate;
    private LocalDate    returnDate;
    private boolean      isReturned;
    private double       fee;

    public static final int DEFAULT_BORROW_DAYS = 14;

    public BorrowRecord(Book book, MemberRecord member, double fee) {
        this.recordId   = ++recordCounter;
        this.book       = book;
        this.member     = member;
        this.fee        = fee;
        this.borrowDate = LocalDate.now();
        this.dueDate    = borrowDate.plusDays(DEFAULT_BORROW_DAYS);
        this.isReturned = false;
    }

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
        this.isReturned = true;
    }

    public long getOverdueDays() {
        LocalDate checkDate = isReturned ? returnDate : LocalDate.now();
        long diff = ChronoUnit.DAYS.between(dueDate, checkDate);
        return diff > 0 ? diff : 0;
    }

    public boolean isOverdue() {
        return getOverdueDays() > 0;
    }

    @Override
    public String toString() {
        return String.format("[Kayıt #%d] %s → %s | Tarih: %s | Son: %s | %s",
                recordId,
                book.getName(),
                member.getName(),
                borrowDate,
                dueDate,
                isReturned ? "OK: İade Edildi (" + returnDate + ")" : (isOverdue() ? "UYARI: GECİKMİŞ" : "Aktif"));
    }

    public int          getRecordId()   { return recordId; }
    public Book         getBook()       { return book; }
    public MemberRecord getMember()     { return member; }
    public LocalDate    getBorrowDate() { return borrowDate; }
    public LocalDate    getDueDate()    { return dueDate; }
    public LocalDate    getReturnDate() { return returnDate; }
    public boolean      isReturned()    { return isReturned; }
    public double       getFee()        { return fee; }
}
