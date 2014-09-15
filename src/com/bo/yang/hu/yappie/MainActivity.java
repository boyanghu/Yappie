package com.bo.yang.hu.yappie;

import android.os.Bundle;
import com.facebook.*;
import com.facebook.model.*;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private TextView exampleTV;
	private Session facebookSession;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//initializeUIElements();
		//makeInitialFacebookCall();
	}

	private void displayUserID(Session session) {
		if (facebookSession.isOpened()) {
			Request.newMeRequest(facebookSession, new Request.GraphUserCallback() {

				  @Override
				  public void onCompleted(GraphUser user, Response response) {
					  exampleTV.setText(user.getName() + " UserID: " + user.getId()); 
				  }
				}).executeAsync();
		}
	}

	private void makeInitialFacebookCall() {
		Session.openActiveSession(this, true, new Session.StatusCallback() {
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				if (exception != null) {
					Log.e(TAG, "Exception thrown: " + exception.getLocalizedMessage());
				} else if (state != null) {
					Log.d(TAG, "SessionState: " + state.toString());
				}
				if (session.isOpened()) {
					Log.d(TAG, "Session is open");
					facebookSession = session;
					displayUserID(session);
				}
			}
		});
	}

	private void initializeUIElements() {
		//exampleTV = (TextView) findViewById(R.id.example_text);
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
