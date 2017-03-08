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
public class D3LetterJ extends D3Objekt {

    public D3LetterJ() {
        //hintere ebene
        points.add(new D3vec(-.25, -.5, .05));//0
        points.add(new D3vec(+.15, -.5, .05));
        points.add(new D3vec(+.25, -.5, .05));
        points.add(new D3vec(-.25, -.4, .05));
        points.add(new D3vec(+.15, -.4, .05));
        points.add(new D3vec(+.25, -.4, .05));

        points.add(new D3vec(-.25, .35, .05));//6
        points.add(new D3vec(-.15, .35, .05));
        points.add(new D3vec(.15,  .35, .05));
        points.add(new D3vec(.25,  .35, .05));

        points.add(new D3vec(-.1,  .4,  .05));//10
        points.add(new D3vec( .1,  .4,  .05));
        points.add(new D3vec(-.1,  .5,  .05));
        points.add(new D3vec( .1,  .5,  .05));

        points.add(new D3vec(-.25, .25, .05));//14
        points.add(new D3vec(-.15, .25, .05));

        //vordere ebene
        points.add(new D3vec(-.25, -.5, -.05));//16
        points.add(new D3vec(+.15, -.5, -.05));
        points.add(new D3vec(+.25, -.5, -.05));
        points.add(new D3vec(-.25, -.4, -.05));
        points.add(new D3vec(+.15, -.4, -.05));
        points.add(new D3vec(+.25, -.4, -.05));

        points.add(new D3vec(-.25, .35, -.05));//22
        points.add(new D3vec(-.15, .35, -.05));
        points.add(new D3vec(.15, .35, -.05));
        points.add(new D3vec(.25, .35, -.05));

        points.add(new D3vec(-.1, .4, -.05));//26
        points.add(new D3vec(.1, .4, -.05));
        points.add(new D3vec(-.1, .5, -.05));
        points.add(new D3vec(.1, .5, -.05));

        points.add(new D3vec(-.25, .25, -.05));//30
        points.add(new D3vec(-.15, .25, -.05));

        //hintere ebene
//        connections.add(new D3line(points.get(0), points.get(1)));
//        connections.add(new D3line(points.get(1), points.get(2)));
//        connections.add(new D3line(points.get(3), points.get(4)));
//        connections.add(new D3line(points.get(4), points.get(5)));
//        connections.add(new D3line(points.get(0), points.get(3)));
//        connections.add(new D3line(points.get(1), points.get(4)));
//        connections.add(new D3line(points.get(2), points.get(5)));
//
//        connections.add(new D3line(points.get(4), points.get(8)));
//        connections.add(new D3line(points.get(5), points.get(9)));
//        connections.add(new D3line(points.get(6), points.get(7)));
//        connections.add(new D3line(points.get(8), points.get(9)));
//
//        connections.add(new D3line(points.get(7), points.get(10)));
//        connections.add(new D3line(points.get(10), points.get(11)));
//        connections.add(new D3line(points.get(11), points.get(8)));
//
//        connections.add(new D3line(points.get(6), points.get(12)));
//        connections.add(new D3line(points.get(12), points.get(13)));
//        connections.add(new D3line(points.get(13), points.get(9)));
//
//        connections.add(new D3line(points.get(10), points.get(12)));
//        connections.add(new D3line(points.get(11), points.get(13)));
//
//        connections.add(new D3line(points.get(6), points.get(14)));
//        connections.add(new D3line(points.get(14), points.get(15)));
//        connections.add(new D3line(points.get(15), points.get(7)));
//
//        //vordere ebene
//        connections.add(new D3line(points.get(16), points.get(17)));
//        connections.add(new D3line(points.get(17), points.get(18)));
//        connections.add(new D3line(points.get(19), points.get(20)));
//        connections.add(new D3line(points.get(20), points.get(21)));
//        connections.add(new D3line(points.get(16), points.get(19)));
//        connections.add(new D3line(points.get(17), points.get(20)));
//        connections.add(new D3line(points.get(18), points.get(21)));
//
//        connections.add(new D3line(points.get(20), points.get(24)));
//        connections.add(new D3line(points.get(21), points.get(25)));
//        connections.add(new D3line(points.get(22), points.get(23)));
//        connections.add(new D3line(points.get(24), points.get(25)));
//
//        connections.add(new D3line(points.get(23), points.get(26)));
//        connections.add(new D3line(points.get(26), points.get(27)));
//        connections.add(new D3line(points.get(27), points.get(24)));
//
//        connections.add(new D3line(points.get(22), points.get(28)));
//        connections.add(new D3line(points.get(28), points.get(29)));
//        connections.add(new D3line(points.get(29), points.get(25)));
//
//        connections.add(new D3line(points.get(26), points.get(28)));
//        connections.add(new D3line(points.get(27), points.get(29)));
//
//        connections.add(new D3line(points.get(22), points.get(30)));
//        connections.add(new D3line(points.get(30), points.get(31)));
//        connections.add(new D3line(points.get(31), points.get(23)));
//
//        //zwei ebenen verbinden
//        connections.add(new D3line(points.get(0), points.get(16)));
//        connections.add(new D3line(points.get(1), points.get(17)));
//        connections.add(new D3line(points.get(2), points.get(18)));
//        connections.add(new D3line(points.get(3), points.get(19)));
//        connections.add(new D3line(points.get(4), points.get(20)));
//        connections.add(new D3line(points.get(5), points.get(21)));
//        connections.add(new D3line(points.get(6), points.get(22)));
//        connections.add(new D3line(points.get(7), points.get(23)));
//        connections.add(new D3line(points.get(8), points.get(24)));
//        connections.add(new D3line(points.get(9), points.get(25)));
//        connections.add(new D3line(points.get(10), points.get(26)));
//        connections.add(new D3line(points.get(11), points.get(27)));
//        connections.add(new D3line(points.get(12), points.get(28)));
//        connections.add(new D3line(points.get(13), points.get(29)));
//        connections.add(new D3line(points.get(14), points.get(30)));
//        connections.add(new D3line(points.get(15), points.get(31)));
    }

}
