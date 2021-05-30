package pvr;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import mx.venado.pvr.controlador.PvrApartado;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class IfrmApartados extends javax.swing.JInternalFrame {

    PvrApartado apartado;
    
    public IfrmApartados(JFrame parent, JLabel lblEstatus, String tipo, int idusuario) {
        initComponents();
        ConfigForm(parent, lblEstatus, tipo, idusuario);
    }
    
    private void ConfigForm(JFrame parent, JLabel lblEstatus, String tipo, int idusuario){
        apartado = new PvrApartado(parent, this, lblEstatus, tipo, idusuario, lblArtApartados, lblCorreoCliente, 
                lblIdCliente, lblNombreCliente, lblTelefonoCliente, lblTotal, 
                tblApartados, txtFiltro, tblApartadosClientes, lblPagado, lblIdApartado, 
                btnAgregarApartado, lblResta, btnCambiar, btnRegresar, btnDescuento);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblApartados = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblIdApartado = new javax.swing.JLabel();
        lblArtApartados = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblPagado = new javax.swing.JLabel();
        lblResta = new javax.swing.JLabel();
        btnAgregarApartado = new javax.swing.JButton();
        btnCambiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnDescuento = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        lblIdCliente = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        lblTelefonoCliente = new javax.swing.JLabel();
        lblCorreoCliente = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblApartadosClientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Apartados");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos apartados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        jScrollPane3.setAutoscrolls(true);

        tblApartados.setFont(new FuenteUbuntu().AplicarRegularFont(15));
        tblApartados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblApartados.setToolTipText("");
        tblApartados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblApartados.setSelectionBackground(new java.awt.Color(245, 245, 245));
        tblApartados.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblApartados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tblApartados);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        lblIdApartado.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblIdApartado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdApartado.setText("-");
        lblIdApartado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Id de apartado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel5.add(lblIdApartado);

        lblArtApartados.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblArtApartados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtApartados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Articulos apartados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel5.add(lblArtApartados);

        lblTotal.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel5.add(lblTotal);

        lblPagado.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblPagado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagado.setText("-");
        lblPagado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Pagado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel5.add(lblPagado);

        lblResta.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblResta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResta.setText("-");
        lblResta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3), "Resta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15), new java.awt.Color(153, 0, 0)));
        jPanel5.add(lblResta);

        btnAgregarApartado.setIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen("/mx/venado/pvr/multimedia/Add.png", 60, 60));
        btnAgregarApartado.setToolTipText("Agregar nuevo apartado");
        btnAgregarApartado.setBorder(null);
        btnAgregarApartado.setBorderPainted(false);
        btnAgregarApartado.setContentAreaFilled(false);
        btnAgregarApartado.setPressedIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen("/mx/venado/pvr/multimedia/AddPressRoll.png", 50, 50));
        btnAgregarApartado.setRolloverIcon(new mx.venado.pvr.utilidades.UtilidadesPvr().RedimencionarImagen("/mx/venado/pvr/multimedia/AddPressRoll.png", 60, 60));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarApartado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarApartado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        btnCambiar.setBackground(new java.awt.Color(0, 0, 0));
        btnCambiar.setBorder(null);

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setToolTipText("<html>\n Para regresar el producto al inventario es necesario:<br>\n *  No haber sido liquidado.<br>\n</html>");
        btnRegresar.setBorder(null);

        btnDescuento.setBackground(new java.awt.Color(0, 0, 0));
        btnDescuento.setToolTipText("<html> \n Para realizar un descuento es necesario:<br>\n  *  No tener descuentos previos.<br>\n  *  No tener abonos registrados previamente.<br>\n  *  No haber sido liquidado.<br>\n</html>");
        btnDescuento.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        txtFiltro.setBackground(new Color(0,0,0,0));
        txtFiltro.setFont(new FuenteUbuntu().AplicarLightFont());
        txtFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        lblIdCliente.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblIdCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        lblNombreCliente.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblNombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Nombre", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        lblTelefonoCliente.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblTelefonoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTelefonoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Teléfono", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        lblCorreoCliente.setFont(new FuenteUbuntu().AplicarLightFont(18, Font.BOLD));
        lblCorreoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCorreoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Correo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new FuenteUbuntu().AplicarRegularFont(15)));

        tblApartadosClientes.setFont(new FuenteUbuntu().AplicarRegularFont(15));
        tblApartadosClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblApartadosClientes.setToolTipText("");
        tblApartadosClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblApartadosClientes.setSelectionBackground(new java.awt.Color(245, 245, 245));
        tblApartadosClientes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tblApartadosClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarApartado;
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnDescuento;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblArtApartados;
    private javax.swing.JLabel lblCorreoCliente;
    private javax.swing.JLabel lblIdApartado;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblPagado;
    private javax.swing.JLabel lblResta;
    private javax.swing.JLabel lblTelefonoCliente;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblApartados;
    private javax.swing.JTable tblApartadosClientes;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
