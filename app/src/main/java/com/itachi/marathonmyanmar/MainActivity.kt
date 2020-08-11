package com.itachi.marathonmyanmar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.os.Build
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val array = arrayOf("a","b","c")
        Log.d("test---","1)"+stripUrlParams("https://google.com?a=1&b=2&a=2",array))
        Log.d("test---","2)"+stripUrlParams("https://google.com?a=1&b=2"))
        Log.d("test---","3)"+stripUrlParams("https://google.com"))
        Log.d("test---","4)"+stripUrlParams("https://google.com?a=1&b=2&a=2&b=1&c=1&b=3",array))

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun stripUrlParams(url : String, arrayParams : Array<String> = emptyArray()) : String{

        var mUrl = url
        val lines = url.split("?")
        if(lines.size>=2) {

            val urlParams = lines[1].split("&")

            val keyList = ArrayList<String>()
            val valueList = ArrayList<String>()

            for(param in urlParams) {
                val keys = param.split("=")
                val key = keys[0]
                val value = keys[1]
                keyList.add(key)
                valueList.add(value)
            }

            var hashMap: HashMap<String, Int> = HashMap<String, Int>()
            for(i in keyList.indices) {
                hashMap.put(keyList[i],i)
                for (l in i+1 until keyList.size) {
                    if(keyList[i]==keyList[l]) {
                        hashMap.replace(keyList[i],l)
                    }
                }
            }
            for (param in arrayParams) {
                hashMap.keys.remove(param)
            }

            val keyListConfirmed = ArrayList<String>(hashMap.keys)
            val valueIndexConfirmed = ArrayList<Int>(hashMap.values)
            mUrl = ""

            for (i in 0..keyListConfirmed.size-1) {
                mUrl = mUrl+keyListConfirmed[i]+"="+valueList[valueIndexConfirmed[i]]
                if(i<keyListConfirmed.size-1) mUrl = mUrl +"&"
            }
            return lines[0]+"?"+mUrl
        }
        else {
            return url
        }

    }

}
