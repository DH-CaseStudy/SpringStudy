import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll //전체 테스트 전에 최초 1회 실행
    static void beforeAll() {
        System.out.println("@Before All");
    }

    @BeforeEach //테스트 케이스가 실행되기 전에 매번 실행
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1(){
        System.out.println("@Test1");
    }

    @Test
    public void test2(){
        System.out.println("@Test2");
    }

    @Test
    public void test3(){
        System.out.println("@Test3");
    }

    @AfterAll // 테스트 종료를 마치고 1회 실행
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach //각 테스트 케이스가 종료하기 전 매번 실행
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
