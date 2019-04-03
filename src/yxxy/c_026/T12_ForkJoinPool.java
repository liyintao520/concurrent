package yxxy.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 注意：【ForkJoinPool 底层实现 不是ThreadPool实现的】
 * ForkJoinPool实现适合运行大数据计算。大任务 拆分
 * 计算一百万数的和，递归（ForkJoinPool实现）
 * 直接传一个0到1000000，然后 把一个大任务拆分成多个小任务。每个任务最多计算50000个数的和。
 */
public class T12_ForkJoinPool {
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	
	static {
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println("计算总和：" + Arrays.stream(nums).sum()); //stream api
	}


	/**
	 * RecursiveAction	没有返回值
	 */
	static class AddTask1 extends RecursiveAction {
		
		int start, end;
		
		AddTask1(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		protected void compute() {
			
			if(end-start <= MAX_NUM) {
//				如果这段长度大于定制的数：  MAX_NUM
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {
//				否则把这段数在拆分成两个子任务，start到中间，中间再到end。
				int middle = start + (end-start)/2;
				
				AddTask1 subTask1 = new AddTask1(start, middle);
				AddTask1 subTask2 = new AddTask1(middle, end);
				subTask1.fork();	//	启动一个线程
				subTask2.fork();
			}
		}
		
	}


	/**
	 * RecursiveTask 有返回值
	 */
	static class AddTask2 extends RecursiveTask<Long> {
		
		private static final long serialVersionUID = 1L;
		int start, end;
		
		AddTask2(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		protected Long compute() {
			if(end-start <= MAX_NUM) {
				System.out.println("如果这段长度大于定制的数：" + MAX_NUM);
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				return sum;
			} 
			
			int middle = start + (end-start)/2;
			
			AddTask2 subTask1 = new AddTask2(start, middle);
			AddTask2 subTask2 = new AddTask2(middle, end);
			subTask1.fork();
			subTask2.fork();
			return subTask1.join() + subTask2.join();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ForkJoinPool fjp = new ForkJoinPool();
		System.out.println("【通过RecursiveAction计算，没有返回值】");
		AddTask1 task = new AddTask1(0, nums.length);
		fjp.execute(task);	// RecursiveAction计算不用jion是因为那他没有返回值只能用 System.in.read();进行模拟阻塞

		System.out.println("【通过RecursiveTas计算，有返回值】：");
		AddTask2 task2 = new AddTask2(0, nums.length);
		fjp.execute(task2);
		long result = task2.join();	//	join本身就是阻塞的。
		System.out.print("result = " + result);
		
		//System.in.read();
		
	}
}
