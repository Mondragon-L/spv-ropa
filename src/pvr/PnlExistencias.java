package pvr;

import java.awt.Color;
import java.awt.Font;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PnlExistencias extends javax.swing.JPanel implements IPvrConstant {

    UtilidadesPvr util = new UtilidadesPvr();
    
    public static final int AGREGAR = 1;
    public static final int DISMINUIR = 0;
    
    private int accion = AGREGAR;
    
    int actuales = 0;
    int valorFinal = 0;
    int agregar = 0;
    
    public PnlExistencias() {
        initComponents();
    }
    
    public void configForm(int existencias) {
        tgbtnAgregar.setIcon(util.RedimencionarImagen(URL_MULTIMEDIA + "Plus.png", 40, 40));
        tgbtnRemover.setIcon(util.RedimencionarImagen(URL_MULTIMEDIA + "Minus.png", 40, 40));
        this.actuales = existencias;
        this.valorFinal = this.actuales;
        lblActuales.setText(Integer.toString(this.actuales));
        lblFinales.setText(Integer.toString(this.valorFinal));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        sldrExistencias = new javax.swing.JSlider();
        tgbtnAgregar = new javax.swing.JToggleButton();
        tgbtnRemover = new javax.swing.JToggleButton();
        lblActuales = new javax.swing.JLabel();
        txtAgregar = new javax.swing.JTextField();
        lblFinales = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        sldrExistencias.setMajorTickSpacing(10);
        sldrExistencias.setMaximum(50);
        sldrExistencias.setMinorTickSpacing(1);
        sldrExistencias.setPaintLabels(true);
        sldrExistencias.setPaintTicks(true);
        sldrExistencias.setValue(0);
        sldrExistencias.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldrExistenciasStateChanged(evt);
            }
        });

        buttonGroup1.add(tgbtnAgregar);
        tgbtnAgregar.setSelected(true);
        tgbtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgbtnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgbtnAgregarMouseExited(evt);
            }
        });
        tgbtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbtnAgregarActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgbtnRemover);
        tgbtnRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgbtnRemoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgbtnRemoverMouseExited(evt);
            }
        });
        tgbtnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbtnRemoverActionPerformed(evt);
            }
        });

        lblActuales.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblActuales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblActuales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Actuales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        txtAgregar.setEditable(false);
        txtAgregar.setBackground(new Color(0,0,0,0));
        txtAgregar.setFont(new FuenteUbuntu().AplicarLightFont());
        txtAgregar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAgregar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Agregar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        lblFinales.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblFinales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFinales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Finales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        jLabel1.setText("Deslice para agregar o disminuir las existencias...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sldrExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tgbtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tgbtnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblActuales, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFinales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tgbtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tgbtnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblActuales, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFinales, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sldrExistencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tgbtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbtnAgregarActionPerformed
        if (tgbtnAgregar.isSelected()) {
            this.accion = AGREGAR;
            txtAgregar.setBorder(
                    javax.swing.BorderFactory.createTitledBorder(
                            javax.swing.BorderFactory.createMatteBorder(
                                    0, 1, 1, 0, 
                                    new java.awt.Color(0, 0, 0)), 
                            "Agregar", 
                            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                            new FuenteUbuntu().AplicarRegularFont()));
        }
    }//GEN-LAST:event_tgbtnAgregarActionPerformed

    private void tgbtnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbtnRemoverActionPerformed
        if (tgbtnRemover.isSelected()) {
            this.accion = DISMINUIR;
            txtAgregar.setBorder(
                    javax.swing.BorderFactory.createTitledBorder(
                            javax.swing.BorderFactory.createMatteBorder(
                                    0, 1, 1, 0, 
                                    new java.awt.Color(0, 0, 0)), 
                            "Disminuir", 
                            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                            new FuenteUbuntu().AplicarRegularFont()));
        }
    }//GEN-LAST:event_tgbtnRemoverActionPerformed

    private void sldrExistenciasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldrExistenciasStateChanged
        this.agregar = sldrExistencias.getValue();
        txtAgregar.setText(Integer.toString(this.agregar));
        this.valorFinal = (this.getAccion() == AGREGAR) ? (this.actuales + this.agregar) : (this.actuales - this.agregar);
        lblFinales.setText(Integer.toString(this.valorFinal));
    }//GEN-LAST:event_sldrExistenciasStateChanged

    private void tgbtnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgbtnAgregarMouseEntered
        jLabel1.setText("Configuración para agregar existencias...");
    }//GEN-LAST:event_tgbtnAgregarMouseEntered

    private void tgbtnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgbtnAgregarMouseExited
        jLabel1.setText("Deslice para agregar o disminuir las existencias...");
    }//GEN-LAST:event_tgbtnAgregarMouseExited

    private void tgbtnRemoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgbtnRemoverMouseEntered
        jLabel1.setText("Configuración para disminuir existencias...");
    }//GEN-LAST:event_tgbtnRemoverMouseEntered

    private void tgbtnRemoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgbtnRemoverMouseExited
        jLabel1.setText("Deslice para agregar o disminuir las existencias...");
    }//GEN-LAST:event_tgbtnRemoverMouseExited

    public double get() {
        return this.valorFinal;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblActuales;
    private javax.swing.JLabel lblFinales;
    private javax.swing.JSlider sldrExistencias;
    private javax.swing.JToggleButton tgbtnAgregar;
    private javax.swing.JToggleButton tgbtnRemover;
    private javax.swing.JTextField txtAgregar;
    // End of variables declaration//GEN-END:variables

    public int getAccion() {
        return accion;
    }
}
