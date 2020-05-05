package ru.job4j.tracker;

public class FindbyNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        Item[] allItem = tracker.findByName(name);
        for (int i = 0; i < allItem.length; i++) {
            System.out.println(i + ". " + allItem[i].getName());
        }
        return true;
    }
}
