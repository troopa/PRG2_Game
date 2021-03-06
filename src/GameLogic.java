
import GameObjects.Figur;
import GameObjects.Line;
import GameObjects.Map;
import GameObjects.Point;
import GameObjects.SaveGame;
import GameObjects.Square;
import Interfaces.LogicInterface;
import Interfaces.ObserverInterface;
import Interfaces.OpponentInterface;
import Interfaces.SubjectInterface;
import java.util.ArrayList;

/**
 *
 * @author Andre
 */
public class GameLogic implements LogicInterface, SubjectInterface {
    
    private Map map;
    private OpponentInterface opponent;
    private Figur localFigur;
    private Figur opponentFigur;
    private Figur nextFigur;
    private boolean tempBoolAddedPoints;
    private ObserverInterface observer;
    
    //@Override
    /*public void initializeGame(int height, int width, OpponentInterface opponent) {
       this.map = new Map(height, width);
       this.opponent = opponent;
       
       localFigur = new Figur(false);
       opponentFigur = new Figur(true);
    }*/
    
    public void initializeGame(Map map, OpponentInterface opponent){
        this.map = map;
        this.opponent = opponent;

        localFigur = new Figur(false);
        nextFigur = localFigur;
        
        opponentFigur = new Figur(true);
    }

    @Override
    public void setLine(Point startPoint, Point endPoint, boolean isOpponent) {
        //Todo
        tempBoolAddedPoints = false;
        
        if(!isOpponent)
        {
            opponent.sendLine(new Line(startPoint, endPoint));
        }
        
        Line line = this.map.getLine(startPoint, endPoint);
        
        if(line.getOwner() == null){
            // hier owner setzen
            this.setOwner(line, isOpponent);
            
            // bestimmem, wer am Zug ist!!
            // meine Squares suchen // höchstens 2 und prüfen, ob ich die letzte linie war
            // nun die Punkte setzten, falls es so war, und nun persistieren, dass ich nochmals am zug bin!!
            ArrayList<Square> squares =  map.getSquaresBy(line);
            
            squares.forEach(s ->{
                if(s.isTaken() && !s.isOwned())
                {
                    setOwner(s, isOpponent);
                    observer.setOwnedSquare(s.getTopLine().getStartPoint().getX(), s.getTopLine().getStartPoint().getY(), isOpponent);
                    tempBoolAddedPoints = true;
                }
            });
            
             if(map.mapIsFull()){
                    observer.gameEnds();
                    return;
             }
             
            if(tempBoolAddedPoints)
            {
                //nochmals einen Zug, juhee 
                if(nextFigur == opponentFigur)
                {
                    opponent.setOpponentTurn();
                }
            }
            else
            {
                if(isOpponent)
                {
                    nextFigur = localFigur;
                    this.observer.setPlayerTurn(false);
                }
                else
                {
                    nextFigur = opponentFigur;
                    opponent.setOpponentTurn();
                    this.observer.setPlayerTurn(true);
                }
            }
                
        }
    }
    
    private void setOwner(Square square, boolean isOpponent)
    {
        if(isOpponent)
                    {
                        square.setOwner(opponentFigur);
                        opponentFigur.incresePoints(1);
                    }
                    else
                    {
                        square.setOwner(localFigur);
                        localFigur.incresePoints(1);
                    }
        
    }
    private void setOwner(Line line, boolean isOpponent){
        if(isOpponent)
        {
            line.setOwner(opponentFigur);
        }
        else
        {
           line.setOwner(localFigur);
        }
        
    }

    @Override
    public void setLine(Line selectedLine, boolean isOpponent) {
        this.setLine(selectedLine.getStartPoint(),selectedLine.getEndPoint(), isOpponent);
    }

    @Override
    public boolean isValidLine(Line selectedLine,boolean isOpponent) {
        
        if(this.nextFigur == null || isOpponent != this.nextFigur.isOpponent()){
                    
           return false;            
        }
    
        Line line = map.getLine(selectedLine.getStartPoint(), selectedLine.getEndPoint());
        return line.getOwner() == null;        
    }

    @Override
    public int getPlayerState() {
        return localFigur.getPoints();
    }

    @Override
    public int getOpponentState() {
        return opponentFigur.getPoints();
    }

    @Override
    public Map getMap()
    {
        return this.map;
    }

    @Override
    public void loadGame(SaveGame saveGame) {
        this.map = saveGame.getMap();
        this.localFigur = saveGame.getPlayer();
        this.opponentFigur = saveGame.getOpponent();
        
        if(saveGame.IsOpponentContining())
        {
            this.nextFigur = opponentFigur;
            opponent.setOpponentTurn();
        }
        else{
            this.nextFigur = localFigur;
        }
    }

    @Override
    public Figur getLocalFigur() {
        return localFigur;
    }

    @Override
    public Figur getOpponentFigure() {
        return  opponentFigur;
    }

    @Override
    public boolean IsOpponentContinuing() {
        return nextFigur.isOpponent();
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        this.observer = observer;
    }

    @Override
    public void setStartTurn(boolean isOpponent) {
        if(isOpponent)
        {
            this.nextFigur = opponentFigur;
        }
        else
        {
            this.nextFigur = localFigur;
        }
    }
}
