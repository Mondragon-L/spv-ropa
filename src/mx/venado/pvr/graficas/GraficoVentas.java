package mx.venado.pvr.graficas;

import java.awt.Rectangle;
import java.util.ArrayList;
import mx.venado.pvr.modelo.vo.VentasInformesVo;
import mx.venado.pvr.utilidades.Fecha;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoVentas {

    public static final int POR_ANIO = 2;
    public static final int POR_MES = 1;
    public static final int POR_DIA = 0;
    
    private int filtro;

    DefaultCategoryDataset dataset;
    JFreeChart chart;
    
    private ArrayList<VentasInformesVo> listVentas;

    public GraficoVentas() {
        dataset = new DefaultCategoryDataset();
    }

    private void porDia(ArrayList<VentasInformesVo> listVentas) {
        dataset.clear();
        String fecha = "";
        double total = 0;
        VentasInformesVo informesVo;
        for (int i = 0; i < listVentas.size(); i++) {
            informesVo = listVentas.get(i);
            if (fecha.isEmpty()) {
                fecha = informesVo.getFecha();
                total += informesVo.getTotal();

            } else if (!fecha.equals(informesVo.getFecha())) {
                //System.out.println("fecha: " + fecha + " -- total: " + total);
                dataset.setValue(total, "$ Total", fecha);
                fecha = informesVo.getFecha();
                total = informesVo.getTotal();
            } else {
                total += informesVo.getTotal();
            }

            if (i == (listVentas.size() - 1)) {
                dataset.setValue(total, "$ Total", fecha);
            }
        }
    }

    private void porMes(ArrayList<VentasInformesVo> listVentas) {
        dataset.clear();
        String mes = "";
        double total = 0;
        VentasInformesVo informesVo;
        for (int i = 0; i < listVentas.size(); i++) {
            informesVo = listVentas.get(i);
            if (mes.isEmpty()) {
                mes = Fecha.monthYearToText(informesVo.getFecha());
                total += informesVo.getTotal();

            } else if (!mes.equals(Fecha.monthYearToText(informesVo.getFecha()))) {
//                System.out.println("fecha: " + mes + " -- total: " + total);
                dataset.setValue(total, "$ Total", mes);
                mes = Fecha.monthYearToText(informesVo.getFecha());
                total = informesVo.getTotal();
            } else {
                total += informesVo.getTotal();
            }

            if (i == (listVentas.size() - 1)) {
                dataset.setValue(total, "$ Total", mes);
            }
        }
    }

    private void porAnio(ArrayList<VentasInformesVo> listVentas) {
        dataset.clear();
        String anio = "";
        double total = 0;
        VentasInformesVo informesVo;
        for (int i = 0; i < listVentas.size(); i++) {
            informesVo = listVentas.get(i);
            if (anio.isEmpty()) {
                anio = informesVo.getFecha().substring(6, 10);
                total += informesVo.getTotal();

            } else if (!anio.equals(informesVo.getFecha().substring(6, 10))) {
//                System.out.println("fecha: " + anio + " -- total: " + total);
                dataset.setValue(total, "$ Total", anio);
                anio = informesVo.getFecha().substring(6, 10);
                total = informesVo.getTotal();
            } else {
                total += informesVo.getTotal();
            }

            if (i == (listVentas.size() - 1)) {
                dataset.setValue(total, "$ Total", anio);
            }
        }
    }

    public void setDatos(ArrayList<VentasInformesVo> listVentas) {
        this.listVentas = listVentas;
    }

    public void crear() {
        String tituloG = "";
        String grupo = "";
        switch (filtro) {
            case POR_ANIO:
                porAnio(listVentas);
                tituloG = "Ventas por año";
                grupo = "Año";
                break;
            case POR_MES:
                porMes(listVentas);
                tituloG = "Ventas por mes";
                grupo = "Mes";
                break;
            case POR_DIA:
                porDia(listVentas);
                tituloG = "Ventas por día";
                grupo = "Fecha";
                break;
            default:
                porDia(listVentas);
        }
        
        chart = ChartFactory.createBarChart(
                tituloG,
                grupo,
                "$",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );
//        chart = ChartFactory.createBarChart3D(title, categoryAxisLabel, valueAxisLabel, dataset)
    }

    public ChartPanel getGrafico() {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(new Rectangle(500, 250));
        return chartPanel;
    }

    public void mostrarGrafico() {
        ChartFrame cf = new ChartFrame("Ventas", chart);
//        cf.setBounds(0,0, 500,500);
        cf.pack();
        cf.setVisible(true);
    }

    public void setFiltro(int filtro) {
        this.filtro = filtro;
    }
}
