package net.graph;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter{
	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}


	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		switch (position) {


		case 0:
			return new HelpActivity();
		case 1:
			return new CalculatorActivity();
		case 2:
			return new GraphpanActivity();
		case 3:
			return new ConverterActivity();
		case 4:
			return new CurrencyActivity();	
		}
		return null;
	}

	@Override
	public int getCount() {
		return 5;
	}

	public CharSequence getPageTitle(int position) {
		switch (position) {

		case 0:
			return "Help";
		case 1:
			return "Calculator";
		case 2:
			return "Graph Panel";
		case 3:
			return "Converter";
		case 4:
			return "Currency";
		}
		return null;
	}
}
