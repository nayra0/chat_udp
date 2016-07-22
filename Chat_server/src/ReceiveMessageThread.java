
import java.io.IOException;
import java.net.DatagramPacket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nayra
 */
public class ReceiveMessageThread extends Thread {

    private Server server;

    public ReceiveMessageThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            byte[] buf = new byte[16000];

            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);

            try {
                this.server.getSocket().receive(receivePacket);

                String message = new String(receivePacket.getData()).trim();
                String[] parts = message.split(":");

                Client client = new Client(receivePacket.getAddress(), receivePacket.getPort());

                if (parts[1].equals(Constants.CONNECT.getValue())) {
                    if (!this.server.getClients().containsKey(parts[0])) {
                        client.setUsername(parts[0]);
                        this.server.getClients().put(client.getUsername(), client);
                        this.server.sendConnectionInformation(Constants.CONNECT_ACCEPTED.getValue(), client);
                    } else {
                        this.server.sendConnectionInformation(Constants.USERNAME_UNAVAILABLE.getValue(), client);
                    }
                } else if (parts[1].equals(Constants.DISCONNECT.getValue())) {
                    if (this.server.getClients().containsKey(parts[0])) {
                        this.server.getClients().remove(parts[0]);
                    }
                } else {
                    if (this.server.getClients().containsKey(parts[0])) {
                        client = this.server.getClients().get(parts[0]);
                        if (parts[1].equals(Constants.SHOW_USERS.getValue())) {
                            this.server.sendMessageClient(this.server.getClients().keySet().toString(), client);
                        } else if (parts[1].equals(Constants.PRIVATE_MESSAGE.getValue())) {
                            
                        } else {
                            this.server.sendBroadcastMessage(message.replace(parts[0] + ":", ""), client);
                        }
                    }
                }

            } catch (IOException e) {
            }
        }
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
