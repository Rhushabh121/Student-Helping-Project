// CLASS: LinkList.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
//  this is a list class, list made of nodes(blocks of data pointing other data)
//
//-----------------------------------------

public class LinkList {
    private Node head;
    public LinkList() {
        head = null;
    }


    public void add ( Parent data ) {
        head = new Node(data, head);
    }

    public boolean search(String userId)
    {
        Node curr = head;
        boolean result = false;

        while (curr!= null && !curr.getData().getUserId().equals(userId)) {

            curr = curr.getNext();
        }
        if(curr !=null && curr.getData().getUserId().equals(userId))
            result = true;

        return result;
    }

    //------------------------------------------------------
    // myMethod: request
    //
    // PURPOSE:    it full fill the request of student wants tutoring
    //              it calls few other methods with do some major work
    // PARAMETERS:
    //     LinkList tutorList : this is a list of tutor, to give hours to students of topic requested
    //     String sId: student id of requested student
    //     String topic: requested topic
    //     int Hours: this much hours student need in the topic
    // Returns: it returns true/false it is a boolean method
    //------------------------------------------------------

    public boolean request(LinkList tutorList, String sId, String topic, int hours)
    {
        boolean result = false;
        Node curr;
        curr = findNode(sId);
        LinkList newList = requestedTutorList(tutorList,topic);
        Node countHours = newList.head;
        Node node;
        Tutor tutor;
        int checkHours = 0;
        int i = 0;
        int j ;
        int check = hours;
        int k =0;
        Node hoursChange;
        Node price;
        Node currTutor;
        Node addInTopic;
        while (countHours != null)
        {
            k+= ((Tutor) countHours.getData()).getHours();
            countHours = countHours.getNext();
        }

        if(hours<= k)
        {
            while (hours >checkHours)
            {
                if(newList.head != null) {
                    node = bestMatch(newList);
                    hoursChange = tutorList.findNode(node.getData().getUserId());

                    if (hours >= (checkHours += ((Tutor) node.getData()).getHours()))
                    {
                        i = checkHours;
                        check = check - ((Tutor) node.getData()).getHours();

                        tutor = new Tutor(node.getData().getUserId(), ((Tutor) node.getData()).getHours());
                        price = ((Tutor) node.getData()).getTopicList().findNode(topic);
                        tutor.getTopicList().add(new Topic(topic,tutor.getUserId(),((Topic)price.getData()).getPrice()));

                        ((Student) curr.getData()).getTutorList().add(tutor);

                        currTutor = tutorList.findNode(node.getData().getUserId());
                        addInTopic = ((Tutor)currTutor.getData()).getTopicList().findNode(topic);
                        ((Topic)addInTopic.getData()).getStudentList().add(new Student(sId,((Tutor) node.getData()).getHours()));

                        ((Tutor)hoursChange.getData()).setHours(0);

                    }
                    else if(hours<=k)
                    {
                        j = hours - i;
                        check = check - j;

                        tutor = new Tutor(node.getData().getUserId(), j);
                        price = ((Tutor) node.getData()).getTopicList().findNode(topic);
                        tutor.getTopicList().add(new Topic(topic,tutor.getUserId(),((Topic)price.getData()).getPrice()));

                        ((Student) curr.getData()).getTutorList().add(tutor);
                        ((Tutor)hoursChange.getData()).setHours(((Tutor)hoursChange.getData()).getHours()-j);

                        currTutor = tutorList.findNode(node.getData().getUserId());
                        addInTopic = ((Tutor)currTutor.getData()).getTopicList().findNode(topic);
                        ((Topic)addInTopic.getData()).getStudentList().add(new Student(sId,j));
                    }
                }
            }
        }
        if(check == 0)
            result = true;



        return result;
    }

    public Node findNode(String item) {
        Node curr = head;

        while (curr!= null && !curr.getData().getUserId().equals(item)) {
            curr = curr.getNext();
        }
        return curr;
    }

    public LinkList requestedTutorList(LinkList tutorList, String topic)
    {
        LinkList newList = new LinkList();
        Node curr = tutorList.head;
        Node topicPtr;

        while(curr!= null )
        {
            topicPtr = ((Tutor)curr.getData()).getTopicList().head;
            while (topicPtr != null) {
                if (topicPtr.getData().getUserId().equals(topic) && ((Tutor) curr.getData()).getHours() > 0) {
                    newList.add(curr.getData());


                }
                topicPtr = topicPtr.getNext();
            }
            curr = curr.getNext();
        }
        return newList;
    }

