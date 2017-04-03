/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import utils.D3line;
import game.Game;
import utils.D3CubCurve;
import utils.D3QuadCurve;

/**
 *
 * @author jonathan
 */
public class D3Graphics {

    NormalisedGraphics ng;
    D3Camera cm;
    private static double cammeraZPos = -2;

    private double perspCoordX(double x, double z) {
        return (x + cm.pos.x) / (z + cm.pos.z * -1);
    }

    private double perspCoordY(double y, double z) {
        return (y + cm.pos.y) / (z + cm.pos.z * -1);
    }

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
        ng.drawLine(perspCoordX(x1, z1), perspCoordY(y1, z1), perspCoordX(x2, z2), perspCoordY(y2, z2));
    }

    public void draw3dObjekt(D3Objekt d) {
        for (D3line d3line : d.getLines()) {
            drawPerspectiveLine(d3line);
        }
    }

    public void drawQuadCurve(D3QuadCurve curve) {
        drawQuadCurve(curve.start.x, curve.start.y, curve.start.z, curve.cont.x, curve.cont.y, curve.cont.z, curve.end.x, curve.end.y, curve.end.z);
    }

    public void drawQuadCurve(double x1, double y1, double z1, double xcont, double ycont, double zcont, double x2, double y2, double z2) {
        ng.drawQuadCurve(perspCoordX(x1, z1), perspCoordY(y1, z1), perspCoordX(xcont, zcont), perspCoordY(ycont, zcont), perspCoordX(x2, z2), perspCoordY(y2, z2));
    }

    public void drawCubCurve(D3CubCurve curve) {
        drawCubCurve(curve.start.x, curve.start.y, curve.start.z, curve.cont1.x, curve.cont1.y, curve.cont1.z, curve.cont2.x, curve.cont2.y, curve.cont2.z, curve.end.x, curve.end.y, curve.end.z);
    }

    public void drawCubCurve(double x1, double y1, double z1, double xcont1, double ycont1, double zcont1, double xcont2, double ycont2, double zcont2, double x2, double y2, double z2) {
        ng.drawCubCurve(perspCoordX(x1, z1), perspCoordY(y1, z1), perspCoordX(xcont1, zcont1), perspCoordY(ycont1, zcont1), perspCoordX(xcont2, zcont2), perspCoordY(ycont2, zcont2), perspCoordX(x2, z2), perspCoordY(y2, z2));
    }
}
