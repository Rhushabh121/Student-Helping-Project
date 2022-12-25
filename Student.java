// CLASS: Student.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
//this class is a child of parent class, has a data of student user id and
//list of tutor
//-----------------------------------------

public class Student extends Parent{

    private int hours;

    private LinkList tutorList;

    public Student(String userId, int hours)
    {
        super(userId);
        this.hours = hours;
        tutorList = new LinkList();
    }

    public int getHours() {
        return hours;
    }

    public LinkList getTutorList() {
        return tutorList;
    }

}
