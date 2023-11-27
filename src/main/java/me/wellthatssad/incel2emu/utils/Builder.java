package me.wellthatssad.incel2emu.utils;

import me.wellthatssad.incel2emu.emu.Incel2;

import java.util.ArrayList;
import java.util.Arrays;

public class Builder {

    public enum BuildType {
        OP("(OPCODE)"),
        OP_DST("(OPCODE, DEST)"),
        OP_SRC1("(OPCODE, SRC1)"),
        OP_IMM("(OPCODE, IMM)"),
        OP_DST_SRC1("(OPCODE, DEST, SRC1)"),
        OP_DST_SRC2("(OPCODE, DEST, PORT<src2>)"),
        OP_DST_IMM("(OPCODE, DEST, IMM)"),
        OP_DST_SRC1_SRC2("(OPCODE, DEST, SRC1, SRC2)"),
        BRC("(BRC<OPCODE>, FLAG, VALUE, IMM)"),
        JMP("(JMP<OPCODE>, POP_BOOL, IMM)");

        String label;

        BuildType(String s) {
            this.label = s;
        }
    }

    /**
     * @author Well_thatssad
     * I just wanted to quote me before deleting the previous builder class:
     * "I could do this with a single function and an enum, and it probably would be better and faster.
     *  However, I am stupid not a masochist, I don't really wanna rewrite this entire stuff...".
     *
     *  AND THEN I PROCEEDED TO FUCKING DELETE THE ENTIRE BUILDER CLASS THAT HAD LIKE 8 FUNCTIONS WITH THE SOLE
     *  PURPOSE OF BUILDING INSTRUCTIONS
     *  I AM STUPID AND A MASOCHIST.
     */
    public static int BuildInstruction(BuildType type, int... args){
        int op = 65536;
        switch (type) {
            //If I had a penny for every single switch statement (with enums) I have used in all my projects I would
            //have many more than I would like to admit :3
            case OP:
                if(!(args.length == 1)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = args[0] << 11;
                break;
            case OP_DST:
                if(!(args.length == 2)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 8));
                break;
            case OP_SRC1:
                if(!(args.length == 2)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 3));
                break;
            case OP_IMM:
                if(!(args.length == 2)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | args[1]);
                break;
            case OP_DST_SRC1:
                if(!(args.length == 3)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 8) | (args[2] << 3));
                break;
            case OP_DST_SRC2:
                if(!(args.length == 3)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 8) | args[2]);
                break;
            case OP_DST_IMM:
                if(!(args.length == 3)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 8) | args[2]);
                break;
            case OP_DST_SRC1_SRC2:
                if(!(args.length == 4)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] <<  8) | (args[2] << 3) | args[3]);
                break;
            case BRC:
                if(!(args.length == 4)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 9) | (args[2] << 8) | args[3]);
                break;
            case JMP:
                if(!(args.length == 3)){
                    System.err.println("Too many or too little arguments for build type: \"" + type.label + "\". Arg count: " + args.length);
                    break;
                }
                op = ((args[0] << 11) | (args[1] << 10) | args[2]);
                break;
            default:
                System.err.println("Invalid Build Type :(");
                break;
        }
        return op;
    }
}
