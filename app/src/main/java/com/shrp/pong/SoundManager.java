package com.shrp.pong;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.HashMap;

/**
 * Created by shriroop on 12-Jun-17.
 */

public class SoundManager {
    static private SoundManager _instance;
    private static SoundPool mSoundPool;
    private static HashMap<Integer, Integer> mSoundPoolMap;
    private static AudioManager mAudioManager;
    private static Context mContext;
    private static boolean playSound;

    public static int SOUND_HIT = 1;
    public static int SOUND_TERMINATOR = 2;
    public static int SOUND_CLAP = 3;

    private SoundManager() {
        playSound = true;
    }

    static synchronized public SoundManager getInstance() {
        if (_instance == null)
            _instance = new SoundManager();
        return _instance;
    }

    public static void initSounds(Context context) {
        mContext = context;
        if(Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(5);
            builder.setAudioAttributes(new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build());
            mSoundPool = builder.build();
        } else {
            mSoundPool =new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public static void loadSounds() {
        mSoundPoolMap.put(SOUND_HIT, mSoundPool.load(mContext, R.raw.hit, 1));
        mSoundPoolMap.put(SOUND_TERMINATOR, mSoundPool.load(mContext, R.raw.terminator, 1));
        mSoundPoolMap.put(SOUND_CLAP, mSoundPool.load(mContext, R.raw.clap, 1));
    }

    public static void playSound(int index, float speed) {
        if (playSound) {
            float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, speed);
        }
    }

    public static void togglePlaySound() {
        playSound = !playSound;
        if (!playSound) {
            for (int soundIndex : mSoundPoolMap.values()) {
                stopSound(soundIndex);
            }
        }
    }

    public static void stopSound(int index) {
        mSoundPool.stop(mSoundPoolMap.get(index));
    }

    public static void cleanup() {
        mSoundPool.release();
        mSoundPool = null;
        mSoundPoolMap.clear();
        mAudioManager.unloadSoundEffects();
        _instance = null;
    }
}
