package me.wellthatssad.incel2emu.utils;

public enum Operations {
    NOOP(0b00000),
    ADD(0b00001),
    SUB(0b00010),
    AND(0b00011),
    OR(0b00100),
    ADC(0b00101),
    RSH(0b00110),
    ADI(0b00111),
    ANDI(0b01000),
    XORI(0b01001),
    LDI(0b01010),
    MST(0b01011),
    MLD(0b01100),
    PST(0b01101),
    PLD(0b01110),
    BRC(0b01111),
    JMP(0b10000),
    JAL(0b10001),
    HLT(0b11111);

    public int getBytecode() {
        return bytecode;
    }

    public int bytecode;
    Operations(int bytecode){
        this.bytecode = bytecode;
    }
}
