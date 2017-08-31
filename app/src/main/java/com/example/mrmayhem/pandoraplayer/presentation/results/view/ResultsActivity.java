package com.example.mrmayhem.pandoraplayer.presentation.results.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.example.mrmayhem.pandoraplayer.di.results.ResultsModule;
import com.example.mrmayhem.pandoraplayer.presentation.player.view.PlayerActivity;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.view.PlaylistActivity;
import com.example.mrmayhem.pandoraplayer.presentation.results.presenter.IResultsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity implements IResultsView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.list_view)
    ListView results;
    @BindView(R.id.empty_list)
    TextView emptyList;

    @Inject
    IResultsPresenter presenter;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        AndroidApp.get(ResultsActivity.this).applicationComponent().plus(new ResultsModule()).inject(this);
        presenter.setQuery(getIntent().getExtras().getString(PlaylistActivity.EXTRA_QUERY));
        presenter.attachView(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search results for: " + presenter.getQuery());

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>());
        results.setAdapter(adapter);

        results.setOnItemClickListener((adapter, v, position, arg3) -> presenter.onListItemClick());
    }

    @Override
    public void showQueryResults(List<SongModel> list) {
        adapter.clear();
        list.forEach(
                (songModel) -> {
                    adapter.add(songModel.getArtist() + " - " + songModel.getName());
                }
        );
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showPlayerActivity() {
        Intent intent = new Intent(ResultsActivity.this, PlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        results.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        results.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyList() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
