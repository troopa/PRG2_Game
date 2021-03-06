package GameObjects;

import java.io.Serializable;

/**
 *
 * @author Andre
 */
public class Point implements Serializable
{
    private final int x;
    private final int y;
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }       
        
        if(obj == null)
        {
            return false;
        }
        
        if(obj.getClass() != this.getClass())
        {
            return false;
        }
                
        if(this.x != ((Point)obj).getX() || this.y != ((Point)obj).getY())
        {
            return false;
        }
        return true;
    }
    
}
