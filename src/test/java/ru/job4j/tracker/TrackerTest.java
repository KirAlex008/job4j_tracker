package ru.job4j.tracker;


import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import org.hamcrest.core.IsNull;

import java.util.List;
import java.util.ArrayList;




public class TrackerTest {

    @Test
    public void whenFindAllItemThenTrackerHasnotNull() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item(null);
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item(null);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        //tracker.findAll();
        tracker.findAll();
        List<Item> forTest = new ArrayList<>();
        List<Item> test = new ArrayList<>();
        test = tracker.findAll();
        forTest.add(item1);
        forTest.add(item2);
        forTest.add(item3);
        forTest.add(item4);
        forTest.add(item5);
        assertThat(test, is(forTest));

    }

    @Test
    public void whenfindByNameItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item(null);
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item("test4");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);

        List<Item> forTest = new ArrayList<>();
        List<Item> test = new ArrayList<>();
        test = tracker.findAll();

        forTest.add(item4);
        forTest.add(item5);

        test = tracker.findByName("test4");


        assertThat(test, is(forTest));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenItemNotExistThenNull() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        tracker.delete(item.getId());
        Item result = tracker.findById(item.getId());
        assertThat(result, (is(IsNull.nullValue())));
    }

    @Test
    public void whenReplace() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}