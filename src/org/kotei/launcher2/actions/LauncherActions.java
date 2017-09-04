package org.kotei.launcher2.actions;

import java.util.ArrayList;
import java.util.List;

import org.kotei.launcher2.R;
import org.kotei.launcher2.Launcher;

import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class LauncherActions {

	public interface Action {

		public String getName();

		public void putIntentExtras(Intent intent);

		public boolean runIntent(Intent intent);

		public int getIconResourceId();
	}

	private static LauncherActions mInstance = null;
	private Launcher mLauncher;

	public static synchronized LauncherActions getInstance() {
		if (mInstance == null)
			mInstance = new LauncherActions();
		return mInstance;
	}

	private LauncherActions() {
	}

	public void init(Launcher launcher) {
		mLauncher = launcher;
	}

	Launcher getLauncher() {
		return mLauncher;
	}

	public List<Action> getList() {
		List<Action> result = new ArrayList<Action>();
		DefaultAction.GetActions(result);
		return result;
	}



	public Intent getIntentForAction(Action action) {
		Intent result = new Intent(RunActionActivity.ACTION_LAUNCHERACTION);
		result.setClass(mLauncher, RunActionActivity.class);
		action.putIntentExtras(result);
		return result;
	}

	void launch(Intent intent) {
		final List<Action> actions = getList();
		for(Action act : actions) {
			if (act.runIntent(intent))
				break;
		}
	}


	public ListAdapter getSelectActionAdapter() {
		final List<Action> mActions = getList();

		return new ListAdapter() {

			public void unregisterDataSetObserver(DataSetObserver observer) {
			}

			public void registerDataSetObserver(DataSetObserver observer) {
			}

			public boolean isEmpty() {
				return mActions.isEmpty();
			}

			public boolean hasStableIds() {
				return false;
			}

			public int getViewTypeCount() {
				return 1;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null)
				{
					LayoutInflater li = mLauncher.getLayoutInflater();
					convertView = li.inflate(R.layout.add_list_item, parent, false);

				}
				Action act = mActions.get(position);

				TextView textView = (TextView) convertView;
		        textView.setText(act.getName());
		        textView.setCompoundDrawablesWithIntrinsicBounds(
		        		mLauncher.getResources().getDrawable(act.getIconResourceId()), null, null, null);
				return convertView;
			}

			public int getItemViewType(int position) {
				return 0;
			}

			public long getItemId(int position) {
				return position;
			}

			public Object getItem(int position) {
				return mActions.get(position);
			}

			public int getCount() {
				return mActions.size();
			}

			public boolean isEnabled(int position) {
				return true;
			}

			public boolean areAllItemsEnabled() {
				return true;
			}
		};
	}

}
