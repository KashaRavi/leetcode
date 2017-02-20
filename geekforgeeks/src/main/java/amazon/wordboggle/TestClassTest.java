package amazon.wordboggle;

import org.junit.Test;

import static amazon.wordboggle.TestClass.findMatchingWords;

/**
 * Created by Ravi Kasha on 20-02-2017.
 */
public class TestClassTest {

    @Test
    public void testCase1() {
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GEE"};
        char[][] boggle = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};
        findMatchingWords(dictionary, boggle);
    }

    @Test
    public  void testCase2() {
        String[] dictionary = {"CAT"};
        char[][] boggle = {
                {'C', 'A', 'P'},
                {'A', 'N', 'D'},
                {'T', 'I', 'E'}};
        findMatchingWords(dictionary, boggle);
    }
}