package me.wellthatssad.incel2emu.emu.component.components;

import me.wellthatssad.incel2emu.emu.component.PortComponent;

public class Stdout extends PortComponent {

    public Stdout() {
        super(false, "stdout");
    }

    @Override
    public int read() {
        System.out.println("You can't read Stdout");
        return 0;
    }

    @Override
    public int read(String message) {
        return read();
    }

    @Override
    public int write(int val) {
        value = val % (int) Math.pow(2, 8);
        System.out.println((char) value);
        return 1;
    }
}
