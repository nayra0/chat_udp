
import java.net.InetAddress;


public class Server {

    int port;
    InetAddress address;

    public Server() {
    }

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
