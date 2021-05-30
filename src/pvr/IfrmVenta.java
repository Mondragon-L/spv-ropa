package pvr;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import mx.venado.pvr.controlador.PvrVenta;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class IfrmVenta extends javax.swing.JInternalFrame {

    PvrVenta venta;
    
    public IfrmVenta(JFrame parent, JLabel lblEstatus, String tipo, int idusuario) {
        initComponents();
        ConfigForm(parent, lblEstatus, tipo, idusuario);
//        jLabel1.setVisible(false);
    }
    
    private void ConfigForm(JFrame parent, JLabel lblEstatus, String tipo, int idusuario){
        venta = new PvrVenta(parent, this, tipo, idusuario, tblListaVenta, btnNuevaVenta, btnVender, lblCantidadProducto, 
                lblIdVendedor, lblTotal, txtBuscarProducto, txtCambio, txtImporte, lblProcesando, lblEstatusVenta, btnReimprimir);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListaVenta = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnVender = new javax.swing.JButton();
        btnNuevaVenta = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarProducto = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        lblIdVendedor = new javax.swing.JLabel();
        lblCantidadProducto = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblEstatusVenta = new javax.swing.JLabel();
        lblProcesando = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ventas");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        tblListaVenta.setFont(new FuenteUbuntu().AplicarRegularFont(15));
        tblListaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblListaVenta.setToolTipText("");
        tblListaVenta.setSelectionBackground(new java.awt.Color(245, 245, 245));
        tblListaVenta.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblListaVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblListaVenta);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estatus de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));
        jPanel3.setName(""); // NOI18N

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnVender.setBackground(new java.awt.Color(0, 102, 0));
        btnVender.setFont(new FuenteUbuntu().AplicarRegularFont(20, Font.BOLD));
        btnVender.setForeground(new java.awt.Color(255, 255, 255));
        btnVender.setMnemonic('V');
        btnVender.setText("VENDER");
        btnVender.setToolTipText("ALT + V");
        jPanel1.add(btnVender);

        btnNuevaVenta.setBackground(new java.awt.Color(153, 0, 0));
        btnNuevaVenta.setFont(new FuenteUbuntu().AplicarRegularFont(10));
        btnNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaVenta.setMnemonic('N');
        btnNuevaVenta.setText("NUEVA VENTA");
        btnNuevaVenta.setToolTipText("ALT + N");
        jPanel1.add(btnNuevaVenta);

        btnReimprimir.setBackground(mx.venado.pvr.utilidades.ConstantesPvr.WARNING);
        btnReimprimir.setFont(new FuenteUbuntu().AplicarRegularFont(10));
        btnReimprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnReimprimir.setText("REIMPRIMIR TICKET");
        btnReimprimir.setToolTipText("Reimprime el ultimo ticket generado");
        jPanel1.add(btnReimprimir);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        txtBuscarProducto.setBackground(new Color(0,0,0,0));
        txtBuscarProducto.setFont(new FuenteUbuntu().AplicarLightFont(15));
        txtBuscarProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Código", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont(15)));
        jPanel4.add(txtBuscarProducto);

        txtImporte.setBackground(new Color(0,0,0,0));
        txtImporte.setFont(new FuenteUbuntu().AplicarLightFont(15));
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporte.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Importe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont(15)));
        jPanel4.add(txtImporte);

        txtCambio.setEditable(false);
        txtCambio.setBackground(new Color(0,0,0,0));
        txtCambio.setFont(new FuenteUbuntu().AplicarLightFont(15));
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Cambio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont(15)));
        jPanel4.add(txtCambio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblIdVendedor.setFont(new FuenteUbuntu().AplicarLightFont(25, Font.BOLD));
        lblIdVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdVendedor.setText("0");
        lblIdVendedor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Vendedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        lblCantidadProducto.setFont(new FuenteUbuntu().AplicarLightFont(25, Font.BOLD));
        lblCantidadProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadProducto.setText("0");
        lblCantidadProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        lblTotal.setFont(new FuenteUbuntu().AplicarLightFont(25, Font.BOLD));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("$0.0");
        lblTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        lblEstatusVenta.setFont(new FuenteUbuntu().AplicarLightFont(15, Font.BOLD));
        lblEstatusVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstatusVenta.setText("OK");
        lblEstatusVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(20)));

        lblProcesando.setFont(new FuenteUbuntu().AplicarLightFont(25, Font.BOLD));
        lblProcesando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProcesando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/venado/pvr/multimedia/cargando.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstatusVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblProcesando)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(lblProcesando)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCantidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(lblEstatusVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnVender;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCantidadProducto;
    private javax.swing.JLabel lblEstatusVenta;
    private javax.swing.JLabel lblIdVendedor;
    private javax.swing.JLabel lblProcesando;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblListaVenta;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables
}
