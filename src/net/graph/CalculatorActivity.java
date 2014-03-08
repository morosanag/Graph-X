package net.graph;

import java.util.Vector;
   
import net.graph.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class CalculatorActivity extends Fragment {
	public static EditText txt = null;
	public static String theText = "";
	public static boolean mustNumbers = true;
	public static Vector<Float> numbers;
	public static Vector<String> signs;
	public static LinearLayout line;
	public static String result="";
	LayoutInflater inflater;
	public static CalculatorActivity newInstance(int index) {
		CalculatorActivity f = new CalculatorActivity();

		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f; 
	}     

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.activity_calcultor, container, false);
		this.inflater=inflater;

		line =  (LinearLayout) layout.findViewById(R.layout.activity_calcultor);

		Button button1= (Button) layout.findViewById(R.id.button1);
		Button button2= (Button) layout.findViewById(R.id.button2);
		Button button3= (Button) layout.findViewById(R.id.button3);
		Button button4= (Button) layout.findViewById(R.id.button4);
		Button button5= (Button) layout.findViewById(R.id.button5);
		Button button6= (Button) layout.findViewById(R.id.button6);
		Button button7= (Button) layout.findViewById(R.id.button7);
		Button button8= (Button) layout.findViewById(R.id.button8);
		Button button9= (Button) layout.findViewById(R.id.button9);
		Button button0= (Button) layout.findViewById(R.id.button0);
		Button buttonx= (Button) layout.findViewById(R.id.buttonUnknown);


		Button buttonplus= (Button) layout.findViewById(R.id.buttonplus);

		Button buttonmul= (Button) layout.findViewById(R.id.buttonx);
		Button buttondiv= (Button) layout.findViewById(R.id.buttondiv);
		Button buttonpoint= (Button) layout.findViewById(R.id.buttonpoint);
		Button buttonequal= (Button) layout.findViewById(R.id.buttonegal);
		Button buttonopen= (Button) layout.findViewById(R.id.open);
		Button buttonclose= (Button) layout.findViewById(R.id.close);

		Button Add= (Button) layout.findViewById(R.id.buttonAdd);

		Button buttonsin= (Button) layout.findViewById(R.id.sin);
		Button buttoncos= (Button) layout.findViewById(R.id.cos);
		Button buttontan= (Button) layout.findViewById(R.id.tan);
		Button buttonctan= (Button) layout.findViewById(R.id.ctan);

		Button buttonPi= (Button) layout.findViewById(R.id.pi);
		Button buttonE= (Button) layout.findViewById(R.id.e);

		Button buttonln= (Button) layout.findViewById(R.id.ln);
		Button buttonlg= (Button) layout.findViewById(R.id.lg);
		Button buttonlb= (Button) layout.findViewById(R.id.lb);
		Button buttonfloor= (Button) layout.findViewById(R.id.floor);
		Button buttonfrac= (Button) layout.findViewById(R.id.frac);
		Button buttonabs= (Button) layout.findViewById(R.id.abs);   

		Button buttonminus= (Button) layout.findViewById(R.id.buttonminus);
		Button buttonsqrt= (Button) layout.findViewById(R.id.buttonsqrt);
		Button buttonexp= (Button) layout.findViewById(R.id.buttonexp);
		Button buttC= (Button) layout.findViewById(R.id.buttonC);


		txt = (EditText) layout.findViewById(R.id.edittext);


		OnClickListener click = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				theText= back(theText);
				txt.setText(theText);
			}
		};

		OnLongClickListener longclick = new OnLongClickListener()
		{	
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub

				init();
				txt.setText("");
				return false;
			}
		};

		txt.setOnClickListener(click);
		txt.setOnLongClickListener(longclick);


		init();
		View.OnClickListener clicklist = new View.OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				Button b = (Button) v;
				theText+= b.getText().toString();
				txt.setText(theText);
				mustNumbers=false;
			}
		};

		View.OnClickListener clicklist2 = new View.OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				Button b = (Button) v;
				if(mustNumbers==false)
				{
					theText=theText + " "+ b.getText().toString()+ " ";
					txt.setText(theText);
					mustNumbers=true;
				}    
			}
		};




		OnClickListener clicklist3 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				Button b = (Button) v;
				if(mustNumbers==false && check(theText))
				{
					theText+=b.getText().toString();
					txt.setText(theText);
					mustNumbers=true;
				}
			}
		};		
		OnClickListener clicklist4 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				txt.setText(Evaluator.evaluate(theText));
				theText=txt.getText()+"";
			}
		};		

		OnClickListener clicklist7 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				Button b = (Button) v;
				theText = theText +" "+ b.getText().toString()+" ";
				txt.setText(theText);
				mustNumbers=false;
			}
		};
		OnClickListener clicklist8 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				Toast.makeText(getActivity(),"Added!", 5).show();
				if(!MainActivity.functions.contains(theText))
				{
					MainActivity.functions.add(theText);
				}
			}
		};
		OnClickListener clicklist9 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	

				Button b = (Button) v;
				theText= theText+ b.getText().toString()+"( ";
				txt.setText(theText);
				mustNumbers=false;
			}
		};
		OnClickListener clicklist10 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				Button b = (Button) v;
				if(mustNumbers==false)
				{
					theText=theText + " "+ b.getText().toString()+ " ";
					txt.setText(theText);
					mustNumbers=true;
				}
				if(theText.compareTo("")==0)
				{
					theText=theText + " "+ b.getText().toString()+ " ";
					txt.setText(theText);
					mustNumbers=true;
				}
			}
		};

		OnClickListener clicklist11 = new OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				theText= back(theText);
				txt.setText(theText);
			}
		};


		buttonplus.setOnClickListener(clicklist2);
		button1.setOnClickListener(clicklist);
		button2.setOnClickListener(clicklist);
		button3.setOnClickListener(clicklist);
		button4.setOnClickListener(clicklist);
		button5.setOnClickListener(clicklist);
		button6.setOnClickListener(clicklist);
		button7.setOnClickListener(clicklist);
		button8.setOnClickListener(clicklist);
		button9.setOnClickListener(clicklist);
		button0.setOnClickListener(clicklist);
		buttonx.setOnClickListener(clicklist);
		buttonmul.setOnClickListener(clicklist2);
		buttondiv.setOnClickListener(clicklist2);   
		buttonexp.setOnClickListener(clicklist2);
		buttonpoint.setOnClickListener(clicklist3);   
		buttonequal.setOnClickListener(clicklist4);
		buttonopen.setOnClickListener(clicklist7);
		buttonclose.setOnClickListener(clicklist7);
		Add.setOnClickListener(clicklist8);
		buttonsin.setOnClickListener(clicklist9);
		buttoncos.setOnClickListener(clicklist9);
		buttontan.setOnClickListener(clicklist9);
		buttonsqrt.setOnClickListener(clicklist9);  
		buttonctan.setOnClickListener(clicklist9);
		buttonPi.setOnClickListener(clicklist);
		buttonE.setOnClickListener(clicklist);      
		buttonln.setOnClickListener(clicklist9);
		buttonlg.setOnClickListener(clicklist9);
		buttonlb.setOnClickListener(clicklist9);
		buttonfloor.setOnClickListener(clicklist9);
		buttonfrac.setOnClickListener(clicklist9);
		buttonabs.setOnClickListener(clicklist9);
		buttonminus.setOnClickListener(clicklist10);
		buttC.setOnClickListener(clicklist11);
		/*txt.setBackgroundResource(R.drawable.buton);
		button1.setBackgroundResource(R.drawable.buton);*/


		return layout;
	}



	public static String back(String txt)
	{
		String [] words = txt.split(" ");
		if(words.length>0)
		{
			try
			{ 
				Double.parseDouble(words[words.length-1]);
				mustNumbers=true;
				return txt.substring(0, txt.length()-1);
			}
			catch(Exception ex)
			{
				String res="";
				for(int i=0;i<words.length-1;i++)
				{
					res+=words[i]+" "; 
				}
				mustNumbers=false;
				return res;
			}
		}
		return "";
	}

	public static void init()
	{
		theText="";
		mustNumbers=true;
	}

	public boolean check(String text)
	{
		String[] words = text.split(" ");
		return !(words[words.length-1].contains(".")==true);
	}

	
}