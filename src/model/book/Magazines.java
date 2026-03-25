package model.book;

public class Magazines extends Book {
    private String  frequency;    
    private int     monthYear;
    private String  genre;        

    public Magazines(String authorName, String name, double price,
                     String edition, String isbn, String publisher,
                     int pageCount, String frequency, int monthYear, String genre) {
        super(authorName, name, price, edition, isbn, publisher, pageCount);
        this.frequency = frequency;
        this.monthYear = monthYear;
        this.genre     = genre;
    }

    @Override
    public String getCategory() { return "Magazin"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Periyot   : " + frequency);
        System.out.println("Ay/Yil    : " + monthYear);
        System.out.println("Tur       : " + genre);
    }

    public String getFrequency() { return frequency; }
    public int    getMonthYear() { return monthYear; }
    public String getGenre()     { return genre; }
}
