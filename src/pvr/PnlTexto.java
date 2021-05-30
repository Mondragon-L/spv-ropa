package pvr;

import java.awt.Color;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class PnlTexto extends javax.swing.JPanel {

    public PnlTexto() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrllpnTxt = new javax.swing.JScrollPane();
        txtDato = new javax.swing.JTextArea();

        scrllpnTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarLightFont(15)));

        txtDato.setBackground(new Color(0,0,0,0));
        txtDato.setColumns(20);
        txtDato.setFont(new FuenteUbuntu().AplicarLightFont());
        txtDato.setLineWrap(true);
        txtDato.setRows(5);
        txtDato.setWrapStyleWord(true);
        scrllpnTxt.setViewportView(txtDato);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrllpnTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrllpnTxt)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getValue() {
        return txtDato.getText();
    }
    
    public void setValue(String val) {
        txtDato.setText(val);
        txtDato.selectAll();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrllpnTxt;
    private javax.swing.JTextArea txtDato;
    // End of variables declaration//GEN-END:variables
}
