package yxxy.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 注意：【newSingleThreadExecutor 底层实现 是ThreadPoolExecutor实现的】
 * 创建且只创建一个线程池
 */
public class T09_SingleThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i=0; i<5; i++) {
			final int j = i;
			service.execute(()->{
				
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
			
	}
}
