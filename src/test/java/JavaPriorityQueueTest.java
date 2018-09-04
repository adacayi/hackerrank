import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaPriorityQueueTest {
    private Priorities priorities;

    @BeforeEach
    public void init() {
        priorities = new Priorities();
    }

    @Test
    public void should_ReturnCGPA_When_GetCPGA() {
        double cGPA = 3.93;
        String name = "Ahmet";
        int id = 2;
        Student student = new Student(id, name, cGPA);
        double expected = cGPA;
        double actual = student.getcGPA();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnName_When_GetName() {
        double cGPA = 3.93;
        String name = "Ahmet";
        int id = 2;
        Student student = new Student(id, name, cGPA);
        String expected = name;
        String actual = student.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnId_When_GetId() {
        double cGPA = 3.93;
        String name = "Ahmet";
        int id = 2;
        Student student = new Student(id, name, cGPA);
        int expected = id;
        int actual = student.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnEmpty_When_NoEvent() {
        List<Student> actual = priorities.getStudents(new ArrayList<String>());
        List<Student> expected = Arrays.asList();
        assertEquals(expected.size(), actual.size(), () -> "Result list sizes must be equal");
    }

    @Test
    public void should_ReturnStudent_When_OnlyOneAdd() {
        List<String> events = Arrays.asList("ENTER Ahmet 3.75 50");
        List<Student> expected = Arrays.asList(new Student(50, "Ahmet", 3.75));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnTwoStudentsInOrderForCGPA_When_OnlyTwoAdded() {
        List<String> events = Arrays.asList("ENTER Ahmet 3.75 50", "ENTER Ahmet 3.76 50");
        List<Student> expected = Arrays.asList(new Student(50, "Ahmet", 3.76),
                new Student(50, "Ahmet", 3.75));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnTwoStudentsInOrderForName_When_OnlyTwoAdded() {
        List<String> events = Arrays.asList("ENTER Mustafa 3.75 50", "ENTER Ahmet 3.75 50");
        List<Student> expected = Arrays.asList(new Student(50, "Ahmet", 3.75),
                new Student(50, "Mustafa", 3.75));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnTwoStudentsInOrderForId_When_OnlyTwoAdded() {
        List<String> events = Arrays.asList("ENTER Ahmet 3.75 50", "ENTER Ahmet 3.75 49");
        List<Student> expected = Arrays.asList(new Student(49, "Ahmet", 3.75),
                new Student(50, "Ahmet", 3.75));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnOneStudentsInOrderForId_When_OnlyTwoAddedOneRemoved() {
        List<String> events = Arrays.asList("ENTER Ahmet 3.75 50", "ENTER Ahmet 3.75 49", "SERVED");
        List<Student> expected = Arrays.asList(new Student(50, "Ahmet", 3.75));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnDanAshleyShafaetMaria() {
        String input = "ENTER John 3.75 50\n" +
                "ENTER Mark 3.8 24\n" +
                "ENTER Shafaet 3.7 35\n" +
                "SERVED\n" +
                "SERVED\n" +
                "ENTER Samiha 3.85 36\n" +
                "SERVED\n" +
                "ENTER Ashley 3.9 42\n" +
                "ENTER Maria 3.6 46\n" +
                "ENTER Anik 3.95 49\n" +
                "ENTER Dan 3.95 50\n" +
                "SERVED";
        List<String> events = Arrays.asList(input.split("\n"));
        List<Student> expected = Arrays.asList(new Student(50, "Dan", 3.95),
                new Student(42, "Ashley", 3.9),
                new Student(35, "Shafaet", 3.7),
                new Student(46, "Maria", 3.6));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnDanAshleyShafaetMaria_WhenOnlyInserted() {
        String input = "ENTER Shafaet 3.7 35\n" +
                "ENTER Ashley 3.9 42\n" +
                "ENTER Maria 3.6 46\n" +
                "ENTER Dan 3.95 50";
        List<String> events = Arrays.asList(input.split("\n"));
        List<Student> expected = Arrays.asList(new Student(50, "Dan", 3.95),
                new Student(42, "Ashley", 3.9),
                new Student(35, "Shafaet", 3.7),
                new Student(46, "Maria", 3.6));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnShafaetMaria_WhenOnlyInserted() {
        String input = "ENTER Shafaet 3.7 35\n" +
                "ENTER Maria 3.6 46";
        List<String> events = Arrays.asList(input.split("\n"));
        List<Student> expected = Arrays.asList(
                new Student(35, "Shafaet", 3.7),
                new Student(46, "Maria", 3.6));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnDanShafaetMaria_WhenOnlyInserted() {
        String input = "ENTER Shafaet 3.7 35\n" +
                "ENTER Maria 3.6 46\n" +
                "ENTER Dan 3.95 50";
        List<String> events = Arrays.asList(input.split("\n"));
        List<Student> expected = Arrays.asList(
                new Student(50, "Dan", 3.95),
                new Student(35, "Shafaet", 3.7),
                new Student(46, "Maria", 3.6));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void should_ReturnDanShafaetMaria_WhenEmptyQueueServeIsCalled() {
        String input = "SERVED\nSERVED\nENTER Shafaet 3.7 35\n" +
                "ENTER Maria 3.6 46\n" +
                "ENTER Dan 3.95 50";
        List<String> events = Arrays.asList(input.split("\n"));
        List<Student> expected = Arrays.asList(
                new Student(50, "Dan", 3.95),
                new Student(35, "Shafaet", 3.7),
                new Student(46, "Maria", 3.6));
        List<Student> actual = priorities.getStudents(events);
        assertNotNull(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
