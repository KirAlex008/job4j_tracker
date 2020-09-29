package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {

/*    public void init(Input input, MemTracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", 7);
       Sta     UserAction action = actions.get(select);
            run = action.execute(input, tracker);

        }*/

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", 7);
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);

        }
    }
    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu.");
        int index = 0;
        for (UserAction action : actions) {
            System.out.println((index + ". " + action.name()));
            index++;
        }


    }
/*    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction());
        actions.add(new FindAllAction());
        actions.add(new ReplaceAction());
        actions.add(new FindByNameAction());
        actions.add(new FindbyIdAction());
        actions.add(new DeteleAction());
        actions.add(new ExitAction());
        new StartUI().init(validate, tracker, actions);*/

/*    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput());
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction());
            actions.add(new FindAllAction());
            actions.add(new ReplaceAction());
            actions.add(new FindByNameAction());
            actions.add(new FindbyIdAction());
            actions.add(new DeteleAction());
            actions.add(new ExitAction());

            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
