import greenfoot.*;

/**
 * @author Jonathan Lim
 * 
 * Textbox class - class to simulate a box that can be edited
 */
public class Textbox extends Actor
{
    private static final int MAX_INPUT_WIDTH = 20;
    private static Textbox focusOn;
    private boolean enter;
    private String text = "Enter name";
    
    /**
     * Textbox constructor - sets image of the Textbox
     */
    public Textbox()
    {
      updateImage();
    }
    
    /**
     * Textbox constructor - updates text based on the number of the player and
     * sets image of the Textbox
     * @param num - nuumber of player
     */
    public Textbox(int num){
        if(num == 1){
            text = "Enter Player 1 name";
            updateImage();
        }
        else{
            text = "Enter Player 2 name";
            updateImage();
        }
    }
    
    /**
     * Method to update image of Textbox
     */
    private void updateImage()
    {
        GreenfootImage textImg=new GreenfootImage(" "+text+" ", 24, Color.BLACK, new Color(0, 0, 0, 0));
        GreenfootImage image=new GreenfootImage(textImg.getWidth()+8, textImg.getHeight()+8);
        image.setColor(Color.BLACK);
        image.drawImage(textImg, (image.getWidth()-textImg.getWidth())/2, (image.getHeight()-textImg.getHeight())/2);
        setImage(image);
    }
  
    /**
     * Method to allow user to edit the text given
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            text = "";
            updateImage();
            while(Greenfoot.getKey() != null);
            focusOn = this;
        }
        if (focusOn == this)
        {
            if (Greenfoot.mouseClicked(null) && !Greenfoot.mouseClicked(this))
            {
                enter = true;
                focusOn = null;
                return;
            }
            String key = Greenfoot.getKey();
            if (key == null) return;
            if ("enter".equals(key) && text.length() > 0){
                enter = true;
                return;
            }
            if ("backspace".equals(key) && text.length() > 0) text = text.substring(0, text.length() - 1);
            if ("escape".equals(key)) text = "";
            if ("space".equals(key)) key = " ";
            if (key.length() == 1 && text.length() < MAX_INPUT_WIDTH) text += key;
            updateImage();
        }
    }
    
    /**
     * Method to get text of image
     * @return text of image
     */
    public String getValue()
    {
        return text;
    }
    
    /**
     * Method to check if text written is final
     * @return true if text is final, else false
     */
    public boolean enter(){
        return enter;
    }
    
    /**
     * Method to set if text written is final
     * @param e - enter value
     */
    public void setEnter(boolean e){
        enter = e;
    }
}