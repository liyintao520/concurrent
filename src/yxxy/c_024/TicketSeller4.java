/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * 
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用ConcurrentQueue提高并发性
 * 
 * @author liyintao
 */
package yxxy.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TicketSeller4 {
	// 队列 同步容器 先进先出 FIFO
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				// TODO 这块拿出和判断也是分开做的 中间也有可能被其他线程拿到，并执行。但是即使拿到数据了也没有对队列执行修改操作，并且再次循环比对
				while(true) {
					// poll 我拿出最头上的那个数据 -- 如果队列中没有数据了 poll得到的就是一个null值
// 源码：ConcurrentLinkedQueue poll() 如果p节点的元素不为null，则通过CAS来设置p节点引用的元素为null，如果成功则返回p节点的元素
					String s = tickets.poll(); // 添加的事cas的锁
					if(s == null) {
						break;
					} else {
						System.out.println("销售了--" + s);
					}
				}
			}).start();
		}
	}
}
