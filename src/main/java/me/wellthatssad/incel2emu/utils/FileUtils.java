package me.wellthatssad.incel2emu.utils;

import java.io.*;

public class FileUtils {

    public static String readAsciiFile(String path){
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(path);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            int ch;
            while ((ch = br.read()) != -1){
                stringBuilder.append((char) ch);
            }
        }catch (EOFException e) {
            return stringBuilder.toString();
        }catch (IOException e){
            System.out.println("Could not open or create file at: " + path);
            return null;
        }
        return stringBuilder.toString();
    }

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
    public static void writeBinaryFile(String path, InstBuffer data){
        File file = new File(path);
        try(DataOutputStream dis = new DataOutputStream(new FileOutputStream(file))) {
            for(Integer i : data.buff){
                dis.writeShort(i);
            }
        }
        catch(IOException e){
            System.err.println("Could not open or create file: " + path);
            e.printStackTrace();
        }
    }
}
