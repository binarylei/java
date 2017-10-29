package com.github.binarylei.design.master_worker;

public class Main {

    public static void main(String[] args) {
        Master master = new Master(new Worker());

        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setId(i);
            task.setName("task-" + i);
            task.setCount(i);
            master.submit(task);
        }

        master.execute();
        long t1 = System.currentTimeMillis();
        while (true) {
            if (master.isComplete()) {
                long t = System.currentTimeMillis() - t1;
                System.out.printf("执行结果：%s；执行时间：%s", master.getResult(), t);
                break;
            }
        }
    }
}
