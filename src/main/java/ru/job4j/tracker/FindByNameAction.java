package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Name ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter name: ");
        List<Item> allItem = new ArrayList<>();
        List<Item> comeName = tracker.findByName(name);
        for (Item element : comeName) {
            allItem.add(element);
        }
        for (Item element : allItem) {
            System.out.println(element.getId() + " " + element.getName());
        }
        return true;
    }
}
