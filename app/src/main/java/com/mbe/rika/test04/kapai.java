package com.mbe.rika.test04;
/**
 * @author Rika
 */
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;


import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.Gravity.VERTICAL_GRAVITY_MASK;

public class kapai {
    int dianshu;//点数
    int dsdx;//点数大小
    String huase;//花色
    String outnum;//对外显示的点数
    String outword;//对外显示的卡牌名称
    boolean dai = false;//是否是三带一或三带二的带牌


    //该卡牌是否已添加按键监听
    boolean isLis = false;

    int value;  //卡牌编号
    int No;     //用来理牌的排序编号
    int zb_X;   //坐标X
    int zb_Y;   //坐标Y
    int speed;
    int width;
    int heigh;


    kapai(int value) {

        this.No=value;

        switch (value % 4) {
            case 0:
                this.huase = "黑桃";
                break;
            case 1:
                this.huase = "红桃";
                break;
            case 2:
                this.huase = "梅花";
                break;
            case 3:
                this.huase = "方片";
                break;
        }

        this.dianshu = (value >> 2) + 1;

        //赋予value能正确比大小的值,A,2,大小王的dsdx值
        if (dianshu == 1) {
            this.outnum = "A";
            this.dsdx = dianshu + 13;
            this.No=value+100;
        } else if (dianshu == 2) {
            this.outnum = dianshu + "";
            this.dsdx = dianshu + 14;
            this.No=value+101;
        } else if (dianshu < 11) {
            this.outnum = dianshu + "";
            this.dsdx = dianshu;
        } else if (dianshu < 14) {
            switch (dianshu) {
                case 11:
                    this.outnum = "J";
                    this.dsdx = dianshu;
                    break;
                case 12:
                    this.outnum = "Q";
                    this.dsdx = dianshu;
                    break;
                case 13:
                    this.outnum = "K";
                    this.dsdx = dianshu;
                    break;
            }
        } else if (value == 52) {
            this.outnum = "LITTLE JOKER";
            this.huase = "";
            this.dsdx = 18;
            this.No=value+200;
        } else {
            this.outnum = "BIG JOKER";
            this.huase = "";
            this.dsdx = 20;
            this.No=value+201;
        }

        outword = huase + outnum;

        this.value=value;


    }

    public void bobao() {

        Log.i("MYC", this.outword + " " + this.value);

    }

    //画出来 并且添加监听
    ImageView katu;

    int ID;     //对应的卡图ID值

    int IDtype;   //对应卡图的类型   0是底牌,1是手牌,2出牌   (该功能暂未实现,目前全用的是手牌)

    int NO = 0;     //在牌列中的位置

    int space = 26;    //两张牌左边界的间隔

    FrameLayout.LayoutParams ofCard;
    //手牌的画法
    public void DrawSelf(Context context,int NO,int space) {
        this.NO=NO;
        this.space=space;

        ID = GameUtil.getImageId(value,1);

        katu = new ImageView(context);

        ofCard = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        ofCard.leftMargin = space * NO;
        ofCard.gravity =BOTTOM;

        katu.setLayoutParams(ofCard);
        katu.setImageResource(ID);

        //添加监听
        //选牌时监听动作
        final View.OnClickListener Click = new View.OnClickListener() {

            public void onClick(View v) {
                xuanpai(v);
            }
        };
        //添加监听
        katu.setOnClickListener(Click);

    }

    //画出来 不是手牌的画法
    public void show(Context context,int NO,int space,int botton) {
        this.NO=NO;
        this.space=space;

        ID = GameUtil.getImageId(value,botton);

        katu = new ImageView(context);

        FrameLayout.LayoutParams ofCard = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        ofCard.leftMargin = space * NO;
        ofCard.gravity = CENTER_VERTICAL;

        katu.setLayoutParams(ofCard);
        katu.setImageResource(ID);

    }


    //点击动作
    boolean up = false;            //升起来了
    int step = 30;                //左右间隔
//    int sum = 0;

    public void xuanpai(View view) {


//        Toast.makeText(view.getContext(), "调用了选牌监听" + sum + "次", Toast.LENGTH_SHORT).show();

        if (up) {
            view.setTranslationY(0);


        } else {

            view.setTranslationY(-step);


        }
        up = !up;
//        sum++;
    }


}
