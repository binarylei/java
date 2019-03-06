package com.github.binarylei.collection.other;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;

/**
 * @author: leigang
 * @version: 2018-05-05
 */
public class WeakHashMapTest {

    // WeakHashMap 要求键为弱引用
    @Test
    public void WeakHashMapTest1() {
        // 调用 GC 后，1和2被回收
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(new String("1"), "1");
        weakHashMap.put(new String("2"), "2");
        weakHashMap.put("3", "3");
        weakHashMap.put("4", "4");

        System.out.println(weakHashMap);
        System.gc();
        System.runFinalization();
        System.out.println(weakHashMap);
    }

    // IdentityHashMap 只比较内存中的地址
    @Test
    public void IdentityHashMapTest() throws InterruptedException {
        IdentityHashMap<String, String> map = new IdentityHashMap<>();
        map.put("1", "1");
        map.put("1", "2");
        System.out.println(map);

        map.put(new String("1"), "1");
        map.put(new String("1"), "1");
        System.out.println(map);
    }

    // EnumMap 要求键为枚举类型
    @Test
    public void EEnuMapTest() throws InterruptedException {
        EnumMap<State, String> map = new EnumMap<>(State.class);
        map.put(State.Connected, "1");
        map.put(State.DisConnected, "1");
        System.out.println(map);
    }

    enum State {
        DisConnected,Connecting,Connected
    }

    @Test
    public void WeakReferenceTest() throws InterruptedException {
        // 常量不能回收
        //String str = "binarylei";
        String str = new String("binarylei1");

        WeakReference<String> wr = new WeakReference<String>(str);

        System.out.println(wr.get());
        System.gc();
        System.runFinalization();
        System.out.println(wr.get()); //????
    }
}
