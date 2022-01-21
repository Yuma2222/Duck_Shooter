package com.example.duck.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class score {
    private int time;
    private String name;

    @Override
    public String toString() {
        return "score:" +
                " time: " + time +
                ", name: " + name;
    }
}
