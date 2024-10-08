package com.network.lab3;

import com.network.lab3.cache.DNSCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@SpringBootApplication
public class AsApplication {
	public static String DNS_FILE_NAME = "dns.txt";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AsApplication.class, args);

		DatagramSocket serverSocket = new DatagramSocket(53533);
		byte[] receiveData = new byte[1024];
		byte[] sendData;
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);

			String sentence = new String(receivePacket.getData()).trim();

			String[] parts = sentence.split("\n");
			String type = null, name = null, value = null;
			int ttl = 0;

			for (String part : parts) {
				String[] keyValue = part.split("=");
				switch (keyValue[0]) {
					case "TYPE":
						type = keyValue[1];
						break;
					case "NAME":
						name = keyValue[1];
						break;
					case "VALUE":
						value = keyValue[1];
						break;
					case "TTL":
						ttl = Integer.parseInt(keyValue[1]);
						break;
				}
			}

			String info = type + "," + name + "," + value + "," + ttl;
			String code;

			synchronized (DNSCache.class) {
				try (FileWriter writer = new FileWriter(DNS_FILE_NAME, true);
					 BufferedWriter bw = new BufferedWriter(writer)) {
					bw.write(info);
					bw.newLine();
					DNSCache.refresh();
					code = "201";
				} catch (IOException e) {
					e.printStackTrace();
					code = "500";
				}
			}

			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			sendData = code.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}

}
