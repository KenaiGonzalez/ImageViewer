package UI;

import Model.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay{
    private Image currentImage;
   
    @Override
    public void paint(Graphics g){
        if(currentImage == null)return;
        g.drawImage(imageOf(currentImage).getScaledInstance(800, 400, java.awt.Image.SCALE_DEFAULT), 0, 0, this);
    }
    private BufferedImage imageOf(Image image){
        try{
            return ImageIO.read(image.stream());
        }catch(IOException e){
            return null;
        }
    }

    @Override
    public Image current() {
        return currentImage;
    }

    @Override
    public void show(Image image) {
        this.currentImage = image;
        this.repaint();
    }

}
