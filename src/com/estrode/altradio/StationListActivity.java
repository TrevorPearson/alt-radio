package com.estrode.altradio;

import com.estrode.altradio.pandora.Pandora;
import com.estrode.altradio.pandora.Station;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StationListActivity extends ListActivity {

	private AltRadio globalState;
	private SongTask songTask;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		globalState = (AltRadio)getApplicationContext();
		ArrayAdapter<Station> adapter = new ArrayAdapter<Station>(this, android.R.layout.simple_list_item_1, globalState.getStations());
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		globalState.setCurrentStation(position);
		songTask = new SongTask();
		songTask.execute((Void) null);
	}
	
	//private void showSongList() {
	//	Intent intent = new Intent(this, NowPlayingActivity.class);
	//	startActivity(intent);
	//}
	
	private void showNowPlaying() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public class SongTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			return globalState.getPlaylist();
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				showNowPlaying();
			} 
		}

		@Override
		protected void onCancelled() {

		}
	}

}
