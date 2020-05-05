package ru.job4j.tracker;

public class DeteleAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.print("Enter id: ");
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Delete");
        } else {
            System.out.println("NOT Delete");
        }
        return true;
    }
}
