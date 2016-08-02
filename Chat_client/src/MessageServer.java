
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Representa uma mensagem enviada pelo servidor
 * para a tela de login
 * 
 * @author nayra
 */
public class MessageServer {
    
    private String message;
    private boolean booleanResponse;

    public MessageServer(String message, boolean booleanResponse) {
        this.message = message;
        this.booleanResponse = booleanResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBooleanResponse() {
        return booleanResponse;
    }

    public void setBooleanResponse(boolean booleanResponse) {
        this.booleanResponse = booleanResponse;
    }
    
}
