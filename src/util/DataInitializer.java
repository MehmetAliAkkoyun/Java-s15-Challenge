package util;

import model.Library;
import model.book.*;
import model.member.Faculty;
import model.member.MemberRecord;
import model.member.Student;
import model.person.Author;
import model.person.Librarian;

public class DataInitializer {

    public static void populate(Library library) {

        Librarian lib = new Librarian(
                "Ayşe Kaya", "akaya@kutuphane.edu.tr", "05321234567",
                "LIB001", "admin123", "Genel Koleksiyon");
        library.addLibrarian(lib);

        Author a1 = new Author("Orhan Pamuk",   "info@orhanpamuk.net", "05001111111", "Türk");
        Author a2 = new Author("Sabahattin Ali", "info@sabahattinaali.net", "05002222222", "Türk");
        Author a3 = new Author("Ursula K. Le Guin", "ule@scifi.com", "05003333333", "Amerikan");
        library.addAuthor(a1);
        library.addAuthor(a2);
        library.addAuthor(a3);

        Book b1 = new Book("Orhan Pamuk", "Kar",
                89.90, "12. Baskı", "978-975-08-0286-3", "İletişim Yayınları", 436);
        Book b2 = new Book("Orhan Pamuk", "Masumiyet Müzesi",
                110.00, "5. Baskı", "978-975-08-0484-3", "İletişim Yayınları", 692);
        Book b3 = new Book("Sabahattin Ali", "Kürk Mantolu Madonna",
                65.00, "38. Baskı", "978-975-363-084-0", "Yapı Kredi Yayınları", 208);
        Book b4 = new Book("Sabahattin Ali", "İçimizdeki Şeytan",
                70.00, "14. Baskı", "978-975-363-085-7", "Yapı Kredi Yayınları", 280);
        Book b5 = new Book("Ursula K. Le Guin", "Karanlığın Sol Eli",
                95.00, "3. Baskı", "978-605-09-0284-1", "Metis Yayınları", 352);

        StudyBooks sb1 = new StudyBooks(
                "Ahmet Kaya", "Java ile Nesne Yönelimli Programlama",
                145.00, "2. Baskı", "978-605-123-001-1", "Seçkin Yayıncılık",
                520, "Programlama", "Üniversite", true);
        StudyBooks sb2 = new StudyBooks(
                "Mehmet Demir", "Veri Yapıları ve Algoritmalar",
                130.00, "1. Baskı", "978-605-123-002-2", "Seçkin Yayıncılık",
                480, "Bilgisayar Bilimleri", "Üniversite", false);

        Journals j1 = new Journals(
                "Çeşitli Yazarlar", "Nature",
                55.00, "Vol.615", "978-001-000-001-1", "Nature Publishing",
                200, "Çok Disiplinli Bilim", 615, "0028-0836");
        Journals j2 = new Journals(
                "Çeşitli Yazarlar", "IEEE Software",
                75.00, "Vol.40", "978-001-000-002-2", "IEEE",
                180, "Yazılım Mühendisliği", 40, "0740-7459");

        Magazines m1 = new Magazines(
                "Editör Kurulu", "National Geographic Türkiye",
                40.00, "Mart 2025", "978-002-000-001-1", "National Geographic",
                120, "Aylık", 32025, "Coğrafya & Doğa");

        for (Book b : new Book[]{b1, b2, b3, b4, b5, sb1, sb2, j1, j2, m1}) {
            library.addBook(b);
        }

        a1.addBook(b1); a1.addBook(b2);
        a2.addBook(b3); a2.addBook(b4);
        a3.addBook(b5);

        Student s1 = new Student("Ali Veli", "Ankara, Çankaya", "05311112233",
                "20210001", "Bilgisayar Mühendisliği", 3);
        Student s2 = new Student("Fatma Çelik", "İstanbul, Kadıköy", "05322223344",
                "20210002", "Türk Dili ve Edebiyatı", 2);
        Faculty f1 = new Faculty("Prof. Dr. Bülent Yılmaz", "Ankara, Üniversite Mah.", "05333334455",
                "FAC001", "Prof. Dr.", "Bilgisayar Mühendisliği");

        s1.addBalance(500.0);
        s2.addBalance(300.0);
        f1.addBalance(1000.0);

        library.addMember(s1);
        library.addMember(s2);
        library.addMember(f1);

        System.out.println("OK: Demo verileri yüklendi.");
        System.out.printf("   → %d kitap, %d üye, %d yazar%n",
                library.getTotalBooks(), library.getTotalMembers(), library.getAuthors().size());
    }
}
