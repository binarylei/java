package com.github.binarylei.thread.volatile0;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leigang
 * @version 2019-05-16
 * @since 2.0.0
 */
public class VolatileTest0 {

    public static void main(String[] args) throws Exception {
        AtomicBoolean isRunning = new AtomicBoolean(true);
        AtomicInteger count = new AtomicInteger();
        while (isRunning.get()) {
            System.out.println(count.incrementAndGet());
            Container container = new Container();
            Thread thread1 = new Thread(container::create);
            Thread thread2 = new Thread(() -> {
                SomeThing instance = container.get();
                if (instance == null) {
                    System.out.println(count.get() + "：" + instance);
                } else if (instance.f1 != 1 || instance.f2 != 2) {
                    isRunning.set(false);
                    System.out.println(count.get() + "：" + instance);
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        }
    }

    public static class Container {
        private SomeThing instance;

        private void create() {
            instance = new SomeThing();
        }

        private SomeThing get() {
            return instance;
        }
    }

    private static class SomeThing {
        private int f1;   // 触发部分初始化问题
        private int f2;

        {
            f1 = 1;
            f2 = 2;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", f1, f2);
        }
    }
}
