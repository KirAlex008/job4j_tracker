package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import org.hamcrest.core.IsNull;


public class TrackerTest {

    @Test
    public void whenFindAllItemThenTrackerHasnotNull() {
        Tracker tracker = new Tracker();
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
        Item[] forTest = new Item[tracker.getPosition()];
        Item[] test = new Item[tracker.getPosition()];
        test = tracker.findAll();
        forTest[0] = item1;
        forTest[1] = item2;
        forTest[2] = item3;
        forTest[3] = item4;
        forTest[4] = item5;
        assertArrayEquals(test, forTest);

    }

    @Test
    public void whenfindByNameItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
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
        Item[] test = new Item[2];
        test = tracker.findByName("test4");
        Item[] forTest = new Item[2];
        forTest[0] = item4;
        forTest[1] = item5;
        assertThat(test, is(forTest));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenItemNotExistThenNull() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        tracker.delete(item.getId());
        Item result = tracker.findById(item.getId());
        assertThat(result, (is(IsNull.nullValue())));
    }
    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }
    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}