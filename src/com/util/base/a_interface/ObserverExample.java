package com.util.base.a_interface;

import java.util.Observable;
import java.util.Observer;

/**
 * @author y15079
 * @create: 9/19/17 12:44 AM
 * @desc:
 *
 * 在Java中通过Observable类和Observer接口实现了观察者模式。
 * Observer对象是观察者，Observable对象是被观察者。
 *
 * 实现观察者模式：
 * [1]创建观察者类，它继承自java.util.Observable类;
 * [2]创建观察者类，它实现java.util.Observer接口;
 * [3]对于被观察者类
 *     void addObserver(Observer o)//把观察者对象添加到观察者对象列表中
 *     当被观察事件发生时，执行：
 *         setChanged(); //用来设置一个内部标志位注明数据发生了变化;
 *         notifyObservers();//调用观察者对象列表中所有的Observer的update()方法，通知它们数据发生了变化
 *     只有在setChange()被调用后，notifyObservers()才会去调用update()
 * [4] 对于观察者类，实现Observer接口的唯一方法update
 *     void update(Observable o, Object arg)
 *     形参Object arg, 对应一个由notifyObservers(Object arg);传递来的参数，当执行的是notifyObservers();时，arg为null
 *
 *
 *
 */
public class ObserverExample {


    //NumObserable是一个被观察者，当它的成员变量data的数值发生变化时，会通知所有的观察者。
    public static class NumObservable extends Observable{
        private int data=0;

        public int getData() {
            return data;
        }

        public void setData(int i) {
            data=i;
            setChanged();
            notifyObservers();
        }
    }

    //NumObserver是观察者。当它的被观察者(NumObserable)执行了notifyObservers()后，它会执行uodate()方法。
    public static class NumObserver implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            if (o instanceof NumObservable){
                NumObservable myObservable=(NumObservable) o;
                System.out.println("Data has changed to "+myObservable.getData());
            }
        }
    }

    //在这里将观察者加入到被观察者的观察列表中。
    public static void main(String[] args) {
        NumObservable number=new NumObservable();
        number.addObserver(new NumObserver());
        number.setData(1);
        number.setData(2);
        number.setData(3);
    }

}
