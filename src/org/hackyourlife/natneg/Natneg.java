// This tool returns your public ip

package org.hackyourlife.natneg;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class Natneg {
	public final static String	natneghost = "natneg3.gamespy.com";
	public final static int		natnegport = 27901;
	public static Info getIP(DatagramSocket datagramSocket) throws IOException, UnknownHostException {
		DatagramPacket in;
		DatagramPacket out;
		InetAddress addr = InetAddress.getByName(natneghost);
		byte[] query = {
			(byte)0xFD, (byte)0xFC, (byte)0x1E, (byte)0x66, (byte)0x6A, (byte)0xB2, (byte)0x04, (byte)0x0A,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x03, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
			(byte)0x00
		};
		byte[] buffer = new byte[0x100];
		out = new DatagramPacket(query, query.length, addr, natnegport);
		datagramSocket.send(out);
		in = new DatagramPacket(buffer, buffer.length);
		datagramSocket.receive(in);
		String ip = (buffer[0x0F] & 0xFF) + "." + (buffer[0x10] & 0xFF) + "." + (buffer[0x11] & 0xFF) + "." + (buffer[0x12] & 0xFF);
		int port = (buffer[0x13] & 0xFF) << 8 | (buffer[0x14] & 0xFF);
		return new Info(ip, port);
	}

	public static void main(String[] args)
	    throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket();
		Info i = getIP(datagramSocket);
		System.out.println(datagramSocket.getLocalPort() + " => " + i.getIP() + ":" + i.getPort());
	}
}
