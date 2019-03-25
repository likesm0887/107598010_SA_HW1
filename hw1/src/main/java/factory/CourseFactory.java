package factory;

import entity.Course;

public class CourseFactory {

    public Course createCourseObject(String courseName,
                                     String courseDescription,
                                     String courseTarget,
                                     int coursePrice,
                                     String courseAttentionNote,
                                     String courseNote) {
        Course course = new Course(courseName,courseDescription,courseTarget,coursePrice,courseAttentionNote,courseNote);
        return course;
    }

}
