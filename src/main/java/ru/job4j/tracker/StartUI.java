package ru.job4j.tracker;

public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showallitems(Input input, Tracker tracker) {
        System.out.println("=== Show all items ====");
        Item[] allItem = tracker.findAll();
        for (int i = 0; i < allItem.length; i++) {
            System.out.println(i + ". " + allItem[i].getName());
        }
    }

    public static void findItembyName(Input input, Tracker tracker) {
        System.out.println("=== Find Item by Name ====");
        String name = input.askStr("Enter name: ");
        Item[] allItem = tracker.findByName(name);
        for (int i = 0; i < allItem.length; i++) {
            System.out.println(i + ". " + allItem[i].getName());
        }
    }

    public static void findItembyId(Input input, Tracker tracker) {
        System.out.println("=== Find Item by Id ====");
        String name = input.askStr("Enter id: ");
        Item allItem = tracker.findById(name);
        if (allItem == null) {
            System.out.print("Item not exist.");
        }
        if (allItem != null) {
            System.out.println(allItem.getName());
        }
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Replace Item ====");
        String name = input.askStr("Enter a new name of item: ");
        String id = input.askStr("Enter id: ");
        Item newItem = new Item(name);
        newItem.setId(id);
        if (tracker.replace(id, newItem)) {
            System.out.println("Replace");
        } else {
            System.out.println("NOT Replace");
        }
    }

    public static void deteleItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ====");
        System.out.print("Enter id: ");
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Delete");
        } else {
            System.out.println("NOT Delete");
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                createItem(input, tracker);
            } else if (select == 1) {
                showallitems(input, tracker);
            } else if (select == 2) {
                findItembyName(input, tracker);
            } else if (select == 3) {
                findItembyId(input, tracker);
            } else if (select == 4) {
                replaceItem(input, tracker);
            } else if (select == 5) {
                deteleItem(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }
    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit item.");
        System.out.println("3. Delete item.");
        System.out.println("4. Find item by Id.");
        System.out.println("5. Find items by name.");
        System.out.println("6. Exit Program.");

    }
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
