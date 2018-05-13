import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * 
 * 
 * @author Jonathan Lim 
 * Period 3
 * 
 * Button class - class to simulate button
 */
public class Button extends Actor
{
    String buttonText = "";
    boolean clicked = false;
 
    /**
     * Button constructor - initializes the text and set the image to the text
     * @param text - text of image
     */
    public Button(String text)
    {
        setText(text);
    }
 
    /**
     * Method to set picture of object to text
     * @param text - text of image
     */
    public void setText(String text)
    {
        buttonText=text;
        GreenfootImage textImg=new GreenfootImage(" "+text+" ", 24, Color.BLACK, new Color(0, 0, 0, 0));
        GreenfootImage image=new GreenfootImage(textImg.getWidth()+8, textImg.getHeight()+8);
        image.setColor(Color.BLACK);
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/2);
        setImage(image);
    }
 
    /**
     * Method to check if button has been pressed
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){
            clicked = true;
        }
    }
    
    /**
     * Method that returns text of button
     * @ return text of button
     */
    public String getText()
    {
        return buttonText;
    }
    
    /**
     * Method that returns if button has been clicked
     * @return true if button is clicked, else return false
     */
    public boolean clicked(){
        return clicked;
    }
    
    /**
     * Method to set whether button has been clicked
     * @param click - clicked state of button
     */
    public void setClicked(boolean click){
        clicked = click;
    }
}
