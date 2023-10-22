import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.*;

public class Grapher extends JPanel{

    // plotPoints is list of points to graph

    private double[][] plotPoints;
    private static Boolean drawLine=true;
    private String equation;
 
    public Grapher(String equation){

        this.plotPoints=new double[Config.numberOfPoints][2];
        this.equation=equation;
    }

    public static void createAndShowGui(String equation) {
      
      Grapher mainPanel = new Grapher(equation);
      JFrame frameGraph = new JFrame("DrawGraph");

      frameGraph.getContentPane().add(mainPanel);
      frameGraph.pack();
      frameGraph.setLocationByPlatform(true);
      frameGraph.setSize(Config.screenWidth, Config.screenHeight);
      frameGraph.setVisible(true);
   }
   
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3=(Graphics2D)g;
        
        g2.setColor(Config.red);
        getPoints();
        g2.translate(Config.screenWidth/2, Config.screenHeight/2);
        g2.scale(10, 10);
        g2.translate(-Config.screenWidth/2, -Config.screenHeight/2);

        for (int i=0; i<plotPoints.length-1; i++) {
            g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1],plotPoints[i+1][0], plotPoints[i+1][1]));
           
            // For functions without asymptotes, the graph will look more realistic if we connect lines between each points

            if (drawLine) {
                g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1],plotPoints[i+1][0], plotPoints[i+1][1]));
            }
            else {
                g2.draw(new Line2D.Double(plotPoints[i][0], plotPoints[i][1], plotPoints[i][0], plotPoints[i][1]));
            }
        }

        // Drawing axes

        g3.setColor(new Color(0, 0, 0));
        g3.drawLine(0, (int)(Config.screenHeight/2), Config.screenWidth, (int)(Config.screenHeight/2));
        g3.drawLine((int)(Config.screenWidth/2), 0, (int)(Config.screenWidth/2), Config.screenHeight);

    }

    // For certain functions centered at x=0 with y intercepts that aren't 0, I translate the points to exaggerate the y intercepts.
    // I am checking for such functions below

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
            System.out.println("error");
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
        for (double xValue=(-1*(Config.screenWidth/2.0)); xValue<(Config.screenWidth/2.0); xValue+=(Config.screenWidth/(Config.numberOfPoints*1.0))) {
            
            // We are generating the points by incrementing through x coordinates, substituting x as the x value, 
            //  and computing the result from the engine before translating the points

            equationSubstitute=equation.replaceAll("x", "(" + String.valueOf(xValue) + ")");
            try {
                ArrayList<String> result=Calculator.compute(equationSubstitute, true);
                yValue=Double.parseDouble(result.get(result.size() - 1));
            }
            catch(Exception error) {
                System.out.println("error");
            }
            
            double plotX=xValue+(Config.screenWidth/2.0);
            double plotY = -1*yValue + (Config.screenHeight/2.0);
            
            plotPoints[index][0]=plotX;
            plotPoints[index][1]=plotY+translateFactor;
            index+=1;
            if (index==Config.numberOfPoints) {
                break;
            }
        }
    }
}
