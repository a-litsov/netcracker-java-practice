package com.netcracker.adlitsov.homework3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task6 {
    public static final int SIZE = 50;
    public static final int MIN = 0;
    public static final int MAX = 10;

    public static void showColorNameManuallyUsingSwitch(Color color) {
        switch (color) {
            case WHITE:
                System.out.println("Branch 0");
                System.out.println("White color");
                break;
            case SILVER:
                System.out.println("Branch 1");
                System.out.println("Silver color");
                break;
            case GREY:
                System.out.println("Branch 2");
                System.out.println("Grey color");
                break;
            case BLACK:
                System.out.println("Branch 3");
                System.out.println("Black color");
                break;
            case BLUE:
                System.out.println("Branch 4");
                System.out.println("Blue color");
                break;
            case CYAN:
                System.out.println("Branch 5");
                System.out.println("Cyan color");
                break;
            case GREEN:
                System.out.println("Branch 6");
                System.out.println("Green color");
                break;
            case YELLOW:
                System.out.println("Branch 7");
                System.out.println("Yellow color");
                break;
            case GOLD:
                System.out.println("Branch 8");
                System.out.println("Gold color");
                break;
            case ORANGE:
                System.out.println("Branch 9");
                System.out.println("Orange color");
                break;
            case BROWN:
                System.out.println("Branch 10");
                System.out.println("Brown color");
                break;
            case RED:
                System.out.println("Branch 11");
                System.out.println("Red color");
                break;
            case PINK:
                System.out.println("Branch 12");
                System.out.println("Pink color");
                break;
            case PURPLE:
                System.out.println("Branch 13");
                System.out.println("Purple color");
                break;
            case VIOLET:
                System.out.println("Branch 14");
                System.out.println("Violet color");
                break;
        }
    }

    public static long runEnumSwitchTest(Color[] colors) {
        System.out.println("\nSwitch version:");

        long startTime = System.nanoTime();
        for (Color color : colors) {
            System.out.println(color);
            showColorNameManuallyUsingSwitch(color);
            System.out.println();
        }

        return System.nanoTime() - startTime;
    }

    public static void showColorNameManuallyUsingIfElse(Color color) {
        if (color == Color.WHITE) {
            System.out.println("Branch 0");
            System.out.println("White color");
        } else {
            System.out.println("Branch 1");
            if (color == Color.SILVER) {
                System.out.println("Silver color");
            } else {
                System.out.println("Branch 2");
                if (color == Color.GREY) {
                    System.out.println("Grey color");
                } else {
                    System.out.println("Branch 3");
                    if (color == Color.BLACK) {
                        System.out.println("Black color");
                    } else {
                        System.out.println("Branch 4");
                        if (color == Color.BLUE) {
                            System.out.println("Blue color");

                        } else {
                            System.out.println("Branch 5");
                            if (color == Color.CYAN) {
                                System.out.println("Cyan color");
                            } else {
                                System.out.println("Branch 6");
                                if (color == Color.GREEN) {
                                    System.out.println("Green color");
                                } else {
                                    System.out.println("Branch 7");
                                    if (color == Color.YELLOW) {
                                        System.out.println("Yellow color");
                                    } else {
                                        System.out.println("Branch 8");
                                        if (color == Color.GOLD) {
                                            System.out.println("Gold color");
                                        } else {
                                            System.out.println("Branch 9");
                                            if (color == Color.ORANGE) {
                                                System.out.println("Orange color");
                                            } else {
                                                System.out.println("Branch 10");
                                                if (color == Color.BROWN) {
                                                    System.out.println("Brown color");
                                                } else {
                                                    System.out.println("Branch 11");
                                                    if (color == Color.RED) {
                                                        System.out.println("Red color");
                                                    } else {
                                                        System.out.println("Branch 12");
                                                        if (color == Color.PINK) {
                                                            System.out.println("Pink color");
                                                        } else {
                                                            System.out.println("Branch 13");
                                                            if (color == Color.PURPLE) {
                                                                System.out.println("Purple color");

                                                            } else {
                                                                System.out.println("Branch 14");
                                                                if (color == Color.VIOLET) {
                                                                    System.out.println("Violet color");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static long runEnumIfElseTest(Color[] colors) {
        System.out.println("\nIf-Else version:");

        long startTime = System.nanoTime();
        for (Color color : colors) {
            System.out.println(color);
            showColorNameManuallyUsingIfElse(color);
            System.out.println();
        }

        return System.nanoTime() - startTime;
    }

    public static int[][] showArrayMembersManuallyUsingSwitch(int[] numbers) {
        int[][] stats = new int[numbers.length][11];
        for (int i = 0; i < numbers.length; i++) {
            switch (numbers[i]) {
                case 0:
                    System.out.println("Branch 0");
                    System.out.println("It's 0 element");
                    stats[i][0]++;
                    break;
                case 1:
                    System.out.println("Branch 1");
                    System.out.println("It's 1 element");
                    stats[i][1]++;
                    break;
                case 2:
                    System.out.println("Branch 2");
                    System.out.println("It's 2 element");
                    stats[i][2]++;
                    break;
                case 3:
                    System.out.println("Branch 3");
                    System.out.println("It's 3 element");
                    stats[i][3]++;
                    break;
                case 4:
                    System.out.println("Branch 4");
                    System.out.println("It's 4 element");
                    stats[i][4]++;
                    break;
                case 5:
                    System.out.println("Branch 5");
                    System.out.println("It's 5 element");
                    stats[i][5]++;
                    break;
                case 6:
                    System.out.println("Branch 6");
                    System.out.println("It's 6 element");
                    stats[i][6]++;
                    break;
                case 7:
                    System.out.println("Branch 7");
                    System.out.println("It's 7 element");
                    stats[i][7]++;
                    break;
                case 8:
                    System.out.println("Branch 8");
                    System.out.println("It's 8 element");
                    stats[i][8]++;
                    break;
                case 9:
                    System.out.println("Branch 9");
                    System.out.println("It's 9 element");
                    stats[i][9]++;
                    break;
                case 10:
                    System.out.println("Branch 10");
                    System.out.println("It's 10 element");
                    stats[i][10]++;
                    break;
            }
        }
        return stats;
    }

    public static long runArraySwitchTest(int[] numbers) {
        System.out.println("\nSwitch version:\n");

        long startTime = System.nanoTime();
        int[][] stats = showArrayMembersManuallyUsingSwitch(numbers);
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("\nSwitch version branch stats:");
        showStats(numbers, stats);
        return estimatedTime;
    }

    public static int[][] showArrayMembersManuallyUsingIfElse(int[] numbers) {
        int[][] stats = new int[numbers.length][11];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                System.out.println("Branch 0");
                stats[i][0]++;
                System.out.println("It's 0 element");
            } else {
                System.out.println("Branch 1");
                stats[i][1]++;
                if (numbers[i] == 1) {
                    System.out.println("It's 1 element");
                } else {
                    System.out.println("Branch 2");
                    stats[i][2]++;
                    if (numbers[i] == 2) {
                        System.out.println("It's 2 element");
                    } else {
                        System.out.println("Branch 3");
                        stats[i][3]++;
                        if (numbers[i] == 3) {
                            System.out.println("It's 3 element");
                        } else {
                            System.out.println("Branch 4");
                            stats[i][4]++;
                            if (numbers[i] == 4) {
                                System.out.println("It's 4 element");
                            } else {
                                System.out.println("Branch 5");
                                stats[i][5]++;
                                if (numbers[i] == 5) {
                                    System.out.println("It's 5 element");
                                } else {
                                    System.out.println("Branch 6");
                                    stats[i][6]++;
                                    if (numbers[i] == 6) {
                                        System.out.println("It's 6 element");
                                    } else {
                                        System.out.println("Branch 7");
                                        stats[i][7]++;
                                        if (numbers[i] == 7) {
                                            System.out.println("It's 7 element");
                                        } else {
                                            System.out.println("Branch 8");
                                            stats[i][8]++;
                                            if (numbers[i] == 8) {
                                                System.out.println("It's 8 element");
                                            } else {
                                                System.out.println("Branch 9");
                                                stats[i][9]++;
                                                if (numbers[i] == 9) {
                                                    System.out.println("It's 9 element");
                                                } else {
                                                    System.out.println("Branch 10");
                                                    stats[i][10]++;
                                                    if (numbers[i] == 10) {
                                                        System.out.println("It's 10 element");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return stats;
    }

    public static long runArrayIfElseTest(int[] numbers) {
        System.out.println("\nIf-Else version:\n");

        long startTime = System.nanoTime();
        int[][] stats = showArrayMembersManuallyUsingIfElse(numbers);
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("\nIf-Else version branch stats:");
        showStats(numbers, stats);
        return estimatedTime;
    }

    public static void showStats(int[] numbers, int[][] stats) {
        for (int i = 0; i < stats.length; i++) {
            System.out.println("Array[" + i + "]=" + numbers[i] + ":" + Arrays.toString(stats[i]));
        }
    }

    public static Color[] generateColors(int enumsCount) {
        Random rnd = new Random();
        Color[] colors = new Color[enumsCount];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.getRandom(rnd);
        }

        return colors;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enum tests:");
        System.out.print("Enter enums count:");
        int enumsCount = sc.nextInt();

        Color[] colors = generateColors(enumsCount);
        long switchTime = runEnumSwitchTest(colors);
        long ifElseTime = runEnumIfElseTest(colors);

        System.out.println("\n\nSwitch time for enums: " + switchTime);
        System.out.println("IfElse time for enums: " + ifElseTime);

        System.out.println("\n=====================================\n");
        System.out.println("Array tests:");

        int[] numbers = new Random().ints(SIZE, MIN, MAX).toArray();

        switchTime = runArraySwitchTest(numbers);
        ifElseTime = runArrayIfElseTest(numbers);

        System.out.println("\n\nSwitch time for arrays: " + switchTime);
        System.out.println("IfElse time for arrays: " + ifElseTime);
    }

    public enum Color {
        WHITE, SILVER, GREY, BLACK, BLUE,
        CYAN, GREEN, YELLOW, GOLD, ORANGE,
        BROWN, RED, PINK, PURPLE, VIOLET;

        public static Color getRandom(Random rnd) {
            return values()[rnd.nextInt(values().length)];
        }
    }
}
