package com.github.binarylei._2_1conn;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait/notify方法实现线程单挑通信(注意这两个方法是Object类的方法)
 *   1. wait和notity必须配合synchronized关键字使用
 *   2. wait方法(关闭线程)释放锁，notify(唤醒线程)方法不释放锁
 * 缺点：通知不实时，使用CountDownLatch实现实时通知
 */
public class ListAdd2 {
	private /*volatile*/ static List list = new ArrayList();
	
	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {

		final ListAdd2 list2 = new ListAdd2();
		final Object lock = new Object();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					synchronized (lock) {
						System.out.println("t1启动..");
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
								lock.notify();
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				synchronized (lock) {
					System.out.println("t2启动..");
					if(list2.size() != 5){
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				}
			}
		}, "t2");

		//必须先启动t2线程
		t2.start();
		t1.start();
	}
}
