package mx.venado.pvr.utilidades;

import java.awt.Color;

public interface IPvrConstant {

    int PANTALLA_APARTADO = 0;
    int PANTALLA_CLIENTE = 1;
    int PANTALLA_INVENTARIO = 2;
    int PANTALLA_VENTA = 3;
    int PANTALLA_INFORME = 4;
    String OK = "[OK]";
    String NR = "[NR]";
    String NA = "[NA]";
    String ADV = "[ADV]";
    String STD = "[STD]";
    String MSJ = "[MSJ]";
    String MSJ_DEFAULT = "Pvr-Venado";
    String LIQUIDADO = "[liquidado]";
    String DEUDA = "[deuda]";

    Color SUCCESS = new Color(0, 102, 0);
    Color ERROR = new Color(153, 0, 0);
    Color WARNING = new Color(212, 114, 30);
    Color DEFAULT = new Color(240, 240, 240);
    Color DEFAULT2 = new Color(0, 0, 0);

    String URL_MULTIMEDIA = "/mx/venado/pvr/multimedia/";
}
