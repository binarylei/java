package com.binarylei.concurrent.theory;

/**
 * @author binarylei
 * @version 2020-03-17
 */
public class VisibilityTest {

    private /*volatile*/ boolean running;

    public void stop() {
        this.running = false;
    }
}
