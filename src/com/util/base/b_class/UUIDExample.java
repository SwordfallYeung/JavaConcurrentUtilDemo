package com.util.base.b_class;

import java.util.UUID;

/**
 * @author y15079
 * @create: 9/26/17 10:57 PM
 * @desc:
 *
 *    UUID 来作为数据库数据表主键是非常不错的选择，保证每次生成的UUID 是唯一的。
 *
 */
public class UUIDExample {

    public static void createUUID(){
        for(int i=0;i<10;i++){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }
    }

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args) {
//        createUUID();
        getUUID(3);
    }
}
