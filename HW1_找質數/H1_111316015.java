package HW1_找質數;
import java.util.Scanner;
public class H1_111316015 {
    public static void main (String[] args){
        Scanner scn = new Scanner(System.in);

        System.out.println("請輸入數字");
        int number = scn.nextInt();

        System.out.println(number);
        scn.close();

        for(int i=2;i<=number;i++){

            int x = number;

            if(x % i == 0){
                x = x /i;

                while (x % i == 0) {
                    x = x /i;

                    if(x == 1)
                    break;
                }
            System.out.printf("i=%d,x=%d\n",i,x);
            }
        }
        System.out.println("算完了");
    } 
}
