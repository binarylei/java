package com.github.binarylei._2_1conn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch实现实时通信
 */
public class ListAdd3 {
	private volatile static List list = new ArrayList();

	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd3 list2 = new ListAdd3();

		//final Object lock = new Object();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					//synchronized (lock) {
						System.out.println("t1启动..");
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
								countDownLatch.countDown();
								//lock.notify();
							}
						}
					//}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				//synchronized (lock) {
					System.out.println("t2启动..");
					if(list2.size() != 5){
						try {
							countDownLatch.await();
							//lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				//}
			}
		}, "t2");

		t2.start();
		t1.start();
		/**
		 * t2启动..
		 * t1启动..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 已经发出通知..
		 * 当前线程：t2收到通知线程停止..
		 * Exception in thread "t2" java.lang.RuntimeException
		 * at com.herolei._2_1conn.ListAdd3$2.run(ListAdd3.java:58)
		 * at java.lang.Thread.run(Thread.java:745)
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 * 当前线程：t1添加了一个元素..
		 */
	}
	
}
