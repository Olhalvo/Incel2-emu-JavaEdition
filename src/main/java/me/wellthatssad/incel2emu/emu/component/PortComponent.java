package me.wellthatssad.incel2emu.emu.component;

import java.nio.file.LinkPermission;

public abstract class PortComponent {
    protected int value;
    protected boolean input;
    protected String name;

    public PortComponent(boolean input, String name){
        this.input = input;
        this.name = name;
        value = 0;
    }

    public abstract int read();

    public abstract int read(String message);

    public abstract int write(int val);
    public boolean isInput(){
        return input;
    }
}
