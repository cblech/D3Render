package graphic;

import game.Game;
import tools.MyFrame;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class RenderPannel extends JPanel {

    Game game;
    Random r = new Random();
    NormalisedGraphics ng;
    D3Graphics dg;
    
    public MyFrame jf;
    public int fps = 0;
    
    public RenderPannel(MyFrame jf) {
        game = new Game(this);
        this.jf = jf;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        ng = new NormalisedGraphics(g, getSize());
        dg = new D3Graphics(ng);
        
        game.paintObjekts(dg);
        
        
    }

}
