package HW1_找質數;
import java.util.Scanner;
public class H1_111316015 {
    /*
    作者:資二甲 王怡婷 111316015

    操作說明:
    程式從 i = 2 開始，把輸入數字逐一除以 i，若能整除則把 i 當作一個質因數記錄，並重複除直到不能被 i 整除（處理相同質因數的多重次方）。
    當剩餘被除數變成 1 時停止。
    若找到且只找到 1 個質因數（也就是原數本身為質數），程式把結果視為質數並輸出特定格式；若找到多個因數則輸出乘法因式分解。
    
    符合的評分標準:100分
    程式有意義且可以執行 (+10分)
    正確顯示答案 (+70分)
    顯示其質因數分解可供驗證是否為質數 (+20分)
    
    自評應得的分數:95分
    有達到所有評分標準目標
    全部自己寫，沒有依靠AI工具或是他人協助(還有特意把github copilot關掉)
    有考慮到0跟1這種特殊情況
    但使用陣列儲存因數，若遇到質因數個數超過 9999，會超出陣列範圍 (雖然機率很小)
    且大多時候陣列設為9999會浪費空間
    如果改成動態記憶體配置會可解決以上兩問題
    */
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        System.out.println("請輸入一個大於0整數");
        int number = scn.nextInt();

        scn.close();

        int x = number;
        int factor = 0;
        int[] f = new int [9999];   //用來儲存目前已找到的因數
        int j = 0;

    // 從 i = 2 開始，逐一嘗試用 i 去整除目前的被除數 x，找出並記錄所有質因數
    for(int i=2;i<=number;i++){

        if(x % i == 0){

            x = x /i;
            f[j] = i;
            j++;
            factor++;

            // 重複除以相同質因數 i，處理相同質因數的多重次方
            while (x % i == 0) {

                 x = x /i;
                 f[j] = i;
                j++;
                    factor++;
                }

            if(x == 1)
            break;

            }
        }

        //輸入數字是質數時的輸出
        if(factor == 1){
            System.out.print("是 "+number+"=1*"+number);
        }

        //如果輸入數字既不是質數也不是合數時的輸出
        else if(number == 1 || number == 0)
            System.out.println("否 "+number+"無法因式分解");
        
        //輸入數字是合數時的輸出    
        else{
            System.out.print("否 "+number+"=");

            // 印出所有已找到的質因數（最後一個單獨列印避免尾部多餘的乘號）
            for(int i = 0 ; i<j-1 ; i++)
                System.out.printf("%d*",f[i]);

            System.out.print(f[j-1]);
        }
    } 
}