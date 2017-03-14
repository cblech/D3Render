package graphic;

import graphic.D3Objekt;
import graphic.NormalisedGraphics;
import graphic.D3Graphics;
import tools.MyFrame;
import utils.D3vec;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class RenderPannel extends JPanel {

//####################################################
//  Scene setup
//####################################################
//  Create scene objekts
    //D3cube dc = new D3cube(new D3vec(-.5, .5, -.5), new D3vec(.5, -.5, .5));
    D3Objekt d3v = new D3Objekt("objekts/LetterV.m3d");
    D3Objekt d3j = new D3Objekt("objekts/LetterJ.m3d");
    D3Objekt d3p = new D3Objekt("objekts/plus.m3d");
    D3Objekt d3c = new D3Objekt("objekts/cube.m3d");

//  Add objekt to drawlist
    private void drawList() {
        //drawList.add(dc);
        //drawList.add(d3v);
        drawList.add(d3j);
        //drawList.add(d3p);
        drawList.add(d3c);
    }

//  Position scene objekts
    private void scenePosition() {
        //change Camera Z offset (Default: -2)
        dg.setCammeraZPos(-1.2);

        d3c.positoin = new D3vec(1.5, 0, 2);
        d3j.positoin = new D3vec(-.7, 0, 0);
        d3v.positoin= new D3vec(.7, 0, 0);
    }

//  Add scene action over time
//  action will perform every 1/60 second
    private void sceneAction() {
        //dc.addXrot(.02);
        //dc.addYrot(.03);
        //dc.addZrot(.01);
       
        d3v.addYrot(-.04);
        d3p.addYrot(-.07);
        d3p.addZrot(-.01);
        d3j.addYrot(.03);
        d3c.addYrot(-.02);
        d3c.addXrot(-.014);
        d3c.addZrot(-.018);
    }

//####################################################
//  end scene setup
//####################################################
    public RenderPannel(MyFrame jf) {
        drawList();
        this.jf = jf;
        new Thread(new Rotator(this)).start();
        scenePosition();
    }
    Random r = new Random();
    NormalisedGraphics ng;
    D3Graphics dg;
    ArrayList<D3Objekt> drawList = new ArrayList<>();
    MyFrame jf;
    int fps = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        ng = new NormalisedGraphics(g, getSize());
        dg = new D3Graphics(ng);

        for (D3Objekt objekt : drawList) {
            dg.draw3dObjekt(objekt);
        }
    }

    class Rotator implements Runnable {

        RenderPannel rp;
        long LastFrameTime = System.currentTimeMillis();

        public Rotator(RenderPannel rp) {
            this.rp = rp;
        }

        @Override
        public void run() {
            while (true) {
                long LastFrameTemp = System.currentTimeMillis();
                rp.sceneAction();
                rp.repaint();

                rp.fps = (int) (1000.0 / ((LastFrameTemp - LastFrameTime) + 0.0000001));
                LastFrameTime = LastFrameTemp;
                if (rp.jf.db != null) {
                    rp.jf.db.setFps(rp.fps);
                }

                try {
                    Thread.sleep(-System.currentTimeMillis() + LastFrameTemp + (1000 / 60));

                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
