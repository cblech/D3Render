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
public class D3QuadCurve {
    public D3vec start, cont, end;

    public D3QuadCurve(D3vec start, D3vec cont, D3vec end) {
        this.start = start;
        this.cont = cont;
        this.end = end;
    }
}
