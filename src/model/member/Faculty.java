package model.member;

public class Faculty extends MemberRecord {
    private static final int FACULTY_BOOK_LIMIT = 5;

    private String staffId;
    private String title;      
    private String facultyDept;

    public Faculty(String name, String address, String phoneNo,
                   String staffId, String title, String facultyDept) {
        super(name, address, phoneNo, FACULTY_BOOK_LIMIT);
        this.staffId     = staffId;
        this.title       = title;
        this.facultyDept = facultyDept;
    }

    @Override
    public String getMemberType() { return "Akademisyen"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Personel No : " + staffId);
        System.out.println("Unvan       : " + title);
        System.out.println("Bolum       : " + facultyDept);
    }

    public String getStaffId()          { return staffId; }
    public String getTitle()            { return title; }
    public void   setTitle(String t)    { this.title = t; }
    public String getFacultyDept()      { return facultyDept; }
}
