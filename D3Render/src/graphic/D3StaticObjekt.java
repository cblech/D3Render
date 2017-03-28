/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

/**
 *
 * @author luebeck.jonathan
 */
public class D3StaticObjekt extends D3Objekt{
    
    public D3StaticObjekt() {
        super();
    }
    
    public D3StaticObjekt(String p) {
        super(p);
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
}
