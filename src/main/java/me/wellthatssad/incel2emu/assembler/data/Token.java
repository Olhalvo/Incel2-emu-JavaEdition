package me.wellthatssad.incel2emu.assembler.data;

import java.util.Objects;

public record Token(TokenType type, int data, int line) {
    public Token{
        Objects.requireNonNull(type);
    }
}
