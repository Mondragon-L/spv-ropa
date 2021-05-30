package mx.venado.pvr.utilidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Luis Antonio
 */
public class Fecha {

    String dia, mes, anio, nuevaFecha, hora, minuto, segundo, AMPM;
    Calendar fecha = Calendar.getInstance();

    public String getAnioActual() {
        return Integer.toString(fecha.get(Calendar.YEAR));
    }

    public String getMesActual() {
        return ((fecha.get(Calendar.MONTH) + 1) < 10)
                ? ("0" + (fecha.get(Calendar.MONTH) + 1))
                : Integer.toString(fecha.get(Calendar.MONTH) + 1);
    }

    public String getDiaActual() {
        return ((fecha.get(Calendar.DAY_OF_MONTH)) < 10)
                ? ("0" + (fecha.get(Calendar.DAY_OF_MONTH)))
                : Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
    }

    public String getFecha() {
        dia = getDiaActual();
        mes = getMesActual();
        anio = getAnioActual();
        nuevaFecha = dia + "/" + mes + "/" + anio;
        return nuevaFecha;
    }

    public String getHora() {
        AMPM = (fecha.get(Calendar.AM_PM) == Calendar.AM) ? "am" : "pm";
        if (fecha.get(Calendar.HOUR) < 10) {
            if (fecha.get(Calendar.HOUR) == 0) {
                hora = Integer.toString(12);
            } else {
                hora = "0" + fecha.get(Calendar.HOUR);
            }
        } else {
            hora = Integer.toString(fecha.get(Calendar.HOUR));
        }
        minuto = (fecha.get(Calendar.MINUTE) < 10) ? ("0" + fecha.get(Calendar.MINUTE)) : Integer.toString(fecha.get(Calendar.MINUTE));
        segundo = (fecha.get(Calendar.SECOND) < 10) ? ("0" + fecha.get(Calendar.SECOND)) : Integer.toString(fecha.get(Calendar.SECOND));

        return hora + ":" + minuto + ":" + segundo + " " + AMPM;
    }

    public String createCodigo() {
        int rd = new Random().nextInt(999999);
        dia = getDiaActual();
        mes = getMesActual();
        anio = getAnioActual();
        if (fecha.get(Calendar.HOUR) < 10) {
            hora = (fecha.get(Calendar.HOUR) == 0) ? Integer.toString(12) : "0" + fecha.get(Calendar.HOUR);
        } else {
            hora = Integer.toString(fecha.get(Calendar.HOUR));
        }
        minuto = (fecha.get(Calendar.MINUTE) < 10) ? ("0" + fecha.get(Calendar.MINUTE)) : Integer.toString(fecha.get(Calendar.MINUTE));
        segundo = (fecha.get(Calendar.SECOND) < 10) ? ("0" + fecha.get(Calendar.SECOND)) : Integer.toString(fecha.get(Calendar.SECOND));

        return ("AZH-" + anio + mes + dia + "-" + hora + minuto + segundo + "-" + rd);
    }

    /**
     * Número de días en un rango de fechas.
     *
     * @param r1 Primer rango
     * @param r2 Segundo rango
     * @return Número de días en caso de éxito, -1 en caso de error
     */
    public static int getNumeroDias(String r1, String r2) {
        try {
            SimpleDateFormat sfecha = new SimpleDateFormat("dd/MM/yyyy");
            Date f1 = sfecha.parse(r1);
            Date f2 = sfecha.parse(r2);
            long inicio = f1.getTime();
            long fin = f2.getTime();
            long diferencia = fin - inicio;
            return (int) TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
//            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static String monthYearToText(String d) {
        String[] meses = {null, "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String[] fec = d.split("/");
        return meses[Integer.parseInt(fec[1])] + "/" + Integer.parseInt(fec[2]);
    }

}
