package com.example.mrmayhem.pandoraplayer;

import com.example.mrmayhem.pandoraplayer.business.models.SongModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrMayhem on 31.08.2017.
 */

public class Dummy {
    //public static Map<Integer, String> map;
    public static List<SongModel> list;
    public static Map<Integer, SongModel> songModels;

    static {
        list = new ArrayList<>();
        songModels = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            SongModel songModel = new SongModel();
            songModel.setArtist("Artist " + i);
            songModel.setId(i);
            songModel.setName("Title " + i);
            if (i % 2 == 0)
                songModel.setImageUrl("https://pbs.twimg.com/profile_images/875133023347326977/rj-yK2x3_400x400.jpg");
            else
                songModel.setImageUrl("https://yt3.ggpht.com/-zK8v1xKkZtY/AAAAAAAAAAI/AAAAAAAAAAA/SmyGR2XCwXw/s900-c-k-no-mo-rj-c0xffffff/photo.jpg");
            list.add(songModel);
            songModels.put(songModel.getId(), songModel);
        }


    }
}
