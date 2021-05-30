package mx.venado.pvr.utilidades;

import br.com.adilson.util.PrinterMatrix;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JLabel;
import mx.venado.pvr.modelo.vo.TicketAbonoVo;
import mx.venado.pvr.modelo.vo.TicketVo;
import mx.venado.pvr.modelo.vo.VentaVo;

public class Ticket {

    VentaVo ventaVo = new VentaVo();
    JLabel lblEstatus;

    public Ticket() {
    }

    public Ticket(JLabel lblEstatus) {
        this.lblEstatus = lblEstatus;
    }

    public void ticketVentaSTD(ArrayList<TicketVo> dat) {
        try {
            int tamanio = (17 + (dat.size() - 1)) + 9;
            FileInputStream inputStream;
            PrinterMatrix printer = new PrinterMatrix();
            printer.setOutSize(tamanio, 48);
            printer.printTextWrap(0, 1, 18, 48, "BOUT.");
            printer.printTextWrap(2, 1, 2, 48, "Fermin J. Villalos 50450 Mex.");
            printer.printTextWrap(3, 1, 15, 48, "Col. centro");
            printer.printTextWrap(5, 1, 0, 48, "Venta:" + rellenarCeros(Integer.toString(dat.get(0).getVentaId()))
                    + " Fecha:" + dat.get(0).getVentaFecha() + " " + dat.get(0).getVentaHora());
            printer.printTextWrap(6, 1, 0, 48, "Atendio: " + dat.get(0).getVentaVendedor());
            printer.printTextWrap(7, 1, 0, 48, "Cliente: Venta mostrador");
            printer.printCharAtCol(9, 1, 48, "*");
            printer.printTextWrap(9, 1, 0, 48, "Lote      Desc         Cant   Precio     Total");
            printer.printCharAtCol(11, 1, 48, "*");
            TicketVo t;
            String cadena = "";
            for (int i = 1; i < dat.size(); i++) {
                t = dat.get(i);
                cadena += rellenarEspacios(t.getProductoLote(), 10);
                cadena += rellenarEspacios(t.getProductoDescripcion(), 13);
                cadena += rellenarEspacios(Double.toString(t.getProductoCantidad()), 7);
                cadena += rellenarEspacios("$" + t.getProductoPventa(), 9);
                cadena += rellenarEspacios("$" + t.getProductoTotal(), 9);
                printer.printTextWrap((10 + i), 1, 0, 48, cadena);
                cadena = "";
            }
            printer.printTextWrap((tamanio - 8) - 5, 1, 31, 48, "Total: $" + dat.get(0).getVentaTotal());
            printer.printTextWrap((tamanio - 8) - 4, 1, 29, 48, "Importe: $" + dat.get(0).getVentaImporte());
            printer.printTextWrap((tamanio - 8) - 3, 1, 30, 48, "Cambio: $" + dat.get(0).getVentaCambio());
            printer.printTextWrap((tamanio - 8) - 2, 1, 12, 48, "Siempre pensando en ti!");
            printer.printTextWrap((tamanio - 8) - 1, 1, 13, 48, "Gracias por su compra!");

            printer.toFile("impresion.txt");
            inputStream = new FileInputStream("impresion.txt");

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                printJob.print(document, attributeSet);
            } else {
                lblEstatus.setText("Impresora no localizada...");
            }
        } catch (FileNotFoundException | PrintException e) {
            lblEstatus.setText("Error al imprimir: " + e.getMessage());
        }
    }
    
    public void ticketAbonoSTD(TicketAbonoVo dat) {
        try {
            int tamanio = 17;
            FileInputStream inputStream;
            PrinterMatrix printer = new PrinterMatrix();
            printer.setOutSize(tamanio, 48);
            printer.printTextWrap(0, 1, 18, 48, "BOUT.");
            printer.printTextWrap(2, 1, 2, 48, "Fermin J. Villalos 50450 Mex.");
            printer.printTextWrap(3, 1, 15, 48, "Col. centro");
            printer.printTextWrap(4, 1, 0, 48, "Fecha:" + dat.getFecha() + " " + dat.getHora());
            printer.printTextWrap(5, 1, 0, 48, "Cliente: " + dat.getNombre() + " " + dat.getApellidoPaterno());
            printer.printTextWrap(6, 1, 0, 48, "Producto: " + dat.getLote());
            printer.printTextWrap(7, 1, 0, 48, "Abono: $" + dat.getCantidad());
            printer.printTextWrap(8, 1, 0, 48, dat.getObservacion());
            printer.printTextWrap(9, 1, 12, 48, "Siempre pensando en ti!");
            printer.printTextWrap(10, 1, 13, 48, "Gracias por su compra!");

            printer.toFile("impresion.txt");
            inputStream = new FileInputStream("impresion.txt");

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc document = new SimpleDoc(inputStream, docFormat, null);
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                printJob.print(document, attributeSet);
            } else {
                System.out.println("Impresora no localizada...");
            }
        } catch (FileNotFoundException | PrintException e) {
            System.out.println("Error al imprimir: " + e.getMessage());
//            lblEstatus.setText("Error al imprimir: " + e.getMessage());
        }
    }

    private String rellenarCeros(String num) {
        int t = num.length();
        for (int i = 0; i < (11 - t); i++) {
            num = "0" + num;
        }
        return num;
    }

    private String rellenarEspacios(String txt, int disponible) {
        int t = txt.length();
        for (int i = 0; i < (disponible - t); i++) {
            txt += " ";
        }
        return txt;
    }

    private void cortar() throws IOException {
        FileWriter fw = new FileWriter("corte.txt");
        try (BufferedWriter bw = new BufferedWriter(fw)) {
//            StringBuilder builder = new StringBuilder();
            char[] cutPaper = new char[]{0x1d, 0x69};
//            builder.append(cutPaper);

            bw.write(cutPaper);
        }
    }
}
