package com.alin.benchmark.ormlite;

import android.content.Context;

import com.alin.benchmark.MainActivity;
import com.alin.benchmark.core.StorageBenchmark;
import com.alin.benchmark.core.TestData;

import java.util.List;

public class OrmliteBenchmark implements StorageBenchmark {
    MainActivity mainActivity;

    public OrmliteBenchmark(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Context context) {

    }

    @Override
    public void singleWrite(TestData testData) {
        try {
            User user = new User();
            user.setName(testData.getName());
            user.setAge(testData.getAge());
            user.setTimestamp(testData.getTimestamp());
            mainActivity.getHelper().getUserDao().create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void singleRead(String name) {
        try {
            List<User> results = mainActivity.getHelper().getUserDao().queryForEq("name", name);
            if (results.size() > 0) {
                User u = results.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void singleUpdate(String name, String newName) {

    }

    @Override
    public void singleDelete(String name) {
        try {
            List<User> results = mainActivity.getHelper().getUserDao().queryForEq("name", name);
            if (results.size() > 0) {
                User u = results.get(0);
                mainActivity.getHelper().getUserDao().delete(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            mainActivity.getHelper().getUserDao().deleteBuilder().delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchWrite(List<TestData> testDatas) {

    }

    @Override
    public void batchRead(List<String> names) {

    }

    @Override
    public void onDestroy() {
    }
}
