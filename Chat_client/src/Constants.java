/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nayra
 */
public enum Constants {
    
    CONNECT("1"),
    CONNECT_ACCEPTED("2"),
    USERNAME_UNAVAILABLE("3"),
    DISCONNECT("4"),
    SHOW_USERS("/susers"),
    PRIVATE_MESSAGE("/p");
    
    String value;

    private Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Constants{" + "value=" + value + '}';
    }
    
}
