package com.alin.benchmark.core;

import android.content.Context;

import java.util.List;


public interface StorageBenchmark {

    void onCreate(Context context);

    void singleWrite(TestData testData);

    void singleRead(String name);

    void singleUpdate(String name, String newName);

    void singleDelete(String name);

    void deleteAll();

    void batchWrite(List<TestData> testDatas);

    void batchRead(List<String> names);

    void onDestroy();

}
