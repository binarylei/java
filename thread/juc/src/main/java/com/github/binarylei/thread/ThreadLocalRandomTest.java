package com.github.binarylei.thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author leigang
 * @version 2019-06-02
 * @since 2.0.0
 */
public class ThreadLocalRandomTest {

    @Test
    public void testRandom() {
        long seed = 343L;
        Random random1 = new Random(seed);
        Random random2 = new Random(seed);

        Assert.assertEquals(random1.nextInt(), random2.nextInt());
        Assert.assertEquals(random1.nextInt(), random2.nextInt());
        Assert.assertEquals(random1.nextInt(), random2.nextInt());
    }

    @Test
    public void testThreadLocalRandom() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        System.out.println(random.nextInt());
    }
}
