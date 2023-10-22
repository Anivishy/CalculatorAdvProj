import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GUICalculator {

    // Initializing enums for different modes  
  
    private enum Mode {
      DEFAULT,
      EXTENDED,
      GRAPHING,
      STAT,
      SETTINGS
    }

    // Establishing current mode as default

    private static Mode mode=Mode.DEFAULT;

    // Defining buttons to modify/access outside of main drawScreen function

    private static JButton graphButton;
    private static JButton extdButton;
    private static JButton statButton;
    private static JButton settingsButton;
    private static JButton clearButton;
    private static JButton submitGraphButton;
    private static JButton submitButton;
    private static JButton delButton;

    private static JTextArea modeBox;
    private static JFrame f=new JFrame("Calculator");;
    private static JScrollPane scrollPaneMain;
    private static JScrollPane scrollPaneSteps;
    private static JTextArea textBox;

    // Defining Arrays of button categories to store all the buttons for a particular category

    private static JButton[] buttonsDefault;
    private static JButton[] buttonsGraphing;
    private static JButton[] buttonsStat;
    private static JButton[] buttonsExtd;
    private static JButton[] buttonsSettings;

    private static JButton[] currentButtons;
    private static JButton[] changedButtons;
    
    // Initializing current color-scheme

    private static String currentColorScheme="dark-gold";

    public static void drawScreen() {
       
      // Defining text boxes to input expression and show steps

      textBox=new JTextArea();
      textBox.setText(" ");
      JTextArea steps = new JTextArea();
      modeBox=new JTextArea();
      modeBox.setText("    Default Mode");
      
      f.setSize(Config.screenWidth,Config.screenHeight);  
      
      textBox.setFont(Config.font1);
      modeBox.setFont(Config.font5);
      modeBox.setSize(300, 50);
      modeBox.setBounds(50, 20, 200, 30);
      modeBox.setEditable(false);

      scrollPaneMain = new JScrollPane(textBox);
      scrollPaneMain.setBounds(50,70,(int)(Config.screenWidth*0.6),(int)(Config.screenHeight*0.325));
      
      f.add(scrollPaneMain);

      steps.setFont(Config.font2);
      steps.setBounds((int)(Config.screenWidth*0.7),50, (int)(Config.screenWidth*0.2),(int)(Config.screenHeight*0.8));
      steps.setEditable(false);
      steps.setText("               STEPS" + "\n" + "_____________________  \n");
      
      scrollPaneSteps = new JScrollPane(steps);
      scrollPaneSteps.setBounds((int)(Config.screenWidth*0.7),50, (int)(Config.screenWidth*0.2),(int)(Config.screenHeight*0.8));

      f.add(scrollPaneSteps);
      
      
      // Button initializations

      // Defining submit button

      submitButton=new JButton("=");  
      submitButton.setBounds((int)(Config.screenWidth*0.49),Config.screenHeight-200,170,50);
      submitButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){

            int currentIndex=textBox.getText().lastIndexOf("\n");
            String expression=textBox.getText().substring(currentIndex+1);
            
            // Updating the main answer text box

            try {
              ArrayList<String> result=Calculator.compute(expression, false);
              String answer=result.get(result.size() - 1);

              textBox.setText(textBox.getText()+"=\n " + answer+"\n"+"............................................................."+"\n ");
              
              // Updating steps box

              steps.setText(steps.getText() + " Input: " + result.get(0) + "\n");
              for (int i = 1; i < result.size() - 1; i++){

                steps.setText(steps.getText() + " Step #" + i + ": " + result.get(i) + "\n");
              } 
              steps.setText(steps.getText() + " Answer: " + result.get(result.size() - 1) + "\n");
              steps.setText(steps.getText() + "_____________________ \n");

            }
            catch (Exception error){
              textBox.setText(" \n     INVALID EXPRESSION");
            }
          }
      });

      submitGraphButton=new JButton("Graph");  
      submitGraphButton.setBounds((int)(Config.screenWidth*0.49),Config.screenHeight-200,170,50);
      submitGraphButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            int currentIndex=textBox.getText().lastIndexOf("\n");
            String equation=textBox.getText().substring(currentIndex+3);
            Grapher.createAndShowGui(equation);
          }
      });
      submitGraphButton.setVisible(false);

      graphButton=new JButton("Graph");  
      graphButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-200,170,50);  
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

      clearButton=new JButton("C");
      int widthPos=(int)(Config.screenWidth*0.19);
      if (mode==Mode.EXTENDED) {
        widthPos=(int)(Config.screenWidth*0.115);
      }
      
      clearButton.setBounds(widthPos,Config.screenHeight-200,170,50);  
      clearButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText("");
            steps.setText("               STEPS" + "\n" + "_____________________  \n");
          }
      });

      JButton ansButton=new JButton("Ans");  
      ansButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-200,170,50);  
      ansButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            
          }
      });

      // Defining ., (-), and pi buttons

      JButton dotButton=new JButton(".");
      dotButton.setFont(Config.font3);
      dotButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-260,80,50);  
      dotButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){ 
          writeToBox(".");
        } 
      });

      JButton negButton=new JButton("(-)");  
      negButton.setBounds((int)(Config.screenWidth*0.19),Config.screenHeight-260,80,50);  
      negButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("-");
          }
      });

      JButton zeroButton=new JButton("0");  
      zeroButton.setBounds((int)(Config.screenWidth*0.115),Config.screenHeight-260,80,50);  
      zeroButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("0");
          }
      });

      JButton piButton=new JButton("π");  
      piButton.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-260,80,50);  
      piButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("π");
          }
      });

      JButton yEqualsButton=new JButton("y=");  
      yEqualsButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-320,80,50);;  
      yEqualsButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("y=");
          }
      });
      yEqualsButton.setVisible(false);

      // Defininf trig buttons

      JButton sinButton=new JButton("sin");  
      sinButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-260,80,50);  
      sinButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("sin(");
          }
      });
     
      JButton cosButton=new JButton("cos");  
      cosButton.setBounds((int)(Config.screenWidth*0.415),Config.screenHeight-260,80,50);  
      cosButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("cos(");
          }
      });

      JButton tanButton=new JButton("tan");  
      tanButton.setBounds((int)(Config.screenWidth*0.49),Config.screenHeight-260,80,50);  
      tanButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("tan(");
          }
      });

      statButton=new JButton("stat");  
      statButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-260,80,50);  
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

      // Defining first set of numbers

      JButton oneButton=new JButton("1");  
      oneButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-320,80,50);  
      oneButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("1");
          }
      });

      JButton twoButton=new JButton("2");  
      twoButton.setBounds((int)(Config.screenWidth*0.115),Config.screenHeight-320,80,50);  
      twoButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("2");
          }
      });

      JButton threeButton=new JButton("3");  
      threeButton.setBounds((int)(Config.screenWidth*0.19),Config.screenHeight-320,80,50);  
      threeButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("3");
          }
      });

      JButton openParanthesisButton=new JButton("(");  
      openParanthesisButton.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-320,80,50);  
      openParanthesisButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("(");
          }
      });

      JButton closedParanthesisButton=new JButton(")");  
      closedParanthesisButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-320,80,50);  
      closedParanthesisButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox(")");
          }
      });

      // Defining the first few operations

      JButton powerButton=new JButton("^");  
      powerButton.setBounds((int)(Config.screenWidth*0.415),Config.screenHeight-320,80,50);  
      powerButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("^");
          }
      });

      JButton inverseButton=new JButton("x^-1");  
      inverseButton.setBounds((int)(Config.screenWidth*0.49),Config.screenHeight-320,80,50);  
      inverseButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("^-1");
          }
      });

      extdButton=new JButton("extd");  
      extdButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-320,80,50);  
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

      // Defining more numbers

      JButton fourButton=new JButton("4");  
      fourButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-380,80,50);  
      fourButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("4");
          }
      });

      JButton fiveButton=new JButton("5");  
      fiveButton.setBounds((int)(Config.screenWidth*0.115),Config.screenHeight-380,80,50);  
      fiveButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("5");
          }
      });

      JButton sixButton=new JButton("6");  
      sixButton.setBounds((int)(Config.screenWidth*0.19),Config.screenHeight-380,80,50);  
      sixButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("6");
          }
      });

      // Defining more buttons and funcions

      JButton eToTheXButton=new JButton("e^x");  
      eToTheXButton.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-380,80,50);  
      eToTheXButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("e^");
          }
      });

      JButton absoluteValueButton=new JButton("abs(");  
      absoluteValueButton.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-380,80,50);  
      absoluteValueButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("abs(");
          }
      });
      absoluteValueButton.setVisible(false);

      JButton xSquaredButton=new JButton("x^2");  
      xSquaredButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-380,80,50);  
      xSquaredButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("^2");
          }
      });

      JButton sqRootButton=new JButton("sqrt(");  
      sqRootButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-380,80,50);  
      sqRootButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("sqrt(");
          }
      });
      sqRootButton.setVisible(false);

      // Defining remainder of operations

      JButton multButton=new JButton("*");
      multButton.setFont(Config.font4);  
      multButton.setBounds((int)(Config.screenWidth*0.415),Config.screenHeight-380,80,50);  
      multButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("*");
          }
      });

      JButton divButton=new JButton("/");
      divButton.setBounds((int)(Config.screenWidth*0.49),Config.screenHeight-380,80,50);  
      divButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("/");
          }
      });

      JButton delButton=new JButton("del");
      delButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-380,80,50);  
      delButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText().substring(0, textBox.getText().length()-1));
          }
      });

      JButton sevenButton=new JButton("7");  
      sevenButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-440,80,50);  
      sevenButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("7");
          }
      });

      JButton eightButton=new JButton("8");  
      eightButton.setBounds((int)(Config.screenWidth*0.115),Config.screenHeight-440,80,50);  
      eightButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("8");
          }
      });

      JButton nineButton=new JButton("9");  
      nineButton.setBounds((int)(Config.screenWidth*0.19),Config.screenHeight-440,80,50);  
      nineButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("9");
          }
      });

      JButton logButton=new JButton("log");  
      logButton.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-440,80,50);  
      logButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("log(");
          }
      });

      // Only for extd mode

      JButton factorialButton=new JButton("!");  
      factorialButton.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-440,80,50);  
      factorialButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("!");
          }
      });
      factorialButton.setVisible(false);

      JButton lnButton=new JButton("ln");  
      lnButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-440,80,50);  
      lnButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("ln(");
          }
      });

      JButton randomButton=new JButton("rand");  
      randomButton.setBounds((int)(Config.screenWidth*0.34),Config.screenHeight-440,80,50);  
      randomButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("rand(min, max)");
          }
      });
      randomButton.setVisible(false);

      // More operations in the extd more

      JButton plusButton=new JButton("+");
      plusButton.setFont(Config.font4);
      plusButton.setBounds((int)(Config.screenWidth*0.415),Config.screenHeight-440,80,50);  
      plusButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("+");
          }
      });

      JButton minusButton=new JButton("-");
      minusButton.setFont(Config.font4);
      minusButton.setBounds((int)(Config.screenWidth*0.49),Config.screenHeight-440,80,50);  
      minusButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("-");
          }
      });

      JButton xCubedButton=new JButton("x^3");  
      xCubedButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-440,80,50);  
      xCubedButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("^3");
          }
      });
      xCubedButton.setVisible(false);

      settingsButton=new JButton("settings");
      settingsButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-440,80,50);  
      settingsButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            if (mode==mode.SETTINGS) {
              changeMode(Mode.DEFAULT, settingsButton, "settings");
            }
            else {
              changeMode(Mode.SETTINGS, settingsButton, "back");
            }
          }
      });

      JButton xButton=new JButton("x");
      xButton.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-440,80,50);  
      xButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            writeToBox("x");
          }
      });
      xButton.setVisible(false);

      // Define change color buttuns for customization

      JButton darkModeRedButton=new JButton("Dark Mode- Red");  
      darkModeRedButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-380,240,50);  
      darkModeRedButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText() + "Color Scheme Changed! \n ............................................................. \n");
            switchDarkMode(Config.red);
          }
      });
      darkModeRedButton.setVisible(false);

      JButton darkModeGoldButton=new JButton("Dark Mode- Gold");  
      darkModeGoldButton.setBounds((int)(Config.screenWidth*0.303),Config.screenHeight-380,240,50);  
      darkModeGoldButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText() + "Color Scheme Changed! \n ............................................................. \n");
            switchDarkMode(Config.gold);
          }
      });
      darkModeGoldButton.setVisible(false);

      JButton lightModeBlueButton=new JButton("Light Mode- Blue");  
      lightModeBlueButton.setBounds((int)(Config.screenWidth*0.04),Config.screenHeight-320,240,50);  
      lightModeBlueButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText() + "  Color Scheme Changed! \n ............................................................. \n  ");
            switchLightMode(Config.lightBlue);
          }
      });
      lightModeBlueButton.setVisible(false);

      JButton lightModePurpleButton=new JButton("Light Mode- Purple");  
      lightModePurpleButton.setBounds((int)(Config.screenWidth*0.303),Config.screenHeight-320,240,50);  
      lightModePurpleButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            textBox.setText(textBox.getText() + "  Color Scheme Changed! \n ............................................................. \n  ");
            switchLightMode(Config.purple);
          }
      });
      lightModePurpleButton.setVisible(false);

      // Populating arrays with defined buttons for each mode

      buttonsDefault=new JButton[] {
        submitButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton, piButton, sinButton, cosButton, tanButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, statButton, 
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, extdButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton, settingsButton};
      
      buttonsExtd=new JButton[] {
        submitButton, clearButton, ansButton, dotButton, negButton, zeroButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton,
        fourButton, fiveButton, sixButton, absoluteValueButton, sqRootButton, multButton, divButton, extdButton, sevenButton, eightButton,
        nineButton, factorialButton, randomButton, plusButton, minusButton, delButton, xCubedButton};
      
      buttonsGraphing=new JButton[] {
        submitGraphButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton, yEqualsButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, 
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton, xButton};
      
      buttonsStat=new JButton[] {
        submitButton, graphButton, clearButton, ansButton, dotButton, negButton, zeroButton,
        oneButton, twoButton, threeButton, openParanthesisButton, closedParanthesisButton, powerButton, inverseButton, statButton,
        fourButton, fiveButton, sixButton, eToTheXButton, xSquaredButton, multButton, divButton, sevenButton, eightButton,
        nineButton, logButton, lnButton, plusButton, minusButton, delButton};
      
      buttonsSettings=new JButton[] {
        delButton, settingsButton, darkModeRedButton, clearButton, darkModeGoldButton, lightModeBlueButton, lightModePurpleButton};
      
      changedButtons=new JButton[] {
        plusButton, minusButton, multButton, divButton, powerButton, inverseButton, darkModeRedButton, ansButton, clearButton, graphButton, darkModeGoldButton, lightModeBlueButton, lightModePurpleButton
      };

      currentButtons=buttonsDefault;

      f.add(modeBox);
      for (JButton b:buttonsDefault) {
        f.add(b);
      }
      f.setLayout(null);  
      f.setVisible(true);
      switchDarkMode(Config.gold);

      // End of buttons

    }

    // Writes text to text boxes

    public static void writeToBox(String symbol) {
      textBox.setText(textBox.getText()+symbol);
    }

    // Modifies the calculator to change its mode

    public static void changeMode(Mode newMode, JButton oldButton, String newText) {
      
      for (JButton button:currentButtons) {
        button.setVisible(false);
      }

      // If the new mode is default, moved buttons have to go back to their original locations

      if (newMode==Mode.DEFAULT) {
        
        changeModeButtons(oldButton,newMode, newText, buttonsDefault);

        // current set of buttons is changed to fit the new mode

        currentButtons=buttonsDefault;
        for (JButton button:currentButtons) {
          if (button==clearButton) {
            button.setBounds((int)(Config.screenWidth*0.19),Config.screenHeight-200,170,50);
          }
          if (button==settingsButton) {
            button.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-440,80,50);
          }

          if (button==delButton) {
            button.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-380,80,50);
          }
          button.setVisible(true);
        }
      }

      else if (newMode==Mode.EXTENDED) {
        changeModeButtons(oldButton,newMode, newText, buttonsExtd);
        currentButtons=buttonsExtd;
        for (JButton button:currentButtons) {
          if (button==clearButton) {
            button.setBounds((int)(Config.screenWidth*0.265),Config.screenHeight-200,170,50);
          }
          button.setVisible(true);
        }
      }

      else if (newMode==Mode.GRAPHING) {
        for (JButton b:buttonsGraphing) {
          f.add(b);
        }
        
        changeModeButtons(graphButton, mode.GRAPHING, newText, buttonsGraphing);
        currentButtons=buttonsGraphing;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
            
      }

      else if (newMode==Mode.SETTINGS) {
        changeModeButtons(oldButton,newMode, newText, buttonsSettings);
        currentButtons=buttonsSettings;
        for (JButton button:currentButtons) {
          if (button==settingsButton) {
            button.setBounds((int)(Config.screenWidth*0.565),Config.screenHeight-320,80,50);
          }

          if (button==clearButton) {
            button.setBounds((int)(Config.screenWidth*0.303),Config.screenHeight-200,240,50);
          }

          button.setVisible(true);
        }
      }

      else if (newMode==Mode.STAT) {
        changeModeButtons(oldButton,newMode, newText, buttonsStat);
        currentButtons=buttonsStat;
        for (JButton button:currentButtons) {
          button.setVisible(true);
        }
      }

      // Calls the appropriate color-scheme function based on a string

      if (currentColorScheme.equals("dark-gold")) {
        switchDarkMode(Config.gold);
      }

      else if (currentColorScheme.equals("dark-red")) {
        switchDarkMode(Config.red);
      }

      else if (currentColorScheme.equals("light-blue")) {
        switchLightMode(Config.lightBlue);
      }

      else if (currentColorScheme.equals("light-purple")) {
        switchLightMode(Config.purple);
      }    
    }

     // Modifies the button names for a particular mode

    public static void changeModeButtons(JButton button, Mode newMode, String newText, JButton[] buttons) {
      for (JButton b:buttons) {
        f.add(b);
      }
      
      mode=newMode;
      String newModeText=(newMode.name().charAt(0) + "").toUpperCase() + (newMode.name().substring(1)).toLowerCase() + " Mode";
      button.setText(newText);
      modeBox.setText(newModeText);
      f.add(button);
    }

    // Changes the current buttons to a new color for customization
    // Dark mode has two modes- dark mode gold and dark mode red

    public static void switchDarkMode(Color color) {
      if (color==Config.gold) {
        currentColorScheme="dark-gold";
      }

      if (color==Config.red) {
        currentColorScheme="dark-red";
      }

      f.getContentPane().setBackground(Color.darkGray);
      scrollPaneMain.getViewport().getView().setBackground(Color.lightGray);
      scrollPaneMain.getViewport().getView().setForeground(Color.darkGray);

      scrollPaneSteps.getViewport().getView().setBackground(Color.lightGray);
      scrollPaneSteps.getViewport().getView().setForeground(Color.darkGray);

      modeBox.setBackground(Config.lightGrey);

      for (JButton b:currentButtons) {
        b.setBackground(Config.grey);
        b.setForeground(Config.white);
      }
      
      for (JButton b:changedButtons) {
        b.setBackground(color);
        b.setForeground(Config.white);
      }

      submitGraphButton.setBackground(Config.green);
      submitButton.setBackground(Config.green);

    }

    // Changes the current buttons to a new color for customization
    // Light mode has two modes- light mode light blue and light mode purple
    
    public static void switchLightMode(Color color) {

      if (color==Config.lightBlue) {
        currentColorScheme="light-blue";
      }

      if (color==Config.purple) {
        currentColorScheme="light-purple";
      }

      f.getContentPane().setBackground(Config.smoke);
      scrollPaneMain.getViewport().getView().setBackground(Config.aliceBlue);
      scrollPaneMain.getViewport().getView().setForeground(Color.DARK_GRAY);
      scrollPaneSteps.getViewport().getView().setBackground(Config.aliceBlue);
      scrollPaneSteps.getViewport().getView().setForeground(Color.DARK_GRAY);
      modeBox.setBackground(Config.lightGrey);

      for (JButton b:currentButtons) {
        b.setBackground(Config.steelBlue);
        b.setForeground(Config.white);
      }
      
      for (JButton b:changedButtons) {
        if (currentColorScheme.equals("light-blue")) {
          b.setBackground(Config.otherLightBlue);
        }
        else {
          b.setBackground(Config.mediumPurple);
        }
        b.setForeground(Config.white);
      }
      submitGraphButton.setBackground(color);
      submitButton.setBackground(color);
    }

    public static void main(String[] arguments) {
      
      drawScreen();
      
    }

}
