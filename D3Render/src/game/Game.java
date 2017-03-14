/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import graphic.D3Graphics;
import graphic.D3Objekt;
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
    D3Objekt d3v = new D3Objekt("objekts/LetterV.m3d");
    D3Objekt d3j = new D3Objekt("objekts/LetterJ.m3d");
    D3Objekt d3p = new D3Objekt("objekts/plus.m3d");
    D3Objekt d3c = new D3Objekt("objekts/cube.m3d");

//  Add objekt to drawlist
    private void onSteup() {
        //drawList.add(d3v);
        drawList.add(d3j);
        //drawList.add(d3p);
        drawList.add(d3c);
        
//  Position scene objekts
        //change Camera Z offset (Default: -2)
        
        //dg.setCammeraZPos(-1.2); //TODO: Make Kammera Objekt

        d3c.positoin = new D3vec(1.5, 0, 2);
        d3j.positoin = new D3vec(-.7, 0, 0);
        d3v.positoin = new D3vec(.7, 0, 0);
    }

//  Add scene action over time
//  action will perform every 1/60 second
    private void onTick() {

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
    ArrayList<D3Objekt> drawList = new ArrayList<>();
    RenderPannel rp;
    public Game(RenderPannel rp) {
        this.rp = rp;
        onSteup();
        new Thread(new Ticker(this)).start();
    }

    public void paintObjekts(D3Graphics dg) {
        
        dg.setCammeraZPos(-1.2); //Quick and Dirty
        
        for (D3Objekt objekt : drawList) {
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
                long LastFrameTemp = System.currentTimeMillis();
                game.onTick();
                rp.repaint();

                rp.fps = (int) (1000.0 / ((LastFrameTemp - LastFrameTime) + 0.0000001));
                LastFrameTime = LastFrameTemp;
                
                //debug
                if (rp.jf.db != null) {
                    rp.jf.db.setFps(rp.fps);
                    KeyMapper mk = (KeyMapper) (rp.jf.getKeyListeners()[0]);
                    rp.jf.db.setJump(mk.pressedJumpKey);
                    rp.jf.db.setRight(mk.pressedRightKey);
                    rp.jf.db.setLeft(mk.pressedLeftKey);
                }

                try {
                    Thread.sleep(-System.currentTimeMillis() + LastFrameTemp + (1000 / 60));
                } catch (Exception ex) {
                }
            }
        }
    }
}
