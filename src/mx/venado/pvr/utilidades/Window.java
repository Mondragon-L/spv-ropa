
package mx.venado.pvr.utilidades;

import java.util.Random;

public class Window {

    public Window() {
    }
    
    public static String createId(String title) {
        Random r = new Random();
        return (title + r.nextInt(999999));
    }
    
}
