package pvr;

import java.awt.Frame;
import java.awt.Image;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mx.venado.pvr.controlador.Pvr;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.UtilidadesPvr;

public class FrmPvr extends javax.swing.JFrame {

    IfrmClientes clientes;
    IfrmProductos productos;
    IfrmVenta ventas;
    IfrmApartados apartados;
    IfrmInformes informes;

    Pvr nora;

    private int IdUsuario = -1;
    private String Tipo = "STD";
    private String Usuario = "NA";

    public FrmPvr() {
        initComponents();
        ConfigForm();
    }

    private void ConfigForm() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Nora - " + Tipo);
        Image icon = new ImageIcon(getClass().getResource(ConstantesPvr.URL_MULTIMEDIA + "icono.png")).getImage();
        this.setIconImage(icon);
        new UtilidadesPvr().SetLog(lblEstatus, ConstantesPvr.STD, ConstantesPvr.MSJ_DEFAULT);
//        nora = new Pvr(btnIfrmApartado, btnIfrmClientes, btnIfrmInventario, btnIfrmVenta, btnIfrmInformes, btnDfrmPerfil Tipo);
    }

    private void Init() {
        clientes = new IfrmClientes(this, lblEstatus, Tipo, IdUsuario);
        productos = new IfrmProductos(this, lblEstatus, Tipo, IdUsuario);
        ventas = new IfrmVenta(this, lblEstatus, Tipo, IdUsuario);
        apartados = new IfrmApartados(this, lblEstatus, Tipo, IdUsuario);
        informes = new IfrmInformes(this);
        nora = new Pvr(btnIfrmApartado, btnIfrmClientes, btnIfrmInventario, btnIfrmVenta, btnIfrmInformes, btnDfrmPerfil, Tipo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        btnIfrmClientes = new javax.swing.JButton();
        btnIfrmInventario = new javax.swing.JButton();
        btnIfrmVenta = new javax.swing.JButton();
        btnIfrmApartado = new javax.swing.JButton();
        btnIfrmInformes = new javax.swing.JButton();
        btnDfrmPerfil = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        DeskNora = new javax.swing.JDesktopPane();
        lblEstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nora");
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setAutoscrolls(true);
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 705));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setHorizontalScrollBar(null);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new java.awt.GridLayout(6, 0));

        btnIfrmClientes.setBackground(new java.awt.Color(51, 51, 51));
        btnIfrmClientes.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Clientes/iconClientes.png"))); // NOI18N
        btnIfrmClientes.setMnemonic('C');
        btnIfrmClientes.setText("Clientes");
        btnIfrmClientes.setToolTipText("Alt+C");
        btnIfrmClientes.setBorder(null);
        btnIfrmClientes.setBorderPainted(false);
        btnIfrmClientes.setContentAreaFilled(false);
        btnIfrmClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIfrmClientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Clientes/pressedIconClientes.png"))); // NOI18N
        btnIfrmClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Clientes/rolloverIconClientes.png"))); // NOI18N
        btnIfrmClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIfrmClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmClientesActionPerformed(evt);
            }
        });
        jPanel3.add(btnIfrmClientes);

        btnIfrmInventario.setBackground(new java.awt.Color(51, 51, 51));
        btnIfrmInventario.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmInventario.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Inventario/iconInventario.png"))); // NOI18N
        btnIfrmInventario.setMnemonic('I');
        btnIfrmInventario.setText("Inventario");
        btnIfrmInventario.setToolTipText("Alt+I");
        btnIfrmInventario.setBorder(null);
        btnIfrmInventario.setBorderPainted(false);
        btnIfrmInventario.setContentAreaFilled(false);
        btnIfrmInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIfrmInventario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Inventario/pressedIconInventario.png"))); // NOI18N
        btnIfrmInventario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Inventario/rolloverIconInventario.png"))); // NOI18N
        btnIfrmInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIfrmInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmInventarioActionPerformed(evt);
            }
        });
        jPanel3.add(btnIfrmInventario);

        btnIfrmVenta.setBackground(new java.awt.Color(51, 51, 51));
        btnIfrmVenta.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Ventas/iconVentas.png"))); // NOI18N
        btnIfrmVenta.setMnemonic('V');
        btnIfrmVenta.setText("Ventas");
        btnIfrmVenta.setToolTipText("Alt+V");
        btnIfrmVenta.setBorder(null);
        btnIfrmVenta.setBorderPainted(false);
        btnIfrmVenta.setContentAreaFilled(false);
        btnIfrmVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIfrmVenta.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Ventas/pressedIconVentas.png"))); // NOI18N
        btnIfrmVenta.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Ventas/rolloverIconVentas.png"))); // NOI18N
        btnIfrmVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIfrmVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmVentaActionPerformed(evt);
            }
        });
        jPanel3.add(btnIfrmVenta);

        btnIfrmApartado.setBackground(new java.awt.Color(51, 51, 51));
        btnIfrmApartado.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmApartado.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmApartado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Apartados/iconApartados.png"))); // NOI18N
        btnIfrmApartado.setMnemonic('A');
        btnIfrmApartado.setText("Apartados");
        btnIfrmApartado.setToolTipText("Alt+A");
        btnIfrmApartado.setBorder(null);
        btnIfrmApartado.setBorderPainted(false);
        btnIfrmApartado.setContentAreaFilled(false);
        btnIfrmApartado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIfrmApartado.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Apartados/pressedIconApartados.png"))); // NOI18N
        btnIfrmApartado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Apartados/rolloverIconApartados.png"))); // NOI18N
        btnIfrmApartado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIfrmApartado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmApartadoActionPerformed(evt);
            }
        });
        jPanel3.add(btnIfrmApartado);

        btnIfrmInformes.setBackground(new java.awt.Color(51, 51, 51));
        btnIfrmInformes.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmInformes.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Informes/iconInformes.png"))); // NOI18N
        btnIfrmInformes.setText("Informes");
        btnIfrmInformes.setToolTipText("");
        btnIfrmInformes.setBorder(null);
        btnIfrmInformes.setBorderPainted(false);
        btnIfrmInformes.setContentAreaFilled(false);
        btnIfrmInformes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIfrmInformes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Informes/pressedIconInformes.png"))); // NOI18N
        btnIfrmInformes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Informes/rolloverIconInformes.png"))); // NOI18N
        btnIfrmInformes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIfrmInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmInformesActionPerformed(evt);
            }
        });
        jPanel3.add(btnIfrmInformes);

        btnDfrmPerfil.setBackground(new java.awt.Color(51, 51, 51));
        btnDfrmPerfil.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnDfrmPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnDfrmPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/perfil/iconPerfil.png"))); // NOI18N
        btnDfrmPerfil.setText("Perfil");
        btnDfrmPerfil.setToolTipText("");
        btnDfrmPerfil.setBorder(null);
        btnDfrmPerfil.setBorderPainted(false);
        btnDfrmPerfil.setContentAreaFilled(false);
        btnDfrmPerfil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDfrmPerfil.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/perfil/pressedIconPerfil.png"))); // NOI18N
        btnDfrmPerfil.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/perfil/rolloverIconPerfil.png"))); // NOI18N
        btnDfrmPerfil.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDfrmPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDfrmPerfilActionPerformed(evt);
            }
        });
        jPanel3.add(btnDfrmPerfil);

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout DeskNoraLayout = new javax.swing.GroupLayout(DeskNora);
        DeskNora.setLayout(DeskNoraLayout);
        DeskNoraLayout.setHorizontalGroup(
            DeskNoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DeskNoraLayout.setVerticalGroup(
            DeskNoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );

        lblEstatus.setFont(new FuenteUbuntu().AplicarLightFont(15));
        lblEstatus.setForeground(new java.awt.Color(255, 255, 255));
        lblEstatus.setText("Nora");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
            .addComponent(DeskNora, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(DeskNora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setRightComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIfrmClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmClientesActionPerformed
        try {
            if (!clientes.isShowing()) {
                if (clientes.isIcon()) {
                    clientes.setMaximum(true);
                } else {
                    this.DeskNora.add(clientes);
                    clientes.setMaximum(true);
                    clientes.show();
                }
            } else {
                clientes.moveToFront();
            }
            nora.Activo(ConstantesPvr.PANTALLA_CLIENTE);
        } catch (PropertyVetoException ex) {
            lblEstatus.setText((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
        }
    }//GEN-LAST:event_btnIfrmClientesActionPerformed

    private void btnIfrmInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmInventarioActionPerformed
        try {
            if (!productos.isShowing()) {
                if (productos.isIcon()) {
                    productos.setMaximum(true);
                } else {
                    this.DeskNora.add(productos);
                    productos.setMaximum(true);
                    productos.show();
                }
            } else {
                productos.moveToFront();
            }
            nora.Activo(ConstantesPvr.PANTALLA_INVENTARIO);
        } catch (PropertyVetoException ex) {
            lblEstatus.setText((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
        }
    }//GEN-LAST:event_btnIfrmInventarioActionPerformed

    private void btnIfrmVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmVentaActionPerformed
        try {
            if (!ventas.isShowing()) {
                if (ventas.isIcon()) {
                    ventas.setMaximum(true);
                } else {
                    this.DeskNora.add(ventas);
                    ventas.setMaximum(true);
                    ventas.show();
                }
            } else {
                ventas.moveToFront();
            }
            nora.Activo(ConstantesPvr.PANTALLA_VENTA);
        } catch (PropertyVetoException ex) {
            lblEstatus.setText((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
        }
    }//GEN-LAST:event_btnIfrmVentaActionPerformed

    private void btnIfrmApartadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmApartadoActionPerformed
        try {
            if (!apartados.isShowing()) {
                if (apartados.isIcon()) {
                    apartados.setMaximum(true);
                } else {
                    this.DeskNora.add(apartados);
                    apartados.setMaximum(true);
                    apartados.show();
                }
            } else {
                apartados.moveToFront();
            }
            nora.Activo(ConstantesPvr.PANTALLA_APARTADO);
        } catch (PropertyVetoException ex) {
            lblEstatus.setText((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
        }
    }//GEN-LAST:event_btnIfrmApartadoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setTitle("Pvr - " + Tipo);
        this.toFront();
        Init();
    }//GEN-LAST:event_formWindowOpened

    private void btnIfrmInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmInformesActionPerformed
        try {
            if (!informes.isShowing()) {
                if (informes.isIcon()) {
                    informes.setMaximum(true);
                } else {
                    this.DeskNora.add(informes);
                    informes.setMaximum(true);
                    informes.show();
                }
            } else {
                informes.moveToFront();
            }
            nora.Activo(ConstantesPvr.PANTALLA_INFORME);
        } catch (PropertyVetoException ex) {
            lblEstatus.setText((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
        }
    }//GEN-LAST:event_btnIfrmInformesActionPerformed

    private void btnDfrmPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDfrmPerfilActionPerformed
        DfrmPerfil dfrmPerfil = new DfrmPerfil(this, true);
        dfrmPerfil.Init(IdUsuario, Usuario, Tipo);
        dfrmPerfil.setVisible(true);

        if (dfrmPerfil.isDatEdit()) {
            this.dispose();
            DfrmSesion2 dialog = new DfrmSesion2(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnDfrmPerfilActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrmPvr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPvr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPvr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPvr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmPvr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DeskNora;
    private javax.swing.JButton btnDfrmPerfil;
    private javax.swing.JButton btnIfrmApartado;
    private javax.swing.JButton btnIfrmClientes;
    private javax.swing.JButton btnIfrmInformes;
    private javax.swing.JButton btnIfrmInventario;
    private javax.swing.JButton btnIfrmVenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblEstatus;
    // End of variables declaration//GEN-END:variables

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

}
