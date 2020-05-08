package ru.job4j.tracker;

public class ValidateStubInput extends ValidateInput {
    private String[] data;
    private int position;

    public ValidateStubInput(String[] data) {
        this.data = data;
    }

    @Override
    public String askStr(String question) {
        //System.out.println(data[position]);
        //position++;
        return data[position++];
    }

    @Override
    public int askInt(String question, int max) {
        return super.askInt(question, max);
    }
}