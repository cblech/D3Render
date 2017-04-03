/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author luebeck.jonathan
 */
public class D3CubCurve {
    public D3vec start,cont1,cont2,end;

    public D3CubCurve(D3vec start, D3vec cont1, D3vec cont2, D3vec end) {
        this.start = start;
        this.cont1 = cont1;
        this.cont2 = cont2;
        this.end = end;
    }
}
