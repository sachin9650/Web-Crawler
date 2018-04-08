
package mywebcrawler;

import java.awt.Font;
import javax.swing.JLabel;

public class Labels 
{
    private String title;
    private BoundsResolution boundsResolution;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private static JLabel jLabel;
    Labels()
    {
        
    }
    
    public static JLabel createLabel(String title, BoundsResolution boundsResolution, String fontName, int fontStyle, int fontSize)
    {
        jLabel=new JLabel(title);
        jLabel.setBounds(boundsResolution.getX(), boundsResolution.getY(), boundsResolution.getWidth(), boundsResolution.getHeight());
        jLabel.setFont(new Font(fontName, fontStyle, fontSize));
        return jLabel;
    }
    
}
