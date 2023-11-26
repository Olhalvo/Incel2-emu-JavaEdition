package me.wellthatssad.incel2emu.assembler.compiler;

import me.wellthatssad.incel2emu.assembler.data.Token;
import me.wellthatssad.incel2emu.assembler.data.TokenType;
import me.wellthatssad.incel2emu.utils.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Parser {
    public final List<String[]> source;
    public Parser(List<String[]> src){
        this.source = src;
    }

    public Operations getOperation(String s){
        switch (s){
            case "NOOP":
                return Operations.NOOP;
            case "ADD":
                return Operations.ADD;
            case "SUB":
                return Operations.SUB;
            case "AND":
                return Operations.AND;
            case "OR":
                return Operations.OR;
            case "ADC":
                return Operations.ADC;
            case "RSH":
                return Operations.RSH;
            case "ADI":
                return Operations.ADI;
            case "ANDI":
                return Operations.ANDI;
            case "XORI":
                return Operations.XORI;
            case "LDI":
                return Operations.LDI;
            case "MST":
                return Operations.MST;
            case "MLD":
                return Operations.MLD;
            case "PST":
                return Operations.PST;
            case "PLD":
                return Operations.PLD;
            case "BRC":
                return Operations.BRC;
            case "JMP":
                return Operations.JMP;
            case "JAL":
                return Operations.JAL;
            case "HLT":
                return Operations.HLT;
            default:
                return null;
        }
    }

    public List<Token> parse(){
        List<Token> tokenList = new ArrayList<>();
        int lineNum = 1;
        for(String[] line : source){
            boolean ignore = false;
            for(String current: line){
                Token temp;
                if(current.startsWith("//") || current.startsWith(">")){
                    ignore = true;
                    break;
                }
                Operations op = getOperation(current);
                if(Objects.nonNull(op)){
                    temp = new Token(TokenType.OPERATION, op.getBytecode(), lineNum);
                    tokenList.add(temp);
                    continue;
                }
                int val;
                switch (current.toCharArray()[0]){
                    case 'P':
                        if(current.toCharArray()[1] == '='){
                            val = Integer.parseInt(current.replace("P=", ""));
                            temp = new Token(TokenType.BOOLEAN, val, lineNum);
                            tokenList.add(temp);
                            break;
                        }
                        val = Integer.parseInt(current.replace("P", ""));
                        temp = new Token(TokenType.PORT, val, lineNum);
                        tokenList.add(temp);
                        break;
                    case 'R':
                        val = Integer.parseInt(current.replace("R", ""));
                        temp = new Token(TokenType.REGISTER, val, lineNum);
                        tokenList.add(temp);
                        break;
                    case 'V':
                        val = Integer.parseInt(current.replace("V", ""));
                        temp = new Token(TokenType.BOOLEAN, val, lineNum);
                        tokenList.add(temp);
                        break;
                    case 'F':
                        val = Integer.parseInt(current.replace("F", ""));
                        temp = new Token(TokenType.FLAG, val, lineNum);
                        tokenList.add(temp);
                        break;
                    case '$':
                        if(current.toCharArray()[1] == '0'){
                            if(current.toCharArray().length >= 3 && current.toCharArray()[2] == 'x'){
                                val = Integer.parseInt(current.replace("$0x", ""), 16);
                                temp = new Token(TokenType.IMMEDIATE, val, lineNum);
                                tokenList.add(temp);
                                break;
                            }
                            if(current.toCharArray().length >= 3 && current.toCharArray()[2] == 'b'){
                                val = Integer.parseInt(current.replace("$0b", ""), 2);
                                temp = new Token(TokenType.IMMEDIATE, val, lineNum);
                                tokenList.add(temp);
                                break;
                            }
                        }
                        val = Integer.parseInt(current.replace("$", ""));
                        if (val < 0) {
                            val = 256 + val;
                        }
                        temp = new Token(TokenType.IMMEDIATE, val, lineNum);
                        tokenList.add(temp);
                        break;
                    default:
                        System.err.println("SYNTAX ERROR: Invalid token at line " + lineNum + "\ntoken: " + current);
                        return null;
                }
            }
            if(!ignore)
                lineNum++;
        }
        for(int i = 1; i <= lineNum; i++){
            final int lineCheck = i;
            List<Token> line = tokenList.stream().filter(token -> {return token.line() == lineCheck ? true:false;}).collect(Collectors.toList());
            System.out.print("line " + i + ":");
            for(Token tok : line){
                System.out.print(tok.toString() + " ");
            }
            System.out.print('\n');
        }
        return tokenList;
    }
}
