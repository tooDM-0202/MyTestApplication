package jp.ac.meijou.android.s241205183;


import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import java.util.Optional;

import io.reactivex.rxjava3.core.Single;


public class PrefDataStore {

    private static PrefDataStore instance;
    private final RxDataStore<Preferences> dataStore;

    /**
     * コンストラクタ
     *
     * @param dataStore DataStore
     */
    private PrefDataStore(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }


    public void setString(String key, String value) {
        dataStore.updateDataAsync(prefsIn -> {
                    var mutablePreferences = prefsIn.toMutablePreferences();
                    var prefKey = PreferencesKeys.stringKey(key);
                    mutablePreferences.set(prefKey, value);
                    return Single.just(mutablePreferences);
                })
                .subscribe();
    }

    public static PrefDataStore getInstance(Context context) {
        if (instance == null) {
            var dataStore = new RxPreferenceDataStoreBuilder(
                    context.getApplicationContext(), "settings").build();
            instance = new PrefDataStore(dataStore);
        }
        return instance;
    }


    /**
     * Stringの値を取得する
     *
     * @param key 取得するキー
     * @return 取得したStringのOptional
     */
    public Optional<String> getString(String key) {
        return dataStore.data()
                .map(prefs -> {
                    var prefKey = PreferencesKeys.stringKey(key);
                    return Optional.ofNullable(prefs.get(prefKey));
                })
                .blockingFirst();
    }



}
