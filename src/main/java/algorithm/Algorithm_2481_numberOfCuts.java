package algorithm;

/**
 * 分割圆最小的分割次数
 * 圆内一个 有效切割 ，符合以下二者之一：
 *
 * 该切割是两个端点在圆上的线段，且该线段经过圆心。
 * 该切割是一端在圆心另一端在圆上的线段。
 * 一些有效和无效的切割如下图所示。
 *
 *
 *
 * 给你一个整数 n ，请你返回将圆切割成相等的 n 等分的 最少 切割次数
 */
public class Algorithm_2481_numberOfCuts {

    public static void main(String[] args) {
        int i = numbersOfCuts(4);
        System.out.println(i);
    }

    public static int numbersOfCuts(int n){
        if (n<1) {
            return n;
        }
        //n>1
        //n=2,output=1   n=3,output=3,n=4 output=2 ,n=5,output=5,n=6 output=3
        //综上所述，当n为奇数，output=n,当n为偶数，output=n/2
        if (n % 2==0) {
            return n/2;
        }else {
            return n;
        }

    }
}
