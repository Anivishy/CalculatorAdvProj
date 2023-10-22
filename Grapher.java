import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Utilities;

public class Grapher extends JPanel{

    private static final int screenWidthDimension=1200;
    private static final int screenHeightDimension=800;

    private static double screenHeight=screenHeightDimension;
    private static double screenWidth=screenWidthDimension;

    private static final int numberOfPoints=1000;

    // window=[[xMin, xMax], [yMin, yMax]]

    private static double[][] graphCoords;
    private static double[][] plotPoints;

    private static Boolean drawLine=true;
    private String equation;
 
    public Grapher(String equation){
    
       
        this.graphCoords=new double[numberOfPoints][2];
        this.plotPoints=new double[numberOfPoints][2];
        this.equation=equation;

    }

    public static void createAndShowGui(String equation) {
      
      Grapher mainPanel = new Grapher(equation);

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setSize(screenWidthDimension, screenHeightDimension);
      frame.setVisible(true);
      
   }
   

    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        
        Graphics2D g2 = (Graphics2D) g;
        
        Graphics2D g3=(Graphics2D)g;
        
        g2.setColor(new Color(57, 255, 20));
        getPoints();
        
       

        g2.translate(screenWidth/2, screenHeight/2);
        g2.scale(10, 10);
        g2.translate(-screenWidth/2, -screenHeight/2);

        for (int i=0; i<plotPoints.length-1; i++) {
            System.out.println(plotPoints[i][0] + ", " + plotPoints[i][1]);
            g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1],plotPoints[i+1][0], plotPoints[i+1][1]));
           
            if (drawLine) {
                
                g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1],plotPoints[i+1][0], plotPoints[i+1][1]));
            }

            else {
                
                g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1], plotPoints[i][0], plotPoints[i][1]));
            }
            
            
            //g3.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1], plo tPoints[i][0], plotPoints[i][1]));
            //g.drawLine(graphCoords[i][0], graphCoords[i][1], graphCoords[i][0], graphCoords[i][1]);
            
        }

        g3.setColor(new Color(0, 0, 0));

        g3.drawLine(0, (int)(screenHeightDimension/2), screenWidthDimension, (int)(screenHeightDimension/2));
        g3.drawLine((int)(screenWidthDimension/2), 0, (int)(screenWidthDimension/2), screenHeightDimension);

    }

    public boolean checkValidTranslate() {
        String[] invalidFunctions={"ln", "log", "e", "x^-1"};
        for (String funct:invalidFunctions) {
            if (this.equation.indexOf(funct)!=-1) {
                return false;                
            }
        }
        
        return ((this.equation.split("x", -1).length - 1)<=1);

    }


    public void getPoints() {
        String equationSubstitute=this.equation.replaceAll("x", "(" + String.valueOf(0) + ")");
        double yIntercept=0.0;
        double translateFactor=0.0;
        try {
            if (equationSubstitute.indexOf("ln")==-1 && equationSubstitute.indexOf("log")==-1) {
                ArrayList<String> result=Calculator.compute(equationSubstitute, true);
                yIntercept=Double.parseDouble(result.get(result.size() - 1));
            }
            
        }
        
        catch(Exception error) {
            
            //blah
            
        }
        if (checkValidTranslate()) {
            if (yIntercept>0) {
                translateFactor=-7;
            }

            else if (yIntercept<0) {
                translateFactor=7;
            }
        }
        

        int index=0;
        double yValue=0.0;
        for (double xValue=(-1*(screenWidth/2.0)); xValue<(screenWidth/2.0); xValue+=(screenWidth/(numberOfPoints*1.0))) {
            
            equationSubstitute=equation.replaceAll("x", "(" + String.valueOf(xValue) + ")");
            
            
            try {
                ArrayList<String> result=Calculator.compute(equationSubstitute, true);
                yValue=Double.parseDouble(result.get(result.size() - 1));
            }
            
            catch(Exception error) {
                // blah
                
            }

            
            
            graphCoords[index][0]=xValue;
            graphCoords[index][1]=yValue;
            
            
            double plotX=xValue+(screenWidth/2.0);
            double plotY = -1*yValue + (screenHeight/2.0);
            
            plotPoints[index][0]=plotX;
            //plotPoints[index][1]=plotY;
            plotPoints[index][1]=plotY+translateFactor;
            index+=1;

            

            if (index==numberOfPoints) {
                
                break;
            }
        }

        
    }
    
    public static void main(String[] args) {
        /** 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGui();
            }
        });
        **/
        
    }

      

}
