
package mx.venado.pvr.utilidades;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class BuildIcon implements Icon {

    private URL url = null;
    private int height = 53;
    private int width = 53;
    
    public BuildIcon(URL url) {
        super();
        this.url = url;
    }
    
    public BuildIcon(URL url, int width, int height) {
        super();
        this.url = url;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        ImageIcon icon = new ImageIcon(this.url);
        ImageIcon fin = new ImageIcon(icon.getImage().getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT));
        Image image = fin.getImage();
        g.drawImage(image, x, y, c);
    }

    @Override
    public int getIconWidth() {
        return this.width;
    }

    @Override
    public int getIconHeight() {
        return this.height;
    }
    
}
