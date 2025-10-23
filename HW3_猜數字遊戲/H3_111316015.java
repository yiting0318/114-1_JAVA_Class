package HW3_猜數字遊戲;
import java.util.Scanner;

public class H3_111316015 {

    static Scanner scn = new Scanner(System.in);

    public static int[] ENTER() { // 輸入猜測數字

        int[] arr = new int[4];

        System.out.println("請輸入一組四位數數字, 例如: 1234 (每一個位數數字不重複)");
        int num = scn.nextInt();// 讀取整行輸入

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

        do {
            n2 = (int) (Math.random() * 10);
        } while (n1 == n2);

        do {
            n3 = (int) (Math.random() * 10);
        } while (n2 == n3 || n1 == n3);

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

    public static void main(String[] args) {

        int time = 0;
        int A, B;
        int[] OUT = Generate();

        //for (int i : OUT) System.out.print(i);

        do {
            A = 0;
            B = 0;
            int[] IN = ENTER();

            if (IN[0] == IN[1] || IN[0] == IN[2] ||
                IN[0] == IN[3] || IN[1] == IN[2] ||
                IN[1] == IN[3] || IN[2] == IN[3]) {

                System.out.println("每一個位數數字請不要重複");
                continue;
            }

            if(IN[0] >= 10){
                System.out.println("只能輸入四位數數字");
                continue;
            }

            for (int i = 0; i <= 3; i++) {
                if (IN[i] == OUT[i]) {
                    A++;
                    IN[i] = -1;
                }
            }

            for (int i : OUT) {
                for (int j = 0; j <= 3; j++) {
                    if (i == IN[j]) {
                        B++;
                        IN[j] = -1;
                    }
                }
            }
            System.out.print(A + "A" + B + "B\n");
            time++;

        } while (A != 4 && time <= 10);

        if (A == 4) {
            System.out.print("遊戲結束，恭喜挑戰成功!\n");
            System.out.print("猜測次數:" + time + "次\n");
        } else {
            System.out.print("遊戲結束，挑戰失敗QAQ\n");
            System.out.print("正確答案:" + OUT[0] + OUT[1] + OUT[2] + OUT[3] + "\n");
        }
        scn.close();
    }
}
