/**
 * 认识Executor
 * Executor任务执行器，执行一个Runnable这项任务的
 */
package yxxy.c_026;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor{

	public static void main(String[] args) {
		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		//new Thread(command).run();
//		new Thread(command).start();
		command.run();
		
	}

}

