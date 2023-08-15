import java.util.Scanner;

public class Main {
    // Simple CRUD application
    // Create, Read, Update, Delete;
    public static void main(String[] args) {

        Scanner scanStr = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        Student[] studentList = new Student[5];


        while (true) {
            System.out.println("1.Add Student\n2.Update Student Information\n3.Delete Student from List\n4.Show Student List\n5.Exit");
            System.out.print("Choose one option: ");
            int option = scanInt.nextInt();
            switch (option){
                case 1:{//Add Student

                    for (int i = 0; i < studentList.length; i++) {

                        if(studentList[i] != null && i == studentList.length - 1){
                            System.out.println("List is full.");
                        }

                        if(studentList[i] != null){
                            continue;
                        } else {
                            System.out.println("Enter your first name: ");
                            String studentName = scanStr.nextLine();
                            System.out.println("Enter your  surname: ");
                            String studentSurname = scanStr.nextLine();
                            System.out.println("Enter your  age: ");
                            int studentAge = scanInt.nextInt();

                            studentList[i] = new Student(studentName, studentSurname, studentAge);
                            System.out.println("Student succesfully added to the list.");
                            break;
                        }
                    }

                }break;
                case 2:{//Update Student Information
                    System.out.println("Please enter Student list Number: ");
                    int n = scanInt.nextInt();
                    for (int i = 0; i < studentList.length; i++) {
                        if(i == n-1 && studentList[i] != null){
                            System.out.println("Enter your first name: ");
                            String studentName = scanStr.nextLine();
                            System.out.println("Enter your  surname: ");
                            String studentSurname = scanStr.nextLine();
                            System.out.println("Enter your  age: ");
                            int studentAge = scanInt.nextInt();

                            studentList[i] = new Student(studentName, studentSurname, studentAge);
                            System.out.println("Student succesfully updated.");

                        }
                    }
                }break;
                case 3:{
                    System.out.println("Please enter Student list Number: ");
                    int n = scanInt.nextInt();
                    for (int i = 0; i < studentList.length; i++) {
                        if(i == n-1){
                            studentList[i] = null;
                        }
                    }
                }break;
                case 4:{
                    for (int i = 0; i < studentList.length; i++) {
                        if(studentList[i] == null){
                            System.out.println("bo'sh joy");
                        }else {
                            System.out.println(i+1 +"." + studentList[i].toString());
                        }

                    }
                    System.out.println("Student Successfully deleted.");
                }break;

                case 5:{}break;

                default: {
                    System.out.println("Wrong option");
                }break;
            }
            if(option == 5){break;}
        }

    }
}
