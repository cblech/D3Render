/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3drenderer;

/**
 *
 * @author jonathan
 */
public class D3Graphics {

    NormalisedGraphics ng;
    private static double cammeraZPos = -2;

    public D3Graphics(NormalisedGraphics ng) {
        this.ng = ng;
    }

    public static void setCammeraZPos(double cammeraZPos) {
        D3Graphics.cammeraZPos = cammeraZPos;
    }
    
    public void drawPerspectiveLine(D3line d) {
        drawPerspectiveLine(d.startPoint.x, d.startPoint.y, d.startPoint.z, d.endPoint.x, d.endPoint.y, d.endPoint.z);
    }

    public void drawPerspectiveLine(double x1, double y1, double z1, double x2, double y2, double z2) {
        ng.drawLine(x1 / (z1 + cammeraZPos*-1), y1 / (z1 + cammeraZPos*-1), x2 / (z2 + cammeraZPos*-1), y2 / (z2 + cammeraZPos*-1));
    }

    public void draw3dObjekt(D3Objekt d) {
//        D3vec p1 = new D3vec(d.fromPoint.x, d.fromPoint.y, d.fromPoint.z);
//        D3vec p2 = new D3vec(d.fromPoint.x, d.toPoint.y, d.fromPoint.z);
//        D3vec p3 = new D3vec(d.toPoint.x, d.toPoint.y, d.fromPoint.z);
//        D3vec p4 = new D3vec(d.toPoint.x, d.fromPoint.y, d.fromPoint.z);
//        D3vec p5 = new D3vec(d.fromPoint.x, d.fromPoint.y, d.toPoint.z);
//        D3vec p6 = new D3vec(d.fromPoint.x, d.toPoint.y, d.toPoint.z);
//        D3vec p7 = new D3vec(d.toPoint.x, d.toPoint.y, d.toPoint.z);
//        D3vec p8 = new D3vec(d.toPoint.x, d.fromPoint.y, d.toPoint.z);

        for (D3line d3line : d.getLines()) {
            drawPerspectiveLine(d3line);
        }
    }
}
