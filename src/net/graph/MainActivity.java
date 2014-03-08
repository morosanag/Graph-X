package net.graph;

import java.util.Vector;

import net.graph.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Spinner;

public class MainActivity extends FragmentActivity{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	 FragmentStatePagerAdapter mSectionsPagerAdapter;
	 public static Vector<String> functions;
	 public static Spinner spinner[];
	 public static String results= "";
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		functions = new Vector<String>();
		final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(adapter);
		mViewPager.setCurrentItem(1);
		mViewPager.setOffscreenPageLimit(1);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to exit?")
	           .setCancelable(false)
	           .setPositiveButton("No", null)
	           .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                    MainActivity.this.finish();
	               }
	           })
	           
	           .show();
	}

}
