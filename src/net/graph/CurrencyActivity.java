package net.graph;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.graph.R;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.KeyboardView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class CurrencyActivity extends Fragment {

	LinearLayout layout;
	LinearLayout mainLayout;
	LinearLayout layoutButtons;
	public static LinearLayout firstLayout; 
	public static String text="";
	public static int nrLines=0;
	public static String [] monede;
	public static String [] monedeCompact;
	public static double [] change;
	public static String [] realName;   
	public static String [] realNameCompact;  
	public static String [] continent;
	public Spinner spinner;
	public static int module;
	public static EditText texty;
	public static TextView texts[];
	public static LinearLayout [] layouts ;
	public static LinearLayout [] layoutsIn;
	public static int[] compactList = {81,21,67,23,14,13,2,36};
	public static boolean compact= true;
	public static Button butMore;
	public static Button butLess;
	public ArrayAdapter<String> spinnerArrayAdapter6;
	public static String result="";
	public static View layoutm;
	public static boolean isFile=false;
	Button refresh;
	ScrollView scrollview;


	CustomKeyboard mCustomKeyboard;
	String filename= "myfile28";
	public static CurrencyActivity newInstance(int index) 
	{
		CurrencyActivity f = new CurrencyActivity();
		Bundle args = new Bundle();   
		args.putInt("index", index);
		f.setArguments(args);       
		return f;
	}

	public void onBackPressed() { 
		// NOTE Trap the back key: when the CustomKeyboard is still visible hide it, only when it is invisible, finish activity 
		if( mCustomKeyboard.isCustomKeyboardVisible() ) mCustomKeyboard.hideCustomKeyboard();
	}


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layoutm = inflater.inflate(R.layout.activity_currency, container, false);

		


		refresh =  (Button) layoutm.findViewById(R.id.buttonRefresh);
		spinner = (Spinner) layoutm.findViewById(R.id.spinnerCurr);
		layout =  (LinearLayout) layoutm.findViewById(R.id.layoutCurrency);
		mainLayout = (LinearLayout) layoutm.findViewById(R.id.mainLayoutCurr);
		layoutButtons = (LinearLayout) layoutm.findViewById(R.id.layoutButtons);
		texty = (EditText) layoutm.findViewById(R.id.edittextCurr);


		layout.setOrientation(LinearLayout.VERTICAL);
		refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				try
				{
					String content = URLConn.changeText(URLConn.getContents());
					writeFile(content,filename);
					getImportant(content);
					createPanels(compact);
					setSpinner(compact);

				}
				catch(Exception ex)
				{
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
					alertDialog.setMessage("There is no internet connection!");

					alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int which) {
							Intent intent=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
							startActivity(intent);
						}
					});

					alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});

					alertDialog.show();
				}
			}
		});

		texty.addTextChangedListener(new textListener());
		texty.setTextSize(25);
		spinner.setOnItemSelectedListener(new Listener2());
		butMore = new Button (getActivity());
		butLess = new Button (getActivity());
		butMore.setText("More");
		butLess.setText("Less");
		Listener3 clicklist2 = new Listener3();
		butMore.setOnClickListener(clicklist2);
		butLess.setOnClickListener(clicklist2);
		mainLayout.addView(butMore);

		butMore.setBackgroundColor(Color.parseColor("#AE4E38"));
		butLess.setBackgroundColor(Color.parseColor("#AE4E38"));



		butMore.setTextColor(Color.parseColor("#252A2D"));
		butLess.setTextColor(Color.parseColor("#252A2D"));
		try
		{
			String content = readFromFile (filename);
			getImportant(content);
			createPanels(true);
			setSpinner(compact);
		}
		catch(Exception ex)
		{

		}

		mCustomKeyboard= new CustomKeyboard(getActivity(), R.id.keyboardview, R.xml.hexkbd, layoutm ,scrollview);
		mCustomKeyboard.registerEditText(texty); 
		return layoutm;
	}

	public class Listener2 implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> spinner, View container,
				int position, long id) 
		{
			try
			{
				double x = Double.parseDouble(texty.getText().toString());
				module = getPosition(spinner.getSelectedItem().toString());
				getResults(x,module,compact);
			}
			catch(Exception ex)
			{}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}

	}

	public static ArrayList<String> createList(String [] monede,String[] nume, int n)
	{
		ArrayList<String> lista =  new ArrayList<String>();
		for(int i=0;i<n;i++)
		{
			lista.add(nume[i]+" ("+monede[i]+")");
		}
		return lista;
	}

	public void setSpinner(boolean compact)
	{
		if(compact==true)
		{
			try
			{
				ArrayList<String> lista = createList(monedeCompact,realNameCompact,monedeCompact.length);
				ArrayAdapter<String> spinnerArrayAdapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lista);
				spinnerArrayAdapter5.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
				spinner.setAdapter(spinnerArrayAdapter5);
			}
			catch(Exception ex)
			{
			}
		}
		else
		{
			ArrayList<String> lista = createList(monede,realName,monede.length);
			spinnerArrayAdapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lista);
			spinnerArrayAdapter6.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
			spinner.setAdapter(spinnerArrayAdapter6);
		}
	}

	class textListener implements TextWatcher
	{

		@Override
		public void afterTextChanged(Editable arg0) 
		{
			try
			{
				double x = Double.parseDouble(texty.getText().toString());
				module = getPosition(spinner.getSelectedItem().toString());
				getResults(x,module,compact);
			}
			catch(Exception ex)
			{

			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

	}
	public void writeFile(String outputString, String filename) throws IOException
	{
		File file = new File(filename);
		FileOutputStream outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
		String lines [] =  outputString.split("\n");
		for(int i=0;i<lines.length;i++)
		{
			outputStream.write(lines[i].getBytes());
			outputStream.write("\n".getBytes());
		}
		outputStream.close();
		long time = new Date().getTime();
		file.setLastModified((time / 1000) * 1000);
	}

	public String readFromFile (String filename) throws IOException 
	{
		String returnedText= "";
		FileInputStream inputStream = getActivity().openFileInput(filename);
		BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder total = new StringBuilder();
		String line;
		while ((line = r.readLine()) != null) {
			total.append(line);
			total.append("\n");
		}
		r.close();
		inputStream.close();
		returnedText=total.toString();
		return returnedText;
	}

	public void getImportant(String text)
	{
		String lines[] = text.split("\n");
		monede = new String[lines.length/3];
		change = new double[lines.length/3];
		realName = new String[lines.length/3];
		continent = new String[lines.length/3];
		monedeCompact = new String[8];
		realNameCompact = new String[8];

		for(int i=0;i<lines.length;i++)
		{
			if(i%3==0)
			{
				String [] words = lines[i].split("/");
				monede[i/3]=words[0].replace(" ", "");
			}
			if(i%3==1)
			{
				String [] words = lines[i].split(" ");
				change[i/3]=Double.parseDouble(words[9].replace(",", ""));
				realName[i/3]=getRealName(words,10,words.length);
			}
			else
			{
				continent[i/3]=lines[i].replace(" ", "");	
			}
		}

		for(int i=0;i<8;i++)
		{
			monedeCompact[i]=monede[compactList[i]];
			realNameCompact[i]=realName[compactList[i]];
		}
	}

	public static String getRealName(String[] line, int start, int stop)
	{
		String res = "";
		while(start<stop)
		{
			res+=line[start]+" ";
			start++;
		}
		return res;
	}

	public void createPanels(boolean compact)
	{
		layout.removeAllViews();
		int nr;
		if(compact==false)
		{
			nr=continent.length/2;
		}
		else
		{
			nr=4;
		}
		LinearLayout [] lays = new LinearLayout[2*nr];
		LinearLayout [] laysin = new LinearLayout[2*nr];
		//LinearLayout [] laysTop =  new LinearLayout[nr];
		texts=new TextView[nr*6];
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		LayoutParams params  = new TableRow.LayoutParams(0, LayoutParams.FILL_PARENT, 1f);
		for(int i=0;i<nr;i++)
		{
			for(int j=0;j<6;j++)
			{
				texts[6*i+j]= new TextView(getActivity());
				texts[6*i+j].setTextSize(18);
			}
			texts[6*i+1].setTextSize(20);
			texts[6*i+1].setMaxLines(1);
			texts[6*i+4].setTextSize(20);
			texts[6*i+4].setMaxLines(1);


			if(compact==true)
			{
				texts[6*i].setText(monede[compactList[2*i]], TextView.BufferType.SPANNABLE);
				texts[6*i+2].setText(realName[compactList[2*i]], TextView.BufferType.SPANNABLE);
				texts[6*i+3].setText(monede[compactList[2*i+1]], TextView.BufferType.SPANNABLE);
				texts[6*i+5].setText(realName[compactList[2*i+1]], TextView.BufferType.SPANNABLE);
			}
			else
			{
				texts[6*i].setText(monede[2*i], TextView.BufferType.SPANNABLE);
				texts[6*i+2].setText(realName[2*i], TextView.BufferType.SPANNABLE);
				texts[6*i+3].setText(monede[2*i+1], TextView.BufferType.SPANNABLE);
				texts[6*i+5].setText(realName[2*i+1], TextView.BufferType.SPANNABLE);
			}
			texts[6*i].setFocusable(false);
			texts[6*i+1].setFocusable(false);
			texts[6*i+2].setFocusable(false);
			texts[6*i+3].setFocusable(false);
			texts[6*i+4].setFocusable(false);
			texts[6*i+5].setFocusable(false);



			lays[2*i] = new LinearLayout(getActivity());
			lays[2*i+1] = new LinearLayout(getActivity());
			laysin[2*i] = new LinearLayout(getActivity());
			laysin[2*i].setOrientation(LinearLayout.VERTICAL);


			laysin[2*i+1] = new LinearLayout(getActivity());
			laysin[2*i+1].setOrientation(LinearLayout.VERTICAL);

			layout.setOrientation(LinearLayout.VERTICAL);


			texts[6*i+2].setTypeface(null, Typeface.BOLD);

			laysin[2*i].addView(texts[6*i+2]);
			laysin[2*i].addView(texts[6*i]);

			laysin[2*i].setLayoutParams(params);
			texts[6*i+1].setLayoutParams(params);

			lays[2*i].addView(laysin[2*i]);
			lays[2*i].addView(texts[6*i+1]);


			texts[6*i+5].setTypeface(null, Typeface.BOLD);
			laysin[2*i+1].addView(texts[6*i+5]);
			laysin[2*i+1].addView(texts[6*i+3]);

			laysin[2*i+1].setLayoutParams(params);
			texts[6*i+4].setLayoutParams(params);

			lays[2*i+1].addView(laysin[2*i+1]);
			lays[2*i+1].addView(texts[6*i+4]);

			lays[2*i].setBackgroundColor(Color.parseColor("#40484B"));
			layout.addView(lays[2*i]);
			layout.addView(lays[2*i+1]);
		}
	}


	public static int getPosition (String word)
	{
		for(int i=0;i<monede.length;i++)
		{
			if(word.compareTo(realName[i]+" (" +monede[i]+")")==0)
			{
				return i;
			}
		}
		return -1;
	}

	public void getResults(double x, int nr, boolean compact)
	{
		Log.d("Debug","Date: "+x+" " + nr+" "+ compact);
		if(compact==false)
		{
			String [] results = new String[change.length];
			for(int i=0;i<change.length;i++)
			{
				try
				{
					float ch = (float) ((float)Math.round(change[i]* 10000.0) / 10000.0);
					float chr = (float) ((float) Math.round(change[nr]* 10000.0) / 10000.0);
					float res= ch/chr;
					float d = (float) (Math.round(res * 10000.0) / 10000.0);
					results[i]=(x*d)+"";
					texts[3*i+1].setText(results[i]+"");
				}
				catch(Exception ex)
				{
					results[i]="";
				}
			}
		}
		else
		{  
			String [] results = new String[8];
			for(int i=0;i<8;i++)
			{
				try
				{
					float ch = (float) ((float)Math.round(change[compactList[i]]* 10000.0) / 10000.0);
					float chr = (float) ((float) Math.round(change[nr]* 10000.0) / 10000.0);
					float res= ch/chr;
					float d = (float) (Math.round(res * 10000.0) / 10000.0);
					results[i]=(x*d)+"";
					texts[3*i+1].setText(results[i]+"");
				}
				catch(Exception ex)
				{
					results[i]="";
				}
			}
		}
	}


	public class Listener3 implements View.OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub	
			Button b = (Button) v;
			try
			{
				if(b.getText().toString().compareTo("More")==0)
				{
					compact = false;
					mainLayout.removeView(butMore);
					createPanels(compact);
					setSpinner(compact);   
					mainLayout.addView(butLess);
				}
				else
				{
					compact=true;
					mainLayout.removeView(butLess);
					createPanels(compact);
					setSpinner(compact);
					mainLayout.addView(butMore);
				}
				double x = Double.parseDouble(texty.getText().toString());
				module = getPosition(spinner.getSelectedItem().toString());
				getResults(x,module,compact);
			}catch(Exception ex)
			{
			}
		}	
	}




}
