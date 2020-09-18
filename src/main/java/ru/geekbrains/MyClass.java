package ru.geekbrains;

public class MyClass {
    private String name;

    public MyClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "myClass name is " + name + '.';
    }

}
