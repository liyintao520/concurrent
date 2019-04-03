package yxxy.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 注意：【newCachedThreadPool 底层实现 是ThreadPoolExecutor实现的】
 * 通常用于执行一些生存期很短的异步型任务
 * 缓存线程：newCachedThreadPool()
 * 来一个任务启动一个线程，比如 再来一个任务，刚好你这个线程空闲，那么就直接用这个。如果不是空闲，那么在启动一个线程。
 * 最多启动线程数--直到你服务器撑不起为止
 * 这个启动的线程数有生命周期【aliveTime】，好像默认是60s，60秒不使用该线程的话，线程就自动销毁。
 */
public class T08_CachedPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("刚开始初始化的时候：" + service);
		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println("有了两个任务之后：" + service);
		
		TimeUnit.SECONDS.sleep(80);	//	默认是60秒空闲销毁。 这里设置80秒 是为了保险起见
		
		System.out.println("休眠80秒之后再次查看：" + service);
		
		
	}
}
