package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.Scanner;

/**
 * @author 12S23025-Alif Aflah Suedi
 * @author 12S23039-Prisca R. Manurung
 */
import java.util.ArrayList;
 public class Driver2 {
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         ArrayList<Course> courses = new ArrayList<>();
         ArrayList<Student> students = new ArrayList<>();
         ArrayList<Enrollment> enrollments = new ArrayList<>();
 
         while (true) {
             String input = scanner.nextLine();
             if (input.equals("---")) {
                 break;
             }
             String[] segments = input.split("#");
             if (segments.length > 0) {
                 String command = segments[0];
                 switch (command) {
                     case "course-add":
                         if (segments.length == 5) {
                             String code = segments[1];
                             String name = segments[2];
                             int credits = Integer.parseInt(segments[3]);
                             String grade = segments[4];
                             courses.add(new Course(code, name, credits, grade));
                         }
                         break;
                     case "student-add":
                         if (segments.length == 5) {
                             String id = segments[1];
                             String name = segments[2];
                             int year = Integer.parseInt(segments[3]);
                             String major = segments[4];
                             students.add(new Student(id, name, year, major));
                         }
                         break;
                     case "enrollment-add":
                         if (segments.length == 5) {
                             String courseCode = segments[1];
                             String studentId = segments[2];
                             String academicYear = segments[3];
                             String semester = segments[4];
                             enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                         }
                         break;
                 }
             }
         }
 
         for (Course course : courses) {
             System.out.println(course.getCode() + "|" + course.getName() + "|" + course.getCredits() + "|" + course.getGrade());
         }
 
         for (Student student : students) {
             System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getMajor());
         }
 
         for (Enrollment enrollment : enrollments) {
             System.out.println(enrollment.getCourseCode() + "|" + enrollment.getStudentId() + "|" + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|None");
         }
 
         scanner.close();
     }
 }