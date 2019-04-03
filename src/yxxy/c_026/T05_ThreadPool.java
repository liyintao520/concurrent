/**
 * 线程池的概念
 */
package yxxy.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 注意：【newFixedThreadPool 底层实现 是ThreadPoolExecutor实现的】
 * Executors.newFixedThreadPool(5) 创建一个固定线程池，大小为5.直观上理解就是new Thread() 了五个线程。
 * 这五个线程是需要的时候才启动
 */
public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5); //execute submit
		for (int i = 0; i < 6; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("当前线程名：" + Thread.currentThread().getName());
			});
		}
//		运行状态，线程池大小，活动线程数，队列中等待的任务个数，完后任务结束后的个数
//		[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
		System.out.println("service" + service);
		
		service.shutdown();	// 线程池关闭。这个关闭是所有线程都执行完 才进行关闭
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
