import databaseSystem.MockDatabase;
import entity.Course;
import org.junit.Before;

public class MockDatabaseTest extends SqlLiteDatabaseTest {
    Course course;

    @Before
    @Override
    public void setup() {
        sql = new MockDatabase();
        createTestCourse();
    }
}
