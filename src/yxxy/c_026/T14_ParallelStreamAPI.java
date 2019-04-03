package yxxy.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * JDK1.8的 List  parallelStream()默认使用多线程。
 */
public class T14_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));

//        System.out.println("list集合：" + nums);

        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println("不使用parallelStream耗时：" + (end - start));

        //使用parallel stream api
//        parallelStream 默认使用多线程
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();

        System.out.println("使用parallelStream耗时：" + (end - start));
    }

    /**
     * 判断是不是质素
     *
     * @param num
     * @return 是质素：false   不是：true
     */
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
