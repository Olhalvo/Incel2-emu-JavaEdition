package me.wellthatssad.incel2emu.assembler.compiler;

import me.wellthatssad.incel2emu.assembler.data.Token;
import me.wellthatssad.incel2emu.assembler.data.TokenType;
import me.wellthatssad.incel2emu.utils.Builder;
import me.wellthatssad.incel2emu.utils.InstBuffer;

import java.util.List;

public class Compiler {
    public final List<Token> tokenList;

    public Compiler(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public InstBuffer compile() {
        InstBuffer buffer = new InstBuffer();
        List<Token> operations = tokenList.stream().filter(token -> token.type() == TokenType.OPERATION).toList();
        try {
            int prevLine = 0;
            for (Token operation : operations) {
                if (operation.line() == prevLine) {
                    throw new RuntimeException("Syntax Error at line " + operation.line() + ": Only one operation per line");
                }
                List<Token> line = tokenList.stream().filter(token -> token.line() == operation.line()).toList();
                switch (operation.data()) {
                    /*
                        Will add proper checks for like Token Types and whatnot, atm I assume the programmer knows
                        what he is doing :3
                     */
                    case 0b00000:
                        break;
                    case 0b00001:
                        if (line.size() != 4) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted ADD instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC1_SRC2, operation.data(), line.get(1).data(), line.get(2).data(), line.get(3).data()));
                        break;
                    case 0b00010:
                        if (line.size() != 4) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted SUB instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC1_SRC2, operation.data(), line.get(1).data(), line.get(2).data(), line.get(3).data()));
                        break;
                    case 0b00011:
                        if (line.size() != 4) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted AND instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC1_SRC2, operation.data(), line.get(1).data(), line.get(2).data(), line.get(3).data()));
                        break;
                    case 0b00100:
                        if (line.size() != 4) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted OR instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC1_SRC2, operation.data(), line.get(1).data(), line.get(2).data(), line.get(3).data()));
                        break;
                    case 0b00101:
                        if (line.size() != 4) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted XOR instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC1_SRC2, operation.data(), line.get(1).data(), line.get(2).data(), line.get(3).data()));
                        break;
                    case 0b00110:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted RSH instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC1, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b00111:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted ADI instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_IMM, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b01000:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted ANDI instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_IMM, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b01001:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted XORI instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_IMM, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b01010:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted LDI instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_IMM, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b01011:
                        if (line.size() != 2) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted MST instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_SRC1, operation.data(), line.get(1).data()));
                        break;
                    case 0b01100:
                        if (line.size() != 2) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted MLD instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST, operation.data(), line.get(1).data()));
                        break;
                    case 0b01101:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted PST instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC2, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b01110:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted PLD instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_DST_SRC2, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b01111:
                        if (line.size() != 4) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted BRC instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.BRC, operation.data(), line.get(1).data(), line.get(2).data(), line.get(3).data()));
                        break;
                    case 0b10000:
                        if (line.size() != 3) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted JMP instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.JMP, operation.data(), line.get(1).data(), line.get(2).data()));
                        break;
                    case 0b10001:
                        if (line.size() != 2) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted JMP instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP_IMM, operation.data(), line.get(1).data()));
                        break;
                    case 0b11111:
                        if (line.size() != 1) {
                            throw new RuntimeException("Syntax Error at line " + operation.line() + ": Badly formatted HLT instruction");
                        }
                        buffer.add(Builder.BuildInstruction(Builder.BuildType.OP, operation.data()));
                        break;
                }
                prevLine = operation.line();
            }
            for(int i : buffer.buff){
                if(i >= 65536){
                    throw new RuntimeException("Compiler ran into an error: " + "Invalid instruction in buffer");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return buffer;
    }

}
