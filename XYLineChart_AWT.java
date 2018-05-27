
import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYLineChart_AWT extends ApplicationFrame {

   public XYLineChart_AWT( String applicationTitle, String Base, String attribute, String chartTitle ,int[] att1,int[] att2,int[] att3,int size ) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         Base ,
         attribute ,
         createDataset(att1,att2,att3,size ) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.BLUE );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
    private XYDataset createDataset(int[] att1,int[] att2, int[] att3,int size) {
        final XYSeries Fitness = new XYSeries( "maxFitness" );                   
        final XYSeries avgFitness = new XYSeries( "avgFitness" ); 
        final XYSeries best10 = new XYSeries( "avgbest10" ); 
        for(int i=0;i<=size;i++)
        {
            Fitness.add(i,att1[i]);
            avgFitness.add(i,att2[i]);
            best10.add(i,att3[i]);      
        }	        
        final XYSeriesCollection dataset = new XYSeriesCollection( );          
        dataset.addSeries( Fitness );          
        dataset.addSeries( avgFitness );
        dataset.addSeries( best10 );
        return dataset;
    }

   public static void Draw( String applicationTitle, String Base, String attribute, String chartTitle, int[] att1,int[] att2,int[] att3 ,int size) {
      XYLineChart_AWT chart = new XYLineChart_AWT(applicationTitle, Base, attribute, chartTitle ,att1,att2,att3,size );
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}
