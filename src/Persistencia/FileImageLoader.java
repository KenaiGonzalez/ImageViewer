package Persistencia;

import Model.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public final class FileImageLoader implements ImageLoader{
    private static File[] files;
    private static int currency;

    public FileImageLoader(File folder) {
        File[] all = folder.listFiles();
        List<File> correctFiles = new ArrayList<>();
        for (File file1 : all) {
            if(ImageType().accept(file1))correctFiles.add(file1);
        }
        if(correctFiles.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Este directorio no contiene archivos con extensión .jpg",
                    "ERROR",JOptionPane.ERROR_MESSAGE);
            exit(1);
        }
        files = new File[correctFiles.size()];
        correctFiles.toArray(files);
    }
    
    
    
    public FileFilter ImageType(){
        return (File file) -> file.getName().endsWith(".jpg")
                || file.getName().endsWith(".JPG");
    }
    
    @Override
    public Image load(){
        return imageAt(0);
    }
    
    @Override
    public Image next() {
        return currency == files.length-1 ? imageAt(0):imageAt(currency+1);
    }

    @Override
    public Image prev() {
        return currency == 0 ? imageAt(files.length-1):imageAt(currency-1);
    }
    
    private Image imageAt(int i){
        currency = i;
        return new Image() {
            @Override
            public String name() {
                return files[i].getName();
            }

            @Override
            public InputStream stream() {
                try {
                    return new BufferedInputStream(new FileInputStream(files[i]));
                } catch (FileNotFoundException ex) {
                    return null;
                }
            }

            
        };
    }
}
