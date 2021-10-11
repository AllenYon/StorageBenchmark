package com.alin.benchmark.realm;

import android.content.Context;
import android.util.Log;

import com.alin.benchmark.core.StorageBenchmark;
import com.alin.benchmark.core.TestData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmBenchmark implements StorageBenchmark {
    private Realm realm;

    @Override
    public void onCreate(Context context) {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void singleWrite(TestData testData) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserRealm t = new UserRealm();
                t.setName(testData.getName());
                t.setAge(testData.getAge());
                t.setTimestamp(testData.getTimestamp());
                realm.copyToRealm(t);
            }
        });
    }

    @Override
    public void singleRead(String name) {
        UserRealm u = realm.where(UserRealm.class).equalTo("name", name).findFirst();
    }

    @Override
    public void singleUpdate(String name, String newName) {
    }

    @Override
    public void singleDelete(String name) {
        UserRealm u = realm.where(UserRealm.class).equalTo("name", name).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (u != null) {
                    u.deleteFromRealm();
                }
            }
        });
    }

    @Override
    public void deleteAll() {
        final RealmResults<UserRealm> userRealms = realm.where(UserRealm.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (userRealms != null) {
                    userRealms.deleteAllFromRealm();
                }
            }
        });
    }

    @Override
    public void batchWrite(List<TestData> testDatas) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                List<UserRealm> userRealms = new ArrayList<>(testDatas.size());
                for (TestData item : testDatas) {
                    UserRealm t = new UserRealm();
                    t.setName(item.getName());
                    t.setAge(item.getAge());
                    t.setTimestamp(item.getTimestamp());
                    userRealms.add(t);
                }
                realm.insertOrUpdate(userRealms);
            }
        });
    }

    @Override
    public void batchRead(List<String> names) {
        String[] namesArray = names.toArray(new String[0]);
        RealmResults<UserRealm> results = realm.where(UserRealm.class).in("name", namesArray).findAll();
        Log.i("YLG", "name:" + names.get(0) + " " + results.get(0));
//        if (results.size() == names.size()) {
//            Log.i("YLG", "name:" + names.get(0) + " " + results.get(0));
//        }
    }

    @Override
    public void onDestroy() {
        realm.close();
    }
}
