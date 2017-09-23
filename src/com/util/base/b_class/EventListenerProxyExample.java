package com.util.base.b_class;

import java.util.*;

/**
 * @author y15079
 * @create 2017-09-23 14:35
 * @desc
 * Java事件机制包括三个部分：事件、事件监听器、事件源。
 * 其中事件类中包含事件源的实例，来标识事件的发出者；
 * 事件监听器类则包含了事件被触发时的响应函数，业务逻辑写在该响应函数中；
 *而事件源则有一个事件监听器列表，当事件触发时，通知所有的监听者，采用的是观察者模式 (发布-订阅模式)。
 *
 **/
public class EventListenerProxyExample {

	/**
	 * 事件类，包含了事件源
	 *
	 * 事件源和事件相当于被观察者
	 */
	public static class ButtonClickEvent extends EventObject{
       private static final long serialVersionUID=1L;

       //事件源
		private Object mSourceObject=null;
		private String mTag="";

		public ButtonClickEvent(Object sObject) {
			super(sObject);
			this.mSourceObject = sObject;
		}

		public ButtonClickEvent( Object sObject, String tag) {
			super(sObject);
			this.mSourceObject = sObject;
			this.mTag = tag;
		}

		//获取事件源
		public Object getmSourceObject() {
			return mSourceObject;
		}

		//设置事件源
		public void setmSourceObject(Object mSourceObject) {
			this.mSourceObject = mSourceObject;
		}

		public String getmTag() {
			return mTag;
		}

		public void setmTag(String mTag) {
			this.mTag = mTag;
		}
	}

	/**
	 * 事件监听器。实现java.util.EventListener接口,注册在事件源上,当事件源触发事件时,取得相应的监听器调用其内部的回调方法。
	 *
	 * 相当于观察者
	 */
	private static class ButtonClickListenerInner implements EventListener{

		/**
		 * 点击事件
		 * @param event
		 */
		public void ButtonClicked(ButtonClickEvent event){
			//获取事件源
			ButtonDemo source = (ButtonDemo)event.getSource();
			System.out.println("内部静态监听类@_@ 你点击的是 : " + source.getItemString()) ;
		}


	}

	/**
	 * 事件源。事件触发的地方，由于事件源的某项属性或状态发生了改变(比如Button被单击等)导致某项事件发生。
	 * 换句话说就是生成了相应的事件对象。因为事件监听器要注册在事件源上,
	 * 所以事件源类中应该含有用来存储事件监听器的容器(List,Set等等)。
	 *
	 * 事件源模拟类,采用的是观察者模式
	 * 被观察者
	 */
	public static class ButtonDemo{
        //item文本文字
		private String mItemName="";
         //监听器哈希集合，可以注册多个监听器
		private Set<EventListener> mClickListeners=null;

		/**
		 * 构造函数
		 */
		public ButtonDemo() {
			this.mItemName = "Default Item Name";
			this.mClickListeners = new HashSet<EventListener>();
		}

		/**
		 * 构造函数
		 * @param mItemName
		 */
		public ButtonDemo(String mItemName) {
			this.mItemName = mItemName;
			this.mClickListeners = new HashSet<EventListener>();
		}

		/**
		 * 添加监听器
		 * @param listener
		 */
		public void AddItemClickListener(EventListener listener){
			//添加到监听器列表
			this.mClickListeners.add(listener);
		}

		/**
		 * 模拟点击事件,触发事件则通知所有监听器
		 */
		public void ButtonClick(){
           //通知所有监听者
			Notifies();
		}

		/**
		 * 通知所有监听者
		 */
		private void Notifies(){
			Iterator<EventListener> iterator=mClickListeners.iterator();
			while (iterator.hasNext()){
				//获取当前的对象
				ButtonClickListenerInner listener=(ButtonClickListenerInner)iterator.next();
				//事件触发，事件的构造函数参数为事件源
				listener.ButtonClicked(new ButtonClickEvent(this));
			}
		}

		/**
		 * 返回该项的名字
		 * @return
		 */
		public String getItemString() {
			return mItemName;
		}
	}

	public static void main(String[] args) {
		ButtonDemo buttonDemo=new ButtonDemo("Hello,I am a ButtonDemo");
		//添加监听器
		buttonDemo.AddItemClickListener(new ButtonClickListenerInner());
		//事件触发
		buttonDemo.ButtonClick();
	}

}
