package ru.job4j.tracker;

public class FindbyIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter id: ");
        Item allItem = tracker.findById(name);
        if (allItem == null) {
            System.out.print("Item not exist.");
        }
        if (allItem != null) {
            System.out.println(allItem.getName());
        }
        return true;
    }
}
