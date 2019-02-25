package databaseSystem;

import entity.Course;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class DatabaseAccess implements Database {

    @Override
    public void connectDB() {

    }

    @Override
    public void createTable() {

    }

    @Override
    public void insert(Course course) {

    }

    @Override
    public List<Course> read() {
        return null;
    }


    @Override
    public void delete(String courseName) {

    }

    @Override
    public void update(Course course) {

    }

    @Override
    public Course select(String courseName) {
        return null;
    }


}
