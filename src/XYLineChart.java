import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYLineChart extends JFrame {

	//Constructor for XYLineChart
    public XYLineChart(ArrayList<Long> iterative, ArrayList<Long> recursive) {
        super("XY Line Chart Example with JFreechart");
 
        JPanel chartPanel = createChartPanel(iterative, recursive);
        add(chartPanel, BorderLayout.CENTER);
        
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    
    }//end constructor
 
    //Calls create dataset and uses that dataset to create a chart
    private JPanel createChartPanel(ArrayList<Long> iterative, ArrayList<Long> recursive) {
    	//Set the variables for chart parameters
    	String chartTitle = "Runtime of Iterative and Recursive Fibonacci Sequences";
        String xAxisLabel = "Number of Terms";
        String yAxisLabel = "Nanoseconds";
        
        //Create dataset with method to use for chart
        XYDataset dataset = createDataset(iterative, recursive);
        
        //Create Chart
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);
        
        //Chart Customization
        XYPlot plot = chart.getXYPlot();
        //Set line thickness
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(4.0f));
        plot.setRenderer(renderer);
        
        return new ChartPanel(chart);
    }//end createChartPanel
 
    //Uses arrayLists to make datasets that will be lines in chart
    private XYDataset createDataset(ArrayList<Long> iterative, ArrayList<Long> recursive) {
    	//Declare Dataset and series for each line, and counter to use as the term number in the list
    	XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries iterativeSeries = new XYSeries("Iterative Terms");
        XYSeries recursiveSeries = new XYSeries("Recursive Terms");
    	int count = 1;
    	
    	//Put Run times of iterative in series
    	for(Long runTime: iterative) {
    		iterativeSeries.add(count, runTime.longValue());
    		count++;
    	}//end for loop
    	
    	//reset counter
    	count = 1;
    	
    	//Put Run times of recursive in series
    	for(Long runTime: recursive) {
    		recursiveSeries.add(count, runTime.longValue());
    		count++;
    	}//end for loop
    	
    	//Add both series to dataset
    	dataset.addSeries(iterativeSeries);
        dataset.addSeries(recursiveSeries);	
        
        return dataset;
        
    }//end createDataset
    
}//end XYLineChart Object
