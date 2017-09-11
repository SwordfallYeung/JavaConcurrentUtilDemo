package com.util.concurrent.d_synchronizedUtil;

import java.util.concurrent.Exchanger;

/**
 * @author y15079
 * @create: 9/10/17 7:49 PM
 * @desc:
 *
 * Exchanger让两个线程可以互换信息。
 * 例子中服务生线程往空的杯子里倒水，顾客线程从装满水的杯子里喝水，
 * 然后通过Exchanger双方互换杯子，服务生接着往空杯子里倒水，顾客接着喝水，
 * 然后交换，如此周而复始。
 *
 * exchange(V x)
 * Waits for another thread to arrive at this exchange point (unless the current thread is interrupted),
 * and then transfers the given object to it, receiving its object in return
 */
public class ExchangerExample {

    public static void main(String[] args) throws Exception{
        //初始化一个Exchanger，并规定可交换的信息类型是杯子
    final Exchanger<Cup> exchanger=new Exchanger<Cup>();
        //初始化一个空的杯子和装满水的杯子
        final Cup initialEmptyCup=new Cup(false);
        final Cup initialFullCup=new Cup(true);

        new Thread(new Waiter(initialEmptyCup,exchanger)).start();
        new Thread(new Customer(initialFullCup,exchanger)).start();
    }

    //描述一个装水的杯子
    public static class Cup{
        //标识杯子是否有水
        private boolean full=false;

        public Cup(boolean full) {
            this.full = full;
        }

        //添水，假设需要5s
        public void addWater(){
            if (!this.full){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.full=true;
            }
        }
        //喝水，假设需要10s
        public void drinkWater(){
            if (this.full){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.full=false;
            }
        }
    }


    //服务生线程
    public static class Waiter implements Runnable{

        private Cup currentCup;
        private Exchanger<Cup> exchanger;

        public Waiter(Cup currentCup, Exchanger<Cup> exchanger) {
            this.currentCup = currentCup;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
//            Cup currentCup = initialEmptyCup;
            try {
                int i=0;
                while (i<2){
                    synchronized (Waiter.class){
                        System.out.println("服务生开始往杯子中添水："+System.currentTimeMillis());
                        //往空的杯子里加水
                        currentCup.addWater();
                        System.out.println("服务生添水完毕："+System.currentTimeMillis());
                    }

                    currentCup=exchanger.exchange(currentCup);
                    System.out.println("服务生与顾客交换杯子完毕："+System.currentTimeMillis());
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //顾客线程
    public static class Customer implements Runnable{

        private Cup currentCup;
        private Exchanger<Cup> exchanger;

        public Customer(Cup currentCup, Exchanger<Cup> exchanger) {
            this.currentCup = currentCup;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
//            Cup currentCup=initialFullCup;
            try {
                int i=0;
                while (i<2){
                    synchronized (Customer.class){
                        System.out.println("顾客开始喝水："+System.currentTimeMillis());
                        //把杯子里的水喝掉
                        currentCup.drinkWater();
                        System.out.println("顾客喝水完毕："+System.currentTimeMillis());
                    }
                    //将空杯子和服务生的满杯子交换
                    System.out.println("顾客等待与服务生交换杯子："+System.currentTimeMillis());
                    currentCup=exchanger.exchange(currentCup);
                    System.out.println("顾客与服务生交换杯子完毕："+System.currentTimeMillis());
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
