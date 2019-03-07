package Factory;

import entity.Course;

public class CourseFactory {

    public Course createCourseObject(String courseName,
                                     String courseDescription,
                                     String courseTarget,
                                     int coursePrice,
                                     String courseAttentionNote,
                                     String courseNote) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);
        course.setCourseTarget(courseTarget);
        course.setCoursePrice(coursePrice);
        course.setCourseAttentionNote(courseAttentionNote);
        course.setCourseNote(courseNote);
        return course;
    }

}
