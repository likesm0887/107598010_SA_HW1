package databaseSystem;

import entity.Course;

import java.io.FileNotFoundException;
import java.util.List;

public interface Database {

    void connectDB();

    void insert(Course course);

    void createTable();

    List<Course> read();

    void delete(String courseName);

    void update(Course course);

    Course select(String courseName);
}
