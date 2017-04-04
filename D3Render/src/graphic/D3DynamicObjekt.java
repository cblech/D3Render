/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import utils.D3vec;
import utils.D3line;
import utils.Vec2;
import java.util.ArrayList;
import java.util.Scanner;
import utils.D3CubCurve;

/**
 *
 * @author jonathan
 */
public class D3DynamicObjekt extends D3Objekt {

    public D3DynamicObjekt() {
        super();
    }

    public D3DynamicObjekt(String p) {
        super(p);
    }

    private void printNextDatatype(Scanner s) {
        if (s.hasNext()) {
            System.out.println("Has next");
        } else {
            System.out.println("Don't has next");
        }
        if (s.hasNextInt()) {
            System.out.println("Has next int");
        } else {
            System.out.println("Don't has next int");
        }
        if (s.hasNextDouble()) {
            System.out.println("Has next double");
        } else {
            System.out.println("Don't has next double");
        }
        if (s.hasNextFloat()) {
            System.out.println("Has next float");
        } else {
            System.out.println("Don't has next float");
        }
        if (s.hasNextLong()) {
            System.out.println("Has next long");
        } else {
            System.out.println("Don't has next long");
        }
    }

    public void addZrot(double r) {
        rotation.z += r;
    }

    public void addYrot(double r) {
        rotation.y += r;
    }

    public void addXrot(double r) {
        rotation.x += r;
    }

    public void setZrot(double r) {
        rotation.z = r;
    }

    public void setYrot(double r) {
        rotation.y = r;
    }

    public void setXrot(double r) {
        rotation.x = r;
    }

    @Override
    public ArrayList<D3line> getLines() {
        ArrayList<D3line> displayedLines = new ArrayList<>();
        ArrayList<D3vec> displayedPoints = new ArrayList<>();

        for (D3vec point : points) {
            displayedPoints.add(calculateRotationOffset(point));
        }

        for (D3vec displayedPoint : displayedPoints) {
            displayedPoint.x += positoin.x;
            displayedPoint.y += positoin.y;
            displayedPoint.z += positoin.z;
        }

        for (Vec2 connection : connections) {
            displayedLines.add(new D3line(displayedPoints.get(connection.a), displayedPoints.get(connection.b)));
        }

        return displayedLines;
    }

    @Override
    public ArrayList<D3CubCurve> getCubCurves() {
        return
    }

    private void calculatePositionOffset() {

    }

    private void prosessZrot(double r) {
        for (D3vec point : points) {
            double nx = point.x * Math.cos(r) - point.y * Math.sin(r);
            double ny = point.x * Math.sin(r) + point.y * Math.cos(r);
            double nz = point.z;
            point.x = nx;
            point.y = ny;
            point.z = nz;
        }
    }

    private void prosessYrot(double r) {
        for (D3vec point : points) {
            double nx = point.x * Math.cos(r) + point.z * Math.sin(r);
            double ny = point.y;
            double nz = point.z * Math.cos(r) - point.x * Math.sin(r);
            point.x = nx;
            point.y = ny;
            point.z = nz;
        }
    }

    private void prosessXrot(double r) {
        for (D3vec point : points) {
            double nx = point.x;
            double ny = point.z * Math.sin(r) + point.y * Math.cos(r);
            double nz = point.z * Math.cos(r) - point.y * Math.sin(r);
            point.x = nx;
            point.y = ny;
            point.z = nz;
        }
    }
}
