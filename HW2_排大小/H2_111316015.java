package HW2_排大小;

import java.util.Scanner;

public class H2_111316015 {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.println("請輸入多個整數, 整數間以空白相隔");

        String input = scn.nextLine();
        String[] number = input.split(" ");
        int arr[] = new int[number.length];

        for (int i = 0; i < number.length; i++) {
            arr[i] = Integer.parseInt(number[i]);
        }

        int max = arr[0];
        int min = arr[0];

        for (int i : arr) {
            if (i < min)
                min = i;
        }

        for (int i = 0; i < arr.length; i++) {

            for (int j : arr) {
                if (j > max) {
                    max = j;
                }
            }
            System.out.print(max + " ");

            for (int x = 0; x < arr.length; x++) {
                if (arr[x] == max) {
                    arr[x] = min;
                    max = min;
                }
            }
        }

        scn.close();

    }
}