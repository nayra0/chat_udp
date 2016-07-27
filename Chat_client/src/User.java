
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

    private String username;
    private Server server;
    private ReceiveMessageThread receiveMessage;
    private DatagramSocket socket;
    private Chat chat;

    public User(String username, Server server) throws SocketException {
        this.username = username;
        this.server = server;
        this.socket = new DatagramSocket();
        this.receiveMessage = new ReceiveMessageThread(this);
    }

    public MessageServer connectServer() {
        try {
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[16000];
            String message = this.username + R.CARACTER.getValue() + R.CONNECT.getValue();
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
            if (message.equals(R.CONNECTION_ACCEPTED.getValue())) {
                return new MessageServer("Conexão efetuada!", true);
            } else if (message.equals(R.USERNAME_UNAVAILABLE.getValue())) {
                return new MessageServer("O username '" + this.username + "' já está em uso!", false);
            }

        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MessageServer("Ocorreu um erro desconhecido!", false);
    }

    public void disconnectServer() {
        try {
            byte[] sendBuffer;
            String message = this.username + R.CARACTER.getValue() + R.DISCONNECT.getValue();
            sendBuffer = message.getBytes();

            DatagramPacket connectPacket = new DatagramPacket(sendBuffer, sendBuffer.length, this.server.getAddress(), this.server.getPort());
            this.socket.send(connectPacket);

            this.socket.close();

        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessageToServer(String command) {
        try {
            byte[] sendBuffer;

            if (command.contains(R.PRIVATE_MESSAGE.getValue())) {
                command = command.replace(R.PRIVATE_MESSAGE.getValue(), "");
                command = this.username + R.CARACTER.getValue() + R.PRIVATE_MESSAGE.getValue() + R.CARACTER.getValue() + command.trim();
            } else {
                command = this.username + R.CARACTER.getValue() + command.trim();
            }
            sendBuffer = command.getBytes();
            DatagramPacket commandPacket = new DatagramPacket(sendBuffer, sendBuffer.length, this.server.getAddress(), this.server.getPort());
            this.socket.send(commandPacket);

        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void starChat() {
        this.receiveMessage.start();
        this.chat = new Chat(this);
        this.chat.setVisible(true);
        this.chat.setTitle(this.username + " on " + this.server.getAddress().getHostName());
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
