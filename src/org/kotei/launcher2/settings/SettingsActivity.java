package org.kotei.launcher2.settings;

import org.kotei.launcher2.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.addPreferencesFromResource(R.xml.settings);
	}


}
