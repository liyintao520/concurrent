总结：
1：对于map/set的选择使用
    map保存的是键值对，set保存的是key
HashMap
TreeMap 默认排序了
LinkedHashMap

Hashtable   效率低
Collections.sychronizedXXX  并发不高的情况下使用

ConcurrentHashMap   并发比较高用这个
ConcurrentSkipListMap   并发比较高并且【是排序的】情况下使用

2：队列
ArrayList
LinkedList
Collections.synchronizedXXX
CopyOnWriteList 读的时候量非常多的时候使用。他是把原先的拷贝过来，然后在new一个新的去操作。
Queue
	CocurrentLinkedQueue //concurrentArrayQueue
	BlockingQueue
		LinkedBQ
		ArrayBQ
		TransferQueue
		SynchronusQueue
	DelayQueue执行定时任务
		
	