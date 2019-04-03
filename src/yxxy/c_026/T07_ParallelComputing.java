/**
 * 注意：【newFixedThreadPool 底层实现 是ThreadPoolExecutor实现的】
 * 线程池的概念
 * nasa
 * 并行计算的一个概念
 */
package yxxy.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T07_ParallelComputing {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);    //  求1到200000中间的质数（只能被1和本身整除的）
        long end = System.currentTimeMillis();
        System.out.println("只用一个主线程计算1到200000的所有质素耗时：" + (end - start) + "（单位毫秒）");

        final int cpuCoreNum = 6;   //  一般cpu有几个核 就至少起几个线程

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
//        注意：越大的数，计算时间会越长。
        MyTask t1 = new MyTask(1, 60000); //1-5 5-10 10-15 15-20
        MyTask t2 = new MyTask(60001, 100000);
        MyTask t3 = new MyTask(100001, 130000);
        MyTask t4 = new MyTask(130001, 150000);
        MyTask t5 = new MyTask(150001, 180000);
        MyTask t6 = new MyTask(180001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);
        Future<List<Integer>> f5 = service.submit(t5);
        Future<List<Integer>> f6 = service.submit(t6);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        f5.get();
        f6.get();
        end = System.currentTimeMillis();
        System.out.println("只用一个主线程计算1到200000的所有质素耗时：" + (end - start) + "（单位毫秒）");
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

    }

    /**
     * 判断是不是质素
     * @param num
     * @return  是质素：false   不是：true
     */
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * 求start到end中间的质数（只能被1和本身整除的）
     * @param start
     * @param end
     * @return
     */
    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) results.add(i);
        }

        return results;
    }
}
