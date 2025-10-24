package HW3_猜數字遊戲;

import java.util.Scanner;

public class H3_111316015 {

    /*
    作者:資二甲 王怡婷 111316015
    
    操作說明:
    啟動程式後，程式會啟動一局遊戲，電腦會隨機生成一組 4 個不重複的數字（每個數字 0–9）。
    內部生成以數字型態處理，並確保四個數字互不相同。
    每回合系統會提示需輸入一組四位數，例如：1234（每個位數不可重複）。
    輸入後，程式會回傳類似 1A2B 的結果：
    A：數字與位置都正確的數量（位置正確且數字正確）
    B：數字正確但位置不正確的數量
    繼續猜測直到：
    猜中（得到 4A0B），遊戲顯示「恭喜挑戰成功!」並顯示猜測次數，或
    猜測回合數達 10 次以上（程式內條件為 time <= 10，超過即結束），遊戲顯示失敗並印出正確答案。
    每局結束後，程式會詢問是否再玩一次：
    輸入 1 表示再玩一次
    輸入 2 表示結束
    若輸入不是整數，程式會提示「請輸入 1 或 2 !」並要求重新輸入
        
    符合的評分標準:100分
    程式有意義且可以執行 (+20分)
    完成遊戲 (+70分)
    其他特殊功能 (+10分)

    特殊功能:
    增加可多次遊玩的功能，並且記錄下遊玩次數及成敗
    增加輸入錯誤檢查，若輸入重複數字或是超過四位數，則須重新輸入

    自評應得的分數:100分
    有達到所有評分標準
    有額外功能
    全部自己寫，沒有依靠AI工具或是他人協助
    
    */

    static Scanner scn = new Scanner(System.in);

    public static int[] ENTER() { // 輸入猜測數字

        int[] arr = new int[4];

        System.out.println("請輸入一組四位數數字, 例如: 1234 (每一個位數數字不重複)");
        int num = scn.nextInt();// 讀取輸入

        int n4 = num % 10;
        int n3 = (num / 10) % 10;
        int n2 = (num / 100) % 10;
        int n1 = (num / 1000);

        arr[0] = n1;
        arr[1] = n2;
        arr[2] = n3;
        arr[3] = n4;
        return arr;

    }

    public static int[] Generate() { // 生成隨機數字
        int n1, n2, n3, n4;

        n1 = (int) (Math.random() * 10);

        // 產生第二位數字，使用 do-while 保證 n2 不等於 n1（避免重複數字）
        do {
            n2 = (int) (Math.random() * 10);
        } while (n1 == n2);

        // 產生 n3，確保 n3 不等於 n1 和 n2
        do {
            n3 = (int) (Math.random() * 10);
        } while (n2 == n3 || n1 == n3);

        // 產生 n4，確保 n4 與前面三個數字都不重複
        do {
            n4 = (int) (Math.random() * 10);
        } while (n2 == n4 || n1 == n4 || n3 == n4);

        int arr[] = new int[4];
        arr[0] = n1;
        arr[1] = n2;
        arr[2] = n3;
        arr[3] = n4;
        return arr;

    }

    public static int[][] Gass() { // 猜測數字

        int[] OUT = Generate();

        // for (int i : OUT) System.out.print(i); //檢查用

        int time = 0;
        int A, B;

        // 讓使用者輸入答案，直到猜中 (A==4) 或次數超過 10 次為止
        do {
            A = 0;
            B = 0;
            int[] IN = ENTER();

            // 若輸入的四位數含重複位數，跳過本回合並要求重新輸入
            if (IN[0] == IN[1] || IN[0] == IN[2] ||
                    IN[0] == IN[3] || IN[1] == IN[2] ||
                    IN[1] == IN[3] || IN[2] == IN[3]) {

                System.out.println("每一個位數數字請不要重複");
                continue;
            }

            // 若輸入非四位數（或格式錯誤），跳過本回合
            if (IN[0] >= 10) {
                System.out.println("只能輸入四位數數字");
                continue;
            }

            // 計算 A：檢查每個位置是否數字相同（位置與數字皆相同）
            // 命中後把該 IN[] 設為 -999，避免後續計算 B 時重複計數
            for (int i = 0; i <= 3; i++) {
                if (IN[i] == OUT[i]) {
                    A++;
                    IN[i] = -999;
                }
            }

            // 計算 B：外層遍歷 OUT 的每個數字，內層檢查 IN 的各位置
            // 若數字相同但位置不同則 B++，並把該 IN[] 設為 -999 以免重複計數
            for (int i : OUT) {
                for (int j = 0; j <= 3; j++) {
                    if (i == IN[j]) {
                        B++;
                        IN[j] = -999;
                    }
                }
            }
            System.out.print(A + "A" + B + "B\n");
            time++;

        } while (A != 4 && time <= 10);

        int[][] point = { { A, B, time }, { OUT[0], OUT[1], OUT[2], OUT[3] } };
        return point;

    }

    public static void main(String[] args) {

        int ans;
        int point[][];
        int time = 0;
        int win = 0;

        // 每次啟動一局遊戲，結束後詢問是否再玩一次
        do {

            point = Gass();

            if (point[0][0] == 4) {
                System.out.print("恭喜挑戰成功!\n");
                System.out.print("猜測次數:" + point[0][2] + "次\n");
                win++;
            }

            else {
                System.out.print("挑戰失敗QAQ\n");
                System.out.print("正確答案:" + point[1][0] + point[1][1] + point[1][2] + point[1][3] + "\n");
                ;
            }
            time++;

            System.out.print("是否再玩一次？(1.Y/2.N)\n");

            // 驗證使用者輸入是否為整數，若不是則提示並丟棄該非整數，繼續等待
            while (!scn.hasNextInt()) {

                System.out.println("請輸入 1 或 2 !");
                scn.next(); // 丟棄非整數輸入
            }
            ans = scn.nextInt();

        } while (ans == 1);

        System.out.print("遊戲結束\n");
        System.out.print("總挑戰次數:" + time + "次\n");
        System.out.print("成功次數:" + win + "次\n");

        scn.close();
    }
}