package services;

public class ClearScreen {
    public static void clearScreen() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public static void clearScreen(int line) {
        for (int i = 0; i < line; i++) {
            System.out.println();
        }
    }
}
