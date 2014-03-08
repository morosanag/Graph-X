package net.graph;



import net.graph.R;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class GraphpanActivity extends Fragment {
	public static int k=0;
	Button btn;
	public static int [] colors = 	{Color.BLUE,Color.CYAN,	Color.GRAY,	Color.GREEN,Color.MAGENTA,	Color.RED,	Color.WHITE,Color.YELLOW};
	public static String [] colorsS = {"Blue",	"Cyan",		"Gray",		"Green",	"Magenta",		"Red",		"White",	"Yellow"};
	public static Spinner spinner[] = new Spinner[50];
	public static Spinner spinner2[] = new Spinner[50];
	public static final EditText text1[] = new EditText[50];
	public static final EditText text2[] = new EditText[50];
	public static int progressChanged=20;
	public static double [] limits;
	CustomKeyboard mCustomKeyboard;
	View layout;
	EditText x1;
	EditText x2;
	EditText y1;
	EditText y2;
	ScrollView scrollview;
	public static GraphpanActivity newInstance(int index) {
		GraphpanActivity f = new GraphpanActivity();

		Bundle args = new Bundle();   
		args.putInt("index", index);
		f.setArguments(args);       

		return f;
	}

	public void onBackPressed() { 
		// NOTE Trap the back key: when the CustomKeyboard is still visible hide it, only when it is invisible, finish activity 
		if( mCustomKeyboard.isCustomKeyboardVisible() ) mCustomKeyboard.hideCustomKeyboard();
	}

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.activity_graphpan, container, false);

		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		btn=(Button) layout.findViewById(R.id.btn);
		Button generate=(Button) layout.findViewById(R.id.btn1);
		Button delete = (Button) layout.findViewById(R.id.btdel);
		SeekBar volumeControl = (SeekBar) layout.findViewById(R.id.volume_bar);
		volumeControl.setOnSeekBarChangeListener(new SeekBarListener());
		final LinearLayout main=(LinearLayout) layout.findViewById(R.id.main);
		x1 = (EditText) layout.findViewById(R.id.limitx1);
		x2 = (EditText) layout.findViewById(R.id.limitx2);
		y1 = (EditText) layout.findViewById(R.id.limity1);
		y2 = (EditText) layout.findViewById(R.id.limity2);

		x1.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus) 
			{
				x1.append("1");
			}
		});


		scrollview = ( ScrollView)  layout.findViewById(R.id.scroll);

		mCustomKeyboard= new CustomKeyboard(getActivity(), R.id.keyboardview, R.xml.hexkbd, layout, scrollview );
		mCustomKeyboard.registerEditText(x1);
		mCustomKeyboard.registerEditText(x2);
		mCustomKeyboard.registerEditText(y1);
		mCustomKeyboard.registerEditText(y2);

		final LinearLayout lays[] =  new LinearLayout[100];

		for(int i=0;i<100;i++)
		{
			lays[i]=new LinearLayout(getActivity());
			lays[i].setOrientation(LinearLayout.HORIZONTAL);
		}

		View.OnClickListener editclick = new View.OnClickListener()
		{	
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				EditText b = (EditText) v;
				Log.d("Debug","### "+b.getText()+"");


			}
		};

		View.OnClickListener clicklist3 = new View.OnClickListener()
		{	
			public void onClick(View v) 
			{
				try
				{
					limits = new double[4];

					limits[0]=Double.parseDouble(Evaluator.evaluate(x1.getText().toString()));
					limits[1]=Double.parseDouble(Evaluator.evaluate(x2.getText().toString()));
					limits[2]=Double.parseDouble(Evaluator.evaluate(y1.getText().toString()));
					limits[3]=Double.parseDouble(Evaluator.evaluate(y2.getText().toString()));


					Intent intent = new Intent(getActivity(), GraphActivity.class);
					getActivity().startActivity(intent);
				}
				catch(Exception ex)
				{
					Toast.makeText(getActivity(),"Missing or erroneous data!", 5).show();
				}
			}
		};

		generate.setOnClickListener(clicklist3);

		View.OnClickListener clicklist4 = new View.OnClickListener()
		{	
			public void onClick(View v) 
			{
				if(k>0)
				{
					k--;
					lays[k].removeAllViews();
					main.removeView(lays[k]);
				}
			}
		};
		delete.setOnClickListener(clicklist4);

		OnClickListener clicklist = new OnClickListener(){

			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				text1[k]= new EditText(getActivity());
				text1[k].setHint("( ");
				text2[k]= new EditText(getActivity());
				text2[k].setHint(" )");
				spinner[k] = new Spinner(getActivity());
				ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, MainActivity.functions);
				spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
				spinner[k].setAdapter(spinnerArrayAdapter);
				spinner2[k] = new Spinner(getActivity());
				ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, colorsS);
				spinnerArrayAdapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
				spinner2[k].setAdapter(spinnerArrayAdapter2);
				spinner[k].setSelection(Math.min(k,MainActivity.functions.size()-1));
				spinner2[k].setSelection(Math.min(k, colorsS.length-1));

												
				lays[k].addView(spinner[k],new TableRow.LayoutParams(0, LayoutParams.FILL_PARENT, 4f));    
				lays[k].addView(spinner2[k],new TableRow.LayoutParams(0, LayoutParams.FILL_PARENT, 1.75f));
				lays[k].addView(text1[k],new TableRow.LayoutParams(0, LayoutParams.FILL_PARENT, 1.25f));
				lays[k].addView(text2[k],new TableRow.LayoutParams(0, LayoutParams.FILL_PARENT, 1.25f));

				mCustomKeyboard.registerEditText(text1[k]);
				mCustomKeyboard.registerEditText(text2[k]);

				main.addView(lays[k]);
				k++;
			}};

			btn.setOnClickListener(clicklist);
			x1.setOnClickListener(editclick);
			return layout;
	}


	public class SeekBarListener implements OnSeekBarChangeListener
	{

		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
			progressChanged = progress;
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	}



}