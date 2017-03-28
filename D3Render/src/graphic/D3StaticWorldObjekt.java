/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.util.ArrayList;
import utils.D3line;
import utils.D3vec;
import utils.Vec2;

/**
 *
 * @author luebeck.jonathan
 */
public class D3StaticWorldObjekt extends D3Objekt {

    ArrayList<D3line> d3lines = new ArrayList<>();
    ArrayList<D3StaticObjekt> d3objekts = new ArrayList<>();

    public void addObjekt(D3StaticObjekt so) {
        d3objekts.add(so);
    }

    public void bakeLines() {
        for (D3StaticObjekt so : d3objekts) {
            int length = this.points.size();
            for (D3vec point : so.points) {
                point = calculateRotationOffset(point,so.rotation);
                point.x += so.positoin.x;
                point.y += so.positoin.y;
                point.z += so.positoin.z;
                this.points.add(point);
            }
            for (Vec2 line : so.connections) {
                this.connections.add(new Vec2(line.a + length, line.b + length));
            }
        }

        d3lines = new ArrayList<>();
        for (Vec2 connection : connections) {
            d3lines.add(new D3line(points.get(connection.a), points.get(connection.b)));
        }
    }

    @Override
    public ArrayList<D3line> getLines() {
        return d3lines;
    }
}
