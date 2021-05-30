package pvr;

import java.awt.Color;
import java.awt.Font;
import mx.venado.pvr.controlador.PvrAbono;
import mx.venado.pvr.modelo.vo.ClienteVo;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class DfrmAbonos extends javax.swing.JDialog {

    PvrAbono abono;

    private ClienteVo clienteVo;
    private ProductoVo productoVo;

    public DfrmAbonos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConfigForm();
    }

    private void ConfigForm() {
        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        abono = new PvrAbono(this, tblAbonos, lblIdApartadoProducto, lblLoteApartadoProducto, 
                lblNombreApartadoProducto, lblNombreCliente, lblRestaApartadoProducto, 
                lblTotalApartadoProducto, txtAbonar, txtConcepto, btnAbonar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblAbonos = new javax.swing.JTable();
        lblNombreCliente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblIdApartadoProducto = new javax.swing.JLabel();
        lblLoteApartadoProducto = new javax.swing.JLabel();
        lblNombreApartadoProducto = new javax.swing.JLabel();
        lblRestaApartadoProducto = new javax.swing.JLabel();
        lblTotalApartadoProducto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtAbonar = new javax.swing.JTextField();
        scrllpnTxtConcepto = new javax.swing.JScrollPane();
        txtConcepto = new javax.swing.JTextArea();
        btnAbonar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abonos");
        setMinimumSize(new java.awt.Dimension(400, 400));
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblAbonos.setFont(new FuenteUbuntu().AplicarLightFont());
        tblAbonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAbonos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblAbonos.setSelectionBackground(new java.awt.Color(245, 245, 245));
        tblAbonos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblAbonos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblAbonos);

        lblNombreCliente.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblNombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        jPanel1.setLayout(new java.awt.GridLayout(5, 1));

        lblIdApartadoProducto.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblIdApartadoProducto.setForeground(new java.awt.Color(153, 0, 0));
        lblIdApartadoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdApartadoProducto.setText("-");
        lblIdApartadoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Id apartado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel1.add(lblIdApartadoProducto);

        lblLoteApartadoProducto.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblLoteApartadoProducto.setForeground(new java.awt.Color(153, 0, 0));
        lblLoteApartadoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoteApartadoProducto.setText("-");
        lblLoteApartadoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Lote", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel1.add(lblLoteApartadoProducto);

        lblNombreApartadoProducto.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblNombreApartadoProducto.setForeground(new java.awt.Color(153, 0, 0));
        lblNombreApartadoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreApartadoProducto.setText("-");
        lblNombreApartadoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel1.add(lblNombreApartadoProducto);

        lblRestaApartadoProducto.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblRestaApartadoProducto.setForeground(new java.awt.Color(153, 0, 0));
        lblRestaApartadoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRestaApartadoProducto.setText("-");
        lblRestaApartadoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Resta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel1.add(lblRestaApartadoProducto);

        lblTotalApartadoProducto.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblTotalApartadoProducto.setForeground(new java.awt.Color(153, 0, 0));
        lblTotalApartadoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalApartadoProducto.setText("-");
        lblTotalApartadoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel1.add(lblTotalApartadoProducto);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        txtAbonar.setBackground(new Color(0,0,0,0));
        txtAbonar.setFont(new FuenteUbuntu().AplicarLightFont(20));
        txtAbonar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Abonar $", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));
        jPanel2.add(txtAbonar);

        scrllpnTxtConcepto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Concepto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        txtConcepto.setBackground(new Color(0,0,0,0));
        txtConcepto.setColumns(20);
        txtConcepto.setFont(new FuenteUbuntu().AplicarLightFont());
        txtConcepto.setLineWrap(true);
        txtConcepto.setRows(5);
        txtConcepto.setWrapStyleWord(true);
        scrllpnTxtConcepto.setViewportView(txtConcepto);

        jPanel2.add(scrllpnTxtConcepto);

        btnAbonar.setBackground(ConstantesPvr.SUCCESS);
        btnAbonar.setFont(new FuenteUbuntu().AplicarRegularFont(20, Font.BOLD));
        btnAbonar.setForeground(ConstantesPvr.DEFAULT);
        btnAbonar.setText("ABONAR");
        jPanel2.add(btnAbonar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        abono.Init(getProductoVo(), getClienteVo());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DfrmAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DfrmAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DfrmAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DfrmAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DfrmAbonos dialog = new DfrmAbonos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdApartadoProducto;
    private javax.swing.JLabel lblLoteApartadoProducto;
    private javax.swing.JLabel lblNombreApartadoProducto;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblRestaApartadoProducto;
    private javax.swing.JLabel lblTotalApartadoProducto;
    private javax.swing.JScrollPane scrllpnTxtConcepto;
    private javax.swing.JTable tblAbonos;
    private javax.swing.JTextField txtAbonar;
    private javax.swing.JTextArea txtConcepto;
    // End of variables declaration//GEN-END:variables

    private ClienteVo getClienteVo() {
        return clienteVo;
    }

    public void setClienteVo(ClienteVo clienteVo) {
        this.clienteVo = clienteVo;
    }

    private ProductoVo getProductoVo() {
        return productoVo;
    }

    public void setProductoVo(ProductoVo productoVo) {
        this.productoVo = productoVo;
    }
}
