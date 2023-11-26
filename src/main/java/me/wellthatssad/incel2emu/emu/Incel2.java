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
    private final int STACK_SIZE = 10;
    private final int BIT_COUNT = 8;
    private final int RAM_INDEX_REG = 7;

    //mem
    private final int[] registers = new int[REG_COUNT];
    private final boolean[] flags = new boolean[FLAG_COUNT];
    private final int[] portsI = new int[PORT_COUNT];
    private final int[] portsO = new int[PORT_COUNT];
    private final int[] ram = new int[RAM_SIZE];
    private final int[] prom = new int[ROM_SIZE];
    private int IP = 0;
    private final int[] callStack = new int[STACK_SIZE];
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
    private boolean v;
    private boolean p;

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
            v =  (((currInst & V_MASK) >> 8) >= 1);
            p = (((currInst & P_MASK) >> 10) >= 1);
            if(opcode == null){
                exitCode = -1;
                halt();
            }

    }

    private void execute(){
        boolean updateFlags = false;
        if(!running){
            return;
        }
        switch(opcode){
            case NOOP:
                break;
            case ADD:
                registers[dst] = (registers[src1] + registers[src2]) % (int) Math.pow(2, BIT_COUNT);
                if((registers[src1] + registers[src2]) > 255)
                    flags[1] = true;
                else
                    flags[1] = false;
                updateFlags = true;
                break;
            case SUB:
                registers[dst] = (registers[src1] - registers[src2]) % (int) Math.pow(2, BIT_COUNT);
                updateFlags = true;
                break;
            case AND:
                registers[dst] = (registers[src1] & registers[src2] );
                updateFlags = true;
                break;
            case OR:
                registers[dst] = (registers[src1] | registers[src2]);
                updateFlags = true;
                break;
            case ADC:
                if(flags[1]){
                    registers[dst] = (registers[src1] + registers[src2] + 1) % (int) Math.pow(2, BIT_COUNT);
                    if((registers[src1] + registers[src2] + 1) > 255)
                        flags[1] = true;
                    else
                        flags[1] = false;
                }
                else {
                    registers[dst] = (registers[src1] + registers[src2]) % (int) Math.pow(2, BIT_COUNT);
                    if ((registers[src1] + registers[src2]) > 255)
                        flags[1] = true;
                    else
                        flags[1] = false;

                }
                updateFlags = true;
                break;
            case RSH:
                registers[dst] = registers[src1] >>1;
                break;
            case ADI:
                int temp = registers[dst];
                registers[dst] = (registers[dst] + imm) % (int) Math.pow(2, BIT_COUNT);
                if(temp + imm > 255)
                    flags[1] = true;
                else
                    flags[1] = false;
                updateFlags = true;
                break;
            case ANDI:
                registers[dst] &= imm;
                updateFlags = true;
                break;
            case XORI:
                registers[dst] ^= imm;
                updateFlags = true;
                break;
            case LDI:
                registers[dst] = imm;
                break;
            case MST:
                ram[registers[RAM_INDEX_REG]] = registers[src1];
                break;
            case MLD:
                registers[dst] = ram[registers[RAM_INDEX_REG]];
                break;
            case PST:
                //ILL do ports later :3
                break;
            case PLD:
                //Ill do ports later :33
                break;
            case BRC:
                if(flags[flag] == v){
                    IP = imm;
                }
                break;
            case JMP:
                if(p){
                    IP = callStack[SP--];
                    break;
                }
                IP = imm;
                break;
            case JAL:
                callStack[SP++] = ++IP;
                IP = imm;
                break;
            case HLT:
                exitCode = 0;
                halt();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcode);
        }
        if(updateFlags){
            flags[0] = (registers[dst] > 127);
            flags[2] = (registers[dst] == 0);
            flags[3] = ((registers[dst] % 2) == 1);
        }
    }

    public void start(){
        running = true;
        new Thread(this).start();
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
            System.out.println("Address " + i + ": " +  ram[i]);
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
