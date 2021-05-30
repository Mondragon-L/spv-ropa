package mx.venado.pvr.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

public class PropBd {

    Properties properties;
    File file = new File("d5ee52/l1.properties");
    File directory = new File("d5ee52");
    FileInputStream fis;
    FileOutputStream fos;
    
    private final ArrayList<PropiedadVo> propiedades;

    public PropBd() {
        properties = new Properties();
        propiedades = new ArrayList<>();
    }

    public void cargar() throws FileNotFoundException, IOException {
        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            properties.put("bd", "");
            properties.put("user", "");
            properties.put("password", "");
            properties.put("url", "");
            fos = new FileOutputStream(file);
            properties.store(fos, "");
            throw new IOException("Error al cargar credenciales");
        } else {
            fis = new FileInputStream(file);
            properties.load(fis);
        }
    }
    
    public ArrayList<PropiedadVo> list() {
        for (Enumeration i = properties.keys(); i.hasMoreElements();) {
            Object o = i.nextElement();
            PropiedadVo propiedadVo = new PropiedadVo();
            propiedadVo.setClave(o.toString());
            propiedadVo.setValor(properties.getProperty(o.toString()));
            propiedades.add(propiedadVo);
        }
        
        return propiedades;
    }
    
    public String get(String key, String defaultValue) throws IOException {
        String value = properties.getProperty(key, defaultValue);
        if (defaultValue == null && "".equals(value)) {
            throw new IOException("Error al cargar credenciales");
        }
        return value;
    }

}
