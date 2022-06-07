import java.util.*;
import java.io.*;

class Main {
    public static void createFile() {
      try {
        File myObj = new File("sortedStudentData.txt");
        if (myObj.createNewFile()) {
          System.out.println("File created: " + myObj.getName());
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }

    public static void assignFreeHours(Student[] stu) {
        int[] availableHours = {5,5,5,5,5};
        for(int i=0; i<25; i++){
            int randHour = (int) (Math.random()*5);
            boolean foundAvail = false;
            while(!foundAvail){
                if(availableHours[randHour]>0){
                    availableHours[randHour]--;
                    foundAvail = true;
                } else {
                    randHour = (int) (Math.random()*5);
                }
            }
            stu[i].freeHour = randHour+1;
        }
    }

    public static void assignChoices(Student[] stu) {
        String[] interestGroups = {"Engineering", "History", "Math", "Sports", "English"};
        for(Student i:stu){
            int interest1 = (int) (Math.random()*5);
            int interest2 = (int) (Math.random()*5);
            int interest3 = (int) (Math.random()*5);
            boolean allUnique = false;
            while(!allUnique){
                if(interest1 != interest2 && interest1 != interest3 && interest2 != interest3){
                    i.choice1 = interestGroups[interest1];
                    i.choice2 = interestGroups[interest2];
                    i.choice3 = interestGroups[interest3];
                    allUnique = true;
                } else {
                    interest2 = (int) (Math.random()*5);
                    interest3 = (int) (Math.random()*5);
                }
            }
        }
    }

    public static void assignNames(Student[] stu) {
        String[] firstNames = {"JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID", "RICHARD", "CHARLES", "JOSEPH", "THOMAS", "MARY", "PATRICIA", "JENNIFER", "LINDA", "ELIZABETH", "BARBARA", "SUSAN", "JESSICA", "SARAH", "KAREN"};
        String[] lastNames = {"SMITH","JOHNSON","WILLIAMS","BROWN","JONES","GARCIA","MILLER","DAVIS","RODRIGUEZ","MARTINEZ","HERNANDEZ","LOPEZ","GONZALEZ","WILSON","ANDERSON","THOMAS","TAYLOR","MOORE","JACKSON","MARTIN","LEE","PEREZ","THOMPSON","WHITE","HARRIS","SANCHEZ","CLARK"};
        ArrayList<String> usedNames = new ArrayList<String>();
        for(Student i:stu){
            int index1 = (int) (Math.random()*firstNames.length);
            int index2 = (int) (Math.random()*lastNames.length);
            boolean foundName = false;
            String name = firstNames[index1] + " " + lastNames[index2];
            while(!foundName){
                if(usedNames.contains(name)){
                    index1 = (int) (Math.random()*firstNames.length);
                    index2 = (int) (Math.random()*lastNames.length);
                    name = firstNames[index1] + " " + lastNames[index2];
                } else {
                    i.name = name;
                    usedNames.add(name);
                    foundName = true;
                }
            }
        }
    }

    private static void printArray(List<String> input) {
        System.out.print('\n');
        for(int i = 0; i < input.size(); i++) {
            System.out.print(input.get(i)+" ");
        }
    }

  public static List<List<String>> permute(String[] thing) {
    List<List<String>> results = new ArrayList<List<String>>();
    if (thing == null || thing.length == 0) {
        return results;
    }
    List<String> result = new ArrayList<>();
    dfs(thing, results, result);
    return results;
}

  public static void dfs(String[] thing, List<List<String>> results, List<String> result) {
      if (thing.length == result.size()) {
          List<String> temp = new ArrayList<>(result);
          results.add(temp);
      }        
      for (int i=0; i<thing.length; i++) {
          if (!result.contains(thing[i])) {
              result.add(thing[i]);
              dfs(thing, results, result);
              result.remove(result.size() - 1);
          }
      }
  }

    public static void main(String[] args) {
        Student student1 = new Student();Student student2 = new Student();Student student3 = new Student();Student student4 = new Student();Student student5 = new Student();Student student6 = new Student();Student student7 = new Student();Student student8 = new Student();Student student9 = new Student();Student student10 = new Student();Student student11 = new Student();Student student12 = new Student();Student student13 = new Student();Student student14 = new Student();Student student15 = new Student();Student student16 = new Student();Student student17 = new Student();Student student18 = new Student();Student student19 = new Student();Student student20 = new Student();Student student21 = new Student();Student student22 = new Student();Student student23 = new Student();Student student24 = new Student();Student student25 = new Student();
        Student[] students = {student1,student2,student3,student4,student5,student6,student7,student8,student9,student10,student11,student12,student13,student14,student15,student16,student17,student18,student19,student20,student21,student22,student23,student24,student25};
        assignFreeHours(students);
        assignChoices(students);
        assignNames(students);
        System.out.println("Assingments Complete");

        String[] interestGroups = {"Engineering", "History", "Math", "Sports", "English"};
        List<List<String>> allOrders = permute(interestGroups); 
      System.out.println(allOrders.size());
      ArrayList<Integer> allScores = new ArrayList<Integer>();
      ArrayList<int[]> allPrefrenceDistribution = new ArrayList<int[]>();
        for(int j=0;j<allOrders.size();j++){
          int[] prefrenceDistribution = new int[3];
          List<String> curr = allOrders.get(j);
          int powerScore = 0;
          for(int l=0;l<students.length;l++){
            Student currStudent = students[l];
            int stufreehour = currStudent.freeHour;
            String offeredInterest = curr.get(stufreehour-1);
            if(currStudent.choice1 == offeredInterest){
              powerScore+=4;
              prefrenceDistribution[0]+=1;
            }else if(currStudent.choice2 == offeredInterest){
              powerScore+=2;
              prefrenceDistribution[1]+=1;
            }
            else if(currStudent.choice3 == offeredInterest){
              powerScore+=1;
              prefrenceDistribution[2]+=1;
            }
          }
          allScores.add(powerScore);
          allPrefrenceDistribution.add(prefrenceDistribution);
        }
      System.out.println(Collections.max(allScores));
      int maxIndex = allScores.indexOf(Collections.max(allScores));
      List<String> bestOrder = allOrders.get(maxIndex);
      System.out.println(bestOrder);
      System.out.println(Arrays.toString(allPrefrenceDistribution.get(maxIndex)));
      createFile();
      try {
        FileWriter myWriter = new FileWriter("sortedStudentData.txt");
        //writes best order
        myWriter.write("Best Order," + String.valueOf(bestOrder).replace("[", "").replace("]","").replace(" ",""));
        //writes best score
        myWriter.write("\r\n" + "Order Score,"+allScores.get(maxIndex));
        //writes pref.dis.
        myWriter.write("\r\n" + "Pref. Distribution,"+ Arrays.toString(allPrefrenceDistribution.get(maxIndex)).replace("[","").replace("]","").replace(" ",""));
        //new line
        myWriter.write("\r\n");
        //writes table header
        myWriter.write("\r\n" + "All Orders,Period 1,Period 2,Period 3,Period 4,Period 5,Score,Pref.Dis.(1st-2nd-3rd)");
        //loops for the rest
        for(int p = 0;p<allScores.size();p++){
          int temp = p+1;
          myWriter.write("\r\n" + temp + "," + String.valueOf(allOrders.get(p)).replace("[", "").replace("]","").replace(" ","")+","+allScores.get(p)+","+Arrays.toString(allPrefrenceDistribution.get(p)).replace("[", "").replace("]","").replace(" ","").replace(",","-"));
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
}