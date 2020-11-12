package team.ourteam;

import java.util.Vector;

public class VectorDemo {

    public static void main(String[] arg) {
        //Create an empty Vector
        Vector<String> vec = new Vector<>();
        //Add elements to the Vector
        vec.add("Python");
        vec.add("Java");
        vec.add("COBOL");
        vec.add("Ruby");
        //Checking if Java is present or not
        if (vec.contains("ajsdk")) {
            System.out.println("Java is present at the index " + vec.indexOf("Java"));
        } else {
            System.out.println("Java is not present in the list");
        }
    }
}
