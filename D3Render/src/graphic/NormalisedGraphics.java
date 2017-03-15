/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author jonathan
 */
public class NormalisedGraphics {

    Graphics g;
    double verhaeltnisX = 2;
    double verhaeltnisY = 1;
    int maxX;
    int maxY;

    public NormalisedGraphics(Graphics g, Dimension compSice) {
        this.g = g;
        maxX = compSice.width;
        maxY = compSice.height;
    }

    private int normalizeX(double x) {
        return (int) (x * maxX / 2 / verhaeltnisX) + maxX / 2;
    }

    private int normalizeY(double y) {
        return (int) (y * maxY / 2 / verhaeltnisY) + maxY / 2;
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        g.drawLine(normalizeX(x1), normalizeY(y1), normalizeX(x2), normalizeY(y2));
    }

    public void drawRect(double x1, double y1, double x2, double y2) {
        int x1Norm = normalizeX(x1);
        int y1Norm = normalizeY(y1);
        g.drawRect(x1Norm, y1Norm, normalizeX(x2) - x1Norm, normalizeY(y2) - y1Norm);
    }

}
