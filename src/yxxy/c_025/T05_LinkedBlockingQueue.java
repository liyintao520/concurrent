package yxxy.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * LinkedBlockingQueue 无界队列。什么时候把内存撑满了什么时候为止
 *  1)add(anObject):把anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则招聘异常
 *
 *  2)offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则返回false.
 *
 *  3)put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
 *
 *  4)poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null
 *
 *  5)take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到Blocking有新的对象被加入为止
 */
public class T05_LinkedBlockingQueue {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

	static Random r = new Random();

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					strs.put("a" + i); //如果满了，就会等待
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "p1").start();

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						System.out.println(Thread.currentThread().getName() + " take -" + strs.take()); //如果空了，就会等待
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();

		}
	}
}
