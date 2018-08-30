import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TagContentExtractorTest {
    @Test
    public void should_ReturnHello_When_TaggedHello() {
        String line = "<a>Hello</a>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Hello"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnImtiazHadASecretCrash_When_TaggedHello() {
        String line = "<SA premium>Imtiaz has a secret crash</SA premium>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Imtiaz has a secret crash"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnHello_When_OutsideContentExists() {
        String line = "<h1>Hello</h1>Again<h2>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Hello"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnEmptyArray_When_NotTagNotClosedCorrectly() {
        String line = "<a>Hello<a>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnEmptyArray_When_NotTagClosedWithDifferentName() {
        String line = "<a>Hello<b>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnEmptyArray_When_CaseChangesBetweenTags() {
        String line = "<a>Hello</A>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnHelloIgnoreWhiteSpacesInTags_When_TaggedCorrectly() {
        String line = "<a    > Hello < /  a >";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Hello"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnHelloAndWorld_When_TaggedHelloWorld() {
        String line = "<a>Hello</a><h1>World</h1>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Hello","World"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnHello_When_InnerTagged() {
        String line = "<a><h1>Hello</h1></a>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Hello"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnHello_When_InnerTaggedAndInvalidContent() {
        String line = "<a><h1>Hello</h1>Again</a>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {"Hello"};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void should_ReturnEmptyArray_When_InvalidInnerTagAndContent() {
        String line = "<a><h1>Hello</h2>Again</a>";
        String[] actual = TagContentExtractor.extractContent(line);
        String[] expected = {};
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
}
