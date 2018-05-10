import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class SinglePlayerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    String buttonText = "Single Player";
    boolean clicked = false;
 
    public Button(int playerCount)
    {
        if(playerCount == 1){
            setText("Single Player");
        }
        else if (playerCount == 2){
            setText("Two Player");
        }
        else{
            setText("How to Play");
        }
    }
 
    public void setText(String text)
    {
        buttonText=text;
        GreenfootImage textImg=new GreenfootImage(" "+text+" ", 24, Color.BLACK, new Color(0, 0, 0, 0));
        GreenfootImage image=new GreenfootImage(textImg.getWidth()+8, textImg.getHeight()+8);
        image.setColor(Color.BLACK);
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/2);
        setImage(image);
    }
 
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            clicked = true;
        }
    }
    public String getText()
    {
        return buttonText;
    }
    public boolean clicked(){
        return clicked;
    }
    
    public void setClicked(boolean click){
        clicked = click;
    }
}
