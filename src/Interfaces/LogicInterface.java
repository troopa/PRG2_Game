package Interfaces;

import GameObjects.Line;
import GameObjects.Map;
import GameObjects.Point;

/**
 *
 * @author Andre
 */
public interface LogicInterface {
    void initializeGame(int height, int width, OpponentInterface opponent);
    void setLine(Point startPoint, Point endPoint, boolean isOpponent);

    public void setLine(Line selectedLine, boolean isOpponent);
    public boolean isValidLine(Line selectedLine);

    public int getPlayerState();

    public int getOpponentState();

    public Map getMap();
}
