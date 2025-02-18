package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.Scanner;
import java.util.Arrays;

/**
 * 12S23025-Alif Aflah Suedi
 * 12S23039-Prisca R. Manurung
 */
public class Driver2 {
    public static void main(String[] _args) {
        Scanner input = new Scanner(System.in);

        Course[] courses = new Course[100];
        Student[] students = new Student[100];
        Enrollment[] enrollments = new Enrollment[100];

        int courseCount = 0;
        int studentCount = 0;
        int enrollmentCount = 0;

        StringBuilder invalidEntries = new StringBuilder();

        while (true) {
            String line = input.nextLine().trim();

            if (line.equals("---")) {
                break;
            }

            String[] data = line.split("#");

            switch (data[0]) {
                case "course-add":
                    if (data.length == 5) {
                        courses[courseCount] = new Course(data[1], data[2], Integer.parseInt(data[3]), data[4]);
                        courseCount++;
                    }
                    break;
                case "student-add":
                    if (data.length == 5) {
                        students[studentCount] = new Student(data[1], data[2], Integer.parseInt(data[3]), data[4]);
                        studentCount++;
                    }
                    break;
                case "enrollment-add":
                    if (data.length == 5) {
                        String courseId = data[1];
                        String studentId = data[2];
                        
                        boolean courseExists = false;
                        boolean studentExists = false;
                        
                        for (int i = 0; i < courseCount; i++) {
                            if (courses[i].getCode().equals(courseId)) {
                                courseExists = true;
                                break;
                            }
                        }
                        
                        for (int i = 0; i < studentCount; i++) {
                            if (students[i].getId().equals(studentId)) {
                                studentExists = true;
                                break;
                            }
                        }
                        
                        if (!courseExists) {
                            invalidEntries.append("invalid course|").append(courseId).append("\n");
                        } else if (!studentExists) {
                            invalidEntries.append("invalid student|").append(studentId).append("\n");
                        } else {
                            boolean isDuplicateEnrollment = false;
                            for (int i = 0; i < enrollmentCount; i++) {
                                if (enrollments[i].getCourseCode().equals(courseId) &&
                                    enrollments[i].getStudentId().equals(studentId) &&
                                    enrollments[i].getAcademicYear().equals(data[3]) &&
                                    enrollments[i].getSemester().equals(data[4])) {
                                    isDuplicateEnrollment = true;
                                    break;
                                }
                            }
                            if (!isDuplicateEnrollment) {
                                enrollments[enrollmentCount] = new Enrollment(data[1], data[2], data[3], data[4]);
                                enrollmentCount++;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Error: Perintah tidak dikenali.");
            }
        }

        input.close();
        System.out.print(invalidEntries.toString());

        // Sorting Arrays
        Arrays.sort(courses, 0, courseCount, (c1, c2) -> c1.getCode().compareTo(c2.getCode())); // Sort ascending
        Arrays.sort(students, 0, studentCount, (s1, s2) -> s1.getId().compareTo(s2.getId())); // Sort ascending
        Arrays.sort(enrollments, 0, enrollmentCount, (e1, e2) -> {
            int cmp = e1.getCourseCode().compareTo(e2.getCourseCode());
            if (cmp != 0) return cmp;
            cmp = e1.getStudentId().compareTo(e2.getStudentId());
            if (cmp != 0) return cmp;
            cmp = e1.getAcademicYear().compareTo(e2.getAcademicYear());
            if (cmp != 0) return cmp;
            return e1.getSemester().compareTo(e2.getSemester());
        });

        // Cetak semua courses
        for (int i = 0; i < courseCount; i++) {
            System.out.println(courses[i].getCode() + "|" + courses[i].getName() + "|" + courses[i].getCredits() + "|" + courses[i].getGrade());
        }

        // Cetak semua students
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].getId() + "|" + students[i].getName() + "|" + students[i].getYear() + "|" + students[i].getMajor());
        }

        // Cetak semua enrollments
        for (int i = 0; i < enrollmentCount; i++) {
            System.out.println(enrollments[i].getCourseCode() + "|" + enrollments[i].getStudentId() + "|" + enrollments[i].getAcademicYear() + "|" + enrollments[i].getSemester() + "|None");
        }
    }
}