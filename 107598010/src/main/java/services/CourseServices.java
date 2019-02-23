package services;

import databaseSystem.Database;
import databaseSystem.DatabaseAccess;
import databaseSystem.SqlLiteDatabase;
import entity.Course;

import java.util.List;

public class CourseServices
{
       private  Database database = new SqlLiteDatabase();
       public  void addCourse(Course course)
       {
           database.insert(course);
       }
       public List<Course> allCourse()
       {
           return  database.read();
       }
       public  void deleteCourse(String courseName)
       {
           database.delete(courseName);
       }
       public Course selectCourse(String courseName)
       {
           return database.select(courseName);
       }

       public void updateCourse(Course course)
       {
           database.update(course);
       }

}
