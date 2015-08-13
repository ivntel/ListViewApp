package com.android.ivant.listviewapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.BaseAdapter;

import com.android.ivant.listviewapp.model.Song;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 04/08/2015.
 */
public class SongsAdapter extends BaseAdapter {
//public class SongsAdapter extends BaseAdapter<SongsAdapter.ViewHolder> {

    public static final String TAG = SongsAdapter.class.getSimpleName();

    private Context mContext;
    private List<Song> mSongs;
    private LayoutInflater mLayoutInflater;

    public SongsAdapter(Context context, List<Song> songs) {
        mContext = context;
        mSongs = songs;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public void updateData(List<Song> songs) {
        mSongs = songs;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public View getView(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh = (vh);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Song song = mSongs.get(position);
        holder.rowArtist.setText(song.getArtistName());
        holder.rowSong.setText(song.getSongName());
        Glide.with(mContext).load(song.getArtistUrl()).into(holder.rowImage);

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on " + song.getSongName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.ARG_SONG, song);
                mContext.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getCount() {

        return mSongs.size();
    }

    public static class ViewHolder {
        @Bind(R.id.row_layout)
        RelativeLayout rowLayout;
        @Bind(R.id.row_image)
        ImageView rowImage;
        @Bind(R.id.row_artist)
        TextView rowArtist;
        @Bind(R.id.row_song)
        TextView rowSong;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
