package me.wellthatssad.incel2emu;

import me.wellthatssad.incel2emu.emu.Incel2;
import me.wellthatssad.incel2emu.utils.InstBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InstBuffer buff = new InstBuffer();
        buff.add(0b0101000101111111);
        buff.add(0b0101001000000001);
        buff.add(0b0000101100001010);
        buff.add(0b0101011100110000);
        buff.add(0b0101100000011000);
        buff.add(0b1111100000000000);
        Incel2 incel2 = new Incel2();
        incel2.loadProgram(buff);
        incel2.start();
    }
}