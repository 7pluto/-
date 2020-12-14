package util;

import java.net.InetAddress;

import javax.swing.JOptionPane;

public class GetLocalIpUtil {

	
	public String getLocalIp() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "获取本机ip失败");
		}
		
		return address.toString().substring(address.toString().lastIndexOf("/")+1);
	}
}
