package com.netcracker.adlitsov.homework3;

import java.util.Scanner;

public class Task3 {

    private static final String SPACE = "  ";
    private static final String SYMBOL = "# ";

    public static void draw0(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(SYMBOL);
            }
            System.out.println();
        }
    }

    public static void drawA(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(SYMBOL);
            }
            System.out.println();
        }
    }

    public static void drawB(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                System.out.print(SYMBOL);
            }
            System.out.println();
        }
    }

    public static void drawC(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(SPACE);
            }
            for (int j = i; j < size; j++) {
                System.out.print(SYMBOL);
            }
            System.out.println();
        }
    }

    public static void drawD(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                System.out.print(SPACE);
            }
            for (int j = size - i - 1; j < size; j++) {
                System.out.print(SYMBOL);
            }
            System.out.println();
        }
    }

    public static void drawE(int size) {
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print(SYMBOL);
                }
                System.out.println();
            } else {
                System.out.print(SYMBOL);
                for (int j = 1; j < size - 1; j++) {
                    System.out.print(SPACE);
                }
                System.out.println(SYMBOL);
            }
        }
    }

    public static void drawF(int size) {
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print(SYMBOL);
                }
                System.out.println();
            } else {
                for (int j = 0; j < i; j++) {
                    System.out.print(SPACE);
                }
                System.out.print(SYMBOL);
                for (int j = i + 1; j < size; j++) {
                    System.out.print(SPACE);
                }
                System.out.println();
            }
        }
    }

    public static void drawG(int size) {
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print(SYMBOL);
                }
                System.out.println();
            } else {
                for (int j = 0; j < size - i - 1; j++) {
                    System.out.print(SPACE);
                }
                System.out.print(SYMBOL);
                for (int j = size - i; j < size; j++) {
                    System.out.print(SPACE);
                }
                System.out.println();
            }
        }
    }

    public static void drawH(int size) {
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print(SYMBOL);
                }
                System.out.println();
            } else {
                for (int j = 0; j < size; j++) {
                    if (j == i || j == size - i - 1) {
                        System.out.print(SYMBOL);
                    } else {
                        System.out.print(SPACE);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void drawI(int size) {
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print(SYMBOL);
                }
                System.out.println();
            } else {
                for (int j = 0; j < size; j++) {
                    if (j == 0 || j == i || j == size - i - 1 || j == size - 1) {
                        System.out.print(SYMBOL);
                    } else {
                        System.out.print(SPACE);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter figure type (one letter 'a' - 'i' or '0' for rectangle): ");
        String type = sc.nextLine();
        int rows = 0, columns = 0;
        if (type.equals("0")) {
            System.out.print("Enter rows count: ");
            rows = sc.nextInt();
            System.out.print("Enter columns count: ");
            columns = sc.nextInt();
        } else {
            System.out.print("Enter size: ");
            rows = sc.nextInt();
        }

        switch (type) {
            case "0":
                draw0(rows, columns);
                break;

            case "a":
                drawA(rows);
                break;

            case "b":
                drawB(rows);
                break;

            case "c":
                drawC(rows);
                break;

            case "d":
                drawD(rows);
                break;

            case "e":
                drawE(rows);
                break;

            case "f":
                drawF(rows);
                break;

            case "g":
                drawG(rows);
                break;

            case "h":
                drawH(rows);
                break;

            case "i":
                drawI(rows);
                break;

            default:
                System.out.println("Unrecognized type!");
                break;
        }

        sc.close();
    }
}
