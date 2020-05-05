package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Replace Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter a new name of item: ");
        String id = input.askStr("Enter id: ");
        Item newItem = new Item(name);
        newItem.setId(id);
        if (tracker.replace(id, newItem)) {
            System.out.println("Replace");
        } else {
            System.out.println("NOT Replace");
        }
        return true;
    }
}
