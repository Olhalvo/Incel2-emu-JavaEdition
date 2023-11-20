package me.wellthatssad.incel2emu.emu;


import java.util.Stack;

public class Incel2 implements Runnable {
    public final static short OPCODE_MASK = (short) 0b1111100000000000;
    public final static short DEST_MASK = (short) 0b0000011100000000;
    public final static short SRC1_MASK = (short) 0b0000000000111000;
    public final static short SRC2_MASK = (short) 0b0000000000000111;
    public final static short FLAG_MASK = (short) 0b0000011000000000;
    public final static short IMM_MASK = (short) 0b0000000011111111;
    public final static short V_MASK = (short) 0b0000000100000000;
    public final static short P_MASK = (short) 0b0000010000000000;

    //defining stuff
    private final int REG_COUNT = 8;
    private final int FLAG_COUNT = 4;
    private final int PORT_COUNT = 7;
    private final int RAM_SIZE = 64;
    private final int ROM_SIZE = 256;
    private final int STACK_SIZE = 5;

    //mem
    private byte registers[] = new byte[REG_COUNT];
    private boolean flags[] = new boolean[FLAG_COUNT];
    private int portsI[] = new int[PORT_COUNT];
    private int portsO[] = new int[PORT_COUNT];
    private byte ram[] = new byte[RAM_SIZE];
    private short prom[] = new short[ROM_SIZE];
    private int IP = 0;
    private int callStack[] = new int[STACK_SIZE];
    private int SP = 0;

    //decoding regs
    private short currInst;
    private int dst;
    private int src1;
    private int src2;



    @Override
    public void run() {

    }
}
