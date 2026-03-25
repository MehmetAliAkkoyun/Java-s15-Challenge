package model.person;

import interfaces.Displayable;

public abstract class Person implements Displayable {

    private String name;
    private String email;
    private String phone;

    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public abstract String whoYouAre();

    public String getName()              { return name; }
    public void   setName(String name)   { this.name = name; }
    public String getEmail()             { return email; }
    public void   setEmail(String email) { this.email = email; }
    public String getPhone()             { return phone; }
    public void   setPhone(String phone) { this.phone = phone; }

    @Override
    public String getSummary() {
        return String.format("[%s] %s | %s | %s", whoYouAre(), name, email, phone);
    }

    @Override
    public void display() {
        System.out.println("--- KISI BILGISI ---");
        System.out.println("Tip     : " + whoYouAre());
        System.out.println("Ad      : " + name);
        System.out.println("E-posta : " + email);
        System.out.println("Telefon : " + phone);
    }

    @Override
    public String toString() { return getSummary(); }
}
