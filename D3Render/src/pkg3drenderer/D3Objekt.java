/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3drenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
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

        addS st = addS.Idel;
        int lineCount = 1;
        ArrayList<TemporaryLineNotation> tlns = new ArrayList<>();
        ArrayList<TemporaryPointNotation> tpns = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(p));

            if (!s.next().equals("#m3d")) {
                throw new Exception("No valid filetype. (Looking for \"#m3d v:0.1\")");
            }

            if (!s.next().equals("v:0.2")) {
                throw new Exception("Wrong m3d-File Version");
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
                                st = addS.Points;
                                break;
                            case "#lines":
                                st = addS.Lines;
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
            Logger.getLogger(D3Objekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(D3Objekt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Syntax error at line: " + lineCount);
        } catch (NoSuchElementException ex) {
            System.out.println("Syntax error at line: " + lineCount);
        } catch (Exception ex) {
            Logger.getLogger(D3Objekt.class.getName()).log(Level.SEVERE, null, ex);
        }

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
