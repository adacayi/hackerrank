import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaAnagramsTest {
    private JavaAnagrams anagrams;

    @BeforeEach
    public void init() {
        anagrams = new JavaAnagrams();
    }

    @Test
    public void should_ReturnNonAnagrams_When_OneIsNull() {
        String first = null;
        String second = "";
        boolean actual = anagrams.isAnagram(first, second);
        assertFalse(actual);
    }

    @Test
    public void should_ReturnAnagrams_When_BothAreNull() {
        String first = null;
        String second = null;
        boolean actual = anagrams.isAnagram(first, second);
        assertTrue(actual);
    }

    @Test
    public void should_ReturnNonAnagrams_When_DifferentLength() {
        String first = "Abd";
        String second = "de";
        boolean actual = anagrams.isAnagram(first, second);
        assertFalse(actual);
    }

    @Test
    public void should_ReturnNonAnagrams_When_DifferentCounts() {
        String first = "Abbd";
        String second = "dbaa";
        boolean actual = anagrams.isAnagram(first, second);
        assertFalse(actual);
    }

    @Test
    public void should_ReturnAnagrams_When_SameCounts() {
        String first = "Abbdda";
        String second = "dbaadb";
        boolean actual = anagrams.isAnagram(first, second);
        assertTrue(actual);
    }
}
