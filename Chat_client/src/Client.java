
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private String username;
    private Server server;
    private ReceiveMessageThread receiveMessage;
    private DatagramSocket socket;
    private Chat chat;

    public Client(String username, Server server) throws SocketException {
        this.username = username;
        this.server = server;
        this.socket = new DatagramSocket();
        this.receiveMessage = new ReceiveMessageThread(this);
    }

    public MessageServer connectServer() {
        try {
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[16000];
            String message = this.username + ":" + Constants.CONNECT.getValue();
            sendBuffer = message.getBytes();

            DatagramPacket connectPack = new DatagramPacket(sendBuffer, sendBuffer.length, this.server.getAddress(), this.server.getPort());
            this.socket.send(connectPack);

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            this.socket.setSoTimeout(5000);

            try {
                this.socket.receive(receivePacket);
            } catch (SocketTimeoutException e) {
                return new MessageServer("Tempo para conexão excedido!", false);
            }

            message = new String(receivePacket.getData()).trim();
            if (message.equals(Constants.CONNECT_ACCEPTED.getValue())) {
                return new MessageServer("Conexão efetuada!", true);
            } else if (message.equals(Constants.USERNAME_UNAVAILABLE.getValue())) {
                return new MessageServer("O username '" + this.username + "' já está em uso!", false);
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MessageServer("Ocorreu um erro desconhecido!", false);
    }

    public void disconnectServer() {
        try {
            byte[] sendBuffer;
            String message = this.username + ":" + Constants.DISCONNECT.getValue();
            sendBuffer = message.getBytes();

            DatagramPacket connectPacket = new DatagramPacket(sendBuffer, sendBuffer.length, this.server.getAddress(), this.server.getPort());
            this.socket.send(connectPacket);

            this.socket.close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessageToServer(String command) {
        try {
            byte[] sendBuffer;

            if (command.contains(Constants.PRIVATE_MESSAGE.getValue())) {
                command = command.replace(Constants.PRIVATE_MESSAGE.getValue(), "");
                command = this.username + ":" + Constants.PRIVATE_MESSAGE.getValue() + ":" + command;
            } else {
                command = this.username + ":" + command;
            }
            sendBuffer = command.getBytes();
            DatagramPacket commandPacket = new DatagramPacket(sendBuffer, sendBuffer.length, this.server.getAddress(), this.server.getPort());
            this.socket.send(commandPacket);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void starChat() {
        this.receiveMessage.start();
        this.chat = new Chat(this);
        this.chat.setVisible(true);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
