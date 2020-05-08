package com.tw.hadoop2;

public class Main {
    public static void main(String[] args){
        Object o = new Object();

        new Thread(new PrintRunnable(o, 1)).start();
        new Thread(new PrintRunnable(o, 2)).start();
        new Thread(new PrintRunnable(o, 3)).start();
    }

}

class PrintRunnable implements Runnable{
    private final Object o;
    private int threadId;
    private static volatile int num = 1;

    PrintRunnable(Object o, int threadId){
        this.o = o;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while(num < 65){
            synchronized (o){
                while(num/5%3 + 1 != threadId){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for(int i=0; i<5; i++, num++)
                    System.out.println("线程"+threadId+": "+num);
                

                o.notifyAll();
            }
        }
    }
}

