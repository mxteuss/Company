package util;

import java.util.Scanner;

public class Singleton {
    private static final Scanner INSTANCE = new Scanner(System.in);
    private Singleton(){}
    public static Scanner getInstance(){
    return INSTANCE;
    }
}
