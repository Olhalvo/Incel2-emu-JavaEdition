package me.wellthatssad.incel2emu;

import me.wellthatssad.incel2emu.emu.Incel2;
import me.wellthatssad.incel2emu.utils.InstBuffer;

public class Main {
    public static void main(String[] args) {
        InstBuffer buff = new InstBuffer();
        buff.add(0b0000101000110010);
        buff.add(0b0001001000010010);
        buff.add(0b1111100000000000);
        Incel2 incel2 = new Incel2();
        incel2.loadProgram(buff);
        incel2.start();
    }
}