// CLASS: Parent.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
//this class is a child of parent class, and has list of students studing in
// that topic
//-----------------------------------------

public class Topic extends Parent
{
    private int price;
    private String tutorId;
    private LinkList studentList;

    public Topic(String topicName, String tutorId, int price)
    {
        super(topicName);
        this.price=price;
        this.tutorId = tutorId;
        studentList = new LinkList();
    }

    public LinkList getStudentList() {
        return studentList;
    }

    public int getPrice() {
        return price;
    }
}
