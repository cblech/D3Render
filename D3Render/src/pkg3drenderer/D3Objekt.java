/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3drenderer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class D3Objekt {

    ArrayList<D3vec> points = new ArrayList<>();
    ArrayList<Vec2> connections = new ArrayList<>();

    D3vec positoin = new D3vec(0, 0, 0);
    private D3vec rotation = new D3vec(0, 0, 0);

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

        addS s = addS.Idel;
        int lineCount = 1;
        ArrayList<TemporaryLineNotation> tlns = new ArrayList<>();
        ArrayList<TemporaryPointNotation> tpns = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(p));

            if (!bf.readLine().equals("#m3d v:0.1")) {
                throw new Exception("No valid filetype. (Looking for \"#m3d v:0.1\")");
            }

            lineCount++;

            while (bf.ready()) {
                String line = bf.readLine();
                if (!(line.startsWith("-") || line.equals(""))) {
                    if (line.startsWith("#")) {
                        if (line.equals("#points")) {
                            s = addS.Points;
                        } else if (line.equals("#lines")) {
                            s = addS.Lines;
                        } else {
                            System.out.println("Unexpected add status at line: " + lineCount);
                        }
                    } else {
                        String[] lineArr = line.split(" ", 4);
                        switch (s) {
                            case Idel:
                                System.out.println("No addstatus specified (line: " + lineCount + ")");
                                break;
                            case Lines:
                                if (lineArr.length != 2) {
                                    throw new Exception("Syntax error at line: " + lineCount);
                                }
                                tlns.add(new TemporaryLineNotation(lineArr[0], lineArr[1]));
                                break;
                            case Points:
                                if (lineArr.length != 4) {
                                    throw new Exception("Syntax error at line: " + lineCount);
                                }

                                tpns.add(new TemporaryPointNotation(lineArr[0], Double.parseDouble(lineArr[1]),
                                        Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[3])));
                                break;
                        }
                    }

                }
                lineCount++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(D3Objekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(D3Objekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Syntax error at line: " + lineCount);
        } catch (Exception ex) {
            Logger.getLogger(D3Objekt.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<D3line> getLines() {
        ArrayList<D3line> displayedLines = new ArrayList<>();
        ArrayList<D3vec> displayedPoints = new ArrayList<>();

        for (D3vec point : points) {
            displayedPoints.add(calculateRotationOffset(point));
        }
        
        for (D3vec displayedPoint : displayedPoints) {
            displayedPoint.x+=positoin.x;
            displayedPoint.y+=positoin.y;
            displayedPoint.z+=positoin.z;
        }
        
        for (Vec2 connection : connections) {
            displayedLines.add(new D3line(displayedPoints.get(connection.a), displayedPoints.get(connection.b)));
        }

        return displayedLines;
    }

    private D3vec calculateRotationOffset(D3vec p) {
        D3vec point = new D3vec(p.x, p.y, p.z);

        //y-axis
        double nx = point.x * Math.cos(rotation.y) + point.z * Math.sin(rotation.y);
        double ny = point.y;
        double nz = point.z * Math.cos(rotation.y) - point.x * Math.sin(rotation.y);
        point.x = nx;
        point.y = ny;
        point.z = nz;

        //x-axis
        nx = point.x;
        ny = point.z * Math.sin(rotation.x) + point.y * Math.cos(rotation.x);
        nz = point.z * Math.cos(rotation.x) - point.y * Math.sin(rotation.x);
        point.x = nx;
        point.y = ny;
        point.z = nz;

        //z-axis
        nx = point.x * Math.cos(rotation.z) - point.y * Math.sin(rotation.z);
        ny = point.x * Math.sin(rotation.z) + point.y * Math.cos(rotation.z);
        nz = point.z;
        point.x = nx;
        point.y = ny;
        point.z = nz;

        return point;
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
