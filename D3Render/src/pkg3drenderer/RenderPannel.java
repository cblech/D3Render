package pkg3drenderer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
class RenderPannel extends JPanel {

//####################################################
//  Scene setup
//####################################################
//  Create scene objekts
    //D3cube dc = new D3cube(new D3vec(-.5, .5, -.5), new D3vec(.5, -.5, .5));
    //D3LetterJ dj = new D3LetterJ();
    D3Objekt d3v = new D3Objekt("objekts/LetterV.m3d");
    D3Objekt d3j = new D3Objekt("objekts/LetterJ.m3d");
    //D3Objekt d3c = new D3Objekt("objekts/cube.m3d");

//  Add objekt to drawlist
    private void drawList() {
        //drawList.add(dc);
        //drawList.add(dj);
        drawList.add(d3v);
        //drawList.add(d3j);
        //drawList.add(d3c);
    }

//  Add scene action over time
//  action will perform every 1/60 second
    private void sceneAction() {
        //dc.addXrot(.02);
        //dc.addYrot(.03);
        //dc.addZrot(.01);

        //dj.addYrot(.01);
        d3v.addYrot(.03);
        //d3j.addYrot(.03);
        //d3c.addYrot(-.02);
    }

//change Camera Z offset (Default: -2)
    private void changeCammera() {
        dg.setCammeraZPos(-1.2);
    }

//####################################################
//  end scene setup
//####################################################
    public RenderPannel(MyFrame jf) {
        drawList();
        this.jf = jf;
        new Thread(new Rotator(this)).start();
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
        changeCammera();
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
                rp.sceneAction();
                rp.repaint();

                long LastFrameTemp = System.currentTimeMillis();
                rp.fps = (int) (1000 / ((System.currentTimeMillis() - LastFrameTime) + 1));
                LastFrameTime = LastFrameTemp;
                if (rp.jf.db != null) {
                    rp.jf.db.setFps(rp.fps);
                }

                try {
                    Thread.sleep(1000 / 60);

                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
