package net.graph;

import net.graph.R;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends Fragment {
	TextView [] text;
	TextView [] mainText;
	ScrollView scroll;
	LinearLayout layoutHelp;
	LinearLayout layouts[];
	int n=28;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.help_activity, container, false);

		layouts = new LinearLayout[4];
		text =  new TextView[n];
		mainText = new TextView[4];
		layouts[0]= (LinearLayout)  layout.findViewById(R.id.mainHelp1);
		layouts[1]= (LinearLayout)  layout.findViewById(R.id.mainHelp2);
		layouts[2]= (LinearLayout)  layout.findViewById(R.id.mainHelp3);
		layouts[3]= (LinearLayout)  layout.findViewById(R.id.mainHelp4);

		mainText[0] = (TextView)  layout.findViewById(R.id.editText1);
		mainText[1] = (TextView)  layout.findViewById(R.id.editText2);
		mainText[2] = (TextView)  layout.findViewById(R.id.editText3);
		mainText[3] = (TextView)  layout.findViewById(R.id.editText4);
		
		for(int i=0;i<4;i++)
		{
			String ttt = mainText[i].getText().toString();
			mainText[i].setText("\u25BA "+ttt);
		}
		
		layoutHelp  =  (LinearLayout)  layout.findViewById(R.id.mainHelp);
		
		scroll = (ScrollView) layout.findViewById(R.id.scrollHelp);

		for(int i=0;i<n;i++)
		{
			text[i]= new TextView(getActivity());
		}

		text[0].setText("Q1: How to delete a character?");
		text[1].setText("A1: Use 'C' key or tap the screen once.");
		text[2].setText("Q2: How to delete all characters?");
		text[3].setText("A2: Long tap on the screen.");
		text[4].setText("Q3: What does Add button do?");
		text[5].setText("A3: It's used to add functions to memory");
		text[6].setText("Q4: What do 'floor' and 'frac' mean?");
		text[7].setText("A4: 'floor' means integer part and 'frac' means factional part.");
		text[8].setText("Q5: 'lg', 'ln', 'lb'?");
		text[9].setText("A5: 'lg' - decimal logarithm \n 'ln' - natural logarithm \n 'lb' - binary logarithm ");
		text[10].setText("Q6: What does X key mean?");
		text[11].setText("A6: X is the variable of a function. Used for graphical purpose only.");
		
		
		text[12].setText("Q1: How to create a graph:");
		text[13].setText("A1: Follow these steps: \n" +
				"1. Write a function in Calculator Panel (could contain x) \n" +
				"2. Go to Graph Panel and press Add button \n" +
				"3. In the new line choose the function you would like to make graph, set the color" +
				" and set the limits of it: (a,b)" );
		text[14].setText("Q2: How can I close the keyboard?");
		text[15].setText("A2: Swipe it down.");
		text[16].setText("Q3: What does precision mean?");
		text[17].setText("A3: Precision represents the number of points which are plotted. " +
				"It is recommended a low precision for first and second degree functions and " +
				"a higher precision for more complex functions (tan, ctan)");
		text[18].setText("Q4: What does Del button do?");
		text[19].setText("A4: Del is used to delete the last added function.");
		text[20].setText("Q5: What should I do when Generate Graph does nothing?");
		text[21].setText("A5: The problem is because some data are incorrent. Make sure you write a " +
				"correct function (without syntax errors), you set correct values for the limits of the entire graph" +
				"and for each function.");
		
		
		text[22].setText("Q1: How can I close the keyboard? ");
		text[23].setText("A1: Swipe it down.");
		
		
		text[24].setText("Q1: What makes Ref button?");
		text[25].setText("A1: Ref button is to refresh the data.");
		text[26].setText("Q2: How can I close the keyboard?  ");
		text[27].setText("A2: Swipe it down.");
		
		
		for(int i=0;i<n;i++)
		{
			text[i].setVisibility(View.GONE);
			if(i%2==0)
			{
				text[i].setTextSize(20);
				text[i].setTypeface(null, Typeface.BOLD);
				text[i].setTextColor(Color.GREEN);
			}
			else
			{
				text[i].setTextSize(17);
				text[i].setTextColor(Color.YELLOW);
			}
		}
		
		for(int i=0;i<12;i++)
		{
			layouts[0].addView(text[i]);
		}
		for(int i=12;i<22;i++)
		{
			layouts[1].addView(text[i]);
		}
		for(int i=22;i<24;i++)
		{
			layouts[2].addView(text[i]);
		}
		for(int i=24;i<28;i++)
		{
			layouts[3].addView(text[i]);
		}
		
		ClickListener click =  new ClickListener();
		ClickListener2 click2 =  new ClickListener2();
		
		for(int i=0;i<4;i++)
		{
			mainText[i].setOnClickListener(click);
		}
		for(int i=0;i<n;i++)
		{
			if(i%2==0)
			{
				text[i].setOnClickListener(click2);
				text[i].setTextColor(Color.GRAY);
			}
			else
			{
				text[i].setTextColor(Color.WHITE);
			}
		}
		return layout;
		
	}

	class ClickListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			TextView theText = (TextView) arg0;
			int m=0,n=0; 
			int k=0;
			if(theText==mainText[0]) { m=0 ;n=12; k=0;}
			if(theText==mainText[1]) { m=12;n=22; k=1;}
			if(theText==mainText[2]) { m=22;n=24; k=2;}
			if(theText==mainText[3]) { m=24;n=28; k=3;}

			if(text[m].getVisibility()==View.GONE)
			{
				for(int i=m;i<n;i++)
				{
					if(i%2==0)
					{
						text[i].setVisibility(View.VISIBLE);
					}	
				}
				String ttt  = mainText[k].getText().toString();
				theText.setText("\u25BC"+ttt.substring(1,ttt.length()));
			}
			else
			{
				for(int i=m;i<n;i++)
				{
					text[i].setVisibility(View.GONE);
				}
				String ttt  = mainText[k].getText().toString();
				theText.setText("\u25BA"+ttt.substring(1,ttt.length()));
			}
		}
		
	}
	
	class ClickListener2 implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			TextView txt = (TextView) v;
			int n= getIndex(txt);
			if(text[n+1].getVisibility()==View.GONE)
			{
				text[n+1].setVisibility(View.VISIBLE);
			}
			else
			{
				text[n+1].setVisibility(View.GONE);
			}
		}
		
	}
	
	public int getIndex(TextView x)
	{
		String txt = (String) x.getText();
		for(int i=0;i<text.length;i++)
		{
			String txt2  = (String) text[i].getText();
			if(txt.compareTo(txt2)==0)
			{
				return i;
			}
		}
		return -1;
	}
}
