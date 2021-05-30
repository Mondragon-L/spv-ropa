package pvr;

import java.awt.Color;
import java.awt.Font;
import mx.venado.pvr.controlador.PvrProducto;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class DfrmAgregarApartados extends javax.swing.JDialog {

    PvrProducto producto;
    ApartadoVo apartadoVo;

    public DfrmAgregarApartados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConfigForm();
    }

    private void ConfigForm() {
        this.setLocationRelativeTo(null);
        this.producto = new PvrProducto(this, txtBuscar, tblProductos, lblIdCliente, lblIdApartado, btnAgregarApartado);
    }

    public void setDatosCliente(ApartadoVo apartadoVo) {
        this.apartadoVo = apartadoVo;
        lblIdCliente.setText("" + apartadoVo.getIdCliente());
        lblIdApartado.setText((apartadoVo.getId() != -1) ? "" + apartadoVo.getId() : "-");
        producto.setDatosApartado(apartadoVo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblIdApartado = new javax.swing.JLabel();
        lblIdCliente = new javax.swing.JLabel();
        btnAgregarApartado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar apartado");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        tblProductos.setFont(new FuenteUbuntu().AplicarRegularFont());
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblProductos.setSelectionBackground(new java.awt.Color(245, 245, 245));
        tblProductos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblProductos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
        );

        txtBuscar.setBackground(new Color(0,0,0,0));
        txtBuscar.setFont(new FuenteUbuntu().AplicarLightFont());
        txtBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lblIdApartado.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblIdApartado.setForeground(new java.awt.Color(153, 0, 0));
        lblIdApartado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdApartado.setText("-");
        lblIdApartado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)), "Id de apartado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel1.add(lblIdApartado);

        lblIdCliente.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblIdCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));
        jPanel1.add(lblIdCliente);

        btnAgregarApartado.setBackground(ConstantesPvr.SUCCESS);
        btnAgregarApartado.setFont(new FuenteUbuntu().AplicarRegularFont(20, Font.BOLD));
        btnAgregarApartado.setForeground(ConstantesPvr.DEFAULT);
        btnAgregarApartado.setText("AGREGAR");
        jPanel1.add(btnAgregarApartado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DfrmAgregarApartados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DfrmAgregarApartados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DfrmAgregarApartados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DfrmAgregarApartados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DfrmAgregarApartados dialog = new DfrmAgregarApartados(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarApartado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdApartado;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
