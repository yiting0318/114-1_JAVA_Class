package HW4_月曆生成器;

import java.util.Scanner;

public class H4_111316015 {

    static Scanner scn = new Scanner(System.in);

    public static int check() { // 確保輸入資料為整數
        int i;
        while (!scn.hasNextInt()) {
            System.out.println("輸入錯誤，請輸入整數");
            scn.next(); // 丟棄非整數輸入
        }
        i = scn.nextInt();
        return i;
    }

    public static String ENTER() { //輸入

        int year, month, day;
        System.out.println("歡迎使用月曆生成器");

        do {
            System.out.print("請輸入年分(須為1900年以後) ");
            year = check();
        } while (year < 1900);

        do {
            System.out.print("請輸入月份 ");
            month = check();
        } while (month < 0 || month > 12);

        if (month == 4 || month == 6 || month == 9 || month == 11 ) {
            do {
                System.out.print("請輸入日期 ");
                day = check();
            } while (day < 1 || day > 30);
        } 
        else if (month == 2) {
            do {
                System.out.print("請輸入日期 ");
                day = check();
            } while (day < 1 || day > 28);
        } 
        else {
            do {
                System.out.print("請輸入日期 ");
                day = check();
            } while (day < 1 || day > 31);

        }

        String dayname = GregorianCalendar(year, month, day);
        return dayname;

    }

    public static String GregorianCalendar ( int year, int month, int day ){  //用蔡勒公式計算出星期

        // 月份調整 (m = 13 或 14)
        // 蔡勒公式將一月和二月視為前一年的 13 月和 14 月。
        if (month == 1) {
            month = 13;
            year--; // 視為前一年
        } else if (month == 2) {
            month = 14;
            year--; // 視為前一年
        }

        // **2. 拆分年份**
        // J: 零基底世紀數 (年份的前兩位)
        int i = year / 100;
        // K: 世紀年中的年份 (年份的後兩位)
        int j = year % 100;

        // **3. 套用蔡勒公式 (Gregorian Calendar)**
        // h = ( q + ⌊13(m+1)/5⌋ + K + ⌊K/4⌋ + ⌊J/4⌋ - 2J ) mod 

        // 注意：在 Java 中，(a % n) 的結果可能為負數，所以需要加 7 再取模來確保結果在 [0, 6] 區間內。
        int h = ( day + ((13*(month+1))/5) + j + (j/4) + (i/4) - (2*i))%7 ;
        String dayname = DayName((h + 7) % 7);
        return dayname;
    }

    public static String DayName(int h) { //換算成星期幾
        String[] days = { "星期六","星期日", "星期一", "星期二", "星期三", "星期四", "星期五" };
        return days[h];
    }
    public static void main(String[] args) {

        String dayname = ENTER();
        System.out.println(dayname);

    }
}
