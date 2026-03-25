package model;

import model.book.Book;
import model.member.MemberRecord;
import java.time.LocalDate;

public class Invoice {
    private static int invoiceCounter = 5000;

    private int          invoiceId;
    private MemberRecord member;
    private Book         book;
    private double       amount;
    private LocalDate    issueDate;
    private String       invoiceType;   
    private boolean      isPaid;

    public Invoice(MemberRecord member, Book book, double amount, LocalDate issueDate) {
        this.invoiceId   = ++invoiceCounter;
        this.member      = member;
        this.book        = book;
        this.amount      = amount;
        this.issueDate   = issueDate;
        this.invoiceType = "ÖDÜNÇ";
        this.isPaid      = false;
    }

    public void markAsPaid() { this.isPaid = true; }

    public void printInvoice() {
        System.out.println();
        System.out.println("--- FATURA #" + invoiceId + " ---");
        System.out.println("Tarih      : " + issueDate);
        System.out.println("Uye        : " + member.getName());
        System.out.println("Uye ID     : " + member.getMemberId());
        System.out.println("Kitap      : " + book.getName());
        System.out.println("Islem Turu : " + invoiceType);
        System.out.println("Tutar      : " + String.format("%.2f TL", amount));
        System.out.println("Durum      : " + (isPaid ? "ODENDI" : "ODEME BEKLENIYOR"));
        System.out.println();
    }

    public int          getInvoiceId()      { return invoiceId; }
    public MemberRecord getMember()         { return member; }
    public Book         getBook()           { return book; }
    public double       getAmount()         { return amount; }
    public LocalDate    getIssueDate()      { return issueDate; }
    public boolean      isPaid()            { return isPaid; }
    public String       getInvoiceType()    { return invoiceType; }
    public void         setInvoiceType(String t) { this.invoiceType = t; }
}
