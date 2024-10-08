package com.network.lab3.service;

import com.network.lab3.entity.FSInformation;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class RegisterService {

    public String putRegister(FSInformation fsInformation) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("host.docker.internal");

        byte[] sendData;
        byte[] receiveData = new byte[3];

        String sentence = String.format("TYPE=A\n" +
                "NAME=%s\n" +
                "VALUE=%s\n" +
                "TTL=10", fsInformation.getHostname(), fsInformation.getIp());
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 53533);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        clientSocket.close();

        return modifiedSentence;
    }

}
