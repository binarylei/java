package com.binarylei.jvm.bytecode;

/**
 * @author binarylei
 * @version 2021-04-20
 */
public class SwitchTest {
    public void testInt(int i) {
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public void testByte(byte i) {
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public void testShort(short i) {
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public void testChar(char c) {
        switch (c) {
            case '1':
                break;
            case '2':
                break;
            case '3':
                break;
            default:
                break;
        }
    }

    public void testString(String s) {
        switch (s) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            default:
                break;
        }
    }

    public void testEnum(SwitchEnum e) {
        switch (e) {
            case A:
                break;
            case B:
                break;
            case C:
                break;
            default:
                break;
        }
    }

    /**
     * tableswitch 指令
     *
     * @param i
     */
    public void testTableSwitch(int i) {
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    /**
     * lookupswitch 指令
     *
     * @param i
     */
    public void testLookupSwitch(int i) {
        switch (i) {
            case 1:
                break;
            case 100:
                break;
            case 1000:
                break;
            default:
                break;
        }
    }

}

enum SwitchEnum {
    A, B, C;
}
