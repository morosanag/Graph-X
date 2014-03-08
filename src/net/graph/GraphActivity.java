package net.graph;


import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;

public class GraphActivity extends Activity {
	private XYMultipleSeriesDataset dataset;
	private GraphicalView graphicalView;
	//private int nr;
	private Handler handler;
	private Runnable updateRunnable;
	private static List<double[]> yValues ;
	private static List<double[]> xValues;
	private static int [] colors;
	private static String[] titles;
	private static String[] texts;
	private static PointStyle[] styles;
	
	
	public double [] getAxes(double xMins, double xMaxs)
	{
		double [] results =  new double[2];
		double middle = (xMins+xMaxs)/2;
		results[0]=middle-6;
		results[1]=middle+6;
		return results;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		xValues = new ArrayList<double[]>(); 
		yValues = new ArrayList<double[]>();

		xValues.clear(); 
		yValues.clear();

		try{
			updateRunnable = new Runnable() {

				@Override
				public void run() 
				{
					graphicalView.repaint();
					//if (addX < 20) handler.postDelayed(updateRunnable, 1);
				}
			};
			handler = new Handler();
			colors = getColors();
			texts= getTexts();
			titles  = getTitles();
			String [] textsFirst;
			textsFirst=texts.clone();
			
			int n=10*GraphpanActivity.progressChanged+1;
			double xcoords[][] = new double[n][n];
			double ycoords[][] = new double[n][n]; 

			int nnn = titles.length;
			double xMins[] = new double[nnn];
			double xMaxs[] = new double[nnn];
			for(int i=0;i<nnn;i++)
			{
				xMins[i]=Double.parseDouble(Evaluator.evaluate(GraphpanActivity.text1[i].getText()+""));
				xMaxs[i]=Double.parseDouble(Evaluator.evaluate(GraphpanActivity.text2[i].getText()+""));
			}

			for (int i = 0; i < nnn; i++)    
			{ 
				ycoords[i] = new double[n-1];
				xcoords[i] = new double[n-1];
				double dif = (xMaxs[i]-xMins[i])/n;
				int j=0;
				int k=1;
				while(k<n)
				{
					xcoords[i][j]=xMins[i]+k*dif;
					try
					{
						double resul = Double.parseDouble(Evaluator.evaluate(textsFirst[i].replaceAll("x", " ( "+xcoords[i][j]+" ) ")));
						ycoords[i][j] = resul;
					}
					catch(Exception ex)
					{
						j--;
					}
					j++;
					k++;
				}
				splitter(xcoords[i],ycoords[i]);
				if(j<=2)
				{
					xValues.remove(xValues.size()-1);
					yValues.remove(yValues.size()-1);
				}
			}


			styles = new PointStyle[xValues.size()];
			for(int i=0;i<styles.length;i++) 
			{ 
				styles[i]=PointStyle.POINT; 
			}

			XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);


			int length = renderer.getSeriesRendererCount();
			for (int i = 0; i < length; i++)
			{
				((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
				.setFillPoints(true);
			}
			double axes[] = getAxes(xMins[0],xMaxs[0]);
			setChartSettings(renderer, "Graphs", "Ox axis",
					"Oy axis", axes[0], axes[1], -5, 14.5, Color.LTGRAY, Color.LTGRAY);

			renderer.setXLabels(12);
			renderer.setYLabels(10);
			renderer.setShowGrid(true);
			renderer.setXLabelsAlign(Align.RIGHT);
			renderer.setYLabelsAlign(Align.RIGHT);
			renderer.setZoomButtonsVisible(true);
			renderer.setPanLimits(new double[] { GraphpanActivity.limits[0], GraphpanActivity.limits[1],GraphpanActivity.limits[2],GraphpanActivity.limits[3]});
			renderer.setZoomLimits(new double[] {  GraphpanActivity.limits[0], GraphpanActivity.limits[1],GraphpanActivity.limits[2],GraphpanActivity.limits[3] });

			dataset = buildDataset(titles, xValues, yValues);

			graphicalView = ChartFactory.getCubeLineChartView(getApplicationContext(), dataset, renderer,(float) 0.5);


			setContentView(graphicalView);
			handler.postDelayed(updateRunnable, 1000);

		}
		catch(Exception ex)
		{
			finish();
		}
	}

	public static int[] add(int[] myArray, int pos, int n)
	{
		int [] aux =  new int [myArray.length+1];
		for(int i=0;i<pos;i++)
		{
			aux[i]=myArray[i];
		}
		aux[pos]=n;
		for(int i=pos+1;i<aux.length;i++)
		{
			aux[i]=myArray[i-1];
		}
		return aux;
	}

	public static final String[] add(String[] myArray, int pos, String n)
	{
		String [] aux =  new String [myArray.length+1];
		for(int i=0;i<pos;i++)
		{
			aux[i]=myArray[i];
		}
		aux[pos]=n;
		for(int i=pos+1;i<aux.length;i++)
		{
			aux[i]=myArray[i-1];
		}
		return aux;
	}

	public static PointStyle[] add(PointStyle[] myArray, int pos,PointStyle n)
	{
		PointStyle[] aux =  new PointStyle [myArray.length+1];
		for(int i=0;i<pos;i++)
		{
			aux[i]=myArray[i];
		}
		aux[pos]=n;
		for(int i=pos+1;i<aux.length;i++)
		{
			aux[i]=myArray[i-1];
		}
		return aux;
	}

	public static void splitter(double [] x, double [] y)
	{
		int k=0;
		int i,j;
		for(i=0;i<y.length-3;i++)
		{
			if( (y[i]<=y[i+1] && y[i+1]>y[i+2] && y[i+2]<=y[i+3]) ||
					(y[i]==y[i+1] && y[i+1]!=y[i+2] && y[i+2]==y[i+3]) ||
					(y[i]>=y[i+1] && y[i+1]<y[i+2] && y[i+2]>=y[i+3]) ||
					Math.abs(x[i+1]-x[i+2])>0.5)
			{
				yValues.remove(y);
				xValues.remove(x); 
				double [] yy = new double [i+2-k];     
				double [] xx = new double [i+2-k];
				for(j=k;j<i+2;j++)
				{
					yy[j-k]=y[j];
					xx[j-k]=x[j];
				} 
				yValues.add(yy);
				xValues.add(xx);
				k=i+2;
				colors = add(colors,xValues.size()-1,colors[xValues.size()-1]); 
				titles = add(titles,xValues.size()-1,titles[xValues.size()-1]);
				texts= add(texts,xValues.size()-1,texts[xValues.size()-1]);
			}
		} 
		double [] yy = new double [y.length-k];
		double [] xx = new double [x.length-k];
		for(j=k;j<y.length;j++)
		{
			yy[j-k]=y[j];
			xx[j-k]=x[j];
		}
		yValues.add(yy);
		xValues.add(xx);
	}

	private String[] getTitles()
	{
		String title[] =  new String[GraphpanActivity.k];
		for(int i=0;i<GraphpanActivity.k;i++)
		{
			String Text = GraphpanActivity.spinner[i].getSelectedItem().toString();
			title[i]=Text;
		}
		return title;
	}
	private int [] getColors()
	{
		int colors[] =  new int[GraphpanActivity.k];
		for(int i=0;i<GraphpanActivity.k;i++)
		{
			String Text = GraphpanActivity.spinner2[i].getSelectedItem().toString();
			int col=colorParseNumber(Text);
			//Log.d("Colors",col+"");
			colors[i]=GraphpanActivity.colors[col];
		}
		return colors;
	}

	private String [] getTexts()
	{
		String texts[] =  new String[GraphpanActivity.k];
		for(int i=0;i<GraphpanActivity.k;i++) 
		{   
			texts[i]=GraphpanActivity.spinner[i].getSelectedItem().toString();
		}
		return texts;
	}

	private int colorParseNumber(String color)
	{
		for(int i=0;i<GraphpanActivity.colorsS.length;i++)
		{
			if(color.compareTo(GraphpanActivity.colorsS[i])==0)
			{
				return i;
			}
		}
		return -1;
	}
	private XYMultipleSeriesDataset buildDataset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
	}

	private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) 
	{
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i], scale);
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) 
			{
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
	}

	private XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles,titles);
		return renderer;
	}

	private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles,  String [] titles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15); 
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 10, 30, 15, 20 });
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			r.setLineWidth(2f);
			if(i>0)
			{
				if(titles[i].compareTo(titles[i-1])==0 &&
						colors[i]==colors[i-1])
				{
					r.setShowLegendItem(false);
				}
			}
			renderer.addSeriesRenderer(r);

		}
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);   
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);   
		renderer.setLabelsColor(labelsColor);
	}
}