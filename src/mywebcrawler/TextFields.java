package mywebcrawler;

import java.awt.Font;
import javax.swing.JTextField;

public class TextFields 
{
    private BoundsResolution boundsResolution;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private static JTextField jTextField;
    
    public static JTextField createTextField(BoundsResolution boundsResolution, String fontName, int fontStyle, int fontSize, int width, int height)
    {
        jTextField = new JTextField();
        jTextField.setBounds(boundsResolution.getX(), boundsResolution.getY(), boundsResolution.getWidth(), boundsResolution.getHeight());
        jTextField.setFont(new Font(fontName, fontStyle, fontSize));
        jTextField.setSize(width, height);
        
        return jTextField;
    }
}
