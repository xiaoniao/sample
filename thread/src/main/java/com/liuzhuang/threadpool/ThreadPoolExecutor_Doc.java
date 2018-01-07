package com.liuzhuang.threadpool;

/*
 * 线程池的几个概念
 * 
 * 我先说一下它的实现模型，预先开启几个线程然后轮训的从队列中获取任务，如果获取到任务则在线程中执行任务，如果没有获取到任务则一直阻塞着。阻塞着的线程会变为WAITING状态。
 * ps：上面说的只是一个大概模型，实际的ThreadPoolExecutor，会做一些优化，线程不是事先创建，而是当任务来临时创建，任务也不是直接加入队列，而是先判断是否有空余线程来执行，没有才加入队列。
 * 
 * ThreadPoolExecutor会将任务交给线程池中的线程来处理，具体的配置需要使用Executors的工厂方法来创建ThreadPoolExecutor，其中指定了线程数量和队列类型。
 * 
 * #线程池解决什么问题
 * 能处理大量异步任务，减少线程的切换次数
 * 
 * 为了应对不同的使用场景，Executors类提供了这几种不同配置的线程池
 *  Executors.newCachedThreadPool()     没有限制线程数量，并且线程自动回收
 *  Executors.newFixedThreadPool(int)   固定的线程数量
 *  Executors.newSingleThreadExecutor() 一个线程
 *  
 * 如果想要调优线程池，阅读下面的指南。
 * 
 * #核心线程数量和最大线程数量
 * 
 *   1、线程数量核心
 *      corePoolSize    核心线程数量，线程一直运行（也可以理解成最小线程数量）
 *      poolSize        当前线程数量
 *      maximumPoolSize 最大线程数量
 *      corePoolSize <= poolSize <= maximumPoolSize
 * 
 *   2、线程数量变化的规则
 *      ThreadPoolExecutor会在corePoolSize和maximumPoolSize之间自动的调节线程数量，如果有一个新任务被提交，并且当前线程数量小于corePoolSize，那么便会创建一个新的线程来执行这个任务，也不管之前的线程是不是空闲，
 *      如果当前线程数量大于corePoolSize并且小于maximumPoolSize，那么只会在队列已满的情况下再创建新的线程来执行任务。
 * 
 *   3、用途说明
 *      如果把maximumPoolSize设置成最大值 例如Integer.MAX_VALUE，你会创建一个可以处理任意数量任务的线程池 newCachedThreadPool
 *      如果corePoolSize和maximumPoolSize相同，那么会创建一个线程数量固定的线程池。 newFixedThreadPool
 * 
 *   4、动态修改线程数量
 *     通常corePoolSize和maximumPoolSize是在构造方法中设置的，但是你也可以通过setCorePoolSize(int)和setmaximumPoolSize(int)来动态改变他们的数量
 * 
 * #线程创建时间
 *  默认情况下，线程是在有新任务来临的时候才会创建并启动，但是我们可以通过prestartCoreThread()和prestartAllCoreThreads()预先启动一个或所有核心线程。这种场景适合再空队列的时候也有线程在运行
 *  
 * #创建线程
	线程是通过ThreadFactory来创建的，默认的是通过 Executors.defaultThreadFactory()来创建，它会拥有相同的ThreadGroup和相同的NORM_PRIORITY优先级并且是非后台线程
	通过指派ThreadFactory，你可以设置线程的名字，线程组，优先级，是否是后台线程，等等。
	如果ThreadFactory创建线程失败，那么线程池可能无法执行任何任务，虽然线程池还在。
	
	如果线程不具有modifyThread运行时权限，就会出现问题，例如更改配置不会立即生效，线程池不能被关闭
	
 * #存活时间
 *	存活时间值的是当线程数量大于corePoolSize时，多余的线程如果闲置的时间超过 keepAliveTime就会终止掉。
 * 	这是为了减少资源的浪费，如果之后任务增多，那么还会再重新创建线程。
 *  如果设置超时时间为 Long.MAX_VALUE TimeUnit.NANOSECONDS，那么线程只会在线程中关闭前被中止
 *  默认对线程超时时间的判断策略只会发生在非核心线程上面[只会在线程数量大于corePoolSize的时候进行]，但是可以通过设置allowCoreThreadTimeOut(boolean) ,在核心线程上也运行该策略
 * 
 * #队列
 * 
 * 任意的BlockingQueue[接口]阻塞队列，都可以实现保存和传输任务的功能。队列的使用和线程池大小有密切关系。
 *  1.如果小于corePoolSize，线程池会添加新线程来执行并不会把任务加入队列。
 *  2.如果大于corePoolSize，会把任务加入队列中。
 *  3.(?队列大小限制)如果超过队列大小，并且当前线程数量小于maximumPoolSize，就会创建新线程来执行任务，否则会拒绝任务抛出异常。
 *
 * 可以把队列分为三种
 *  1.Direct handoffs. 传递性队列[只能有一个]。获取或创建线程，有就获取没有就创建，阻塞版本，线程安全。
 *  	例如 SynchronousQueue，它只负责在线程间传递任务，并不保存这个任务。
 *  	它的一个实际用途是，当我们往队列中添加一个任务，每当当前没有线程能立刻来处理它，所以便会创建一个新的线程来处理它。【实现代码 synchronousQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS)】
 *  	这个策略解决了有大量任务时获取线程的死锁问题。	使用传递性队列，需要把maximumPoolSizes设置成最大，来避免出现拒绝任务执行的情况，这也会出现导致在线程不断疯长，当处理速度远低于生产速度。
 *  
 *  2.Unbounded queues 无边界大小的队列[无限大小]
 *  	例如 LinkedBlockingQueue，当有新任务来临，并且核心线程都在执行任务，这时候它会把新任务加入到队列中然后等待线程执行。
 *  	在无边界大小的队列中maximumPoolSizes就无效了，因为线程数量不会超过corePoolSize。
 *  	这种对于任务之间互相独立的场景非常合适，例如web服务器，它可以承接无限大的任务数量，并且只需要很小的线程数量来执行，这可以解决超大并发的问题，可以让并发更平缓的执行。
 *  
 *  3.Bounded queues 有边界大小的队列[有限大小]
 *  	例如 ArrayBlockingQueue，它能在有限的maximumPoolSizes下，防止资源枯竭。
 *  	队列大小和线程数量可能会相互影响
 *  		使用大的队列和小的线程池，会减少CPU使用，系统资源分配，线程上下文切换，但是会导致低吞吐量（每秒钟处理任务数量），如果任务被频繁的阻塞例如IO操作，系统可能要花费更多的时间。
 *  		使用小的队列和大的线程池，会增加CPU使用，但会造成更多的系统开销，也有可能会降低吞吐量。
 *  
 * #拒绝任务
 * 	执行execute()加入新任务时，如果线程池已经shutdown或者最大线程数量到头和队列容量也满了的情况下任务会被拒绝执行。
 *  拒绝的任务会通过RejectedExecutionHandler.rejectedExecution(Runnable, ThreadPoolExecutor)抛出异常，总共有4种异常类型。
 *  1.ThreadPoolExecutor.AbortPolicy
 *  2.ThreadPoolExecutor.CallerRunsPolicy
 *  3.ThreadPoolExecutor.DiscardPolicy
 *  4.ThreadPoolExecutor.DiscardOldestPolicy
 * 
 * #钩子回调方法
 * 	 beforeExecute(Thread, Runnable)
 * 	 afterExecute(Runnable, Throwable)
 * 
 * #队列操作
 * 	通过getQueue()可以获得在线程池中的队列，但不推荐使用，除非你是为了调试debug队列，或者你有很大的队列数量你想取消其中的一些任务。
 * 
 * #确保线程池能够终止
 * 	如果你想确保线程池能彻底的关闭，而不让它一直运行，即使不掉用shutdown，也让它自动关闭，可以通过设置allowCoreThreadTimeOut(boolean)可以让线程池中的线程自动关闭。
 * 
 * http://blog.csdn.net/ajian005/article/details/18894855
 * http://www.cnblogs.com/exe19/p/5359885.html
 */
public class ThreadPoolExecutor_Doc {

	public static void main(String[] args) {
		
	}
}
