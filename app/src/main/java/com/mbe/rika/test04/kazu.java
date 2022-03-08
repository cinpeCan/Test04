package com.mbe.rika.test04;//package com.example.rika.mytest02.text001;
/**
 * @author Rika
 */
import android.content.Context;
import android.util.Log;

import java.util.Random;

public class kazu {

    public static final int lenth=54;

    kapai[] kapai;

    //构造一个卡组，并进行洗牌
    public kazu(){

        kapai=new kapai[54];
        for(int i=0;i<lenth;i++){
            kapai[i]=new kapai(i);//实例化每一张卡牌



            kapai[i].bobao();//播报每一张卡牌对象的创建


        }


        Log.i("MYC", "创建了一个卡组，卡组共有"+lenth+"张牌");

    }

    //打印卡组的方法
    public void dayin(){
        for(int i=0;i<this.kapai.length;i++){
            Log.i("MYC", this.kapai[i].outword);

        }
    }
    //打印卡组的方法02
    public void dayin(kapai[] v_kapai){
        for(int i=0;i<v_kapai.length;i++){
            Log.i("MYC", v_kapai[i].outword);
        }
    }

    //洗牌的方法
    public void xipai(){
        Log.i("MYC", "-----------------开始洗牌------------------");
        kapai[] temp=new kapai[kapai.length];
        System.arraycopy(kapai, 0, temp, 0, kapai.length);//创建个临时卡组temp，备份kapai。

        int temp_length=temp.length;

        Random k=new Random();
        int temp_k;

        for(int j=0;j<kapai.length;j++){

            if(temp_length>1){
                temp_k=k.nextInt(temp_length);
            }else{temp_k=0;}


            kapai[j]=temp[temp_k];
            System.arraycopy(temp, temp_k+1, temp, temp_k, temp_length-1-temp_k);
            temp[temp_length-1]=null;
            temp_length--;
        }
        Log.i("MYC","-----------------洗牌完了------------------");

    }


}
