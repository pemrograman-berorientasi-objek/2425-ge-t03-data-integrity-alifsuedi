package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.*;

/**
 * @author 12S23025-Alif Aflah Suedi
 * @author 12S23039-Prisca R. Manurung
 */

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();

        Set<String> courseSet = new HashSet<>();
        Set<String> studentSet = new HashSet<>();
        Set<String> enrollmentSet = new HashSet<>();

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

                            if (!courseSet.contains(code)) {
                                courses.add(new Course(code, name, credits, grade));
                                courseSet.add(code);  // Tambahkan ke set
                            }
                        }
                        break;

                    case "student-add":
                        if (segments.length == 5) {
                            String id = segments[1];
                            String name = segments[2];
                            int year = Integer.parseInt(segments[3]);
                            String major = segments[4];

                            if (!studentSet.contains(id)) {
                                students.add(new Student(id, name, year, major));
                                studentSet.add(id);  // Tambahkan ke set
                            }
                        }
                        break;

                    case "enrollment-add":
                        if (segments.length == 5) {
                            String courseCode = segments[1];
                            String studentId = segments[2];
                            String academicYear = segments[3];
                            String semester = segments[4];
                            String enrollmentKey = courseCode + "#" + studentId + "#" + academicYear + "#" + semester;

                            if (!enrollmentSet.contains(enrollmentKey)) {
                                enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                                enrollmentSet.add(enrollmentKey);  // Tambahkan ke set
                            }
                        }
                        break;
                }
            }
        }

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
