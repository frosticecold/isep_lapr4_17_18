package pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 *
 * Factory pattern
 *
 * Singleton Pattern
 *
 * Context of strategy pattern of chart
 */
public class ChartFactory {

    private static final ChartFactory instance = new ChartFactory();
    private Chart strategy;

    private ChartFactory() {

    }

    public static ChartFactory getInstance() {

        return instance;
    }

    /**
     * Build a object Chart based on the strategy pattern
     *
     * @param type
     * @param name
     * @param wantsLabel
     * @return
     */
    public Chart createChart(ChartType type, String name, boolean wantsLabel) {

        Chart chart;

        if (type.compareTo(ChartType.BARCHART) == 0) {
            chart = buildBarChart(name, wantsLabel);
        } else if (type.compareTo(ChartType.PIECHART) == 0) {
            chart = buildPieChart(name, wantsLabel);
        } else {
            chart = null;
        }

        return chart;
    }

    /**
     * Returns a BarChart
     *
     * @param name
     * @param wantsLabel
     * @return
     */
    private BarChart buildBarChart(String name, boolean wantsLabel) {

        return new BarChart(name, wantsLabel);
    }

    /**
     * Returns a PieChart
     *
     * @param name
     * @return
     */
    private PieChart buildPieChart(String name, boolean wantsLabel) {

        return new PieChart(name, wantsLabel);
    }

}
