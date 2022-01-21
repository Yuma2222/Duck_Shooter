package com.example.duck.Model;

import lombok.Getter;

@Getter
public enum CONFIG {
    CONFIG(10);

    public int LIVES;

    CONFIG(int LIVES) {
        this.LIVES = LIVES;
    }
}
