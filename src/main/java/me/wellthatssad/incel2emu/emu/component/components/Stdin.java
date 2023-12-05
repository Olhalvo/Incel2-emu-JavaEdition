package me.wellthatssad.incel2emu.emu.component.components;

import me.wellthatssad.incel2emu.emu.component.PortComponent;

import java.util.Scanner;

public class Stdin extends PortComponent{
    private Scanner sc = new Scanner(System.in);

    public Stdin() {
        super(true, "stdin");
    }

    @Override
    public int read() {
        return read("Input required: ");
    }

    @Override
    public int read(String message) {
        System.out.print(message);
        value = sc.nextInt();
        return value;
    }

    @Override
    public int write(int val) {
        System.out.println("Component \"" + this.name + "\" is read only");
        return 0;
    }
}
