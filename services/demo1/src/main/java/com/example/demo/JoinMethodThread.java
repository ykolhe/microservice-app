package com.example.demo;
/*
        The join() method is used when you want the main program (or another thread)
        to wait until this thread finishes before moving on.
 */
public class JoinMethodThread extends Thread{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            for(int i=0;i<5;i++){
                System.out.println("Thread One "+i);
            }
        });
        Thread thread1 = new Thread(() ->{
            for(int i=0;i<25;i++){
                System.out.println("Thread Two "+i);
            }
        });
        System.out.println("Check the Execution of Thread");
        thread.start();
        thread.join();
        thread1.start();
        thread1.join();
        System.out.println("Done execution of Thread");
    }

}
