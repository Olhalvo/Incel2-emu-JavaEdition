package me.wellthatssad.incel2emu.emu;


import me.wellthatssad.incel2emu.utils.InstBuffer;
import me.wellthatssad.incel2emu.utils.OpcodeConverter;
import me.wellthatssad.incel2emu.utils.Operations;

import java.util.Stack;

public class Incel2 implements Runnable {
    //I would use shorts but Java is a bitch(it does not have unsigned integer types)
    public final static int OPCODE_MASK = 0b1111100000000000;
    public final static int DEST_MASK = 0b0000011100000000;
    public final static int SRC1_MASK = 0b0000000000111000;
    public final static int SRC2_MASK = 0b0000000000000111;
    public final static int FLAG_MASK = 0b0000011000000000;
    public final static int IMM_MASK = 0b0000000011111111;
    public final static int V_MASK = 0b0000000100000000;
    public final static int P_MASK = (short) 0b0000010000000000;

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
    private int prom[] = new int[ROM_SIZE];
    private int IP = 0;
    private int callStack[] = new int[STACK_SIZE];
    private int SP = 0;
    private boolean running = false;
    private int exitCode = 0;

    //decoding regs
    private int currInst;
    private Operations opcode;
    private int dst;
    private int src1;
    private int src2;
    private int flag;
    private int imm;
    private int v;
    private int p;

    public Incel2(){
        IP = 0;
    }

    private void fetch(){
        if(IP >= ROM_SIZE){
            exitCode = -1;
            System.out.println("Error during fetching: reached maximum rom size before halt instruction");
            halt();
        }
        currInst = prom[IP++];
    }

    private void decode(){
        if(!running){
            return;
        }
            opcode = OpcodeConverter.convert((currInst & OPCODE_MASK) >> 11);
            dst = ((currInst & DEST_MASK) >> 8);
            src1 = ((currInst & SRC1_MASK) >> 3);
            src2 = ((currInst & SRC2_MASK));
            flag = ((currInst & FLAG_MASK) >> 9);
            imm = ((currInst & IMM_MASK));
            v = ((currInst & V_MASK) >> 8);
            p = ((currInst & P_MASK) >> 10);
            if(opcode == null){
                exitCode = -1;
                halt();
            }

    }

    private void execute(){
        if(!running){
            return;
        }
        switch(opcode){
            case NOOP:
                System.out.println("NOOP");
                break;
            case ADD:
                System.out.println("ADD");
                break;
            case SUB:
                System.out.println("SUB");
                break;
            case AND:
                System.out.println("AND");
                break;
            case OR:
                System.out.println("OR");
                break;
            case ADC:
                System.out.println("ADC");
                break;
            case RSH:
                System.out.println("RCH");
                break;
            case ADI:
                System.out.println("ADI");
                break;
            case ANDI:
                System.out.println("ANDI");
                break;
            case XORI:
                System.out.println("XORI");
                break;
            case LDI:
                System.out.println("LDI");
                break;
            case MST:
                System.out.println("MST");
                break;
            case MLD:
                System.out.println("MLD");
                break;
            case PST:
                System.out.println("PST");
                break;
            case PLD:
                System.out.println("PLD");
                break;
            case BRC:
                System.out.println("BRC");
                break;
            case JMP:
                System.out.println("JMP");
                break;
            case JAL:
                System.out.println("JAL");
                break;
            case HLT:
                System.out.println("HLT");
                exitCode = 0;
                halt();
                break;
        }
    }

    public void start(){
        running = true;
        run();
    }
    public void halt(){
        System.out.println("Incel2 halted with exit code: " + exitCode);
        running = false;
    }
    @Override
    public void run() {
        while(running){
            fetch();
            decode();
            execute();
            displayRam();
        }
    }

    public void displayRam(){
        for(int i = 48; i < RAM_SIZE; i++){
            System.out.println("Adress " + i + ": " +  ram[i]);
        }
    }
    public void loadProgram(InstBuffer buff){
        if(buff.buff.size() >= ROM_SIZE){
            System.out.println("Invalid program: program size is bigger than ROM size :(");
            return;
        }
        IP = 0;
        for(int i = 0; i < buff.buff.size(); i++){
            prom[i] = buff.buff.get(i);
        }
    }
}
