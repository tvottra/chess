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
    private boolean invisible;
    GreenfootImage image = new GreenfootImage(1, 1);
 
    public Title(String text, int s, boolean invis)
    {
        size = s;
        invisible = invis;
        setText(text);
    }
 
    public void setText(String text)
    {
        buttonText=text;
        GreenfootImage textImg=new GreenfootImage(" "+text+" ", size, Color.BLACK, new Color(0, 0, 0, 0));
        GreenfootImage image=new GreenfootImage(textImg.getWidth()+8, textImg.getHeight()+8);
        image.setColor(Color.BLACK);
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/2);
        if(invisible){
            image.setTransparency(100);
        }
        setImage(image);
    }
 
    public void act()
    {

    }
    public String getText()
    {
        return buttonText;
    }
    
    public void fadeInOut(){
        if(image.getTransparency() == 100){
            invisible = true;
        }
        else if(image.getTransparency() > 0 && invisible){
            image.setTransparency(image.getTransparency() - 1);
            setimage(image);
        }
        else if(image.getTransparency() == 0){
            invisible  = false;
            image.setTransparency(image.getTransparency() + 1);
            setimage(image);
        }
        else if(image.getTransparency() < 100 && !invisible){
            image.setTransparency(image.getTransparency() + 1);
            setimage(image);
        }
    }
    
    public GreenfootImage getImage(){
        return image;
    }
}