# MarathonMyanmar

1st question)

fun stripUrlParams(url : String, arrayParams : Array = emptyArray()) : String{

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

-----------------------------------------------------------------------------------------------------------------

2nd question)

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
                strList.add(str)
            }
        }
    }

    else if(threeDArray.isNotEmpty()) {
        strList.clear()
        for(firstAry in threeDArray) {
            for(secondAry in firstAry) {
                for(str in secondAry) {
                    strList.add(str)
                }
            }
        }
    }

    if(strList.isNotEmpty()) {
        for(str in strList) {
            var numberStr = ""
            var isNegative = false

            for(i in str.indices) {
                val c = str[i]

                if(c.equals('-')) {
                    isNegative = true
                }

                if(c in '0'..'9') {
                    numberStr += c

                    if(i== str.length-1) {

                        if(isNegative) {
                            result -= numberStr.toInt()
                        }
                        else {
                            result += numberStr.toInt()
                        }
                        isNegative = false
                    }
                }
                else if( !numberStr.isNullOrBlank()) {
                    if(isNegative) {
                        result -= numberStr.toInt()
                    }
                    else {
                        result += numberStr.toInt()
                    }
                    numberStr = ""
                    isNegative = false
                }
            }
        }
    }
    return result
}
