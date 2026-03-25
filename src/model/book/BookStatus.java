package model.book;

public enum BookStatus {
    AVAILABLE("Mevcut"),
    BORROWED("Ödünçte"),
    RESERVED("Rezerve"),
    LOST("Kayıp"),
    MAINTENANCE("Bakımda");

    private final String label;

    BookStatus(String label) { this.label = label; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
