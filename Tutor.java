// CLASS: Tutor.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
//this class extends parent class means it is a child of parent class
// it has data of a tutor, userId, a list of topic and a list of topics
//-----------------------------------------

public class Tutor extends Parent{
    private int hours;
    private LinkList topicList;

    public Tutor(String userId, int hours)
    {
        super(userId);
        this.hours = hours;
        topicList = new LinkList();
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LinkList getTopicList() {
        return topicList;
    }

}
