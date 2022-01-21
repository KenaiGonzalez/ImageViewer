package Persistencia;

import Model.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class FileImageLoader implements ImageLoader{
    private final File[] files;

    public FileImageLoader(File folder) {
        File[] all = folder.listFiles();
        List<File> correctFiles = new ArrayList<>();
        for (File file1 : all) {
            if(ImageType().accept(file1))correctFiles.add(file1);
        }
        files = new File[correctFiles.size()];
        correctFiles.toArray(files);
    }
    
    
    
    public FileFilter ImageType(){
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".jpg")
                        || file.getName().endsWith(".JPG");
            }
        };
    }
    
    @Override
    public Image load(){
        return imageAt(0);
    }
    
    private Image imageAt(int i){
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

            @Override
            public Image next() {
                return i == files.length-1 ? imageAt(0):imageAt(i+1);
            }

            @Override
            public Image prev() {
                return i == 0 ? imageAt(files.length-1):imageAt(i-1);
            }
        };
    }
}
