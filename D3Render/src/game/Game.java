/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import graphic.D3Camera;
import graphic.D3Graphics;
import graphic.D3DynamicObjekt;
import graphic.D3Objekt;
import graphic.D3StaticObjekt;
import graphic.D3StaticWorldObjekt;
import graphic.RenderPannel;
import java.util.ArrayList;
import utils.D3vec;

/**
 *
 * @author jonathan
 */
public class Game {

//####################################################
//  Scene setup
//####################################################
//  Create scene objekts
    D3Camera cam;
    D3DynamicObjekt curvyCube = new D3DynamicObjekt("objekts/cuberound.m3d");
////    D3StaticObjekt std3v = new D3StaticObjekt("objekts/LetterV.m3d");
////    D3DynamicObjekt d3v = new D3DynamicObjekt("objekts/LetterV.m3d");
////    D3StaticObjekt std3j = new D3StaticObjekt("objekts/LetterJ.m3d");
////    D3DynamicObjekt d3j = new D3DynamicObjekt("objekts/LetterJ.m3d");
////    D3DynamicObjekt d3p = new D3DynamicObjekt("objekts/plus.m3d");
////    D3DynamicObjekt d3c = new D3DynamicObjekt("objekts/cube.m3d");

//  Add objekt to drawlist
    private void onSteup() {
        objekts.add(curvyCube);
//        objekts.add(d3j);
//        objekts.add(d3c);
//        worldObjekt.addObjekt(std3v);
//        worldObjekt.addObjekt(std3j);

//  Position scene objekts
        //change Camera Z offset (Default: -2)
        //dg.setCammeraZPos(-1.2); //TODO: Make Kammera Objekt
        cam.pos = new D3vec(0, 0, -1.2);
//        d3c.positoin = new D3vec(1.5, 0, 2);
//        d3j.positoin = new D3vec(-.7, 0, 0);
//        d3v.positoin = new D3vec(.7, 0, 0);
//        std3j.positoin = new D3vec(-.7, 3, 0);
//        std3v.positoin = new D3vec(.7, 3, 0);
//        std3j.setYrot(0.4);
    }

//  Add scene action over time
//  action will perform every 1/60 second
    private void onTick() {
        curvyCube.addXrot(.02);
        curvyCube.addYrot(.025);
        curvyCube.addZrot(.01);
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

    public Game(RenderPannel rp) {
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

        Game game;
        long LastFrameTime = System.currentTimeMillis();

        public Ticker(Game game) {
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
