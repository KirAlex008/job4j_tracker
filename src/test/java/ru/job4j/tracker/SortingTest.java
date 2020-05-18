package ru.job4j.tracker;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SortingTest {

    @Test
    public void compareHight() {
        List<Item> items1 = Arrays.asList(
                new Item("test3"),
                new Item("test1"),
                new Item("test2")
        );
        List<Item> items2 = Arrays.asList(
                new Item("test1"),
                new Item("test2"),
                new Item("test3")
        );
        Collections.sort(items1, new SortingHight());
        assertThat(items1, (is(items2)));
    }

    @Test
    public void compareLow() {
        SortingLow low = new SortingLow();
        List<Item> items1 = Arrays.asList(
                new Item("test3"),
                new Item("test1"),
                new Item("test2")
        );
        List<Item> items2 = Arrays.asList(
                new Item("test3"),
                new Item("test2"),
                new Item("test1")
        );
        low.sorting(items1);
        assertThat(items1, (is(items2)));
    }
}