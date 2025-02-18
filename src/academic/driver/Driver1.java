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
public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Course[] courses = new Course[100];
        Student[] students = new Student[100];
        Enrollment[] enrollments = new Enrollment[100];

        int courseCount = 0;
        int studentCount = 0;
        int enrollmentCount = 0;

        while (scanner.hasNextLine()) {
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

                            boolean exists = false;
                            for (int i = 0; i < courseCount; i++) {
                                if (courses[i].getCode().equals(code)) {
                                    exists = true;
                                    break;
                                }
                            }

                            if (!exists) {
                                courses[courseCount++] = new Course(code, name, credits, grade);
                            }
                        }
                        break;

                    case "student-add":
                        if (segments.length == 5) {
                            String id = segments[1];
                            String name = segments[2];
                            int year = Integer.parseInt(segments[3]);
                            String major = segments[4];

                            boolean exists = false;
                            for (int i = 0; i < studentCount; i++) {
                                if (students[i].getId().equals(id)) {
                                    exists = true;
                                    break;
                                }
                            }

                            if (!exists) {
                                students[studentCount++] = new Student(id, name, year, major);
                            }
                        }
                        break;

                    case "enrollment-add":
                        if (segments.length == 5) {
                            String courseCode = segments[1];
                            String studentId = segments[2];
                            String academicYear = segments[3];
                            String semester = segments[4];

                            boolean exists = false;
                            for (int i = 0; i < enrollmentCount; i++) {
                                if (enrollments[i].getCourseCode().equals(courseCode) &&
                                    enrollments[i].getStudentId().equals(studentId) &&
                                    enrollments[i].getAcademicYear().equals(academicYear) &&
                                    enrollments[i].getSemester().equals(semester)) {
                                    exists = true;
                                    break;
                                }
                            }

                            if (!exists) {
                                enrollments[enrollmentCount++] = new Enrollment(courseCode, studentId, academicYear, semester);
                            }
                        }
                        break;
                }
            }
        }

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

        printCourses(courses, courseCount);
        printStudents(students, studentCount);
        printEnrollments(enrollments, enrollmentCount);

        scanner.close();
    }

    private static void printCourses(Course[] courses, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(courses[i].getCode() + "|" + courses[i].getName() + "|" + courses[i].getCredits() + "|" + courses[i].getGrade());
        }
    }

    private static void printStudents(Student[] students, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(students[i].getId() + "|" + students[i].getName() + "|" + students[i].getYear() + "|" + students[i].getMajor());
        }
    }

    private static void printEnrollments(Enrollment[] enrollments, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(enrollments[i].getCourseCode() + "|" + enrollments[i].getStudentId() + "|" + enrollments[i].getAcademicYear() + "|" + enrollments[i].getSemester() + "|None");
        }
    }
}