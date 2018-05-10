/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;
public class Title extends Actor
{
    private String buttonText = "";
    private int size;
 
    public Title(String text, int s)
    {
        size = s;
        setText(text);
    }
 
    public void setText(String text)
    {
        buttonText=text;
        GreenfootImage textImg=new GreenfootImage(" "+text+" ", size, Color.BLACK, new Color(0, 0, 0, 0));
        GreenfootImage image=new GreenfootImage(textImg.getWidth()+8, textImg.getHeight()+8);
        image.setColor(Color.BLACK);
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/2);
        setImage(image);
    }
 
    public void act()
    {

    }
    public String getText()
    {
        return buttonText;
    }
}