package tools;

/**
 *
 * @author jonathan
 */
public class Main {
    static MyFrame mf;
    static final boolean editor = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mf = new MyFrame(editor);
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
