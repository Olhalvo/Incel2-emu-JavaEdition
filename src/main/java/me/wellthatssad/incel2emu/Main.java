package me.wellthatssad.incel2emu;

import me.wellthatssad.incel2emu.assembler.Incel2Assembler;
import me.wellthatssad.incel2emu.utils.InstBuffer;

public class Main {
    public static void main(String[] args) {
        /*InstBuffer buff = new InstBuffer();
        buff.add(0b0101000101111111);
        buff.add(0b0101001000000001);
        buff.add(0b0000101100001010);
        buff.add(0b0101011100110000);
        buff.add(0b0101100000011000);
        buff.add(0b1111100000000000);
        Incel2 incel2 = new Incel2();
        incel2.loadProgram(buff);
        incel2.start();*/
        Incel2Assembler assembler = new Incel2Assembler("random.txt", "random.i2b");
        InstBuffer buff = new InstBuffer();
        assembler.start();
    }
}