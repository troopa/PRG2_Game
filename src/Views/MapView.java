package Views;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import GameObjects.Line;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
/**
 * Does display the Map.
 * @author Martin Etterlin
 */
public class MapView extends JPanel {
    private int angle = 0;
    private int mWidth;
    private int mHeight;
    private static int space=60;
    private ArrayList<LineView> linesOpponent;
    private ArrayList<LineView> linesPlayer;
    private ArrayList<SquareView> squaresOpponent;
    private ArrayList<SquareView> squaresPlayer;
    
    Rectangle2D.Double[][] points;
    Graphics2D graphicsOpponent;
    Graphics2D graphicsPlayer;
    Graphics2D graphicsPoints;
    Graphics2D graphicstest;
    SquareView[][] squaresview;
    
    
    /**
     * Create MapView with specified size
     * @param mWidth
     * @param mHeight 
     */
    public MapView(int mWidth, int mHeight){
        this.mWidth = mWidth;
        this.mHeight= mHeight;
        this.squaresview = new SquareView[mWidth][mHeight];
        linesOpponent = new ArrayList<>();
        linesPlayer = new ArrayList<>();
        setBackground(Color.WHITE);
        squaresOpponent = new ArrayList<>();
        squaresPlayer = new ArrayList<>();
        setup();
    }
    
    //Private method to Setup GUI Components
    private void setup(){
        setPreferredSize(new Dimension(mWidth*space+5, mHeight*space+5));
        generateField();
        initPoints();
    }
         @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Graphics Player
        graphicsPlayer = (Graphics2D) g.create();
        graphicsPlayer.setColor(Color.red);
        graphicsPlayer.setStroke(new BasicStroke(5));
        
        //Graphics Oponent
        graphicsOpponent = (Graphics2D) g.create();
        graphicsOpponent.setColor(Color.blue);
        graphicsOpponent.setStroke(new BasicStroke(5));
        
        //Graphics Point
        graphicsPoints =(Graphics2D) g.create();
        graphicsPoints.setColor(Color.BLACK);
        
        //DrawLines Player and Opponent
        for(Line2D.Double line : linesPlayer){
            graphicsPlayer.draw(line);
        }
        for(Line2D.Double line: linesOpponent){
            graphicsOpponent.draw(line);
        } 
        
        //Draw Points
        for(SquareView square: squaresOpponent){
            graphicsOpponent.fillRect((int)square.getLineTop().getX1(), (int)square.getLineTop().getY1(), space, space);
            
        }
        for(SquareView square: squaresPlayer){
            graphicsPlayer.fillRect((int)square.getLineTop().getX1(), (int)square.getLineTop().getY1(), space, space);
        }
        drawPoints();        
    }

    /**
     * Returns Line by Point selected with MousClick
     * @param point
     * @return
     */
    public LineView getLineViewByPoint(Point2D.Double point){
        LineView line = null;
        for(int i=0; i<mWidth; i++)
        {
            for(int z=0; z<mHeight; z++){
                SquareView square= squaresview[i][z];
                if(square.contains(point)){
                    line = square.getLine(point);
                }
            }
        }
        return line;
    }
    
    /**
     * Draws the Line
     * @param line line to draw
     * @param isOpponent defines which Player draws
     */
    
    public void drawLine(Line line, Boolean isOpponent){
        LineView line2d = new LineView(line.getStartPoint().getX()*space, line.getStartPoint().getY()*space, line.getEndPoint().getX()*space, line.getEndPoint().getY()*space);
        for(int i=0; i<mWidth; i++)
        {
            for(int y=0; y<mHeight; y++){
               SquareView square= squaresview[i][y];
               if(square.getLineTop().equals(line2d)){
                   if(isOpponent){
                       linesOpponent.add(square.getLineTop()); 
                   }
                   else{
                       linesPlayer.add(square.getLineTop()); 
                   }
               }
               else if(square.getLineRight().equals(line2d)){
                   if(isOpponent){
                       linesOpponent.add(square.getLineRight());
                   }
                   else{
                       linesPlayer.add(square.getLineRight());
                   }
               }
               else if(square.getLineBot().equals(line2d)){
                   if(isOpponent){
                       linesOpponent.add(square.getLineBot());
                   }
                   else{
                       linesPlayer.add(square.getLineBot());
                   }
               }
               else if(square.getLineLeft().equals(line2d)){
                   if(isOpponent){
                       linesOpponent.add(square.getLineLeft());
                   }
                   else{
                       linesPlayer.add(square.getLineLeft());
                   }
               }
            }
        }
        this.repaint();
    }
    
    //Private method to generate the Field
    private void generateField(){
        for(int i = 0; i < mWidth; i++)
        {
            for(int y = 0; y < mHeight; y++)
            {   
                Point2D.Double pTopLeft = new Point2D.Double(i*space,y*space);
                Point2D.Double pTopRight = new Point2D.Double((i+1)*space,y*space);
                Point2D.Double pBotLeft = new Point2D.Double(i*space,(y+1)*space);
                Point2D.Double pBotRight = new Point2D.Double((i+1)*space,(y+1)*space);                
                SquareView s = new SquareView();
                if(i == 0){
                    s.setLineLeft(new LineView(pTopLeft,pBotLeft));
                }
                else{
                    s.setLineLeft((squaresview[i-1][y]).getLineRight());
                }
                if(y == 0){
                    s.setLineTop(new LineView(pTopLeft,pTopRight));
                }
                else{
                    s.setLineTop(squaresview[i][y-1].getLineBot());
                }
                s.setLineBot(new LineView(pBotLeft,pBotRight));
                s.setLineRight(new LineView(pTopRight,pBotRight));
                squaresview[i][y] = s;
            }
        }
    }
    
    //Private method to create the points
    private void initPoints(){
        int ycoordinates=0;
        int xcoordinates=0;
        points= new Rectangle2D.Double[mWidth+1][mHeight+1];
        for(int i=0; i<=mWidth;i++){
            for(int y=0; y<=mHeight; y++){
                points[i][y]= new Rectangle2D.Double(xcoordinates-2, ycoordinates-2, 4, 4);
                ycoordinates= ycoordinates+space;
            }
            ycoordinates=0;
            xcoordinates=xcoordinates+space;
        } 
    }
    
    //Private method to draw Points
    private void drawPoints(){
        for(int i=0; i<=mWidth;i++){
            for(int y=0; y<=mHeight; y++){
                graphicsPoints.draw(points[i][y]);
                graphicsPoints.fill(points[i][y]);
            }
        }
        
    }
    
    /**
     * Does generate the filled square to draw if a Player took a Square
     * @param x coordinate of the leftupper point of the square
     * @param y coordinate of the leftupper point of the square
     * @param isOpponent 
     */
    
    public void drawSquare(int x , int y , boolean isOpponent){
        x=x*space;
        y=y*space;
        for(SquareView squarex[] : squaresview){
            for(SquareView square : squarex){
                int squareX=(int) square.getLineTop().getP1().getX();
                int squareY=(int) square.getLineTop().getP1().getY();
                if(x== squareX && y== squareY){
                    if(isOpponent){
                        squaresOpponent.add(square);
                    }
                    else{
                        squaresPlayer.add(square);
                    }
                }
            }
        }
        repaint();
    }
    
    /**
     * Retuns the space between two points
     * @return 
     */
    public static int getSpace() {
        return space;
    }
}
