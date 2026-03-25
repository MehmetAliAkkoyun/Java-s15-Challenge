package model;

import model.book.Book;
import model.member.MemberRecord;
import model.person.Author;
import model.person.Librarian;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private String name;
    private String address;

    private final Map<Integer, Book>         bookCatalog;    
    private final Map<String, MemberRecord>  memberRegistry; 

    private final List<Author>               authors;
    private final List<Librarian>            librarians;
    private final List<BorrowRecord>         borrowHistory;
    private final List<Invoice>              invoices;

    private final Set<String>               isbnRegistry;

    public Library(String name, String address) {
        this.name           = name;
        this.address        = address;
        this.bookCatalog    = new HashMap<>();
        this.memberRegistry = new HashMap<>();
        this.authors        = new ArrayList<>();
        this.librarians     = new ArrayList<>();
        this.borrowHistory  = new ArrayList<>();
        this.invoices       = new ArrayList<>();
        this.isbnRegistry   = new HashSet<>();
    }

    public boolean addBook(Book book) {
        if (isbnRegistry.contains(book.getIsbn())) {
            System.out.println("UYARI: Bu ISBN zaten kayıtlı: " + book.getIsbn());
            return false;
        }
        bookCatalog.put(book.getBookId(), book);
        isbnRegistry.add(book.getIsbn());
        return true;
    }

    public boolean removeBook(int bookId) {
        Book removed = bookCatalog.remove(bookId);
        if (removed != null) {
            isbnRegistry.remove(removed.getIsbn());
            return true;
        }
        return false;
    }

    public Book getBookById(int id) {
        return bookCatalog.get(id);
    }

    public List<Book> searchByName(String name) {
        String kw = name.toLowerCase();
        return bookCatalog.values().stream()
                .filter(b -> b.getName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        String kw = author.toLowerCase();
        return bookCatalog.values().stream()
                .filter(b -> b.getAuthorName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByCategory(String category) {
        String kw = category.toLowerCase();
        return bookCatalog.values().stream()
                .filter(b -> b.getCategory().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public Collection<Book> getAllBooks() {
        return bookCatalog.values();
    }

    public void addMember(MemberRecord member) {
        memberRegistry.put(member.getMemberId(), member);
    }

    public MemberRecord getMemberById(String id) {
        return memberRegistry.get(id);
    }

    public Collection<MemberRecord> getAllMembers() {
        return memberRegistry.values();
    }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    public BorrowRecord getActiveBorrowRecord(int bookId) {
        return borrowHistory.stream()
                .filter(r -> r.getBook().getBookId() == bookId && !r.isReturned())
                .findFirst()
                .orElse(null);
    }

    public List<BorrowRecord> getMemberBorrowHistory(String memberId) {
        return borrowHistory.stream()
                .filter(r -> r.getMember().getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    public List<BorrowRecord> getAllActiveLoans() {
        return borrowHistory.stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getMemberInvoices(String memberId) {
        return invoices.stream()
                .filter(i -> i.getMember().getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    public void addAuthor(Author author)         { authors.add(author); }
    public void addLibrarian(Librarian lib)      { librarians.add(lib); }
    public List<Author>     getAuthors()         { return authors; }
    public List<Librarian>  getLibrarians()      { return librarians; }

    public int  getTotalBooks()   { return bookCatalog.size(); }
    public int  getTotalMembers() { return memberRegistry.size(); }

    public void showStats() {
        System.out.println("\n--- KUTUPHANE BILGILERI ---");
        System.out.println("Kutuphane : " + name);
        System.out.println("Adres     : " + address);
        System.out.println("Toplam Kitap  : " + getTotalBooks());
        System.out.println("Toplam Uye    : " + getTotalMembers());
        System.out.println("Aktif Odunc   : " + getAllActiveLoans().size());
        System.out.println("Yazar Sayisi  : " + authors.size());
        System.out.println();
    }

    public String getName()    { return name; }
    public String getAddress() { return address; }
}
