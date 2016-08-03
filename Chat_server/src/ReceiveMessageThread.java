
import java.io.IOException;
import java.net.DatagramPacket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Thread onde o socket do servidor irá esperar por mensagens.
 *
 * @author nayra
 */
public class ReceiveMessageThread extends Thread {

    private Server server;
    private DatagramPacket receivePacket;

    /**
     *
     * Construtor que recebe uma instância de server
     *
     * @param server
     */
    public ReceiveMessageThread(Server server) {

        this.server = server;
    }

    @Override
    public void run() {
        while (true) {

            byte[] buf = new byte[16000];

            this.receivePacket = new DatagramPacket(buf, buf.length);

            try {

                this.server.getSocket().receive(this.receivePacket);

                String message = new String(receivePacket.getData()).trim();
                String[] parts = message.split(R.CARACTER.getValue());

                if (message.isEmpty() || parts.length < 2) {

                    continue;
                }

                String username = parts[0].trim();
                String command = parts[1].trim();

                User user = new User(receivePacket.getAddress(), receivePacket.getPort());

                if (command.equals(R.CONNECT.getValue())) {

                    if (!this.server.getUsers().containsKey(username)) {

                        this.server.register(username, user);
                    } else {

                        this.server.sendMessageUser(R.USERNAME_UNAVAILABLE.getValue(), user);
                    }
                } else if (command.equals(R.DISCONNECT.getValue())) {

                    this.server.unregister(username);

                } else {

                    if (this.server.getUsers().containsKey(username)) {

                        user = this.server.getUsers().get(username);
                        if (command.equals(R.SHOW_USERS.getValue())) {

                            this.server.sendMessageUser(this.server.getUsers().keySet().toString(), user);
                        } else if (command.equals(R.PRIVATE_MESSAGE.getValue())) {

                            if (parts.length >= 3) {

                                String usernameDest = parts[2].trim();
                                if (this.server.getUsers().containsKey(usernameDest)) {
                                    this.server.sendPrivateMessage(message, user, usernameDest);
                                } else {
                                    this.server.sendMessageUser("Usuário " + usernameDest + " não encontrado!", user);
                                }
                            } else {

                                this.server.sendMessageUser("Especifique o usuário destino!", user);
                            }
                        } else if (command.equals(R.HISTORY.getValue())) {

                            if (parts.length >= 3) {

                                String usernameDest = parts[2].trim();
                                if (usernameDest.trim().isEmpty()) {

                                    this.server.sendPublicHistory(user);
                                } else {
                                    if (this.server.getUsers().containsKey(usernameDest)) {
                                        User dest = this.server.getUsers().get(usernameDest);
                                        this.server.sendPrivateHistory(user, dest);
                                    } else {
                                        this.server.sendMessageUser("Usuário " + usernameDest + " não encontrado!", user);
                                    }
                                }
                            } else {

                                this.server.sendPublicHistory(user);
                            }
                        } else {
                            this.server.sendBroadcastMessage(message.replace(username + R.CARACTER.getValue(), ""), user);
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println("Ocorreu um erro desconhecido!");
            }
        }
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public DatagramPacket getReceivePacket() {
        return receivePacket;
    }

    public void setReceivePacket(DatagramPacket receivePacket) {
        this.receivePacket = receivePacket;
    }
}
