
package pvr;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import mx.venado.pvr.seguridad.Login;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.PropSistema;

public class DfrmSesion extends javax.swing.JDialog {

    Login login;
    private int x, y;
    PropSistema propNora = new PropSistema();

    public DfrmSesion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConfigForm();
    }

    private void ConfigForm() {
        login = new Login(this, btnAceptar, btnCerrar, lblBloqueo, lblTipo, pwdtxtClave, tgbtnAdministrador, tgbtnVendedor);
        DfrmSesion.this.setLocationRelativeTo(null);
        DfrmSesion.this.setAlwaysOnTop(true);
        this.NombreNegocio();
    }

    private void NombreNegocio() {
        String valor;
        try {
            propNora.cargar();
        } catch (IOException e) {
        }
        try {
            valor = propNora.get("name", "NORA");
        } catch (IOException e) {
            valor = "";
        }
        lblNombreNegocio.setText(valor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngpoUsuarios = new javax.swing.ButtonGroup();
        lblBloqueo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tgbtnAdministrador = new javax.swing.JToggleButton();
        tgbtnVendedor = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pwdtxtClave = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblTipo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNombreNegocio = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(405, 350));
        setUndecorated(true);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBloqueo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/Bloqueado.png"))); // NOI18N
        getContentPane().add(lblBloqueo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 130, -1));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btngpoUsuarios.add(tgbtnAdministrador);
        tgbtnAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/sesion/admin.png"))); // NOI18N
        tgbtnAdministrador.setText("Administrador");
        tgbtnAdministrador.setFocusable(false);
        tgbtnAdministrador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnAdministrador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(tgbtnAdministrador);

        btngpoUsuarios.add(tgbtnVendedor);
        tgbtnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/sesion/vendedor.png"))); // NOI18N
        tgbtnVendedor.setText("Vendedor");
        tgbtnVendedor.setFocusable(false);
        tgbtnVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgbtnVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(tgbtnVendedor);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 220, 100));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/sesion/llave.png"))); // NOI18N

        pwdtxtClave.setBackground(new Color(0,0,0,0));
        pwdtxtClave.setFont(new FuenteUbuntu().AplicarLightFont(25));
        pwdtxtClave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pwdtxtClave.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(pwdtxtClave, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(pwdtxtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 220, -1));

        btnAceptar.setBackground(ConstantesPvr.SUCCESS);
        btnAceptar.setFont(new FuenteUbuntu().AplicarRegularFont(20, Font.BOLD));
        btnAceptar.setForeground(ConstantesPvr.DEFAULT);
        btnAceptar.setText("ACEPTAR");
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 220, 50));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/CerrarIcon.png"))); // NOI18N
        btnCerrar.setToolTipText("Cerrar");
        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/leicasoluciones/nora/multimedia/CerrarPressIcon.png"))); // NOI18N
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        lblTipo.setFont(new FuenteUbuntu().AplicarRegularFont(10));
        lblTipo.setText("xxxxxxxxxxx");
        getContentPane().add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 130, -1));

        jLabel4.setFont(new FuenteUbuntu().AplicarLightFont(25));
        jLabel4.setText("NORA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));

        lblNombreNegocio.setFont(new FuenteUbuntu().AplicarRegularFont(12));
        lblNombreNegocio.setText("BOUT.LUNA");
        getContentPane().add(lblNombreNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 130, -1));

        jLabel6.setFont(new FuenteUbuntu().AplicarRegularFont(14));
        jLabel6.setText("Ingresar como:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 130, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        this.x = evt.getX();
        this.y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point posicion = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(posicion.x - x, posicion.y - y);
    }//GEN-LAST:event_formMouseDragged

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DfrmSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DfrmSesion dialog = new DfrmSesion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.ButtonGroup btngpoUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBloqueo;
    private javax.swing.JLabel lblNombreNegocio;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPasswordField pwdtxtClave;
    private javax.swing.JToggleButton tgbtnAdministrador;
    private javax.swing.JToggleButton tgbtnVendedor;
    // End of variables declaration//GEN-END:variables
}
