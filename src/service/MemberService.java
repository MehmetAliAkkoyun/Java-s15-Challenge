package service;

import model.Library;
import model.member.Faculty;
import model.member.MemberRecord;
import model.member.Student;

import java.util.Scanner;

public class MemberService {
    private final Library library;

    public MemberService(Library library) {
        this.library = library;
    }

    public Student registerStudent(Scanner sc) {
        System.out.println("\n--- Yeni Öğrenci Üye Kaydı ");
        String name       = prompt(sc, "Ad Soyad       : ");
        String address    = prompt(sc, "Adres          : ");
        String phone      = prompt(sc, "Telefon        : ");
        String studentId  = prompt(sc, "Öğrenci No     : ");
        String department = prompt(sc, "Bölüm          : ");
        int    grade      = promptInt(sc, "Sınıf (1-4)   : ");

        Student student = new Student(name, address, phone, studentId, department, grade);
        library.addMember(student);
        System.out.println("OK: Öğrenci kaydedildi. Üye ID: " + student.getMemberId());
        return student;
    }

    public Faculty registerFaculty(Scanner sc) {
        System.out.println("\n--- Yeni Akademisyen Üye Kaydı ");
        String name    = prompt(sc, "Ad Soyad       : ");
        String address = prompt(sc, "Adres          : ");
        String phone   = prompt(sc, "Telefon        : ");
        String staffId = prompt(sc, "Personel No    : ");
        String title   = prompt(sc, "Unvan          : ");
        String dept    = prompt(sc, "Fakülte/Bölüm  : ");

        Faculty faculty = new Faculty(name, address, phone, staffId, title, dept);
        library.addMember(faculty);
        System.out.println("OK: Akademisyen kaydedildi. Üye ID: " + faculty.getMemberId());
        return faculty;
    }

    public void listAllMembers() {
        var members = library.getAllMembers();
        if (members.isEmpty()) { System.out.println("Kayıtlı üye yok."); return; }
        System.out.println("\n--- TUM UYELER (" + members.size() + " kisi) ---");
        members.forEach(m -> System.out.println(m.getSummary()));
    }

    public void showMemberDetail(Scanner sc) {
        String id = prompt(sc, "Üye ID: ");
        MemberRecord m = library.getMemberById(id);
        if (m == null) { System.out.println("UYARI: Üye bulunamadı."); return; }
        m.display();
    }

    public void addBalance(Scanner sc) {
        String id = prompt(sc, "Üye ID      : ");
        MemberRecord m = library.getMemberById(id);
        if (m == null) { System.out.println("UYARI: Üye bulunamadı."); return; }
        double amount = promptDouble(sc, "Yüklenecek miktar (TL): ");
        m.addBalance(amount);
        System.out.printf("OK: Bakiye yüklendi. Yeni bakiye: %.2f TL%n", m.getBalance());
    }

    private String prompt(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private int promptInt(Scanner sc, String msg) {
        System.out.print(msg);
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return 0; }
    }

    private double promptDouble(Scanner sc, String msg) {
        System.out.print(msg);
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }
}
