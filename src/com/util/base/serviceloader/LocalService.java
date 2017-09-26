package com.util.base.serviceloader;

/**
 * @author y15079
 * @create 2017-09-26 11:45
 * @desc
 **/
public class LocalService  implements IService{
		@Override
		public String sayHello() {
			return "Hello Local!!";
		}

		@Override
		public String getScheme() {
			return "local";
		}
}
