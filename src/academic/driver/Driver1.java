package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 12S23025-Alif Aflah Suedi
 * @author 12S23039-Prisca R. Manurung
 */

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();

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

                            if (courses.stream().noneMatch(course -> course.getCode().equals(code))) {
                                courses.add(new Course(code, name, credits, grade));
                            }
                        }
                        break;

                    case "student-add":
                        if (segments.length == 5) {
                            String id = segments[1];
                            String name = segments[2];
                            int year = Integer.parseInt(segments[3]);
                            String major = segments[4];

                            if (students.stream().noneMatch(student -> student.getId().equals(id))) {
                                students.add(new Student(id, name, year, major));
                            }
                        }
                        break;

                    case "enrollment-add":
                        if (segments.length == 5) {
                            String courseCode = segments[1];
                            String studentId = segments[2];
                            String academicYear = segments[3];
                            String semester = segments[4];

                            if (enrollments.stream().noneMatch(enrollment -> 
                                    enrollment.getCourseCode().equals(courseCode) &&
                                    enrollment.getStudentId().equals(studentId) &&
                                    enrollment.getAcademicYear().equals(academicYear) &&
                                    enrollment.getSemester().equals(semester))) {
                                enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                            }
                        }
                        break;
                }
            }
        }

        // Sorting Lists
        courses.sort(Comparator.comparing(Course::getCode)); // Sort ascending
        students.sort(Comparator.comparing(Student::getId)); // Sort ascending
        enrollments.sort(Comparator.comparing(Enrollment::getCourseCode)
                .thenComparing(Enrollment::getStudentId)
                .thenComparing(Enrollment::getAcademicYear)
                .thenComparing(Enrollment::getSemester));

        printCourses(courses);
        printStudents(students);
        printEnrollments(enrollments);

        scanner.close();
    }

    private static void printCourses(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course.getCode() + "|" + course.getName() + "|" + course.getCredits() + "|" + course.getGrade());
        }
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getMajor());
        }
    }

    private static void printEnrollments(List<Enrollment> enrollments) {
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getCourseCode() + "|" + enrollment.getStudentId() + "|" + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|None");
        }
    }
}