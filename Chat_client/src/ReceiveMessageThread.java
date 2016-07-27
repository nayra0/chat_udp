
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

    private User user;

    public ReceiveMessageThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        byte[] recBuffer = new byte[16000];
        String trimmed = "";
        do {
            DatagramPacket recPack = new DatagramPacket(recBuffer, recBuffer.length);
            recBuffer = new byte[recBuffer.length];
            try {
                this.user.getSocket().receive(recPack);
            } catch (IOException e) {
            }
            String recString = new String(recPack.getData());
            trimmed = recString.trim();

            if (!trimmed.isEmpty()) {
                this.user.getChat().getTextPane().setText(this.user.getChat().getTextPane().getText() + trimmed + "\n");
            }

        } while (!trimmed.equals("/endchat"));
        this.user.getSocket().close();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
