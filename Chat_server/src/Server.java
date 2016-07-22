
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    int port;
    DatagramSocket socket;
    HashMap<String, Client> clients;
    ReceiveMessageThread receiveMessageThread;

    public Server(int port) throws SocketException {
        this.port = port;
        this.socket = new DatagramSocket(this.port);
        this.clients = new HashMap();
        this.receiveMessageThread = new ReceiveMessageThread(this);
    }

    public int getPort() {
        return port;
    }

    public void sendConnectionInformation(String message, Client client) {
        try {
            byte[] sendBuffer;
            DatagramPacket sendPacket;
            sendBuffer = message.getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, client.getIpAddress(), client.getPort());
            this.socket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessageClient(String message, Client client) {
        try {
            byte[] sendBuffer;
            DatagramPacket sendPacket;
            message = "[server on ]=>" + message;
            sendBuffer = message.getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, client.getIpAddress(), client.getPort());
            this.socket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendPrivateMessage(String message, Client origin, Client dest) {
    }

    public void sendBroadcastMessage(String message, Client origin) {
        byte[] sendBuffer;
        DatagramPacket sendPacket;
        message = "[" + origin.getUsername() + "]=>" + message;

        for (String key : this.clients.keySet()) {
            Client client = this.clients.get(key);
            try {
                sendBuffer = message.getBytes();
                sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, client.getIpAddress(), client.getPort());
                this.socket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public ReceiveMessageThread getReceiveMessageThread() {
        return receiveMessageThread;
    }

    public void setReceiveMessageThread(ReceiveMessageThread receiveMessageThread) {
        this.receiveMessageThread = receiveMessageThread;
    }

    public HashMap<String, Client> getClients() {
        return clients;
    }

    public void setClients(HashMap<String, Client> clients) {
        this.clients = clients;
    }
}
