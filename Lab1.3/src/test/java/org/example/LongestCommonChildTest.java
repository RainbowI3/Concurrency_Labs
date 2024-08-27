package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonChildTest {

    @Test
    public void findLongestCommonChild() {
        LongestCommonChild lcc = new LongestCommonChild();

        assertEquals("ry", lcc.findLongestCommonChild("harry", "robby"));
        assertEquals("error", lcc.findLongestCommonChild("xx", "zz"));
        assertEquals("acdf", lcc.findLongestCommonChild("abcdef", "acdf"));
        assertEquals("abd", lcc.findLongestCommonChild("abcd", "abdc"));

    }
}