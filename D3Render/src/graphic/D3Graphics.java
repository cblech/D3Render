/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import utils.D3line;
import game.Game;

/**
 *
 * @author jonathan
 */
public class D3Graphics {

    NormalisedGraphics ng;
    D3Camera cm;
    private static double cammeraZPos = -2;

    public D3Graphics(NormalisedGraphics ng) {
        this.ng = ng;
        cm = Game.usedCam;
    }

    public static void setCammeraZPos(double cammeraZPos) {
        D3Graphics.cammeraZPos = cammeraZPos;
    }

    public void drawPerspectiveLine(D3line d) {
        drawPerspectiveLine(
                d.startPoint.x, d.startPoint.y, d.startPoint.z,
                d.endPoint.x, d.endPoint.y, d.endPoint.z);
    }

    public void drawPerspectiveLine(double x1, double y1, double z1, double x2, double y2, double z2) {
        ng.drawLine(
                (x1 + cm.pos.x) / (z1 + cm.pos.z * -1),
                (y1 + cm.pos.y) / (z1 + cm.pos.z * -1),
                (x2 + cm.pos.x) / (z2 + cm.pos.z * -1),
                (y2 + cm.pos.y) / (z2 + cm.pos.z * -1));
    }

    public void draw3dObjekt(D3DynamicObjekt d) {
        for (D3line d3line : d.getLines()) {
            drawPerspectiveLine(d3line);
        }
    }
}
