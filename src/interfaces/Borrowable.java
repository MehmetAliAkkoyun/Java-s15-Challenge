package interfaces;

import model.book.Book;
import model.member.MemberRecord;

public interface Borrowable {
    boolean borrowBook(Book book, MemberRecord member);
    boolean returnBook(Book book, MemberRecord member);
    double calculateFee(Book book, int days);
}
