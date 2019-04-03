/**
 *
 */
package yxxy.c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 注意：【newWorkStealingPool 底层实现 是ForkJoinPool实现的】
 * Executors.newWorkStealingPool();		这个是当他做完自己工作的时候，会主动地找活干。
 * 这个是根据自己的cpu是多少核，就启动多少个线程
 */
public class T11_WorkStealingPool {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println("本机器是几核的CPU：" + Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(3000));
		service.execute(new R(4000)); //daemon
		service.execute(new R(5000));
		service.execute(new R(6000));
		service.execute(new R(7000));
		service.execute(new R(8000));
		service.execute(new R(9000)); //daemon
		service.execute(new R(10000));
		service.execute(new R(11000));
		service.execute(new R(12000));
		service.execute(new R(13000));
		service.execute(new R(14000)); //daemon
		service.execute(new R(15000));

		//由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
		System.in.read(); 
	}

	static class R implements Runnable {

		int time;

		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {
			
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("休眠多少毫秒：" + time  + "， 当前线程名：" + Thread.currentThread().getName());
			
		}

	}
}
