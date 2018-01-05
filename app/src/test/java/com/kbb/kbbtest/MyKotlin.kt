package com.kbb.kbbtest

import android.util.Log
import okhttp3.Cookie
import org.junit.Test

/**
 * Created by longlong on 2017/11/17.
 * @ClassName: MyKotlin
 * @Description:
 * @Date 2017/11/17
 */
 class MyKotlin {

    @Test
     fun testFun() {
         var num = test(2,5);
        Log.e("TAG","sum:"+num);
    }

    private fun test( a:Int,b:Int):Int{
        return a+b;
    }

}