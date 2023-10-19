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
    private static final int numberOfPoints=1000;

    // window=[[xMin, xMax], [yMin, yMax]]
    private static double[][] window;
    private static double[][] graphCoords;
    private static double[][] plotPoints;

    public Grapher(double[][] window){
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(450,450);

        this.window=window;
        this.graphCoords=new double[numberOfPoints][2];
        this.plotPoints=new double[numberOfPoints][2];

    }

    public void changeWindow(double[][] newWindow) {
        this.window=newWindow;
    }

    public static void graph(String equation) {

    }

    public void getPoints(String equation) {
        int index=0;
        for (double xValue=0; xValue<screenWidth; xValue+=((Grapher.window[0][1]-this.window[0][0])/numberOfPoints)) {
            
            String equationSubstitute=equation.replaceAll("x", "(" + String.valueOf(xValue) + ")");
            ArrayList<String> result=Calculator.compute(equationSubstitute);
            Double yValue=Double.parseDouble(result.get(result.size() - 1));
            graphCoords[index][0]=xValue;
            graphCoords[index][1]=yValue;
            index+=1;

            double plotX=(xValue+((window[0][1]-window[0][0])/2))/(window[0][1]-window[0][0]) * screenWidth;
            double plotY=(yValue+((window[1][1]-window[1][0])/2))/(window[1][1]-window[1][0]) * screenHeight;

            plotPoints[index][0]=plotX;
            plotPoints[index][1]=plotY;

            

        }
        
        //

        
            
        
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
        
    }

    public static void main(String []args){
        window=new double[][] {{-10.0, 10.0}, {-10.0, 10.0}};
        Grapher s=new Grapher(window);
        s.getPoints("2(x+1) + x^2");
        //s.setVisible(true);
    }
    

      

}
