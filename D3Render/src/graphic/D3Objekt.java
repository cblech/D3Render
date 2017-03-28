/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.D3line;
import utils.D3vec;
import utils.Vec2;

/**
 *
 * @author jonathan
 */
public class D3Objekt {

    ArrayList<D3vec> points = new ArrayList<>();
    ArrayList<Vec2> connections = new ArrayList<>();

    public D3vec positoin = new D3vec(0, 0, 0);
    public D3vec rotation = new D3vec(0, 0, 0);

    private enum addS {
        Idel, Points, Lines
    }

    public D3Objekt() {
    }

    public D3Objekt(String p) {

        class TemporaryLineNotation {

            public TemporaryLineNotation(String a, String b) {
                this.a = a;
                this.b = b;
            }

            String a, b;
        }

        class TemporaryPointNotation extends D3vec {

            String name;
            D3vec actual3vec;

            public TemporaryPointNotation(String name, double x, double y, double z) {
                super(x, y, z);
                this.name = name;
            }
        }

        D3Objekt.addS st = D3Objekt.addS.Idel;
        int lineCount = 1;
        ArrayList<TemporaryLineNotation> tlns = new ArrayList<>();
        ArrayList<TemporaryPointNotation> tpns = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(p));

            if (!s.next().equals("#m3d")) {
                throw new Exception("No valid filetype. (Looking for \"#m3d [...]\")");
            }

            if (!s.next().equals("v:0.2")) {
                throw new Exception("Wrong m3d-File Version. (Looking for \"v:0.2\")");
            }

            while (s.hasNext()) {
                String line = s.nextLine();
                line = line.split("--")[0];
                Scanner t = new Scanner(line);
                t.useLocale(Locale.US);
                if (t.hasNext()) {

                    if (line.startsWith("#")) {
                        switch (t.next()) {
                            case "#points":
                                st = D3Objekt.addS.Points;
                                break;
                            case "#lines":
                                st = D3Objekt.addS.Lines;
                                break;
                            default:
                                throw new Exception("Unexpected add status at line: " + lineCount);

                        }
                    } else {
                        String[] lineArr = line.split(" ", 4);
                        switch (st) {
                            case Idel:
                                throw new Exception("No addstatus specified (line: " + lineCount + ")");

                            case Lines:

                                tlns.add(new TemporaryLineNotation(t.next(), t.next()));
                                if (t.hasNext()) {
                                    System.out.println("=> " + t.next());
                                    throw new Exception("Syntax error at line: " + lineCount);
                                }
                                break;
                            case Points:
                                if (lineArr.length != 4) {
                                    throw new Exception("Syntax error at line: " + lineCount);
                                }
                                tpns.add(new TemporaryPointNotation(t.next(), t.nextDouble(),
                                        t.nextDouble(), t.nextDouble()));
                                break;
                        }
                    }

                }
                lineCount++;
            }

            for (TemporaryPointNotation tpn : tpns) {
                D3vec tmpVec = new D3vec(tpn.x, tpn.y, tpn.z);
                points.add(tmpVec);
                tpn.actual3vec = tmpVec;
            }

            for (TemporaryLineNotation tln : tlns) {
                D3vec a = null, b = null;

                for (TemporaryPointNotation tpn : tpns) {
                    if (tpn.name.equals(tln.a)) {
                        a = tpn.actual3vec;
                    }
                    if (tpn.name.equals(tln.b)) {
                        b = tpn.actual3vec;
                    }
                }
                if (a == null) {
                    System.out.println("point: " + tln.a + " does not exist.");
                } else if (b == null) {
                    System.out.println("point: " + tln.b + " does not exist.");
                } else {
                    connections.add(new Vec2(points.indexOf(a), points.indexOf(b)));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(D3DynamicObjekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(D3DynamicObjekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Syntax error at line: " + lineCount);
        } catch (NoSuchElementException ex) {
            System.out.println("Syntax error at line: " + lineCount);
        } catch (Exception ex) {
            Logger.getLogger(D3DynamicObjekt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<D3line> getLines() {
        return null;
    }

    D3vec calculateRotationOffset(D3vec p) {
        return calculateRotationOffset(p, rotation);
    }

    D3vec calculateRotationOffset(D3vec p, D3vec r) {
        D3vec point = new D3vec(p.x, p.y, p.z);

        //y-axis
        double nx = point.x * Math.cos(r.y) + point.z * Math.sin(r.y);
        double ny = point.y;
        double nz = point.z * Math.cos(r.y) - point.x * Math.sin(r.y);
        point.x = nx;
        point.y = ny;
        point.z = nz;

        //x-axis
        nx = point.x;
        ny = point.z * Math.sin(r.x) + point.y * Math.cos(r.x);
        nz = point.z * Math.cos(r.x) - point.y * Math.sin(r.x);
        point.x = nx;
        point.y = ny;
        point.z = nz;

        //z-axis
        nx = point.x * Math.cos(r.z) - point.y * Math.sin(r.z);
        ny = point.x * Math.sin(r.z) + point.y * Math.cos(r.z);
        nz = point.z;
        point.x = nx;
        point.y = ny;
        point.z = nz;

        return point;
    }

}
