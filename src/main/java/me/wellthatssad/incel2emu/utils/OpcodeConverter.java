package me.wellthatssad.incel2emu.utils;

import me.wellthatssad.incel2emu.emu.Incel2;

public class OpcodeConverter {
    public static Operations convert(int opcode){
        switch (opcode){
            case 0b0:
                return Operations.NOOP;
            case 0b1:
                return Operations.ADD;
            case 0b10:
                return Operations.SUB;
            case 0b11:
                return Operations.AND;
            case 0b100:
                return Operations.OR;
            case 0b101:
                return Operations.ADC;
            case 0b110:
                return Operations.RSH;
            case 0b111:
                return Operations.ADI;
            case 0b1000:
                return Operations.ANDI;
            case 0b1001:
                return Operations.XORI;
            case 0b1010:
                return Operations.LDI;
            case 0b1011:
                return Operations.MST;
            case 0b1100:
                return Operations.MLD;
            case 0b1101:
                return Operations.PST;
            case 0b1110:
                return Operations.PLD;
            case 0b1111:
                return Operations.BRC;
            case 0b10000:
                return Operations.JMP;
            case 0b10001:
                return Operations.JAL;
            case 0b11111:
                return Operations.HLT;
            default:
                System.out.println("Invalid operation: " + Integer.toBinaryString(opcode));
                return null;
        }
    }
}
