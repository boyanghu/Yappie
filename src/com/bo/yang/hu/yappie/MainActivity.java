package com.bo.yang.hu.yappie;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.bo.yang.hu.yappie.ui.LogonFragment;
import com.bo.yang.hu.yappie.ui.LogonFragment.LogonFragmentListener;
import com.facebook.Session;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showLogonFragment();
	}

	private void showLogonFragment() {
		if (!(getCurrentlyDisplayedFragment() instanceof LogonFragment)) {
			showFragment(LogonFragment.create(new LogonFragmentListener() {

				@Override
				public void onLogonSuccessful(Session session) {
					Log.d(TAG, "Logon successful");

				}

				@Override
				public void onLogonFailed() {
					Log.e(TAG, "Logon failed");
				}
			}));
		}
	}

	private Fragment getCurrentlyDisplayedFragment() {
		return getFragmentManager().findFragmentById(R.id.fragment_container);
	}

	private void showFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, fragment);
		transaction.commit();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
