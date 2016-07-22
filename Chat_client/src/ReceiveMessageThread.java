
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

    private Client client;

    public ReceiveMessageThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        byte[] recBuffer = new byte[16000];
        String trimmed = "";
        do {
            DatagramPacket recPack = new DatagramPacket(recBuffer, recBuffer.length);
            recBuffer = new byte[recBuffer.length];
            try {
                this.client.getSocket().receive(recPack);
            } catch (IOException e) {
            }
            String recString = new String(recPack.getData());
            trimmed = recString.trim();

            if (!trimmed.isEmpty()) {
                this.client.getChat().getTextPane().setText(this.client.getChat().getTextPane().getText() + "\n"+  trimmed);
            }

        } while (!trimmed.equals("/endchat"));
        this.client.getSocket().close();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
