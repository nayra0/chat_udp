/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Comandos recebidos/enviados por Usuários/Servidor
 * 
 * @author nayra
 */
public enum R {

    CARACTER(" "),
    CONNECT("/request_connect"),
    CONNECTION_ACCEPTED("/connection_accepted"),
    USERNAME_UNAVAILABLE("/user_unavailable"),
    DISCONNECT("/disconnect"),
    SHOW_USERS("/users"),
    PRIVATE_MESSAGE("/private"),
    HISTORY("/history");
    String value;

    private R(String value) {
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
