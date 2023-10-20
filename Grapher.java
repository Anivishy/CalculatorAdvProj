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

    private static final int screenWidth=900;
    private static final int screenHeight=900;
    private static final int numberOfPoints=5000;

    // window=[[xMin, xMax], [yMin, yMax]]

    private static double[][] graphCoords;
    private static double[][] plotPoints;

 
    public Grapher(){
    
       
        this.graphCoords=new double[numberOfPoints][2];
        this.plotPoints=new double[numberOfPoints][2];
    

    }

    private static void createAndShowGui() {
      
      Grapher mainPanel = new Grapher();

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setSize(screenWidth, screenHeight);
      frame.setVisible(true);
      
   }
   

    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        String equation="x^3+1";
        Graphics2D g2 = (Graphics2D) g;

        getPoints(equation);

        for (int i=0; i<graphCoords.length; i++) {
            System.out.println(plotPoints[i][0] + ", " + plotPoints[i][1]);
            g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1], plotPoints[i][0], plotPoints[i][1]));
            //g.drawLine(graphCoords[i][0], graphCoords[i][1], graphCoords[i][0], graphCoords[i][1]);
            
        }
        


    }

    public static void getPoints(String equation) {
        int index=0;
        for (double xValue=(-1*(screenWidth/2.0)); xValue<(screenWidth/2.0); xValue+=(screenWidth/(numberOfPoints*1.0))) {
            
            String equationSubstitute=equation.replaceAll("x", "(" + String.valueOf(xValue) + ")");
            System.out.println("substitue: " + equationSubstitute);
            ArrayList<String> result=Calculator.compute(equationSubstitute, true);
            Double yValue=Double.parseDouble(result.get(result.size() - 1));
            graphCoords[index][0]=xValue;
            graphCoords[index][1]=yValue;
            
            
            double plotX=xValue+(screenWidth/2.0);
            double plotY = -1*yValue + (screenHeight/2.0);
            
            plotPoints[index][0]=plotX;
            plotPoints[index][1]=plotY;
            index+=1;

            if (index==1000) {
                break;
            }
        }
        
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGui();
            }
        });
        
    }

      

}
