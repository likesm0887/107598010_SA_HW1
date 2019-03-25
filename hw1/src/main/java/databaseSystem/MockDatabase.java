package databaseSystem;

import factory.CourseFactory;
import entity.Course;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MockDatabase extends DatabaseAccess {
    private Connection sqlDatabase;
    List<Course> mockCourseDatabase = new ArrayList<>();


    @Override
    public void connectDB() {

    }

    @Override
    public Course select(String courseName) {
        for (Course course : mockCourseDatabase) {
            if (course.getCourseName().equals(courseName)) {
                return courseFactory.createCourseObject(course.getCourseName(),
                        course.getCourseDescription(),
                        course.getCourseTarget(),
                        course.getCoursePrice(),
                        course.getCourseAttentionNote(),
                        course.getCourseNote());
            }
        }
        return null;
    }

    @Override
    public void insert(Course course) {
        mockCourseDatabase.add(course);
    }

    @Override
    public List<Course> read() {
        List<Course> courseList = new ArrayList<>();
        for (Course course : mockCourseDatabase) {
            courseList.add(courseFactory.createCourseObject(course.getCourseName(),
                    course.getCourseDescription(),
                    course.getCourseTarget(),
                    course.getCoursePrice(),
                    course.getCourseAttentionNote(),
                    course.getCourseNote()));

        }
        return courseList;
    }

    @Override
    public void delete(String courseName) {
        for (int i = 0; i < mockCourseDatabase.size(); i++) {
            if (mockCourseDatabase.get(i).equals(courseName)) {
                mockCourseDatabase.remove(i);
            }
        }
    }

    @Override
    public void update(Course course) {
        for (int i = 0; i < mockCourseDatabase.size(); i++) {
            if (mockCourseDatabase.get(i).equals(course.getCourseName())) {
                mockCourseDatabase.get(i).setCourseDescription(course.getCourseDescription());
                mockCourseDatabase.get(i).setCourseTarget(course.getCourseTarget());
                mockCourseDatabase.get(i).setCoursePrice(course.getCoursePrice());
                mockCourseDatabase.get(i).setCourseAttentionNote(course.getCourseAttentionNote());
                mockCourseDatabase.get(i).setCourseNote(course.getCourseName());
            }
        }
    }
}
