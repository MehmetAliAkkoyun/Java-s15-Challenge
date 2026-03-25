package model.book;

public class StudyBooks extends Book {
    private String subject;      
    private String gradeLevel;   
    private boolean hasAnswerKey;

    public StudyBooks(String authorName, String name, double price,
                      String edition, String isbn, String publisher,
                      int pageCount, String subject, String gradeLevel, boolean hasAnswerKey) {
        super(authorName, name, price, edition, isbn, publisher, pageCount);
        this.subject      = subject;
        this.gradeLevel   = gradeLevel;
        this.hasAnswerKey = hasAnswerKey;
    }

    @Override
    public String getCategory() { return "Ders/Çalışma Kitabı"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Konu      : " + subject);
        System.out.println("Seviye    : " + gradeLevel);
        System.out.println("Cevap An. : " + (hasAnswerKey ? "Var" : "Yok"));
    }

    public String  getSubject()      { return subject; }
    public String  getGradeLevel()   { return gradeLevel; }
    public boolean isHasAnswerKey()  { return hasAnswerKey; }
}
