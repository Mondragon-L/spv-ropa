/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.venado.pvr.utilidades;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Luis Antonio
 */
public class FuenteUbuntu {
    private Font ubuntuRegularFont = null;
    private Font ubuntuLightFont = null;

    public FuenteUbuntu() {
        String fuenteRegular = "/ubuntu/Ubuntu-Regular.ttf";
        String fuenteLight = "/ubuntu/Ubuntu-Light.ttf";
        try {
            InputStream isRegular = getClass().getResourceAsStream(fuenteRegular);
            InputStream isLight = getClass().getResourceAsStream(fuenteLight);
            
            ubuntuRegularFont = Font.createFont(Font.TRUETYPE_FONT, isRegular);
            ubuntuLightFont = Font.createFont(Font.TRUETYPE_FONT, isLight);
        } catch (FontFormatException | IOException e) {
            ubuntuRegularFont = new Font("Tahoma", 1, 14);
            ubuntuLightFont = new Font("Tahoma", 1, 14);
        }
    }
    
    public Font AplicarRegularFont(){
        return ubuntuRegularFont.deriveFont(Font.PLAIN, 14);
    }
    
    public Font AplicarLightFont(){
        return ubuntuLightFont.deriveFont(Font.BOLD, 14);
    }
    
    public Font AplicarRegularFont(float tamanio){
        return ubuntuRegularFont.deriveFont(Font.PLAIN, tamanio);
    }
    
    public Font AplicarLightFont(float tamanio){
        return ubuntuLightFont.deriveFont(Font.BOLD, tamanio);
    }
    
    public Font AplicarRegularFont(float tamanio, int estilo){
        return ubuntuRegularFont.deriveFont(estilo, tamanio);
    }
    
    public Font AplicarLightFont(float tamanio, int estilo){
        return ubuntuLightFont.deriveFont(estilo, tamanio);
    }
    
}
