package me.infinity.minecraft.utils;

import java.util.Random;

public class Utils {
    public static int randInt(Random random, int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
