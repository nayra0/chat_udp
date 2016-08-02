
import java.net.InetAddress;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Representa o usuário na aplicação servidor
 * 
 * @author nayra
 */
public class User {

    private String username;
    private InetAddress ipAddress;
    private int portNumber;

    /**
     * 
     * Construtor padrão
     * 
     */
    public User() {
    }

    /**
     * 
     * Construtor que recebe o IP e a porta
     * que o usuário enviou o pacote de conexão.
     * 
     * @param ipAddress
     * @param port 
     */
    public User(InetAddress ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.portNumber = port;
    }

    /**
     * 
     * Construtor que recebe o username, IP e a porta
     * que o usuário enviou o pacote de conexão.
     * 
     * @param username
     * @param ipAddress
     * @param port 
     */
    public User(String username, InetAddress ipAddress, int port) {
        this(ipAddress, port);
        this.portNumber = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPort(int portNumber) {
        this.portNumber = portNumber;
    }
}
