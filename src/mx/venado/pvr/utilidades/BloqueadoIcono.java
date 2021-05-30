
package mx.venado.pvr.utilidades;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class BloqueadoIcono implements Icon{
    @Override
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
       Image image = new UtilidadesPvr().RedimencionarImagen(ConstantesPvr.URL_MULTIMEDIA + "Bloqueado.png", 53, 53).getImage();
       g.drawImage(image, x, y, c);
   }
   @Override
   public int getIconWidth()
   {
       return 54;
   }
   @Override
   public int getIconHeight()
   {
       return 53;
   }
}
