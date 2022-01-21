package imageviewer;

import Controlador.MainFrame;
import Model.Image;
import Persistencia.FileImageLoader;
import java.io.File;

public class ImageViewer {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\kenai\\Desktop\\Imagenes_ImageViewer");
        FileImageLoader imageLoader = new FileImageLoader(file);
        Image image = imageLoader.load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.getImageDisplay().show(image);
                
    }
    
}
