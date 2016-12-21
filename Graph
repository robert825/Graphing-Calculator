import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Graph extends JPanel
{
   JPanel panel, graphUtil, findUtil, toolPanel, graphUtilTop, findUtilTop;
   JPanel findUtilMid, findUtilBottom, inverse, tools;
   JLabel enterExpression, findValue, printY;
   JTextField expression, find;
   JButton graph, findButton, showInverse, clear;
   FlowLayout lay;
   BoxLayout graphLay, intLay;
   
   MathEval genius = new MathEval();
 
   int sizeX=20, sizeY=20;
   int maxX = sizeX/2;
   int minX = maxX*-1;
   int maxY = sizeY/2;
   int minY = maxY*-1;
   int pointX, pointY = 0;
   String modify;
   boolean showPoint, showInv = false;
   int[] xVals = new int[(sizeX*2)+1];
   int[] yVals = new int[(sizeY*2)+1];
   
   public void paintComponent(Graphics g)
   {  
      //plot coordinate plane
      g.setColor(new Color(234,234,234));
      g.fillRect(0,0,600,700);
      g.setColor(Color.BLACK);
      for(int a=50; a<=450; a+=20)
         g.fillOval(297,a-3,6,6); 
      for(int b=100; b<=500; b+=20)
         g.fillOval(b-3,247,6,6);
      g.drawLine(300,50,300,450);
      g.drawLine(100,250,500,250);
      g.drawString(""+minX, 75, 255);
      g.drawString(""+maxX, 505, 255);
      g.drawString(""+minY, 290, 465);
      g.drawString(""+maxY, 292, 45);
   
      //plots points drawn from array containing X and Y values
      g.setColor(Color.RED);
      for(int a=0; a<41; a+=2)
      {
         g.fillOval(xVals[a]-5,yVals[a]-5,10,10);
      }
      for(int b=0; b<40; b++)
      {
         g.drawLine(xVals[b],yVals[b],xVals[b+1],yVals[b+1]);
      }
      
      if(showInv)
      {
         g.setColor(Color.BLUE);
         for(int a=0; a<41; a+=2)
         {
            g.fillOval(600-(yVals[a]+55),600-(xVals[a]+55),10,10);
         }
         for(int b=0; b<40; b++)
         {
            g.drawLine(600-(yVals[b]+50), 600-(xVals[b]+50), 600-(yVals[b+1]+50), 600-(xVals[b+1]+50));
         }
      }
      
      if(showPoint)
      {
         g.setColor(Color.MAGENTA);
         for(int a=6; a<=8; a++)
            g.drawRect(pointX-a, pointY-a, 2*a, 2*a);
         g.fillOval(pointX-2, pointY-2, 4, 4);
      }
      g.setColor(new Color(234,234,234));
      g.fillRect(0,0,5,5);
      g.fillRect(545,545,10,10);
   } 

   public Graph() 
   { 
      setLayout(new BorderLayout()); 
   
      panel = new JPanel(); 
      panel.setLayout(lay = new FlowLayout());
      panel.setBackground(Color.GRAY);
      add(panel, BorderLayout.SOUTH); 
      
      toolPanel = new JPanel();
      toolPanel.setLayout(intLay = new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
      panel.add(toolPanel, FlowLayout.LEFT);
      
      tools = new JPanel();
      toolPanel.add(tools);
      
      inverse = new JPanel();
      toolPanel.add(inverse); 
        
      graphUtil = new JPanel();
      graphUtil.setLayout(graphLay = new BoxLayout(graphUtil, BoxLayout.Y_AXIS));
      panel.add(graphUtil, FlowLayout.CENTER);
      
      graphUtilTop = new JPanel();
      graphUtil.add(graphUtilTop);
      
      findUtil = new JPanel();
      findUtil.setLayout(graphLay = new BoxLayout(findUtil, BoxLayout.Y_AXIS));
      panel.add(findUtil, FlowLayout.RIGHT);
      
      findUtilTop = new JPanel();
      findUtil.add(findUtilTop);
      
      findUtilMid = new JPanel();
      findUtil.add(findUtilMid);
      
      findUtilBottom = new JPanel();
      findUtil.add(findUtilBottom);
     
      clear = new JButton("CLEAR GRAPH");
      clear.addActionListener(new ClearListener());
      tools.add(clear);
      
      showInverse = new JButton("SHOW INVERSE");
      showInverse.addActionListener(new InverseListener());
      inverse.add(showInverse);
           
      enterExpression = new JLabel("y=");
      graphUtilTop.add(enterExpression);
      
      expression = new JTextField("Enter equation here", 10);
      graphUtilTop.add(expression);
               
      graph = new JButton("GRAPH");
      graph.addActionListener(new GraphListener());
      graphUtil.add(graph);
        
      findValue = new JLabel("Find y when x = ");
      findUtilTop.add(findValue);
      
      find = new JTextField("",3);
      findUtilTop.add(find);
      
      findButton = new JButton("FIND");
      findButton.addActionListener(new FindListener());
      findUtilMid.add(findButton);
      
      printY = new JLabel("      ");
      findUtilBottom.add(printY);
   } 
    
   public class GraphListener implements ActionListener 
   { 
      public void actionPerformed(ActionEvent e) 
      { 
         showPoint= false;
         showInv = false;
         printY.setText("      ");
         modify = expression.getText();
         int indexOfX=0;
         for(int a=0; a<modify.length(); a++)
         {
            if(modify.charAt(a)=='x')
            {
               indexOfX=a;
               break;
            }
         }
         if(indexOfX!=0)
         {
            if(modify.charAt(indexOfX-1)!=' ' && modify.charAt(indexOfX-1)!='(' && modify.charAt(indexOfX-1)!='/')
            {
               if(modify.charAt(indexOfX-1)!='*' && modify.charAt(indexOfX-1)!='-' && modify.charAt(indexOfX-1)!='^')
               {
                  String beforeX = modify.substring(0,indexOfX)+"*";
                  String afterX = modify.substring(indexOfX);
                  modify=beforeX+afterX;
               }
            }
         }
         
         double result, step = 0;
         step = 0.5;
         int translatedX, translatedY = 0;
         
         for(double a=(double)minX; a<=(double)maxX; a+=step)
         {
            genius.setVariable("x",a);
            result = genius.evaluate(modify);
            translatedX = (int)((a*sizeX)+300);
            translatedY = (int)(250-(result*sizeX));
            xVals[(int)((a+maxX)*2)]=translatedX;
            yVals[(int)((a+maxX)*2)]=translatedY;
         } 
         repaint(); 
      }        
   } 
   public class FindListener implements ActionListener 
   { 
      public void actionPerformed(ActionEvent e) 
      { 
         boolean noPi = true;
         boolean noE = true;
         double xValue = 0;
         String text = find.getText();
         for(int a=0; a<text.length()-1; a++)
         {
            if(text.substring(a,a+2).equals("PI"))
            {
               xValue = Math.PI;
               noPi = false;
               break;
            }
            else
               noPi = true;
         }
         for(int a=0; a<text.length(); a++)
         {
            if(text.substring(a,a+1).equals("E"))
            {
               xValue = Math.E;
               noE = false;
               break;
            }
            else
               noE = true;
         }
      
         if(noPi && noE)
            xValue = Double.parseDouble(text);
         genius.setVariable("x",xValue);
         double result = genius.evaluate(modify);
         result = Math.round(result*100000);
         result = result/100000;
         printY.setText("y= "+result);
         
         showPoint = true;
         pointX = (int)((xValue*sizeX)+300);
         pointY = (int)(250-(result*sizeX));
         repaint();
      }              
   }
   public class InverseListener implements ActionListener 
   { 
      public void actionPerformed(ActionEvent e) 
      { 
         showInv = !showInv;
         repaint();
      }
   }
   public class ClearListener implements ActionListener 
   { 
      public void actionPerformed(ActionEvent e) 
      { 
         expression.setText("");
         find.setText("");
         printY.setText("      ");
         for(int a=0; a<41; a++)
         {
            xVals[a]=0;
            yVals[a]=0;
         }
         showInv=false;
         showPoint=false;
         repaint();
      }
   }
}
