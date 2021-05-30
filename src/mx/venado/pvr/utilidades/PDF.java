/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.venado.pvr.utilidades;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mx.venado.pvr.modelo.vo.ProductoVo;

/**
 *
 * @author LeicaSoluciones
 */
public class PDF {

    public PDF() {
    }

    public void CrearPDF(String nombrefichero, ArrayList<ProductoVo> pvs) {
        try {
            Document document = new Document(PageSize.LETTER);
            FileOutputStream archivo = new FileOutputStream(nombrefichero + ".pdf");
            PdfWriter.getInstance(document, archivo).setInitialLeading(20);
            PdfPTable tblInvent = new PdfPTable(6);
            tblInvent.addCell("Lote");
            tblInvent.addCell("Producto");
            tblInvent.addCell("Modelo");
            tblInvent.addCell("P.Compra");
            tblInvent.addCell("p.Venta");
            tblInvent.addCell("Existencias");
            ProductoVo pv;
            for (int i = 0; i < pvs.size(); i++) {
                pv = pvs.get(i);
                tblInvent.addCell(pv.getLote());
                tblInvent.addCell(pv.getProducto());
                tblInvent.addCell(pv.getModelo());
                tblInvent.addCell("" + pv.getPcompra());
                tblInvent.addCell("" + pv.getPventa());
                tblInvent.addCell("" + pv.getExistencias());
            }

            document.open();
            document.add(tblInvent);
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, "ocurrio un error al generar pdf" + e.getMessage());
        }
    }
}
