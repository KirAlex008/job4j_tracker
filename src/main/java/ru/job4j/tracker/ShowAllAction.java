package ru.job4j.tracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] allItem = tracker.findAll();
        for (int i = 0; i < allItem.length; i++) {
            System.out.println(i + ". " + allItem[i].getName());
        }
        return true;
    }
}
