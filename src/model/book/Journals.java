package model.book;

public class Journals extends Book {
    private String journalField;   
    private int    issueNumber;
    private String issn;

    public Journals(String authorName, String name, double price,
                    String edition, String isbn, String publisher,
                    int pageCount, String journalField, int issueNumber, String issn) {
        super(authorName, name, price, edition, isbn, publisher, pageCount);
        this.journalField = journalField;
        this.issueNumber  = issueNumber;
        this.issn         = issn;
    }

    @Override
    public String getCategory() { return "Dergi / Jurnal"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Alan      : " + journalField);
        System.out.println("Sayi No   : " + issueNumber);
        System.out.println("ISSN      : " + issn);
    }

    public String getJournalField() { return journalField; }
    public int    getIssueNumber()  { return issueNumber; }
    public String getIssn()         { return issn; }
}
