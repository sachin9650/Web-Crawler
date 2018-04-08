package mywebcrawler;

class BoundsResolution 
{
    private int x;
    private int y;
    private int width;
    private int height;
    private static final BoundsResolution buttonResolution=new BoundsResolution();
    
    private BoundsResolution()
    {
    }
    
    public static BoundsResolution getBRInstance(int x, int y, int width, int height)
    {
        buttonResolution.setX(x);
        buttonResolution.setY(y);
        buttonResolution.setWidth(width);
        buttonResolution.setHeight(height);
        
        return buttonResolution;
    }
    
    public int getX()
    {
        return x;
    }
    
    private void setX(int x)
    {
        this.x=x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y=y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setWidth(int width)
    {
        this.width=width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setHeight(int height)
    {
        this.height=height;
    }
}
