
import databaseSystem.Database;
import databaseSystem.SqlLiteDatabase;
import entity.Course;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;

public class SqlLiteDatabaseTest {
    Course course;
    Database sql;
    @Before
    public void setup()
    {
        sql = new SqlLiteDatabase();
        course = new Course();
        course.setCourseDescription("很棒");
        course.setCourseAttentionNote("要修過ooad");
        course.setCourseNote("加油");
        course.setCourseTarget("學生");
        course.setCoursePrice(100);
        course.setCourseName("SA");
    }
    @Test
    public void  testConnect()
    {
        sql.connectDB();
    }
    @Test
    public void testRead()
    {
        sql.connectDB();
        sql.read();
    }
    @Test
    public void  testInsert() throws FileNotFoundException {

        sql.connectDB();
        sql.insert(course);
        sql.read();
    }
    @Test
    public void  testDelete() throws FileNotFoundException, ClassNotFoundException, SQLException, UnsupportedEncodingException {

        sql.connectDB();
        sql.createTable( );
        sql.insert(course);
        sql.delete("SA");
        sql.read();
    }
    @Test
    public void testSelect()
    {
        sql.connectDB();
        System.out.println(sql.select("SA").getCourseName());
    }
    @Test
    public void  testUpdate() throws FileNotFoundException, ClassNotFoundException, SQLException, UnsupportedEncodingException {

        sql.connectDB();
        sql.read();
        course = new Course();
        course.setCourseDescription("tes");
        course.setCourseAttentionNote("tet");
        course.setCourseNote("tet");
        course.setCourseTarget("teacher");
        course.setCoursePrice(10);
        course.setCourseName("sa");
        sql.update(course);
        sql.read();
    }





}
