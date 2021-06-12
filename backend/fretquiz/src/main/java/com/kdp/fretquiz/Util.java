package com.kdp.fretquiz;

import java.util.Random;

public class Util
{
    public static <T> T randomElement(T[] array)
    {
        final var index = new Random().nextInt(array.length);
        return array[index];
    }
}
