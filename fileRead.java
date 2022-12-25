// CLASS: fileRead.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
// it reads the file and do what the commands told to do in the file
//
//-----------------------------------------

import java.io.*;
import java.util.Scanner;

public class fileRead {

    public fileRead() {
    }

    public void read() {
        try {
            String[] words;
            String line;
            System.out.println("Enter the FileName:");
            Scanner user = new Scanner(System.in);
            line = user.nextLine();
            BufferedReader read = new BufferedReader(new FileReader(line)); //reading the given file


            LinkList studentList = new LinkList();
            LinkList tutorList   = new LinkList();
            LinkList topicList   = new LinkList();

            line = read.readLine();
            while (line != null) {
                words = line.split("\\s+");

                if (words[0].equals("TUTOR")) {
                    if (!tutorList.search(words[1])) {
                        tutorList.add(new Tutor(words[1], Integer.parseInt(words[2])));
                        System.out.println("Tutor with userid "+words[1]+ " successfully created.");

                    }
                    else
                    {
                        System.out.println("Duplicate Tutor with userid "+ words[1]+".");
                    }
                }
                else if (words[0].equals("STUDENT")) {
                    if (!studentList.search(words[1])) {
                        studentList.add(new Student(words[1],0));
                        System.out.println("Student with userid "+words[1]+" successfully created.");
                    }
                    else
                    {
                        System.out.println("Duplicate Student with userid "+ words[1]+".");
                    }
                }
                else if (words[0].equals("TOPIC")) {
                    Node currentList = tutorList.findNode(words[2]);
                    if(tutorList.search(words[2])) {
                        ((Tutor) currentList.getData()).getTopicList().add(new Topic(words[1], words[2], Integer.parseInt(words[3])));
                        topicList.add(new Topic(words[1], words[2], Integer.parseInt(words[3])));
                        System.out.println("Topic "+words[1]+" added to Tutor "+ words[2]+ " with price "+words[3]);
                    }
                    else
                    {
                        System.out.println("Duplicate topic "+ words[1] +" for Tutor "+ words[2]);
                    }


                }
                else if (words[0].equals("REQUEST"))
                {
                    if(topicList.search(words[2]))
                    {
                        if (studentList.search(words[1])) {
                            System.out.println("Attempting to fulfil request for "+words[1]+" to receive "+words[3]+" hours of tutoring\n" +
                                    "in topic "+ words[2]+".");
                            if (studentList.request(tutorList, words[1], words[2], Integer.parseInt(words[3]))) {
                                System.out.println("----SUCCESS----");
                            } else {
                                System.out.println("No tutors available for Student"+words[1]+" for"+words[3]+" hours in topic "+words[2]+".");
                                System.out.println("----FAIL----");
                            }
                        }
                    }
                    else {
                        System.out.println("NOT_FOUND");
                    }

                }
                else if (words[0].equals("STUDENTREPORT"))
                {
                    System.out.println("-------------------------------------------------------");
                    if(studentList.search(words[1])) {
                        studentList.studentReport(words[1]);
                    }
                    else {
                        System.out.println("Student "+words[1]+" not found.");
                    }
                    System.out.println("-------------------------------------------------------");
                }
                else if (words[0].equals("TUTORREPORT"))
                {
                    System.out.println("-------------------------------------------------------");
                    if(tutorList.search(words[1])) {
                        tutorList.tutorReport(words[1]);
                    }
                    else {
                        System.out.println("Tutor "+words[1]+" not found.");
                    }
                    System.out.println("-------------------------------------------------------");
                }
                else if (words[0].equals("#"))
                {
                    System.out.println("\nCurrent on test: " + words[2] + "\n");

                }
                else if (words[0].equals("QUIT"))
                {
                    System.out.println(".......................................................");
                    System.out.println("-------------------------BYE---------------------------");
                    break;
                }
                line = read.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
