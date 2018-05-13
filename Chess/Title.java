/**
 * .
 * 
 * @author Jonathan Lim 
 * 
 * Title class - class to display text
 */
import greenfoot.*;
public class Title extends Actor
{
    private String buttonText = "";
    private int size;
    GreenfootImage image = new GreenfootImage(1, 1);
    private Color color = Color.BLACK;

    /**
     * Title constructor - initializes the text and the size and sets the image
     * to the text of the certain size
     * @param text - text of image
     * @param s - size of image
     */
    public Title(String text, int s){
        size = s;
        setText(text);
    }
 
    /**
     * Method to set image of object to the text
     * @param text of image
     */
    public void setText(String text)
    {
        buttonText=text;
        GreenfootImage textImg=new GreenfootImage(" "+text+" ", size, color, new Color(0, 0, 0, 0));
        image=new GreenfootImage(textImg.getWidth()+8, textImg.getHeight()+8);
        image.setColor(color);
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/2);
        setImage(image);
    }
 
    /**
     * Method to act
     */
    public void act()
    {

    }
    
    /**
     * Method to get text of Title
     * @return text of title
     */
    public String getText()
    {
        return buttonText;
    }
    
    /**
     * Method to return image of title
     * @return image of title
     */
    public GreenfootImage getImage(){
        return image;
    }
    
    /**
     * Method to set transparency of title
     * @param i - transparency level of title
     */
    public void setTransparency(int i){
        image.setTransparency(i);
        setImage(image);
    }
}