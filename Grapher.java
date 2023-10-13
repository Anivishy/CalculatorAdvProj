import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Utilities;

public class Grapher extends JFrame{

    private static final int screenWidth=1200;
    private static final int screenHeight=800;

    public Grapher(){
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(450,450);

    }

   

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int numberOfPoints=1000;
        
        int[] x=new int[numberOfPoints];
        int[] y=new int[numberOfPoints];

        int leftBound=100;
        int rightBound=500;
        g.drawLine(leftBound, rightBound, leftBound+100, rightBound+100);
        //g.drawLine(leftBound, rightBound, leftBound, rightBound);
        /** 
        double increment=((rightBound-leftBound)/numberOfPoints);
        for (int i=leftBound; i<rightBound; i+=increment) {
            x[i-leftBound]=i;
            y[i-leftBound]=(i-leftBound)*(i-leftBound);
            g.drawLine(i, (i-leftBound)*(i-leftBound), i+3, (i-leftBound)*(i-leftBound) +3);
            break;
        }
        **/
        

    

        //g.drawPolygon(x, y, 3);
        //g.draw3DRect(100, 100, 200, 100, true);
          // fixes the immediate problem.
        //Graphics2D g2 = (Graphics2D) g;
        //Line2D lin = new Line2D.Float(100, 100, 250, 260);
        //g2.draw(lin);
    }

    public static void main(String []args){
        Grapher s=new Grapher();
        s.setVisible(true);
    }
    

      

}
