import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Utilities;


public class GUICalculator {

    private static final int screenWidth=1200;
    private static final int screenHeight=800;
    private final static String newline = "\n";
 
    private static Engine engine=new Engine();
    private static Formatter formatter=new Formatter();

    
      
    

    public static void drawScreen() {
      

      Font font1 = new Font("SansSerif", Font.BOLD, 50);
      Font font2 = new Font("SansSerif", Font.ITALIC, 20);
      Font font3 = new Font("SansSerif", Font.PLAIN, 30);
      Font font4 = new Font("SansSerif", Font.PLAIN, 17);

      JFrame f=new JFrame("Calculator");  
      JTextArea textBox=new JTextArea();
      JTextArea steps = new JTextArea();
      
      f.setSize(screenWidth,screenHeight);  
      
      textBox.setFont(font1);
    
      //textBox.setBounds(50,50, (int)(screenWidth*0.6),(int)(screenHeight*0.3));

      JScrollPane scrollPaneMain = new JScrollPane(textBox);
      scrollPaneMain.setBounds(50,50,(int)(screenWidth*0.6),(int)(screenHeight*0.325));
      
      f.add(scrollPaneMain);

      steps.setFont(font2);
      steps.setBounds((int)(screenWidth*0.7),50, (int)(screenWidth*0.2),(int)(screenHeight*0.8));

      JScrollPane scrollPaneSteps = new JScrollPane(steps);
      scrollPaneSteps.setBounds((int)(screenWidth*0.7),50, (int)(screenWidth*0.2),(int)(screenHeight*0.8));

      f.add(scrollPaneSteps);

      
      // BUTTONS

      // Bottom row (row 1)

      JButton submitButton=new JButton("=");  
      submitButton.setBounds((int)(screenWidth*0.49),screenHeight-200,170,50);

      submitButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){

            int currentIndex=textBox.getText().lastIndexOf("\n");
            String expression=textBox.getText().substring(currentIndex+1);

            System.out.println("Submit button was pressed");
           
            
            ArrayList<String> steps=Calculator.compute(expression);


            String answer=steps.get(steps.size() - 1);
            
            textBox.setText(textBox.getText()+"=\n" + answer+"\n"+"................................................"+"\n");
           
          
          }
      });

      JButton graphButton=new JButton("Graph");  
      graphButton.setBounds((int)(screenWidth*0.34),screenHeight-200,170,50);  
      graphButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("Graph button was pressed");
          }
      });

      JButton clearButton=new JButton("C");  
      clearButton.setBounds((int)(screenWidth*0.19),screenHeight-200,170,50);  
      clearButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("Clear button was pressed");
            textBox.setText("");
          }
      });

      JButton ansButton=new JButton("Ans");  
      ansButton.setBounds((int)(screenWidth*0.04),screenHeight-200,170,50);  
      ansButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("Ans button was pressed");
          }
      });

      // Row 2

      JButton dotButton=new JButton(".");
      dotButton.setFont(font3);
      dotButton.setBounds((int)(screenWidth*0.04),screenHeight-260,80,50);  
      dotButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println(". button was pressed");
            textBox.setText(textBox.getText()+".");
          }
      });

      JButton negButton=new JButton("(-)");  
      negButton.setBounds((int)(screenWidth*0.115),screenHeight-260,80,50);  
      negButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("Negative button was pressed");
            textBox.setText(textBox.getText()+"-");
          }
      });

      JButton zeroButton=new JButton("0");  
      zeroButton.setBounds((int)(screenWidth*0.19),screenHeight-260,80,50);  
      zeroButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("0 button was pressed");
            textBox.setText(textBox.getText()+"0");
          }
      });

      JButton piButton=new JButton("π");  
      piButton.setBounds((int)(screenWidth*0.265),screenHeight-260,80,50);  
      piButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("π button was pressed");
            textBox.setText(textBox.getText()+"π");
          }
      });

      JButton sinButton=new JButton("sin");  
      sinButton.setBounds((int)(screenWidth*0.34),screenHeight-260,80,50);  
      sinButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("sin button was pressed");
            textBox.setText(textBox.getText()+"sin(");
          }
      });

      JButton cosButton=new JButton("cos");  
      cosButton.setBounds((int)(screenWidth*0.415),screenHeight-260,80,50);  
      cosButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("cos button was pressed");
            textBox.setText(textBox.getText()+"cos(");
          }
      });

      JButton tanButton=new JButton("tan");  
      tanButton.setBounds((int)(screenWidth*0.49),screenHeight-260,80,50);  
      tanButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("tan button was pressed");
            textBox.setText(textBox.getText()+"tan(");
          }
      });

      JButton secondButton=new JButton("2nd");  
      secondButton.setBounds((int)(screenWidth*0.565),screenHeight-260,80,50);  
      secondButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("2nd button was pressed");

            // Implement 2nd button logic here

          }
      });

      JButton oneButton=new JButton("1");  
      oneButton.setBounds((int)(screenWidth*0.04),screenHeight-320,80,50);  
      oneButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("1 button was pressed");
            textBox.setText(textBox.getText()+"1");
          }
      });

      JButton twoButton=new JButton("2");  
      twoButton.setBounds((int)(screenWidth*0.115),screenHeight-320,80,50);  
      twoButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("2 button was pressed");
            textBox.setText(textBox.getText()+"2");
          }
      });

      JButton threeButton=new JButton("3");  
      threeButton.setBounds((int)(screenWidth*0.19),screenHeight-320,80,50);  
      threeButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("3 button was pressed");
            textBox.setText(textBox.getText()+"3");
          }
      });

      JButton openParanthesisButton=new JButton("(");  
      openParanthesisButton.setBounds((int)(screenWidth*0.265),screenHeight-320,80,50);  
      openParanthesisButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("( button was pressed");
            textBox.setText(textBox.getText()+"(");
          }
      });

      JButton closedParanthesisButton=new JButton(")");  
      closedParanthesisButton.setBounds((int)(screenWidth*0.34),screenHeight-320,80,50);  
      closedParanthesisButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println(") button was pressed");
            textBox.setText(textBox.getText()+")");
          }
      });

      JButton powerButton=new JButton("^");  
      powerButton.setBounds((int)(screenWidth*0.415),screenHeight-320,80,50);  
      powerButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("^ button was pressed");
            textBox.setText(textBox.getText()+"^");
          }
      });

      JButton inverseButton=new JButton("x^-1");  
      inverseButton.setBounds((int)(screenWidth*0.49),screenHeight-320,80,50);  
      inverseButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("x^-1 button was pressed");
            textBox.setText(textBox.getText()+"^-1");
          }
      });

      JButton mathButton=new JButton("math");  
      mathButton.setBounds((int)(screenWidth*0.565),screenHeight-320,80,50);  
      mathButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("math button was pressed");

            // implement math button logic here
            
          }
      });

      JButton fourButton=new JButton("4");  
      fourButton.setBounds((int)(screenWidth*0.04),screenHeight-380,80,50);  
      fourButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("4 button was pressed");
            textBox.setText(textBox.getText()+"4");
          }
      });

      JButton fiveButton=new JButton("5");  
      fiveButton.setBounds((int)(screenWidth*0.115),screenHeight-380,80,50);  
      fiveButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("5 button was pressed");
            textBox.setText(textBox.getText()+"5");
          }
      });

      JButton sixButton=new JButton("6");  
      sixButton.setBounds((int)(screenWidth*0.19),screenHeight-380,80,50);  
      sixButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("6 button was pressed");
            textBox.setText(textBox.getText()+"6");
          }
      });

      JButton eToTheXButton=new JButton("e^x");  
      eToTheXButton.setBounds((int)(screenWidth*0.265),screenHeight-380,80,50);  
      eToTheXButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("e^x button was pressed");
            textBox.setText(textBox.getText()+"e^");
          }
      });

      JButton xSquaredButton=new JButton("x^2");  
      xSquaredButton.setBounds((int)(screenWidth*0.34),screenHeight-380,80,50);  
      xSquaredButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("x^2 button was pressed");
            textBox.setText(textBox.getText()+"^2");
          }
      });

      JButton multButton=new JButton("x");
      multButton.setFont(font4);  
      multButton.setBounds((int)(screenWidth*0.415),screenHeight-380,80,50);  
      multButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("* button was pressed");
            textBox.setText(textBox.getText()+"*");
          }
      });

      JButton divButton=new JButton("/");
      divButton.setFont(font4);  
      divButton.setBounds((int)(screenWidth*0.49),screenHeight-380,80,50);  
      divButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("/ button was pressed");
            textBox.setText(textBox.getText()+"/");
          }
      });

      JButton delButton=new JButton("del");
      delButton.setBounds((int)(screenWidth*0.565),screenHeight-380,80,50);  
      delButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("del button was pressed");
            textBox.setText(textBox.getText().substring(0, textBox.getText().length()-1));
          }
      });
      
      JButton sevenButton=new JButton("7");  
      sevenButton.setBounds((int)(screenWidth*0.04),screenHeight-440,80,50);  
      sevenButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("7 button was pressed");
            textBox.setText(textBox.getText()+"7");
          }
      });

      JButton eightButton=new JButton("8");  
      eightButton.setBounds((int)(screenWidth*0.115),screenHeight-440,80,50);  
      eightButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("8 button was pressed");
            textBox.setText(textBox.getText()+"8");
          }
      });

      JButton nineButton=new JButton("9");  
      nineButton.setBounds((int)(screenWidth*0.19),screenHeight-440,80,50);  
      nineButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("9 button was pressed");
            textBox.setText(textBox.getText()+"9");
          }
      });

      JButton logButton=new JButton("log");  
      logButton.setBounds((int)(screenWidth*0.265),screenHeight-440,80,50);  
      logButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("log button was pressed");
            textBox.setText(textBox.getText()+"log(");
          }
      });

      JButton lnButton=new JButton("ln");  
      lnButton.setBounds((int)(screenWidth*0.34),screenHeight-440,80,50);  
      lnButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("ln button was pressed");
            textBox.setText(textBox.getText()+"ln(");
          }
      });

      JButton plusButton=new JButton("+");
      plusButton.setFont(font4);
      plusButton.setBounds((int)(screenWidth*0.415),screenHeight-440,80,50);  
      plusButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("+ button was pressed");
            textBox.setText(textBox.getText()+"+");
          }
      });

      JButton minusButton=new JButton("-");
      minusButton.setFont(font4);
      minusButton.setBounds((int)(screenWidth*0.49),screenHeight-440,80,50);  
      minusButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("- button was pressed");
            textBox.setText(textBox.getText()+"-");
          }
      });

      JButton modeButton=new JButton("mode");
      modeButton.setBounds((int)(screenWidth*0.565),screenHeight-440,80,50);  
      modeButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("mode button was pressed");
            
            // Implement settings button logic here

          }
      });
      

      f.add(submitButton);
      f.add(graphButton);
      f.add(clearButton);
      f.add(ansButton);

      f.add(dotButton);
      f.add(negButton);
      f.add(zeroButton);
      f.add(piButton);
      f.add(sinButton);
      f.add(cosButton);
      f.add(tanButton);

      f.add(oneButton);
      f.add(twoButton);
      f.add(threeButton);
      f.add(openParanthesisButton);
      f.add(closedParanthesisButton);
      f.add(powerButton);
      f.add(inverseButton);
      f.add(secondButton);

      f.add(fourButton);
      f.add(fiveButton);
      f.add(sixButton);
      f.add(eToTheXButton);
      f.add(xSquaredButton);
      f.add(multButton);
      f.add(divButton);
      f.add(mathButton);

      f.add(sevenButton);
      f.add(eightButton);
      f.add(nineButton);
      f.add(logButton);
      f.add(lnButton);
      f.add(plusButton);
      f.add(minusButton);
      f.add(delButton);
      f.add(modeButton);

      f.setLayout(null);  
      f.setVisible(true);
    
    }

    

    public static void main(String[] arguments) {
      drawScreen();
    
 
  }


}
