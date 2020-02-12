package com.projectdb;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ComputerName {
	public static String getMachineName() {
		String hostname = "";
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException ex) {
			System.out.println("Hostname can not be resolved");
		}
		return hostname;
	}
}
