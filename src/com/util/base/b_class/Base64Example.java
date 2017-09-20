package com.util.base.b_class;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author y15079
 * @create: 9/20/17 11:44 PM
 * @desc:
 */
public class Base64Example {
    public static void main(String[] args) throws Exception{
        String src="my name is hello";
        BASE64Encoder encoder=new BASE64Encoder();
        String encode=encoder.encode(src.getBytes());
        System.out.println("encode: "+encode);

        BASE64Decoder decoder=new BASE64Decoder();
        String decode=new String(decoder.decodeBuffer(encode));
        System.out.println("decode: "+decode);
    }
}
