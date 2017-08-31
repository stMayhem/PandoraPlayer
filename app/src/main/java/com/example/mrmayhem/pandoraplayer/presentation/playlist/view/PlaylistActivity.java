package com.example.mrmayhem.pandoraplayer.presentation.playlist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrmayhem.pandoraplayer.AndroidApp;
import com.example.mrmayhem.pandoraplayer.R;
import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.di.playlist.PlaylistModule;
import com.example.mrmayhem.pandoraplayer.presentation.auth.view.AuthActivity;
import com.example.mrmayhem.pandoraplayer.presentation.player.view.PlayerActivity;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.presenter.IPlaylistPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.results.view.ResultsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistActivity extends AppCompatActivity implements IPlaylistView {
    public static final String EXTRA_QUERY = "query-extra";
    public static final String EXTRA_SONG_ID = "song-id-extra";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.empty_list)
    TextView emptyList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.list_view)
    ListView playlist;


    @Inject
    IPlaylistPresenter presenter;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        ButterKnife.bind(this);
        AndroidApp.get(PlaylistActivity.this).applicationComponent().plus(new PlaylistModule()).inject(this);
        presenter.attachView(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>());

        playlist.setAdapter(adapter);
        playlist.setOnItemClickListener((adapter, v, position, arg3) -> presenter.onListItemClick(
                position-1
        ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_playlist, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchViewItem.getActionView();

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {

                return true;
            }

            public boolean onQueryTextSubmit(String query) {
               if(!query.isEmpty()) showResultsActivity(query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_logout:
                onLogoutClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showResultsActivity(String query) {
        Intent intent = new Intent(PlaylistActivity.this, ResultsActivity.class);
        intent.putExtra(EXTRA_QUERY, query);
        startActivity(intent);
    }

    @Override
    public void showLoginActivity() {
        Intent intent = new Intent(PlaylistActivity.this, AuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPlayerActivity(int position) {
        Intent intent = new Intent(PlaylistActivity.this, PlayerActivity.class);
        intent.putExtra(EXTRA_SONG_ID,position);
        startActivity(intent);
    }

    @Override
    public void showPlaylist(List<SongModel> list) {
        adapter.clear();
        list.forEach(
                (songModel) -> {
                    adapter.add(songModel.getArtist()+" - "+songModel.getName());
                }
        );

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        playlist.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        playlist.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void showEmptyList(){
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void onLogoutClick() {
        presenter.logout();
    }

}
