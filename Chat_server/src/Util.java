
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Classe utilitária
 * 
 * @author nayra
 */
public class Util {

    /**
     * 
     * Retorna uma string da data 'date' no formato 'dd:MM:yyyy HH:mm:ss'
     * 
     * @param date
     * @return 
     */
    public static String printDateHour(Date date) {
        DateFormat df = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
        return df.format(date);
    }
}
