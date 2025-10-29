package HW4_月曆生成器;
import java.util.Scanner;

public class H4_111316015 {

    /*
    作者:資二甲 王怡婷 111316015

    操作說明:
    此程式為月曆生成器，能根據使用者輸入的日期，計算出該日為星期幾，並生成該月份的完整月曆。
    
    1. 執行程式後，系統將提示您輸入年、月、日。
    2. 請依序輸入：
       - 年份：必須輸入大於等於 1900 年的整數。
       - 月份：必須輸入 1 到 12 之間的整數。
       - 日期：必須輸入符合該月份天數的有效整數（例如：二月在非閏年最大為 28，閏年最大為 29）。
    3. 程式會對所有輸入進行驗證，如果輸入格式錯誤（非整數）或數值超出範圍，程式會提示並要求重新輸入。
    4. 輸出結果包含兩部分：
       - 您輸入的日期是星期幾。
       - 該年份該月份的完整月曆。

    程式運作原理:
    此程式的核心是利用「Zeller's Congruence」來計算特定日期是星期幾。
    1. 資料輸入與驗證 (ENTER/check)：確保輸入的年/月/日為有效整數且在合理範圍內 (年 >= 1900)。
    2. 日期規則判斷 (isLeapYear/MaxDays)：判斷是否為閏年，並計算該月最大天數。
    3. 星期計算 (ZellerCongruence)：使用蔡勒公式計算給定日期（或該月第一天）對應的星期數 (0=日, 1=一... 6=六)。
    4. 月曆輸出 (GenerateCalendar)：利用該月第一天的星期數，決定日曆的起始位置，並逐日列印，逢星期六換行。


    符合的評分標準:100分
    程式有意義且可以執行 (+20分)
    完成全部功能 (+80分)


    評應得的分數:98分
    有達到所有評分標準目標
    全部自己寫，沒有依靠AI工具或是他人協助(但有上網google蔡勒公式是甚麼)
    可以判定使用者輸入是否合理，否則要求重新輸入
    後來發現用「Kim Larsen's Formula」可以讓程式更簡潔?但我寫完了不想改，且基姆拉爾森公式有點難懂(ㆆᴗㆆ)
     */

    static Scanner scn = new Scanner(System.in);

    public static int check() { // 確保輸入資料為整數
        int i;
        // 只要下一個輸入不是整數就持續要求輸入
        while (!scn.hasNextInt()) {
            System.out.println("輸入錯誤，請輸入整數");
            scn.next(); // 丟棄非整數輸入
        }
        i = scn.nextInt();
        return i;
    }

    public static boolean isLeapYear(int year) { // 判斷是否為閏年
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    public static int MaxDays(int year, int month) { // 獲取月份最大天數
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else {
            return 31;
        }
    }

    public static int[] ENTER() { // 輸入年、月、日
        int year, month, day;
        System.out.println("歡迎使用月曆生成器");

        // 驗證年分是否 >= 1900
        do {
            System.out.print("請輸入年分(須為1900年以後): ");
            year = check();
        } while (year < 1900);

        // 驗證月份在 1~12 之間
        do {
            System.out.print("請輸入月份 (1~12): ");
            month = check();
        } while (month < 1 || month > 12);

        int maxDay = MaxDays(year, month);

        // 驗證日期是否在該月份的合法範圍內
        do {
            System.out.printf("請輸入日期 (1~%d): ", maxDay);
            day = check();
        } while (day < 1 || day > maxDay);
        return new int[] { year, month, day };
    }

    public static int ZellerCongruence ( int year, int month, int day ){  //用蔡勒公式計算出星期

        // 月份調整 (m = 13 或 14)
        // 蔡勒公式將一月和二月視為前一年的 13 月和 14 月。
        if (month == 1) {
            month = 13;
            year--; // 視為前一年
        } else if (month == 2) {
            month = 14;
            year--; // 視為前一年
        }

        int h = ( day + ((13*(month+1))/5) + (year%100) + ((year%100)/4) + ((year/100)/4) - (2*(year/100)))%7 ;
        
        //讓週日為0，週六為6
        if(h == 0)h = h+6;
        else h--;

        return h;
    }

    public static String printDayName(int h) { //換算成星期幾
        String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
        return days[h];
    }

    public static int[][] Generate (int h){ //把蔡勒公式數值傳換成中文

        int [][] calendar = new int[6][7];

        System.out.println("日  一  二  三  四  五  六");

        // 用以初始化陣列，calendar 的第 i 列第 1 欄設為 0
        for(int i = 0 ; i<=7 ; i++){
            calendar[i][1]= 0;
        }
        return calendar;
    }

    public static void GenerateCalendar(int year, int month) { // 生成月曆

        int startDay = ZellerCongruence(year, month, 1);

        int maxDay = MaxDays(year, month);

        System.out.println("日  一  二  三  四  五  六"); 

        // 用來輸出起始空白，補足本月第一天之前的欄位
        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        int Day = 1;
        int WeekDay = startDay; 

        // 從 1 一直列印到該月份的最大天數
        while (Day <= maxDay) {

            System.out.printf("%-4d", Day);

            // 如果是星期六則換行（換列）
            if (WeekDay == 6) {
                System.out.println();
                WeekDay = 0; // 換行後將 WeekDay 重設為 0 (星期日)
            } else {
            // 否則遞增到下一個星期日
                WeekDay++;
            }
            Day++;
        }

        // 確保最後一行有換行
        if (WeekDay != 0) {
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[] date = ENTER();
        int year = date[0];
        int month = date[1];
        int day = date[2];

        int result = ZellerCongruence(year, month, day);
        String dayName = printDayName(result);

        System.out.println(dayName);
        GenerateCalendar(year, month);

        scn.close();

    }
}
