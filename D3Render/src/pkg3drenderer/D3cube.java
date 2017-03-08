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

        connections.add(new Vec2(0, 1));
        connections.add(new Vec2(1, 2));
        connections.add(new Vec2(2, 3));
        connections.add(new Vec2(3, 0));
        connections.add(new Vec2(0, 4));
        connections.add(new Vec2(1, 5));
        connections.add(new Vec2(2, 6));
        connections.add(new Vec2(3, 7));
        connections.add(new Vec2(4, 5));
        connections.add(new Vec2(5, 6));
        connections.add(new Vec2(6, 7));
        connections.add(new Vec2(7, 4));
    }

}
