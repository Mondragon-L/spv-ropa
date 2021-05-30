package mx.venado.pvr.controlador;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import mx.venado.pvr.utilidades.PropBd;
import mx.venado.pvr.utilidades.PropSistema;
import mx.venado.pvr.utilidades.PropiedadVo;
import mx.venado.pvr.vista.ModeloConfiguracion;
import mx.venado.pvr.vista.TableCellRendererConfig;
import pvr.DfrmNucleo;

public class PvrConfiguracion {

    DfrmNucleo dfrmNucleo;
    ModeloConfiguracion modeloSistema;
    ModeloConfiguracion modeloBd;
    PropSistema propSistema;
    PropBd propBd;

    public PvrConfiguracion(DfrmNucleo dfrmNucleo) {
        this.dfrmNucleo = dfrmNucleo;
        configForm();
    }

    private void configForm() {
        this.dfrmNucleo.setTitle("Configuraciones");
        this.dfrmNucleo.setLocationRelativeTo(null);
        
        this.propSistema = new PropSistema();
        this.propBd = new PropBd();
        
        this.modeloSistema = new ModeloConfiguracion();
        this.modeloBd = new ModeloConfiguracion();
        
        this.tblSistema = this.dfrmNucleo.getTblSistema();
        this.tblBd = this.dfrmNucleo.getTblBd();
        
        this.tblSistema.setDefaultRenderer(Object.class, new TableCellRendererConfig());
        this.tblSistema.setModel(modeloSistema);
        
        this.tblBd.setDefaultRenderer(Object.class, new TableCellRendererConfig());
        this.tblBd.setModel(modeloBd);
        
        leerConfigSistema();
        leerConfigBd();
    }
    
    private void leerConfigSistema() {
        try {
            propSistema.cargar();
            for (PropiedadVo propiedadVo : propSistema.list()) {
                modeloSistema.addRow(new Object[] {propiedadVo.getClave(), propiedadVo.getValor()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dfrmNucleo, e.getMessage());
        }
    }
    
    private void leerConfigBd() {
        try {
            propBd.cargar();
            for (PropiedadVo propiedadVo : propBd.list()) {
                Object[] objects = new Object[2];
                objects[0] = propiedadVo.getClave();
                objects[1] = propiedadVo.getValor();
                
                if ("password".equals(propiedadVo.getClave())) {
                    JPasswordField field = new JPasswordField(propiedadVo.getValor().toString());
                    field.setEchoChar('X');
                    objects[1] = field;
                }
                
                modeloBd.addRow(objects);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dfrmNucleo, e.getMessage());
        }
    }
    
    private JTable tblBd;
    private JTable tblSistema;
}
