package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import org.hamcrest.core.IsNull;

public class StartUITest {
    /**
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC", "Fix PC2", "Fix PC3" };
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        StartUI.createItem(input, tracker);
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[2];
        Item expected = new Item("Fix PC3");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                "replaced item",
                item.getId() // id сохраненной заявки в объект tracker.
        };
        Input input = new StubInput(answers);
        StartUI.replaceItem(input, tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {

                item.getId() // id сохраненной заявки в объект tracker.
        };
        Input input = new StubInput(answers);
        StartUI.deteleItem(input, tracker);
        Item result = tracker.findById(item.getId());
        assertThat(result, (is(IsNull.nullValue())));
    }
    **/
}