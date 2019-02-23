import databaseSystem.MockDatabase;
import entity.Course;
import org.junit.Before;

public class MockDatabaseTest extends SqlLiteDatabaseTest{
    Course course;
    @Before
    @Override
    public void  setup()
    {
        sql = new MockDatabase();
        course = new Course();
        course.setCourseDescription("很棒");
        course.setCourseAttentionNote("要修過ooad");
        course.setCourseNote("加油");
        course.setCourseTarget("學生");
        course.setCoursePrice(100);
        course.setCourseName("軟體工程");
    }


}
