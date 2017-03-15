package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class KeyMapper extends KeyAdapter {

    Properties properties;
    EventDirector director;

    public boolean pressedRightKey;
    public boolean pressedLeftKey;
    public boolean pressedJumpKey;

    public KeyMapper() {
        properties = new Properties();
        try {
            properties.load(new FileReader("config/keyinputs.conf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KeyMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KeyMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateKeymaping();
    }

    public void updateKeymaping() {
        try {
            director = new EventDirector() {
                final int jump = Integer.parseInt(properties.getProperty("jump"));
                final int right = Integer.parseInt(properties.getProperty("right"));
                final int left = Integer.parseInt(properties.getProperty("left"));

                public void keyDirect(int key, boolean set) {
                    if (key == jump) {
//                        System.out.println("Key Jump");
                        pressedJumpKey = set;
                    } else if (key == right) {
//                        System.out.println("Key Right");
                        pressedRightKey = set;
                    } else if (key == left) {
//                        System.out.println("Key Left");
                        pressedLeftKey = set;
                    }
                }
            };
        } catch (Exception ex) {
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getExtendedKeyCode());
        director.keyDirect(e.getExtendedKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        director.keyDirect(e.getExtendedKeyCode(),false);
    }

    class EventDirector {
        public void keyDirect(int key, boolean set) {
        }
    }
}
