/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3drenderer;

import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class D3cube extends D3Objekt {
    //D3vec points[] = new D3vec[8];

    public D3cube(D3vec fromPoint, D3vec toPoint) {
        points.add(new D3vec(fromPoint.x, fromPoint.y, fromPoint.z));
        points.add(new D3vec(fromPoint.x, toPoint.y, fromPoint.z));
        points.add(new D3vec(toPoint.x, toPoint.y, fromPoint.z));
        points.add(new D3vec(toPoint.x, fromPoint.y, fromPoint.z));
        
        points.add(new D3vec(fromPoint.x, fromPoint.y, toPoint.z));
        points.add(new D3vec(fromPoint.x, toPoint.y, toPoint.z));
        points.add(new D3vec(toPoint.x, toPoint.y, toPoint.z));
        points.add(new D3vec(toPoint.x, fromPoint.y, toPoint.z));

        connections.add(new D3line(points.get(0), points.get(1)));
        connections.add(new D3line(points.get(1), points.get(2)));
        connections.add(new D3line(points.get(2), points.get(3)));
        connections.add(new D3line(points.get(3), points.get(0)));
        
        connections.add(new D3line(points.get(0), points.get(4)));
        connections.add(new D3line(points.get(1), points.get(5)));
        connections.add(new D3line(points.get(2), points.get(6)));
        connections.add(new D3line(points.get(3), points.get(7)));
        
        connections.add(new D3line(points.get(4), points.get(5)));
        connections.add(new D3line(points.get(5), points.get(6)));
        connections.add(new D3line(points.get(6), points.get(7)));
        connections.add(new D3line(points.get(7), points.get(4)));
    }

}
