package com.hms.app.utils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static final Random random = new Random();
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

    public static String getMacAddress() {
        String result = "";

        try {
            for (NetworkInterface ni : Collections.list(
                    NetworkInterface.getNetworkInterfaces())) {
                byte[] hardwareAddress = ni.getHardwareAddress();

                if (hardwareAddress != null) {
                    for (int i = 0; i < hardwareAddress.length; i++)
                        result += String.format((i == 0 ? "" : "-") + "%02X", hardwareAddress[i]);

                    return result;
                }
            }

        } catch (SocketException e) {
            System.out.println("Could not find out MAC Adress. Exiting Application ");
            System.exit(1);
        }
        return result;
    }

    public static int createRandomNumberBetween(int min, int max) {

        return random.nextInt(max - min + 1) + min;
    }
    
    public static Date getToday() {
		Date today = new Date();
		String todayAsString = df.format(today);
		try {
			today = df.parse(todayAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("todayAsString-->"+todayAsString);
		return today;
    }
    
    public static Date getYesterday() {
    	Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);
		String yesterdayAsString = df.format(yesterday);
		try {
			yesterday = df.parse(yesterdayAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("yesterdayAsString-->"+yesterdayAsString);
		return yesterday;
    }
    
    public static Date getLastWeekDate() {
    	Date lastWeekDate = new Date(System.currentTimeMillis()-7*24*60*60*1000);
		String lastWeekDateAsString = df.format(lastWeekDate);
		try {
			lastWeekDate = df.parse(lastWeekDateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("lastWeekDateAsString-->"+lastWeekDateAsString);
		return lastWeekDate;
    }
    
}