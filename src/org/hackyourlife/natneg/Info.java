package org.hackyourlife.natneg;
public class Info {
	public String	ip;
	public int	port;
	public Info(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	public String getIP() {
		return ip;
	}
	public int getPort() {
		return port;
	}
}
