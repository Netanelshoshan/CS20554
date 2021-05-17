import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    //The students array
    static Student[] listOfStudents = {
            new Student(205695232, "Mikael", 67),
            new Student(316393321, "Netanel", 81.5),
            new Student(316393321, "Netanel", 81.5),
            new Student(224232449, "Or", 75.8),
            new Student(311353447, "Alex", 89),
            new Student(206439544, "Yogev", 60),
            new Student(232142033, "Avraham", 69),
            new Student(68701159, "Miryam", 88)};


    public static void main(String[] args) {

        //Initializing empty group
        System.out.println("Initializing empty sortedGroup..");
        SortedGroup<Student> sortedGroup = new SortedGroup<>();
        System.out.printf("SortedGroup status: %s%n", sortedGroup);

        //Adding the student to the group
        System.out.println("Now adding new students..\n");
        for (int i = 0; i < listOfStudents.length; i++) {
            System.out.printf("Student Added: %s%n", listOfStudents[i].toString());
            sortedGroup.add(listOfStudents[i]);
        }
        System.out.printf("\nSortedGroup status: %s%n", sortedGroup);

        //Removing random student from group;
        System.out.printf("Now removing random students.. (1-2)\n\n");
        SecureRandom random = new SecureRandom();
        int guess = 1 + random.nextInt(5);
        int cnt = 0;
        while (cnt < 2) {
            System.out.printf("Removing student : %s\n", listOfStudents[guess].toString());
            System.out.printf("Students Removed: %d\n", sortedGroup.remove(listOfStudents[guess]));
            guess = random.nextInt(5);
            cnt++;

        }
        System.out.printf("\nSortedGroup status: %s%n", sortedGroup);


        //Testing the reduce method
        System.out.printf("Now testing the reduce method..\n\nPlease enter desired grade:");

        int g = new Scanner(System.in).nextInt();
        Student student = new Student(0, "request", g);
        System.out.printf("First, we create our request by creating new \"%s\" " +
                "\nAnd we wand to reduce anything below %s.\n", student, student.getGrade());
        System.out.printf("\nSortedGroup Before reduction: %s\n", sortedGroup);
        SortedGroup<Student> reduced = Other.reduce(sortedGroup, student);
        System.out.printf("SortedGroup After reduction: %s\n", reduced);
        System.out.printf("SortedGroup After changes (deletions): %s\n", sortedGroup);


    }
}
