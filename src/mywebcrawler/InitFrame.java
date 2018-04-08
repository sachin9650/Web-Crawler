
package mywebcrawler;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Sachin
 */
public class InitFrame 
{
    private boolean visibility;
    private Dimension minSize;
    private String title;
    private boolean resizable;
    private int closeOperation;
    
    private InitFrame()
    {
    }
    
    private void setVisibility(boolean visibility)
    {
        this.visibility=visibility;
    }
    
    public boolean getVisibility()
    {
        return visibility;
    }
    
    private void setMinSize(Dimension minSize)
    {
        this.minSize=minSize;
    }
    
    public Dimension getMinSize()
    {
        return minSize;
    }
    
    private void setTitle(String title)
    {
        this.title=title;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    private void setResizable(boolean resizable)
    {
        this.resizable=resizable;
    }
    
    public boolean getResizable()
    {
        return resizable;
    }
    
    private void setCloseOperation(int closeOperation)
    {
        this.closeOperation=closeOperation;
    }
    
    public static void initialiseFrame(JFrame frame, boolean visibility, Dimension minSize, String title, boolean resizable, int closeOperation)
    {
        frame.setVisible(visibility);
        frame.setMinimumSize(minSize);
        frame.setTitle(title);
        frame.setResizable(resizable);
        frame.setDefaultCloseOperation(closeOperation);
    }
}
