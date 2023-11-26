package me.wellthatssad.incel2emu.assembler.utils;

import me.wellthatssad.incel2emu.emu.Incel2;

public class InstructionBuilder {
    public static int buildOp(int opcode){
        return ((opcode & Incel2.OPCODE_MASK) << 11);
    }
    public static int buildOpDest(int opcode, int dest){
        return (((opcode & Incel2.OPCODE_MASK) << 11) | ((dest & Incel2.DEST_MASK)<<8));
    }

    public static int buildOpSrc(int opcode, int dest){
        return (((opcode & Incel2.OPCODE_MASK) << 11) | ((dest & Incel2.DEST_MASK)<<8));
    }

    public static int buildOpDstImm(int opcode, int dst, int Imm){
        return (((opcode & Incel2.OPCODE_MASK) << 11)) | ((dst& Incel2.DEST_MASK) << 8) | (Imm & Incel2.IMM_MASK);
    }

    public static int buildOpDestSrc1Src2(int opcode,int dest,int src1,int src2){
        return (((opcode & Incel2.OPCODE_MASK) << 11) | ((dest & Incel2.DEST_MASK) << 8) | ((src1 & Incel2.SRC1_MASK) << 3) | (src2 & Incel2.SRC2_MASK));
    }

}
