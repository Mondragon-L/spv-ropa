package mx.venado.pvr.utilidades;

import java.awt.Color;

public class ConstantesPvr {

    public final static int PANTALLA_APARTADO = 0;
    public final static int PANTALLA_CLIENTE = 1;
    public final static int PANTALLA_INVENTARIO = 2;
    public final static int PANTALLA_VENTA = 3;
    public final static int PANTALLA_INFORME = 4;
    public final static String OK = "[OK]";
    public final static String NR = "[NR]";
    public final static String NA = "[NA]";
    public final static String ADV = "[ADV]";
    public final static String STD = "[STD]";
    public final static String MSJ = "[MSJ]";
    public final static String MSJ_DEFAULT = "PVR-Venado";
    public final static String LIQUIDADO = "[liquidado]";
    public final static String DEUDA = "[deuda]";

    public final static Color SUCCESS = new Color(0, 102, 0);
    public final static Color ERROR = new Color(153, 0, 0);
    public final static Color WARNING = new Color(212, 114, 30);
    public final static Color DEFAULT = new Color(240, 240, 240);
    public final static Color DEFAULT2 = new Color(0, 0, 0);
    
    public final static String URL_MULTIMEDIA = "/mx/venado/pvr/multimedia/";

    /**
     * Esquema clientes
     */
    public final static String TABLA_CLIENTES = "clientes";
    public final static String CLIENTES_ID = "clientes_id";
    public final static String CLIENTES_NOMBRE = "clientes_nombre";
    public final static String CLIENTES_AP = "clientes_ap";
    public final static String CLIENTES_AM = "clientes_am";
    public final static String CLIENTES_TELEFONO = "clientes_telefono";
    public final static String CLIENTES_CORREO = "clientes_correo";
    public final static String CLIENTES_OBSERVACION = "clientes_observacion";

    /**
     * Esquema inventario
     */
    public final static String TABLA_INVENTARIO = "inventario";
    public final static String INVENTARIO_ID = "inventario_id";
    public final static String INVENTARIO_LOTE = "inventario_lote";
    public final static String INVENTARIO_PRODUCTO = "inventario_producto";
    public final static String INVENTARIO_MODELO = "inventario_modelo";
    public final static String INVENTARIO_TALLA = "inventario_talla";
    public final static String INVENTARIO_PCOMPRA = "inventario_pcompra";
    public final static String INVENTARIO_PVENTA = "inventario_pventa";
    public final static String INVENTARIO_EXISTENCIAS = "inventario_existencias";
    public final static String INVENTARIO_OBSERVACION = "inventario_observacion";
    public final static String IINVENTARIO_ID = "inventario.inventario_id";
    public final static String IINVENTARIO_LOTE = "inventario.inventario_lote";
    public final static String IINVENTARIO_PRODUCTO = "inventario.inventario_producto";
    public final static String IINVENTARIO_MODELO = "inventario.inventario_modelo";
    public final static String IINVENTARIO_PCOMPRA = "inventario.inventario_pcompra";
    public final static String IINVENTARIO_PVENTA = "inventario.inventario_pventa";
    public final static String IINVENTARIO_EXISTENCIAS = "inventario.inventario_existencias";
    public final static String IINVENTARIO_OBSERVACION = "inventario.inventario_observacion";

    /**
     * Esquema Apartado
     */
    public final static String TABLA_APARTADO = "apartado";
    public final static String APARTADO_ID = "apartado_id";
    public final static String APARTADO_ID_CLIENTE = "apartado_idcliente";
    public final static String APARTADO_TOTAL = "apartado_total";
    public final static String APARTADO_ESTATUS = "apartado_estatus";
    /**
     * Esquema ApartadoProducto
     */
    public final static String TABLA_APARTADOPRODUCTO = "apartprod";
    public final static String APARTADOPRODUCTO_ID = "apartprod_id";
    public final static String APARTADOPRODUCTO_ID_APARTADO = "apartprod_idapartado";
    public final static String APARTADOPRODUCTO_LOTE = "apartprod_lote";
    public final static String APARTADOPRODUCTO_FECHA = "apartprod_fecha";
    public final static String APARTADOPRODUCTO_HORA = "apartprod_hora";
    public final static String APARTADOPRODUCTO_PRECIOVENTA = "apartprod_precioventa";
    public final static String APARTADOPRODUCTO_CANTIDAD = "apartprod_cantidad";
    public final static String APARTADOPRODUCTO_TOTAL = "apartprod_total";
    /**
     * Esquema ApartadoAbono
     */
    public final static String TABLA_APARTADOABONO = "apartadoabono";
    public final static String APARTADOABONO_ID = "apartadoabono_id";
    public final static String APARTADOABONO_ID_APARTADOPRODUCTO = "apartadoabono_idapartprod";
    public final static String APARTADOABONO_FECHA = "apartadoabono_fecha";
    public final static String APARTADOABONO_HORA = "apartadoabono_hora";
    public final static String APARTADOABONO_CANTIDAD = "apartadoabono_cantidad";
    public final static String APARTADOABONO_OBSERVACION = "apartadoabono_observacion";
    /**
     * Esquema venta
     */
    public final static String TABLA_VENTA = "venta";
    public final static String VENTA_ID = "venta_id";
    public final static String VENTA_FECHA = "venta_fecha";
    public final static String VENTA_HORA = "venta_hora";
    public final static String VENTA_TOTAL = "venta_total";
    public final static String VENTA_IMPORTE = "venta_importe";
    public final static String VENTA_CAMBIO = "venta_cambio";
    public final static String VENTA_VENDEDOR = "venta_vendedor";
    public final static String VVENTA_ID = "venta.venta_id";
    public final static String VVENTA_FECHA = "venta.venta_fecha";
    public final static String VVENTA_HORA = "venta.venta_hora";
    public final static String VVENTA_TOTAL = "venta.venta_total";
    public final static String VVENTA_IMPORTE = "venta.venta_importe";
    public final static String VVENTA_CAMBIO = "venta.venta_cambio";
    public final static String VVENTA_VENDEDOR = "venta.venta_vendedor";
    /**
     * Esquema vntprod
     */
    public final static String TABLA_VNTPROD = "vntprod";
    public final static String VNTPROD_ID = "vntprod_id"; 
    public final static String VNTPROD_LOTE = "vntprod_lote";
    public final static String VNTPROD_CANTIDAD = "vntprod_cantidad";
    public final static String VNTPROD_PVENTA = "vntprod_pventa";
    public final static String VNTPROD_TOTAL = "vntprod_total";
    public final static String VNTPROD_OBERVACION = "vntprod_observacion"; 
    public final static String VNTPROD_VENTAID = "vntprod_venta_id"; 
    public final static String VVNTPROD_ID = "vntprod.vntprod_id"; 
    public final static String VVNTPROD_LOTE = "vntprod.vntprod_lote";
    public final static String VVNTPROD_CANTIDAD = "vntprod.vntprod_cantidad";
    public final static String VVNTPROD_PVENTA = "vntprod.vntprod_pventa";
    public final static String VVNTPROD_TOTAL = "vntprod.vntprod_total";
    public final static String VVNTPROD_OBERVACION = "vntprod.vntprod_observacion"; 
    public final static String VVNTPROD_VENTAID = "vntprod.vntprod_venta_id"; 
    
    /**
     * Esquema usuarios
     */
    public final static String TABLA_USUARIO = "usuario";
    public final static String UUSUARIO_ID = "usuario.usuario_id";
    public final static String UUSUARIO_TIPO = "usuario.usuario_tipo";
}