    public Node bestMatch(LinkList newList)
    {
        Node curr = newList.head;
        Node check;
        int currValue;
        int checkValue;
        Node returnNode;
        if(curr.getNext() != null) {
            check = curr.getNext();

            while (check != null) {

                    Node topicNode = ((Tutor) curr.getData()).getTopicList().head;
                    currValue = ((Topic) topicNode.getData()).getPrice();
                    Node nextTopic = ((Tutor) check.getData()).getTopicList().head;
                    checkValue = ((Topic) nextTopic.getData()).getPrice();

                    if (currValue > checkValue) {
                        curr = check;
                        check = check.getNext();

                    }
                    else if (currValue < checkValue) {
                        check = check.getNext();
                    }
                    else if (currValue == checkValue) {
                        if (((Tutor) curr.getData()).getHours() > ((Tutor)check.getData()).getHours()) {
                                check = check.getNext();
                        }
                        else if (((Tutor) curr.getData()).getHours() < ((Tutor) check.getData()).getHours()) {
                            curr = check;
                            check = check.getNext();

                        }
                        else if (((Tutor) curr.getData()).getHours() == ((Tutor) check.getData()).getHours()) {
                            if((curr.getData()).getUserId().compareTo((check.getData()).getUserId()) < 0)
                            {
                                check = check.getNext();
                            }
                            else if((curr.getData()).getUserId().compareTo((check.getData()).getUserId()) > 0)
                            {
                                curr = check;
                                check = check.getNext();
                            }

                        }
                    }

            }

            returnNode = delete(curr, newList);
        }
        else
        {
            returnNode = delete(curr, newList);
        }
        return returnNode;
    }

    public Node delete(Node node,LinkList list)
    {
        Node curr;
        Node head = list.head;
        Node returnNode = null;

        if(head != node)
        {
            curr = head;
            while (curr.getNext() != node)
            {
                curr = curr.getNext();
            }
            if(curr.getNext()== node)
            {
                if(curr.getNext().getNext() == null)
                {
                    returnNode = node;
                    curr.setNext(null);
                }
                else
                {
                    returnNode= curr.getNext();
                    curr.setNext(curr.getNext().getNext());
                }
            }
        }
        else
        {
            if(node.getNext() != null)
            {
                returnNode = node;
                list.head = node.getNext();
            }
            else
            {
                returnNode = node;
                head.setNext(null);
            }
        }
        return returnNode;
    }

    public void studentReport(String name) {
        Node newNode = findNode(name);
        if (newNode != null) {
            System.out.println("REPORT FOR STUDENT "+newNode.getData().getUserId());
            Node current = ((Student) newNode.getData()).getTutorList().head;
            int totalCost;
            int totalHours = 0;
            int grandCost = 0;
            String tutorName;
            String topicName;
            int hours;
            int cost;
            Node topicNode;
            Node topic;
            while (current != null) {
                tutorName = current.getData().getUserId();
                topic = ((Tutor) current.getData()).getTopicList().head;
                topicName = topic.getData().getUserId();
                hours = ((Tutor) current.getData()).getHours();
                topicNode = ((Tutor) current.getData()).getTopicList().head;
                cost = ((Topic) topicNode.getData()).getPrice();
                totalCost = hours * cost;
                grandCost += totalCost;
                totalHours += hours;
                System.out.println("Appointment: Tutor: " + tutorName + ", topic: " + topicName + ", hours: " + hours + ", total cost:" + totalCost);
                current = current.getNext();
            }
            System.out.println("Total number of hours of tutoring: " + totalHours);
            System.out.println("Total cost of tutoring: " + grandCost);
        }
    }

    public void tutorReport(String name)
    {
        Node newNode = findNode(name);
        if(newNode != null)
        {
            System.out.println("Report for Tutor "+ newNode.getData().getUserId());

            String studentName;
            String topicName;
            int hours;
            int revenue;
            int totalHours = 0;
            int totalRevenue =0;
            int price;
            Node topicNode = ((Tutor)newNode.getData()).getTopicList().head;
            Node studentNode;
            if(topicNode != null)
            {
                while (topicNode != null)
                {
                    price = ((Topic)topicNode.getData()).getPrice();
                    studentNode = ((Topic)topicNode.getData()).getStudentList().head;
                    topicName = topicNode.getData().getUserId();
                    if(studentNode != null)
                    {
                        while (studentNode != null)
                        {
                            studentName = studentNode.getData().getUserId();
                            hours = ((Student)studentNode.getData()).getHours();
                            totalHours+=hours;
                            revenue = price*hours;
                            totalRevenue+=revenue;
                            studentNode = studentNode.getNext();
                            System.out.println("Appointment: Student:"+studentName+", topic: "+topicName+", hours: "+hours+", total revenue: "+revenue);
                        }
                    }
                    topicNode = topicNode.getNext();
                }
                System.out.println("Total number of hours of tutoring: "+ totalHours);
                System.out.println("Total revenue from tutoring: "+ totalRevenue);
            }
        }
    }

}
