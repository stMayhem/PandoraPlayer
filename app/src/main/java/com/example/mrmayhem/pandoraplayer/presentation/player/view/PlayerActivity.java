package com.example.mrmayhem.pandoraplayer.presentation.player.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mrmayhem.pandoraplayer.AndroidApp;
import com.example.mrmayhem.pandoraplayer.R;
import com.example.mrmayhem.pandoraplayer.business.models.SongModel;
import com.example.mrmayhem.pandoraplayer.di.player.PlayerModule;
import com.example.mrmayhem.pandoraplayer.di.playlist.PlaylistModule;
import com.example.mrmayhem.pandoraplayer.presentation.player.presenter.IPlayerPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.presenter.IPlaylistPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.view.PlaylistActivity;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerActivity extends AppCompatActivity implements IPlayerView {
    public static final String TAG = PlayerActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_cover)
    ImageView imgCover;
    @BindView(R.id.img_next)
    ImageView imgNext;
    @BindView(R.id.img_prev)
    ImageView imgPrev;
    @BindView(R.id.img_play)
    ImageView imgPlay;
    @BindView(R.id.img_pause)
    ImageView imgPause;

    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_artist)
    TextView txtArtist;

    @Inject
    IPlayerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        AndroidApp.get(PlayerActivity.this).applicationComponent().plus(new PlayerModule()).inject(this);
        presenter.setSongId(getIntent().getExtras().getInt(PlaylistActivity.EXTRA_SONG_ID));
        presenter.attachView(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @OnClick(R.id.img_next)
    public void onNextClick() {
        presenter.onNextClick();
    }

    @OnClick(R.id.img_prev)
    public void onPrevClick() {
        presenter.onPrevClick();
    }

    @OnClick(R.id.img_play)
    public void onPlayClick() {
        presenter.onPlayClick();
    }

    @OnClick(R.id.img_pause)
    public void onPauseClick() {
        presenter.onPauseClick();
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

    @Override
    public void showSongInfo(SongModel model) {
        txtArtist.setText(model.getArtist());
        txtName.setText(model.getName());
        Glide.with(this)
                .load(model.getImageUrl())
                .into(imgCover);

    }

    @Override
    public void hidePauseButton() {
        imgPause.setVisibility(View.INVISIBLE);
        imgPlay.setVisibility(View.VISIBLE);
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hidePlayButton() {
        imgPlay.setVisibility(View.INVISIBLE);
        imgPause.setVisibility(View.VISIBLE);
        Toast.makeText(this, "play", Toast.LENGTH_SHORT).show();

    }
}
