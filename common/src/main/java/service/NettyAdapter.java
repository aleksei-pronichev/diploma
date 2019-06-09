package service;
/* Работа с сетью
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import packets.Packet;
import packets.PacketError;
import packets.PacketResponse;

import java.io.IOException;
import java.net.Socket;


public class NettyAdapter implements ConnectionAdapter {
    private String address;
    private int port;
    private Socket socket;
    private ObjectDecoderInputStream in;
    private ObjectEncoderOutputStream out;

    public boolean isConnected() {
        return connected;
    }

    private boolean connected = false;

    public NettyAdapter(String address, int port) {
        this.address = address;
        this.port = port;
        connect();
    }

    private boolean resetConnection() {
        try {
            socket = new Socket(address, port);
            in = new ObjectDecoderInputStream(socket.getInputStream());
            out = new ObjectEncoderOutputStream(socket.getOutputStream());
            connected = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
            return false;
        }
    }

    public boolean connect() {
        if (connected) return true;
        return resetConnection();
    }

    public Packet sendPacket(Packet packet) {
        try {
            out.writeObject(packet);
            PacketResponse respons = (PacketResponse) in.readObject();
            if (packet.getId().equals(respons.getRespID())) {
                return respons;
            } else {
                closeConnection();
                return new PacketError("Ответ на запрос не был получен");
            }
        } catch (IOException e) {
            closeConnection();
            return new PacketError("Соединение разорвано");
        } catch (ClassNotFoundException e) {
            closeConnection();
            return new PacketError("Был послан неверный класс");
        }
    }


    public void closeConnection() {
        connected = false;
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}