package Persistencia;

import Model.Image;

public interface ImageLoader {
    public Image load();
    public Image next();
    public Image prev();
}
