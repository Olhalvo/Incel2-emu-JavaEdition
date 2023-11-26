package me.wellthatssad.incel2emu.utils;

import java.util.ArrayList;
import java.util.List;

public class InstBuffer {
    public List<Integer> buff = new ArrayList<>();

    public void add(int add){
        if(add > 65535){
            System.out.println("Instruction \"" + Integer.toBinaryString(add) + "\" " + "is greater than the unsigned 16 bit limit");
            return;
        }
        buff.add(add);
        if(buff.size() >= 256){
            buff.remove(buff.size() -1);
            System.out.println("Instruction buffer bigger than rom size, removing latest added instruction :3");
        }
    }
}
