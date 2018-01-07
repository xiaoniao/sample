package com.liuzhuang.projectc;

/**
 * Reference queues, to which registered reference objects are appended by the
 * garbage collector after the appropriate reachability changes are detected.
 * 
 * 一个引用的队列
 * 当已经注册过的对象被垃圾回收器侦查到引用可达性发生变化的时候就会追加到此队列中.
 */
public class ReferenceQueueT<T> {
  
  public static void main(String[] args) {
    String value = "hello";
    ReferenceQueueT<String> referenceQueue = new ReferenceQueueT<>();
    if (referenceQueue.enqueue(new ReferenceT<String>(value) {
      
    })) {
      System.out.println(referenceQueue.poll().get());
    }
  }

  /**
   * Constructs a new reference-object queue.
   */
  public ReferenceQueueT() { }

  //空引用队列
  private static class Null<S> extends ReferenceQueueT<S> {
      boolean enqueue(ReferenceT<? extends S> r) {
          return false;
      }
  }

  static ReferenceQueueT<Object> NULL = new Null<>();//空队列
  static ReferenceQueueT<Object> ENQUEUED = new Null<>();//空入队队列

  static private class Lock { };//用来多线程加锁的
  private Lock lock = new Lock();
  private volatile ReferenceT<? extends T> head = null;//头引用
  private long queueLength = 0;//队列长度

  /**
   * 入队列  引用queue是NULL或者ENQUEUED不允许入队列
   * 加入到头部
   * @param r 引用
   * @return
   */
  boolean enqueue(ReferenceT<? extends T> r) { /* Called only by Reference class */
      synchronized (lock) {
          // Check that since getting the lock this reference hasn't already been
          // enqueued (and even then removed)
          ReferenceQueueT<?> queue = r.queue;
          if ((queue == NULL) || (queue == ENQUEUED)) {
              System.out.println("入队列失败");
              return false;//入队列失败
          }
          assert queue == this;
          r.queue = ENQUEUED;
          r.next = (head == null) ? r : head;//只有一个元素的时候,下一个元素是自己
          head = r;
          queueLength++;
          /*if (r instanceof FinalReference) {
              sun.misc.VM.addFinalRefCount(1);
          }*/
          lock.notifyAll();
          return true;
      }
  }

  /**
   * 出头部队列
   */
  @SuppressWarnings("unchecked")
  private ReferenceT<? extends T> reallyPoll() {       /* Must hold lock */
      ReferenceT<? extends T> r = head;
      if (r != null) {
          head = (r.next == r) ?
              null :
              r.next; // Unchecked due to the next field having a raw type in Reference
          r.queue = NULL;
          r.next = r;
          queueLength--;
          /*if (r instanceof FinalReference) {
              sun.misc.VM.addFinalRefCount(-1);
          }*/
          return r;
      }
      return null;
  }

  /**
   * 出队列
   * Polls this queue to see if a reference object is available.  If one is
   * available without further delay then it is removed from the queue and
   * returned.  Otherwise this method immediately returns <tt>null</tt>.
   *
   * @return  A reference object, if one was immediately available,
   *          otherwise <code>null</code>
   */
  public ReferenceT<? extends T> poll() {
      if (head == null)
          return null;
      synchronized (lock) {
          return reallyPoll();
      }
  }

  /**
   * 出队列加了超时时间
   * 
   * Removes the next reference object in this queue, blocking until either
   * one becomes available or the given timeout period expires.
   *
   * <p> This method does not offer real-time guarantees: It schedules the
   * timeout as if by invoking the {@link Object#wait(long)} method.
   *
   * @param  timeout  If positive, block for up to <code>timeout</code>
   *                  milliseconds while waiting for a reference to be
   *                  added to this queue.  If zero, block indefinitely.
   *
   * @return  A reference object, if one was available within the specified
   *          timeout period, otherwise <code>null</code>
   *
   * @throws  IllegalArgumentException
   *          If the value of the timeout argument is negative
   *
   * @throws  InterruptedException
   *          If the timeout wait is interrupted
   */
  public ReferenceT<? extends T> remove(long timeout)
      throws IllegalArgumentException, InterruptedException
  {
      if (timeout < 0) {
          throw new IllegalArgumentException("Negative timeout value");
      }
      synchronized (lock) {
          ReferenceT<? extends T> r = reallyPoll();
          if (r != null) return r;
          long start = (timeout == 0) ? 0 : System.nanoTime();
          for (;;) {
              lock.wait(timeout);
              r = reallyPoll();
              if (r != null) return r;
              if (timeout != 0) {
                  long end = System.nanoTime();
                  timeout -= (end - start) / 1000000;
                  if (timeout <= 0) return null;
                  start = end;
              }
          }
      }
  }

  /**
   * 出队列
   * Removes the next reference object in this queue, blocking until one
   * becomes available.
   *
   * @return A reference object, blocking until one becomes available
   * @throws  InterruptedException  If the wait is interrupted
   */
  public ReferenceT<? extends T> remove() throws InterruptedException {
      return remove(0);
  }

}
