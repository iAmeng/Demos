package imeng.javafundation.purejava;

import imeng.javafundation.purejava.extend.ChildJava;

/**
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/4/1 11:29
 * @Version:
 */
public class Main {
    /** static 会在 main之前被调用的 */
    static {
        System.out.println("Hello Wordld! up at mainfun");
    }


    public static void main(String args[]) {
        System.out.println("Hello Wrold");

        ChildJava cj = new ChildJava();


    }

    /** static 会在 main之前被调用的 */
    static {
        System.out.println("Hello Wordld! below main fun");
    }

}

