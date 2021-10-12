
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {

   public static void main(String[] args) {

      File file = new File("tasks.txt");
       int foo = 0;

       try {
           if (file.createNewFile()) {

           }
       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }


       ArrayList<String> tasks = new ArrayList<String>();

    try {

        Scanner scReader = new Scanner(file);
        String [] tempTasks = scReader.nextLine().split(", ");
        Collections.addAll(tasks, tempTasks);


    }catch (IOException e){

        e.printStackTrace();
    }
       System.out.println("Write new task or mark as done an existent one:");
       System.out.println("Use \"close\" to end the session safely.");

       while (true) {

           Scanner sc = new Scanner(System.in);
           String actualtext = sc.nextLine();

           try {
              foo = Integer.parseInt(actualtext);

           }catch (NumberFormatException e)
           { }

           if (!actualtext.equals("") && !actualtext.equals("close")) {
               //adauga sau marcheaza task

               if (isNumeric(actualtext) == false) {
                   //adauga task cu numele actualtext
                   tasks.add(actualtext);
                   System.out.println("Task added successfully!");
               } else {
                   //marcheaza ca facut taskul de la positia actualtext si verifica ca e valid
                   if (foo - 1 < tasks.size()) {
                       tasks.remove(foo - 1);
                       tasks.trimToSize();
                       System.out.println("Task removed!");
                   } else {
                       System.out.println("Task is inexistent");
                   }
               }

           } else if (actualtext.equals("close") ){

               try {
                   //scrie in fisier
                   FileWriter fw = new FileWriter(file);
                   fw.write("");
                   fw.write(tasks.toString().trim());
                   fw.flush();
                   fw.close();
                   System.out.println("Program inchis cu succes");
                   System.exit(0);

               } catch (IOException e) {
                   e.printStackTrace();
               }

           } else {
               System.out.println("Please insert a valid task name, task name or close the program.");
           }

       }
   }

    public static boolean isNumeric(String strNum) {

        try {
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }




}
