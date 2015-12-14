package com.service;

import com.util.DateUtils;
import com.util.IPUtils;
import com.util.TurnOffPCUtils;

public class PCService {
    private TurnOffPCUtils turnOff = new TurnOffPCUtils();

    public void turnOffPC(double time){
        turnOff.turnOffPC(time);
    }
    public void cancelOffPC(){
        turnOff.cancelOffPC();
    }
    public String currentDetailTime(){
        return DateUtils.getCurrentDetailTime();
    }
    public String getIpAddr(String ipAddr){
        return IPUtils.getAddressByIP(ipAddr);
    }
}
