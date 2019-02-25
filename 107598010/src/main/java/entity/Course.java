package entity;

public class Course {


    String courseName;
    String courseDescription;
    String courseTarget;
    int coursePrice;
    String courseAttentionNote;
    String courseNote;

    public Course() {

    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName = courseName;
    }


    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDescription() {
        return this.courseDescription = courseDescription;
    }


    public void setCourseTarget(String CourseTarget) {
        this.courseTarget = CourseTarget;
    }

    public String getCourseTarget() {
        return this.courseTarget = courseTarget;
    }


    public void setCourseAttentionNote(String courseAttentionNote) {
        this.courseAttentionNote = courseAttentionNote;
    }

    public String getCourseAttentionNote() {
        return this.courseAttentionNote = courseAttentionNote;
    }


    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    public int getCoursePrice() {
        return this.coursePrice;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public String getCourseNote() {
        return this.courseNote;
    }
}
