package me.wellthatssad.incel2emu.assembler.exceptions;

import me.wellthatssad.incel2emu.assembler.data.AssemblerPhase;

public class AssemblerException extends Exception {
    public AssemblerPhase phase;

    public AssemblerException(AssemblerPhase phase){
        super("Assembler reached an Exception at phase: ");
        this.phase = phase;
    }
}
