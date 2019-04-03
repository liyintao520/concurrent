/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 * @author liyintao
 */
package yxxy.c_025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists =
                //new ArrayList<>(); //这个会出并发问题！
                //new Vector();
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) lists.add("a" + r.nextInt(10000));
                }

            };
            ths[i] = new Thread(task);
        }


        runAndComputeTime(ths);

        System.out.println(lists.size());
    }

//    线程实例的方法join()方法可以使得一个线程在另一个线程结束后再执行。如果join()方法在一个线程实例上调用，当前运行着的线程将阻塞直到这个线程实例完成了执行。
    static void runAndComputeTime(Thread[] ths) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);

    }
}

