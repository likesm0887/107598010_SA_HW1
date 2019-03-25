import databaseSystem.Database;
import databaseSystem.MockDatabase;
import entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.CourseServices;


public class CourseServicesTest {
    private Database database = new MockDatabase();
    private CourseServices cs = new CourseServices();
    private Course course = new Course();

    @Before
    public void setUp() {
        createTestCourse();
        cs.setDatabase(database);
    }

    private void createTestCourse() {
        course.setCourseDescription("很棒");
        course.setCourseAttentionNote("for services");
        course.setCourseNote("加油");
        course.setCourseTarget("學生");
        course.setCoursePrice(100);
        course.setCourseName("SA");
    }

    @Test
    public void addCourseTest()
    {
        cs.addCourse(course);
        database.insert(course);
        Assert.assertEquals("SA", database.select((course.getCourseName())).getCourseName());

    }

    @Test
    public void allCourseTest() {
        cs.addCourse(course);
        course.setCourseName("軟體工程");
        cs.addCourse(course);
        Assert.assertEquals(2, cs.allCourse().size());

    }

    public void deleteCourseTest() {
        cs.addCourse(course);
        course.setCourseName("軟體工程");
        cs.addCourse(course);
        Assert.assertEquals(2, cs.allCourse().size());
        cs.deleteCourse("SA");
        Assert.assertEquals(1, cs.allCourse().size());
    }

    @Test
    public void selectCourse() {
        cs.addCourse(course);

        Assert.assertEquals("SA", cs.selectCourse("SA").getCourseName());
    }

    @Test
    public void updateCourseTest() {
        cs.addCourse(course);
        course.setCoursePrice(1000);
        cs.updateCourse(course);
        Assert.assertEquals(1000, cs.selectCourse("SA").getCoursePrice());
    }
}
