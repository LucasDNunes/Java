package arcade.intro;


import org.junit.Test;

import static org.junit.Assert.*;

public class TheJourneyBeginsTest {

    private int add(int param1, int param2) {
        return param1 + param2;
    }

    int centuryFromYear(int year) {
        return year%100 == 0 ? year/100 : (year/100)+1;
    }

    boolean checkPalindrome(String inputString) {
        String invertida = new StringBuffer(inputString).reverse().toString();
        return invertida.equals(inputString);
    }

    @Test
    public void testAdd() {
        assertEquals(2,add(1,1));
        assertEquals(4,add(1,3));
        assertEquals(7,add(4,3));
        assertEquals(13,add(10,3));
    }

    @Test
    public void testcenturyFromYear(){
        assertEquals(17, centuryFromYear(1700));
        assertEquals(18, centuryFromYear(1701));
    }

    @Test
    public void testcheckPalindrome(){
        assertTrue(checkPalindrome("aca"));
        assertTrue(checkPalindrome("aabaa"));
        assertTrue(checkPalindrome("a"));
        assertTrue(checkPalindrome("aia"));
        assertTrue(checkPalindrome("arara"));
        assertFalse(checkPalindrome("abac"));
    }

}
