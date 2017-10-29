package com.github.binarylei._2_2;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BlockQueue: 该队列支持阻塞机制，阻塞的放入和得到数据
 * put(Object o): BlockQueue无空间则调用此方法的线程被阻塞，直到BlockQueue有空间才被激活
 * take(): BlockQueue为空则调用此方法的线程被阻塞，直到BlockQueue有数据才被激活
 */
public class MyQueue {

	private final LinkedList<Object> list = new LinkedList<Object>();

	//元素个数计数器
	private final AtomicInteger count = new AtomicInteger(0);
	
	private final int maxSize;
	private final int minSize = 0;

	//锁
	private final Object lock = new Object();
	
	public MyQueue(int maxSize){
		this.maxSize = maxSize;
	}

	public void put (Object obj) {
		synchronized(lock){
			//1. 没有空间阻塞线程
			while(count.get() == maxSize){
				try {
					lock.wait();//阻塞
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//2. BlockQueue有空间则添加数据且计数器自加
			list.add(obj);
			count.getAndIncrement();
			System.out.println(" 元素 " + obj + " 被添加 ");
			//3. BlockQueue有数据激活take()线程
			lock.notify();
		}
	}
	
	public Object take(){
		Object temp = null;
		//1. BlockQueue无数据阻塞线程
		synchronized (lock) {
			while(count.get() == minSize){
				try {
					lock.wait();//阻塞
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//2. BlockQueue有数据并取出数据且计数器自减
			count.getAndDecrement();
			temp = list.removeFirst();
			System.out.println(" 元素 " + temp + " 被消费 ");
			//3. BlockQueue有数据激活put()线程
			lock.notify();
		}
		return temp;
	}
	
	public int size(){
		return count.get();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		final MyQueue m = new MyQueue(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("当前元素个数：" + m.size());
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				m.put("h");
				m.put("i");
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
					Object t1 = m.take();
					//System.out.println("被取走的元素为：" + t1);
					Thread.sleep(1000);
					Object t2 = m.take();
					//System.out.println("被取走的元素为：" + t2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t1.start();
		Thread.sleep(1000);
		t2.start();
	}
}
