package model.member;

import interfaces.Displayable;
import java.time.LocalDate;

public abstract class MemberRecord implements Displayable {
    private static int idCounter = 100;

    private String    memberId;
    private String    type;
    private LocalDate dateOfMembership;
    private int       noBooksIssued;
    private int       maxBookLimit;
    private String    name;
    private String    address;
    private String    phoneNo;
    private double    balance;      

    public MemberRecord(String name, String address, String phoneNo, int maxBookLimit) {
        this.memberId         = "MBR" + (++idCounter);
        this.name             = name;
        this.address          = address;
        this.phoneNo          = phoneNo;
        this.maxBookLimit     = maxBookLimit;
        this.noBooksIssued    = 0;
        this.dateOfMembership = LocalDate.now();
        this.balance          = 0.0;
        this.type             = getMemberType();
    }

    public abstract String getMemberType();

    public boolean canBorrowMore() {
        return noBooksIssued < maxBookLimit;
    }

    public void incrementBooksIssued() {
        if (noBooksIssued < maxBookLimit) noBooksIssued++;
    }

    public void decrementBooksIssued() {
        if (noBooksIssued > 0) noBooksIssued--;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public boolean deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void display() {
        System.out.println("--- UYE BILGISI ---");
        System.out.println("Uye ID      : " + memberId);
        System.out.println("Uye Turu    : " + getMemberType());
        System.out.println("Ad Soyad    : " + name);
        System.out.println("Adres       : " + address);
        System.out.println("Telefon     : " + phoneNo);
        System.out.println("Uye Tarihi  : " + dateOfMembership);
        System.out.println("Kitap Sayisi: " + noBooksIssued + "/" + maxBookLimit);
        System.out.println("Bakiye      : " + String.format("%.2f TL", balance));
    }

    @Override
    public String getSummary() {
        return String.format("[%s] %-25s | %-10s | Kitap: %d/%d | Bakiye: %.2f TL",
                memberId, name, getMemberType(), noBooksIssued, maxBookLimit, balance);
    }

    @Override
    public String toString() { return getSummary(); }

    public String    getMemberId()             { return memberId; }
    public String    getType()                 { return type; }
    public LocalDate getDateOfMembership()     { return dateOfMembership; }
    public int       getNoBooksIssued()        { return noBooksIssued; }
    public int       getMaxBookLimit()         { return maxBookLimit; }
    public void      setMaxBookLimit(int l)    { this.maxBookLimit = l; }
    public String    getName()                 { return name; }
    public void      setName(String n)         { this.name = n; }
    public String    getAddress()              { return address; }
    public void      setAddress(String a)      { this.address = a; }
    public String    getPhoneNo()              { return phoneNo; }
    public void      setPhoneNo(String p)      { this.phoneNo = p; }
    public double    getBalance()              { return balance; }
}
