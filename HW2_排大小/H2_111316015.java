package HW2_排大小;
import java.util.Scanner;
public class H2_111316015 {
    /*
    作者:資二甲 王怡婷 111316015

    操作說明:
    程式會要求使用者輸入多個整數，並以空白分隔這些整數。
    程式會驗證每個輸入是否為有效的整數，若有任何一個輸入無法轉換為整數，程式會提示錯誤並要求重新輸入。
    當所有輸入皆為有效整數後，程式會找出這些整數中的最大值，將其輸出，
    然後將所有等於該最大值的元素替換為陣列中的最小值。
    這個過程會重複進行，直到所有輸入的整數都被依序輸出為止，達到從大到小排序的效果。
    
    符合的評分標準:100分
    程式有意義且可以執行 (+10分)
    正確顯示答案 (+70分)
    會偵測輸入是否不為整數 (+20分)
    
    自評應得的分數:95分
    有達到所有評分標準
    原本自己寫的程式只能整數排大小，但無法在輸入非整數數字時正常運作
    在詢問chatGPT後，使用 try-catch 做輸入驗證

    問chatGPT的prompt: 可否讓如果輸入非整數的數字被Integer.parseInt()偵測到，就從新輸入
    回覆:可以用 try-catch 來捕捉 Integer.parseInt() 拋出的 NumberFormatException，
        如果使用者輸入的不是整數，就提醒他重新輸入。這樣就不會程式崩潰。
    */
    

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String input;
        String[] number;
        int[] arr;

        // 重複要求輸入直到使用者輸入的每個項目皆可轉為整數
        while (true) {

            System.out.println("請輸入多個整數, 整數間以空白相隔");
            
            input = scn.nextLine();// 讀取整行輸入
            number = input.split(" ");// 以空白分隔字串成字串陣列
            
            arr = new int[number.length];
            boolean valid = true;

            // 逐一嘗試將每個字串轉為整數
            for (int i = 0; i < number.length; i++) {
                try {
                    // 若轉換成功，存入 arr
                    arr[i] = Integer.parseInt(number[i]);
                } 
                // 若任一轉換失敗，提示錯誤並標記為 invalid，離開 for 以重試輸入
                catch (NumberFormatException e) {
                    
                    System.out.println("輸入錯誤，請重新輸入！");
                    valid = false;
                    break; 
                }

            }
            // 若所有字串皆成功轉為整數，跳出 while 繼續後續處理
            if (valid) break;
        }
        scn.close();

        int min = arr[0];
        int max = arr[0];

        // 找出陣列中的最小值，用於後面作為替換的最小標記
        // 使用 for-each 遍歷 arr 的每個元素
        for (int i : arr) {
            if (i < min)
                min = i; 
        }

        // 重複 arr.length 次，每次找出目前陣列中的最大值並輸出
        for (int i = 0; i < arr.length; i++) {

            // 掃描整個陣列以找出當前最大的元素
            for (int j : arr) {
                if (j > max) {
                    max = j; 
                }
            }
  
            System.out.print(max + " ");

            // 將所有等於 max 的元素位置設定為最小值
            // 這樣在下一輪找最大值時，這些已輸出的元素不會再被選中
            for (int x = 0; x < arr.length; x++) {
                if (arr[x] == max) {
                    arr[x] = min; 
                    max = min;  
                }
            }
        }
    }
}