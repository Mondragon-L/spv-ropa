package pvr;

import mx.venado.pvr.seguridad.Perfil;

public class DfrmPerfil extends javax.swing.JDialog {

    private int IdUsuario = -1;
    private String Tipo = "STD";
    private String Usuario = "NA";
    
    private boolean datEdit = false;
    
    Perfil perfil;
    
    public DfrmPerfil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configForm();
    }
    
    private void configForm() {
        this.setTitle("Perfil");
        this.setLocationRelativeTo(null);
    }
    
    public void Init(int Id, String Usuario, String Tipo) {
        this.IdUsuario = Id;
        this.Usuario = Usuario;
        this.Tipo = Tipo;
        perfil = new Perfil(this, Id, Usuario, Tipo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnEditUsuario = new javax.swing.JButton();
        btnEditCon = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pwdContraseniaActual = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        pwdContraseniaNueva = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pwdRepContraseniaNueva = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnEditUsuario.setText("Editar usuario");

        btnEditCon.setText("Editar contraseña");

        jLabel1.setText("Usuario");

        txtUsuario.setEnabled(false);

        jLabel2.setText("Contraseña actual");

        pwdContraseniaActual.setEnabled(false);

        jLabel3.setText("Contraseña nueva");

        pwdContraseniaNueva.setEnabled(false);

        btnAceptar.setText("Aceptar");

        jLabel4.setText("Repetir contraseña nueva");

        pwdRepContraseniaNueva.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pwdRepContraseniaNueva)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwdContraseniaActual)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwdContraseniaNueva)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEditUsuario)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditCon))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditUsuario)
                    .addComponent(btnEditCon))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(pwdContraseniaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(pwdContraseniaNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(pwdRepContraseniaNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.datEdit = perfil.isEditado();
        evt.getWindow().dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(DfrmPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DfrmPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DfrmPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DfrmPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DfrmPerfil dialog = new DfrmPerfil(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEditCon;
    private javax.swing.JButton btnEditUsuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pwdContraseniaActual;
    private javax.swing.JPasswordField pwdContraseniaNueva;
    private javax.swing.JPasswordField pwdRepContraseniaNueva;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getBtnAceptar() {
        return btnAceptar;
    }

    public javax.swing.JButton getBtnEditCon() {
        return btnEditCon;
    }

    public javax.swing.JButton getBtnEditUsuario() {
        return btnEditUsuario;
    }

    public javax.swing.JPasswordField getPwdContraseniaActual() {
        return pwdContraseniaActual;
    }

    public javax.swing.JPasswordField getPwdContraseniaNueva() {
        return pwdContraseniaNueva;
    }

    public javax.swing.JPasswordField getPwdRepContraseniaNueva() {
        return pwdRepContraseniaNueva;
    }

    public javax.swing.JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public boolean isDatEdit() {
        return datEdit;
    }
    
}
