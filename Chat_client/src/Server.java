
import java.net.InetAddress;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Representa o servidor na aplicação cliente
 * 
 * @author nayra
 */
public class Server {

    int port;
    InetAddress address;
    
    /**
     * 
     * Construtor padrão
     * 
     */
    public Server() {
    }

    /**
     * 
     * Construtor que recebe a porta e o IP do servidor
     * 
     * @param port
     * @param address 
     */
    public Server(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
    
}
