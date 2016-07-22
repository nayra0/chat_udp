
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
        
        try {
            Server server = new Server(8080);
            server.getReceiveMessageThread().start();
            
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
