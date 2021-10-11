package com.alin.benchmark.dbflow;

import android.content.Context;

import com.dbflow5.config.FlowManager;
import com.dbflow5.database.DatabaseWrapper;
import com.dbflow5.query.SQLite;
import com.alin.benchmark.core.StorageBenchmark;
import com.alin.benchmark.core.TestData;

import java.util.List;

public class DBFlowBenchmark implements StorageBenchmark {
    @Override
    public void onCreate(Context context) {

    }

    @Override
    public void singleWrite(TestData testData) {
        DatabaseWrapper db = FlowManager.getDatabase(AppDatabase.class).getWritableDatabase();
        User user = new User();
        user.setName(testData.getName());
        user.setAge(testData.getAge());
        user.setTimestamp(testData.getTimestamp());
        user.save(db);
    }

    @Override
    public void singleRead(String name) {
        DatabaseWrapper db = FlowManager.getDatabase(AppDatabase.class).getWritableDatabase();
        User user = SQLite.select().from(User.class).where(User_Table.name.eq(name)).querySingle(db);
    }

    @Override
    public void singleUpdate(String name, String newName) {

    }

    @Override
    public void singleDelete(String name) {
        DatabaseWrapper db = FlowManager.getDatabase(AppDatabase.class).getWritableDatabase();
        SQLite.delete(User.class).where(User_Table.name.eq(name));
    }

    @Override
    public void deleteAll() {
        DatabaseWrapper db = FlowManager.getDatabase(AppDatabase.class).getWritableDatabase();
        SQLite.delete(User.class);
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
