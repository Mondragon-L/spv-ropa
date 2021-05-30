

package mx.venado.pvr.utilidades;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class AdvertenciaIcono implements Icon {
   @Override
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
       Image image = new ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Advertencia.png")).getImage();
       g.drawImage(image, x, y, c);
   }
   @Override
   public int getIconWidth()
   {
       return 53;
   }
   @Override
   public int getIconHeight()
   {
       return 53;
   }
}
