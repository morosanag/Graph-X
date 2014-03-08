package net.graph;

import java.util.ArrayList;

import net.graph.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ConverterActivity extends Fragment{
	int nrEditexts;
	int module;
	public LinearLayout usable;
	public LinearLayout main_layout;
	public LinearLayout [] layouts; 
	public LinearLayout [] layoutsIn;
	public EditText [] unitText;
	public EditText [] unitResult;
	public EditText textu;
	public Dates d;   
	Button butMore;
	Button butLess;
	public Spinner spinner;
	ScrollView scrollview;

	CustomKeyboard mCustomKeyboard;

	public static ConverterActivity newInstance(int index) {
		ConverterActivity f = new ConverterActivity();
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
		View layout = inflater.inflate(R.layout.activity_converter, container, false);

		d = new Dates(true);
		module =0;
		nrEditexts=d.numbers[module];
		main_layout =  (LinearLayout) layout.findViewById(R.id.layoutConv);
		usable =   (LinearLayout) layout.findViewById(R.id.layoutResults);
		Button butLength =  (Button) layout.findViewById(R.id.buttonLength);
		Button butArea =  (Button) layout.findViewById(R.id.buttonArea);
		Button butTemp =  (Button) layout.findViewById(R.id.buttonTemp);
		Button butVolume =  (Button) layout.findViewById(R.id.buttonVolume);
		Button butWeight =  (Button) layout.findViewById(R.id.buttonWeight);
		Button butTime =  (Button) layout.findViewById(R.id.buttonTime);
		butMore = new Button (getActivity());
		butLess = new Button (getActivity());
		butMore.setText("More");
		butLess.setText("Less");
		
		Listener clicklist = new Listener();
		Listener3 clicklist2  = new Listener3();
		butLength.setOnClickListener(clicklist);
		butArea.setOnClickListener(clicklist);
		butTemp.setOnClickListener(clicklist);
		butVolume.setOnClickListener(clicklist);
		butWeight.setOnClickListener(clicklist);
		butTime.setOnClickListener(clicklist);
		butMore.setOnClickListener(clicklist2);
		butLess.setOnClickListener(clicklist2);
		butMore.setBackgroundColor(Color.parseColor("#AE4E38"));
		butLess.setBackgroundColor(Color.parseColor("#AE4E38"));
		butMore.setTextColor(Color.parseColor("#252A2D"));
		butLess.setTextColor(Color.parseColor("#252A2D"));

		spinner = (Spinner) layout.findViewById(R.id.spinnerConv);
		setSpinner();
		spinner.setOnItemSelectedListener(new Listener2());
		usable.setOrientation(LinearLayout.VERTICAL);
		textu = (EditText)layout.findViewById(R.id.edittextConv);
		textu.addTextChangedListener(new textListener());
		main_layout.addView(butMore);

		mCustomKeyboard= new CustomKeyboard(getActivity(), R.id.keyboardview, R.xml.hexkbd, layout, scrollview );
		mCustomKeyboard.registerEditText(textu); 
		createLayouts();
		return layout;
	}


	public class Listener2 implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> spinner, View container,
				int position, long id) 
		{
			try
			{
				double x= Double.parseDouble(textu.getText().toString()+"");
				String [] results = d.getResults(x, module,spinner.getSelectedItemPosition());
				for(int i=0;i<results.length;i++)
				{
					unitResult[i].setText(results[i]);
				}
			}
			catch(Exception ex)
			{}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}

	}
	public class Listener implements View.OnClickListener
	{

		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub	
			Button b = (Button) v;
			module= getNumber(b.getText().toString());
			setSpinner();
			createLayouts();
		}

	}
	public class Listener3 implements View.OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub	
			Button b = (Button) v;
			if(b.getText().toString().compareTo("More")==0)
			{
				d = new Dates(false);
				setSpinner();
				createLayouts();
				main_layout.removeView(butMore);
				main_layout.addView(butLess);
			}
			else
			{
				d = new Dates(true);
				setSpinner();
				createLayouts();
				main_layout.removeView(butLess);
				main_layout.addView(butMore);
			}
		}	
	}
	public int getNumber(String name)
	{
		if(name.compareTo("LENGTH")==0) 	return 0;
		if(name.compareTo("AREA"  )==0) 	return 1;
		if(name.compareTo("TEMP"  )==0) 	return 2;
		if(name.compareTo("VOLUME")==0) 	return 3;
		if(name.compareTo("WEIGHT")==0) 	return 4;
		if(name.compareTo("TIME"  )==0) 	return 5;
		return -1;
	}

	public void setSpinner()
	{
		ArrayList<String> lista = createList(d.units[module],d.numbers[module]);
		ArrayAdapter<String> spinnerArrayAdapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lista);
		spinnerArrayAdapter5.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		spinner.setAdapter(spinnerArrayAdapter5);
	}

	public static ArrayList<String> createList(String [] name, int n)
	{
		ArrayList<String> lista =  new ArrayList<String>();
		for(int i=0;i<n;i++)
		{
			lista.add(name[i]);
		}
		return lista;
	}

	class textListener implements TextWatcher
	{

		@Override
		public void afterTextChanged(Editable arg0) 
		{
			// TODO Auto-generated method stub
			try
			{
				double x= Double.parseDouble(textu.getText().toString()+"");

				if(module!=2)
				{   
					String  []results = d.getResults(x, module,spinner.getSelectedItemPosition());
					for(int i=0;i<results.length;i++)
					{
						unitResult[i].setText(results[i]);
					}
				}
				else
				{
					try
					{
						String results[] = Dates.evaluateTemp(spinner.getSelectedItemPosition(), x);
						for(int i=0;i<results.length;i++)
						{
							unitResult[i].setText(results[i]);
						}
					}
					catch(Exception ex)
					{
						Toast.makeText(getActivity(),"Exception: "+ex, 5).show();
					}
				}

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

	public void createLayouts()
	{
		int nr=d.numbers[module];
		usable.removeAllViews();
		layouts= new  LinearLayout [nr/2];
		layoutsIn= new  LinearLayout [nr];
		unitText = new  EditText [nr];
		unitResult = new  EditText [nr];
		LayoutParams params  = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
		for(int i=0;i<nr/2;i++)
		{
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
			layouts[i]= new LinearLayout(getActivity());
			layoutsIn[2*i]=new LinearLayout(getActivity());
			layoutsIn[2*i+1]=new LinearLayout(getActivity());
			layouts[i].setOrientation(LinearLayout.HORIZONTAL);
			layoutsIn[2*i].setOrientation(LinearLayout.VERTICAL);
			layoutsIn[2*i+1].setOrientation(LinearLayout.VERTICAL);
			layoutsIn[2*i].setWeightSum(1);
			layoutsIn[2*i+1].setWeightSum(1);

			layouts[i].setLayoutParams(lp);
			layoutsIn[2*i].setLayoutParams(params);
			layoutsIn[2*i+1].setLayoutParams(params);

			unitResult[2*i]=  new  EditText(getActivity());
			unitResult[2*i+1]=  new  EditText(getActivity());
			unitText[2*i]=  new  EditText(getActivity());
			unitText[2*i+1]=  new  EditText(getActivity());

			unitResult[2*i].setFocusable(false);
			unitResult[2*i+1].setFocusable(false);
			unitText[2*i].setFocusable(false);
			unitText[2*i+1].setFocusable(false);

			unitText[2*i].setText(d.units[module][2*i],TextView.BufferType.SPANNABLE );
			unitText[2*i+1].setText(d.units[module][2*i+1],TextView.BufferType.SPANNABLE );


			layoutsIn[2*i].addView(unitText[2*i]);
			layoutsIn[2*i].addView(unitResult[2*i]);

			layoutsIn[2*i+1].addView(unitText[2*i+1]);
			layoutsIn[2*i+1].addView(unitResult[2*i+1]);

			if(i%2==0)
			{
				layoutsIn[2*i].setBackgroundColor(Color.parseColor("#40484B"));
			}
			else
			{
				layoutsIn[2*i+1].setBackgroundColor(Color.parseColor("#40484B"));
			}
			layouts[i].addView(layoutsIn[2*i]);
			layouts[i].addView(layoutsIn[2*i+1]);
			usable.addView(layouts[i]);	
		}
	}

}
