package sedifusov2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author carlos
 */
public class Plano extends JPanel{

    int c1=0;
    int c2=0;
    int c3=0;
    int c4=0;
    private ArrayList<Integer[]> figuras;   
    private ArrayList<String> terminos;
    public Plano() {
        //super();        
        figuras = new ArrayList<>();
        terminos= new ArrayList<>();
        this.setBackground(Color.WHITE);           
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.                
        draw(g);
    }
    
    public void addFigura(String termino,Integer[] puntos){
        figuras.add(puntos);
        terminos.add(termino);
    }
    
    private void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);  
        setEjes(g2d);
        paintCuadricula(g2d);
        setRango(g2d);   
        
        for (int i = 0; i < figuras.size(); i++) { 
            Integer[] f = figuras.get(i);
            if(f.length==3){
                dibujarTriangulo(terminos.get(i),g2d, f[0], f[1], f[2]);
            }else if(f.length==4){
                dibujarTrapecio(terminos.get(i),g2d, f[0], f[1], f[2], f[3]);
            }
        }
        
        drawCorte(g2d);
        
    }
    
    private void drawCorte(Graphics2D g2d){
        g2d.setColor(new Color((int)(Math.random()*254)));
        g2d.drawLine(100, 260-c1, 40*4+100, 260-c1);//NA
        g2d.drawLine(35*4+100, 260-c2, 600, 260-c2);//CA
        g2d.drawLine(65*4+100, 260-c3, 600, 260-c3);//A
        g2d.drawLine(70*4+100, 260-c4, 600, 260-c4);//AE
    }
    
    private void setEjes(Graphics2D g2d){
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.drawLine(100, 260, 500, 260);//eje x        
        g2d.drawLine(100, 60, 100, 260);//Eje y
        
        //100 en x + 360 y = x0                        
    }
    
    private void setRango(Graphics2D g2d){        
        for (int i = 0; i <= 20; i++) {            
            g2d.drawString(""+(i*5), 100+(i*20)-4, 280);
        }
        
        for (int i = 0; i <= 10; i++) {            
            g2d.drawString(""+(i/10.0f), 75, 260-(i*20));
        }
        
    }
    
    private void paintCuadricula(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(0.2f));
                
        for (int i = 0; i < 15; i++) {                 
            g2d.drawLine(0, i*20, 600, i*20);//Horizaontales            
        }
        
        for (int i = 0; i < 30; i++) {               
            g2d.drawLine(i*20, 0, i*20, 280); //Verticales           
        }        
    }   
    
    public void dibujarTriangulo(String termino,Graphics2D g2d,int a,int b,int c){
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.5f));
        int x3[] = { a*4+100, b*4+100, c*4+100};
        int y3[] = { 260, 60,260};
        g2d.drawPolygon (x3, y3, 3);
        g2d.drawString(""+termino, b*4+80, 58);
        
    }
    
    public void dibujarTrapecio(String termino,Graphics2D g2d,int a,int b,int c,int d){
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.5f));
        int x3[] = { a*4+100, b*4+100, c*4+100,d*4+100};
        int y3[] = { 260, 60,60,260};
        g2d.drawPolygon (x3, y3, 4);
        g2d.drawString(""+termino, b*4+100, 58);
    }
}
