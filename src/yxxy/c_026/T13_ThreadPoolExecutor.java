/**
 * 自定义线程池
 */
package yxxy.c_026;


/**
 * corePoolSize：核心线程数，核心线程会一直存活，即使没有任务需要处理。
 * maximumPoolSize：当线程数大于或等于核心线程，【且任务队列已满时】，线程池会创建新的线程，直到线程数量达到maxPoolSize。
 *                  如果线程数已等于maxPoolSize，且任务队列已满，则已超出线程池的处理能力，线程池会拒绝处理任务而抛出异常。
 * keepAliveTime：
 * unit：
 * workQueue：
 * threadFactory：线程工厂，主要用来创建线程，比如可以指定线程的名字；
 * handler：如果线程池已满，新的任务的处理方式
 * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
 *                    TimeUnit unit, BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler)
 *
 * 线程池的阻塞队列包含哪几种选择？
 *
 1. ArrayBlockingQueue
 2. DelayQueue
 3. LinkedBlockingQueue
 4. PriorityBlockingQueue
 5. SynchronousQueue
 */
public class T13_ThreadPoolExecutor {
    public static void main(String[] args) {

    }
}
