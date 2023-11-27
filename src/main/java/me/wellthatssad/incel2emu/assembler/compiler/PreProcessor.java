package me.wellthatssad.incel2emu.assembler.compiler;

import java.lang.reflect.Array;
import java.util.*;

public class PreProcessor {
    private final Map<String, Integer> labelMap = new HashMap<>();
    private final String source;

    public PreProcessor(String src){
        source = src;
    }

    public List<String[]> getLabels(List<String[]> inst){
        int lineIndex = 0;
        for(String[] line : inst){
            if(line[0].startsWith("//") && line[0].startsWith(">"))
                break;
            if(line[0].startsWith("[")){
                String label = line[0].replaceAll("\\[", "").replaceAll("]", "");
                labelMap.put(label,inst.indexOf(line));
                String[] newLine = new String[line.length -1];
                for(int i = 1; i < line.length; i++){
                    newLine[i-1] = line[i];
                }
                inst.set(lineIndex, newLine);
            }
            lineIndex++;
        }
        return inst;
    }

    public List<String[]> PreProcess(){
        String[] temp = source.split("\n");
        List<String[]> list = new ArrayList<>();
        for(int i = 0; i < temp.length; i++){
            temp[i] = temp[i].trim().replaceAll(" +", " ");
            list.add(i, temp[i].split(" "));
        }
        list = getLabels(list);
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).length; j++) {
                String str = list.get(i)[j];
                if (str.startsWith("$") && str.toCharArray()[1] == '[') {
                    String label = str.replaceAll("\\$", "").replaceAll("\\[", "").replaceAll("]", "");
                    if(!(labelMap.containsKey(label))){
                        System.err.println("invalid label: " + label);
                    }
                    list.get(i)[j] = "$" + labelMap.get(label).intValue();
                }
            }
        }

        return list;
    }

}
