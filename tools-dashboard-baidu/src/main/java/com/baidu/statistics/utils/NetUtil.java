package com.baidu.statistics.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by benjaminkc on 16/12/13.
 */
@Slf4j
public class NetUtil {
//    public static void main(String[] args) throws UnknownHostException, SocketException {
//        InetAddress ia = InetAddress.getLocalHost();
//        System.out.println(ia);
//        System.out.println(getLocalMac());
//    }

    public static String getLocalMac() {
//        byte[] mac = new byte[0];
//        try {
//            InetAddress ia = InetAddress.getLocalHost();
//
//            //获取网卡，获取地址
//            mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//
//        StringBuffer sb = new StringBuffer("");
//        for(int i=0; i<mac.length; i++) {
//            if(i!=0) {
//                sb.append("-");
//            }
//            //字节转换为整数
//            int temp = mac[i]&0xff;
//            String str = Integer.toHexString(temp);
//
//            if(str.length()==1) {
//                sb.append("0"+str);
//            }else {
//                sb.append(str);
//            }
//        }
//        return sb.toString().toUpperCase();

        String host = "";
        try {
            host = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {}

        return host;
    }
}
