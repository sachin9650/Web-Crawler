package mywebcrawler;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MyWebCrawler 
{
    private static Dimension dim;
    MyWebCrawler()
    {
        super();
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) 
    {
        setAesthetics();
        MainWindow mainWindow=new MainWindow();
        mainWindow.createWindow();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(MainWindow.f);
    }
    
    static void setLocation(JFrame frame)
    {
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
    
    private static void setAesthetics()
    {
        try 
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (UnsupportedLookAndFeelException e) 
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }        
    }
}
