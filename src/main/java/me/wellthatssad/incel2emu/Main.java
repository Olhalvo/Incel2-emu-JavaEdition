package me.wellthatssad.incel2emu;

import me.wellthatssad.incel2emu.assembler.Incel2Assembler;
import me.wellthatssad.incel2emu.emu.Incel2;
import me.wellthatssad.incel2emu.utils.Builder;
import me.wellthatssad.incel2emu.utils.FileUtils;
import me.wellthatssad.incel2emu.utils.InstBuffer;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Incel2Assembler assembler = new Incel2Assembler("random.txt", "random.i2b");
        //assembler.start();
        Incel2 incel2 = new Incel2();
        incel2.loadProgram(Objects.requireNonNull(FileUtils.readBinaryFile("random.i2b")));
        incel2.start();
    }
}