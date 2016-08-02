
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Classe que representa o servidor
 * 
 * @author nayra
 */
public class Server {

    private int port;
    private DatagramSocket socket;
    private HashMap<String, User> users;
    private ReceiveMessageThread receiveMessageThread;
    private String publicHistory;
    private HashMap<String, String> privateMessageHistory;

    /**
     * 
     * Construtor que recebe a porta onde o servidor
     * vai abrir o socket UDP
     * 
     * @param port
     * @throws SocketException 
     */    
    public Server(int port) throws SocketException {

        this.port = port;
        this.socket = new DatagramSocket(this.port);
        this.users = new HashMap();
        this.receiveMessageThread = new ReceiveMessageThread(this);
        this.publicHistory = "";
        this.privateMessageHistory = new HashMap();
    }

    /**
     * 
     * Método que registra o usuário no servidor.
     * Adiciona o usuário na lista de usuários ativos
     * e envia mensagem para o usuário que a conexão foi aceita.
     * 
     * @param username
     * @param user 
     */
    public void register(String username, User user) {

        user.setUsername(username);
        this.users.put(user.getUsername(), user);
        sendMessageUser(R.CONNECTION_ACCEPTED.getValue(), user);
    }

    /**
     * 
     * Remove o registro do usuário no servidor.
     * Remove da lista de usuários ativos.
     * 
     * @param username 
     */
    public void unregister(String username) {

        if (this.users.containsKey(username)) {

            this.users.remove(username);
        }
    }

    /**
     * 
     * Envia uma mensagem ao determinado usuário.
     * 
     * @param message
     * @param user 
     */
    public void sendMessageUser(String message, User user) {

        try {

            byte[] sendBuffer;
            DatagramPacket sendPacket;
            sendBuffer = message.getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, user.getIpAddress(), user.getPortNumber());
            this.socket.send(sendPacket);
        } catch (IOException ex) {

            System.err.println("Erro ao enviar a mensagem para o usuário: " + user.getUsername());
        }

    }

    /**
     * 
     * Envia uma mensagem a um determinado usuário.
     * 
     * @param message
     * @param username 
     */
    public void sendMessageUser(String message, String username) {

        User user = this.users.get(username);
        sendMessageUser(message, user);
    }

    /**
     * 
     * Envia uma mensagem privada do usuário 'origin' 
     * para o usuário destino 'usernameDest'
     * 
     * @param message
     * @param origin
     * @param usernameDest 
     */
    public void sendPrivateMessage(String message, User origin, String usernameDest) {

        User dest = this.users.get(usernameDest);
        sendPrivateMessage(message, origin, dest);
    }

    
    /**
     * 
     * Envia uma mensagem privada do usuário 'origin' 
     * para o usuário 'dest' e adiciona no histórico.
     * 
     * @param message
     * @param origin
     * @param usernameDest 
     */
    public void sendPrivateMessage(String message, User origin, User dest) {

        message = message.replace(origin.getUsername() + R.CARACTER.getValue() + R.PRIVATE_MESSAGE.getValue() + R.CARACTER.getValue() + dest.getUsername(), "");

        String messageDest = "[ " + Util.printDateHour(new Date()) + " " + origin.getUsername() + " sends ]=> " + message;
        sendMessageUser(messageDest, dest);

        String messageOrig = "[ " + Util.printDateHour(new Date()) + " to " + dest.getUsername() + "]=> " + message;
        sendMessageUser(messageOrig, origin);

        message = "[ " + Util.printDateHour(new Date()) + " " + origin.getUsername() + " ]=>" + message;
        addPrivateHistory(origin, dest, message);
    }

    /**
     * 
     * Envia uma mensagem do usuário 'origin' para 
     * todos os usuários ativos e adiciona no histórico.
     * 
     * @param message
     * @param origin 
     */
    public void sendBroadcastMessage(String message, User origin) {

        message = "[ " + Util.printDateHour(new Date()) + " " + origin.getUsername() + " ]=> " + message;
        addPublicHistory(message);

        for (String key : this.users.keySet()) {

            User user = this.users.get(key);
            sendMessageUser(message, user);
        }
    }

    /**
     * 
     * Envia o histórico público para um usuário.
     * 
     * @param user 
     */
    public void sendPublicHistory(User user) {

        sendMessageUser(publicHistory, user);
    }

    /**
     * 
     * Envia o histórico privado do usuário 'origin' com usuário 'dest'
     * para o usuário 'origin'.
     * 
     * @param origin
     * @param dest 
     */
    public void sendPrivateHistory(User origin, User dest) {

        String key = origin.getUsername() + "_" + dest.getUsername();
        String inverseKey = dest.getUsername() + "_" + origin.getUsername();
        if (!this.privateMessageHistory.containsKey(key) && this.privateMessageHistory.containsKey(inverseKey)) {

            key = inverseKey;
        }
        if (!this.privateMessageHistory.containsKey(key)) {

            sendMessageUser("Não há histórico", origin);
        } else {

            sendMessageUser(this.privateMessageHistory.get(key), origin);
        }

    }

    /**
     * 
     * Adiciona mensagem ao histórico público.
     * 
     * @param message 
     */
    public void addPublicHistory(String message) {

        this.publicHistory = this.publicHistory + "\n" + message;
    }

    /**
     * 
     * Adiciona mensagem ao histórico privado do usuário 'origin' e usuário 'dest'
     * 
     * @param origin
     * @param dest
     * @param message 
     */
    public void addPrivateHistory(User origin, User dest, String message) {

        String key = origin.getUsername() + "_" + dest.getUsername();
        String reverseKey = dest.getUsername() + "_" + origin.getUsername();
        if (!this.privateMessageHistory.containsKey(key) && this.privateMessageHistory.containsKey(reverseKey)) {

            key = reverseKey;
        }

        if (this.privateMessageHistory.containsKey(key)) {

            message = this.privateMessageHistory.get(key) + "\n" + message;
        }
        this.privateMessageHistory.put(key, message);
    }

    /**
     * 
     * Inicia a thread que irá ficar recebendo as mensagens dos usuários.
     * 
     */
    public void start() {
        this.receiveMessageThread.start();
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

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public String getPublicHistory() {
        return publicHistory;
    }

    public void setPublicHistory(String publicHistory) {
        this.publicHistory = publicHistory;
    }

    public HashMap<String, String> getPrivateMessageHistory() {
        return privateMessageHistory;
    }

    public void setPrivateMessageHistory(HashMap<String, String> privateMessageHistory) {
        this.privateMessageHistory = privateMessageHistory;
    }
    
    public int getPort() {
        return port;
    }
}
