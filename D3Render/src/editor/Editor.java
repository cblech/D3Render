/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import graphic.D3Camera;
import graphic.D3Graphics;
import graphic.D3DynamicObjekt;
import graphic.D3Objekt;
import graphic.D3StaticWorldObjekt;
import graphic.RenderPannel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import utils.D3vec;

/**
 *
 * @author jonathan
 */
public class Editor {

//####################################################
//  Scene setup
//####################################################
//  Create scene objekts
    D3Camera cam;
    JFileChooser fc;
    D3DynamicObjekt editObj;

//  Add objekt to drawlist
    private void onSteup() {
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File("objekts"));
        
        if (fc.showOpenDialog(rp)!=JFileChooser.APPROVE_OPTION){
            System.err.println("File Chooser Error");
            System.exit(1);
        }
        System.out.println(fc.getSelectedFile().getPath());
        editObj = new D3DynamicObjekt(fc.getSelectedFile().getPath());

        objekts.add(editObj);
        
//  Position scene objekts

        cam.pos = new D3vec(0, 0, -1.2);
        
        editObj.positoin= new D3vec(0, 0, 0);
    }

//  Add scene action over time
//  action will perform every 1/60 second
    private void onTick() {
//        curvyCube.addXrot(.02);
//        curvyCube.addYrot(.025);
//        curvyCube.addZrot(.01);
//        d3v.addYrot(-.04);
//        d3p.addYrot(-.07);
//        d3p.addZrot(-.01);
//        d3j.addYrot(.03);
//        d3c.addYrot(-.02);
//        d3c.addXrot(-.014);
//        d3c.addZrot(-.018);
    }

//####################################################
//  end scene setup
//####################################################
    public static D3Camera usedCam;
    ArrayList<D3Objekt> objekts = new ArrayList<>();
    D3StaticWorldObjekt worldObjekt = new D3StaticWorldObjekt();

    RenderPannel rp;

    public Editor(RenderPannel rp) {
        objekts.add(worldObjekt);
        this.rp = rp;
        cam = new D3Camera();
        usedCam = cam;
        onSteup();
        worldObjekt.bakeLines();
        new Thread(new Ticker(this)).start();
    }

    public void whileRight() {
        cam.pos.y += 0.05;
    }

    public void whileLeft() {
        cam.pos.y -= 0.05;
    }

    public void paintObjekts(D3Graphics dg) {
        for (D3Objekt objekt : objekts) {
            dg.draw3dObjekt(objekt);
        }
    }

    class Ticker implements Runnable {

        Editor game;
        long LastFrameTime = System.currentTimeMillis();

        public Ticker(Editor game) {
            this.game = game;
        }

        @Override
        public void run() {
            while (true) {
                //store Systemtime on loop start
                long LastFrameTemp = System.currentTimeMillis();

                game.onTick();

                if (rp.jf.km.pressedRightKey) {
                    whileRight();
                }
                if (rp.jf.km.pressedLeftKey) {
                    whileLeft();
                }

                rp.repaint();

                //debug
                if (rp.jf.db != null) {
                    rp.jf.db.setFps(rp.fps);
                    rp.jf.db.setJump(rp.jf.km.pressedJumpKey);
                    rp.jf.db.setRight(rp.jf.km.pressedRightKey);
                    rp.jf.db.setLeft(rp.jf.km.pressedLeftKey);
                }

                //fps counter
                rp.fps = (int) (1000.0 / ((LastFrameTemp - LastFrameTime) + 0.0000001));
                LastFrameTime = LastFrameTemp;

                //sleeper
                try {
                    Thread.sleep(-System.currentTimeMillis() + LastFrameTemp + (1000 / 60));
                } catch (Exception ex) {
                }
            }
        }
    }
}
