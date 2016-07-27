
import java.net.InetAddress;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nayra
 */
public class User {

    private String username;
    private InetAddress ipAddress;
    private int portNumber;

    public User() {
    }

    public User(InetAddress ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.portNumber = port;
    }

    public User(String username, InetAddress ipAddress, int port) {
        this.username = username;
        this.ipAddress = ipAddress;
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
