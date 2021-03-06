package Interfaces;

import GameObjects.Line;
import GameObjects.Square;
/**
 *
 * @author Daniel
 */
public interface ObserverInterface{
    /**
     * TODO: evtl. umbenennen - View und Opponent benützen diese Methode um eine Linie zusetzen.
     * @param selectedLine
     * @param isOpponent 
     */
    void makeMove(Line selectedLine, boolean isOpponent);
    
    /**
     * TODO: evtl. umbennen - Controller informieren, dass die Einstellungen übernommen werden können
     */
    void submitOptions();
    
    void openGame(String path);

    void saveGame(String saveFileDirectory);
    
    void setPlayerTurn(boolean isOpponent);

    void gameEnds();
    
    void setOwnedSquare(int x1, int y1, boolean isOpponent);
}
