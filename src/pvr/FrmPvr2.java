package pvr;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import javax.swing.ImageIcon;
import mx.venado.pvr.controlador.Pvr;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.utilidades.IPvrConstant;

public class FrmPvr2 extends javax.swing.JFrame implements IPvrConstant {

    Pvr pvr;

    private int IdUsuario = -1;
    private String Tipo = "STD";
    private String Usuario = "NA";

    public FrmPvr2() {
        initComponents();
        configForm();
    }

    private void configForm() {
        this.getContentPane().setBackground(new Color(0, 102, 153));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Pvr - " + Tipo);
        Image icon = new ImageIcon(getClass().getResource(ConstantesPvr.URL_MULTIMEDIA + "icono.png")).getImage();
        this.setIconImage(icon);
        new UtilidadesPvr().SetLog(lblEstatus, ConstantesPvr.STD, ConstantesPvr.MSJ_DEFAULT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnIfrmClientes = new javax.swing.JButton();
        btnIfrmInventario = new javax.swing.JButton();
        btnIfrmVenta = new javax.swing.JButton();
        btnIfrmApartado = new javax.swing.JButton();
        btnIfrmInformes = new javax.swing.JButton();
        btnDfrmPerfil = new javax.swing.JButton();
        DeskPvr = new javax.swing.JDesktopPane();
        lblEstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        btnIfrmClientes.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmClientes.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen(URL_MULTIMEDIA + "Clientes/pressedIconClientes.png")
        );
        btnIfrmClientes.setMnemonic('C');
        btnIfrmClientes.setText("Clientes");
        btnIfrmClientes.setToolTipText("Alt+C");
        btnIfrmClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnIfrmClientes);

        btnIfrmInventario.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmInventario.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmInventario.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen(URL_MULTIMEDIA + "Inventario/pressedIconInventario.png"));
        btnIfrmInventario.setMnemonic('I');
        btnIfrmInventario.setText("Inventario");
        btnIfrmInventario.setToolTipText("Alt+I");
        btnIfrmInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmInventarioActionPerformed(evt);
            }
        });
        jToolBar1.add(btnIfrmInventario);

        btnIfrmVenta.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmVenta.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen(URL_MULTIMEDIA + "Ventas/pressedIconVentas.png"));
        btnIfrmVenta.setMnemonic('V');
        btnIfrmVenta.setText("Ventas");
        btnIfrmVenta.setToolTipText("Alt+V");
        btnIfrmVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmVentaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnIfrmVenta);

        btnIfrmApartado.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmApartado.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmApartado.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen(URL_MULTIMEDIA + "Apartados/pressedIconApartados.png"));
        btnIfrmApartado.setMnemonic('A');
        btnIfrmApartado.setText("Apartados");
        btnIfrmApartado.setToolTipText("Alt+A");
        btnIfrmApartado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmApartadoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnIfrmApartado);

        btnIfrmInformes.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnIfrmInformes.setForeground(new java.awt.Color(255, 255, 255));
        btnIfrmInformes.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen(URL_MULTIMEDIA + "Informes/pressedIconInformes.png"));
        btnIfrmInformes.setText("Informes");
        btnIfrmInformes.setToolTipText("");
        btnIfrmInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIfrmInformesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnIfrmInformes);

        btnDfrmPerfil.setFont(new FuenteUbuntu().AplicarRegularFont());
        btnDfrmPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnDfrmPerfil.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen(URL_MULTIMEDIA + "perfil/pressedIconPerfil.png"));
        btnDfrmPerfil.setText("Perfil");
        btnDfrmPerfil.setToolTipText("");
        btnDfrmPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDfrmPerfilActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDfrmPerfil);

        javax.swing.GroupLayout DeskPvrLayout = new javax.swing.GroupLayout(DeskPvr);
        DeskPvr.setLayout(DeskPvrLayout);
        DeskPvrLayout.setHorizontalGroup(
            DeskPvrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DeskPvrLayout.setVerticalGroup(
            DeskPvrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
        );

        lblEstatus.setFont(new FuenteUbuntu().AplicarLightFont(15));
        lblEstatus.setForeground(new java.awt.Color(255, 255, 255));
        lblEstatus.setText("Nora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DeskPvr)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblEstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeskPvr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIfrmClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmClientesActionPerformed
        IfrmClientes clientes = new IfrmClientes(this, lblEstatus, Tipo, IdUsuario);
        this.DeskPvr.add(clientes);
        clientes.show();
    }//GEN-LAST:event_btnIfrmClientesActionPerformed

    private void btnIfrmInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmInventarioActionPerformed
        IfrmProductos ifrmProductos = new IfrmProductos(this, lblEstatus, Tipo, IdUsuario);
        this.DeskPvr.add(ifrmProductos);
        ifrmProductos.show();
    }//GEN-LAST:event_btnIfrmInventarioActionPerformed

    private void btnIfrmVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmVentaActionPerformed
        IfrmVenta ifrmVenta = new IfrmVenta(this, lblEstatus, Tipo, IdUsuario);
        this.DeskPvr.add(ifrmVenta);
        ifrmVenta.show();
    }//GEN-LAST:event_btnIfrmVentaActionPerformed

    private void btnIfrmApartadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmApartadoActionPerformed
        IfrmApartados ifrmApartados = new IfrmApartados(this, lblEstatus, Tipo, IdUsuario);
        this.DeskPvr.add(ifrmApartados);
        ifrmApartados.show();
    }//GEN-LAST:event_btnIfrmApartadoActionPerformed

    private void btnIfrmInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIfrmInformesActionPerformed
        IfrmInformes ifrmInformes = new IfrmInformes(this);
        this.DeskPvr.add(ifrmInformes);
        ifrmInformes.show();
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setTitle("Pvr - " + Tipo);
        this.toFront();
        if ("Vendedor".equals(Tipo)) {
            this.btnIfrmInformes.setVisible(false);
            this.btnIfrmInventario.setVisible(false);
        }
//        this.pvr = new Pvr(btnIfrmApartado, btnIfrmClientes, btnIfrmInventario, btnIfrmVenta, btnIfrmInformes, btnDfrmPerfil, Tipo);
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(FrmPvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPvr2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DeskPvr;
    private javax.swing.JButton btnDfrmPerfil;
    private javax.swing.JButton btnIfrmApartado;
    private javax.swing.JButton btnIfrmClientes;
    private javax.swing.JButton btnIfrmInformes;
    private javax.swing.JButton btnIfrmInventario;
    private javax.swing.JButton btnIfrmVenta;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblEstatus;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JDesktopPane getDeskPvr() {
        return DeskPvr;
    }

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
