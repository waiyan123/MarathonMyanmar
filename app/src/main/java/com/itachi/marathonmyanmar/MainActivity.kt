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

        //1st question
//        val array = arrayOf("a","b","c")
//        Log.d("test---","1)"+stripUrlParams("https://google.com?a=1&b=2&a=2",array))
//        Log.d("test---","2)"+stripUrlParams("https://google.com?a=1&b=2"))
//        Log.d("test---","3)"+stripUrlParams("https://google.com"))
//        Log.d("test---","4)"+stripUrlParams("https://google.com?a=1&b=2&a=2&b=1&c=1&b=3",array))


        //2nd question
        val oneArray = arrayOf("a -1 23","b234","c345-1")
        val twoArray = arrayOf(
            arrayOf("a1","b2","c3"),
            arrayOf("d4","e5","f6")
        )
        val threeArray = arrayOf(
            arrayOf(
                arrayOf("a1","b2","c3"),
                arrayOf("d4","e5","f6"),
                arrayOf("g7","h8","i9")
            )
        )

        Log.d("test---","sum "+sum())
        Log.d("test---","sum "+sum(oneArray))
//        Log.d("test---","sum "+sum(twoDArray = twoArray))
//        Log.d("test---","sum "+sum(threeDArray = threeArray))

    }

    // for 1st question
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

    //for 2nd question
    fun sum (oneDArray : Array<String> = emptyArray(),
             twoDArray : Array<Array<String>> = emptyArray(),
             threeDArray : Array<Array<Array<String>>> = emptyArray()) : Int {

        val strList = ArrayList<String>()

        var result = 0
        if(oneDArray.isNotEmpty()) {
            strList.clear()
            for(str in oneDArray) {
                strList.add(str)
            }
        }
        else if(twoDArray.isNotEmpty()) {
            strList.clear()
            for(firstAry in twoDArray) {
                for(str in firstAry) {
//                    Log.d("test---",str)
                    strList.add(str)
                }
            }
        }

        else if(threeDArray.isNotEmpty()) {
            strList.clear()
            for(firstAry in threeDArray) {
                for(secondAry in firstAry) {
                    for(str in secondAry) {
//                        Log.d("test---",str)
                        strList.add(str)
                    }
                }
            }
        }

        if(strList.isNotEmpty()) {
            for(str in strList) {
                var numberStr = ""

                for(i in str.indices) {
                    val c = str[i]

                    if(c in '0'..'9') {
                        numberStr += c

                        if(i== str.length-1) {

                            if(i!=0 && str[i-1].equals('-')) {
                                result -= numberStr.toInt()
                                Log.d("test---","negative $i")
                            }
                            else {
                                Log.d("test---","index $i")
                                result += numberStr.toInt()
                            }
                        }
                    }
                    else if( !numberStr.isNullOrBlank()) {
                        if(i!=0 && str[i-1].equals('-')) {
                            Log.d("test---","negative $i")
                            result -= numberStr.toInt()
                        }
                        else {
                            Log.d("test---","index $i")
                            result += numberStr.toInt()
                        }
                        numberStr = ""
                    }
                }
            }
        }
        return result
    }
}
