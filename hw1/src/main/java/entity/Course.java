package entity;

public class Course {


    private String courseName;
    private String courseDescription;
    private String courseTarget;
    private int coursePrice;
    private String courseAttentionNote;
    private String courseNote;

    public Course(String courseName, String courseDescription, String courseTarget, int coursePrice, String courseAttentionNote, String courseNote) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseTarget = courseTarget;
        this.coursePrice = coursePrice;
        this.courseAttentionNote = courseAttentionNote;
        this.courseNote = courseNote;
    }
    public  Course(){};

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }


    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDescription() {
        return this.courseDescription;
    }


    public void setCourseTarget(String CourseTarget) {
        this.courseTarget = CourseTarget;
    }

    public String getCourseTarget() {
        return this.courseTarget;
    }


    public void setCourseAttentionNote(String courseAttentionNote) {
        this.courseAttentionNote = courseAttentionNote;
    }

    public String getCourseAttentionNote() {
        return this.courseAttentionNote;
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
