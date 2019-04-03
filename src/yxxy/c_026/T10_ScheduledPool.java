package yxxy.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 注意：【newScheduledThreadPool 底层实现 是ThreadPoolExecutor实现的】
 * Executors.newScheduledThreadPool(4);
 * 要执行的任务，初始化延迟第一次执行的延迟时间，连续执行之间的时间段，初始延迟和周期参数的时间单位
 * scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
 */
public class T10_ScheduledPool {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		service.scheduleAtFixedRate(()->{ // 以固定的频率执行任务。
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程名：" + Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
		
	}
}
