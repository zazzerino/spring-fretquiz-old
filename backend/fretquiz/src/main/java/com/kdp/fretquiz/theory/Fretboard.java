package com.kdp.fretquiz.theory;

import java.util.List;

public record Fretboard(List<String> tuning,
                        int startFret,
                        int endFret)
{
    public static Fretboard DEFAULT = new Fretboard(Fretboard.DEFAULT_TUNING,0,4);

    public static List<String> DEFAULT_TUNING = List.of("E5", "B4", "G4", "D4", "A3", "E3");
}
