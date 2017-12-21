package com.example.natan.project3take1.Fragments;

import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natan.project3take1.Activity.DetailActivity;
import com.example.natan.project3take1.Activity.MainActivity;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Pojo.Steps;
import com.example.natan.project3take1.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by natan on 12/20/2017.
 */

public class FragmentDetailActivity extends Fragment implements ExoPlayer.EventListener {

    protected static int index = 0;
    protected Steps steps;
    private ArrayList<Steps> stepList;
    private ArrayList<Recepie> recipeList;
    @BindView(R.id.next)
    Button btn_next;
    @BindView(R.id.prev)
    Button btn_prev;
    @BindView(R.id.recipe_short_desc)
    TextView txt_recipe_short;
    @BindView(R.id.recipe_desc)
    TextView txt_recipe_desc;
    @BindView(R.id.recipe_image)
    ImageView img_recipe;
    @BindView(R.id.recipe_step_video)
    SimpleExoPlayerView playerView;
    private static MediaSessionCompat mediaSession;
    private SimpleExoPlayer exoPlayer;
    private PlaybackStateCompat.Builder stateBuilder;

    public FragmentDetailActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);


        ButterKnife.bind(this, rootView);


        stepList = getActivity().getIntent().getParcelableArrayListExtra("stepsi");
        index = getActivity().getIntent().getExtras().getInt("position");
        Log.i("fragu21", String.valueOf(index));
        if (index == 0) {
            Toast.makeText(getActivity(), String.valueOf(index), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), String.valueOf(index), Toast.LENGTH_SHORT).show();

            Steps steps = stepList.get(index);
            txt_recipe_short.setPaintFlags(txt_recipe_short.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


            setUpView(steps);
        }

        return rootView;

    }


    private void setUpView(Steps step) {


        txt_recipe_short.setText(step.getShortDescription());
        txt_recipe_desc.setText(step.getDescription());
        //Getting image url
        String imageUrl = step.getThumbnailURL();
        setupImageView(imageUrl);

        //getting video url
        String videoUrl = step.getVideoURL();
        setupVideoView(videoUrl);


    }

    private void setupVideoView(String videoUrl) {

        if (videoUrl != null && !videoUrl.isEmpty()) {

            // Init and show video view
            setViewVisibility(playerView, true);
            initializeMediaSession();
            initializePlayer(Uri.parse(videoUrl));


        } else {
            // Hide video view
            setViewVisibility(playerView, false);
        }
    }


    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            playerView.setPlayer(exoPlayer);
            exoPlayer.addListener((ExoPlayer.EventListener) this);

            String userAgent = Util.getUserAgent(getActivity(), "StepVideo");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }


    }


    /**
     * Media session initialization
     */
    private void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(getActivity(), "RecipePageFragment");

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }

        });
        mediaSession.setActive(true);
    }


    private void setViewVisibility(SimpleExoPlayerView playerView, boolean b) {

        if (b) {
            playerView.setVisibility(View.VISIBLE);
        } else {
            playerView.setVisibility(View.GONE);
        }


    }


    private void setupImageView(String imageUrl) {
        if (imageUrl.isEmpty()) {
            Picasso.with(getActivity())
                    .load(R.drawable.thumb)
                    .into(img_recipe);
        } else {
            Picasso.with(getActivity())
                    .load(imageUrl)
                    .error(R.drawable.thumb)
                    .into(img_recipe);
        }


    }


    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    /**
     * Exoplayer release method
     */
    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }


    @OnClick(R.id.next)
    void doNextStep() {
        if (index == stepList.size() - 1) {
            Toast.makeText(getActivity(), String.valueOf(index), Toast.LENGTH_SHORT).show();
            if (btn_next.isEnabled()) {
                btn_next.setEnabled(false);
            }
        } else {
            index++;

            if (!btn_prev.isEnabled()) btn_prev.setEnabled(true);
            Log.i("tagu", String.valueOf(index));
            steps = stepList.get(index);
            releasePlayer();
            setUpView(steps);

        }
    }


    @OnClick(R.id.prev)
    void doPreviousStep() {
        if (index == 0) {
            Toast.makeText(getActivity(), String.valueOf(index), Toast.LENGTH_SHORT).show();
            if (btn_prev.isEnabled()) btn_prev.setEnabled(false);
        } else {
            index--;

            if (!btn_next.isEnabled()) btn_next.setEnabled(true);
            Log.i("tagu", String.valueOf(index));

            steps = stepList.get(index);
            releasePlayer();
            setUpView(steps);
        }
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());


    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }


}
