package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Main {
    static MyFrame mf;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mf = new MyFrame();
        mf.setVisible(true);
//        Properties p = new Properties();
//        p.setProperty("jump", "87");
//        p.setProperty("left", "65");
//        p.setProperty("right", "68");
//        try {
//            p.store(new FileWriter("config/keyinputs.conf"), null);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
