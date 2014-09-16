package com.bo.yang.hu.yappie.ui;

import com.bo.yang.hu.yappie.R;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LogonFragment extends Fragment {

	private static final String TAG = LogonFragment.class.getSimpleName();
	private boolean isStarted = false;
	private LogonFragmentListener listener;
	
	public static LogonFragment create(LogonFragmentListener listener) {
		LogonFragment fragment = new LogonFragment();
		fragment.listener = listener;
		return fragment;
	}
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_logon, container, false);
	}
	
	@Override 
	public void onStart() {
		super.onStart();
		if (!isStarted) {
			isStarted = true;
			logon();
		}
	}

	private void logon() {
		showStatus(getString(R.string.attempting_logon));
		Session.openActiveSession(getActivity(), true, new StatusCallback() {
			
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				isStarted = false;
				if (exception != null) {
					Log.e(TAG, exception.getLocalizedMessage());
					showStatus(getString(R.string.logon_failed));
					listener.onLogonFailed();
				} else {
					showStatus(getString(R.string.logon_successful));
					listener.onLogonSuccessful(session);
				}
				
			}
		});
	}
	
	private void showStatus(String status) {
		((TextView) getActivity().findViewById(R.id.logon_status_tv)).setText(status);
	}
	
	public interface LogonFragmentListener {
		public void onLogonSuccessful(Session session);
		public void onLogonFailed();
	}
}
