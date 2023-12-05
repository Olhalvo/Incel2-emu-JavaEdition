package me.wellthatssad.incel2emu.emu.component.components;

import me.wellthatssad.incel2emu.emu.component.PortComponent;

import java.util.Random;

public class RandomNumGen extends PortComponent {
    private Random random;

    public RandomNumGen(int seed) {
        super(true, "RandomNumberGenerator");
        random = new Random(seed);
    }

    @Override
    public int read() {
        this.value = random.nextInt();
        return this.value;
    }

    @Override
    public int read(String message) {
        return read();
    }

    @Override
    public int write(int val) {
        System.out.println("Component \"" + this.name + "\" is read only");
        return  0;
    }
}
