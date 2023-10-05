import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;


public class GUICalculator {

    private static final int screenWidth=1200;
    private static final int screenHeight=800;
    private final static String newline = "\n";

    public static void drawScreen() {

      Font font1 = new Font("SansSerif", Font.BOLD, 70);
      Font font2 = new Font("SansSerif", Font.ITALIC, 20);
      Font font3 = new Font("SansSerif", Font.PLAIN, 30);

      JFrame f=new JFrame("Calculator");  
      JTextField textBox=new JTextField();  

      textBox.setFont(font1);
      textBox.setBounds(50,50, (int)(screenWidth*0.6),(int)(screenHeight*0.25));
      
      
      JTextField steps=new JTextField();  

      steps.setFont(font2);
      steps.setBounds((int)(screenWidth*0.7),50, (int)(screenWidth*0.2),(int)(screenHeight*0.8));

      // BUTTONS

      // Bottom row (row 1)

      JButton submitButton=new JButton("=");  
      submitButton.setBounds((int)(screenWidth*0.49),screenHeight-200,170,50);  
      submitButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("Submit button was pressed");
            textBox.setText(textBox.getText()+"+1");
            System.out.println(textBox.getText());
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
      dotButton.setBounds((int)(screenWidth*0.04),screenHeight-260,120,50);  
      dotButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println(". button was pressed");
            textBox.setText(textBox.getText()+".");
          }
      });

      JButton negButton=new JButton("(-)");  
      negButton.setBounds((int)(screenWidth*0.15),screenHeight-260,120,50);  
      negButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("Negative button was pressed");
            textBox.setText(textBox.getText()+"-");
          }
      });

      JButton zeroButton=new JButton("0");  
      zeroButton.setBounds((int)(screenWidth*0.26),screenHeight-260,120,50);  
      zeroButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("0 button was pressed");
            textBox.setText(textBox.getText()+"0");
          }
      });





      JButton oneButton=new JButton("1");  
      oneButton.setBounds((int)(screenWidth*0.04),screenHeight-320,120,50);  
      oneButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("1 button was pressed");
            textBox.setText(textBox.getText()+"1");
          }
      });

      JButton twoButton=new JButton("2");  
      twoButton.setBounds((int)(screenWidth*0.15),screenHeight-320,120,50);  
      twoButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("2 button was pressed");
            textBox.setText(textBox.getText()+"2");
          }
      });

      JButton threeButton=new JButton("3");  
      threeButton.setBounds((int)(screenWidth*0.26),screenHeight-320,120,50);  
      threeButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("3 button was pressed");
            textBox.setText(textBox.getText()+"3");
          }
      });

      JButton fourButton=new JButton("4");  
      fourButton.setBounds((int)(screenWidth*0.04),screenHeight-380,120,50);  
      fourButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("4 button was pressed");
            textBox.setText(textBox.getText()+"4");
          }
      });

      JButton fiveButton=new JButton("5");  
      fiveButton.setBounds((int)(screenWidth*0.15),screenHeight-380,120,50);  
      fiveButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("5 button was pressed");
            textBox.setText(textBox.getText()+"5");
          }
      });

      JButton sixButton=new JButton("6");  
      sixButton.setBounds((int)(screenWidth*0.26),screenHeight-380,120,50);  
      sixButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("6 button was pressed");
            textBox.setText(textBox.getText()+"6");
          }
      });

      JButton sevenButton=new JButton("7");  
      sevenButton.setBounds((int)(screenWidth*0.04),screenHeight-440,120,50);  
      sevenButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("7 button was pressed");
            textBox.setText(textBox.getText()+"7");
          }
      });

      JButton eightButton=new JButton("8");  
      eightButton.setBounds((int)(screenWidth*0.15),screenHeight-440,120,50);  
      eightButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("8 button was pressed");
            textBox.setText(textBox.getText()+"8");
          }
      });

      JButton nineButton=new JButton("9");  
      nineButton.setBounds((int)(screenWidth*0.26),screenHeight-440,120,50);  
      nineButton.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){
            System.out.println("9 button was pressed");
            textBox.setText(textBox.getText()+"9");
          }
      });















      f.add(submitButton);
      f.add(graphButton);
      f.add(clearButton);
      f.add(ansButton);

      f.add(dotButton);
      f.add(negButton);
      f.add(zeroButton);

      f.add(oneButton);
      f.add(twoButton);
      f.add(threeButton);
      f.add(fourButton);
      f.add(fiveButton);
      f.add(sixButton);
      f.add(sevenButton);
      f.add(eightButton);
      f.add(nineButton);

      f.add(textBox);
      f.add(steps);
      f.setSize(screenWidth,screenHeight);  
      f.setLayout(null);  
      f.setVisible(true);
    
    }

  

    public static void main(String[] arguments) {
      drawScreen();
    
 
  }


}
