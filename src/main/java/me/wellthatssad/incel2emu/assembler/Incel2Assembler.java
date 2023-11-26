package me.wellthatssad.incel2emu.assembler;

import me.wellthatssad.incel2emu.assembler.compiler.Compiler;
import me.wellthatssad.incel2emu.assembler.compiler.Parser;
import me.wellthatssad.incel2emu.assembler.compiler.PreProcessor;
import me.wellthatssad.incel2emu.assembler.data.AssemblerPhase;
import me.wellthatssad.incel2emu.assembler.data.Token;
import me.wellthatssad.incel2emu.utils.FileUtils;
import me.wellthatssad.incel2emu.utils.InstBuffer;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class Incel2Assembler implements Runnable{
    public final String fileSrc;
    public final String destination;
    public AssemblerPhase phase;
    public Incel2Assembler(String src, String destination){
        fileSrc = src;
        this.destination = destination;
    }

    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            String data = FileUtils.readAsciiFile(fileSrc);
            if (Objects.isNull(data))
                throw new FileNotFoundException();
            phase = AssemblerPhase.PRE_PROCESSOR_;
            List<String[]> source = new PreProcessor(data).PreProcess();
            phase = AssemblerPhase.PARSER;
            List<Token> tokenList = new Parser(source).parse();
            phase = AssemblerPhase.COMPILER;
            InstBuffer buff = new Compiler(tokenList).compile();
            //FileUtils.writeBinaryFile(destination, buff);
        } catch (FileNotFoundException e) {
            System.out.println("Incel2 Assembler has ran into an error: Could not open file \"" + fileSrc + "\"" );
        }
    }
}
