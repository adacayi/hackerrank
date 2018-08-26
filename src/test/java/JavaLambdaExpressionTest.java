import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JavaLambdaExpressionTest {
    private MyMath myMath;

    @BeforeEach
    public void init() {
        myMath = new MyMath();
    }

    @Test
    public void should_ReturnTrue_When_PalindromeCheckFor3() {
        assertTrue(myMath.isPalindrome().check(3));
    }

    @Test
    public void should_ReturnTrue_When_PalindromeCheckFor33() {
        assertTrue(myMath.isPalindrome().check(33));
    }
    @Test
    public void should_ReturnFalse_When_PalindromeCheckFor34() {
        assertFalse(myMath.isPalindrome().check(34));
    }
    @Test
    public void should_ReturnTrue_When_PalindromeCheckFor343() {
        assertTrue(myMath.isPalindrome().check(343));
    }
    @Test
    public void should_ReturnTrue_When_PrimeCheckFor2() {
        assertTrue(myMath.isPrime().check(2));
    }
    @Test
    public void should_ReturnTrue_When_PrimeCheckFor3() {
        assertTrue(myMath.isPrime().check(3));
    }
    @Test
    public void should_ReturnTrue_When_PrimeCheckFor7() {
        assertTrue(myMath.isPrime().check(7));
    }
    @Test
    public void should_ReturnTrue_When_PrimeCheckFor13() {
        assertTrue(myMath.isPrime().check(13));
    }
    @Test
    public void should_ReturnFalse_When_PrimeCheckFor0() {
        assertFalse(myMath.isPrime().check(0));
    }
    @Test
    public void should_ReturnFalse_When_PrimeCheckFor1() {
        assertFalse(myMath.isPrime().check(1));
    }
    @Test
    public void should_ReturnFalse_When_PrimeCheckFor4() {
        assertFalse(myMath.isPrime().check(4));
    }
    @Test
    public void should_ReturnFalse_When_PrimeCheckFor6() {
        assertFalse(myMath.isPrime().check(6));
    }
    @Test
    public void should_ReturnFalse_When_PrimeCheckFor9() {
        assertFalse(myMath.isPrime().check(9));
    }
}
