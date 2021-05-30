package mx.venado.pvr.utilidades;

import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class UtilidadesPvr {

    public UtilidadesPvr() {
    }

    /**
     * Formatea el texto de acuerdo a tipo y lo muestra en el jlabel
     *
     * @param lbl JLabel donde se mostrara el mensaje
     * @param tipo Tipo del mensaje (Mensajes de
     * éxito)<code>ConstantesPvr.OK</code>, (Mensajes de
     * error)<code>ConstantesPvr.NR</code>, (Mensajes
     * normales)<code>ConstantesPvr.STD</code>, (Mensajes de advertencia)
     * <code>ConstantesPvr.ADV</code>
     * @param msj Cadena con el mensaje a mostrar
     */
    public void SetLog(JLabel lbl, String tipo, String msj) {
        lbl.setText(msj);
        switch (tipo) {
            case ConstantesPvr.OK:
                lbl.setForeground(ConstantesPvr.SUCCESS);
                break;
            case ConstantesPvr.ADV:
                lbl.setForeground(ConstantesPvr.WARNING);
                break;
            case ConstantesPvr.NR:
                lbl.setForeground(ConstantesPvr.ERROR);
                break;
            case ConstantesPvr.MSJ:
                lbl.setForeground(ConstantesPvr.DEFAULT2);
                break;
            default:
                lbl.setForeground(ConstantesPvr.DEFAULT);
        }
    }

    /**
     * Redimención de imagenes optimizado para tablas
     *
     * @param imagen Ruta de la imagen
     * @return ImageIcon Imagen redimencionada dimen:30x30
     */
    public ImageIcon RedimencionarImagen(String imagen) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagen));
        ImageIcon fin = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        return fin;
    }

    /**
     * Redimención de imagenes.
     *
     * @param imagen Ruta de la imagen
     * @param width Ancho de la imagen
     * @param height Alto de la imagen
     * @return Imagen redimencionada
     */
    public ImageIcon RedimencionarImagen(String imagen, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagen));
        ImageIcon fin = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return fin;
    }

    /**
     * Establece los bordes con colores obscuros si el campo esta vacio y
     * colores claros de lo contrario.
     *
     * @param txt JTextField a tratar
     * @param titulo Etiqueta del campo ej: "Nombre"
     */
    public void IndicadorContenido(JTextField txt, String titulo) {
        if (!"".equals(txt.getText())) {
            txt.setBorder(
                    BorderFactory.createTitledBorder(
                            BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(255, 255, 255)),
                            titulo,
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont(), new Color(102, 102, 102)));
        } else {
            txt.setBorder(
                    BorderFactory.createTitledBorder(
                            BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)),
                            titulo,
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));
        }
    }

    /**
     * Establece los bordes con colores obscuros si el campo esta vacio y
     * colores claros de lo contrario.
     *
     * @param pnltxtar JScrollPane padre de JTextArea
     * @param txt JTextArea a tratar
     * @param titulo Etiqueta del campo ej: "Nombre"
     */
    public void IndicadorContenido(JScrollPane pnltxtar, JTextArea txt, String titulo) {
        if (!"".equals(txt.getText())) {
            pnltxtar.setBorder(
                    BorderFactory.createTitledBorder(
                            BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(255, 255, 255)),
                            titulo,
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont(), new Color(102, 102, 102)));
        } else {
            pnltxtar.setBorder(
                    BorderFactory.createTitledBorder(
                            BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)),
                            titulo,
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));
        }
    }

    public String ExtraerMensajeError(String msj) {
        if (msj.contains("Duplicate entry")) {
            return "El lote ingresado ya esta registrado!!";
        } else {
            return msj;
        }
    }

    public String formatoDinero(double monto) {
//        char[] arr1 = String.valueOf(monto).toCharArray();
//        System.out.println(Arrays.toString(arr1));
        String[] arr = String.valueOf(monto).split(".");
        String formato = "";
        String formatofin;
        
        int tamanio = arr[0].length();
        if (tamanio < 4) {
            formato = String.valueOf(monto);
        } else if (tamanio > 3 && tamanio < 7) {
            char[] carcter = arr[0].toCharArray();
            int n = carcter.length;
            for (int i = 0; i < n; i++) {
                if (n == 3) {
                    formato += ",";
                }
                formato += String.valueOf(carcter[i]);
                n--;
            }
        }

        if (arr.length > 1) {
            formato += arr[1];
        }

        formatofin = ("$" + formato);

        return formatofin;
    }

    public boolean esNumero(String obj) {
        try {
            Double.parseDouble(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void success(String message) {
        JOptionPane.showMessageDialog(null, message, "Correcto", JOptionPane.INFORMATION_MESSAGE, new CorrectoIcono());
    }
    
    public static void warning(String message) {
        JOptionPane.showMessageDialog(null, message, "Advertencia", JOptionPane.WARNING_MESSAGE, new AdvertenciaIcono());
    }
    
    public static void error(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE, new ErrorIcono());
    }
    
    public static void info(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE, new InformacionIcono());
    }

}
