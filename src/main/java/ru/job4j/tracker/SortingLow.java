package ru.job4j.tracker;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingLow implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        return first.getName().compareTo(second.getName());
    }

    public List<Item> sorting(List<Item> item1) {
        Collections.sort(item1, Collections.reverseOrder(new SortingHight()));
        return item1;
    }
}
