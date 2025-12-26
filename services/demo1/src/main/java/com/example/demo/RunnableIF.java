package com.example.demo;

public class RunnableIF {
    // Creating A Thread using Runnable Interface
    public static void main(String[] args) {
        Thread threadOne = new Thread(new ThreadOneRunnable());
        Thread threadTwo = new Thread(new ThreadTwoRunnable());
        threadOne.start();  // Once This method get call Jvm will
        threadTwo.start();  // start this thread which will be in runnable state
    }
}
class ThreadOneRunnable implements Runnable{
    @Override
    public void run() {
            for (int i=0; i <=5;i++) {
                System.out.println("Thread Created using Runnable Thread one "+i);
            }
        }
}
class ThreadTwoRunnable implements Runnable{
    @Override
    public void run() {
        for (int i=0; i <=5;i++) {
            System.out.println("Thread Created using Runnable Thread Two "+i);
        }
    }
}
