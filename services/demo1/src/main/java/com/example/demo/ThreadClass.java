package com.example.demo;

public class ThreadClass  {
    public static void main(String[] args) {
        Thread thread = new ThreadOne();
        thread.start();
        Thread thread2 = new ThreadTwo();
        thread2.start();
    }
}
class ThreadOne extends Thread{
    @Override
    public void run() {
        for (int i=0; i <= 5; i++){
            System.out.println("Thread Created using Runnable Thread one "+i);
        }
    }
}
class ThreadTwo extends Thread{
    @Override
    public void run() {
        for(int i=0;i <=5;i++){
            System.out.println("Thread Created using Runnable Thread Two "+i);
        }
    }
}
