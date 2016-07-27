
import java.net.SocketException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nayra
 */
public class Main {

    public static void main(String[] args) {

        try {
            Server server = new Server(8080);
            server.start();

        } catch (SocketException e) {
            System.err.println("Ocorreu um erro desconhecido");
        }

    }
}
