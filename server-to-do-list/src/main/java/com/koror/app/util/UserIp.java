package com.koror.app.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UserIp {

    public static String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
