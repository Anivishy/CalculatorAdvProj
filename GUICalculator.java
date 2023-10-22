import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Utilities;


public class GUICalculator {

    private static int screenWidth=1200;
    private static int screenHeight=800;
    private final static String newline = "\n";
      
    private enum Mode {
      DEFAULT,
      EXTENDED,
      GRAPHING,
      STAT,
      SETTINGS
    }

    private static Mode mode=Mode.DEFAULT;
    
    private static int index=0;

    static JButton graphButton;
    static JButton extdButton;
    static JButton statButton;
    static JButton settingsButton;

    static JTextArea modeBox;
    static JFrame f;

    static JButton[] buttonsDefault;
    static JButton[] buttonsGraphing;
    static JButton[] buttonsStat;
    static JButton[] buttonsExtd;
    static JButton[] buttonsSettings;

    static JButton[] currentButtons;
  

    private static final Font font1 = new Font("SansSerif", Font.PLAIN, 40);
    private static final Font font2 = new Font("SansSerif", Font.ITALIC, 20);
    private static final Font font3 = new Font("SansSerif", Font.PLAIN, 30);
    private static final Font font4 = new Font("SansSerif", Font.PLAIN, 17);
    private static final Font font5 = new Font("SansSerif", Font.BOLD, 17);


    public static void drawScreen() {
      

      f=new JFrame("Calculator");
      Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\aksha\\Downloads\\Clash-Royale-emblem.png");
        
      f.setIconImage(icon);
       
      JTextArea textBox=new JTextArea();
      JTextArea steps = new JTextArea();

      modeBox=new JTextArea();
      modeBox.setText("Default Mode");
      
      f.setSize(screenWidth,screenHeight);  
      
      textBox.setFont(font1);

      modeBox.setFont(font5);
      modeBox.setSize(300, 50);
      modeBox.setBounds(50, 20, 200, 30);
      modeBox.setEditable(false);

      JScrollPane scrollPaneMain = new JScrollPane(textBox);
      scrollPaneMain.setBounds(50,70,(int)(screenWidth*0.6),(int)(screenHeight*0.325));
      
      f.add(scrollPaneMain);

      steps.setFont(font2);
      steps.setBounds((int)(screenWidth*0.7),50, (int)(screenWidth*0.2),(int)(screenHeight*0.8));
      steps.setEditable(false);
      steps.setText("               STEPS" + "\n" + "_____________________  \n");
      
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
            
            // Updating the main answer text box
            try {
              ArrayList<String> result=Calculator.compute(expression, false);
              String answer=result.get(result.size() - 1);

              textBox.setText(textBox.getText()+"=\n" + answer+"\n"+"............................................................."+"\n");
              
              // Updating steps box

              steps.setText(steps.getText() + " Input: " + result.get(0) + "\n");
              for (int i = 1; i < result.size() - 1; i++){

                steps.setText(steps.getText() + " Step #" + i + ": " + result.get(i) + "\n");
              } 
              steps.setText(steps.getText() + " Answer: " + result.get(result.size() - 1) + "\n");
              steps.setText(steps.getText() + "_____________________ \n");
              //steps.setText(steps.getText() + ".......................................... \n");
            }
            catch (Exception error){
              textBox.setText(" \n     INVALID EXPRESSION");
            }
            
          }
      });

      JButton submitGraphButton=new JButton("Graph");  
      submitGraphButton.setBounds((int)(screenWidth*0.49),screenHeight-200,170,50);

      submitGraphButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){

          }
      });
      submitGraphButton.setVisible(false);

      graphButton=new JButton("Graph");  
      graphButton.setBounds((int)(screenWidth*0.34),screenHeight-200,170,50);  
      graphButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            if (mode==mode.GRAPHING) {
              changeMode(Mode.DEFAULT, graphButton, "Graphing");
            }
            else {
              changeMode(Mode.GRAPHING, graphButton, "back");
            }
          }
      });

      JButton clearButton=new JButton("C");  
      clearButton.setBounds((int)(screenWidth*0.19),screenHeight-200,170,50);  
      clearButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText("");
            steps.setText("               STEPS" + "\n" + "_____________________  \n");
          }
      });

      JButton ansButton=new JButton("Ans");  
      ansButton.setBounds((int)(screenWidth*0.04),screenHeight-200,170,50);  
      ansButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            // implement logic here
          }
      });

      // Row 2

      JButton dotButton=new JButton(".");
      dotButton.setFont(font3);
      dotButton.setBounds((int)(screenWidth*0.04),screenHeight-260,80,50);  
      dotButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+".");
          }
      });

      JButton negButton=new JButton("(-)");  
      negButton.setBounds((int)(screenWidth*0.115),screenHeight-260,80,50);  
      negButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"-");
          }
      });

      JButton zeroButton=new JButton("0");  
      zeroButton.setBounds((int)(screenWidth*0.19),screenHeight-260,80,50);  
      zeroButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"0");
          }
      });

      JButton piButton=new JButton("π");  
      piButton.setBounds((int)(screenWidth*0.265),screenHeight-260,80,50);  
      piButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"π");
          }
      });

      JButton yEqualsButton=new JButton("y=");  
      yEqualsButton.setBounds((int)(screenWidth*0.265),screenHeight-260,80,50);  
      yEqualsButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"y=");
          }
      });
      yEqualsButton.setVisible(false);

      JButton sinButton=new JButton("sin");  
      sinButton.setBounds((int)(screenWidth*0.34),screenHeight-260,80,50);  
      sinButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"sin(");
          }
      });
     
      JButton cosButton=new JButton("cos");  
      cosButton.setBounds((int)(screenWidth*0.415),screenHeight-260,80,50);  
      cosButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"cos(");
          }
      });

      JButton tanButton=new JButton("tan");  
      tanButton.setBounds((int)(screenWidth*0.49),screenHeight-260,80,50);  
      tanButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"tan(");
          }
      });

      statButton=new JButton("stat");  
      statButton.setBounds((int)(screenWidth*0.565),screenHeight-260,80,50);  
      statButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            if (mode==mode.STAT) {
              changeMode(Mode.DEFAULT, statButton, "Stat");
            }
            else {
              changeMode(Mode.STAT, statButton, "back");
            }
          }
      });

      JButton oneButton=new JButton("1");  
      oneButton.setBounds((int)(screenWidth*0.04),screenHeight-320,80,50);  
      oneButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"1");
          }
      });

      JButton twoButton=new JButton("2");  
      twoButton.setBounds((int)(screenWidth*0.115),screenHeight-320,80,50);  
      twoButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"2");
          }
      });

      JButton threeButton=new JButton("3");  
      threeButton.setBounds((int)(screenWidth*0.19),screenHeight-320,80,50);  
      threeButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"3");
          }
      });

      JButton openParanthesisButton=new JButton("(");  
      openParanthesisButton.setBounds((int)(screenWidth*0.265),screenHeight-320,80,50);  
      openParanthesisButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"(");
          }
      });

      JButton closedParanthesisButton=new JButton(")");  
      closedParanthesisButton.setBounds((int)(screenWidth*0.34),screenHeight-320,80,50);  
      closedParanthesisButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+")");
          }
      });

      JButton powerButton=new JButton("^");  
      powerButton.setBounds((int)(screenWidth*0.415),screenHeight-320,80,50);  
      powerButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"^");
          }
      });

      JButton inverseButton=new JButton("x^-1");  
      inverseButton.setBounds((int)(screenWidth*0.49),screenHeight-320,80,50);  
      inverseButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"^-1");
          }
      });

      extdButton=new JButton("extd");  
      extdButton.setBounds((int)(screenWidth*0.565),screenHeight-320,80,50);  
      extdButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            if (mode==mode.EXTENDED) {
              changeMode(Mode.DEFAULT, extdButton, "Extd");
            }
            else {
              changeMode(Mode.EXTENDED, extdButton, "back");
            }
          }
      });

      JButton windowButton=new JButton("window");  
      windowButton.setBounds((int)(screenWidth*0.565),screenHeight-320,80,50);  
      windowButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText() + "window(minX, maxX, minY, maxY)");
          }
      });
      windowButton.setVisible(false);

      JButton fourButton=new JButton("4");  
      fourButton.setBounds((int)(screenWidth*0.04),screenHeight-380,80,50);  
      fourButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"4");
          }
      });

      JButton fiveButton=new JButton("5");  
      fiveButton.setBounds((int)(screenWidth*0.115),screenHeight-380,80,50);  
      fiveButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"5");
          }
      });

      JButton sixButton=new JButton("6");  
      sixButton.setBounds((int)(screenWidth*0.19),screenHeight-380,80,50);  
      sixButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"6");
          }
      });

      JButton eToTheXButton=new JButton("e^x");  
      eToTheXButton.setBounds((int)(screenWidth*0.265),screenHeight-380,80,50);  
      eToTheXButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"e^");
          }
      });

      // Only for extd mode

      JButton absoluteValueButton=new JButton("|");  
      absoluteValueButton.setBounds((int)(screenWidth*0.265),screenHeight-380,80,50);  
      absoluteValueButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"|");
          }
      });
      absoluteValueButton.setVisible(false);

      JButton xSquaredButton=new JButton("x^2");  
      xSquaredButton.setBounds((int)(screenWidth*0.34),screenHeight-380,80,50);  
      xSquaredButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"^2");
          }
      });

      JButton sqRootButton=new JButton("sqrt(");  
      sqRootButton.setBounds((int)(screenWidth*0.34),screenHeight-380,80,50);  
      sqRootButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"sqrt(");
          }
      });
      sqRootButton.setVisible(false);



      JButton multButton=new JButton("*");
      multButton.setFont(font4);  
      multButton.setBounds((int)(screenWidth*0.415),screenHeight-380,80,50);  
      multButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"*");
          }
      });

      JButton divButton=new JButton("/");
      divButton.setFont(font4);  
      divButton.setBounds((int)(screenWidth*0.49),screenHeight-380,80,50);  
      divButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"/");
          }
      });

      JButton delButton=new JButton("del");
      delButton.setBounds((int)(screenWidth*0.565),screenHeight-380,80,50);  
      delButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText().substring(0, textBox.getText().length()-1));
          }
      });

      
      
      JButton sevenButton=new JButton("7");  
      sevenButton.setBounds((int)(screenWidth*0.04),screenHeight-440,80,50);  
      sevenButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"7");
          }
      });

      JButton eightButton=new JButton("8");  
      eightButton.setBounds((int)(screenWidth*0.115),screenHeight-440,80,50);  
      eightButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"8");
          }
      });

      JButton nineButton=new JButton("9");  
      nineButton.setBounds((int)(screenWidth*0.19),screenHeight-440,80,50);  
      nineButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"9");
          }
      });

      JButton logButton=new JButton("log");  
      logButton.setBounds((int)(screenWidth*0.265),screenHeight-440,80,50);  
      logButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"log(");
          }
      });

      // Only for extd mode

      JButton factorialButton=new JButton("!");  
      factorialButton.setBounds((int)(screenWidth*0.265),screenHeight-440,80,50);  
      factorialButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"!");
          }
      });
      factorialButton.setVisible(false);

      JButton lnButton=new JButton("ln");  
      lnButton.setBounds((int)(screenWidth*0.34),screenHeight-440,80,50);  
      lnButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"ln(");
          }
      });

      JButton randomButton=new JButton("rand");  
      randomButton.setBounds((int)(screenWidth*0.34),screenHeight-440,80,50);  
      randomButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"rand(min,max)");
          }
      });
      randomButton.setVisible(false);

      JButton plusButton=new JButton("+");
      plusButton.setFont(font4);
      plusButton.setBounds((int)(screenWidth*0.415),screenHeight-440,80,50);  
      plusButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"+");
          }
      });

      JButton minusButton=new JButton("-");
      minusButton.setFont(font4);
      minusButton.setBounds((int)(screenWidth*0.49),screenHeight-440,80,50);  
      minusButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"-");
          }
      });

      settingsButton=new JButton("settings");
      settingsButton.setBounds((int)(screenWidth*0.565),screenHeight-440,80,50);  
      settingsButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            if (mode==mode.SETTINGS) {
              changeMode(Mode.DEFAULT, settingsButton, "Settings");
            }
            else {
              changeMode(Mode.SETTINGS, settingsButton, "back");
            }
          }
      });

      JButton xButton=new JButton("x");
      xButton.setBounds((int)(screenWidth*0.565),screenHeight-440,80,50);  
      xButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText()+"x");
          }
      });
      xButton.setVisible(false);

      buttonsDefault=new JButton[] {
        submitButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton, piButton, sinButton, cosButton, tanButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, statButton, 
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, extdButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton, settingsButton};
      
      buttonsExtd=new JButton[] {
        submitButton, clearButton, ansButton, dotButton, negButton, zeroButton, piButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton,
        fourButton, fiveButton, sixButton, absoluteValueButton, sqRootButton, multButton, divButton, extdButton, sevenButton, eightButton,
        nineButton, factorialButton, randomButton, plusButton, minusButton, delButton, settingsButton};
      
      buttonsGraphing=new JButton[] {
        submitGraphButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton, yEqualsButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, 
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, windowButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton, xButton};
      
      buttonsStat=new JButton[] {
        submitButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, statButton,
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton};
      
      buttonsSettings=new JButton[] {
        submitButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton, piButton, sinButton, cosButton, tanButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, statButton, 
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, extdButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton, settingsButton};

      currentButtons=buttonsDefault;

      f.add(modeBox);
      for (JButton b:buttonsDefault) {
        f.add(b);
      }
      f.setLayout(null);  
      f.setVisible(true);
    
    }

    public static void changeMode(Mode newMode, JButton oldButton, String newText) {
      for (JButton button:currentButtons) {
        button.setVisible(false);
      }

      if (newMode==Mode.DEFAULT) {
        
        changeMode(oldButton,newMode, newText, buttonsDefault);
        currentButtons=buttonsDefault;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
      }

      else if (newMode==Mode.EXTENDED) {
        changeMode(oldButton,newMode, newText, buttonsExtd);
        currentButtons=buttonsExtd;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
      }

      else if (newMode==Mode.GRAPHING) {
        //mode=Mode.GRAPHING;
        for (JButton b:buttonsGraphing) {
          f.add(b);
        }
        
        changeMode(graphButton, mode.GRAPHING, newText, buttonsGraphing);
        currentButtons=buttonsGraphing;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
            
      }

      else if (newMode==Mode.SETTINGS) {
        changeMode(oldButton,newMode, newText, buttonsSettings);
        currentButtons=buttonsSettings;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
      }

      else if (newMode==Mode.STAT) {
        changeMode(oldButton,newMode, newText, buttonsStat);
        currentButtons=buttonsStat;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
      }

    }

    public static void changeMode(JButton button, Mode newMode, String newText, JButton[] buttons) {
      for (JButton b:buttons) {
        f.add(b);
      }
      mode=newMode;
      String newModeText=(newMode.name().charAt(0) + "").toUpperCase() + (newMode.name().substring(1)).toLowerCase() + " Mode";
      button.setText(newText);
      modeBox.setText(newModeText);
      f.add(button);
    }

    public static void main(String[] arguments) {
      
      drawScreen();
      
    }

}
