package com.alin.benchmark.core

import android.content.Context
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat


object StorageBenchmarkManager {
    const val TAG = "Benchmark"

    private var storageBenchmarks = mutableListOf<StorageBenchmark>()
    private var singleDatas = mutableListOf<TestData>()
    private var batchDatas = mutableListOf<TestData>()

    private var formatter = DecimalFormat("0.00")

    fun register(storageBenchmark: StorageBenchmark) {
        storageBenchmarks.add(storageBenchmark)
    }

    fun start(context: Context, loop: Int) {
        GlobalScope.launch {
            prepareTestData(loop);
            for (item in storageBenchmarks) {
                Log.i(TAG, "-------------------Start-------------------")
                item.onCreate(context)
                singleWrite(loop, item)
                singleRead(loop, item)

                deleteAll(loop, item)

                batchWrite(loop, item)
                batchRead(loop, item)

                deleteAll(loop, item)

                item.onDestroy()
                Log.i(TAG, "-------------------End-------------------")
            }
        }
    }


    private fun singleWrite(loop: Int, item: StorageBenchmark) {
        val startTime = System.nanoTime()
        for (i in 0 until loop) {
            item.singleWrite(singleDatas[i])
        }
        val endTime = (System.nanoTime() - startTime) / 1000000.0
        logCost(item, "SingleWrite", loop, endTime)
    }


    private fun singleRead(loop: Int, item: StorageBenchmark) {
        val startTime = System.nanoTime()
        for (i in 0 until loop) {
            item.singleRead(singleDatas[i].name)
        }
        val endTime = (System.nanoTime() - startTime) / 1000000.0
        logCost(item, "SingleRead", loop, endTime)
    }


    private fun testUpdate(loop: Int, item: StorageBenchmark) {
        val startTime = System.nanoTime()
        for (i in 0 until loop) {
            item.singleUpdate(singleDatas[i].name, singleDatas[i].name + "_new")
        }
        val endTime = (System.nanoTime() - startTime) / 1000000.0
        logCost(item, "Update Single", loop, endTime)
    }


    private fun testDelete(loop: Int, item: StorageBenchmark) {
        val startTime = System.nanoTime()
        for (i in 0 until loop) {
            item.singleDelete(singleDatas[i].name + "_new")
        }
        val endTime = (System.nanoTime() - startTime) / 1000000.0
        logCost(item, "Delete Single", loop, endTime)
    }

    private fun deleteAll(loop: Int, item: StorageBenchmark) {
        item.deleteAll()
    }

    private fun batchWrite(loop: Int, item: StorageBenchmark) {
        val startTime = System.nanoTime()
        item.batchWrite(batchDatas)
        val endTime = (System.nanoTime() - startTime) / 1000000.0
        logCost(item, "BatchWrite", loop, endTime)
    }

    private fun batchRead(loop: Int, item: StorageBenchmark) {
        val names = mutableListOf<String>()
        for (i in batchDatas) {
            names.add(i.name)
        }
        val startTime = System.nanoTime()
        item.batchRead(names)
        val endTime = (System.nanoTime() - startTime) / 1000000.0
        logCost(item, "BatchRead", loop, endTime)
    }

    private fun logCost(item: StorageBenchmark, testCase: String, loop: Int, endTime: Double) {
        Log.i("YLG", "${item.javaClass.simpleName} $testCase | loop=$loop | cost=${formatter.format(endTime)} ms | avgCost=${formatter.format(endTime/loop)} ms ")
    }


    private fun prepareTestData(loop: Int) {
        singleDatas.clear()
        batchDatas.clear()
        for (i in 0 until loop) {
            val s = object : TestData {
                override fun getName(): String {
                    return "name_$i"
                }

                override fun getAge(): Int {
                    return i
                }

                override fun getTimestamp(): Long {
                    return System.currentTimeMillis()
                }
            }
            val b = object : TestData {
                override fun getName(): String {
                    return "name_$i"
                }

                override fun getAge(): Int {
                    return i
                }

                override fun getTimestamp(): Long {
                    return System.currentTimeMillis()
                }
            }

            singleDatas.add(s)
            batchDatas.add(b)
        }
    }

}