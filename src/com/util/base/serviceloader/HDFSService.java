package com.util.base.serviceloader;

/**
 * @author y15079
 * @create 2017-09-26 11:44
 * @desc
 **/
public  class HDFSService implements IService {
		@Override
		public String sayHello() {
			return "Hello HDFS!!";
		}

		@Override
		public String getScheme() {
			return "hdfs";
		}
}
