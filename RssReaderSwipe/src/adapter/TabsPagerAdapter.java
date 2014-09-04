package adapter;

import fragment.PC;
import fragment.SME;
import fragment.Topky;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public Activity mainActivity;

	public TabsPagerAdapter(FragmentManager fm, Activity mainAct) {
		super(fm);
		mainActivity = mainAct;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new Topky();
		case 1:
			return new SME();
		case 2:
			return new PC();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}