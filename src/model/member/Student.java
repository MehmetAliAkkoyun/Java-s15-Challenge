package model.member;

public class Student extends MemberRecord {
    private static final int STUDENT_BOOK_LIMIT = 3;

    private String studentId;
    private String department;
    private int    grade;

    public Student(String name, String address, String phoneNo,
                   String studentId, String department, int grade) {
        super(name, address, phoneNo, STUDENT_BOOK_LIMIT);
        this.studentId  = studentId;
        this.department = department;
        this.grade      = grade;
    }

    @Override
    public String getMemberType() { return "Öğrenci"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Ogrenci No  : " + studentId);
        System.out.println("Bolum       : " + department);
        System.out.println("Sinif       : " + grade + ". sinif");
    }

    public String getStudentId()        { return studentId; }
    public String getDepartment()       { return department; }
    public int    getGrade()            { return grade; }
    public void   setGrade(int g)       { this.grade = g; }
}
