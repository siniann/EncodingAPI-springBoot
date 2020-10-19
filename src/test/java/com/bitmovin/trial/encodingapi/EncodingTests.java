package com.bitmovin.trial.encodingapi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: sini_ann
 * Date: 16/10/20 5:42 PM
 */
public class EncodingTests {
    @Test
    public void get100EncodingListShouldReturnListOfSize100() {
        Encoding tester = new Encoding(); // Encoding is tested
        List<Encoding> testList = tester.get100EncodingList();

        assertNotNull(testList);
        assertEquals(100, testList.size() ,"Size of list must be 100");
    }

    @Test()
    public void sortingWithNoParametersShouldThrowIllegalArgumentException() {
        Encoding tester = new Encoding(); // Encoding is tested
        // empty ArrayList
        List<Encoding> testList  = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            tester.sortEncodingsByPriority(testList);
        });
    }

    @Test
    public void sortEncodingsByPriorityDescendingTest() {
        Encoding e1 = new Encoding("bc1b560c-7436-4b6a-aebe-5b462f6d33e9", 7);
        Encoding e2 = new Encoding("bc1b560c-7436-4b6a-aebe-5b462f6d33e9", 99);
        Encoding e3 = new Encoding("0b111a5e-5cbc-420b-b582-6f159ca66d33", 45);

        List<Encoding> testList  = new ArrayList<>();
        testList.add(e1);
        testList.add(e2);
        testList.add(e3);

        Encoding tester = new Encoding(); // Encoding is tested

        List<Encoding> sortedlist = tester.sortEncodingsByPriority(testList);

        assertNotNull(sortedlist);
        assertEquals(99, sortedlist.get(0).getPriority() ,"Largest priority should be stored 1st");
        assertEquals(45, sortedlist.get(1).getPriority() ,"2nd largest priority should be stored 2nd");
        assertEquals(7, sortedlist.get(2).getPriority() ,"Smallest priority should be stored last");
    }



}
