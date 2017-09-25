package com.util.base.b_class;

import java.util.Observable;
import java.util.Observer;

/**
 * @author y15079
 * @create 2017-09-25 11:15
 * @desc
 *
 * 如果想要实现观察者模式，则必须依靠java.util包中提供的Observable类和Observer接口
 *
 * 观察者设计模式
 * 现在很多的购房者都在关注着房子的价格变化，每当房子价格变化的时候，所有的购房者都可以观察得到。
 * 实际上以上的购房者就是观察者，他们所关注的房价就是被观察者
 *
 * 其中要求，被观察者需要继承Observable类，观察则需要实现Observer接口
 *
 **/
public class ObservableExample {
	public static class House extends Observable{
		private double price;

		public House(double price) {
			this.price = price;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			if (this.price!=price){
				this.price = price;
				setChanged();//标注价格已经被更改
				this.notifyObservers();//通知观察者数据已被更改
			}
		}

		@Override
		public String toString() {
			return "当前房价为："+price;
		}
	}

	public static class HousePriceObserver implements Observer{
		private String name;

		public HousePriceObserver(String name) {
			this.name = name;
		}

		@Override
		public void update(Observable o, Object arg) {
			//这里最好判断一下通知是否来自于房价，有可能来自其他地方
			if (o instanceof House){
				House house=(House)o;
				System.out.println("购物者"+name+"观察到房价已调整为："+house.getPrice());
			}
		}
	}

	public static void main(String[] args) {
		House house=new House(10000);
		HousePriceObserver A=new HousePriceObserver("A");
		HousePriceObserver B=new HousePriceObserver("B");
		HousePriceObserver C=new HousePriceObserver("C");
		house.addObserver(A);
		house.addObserver(B);
		house.addObserver(C);
		System.out.println(house);
        house.setPrice(6000);
		house.setPrice(8000);
	}
}
