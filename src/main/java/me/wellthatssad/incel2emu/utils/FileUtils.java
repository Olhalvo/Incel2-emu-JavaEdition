package me.wellthatssad.incel2emu.utils;

import java.io.*;

public class FileUtils {
    public static InstBuffer readBinaryFile(String path){
        File file = new File(path);
        InstBuffer data = new InstBuffer();
        try{
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            while(true){
                data.add(dis.readShort());
            }
        }
        catch (EOFException e){
            return data;
        }
        catch (IOException e) {
            System.err.println("Could not open or create file at: " + path);
            return null;
        }
    }
}
