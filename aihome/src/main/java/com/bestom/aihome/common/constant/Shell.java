package com.bestom.aihome.common.constant;

public class Shell {

    public static final String IFCONFIG="ifconfig usb0 192.168.32.4";
    public static final String RULE1="ip rule add from 192.168.32.4 table 16";
    public static final String RULE2="ip rule add to 192.168.32.3 table 16";
    public static final String Route="ip route add 192.168.32.0/24 dev usb0 src 192.168.32.4 table 16";
    public static final String REBOOT="reboot";

}
