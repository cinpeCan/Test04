package com.mbe.rika.test04;
/**
 * @author Rika
 */

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Random;

import static android.view.Gravity.CENTER_VERTICAL;

public class dapaiActivity extends AppCompatActivity {
    int vv;
    public Context context;
    FrameLayout spbom;
    FrameLayout P1cp;
    FrameLayout P2cp;
    FrameLayout P3cp;
    FrameLayout DP;
    TextView P1yaobuqi;
    TextView P2yaobuqi;
    TextView P3yaobuqi;
    TextView P2shengyu;
    TextView P3shengyu;

    pingtai table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dapai);

        //获得上下文
        context = getApplicationContext();

        table = new pingtai();
        Log.i("MYC", "平台创建成功");
        spbom = findViewById(R.id.shoupai);             //玩家一手牌域
        P1cp = findViewById(R.id.P1cp);                 //玩家一出牌域
        P2cp = findViewById(R.id.P2cp);                 //玩家二出牌域
        P3cp = findViewById(R.id.P3cp);                 //玩家三出牌域
        DP = findViewById(R.id.DP);                    //底牌域
        P1yaobuqi = findViewById(R.id.P1yaobuqi);       //玩家一要不起提示
        P2yaobuqi = findViewById(R.id.P2yaobuqi);       //玩家二要不起提示
        P3yaobuqi = findViewById(R.id.P3yaobuqi);       //玩家三要不起提示
        P2shengyu = findViewById(R.id.P2shengyu);       //玩家二剩余手牌提示
        P3shengyu = findViewById(R.id.P3shengyu);       //玩家三剩余手牌提示


        Log.i("MYC", "域获取成功");

        table.player01.drawSP(context);
        Log.i("MYC", "手牌卡图初始化成功");
        table.drawDP(context);
        Log.i("MYC", "底牌卡图初始化成功");

        btn_cp = findViewById(R.id.cpButton);        //出牌按钮
        btn_bc = findViewById(R.id.bcButton);        //不出按钮
        btn_ts = findViewById(R.id.tsButton);        //提示按钮


        //手牌注入
        for (int i = 0; i < table.player01.shoupai.length; i++) {
            spbom.addView(table.player01.shoupai[i].katu);
            Log.i("MYC", "第" + i + "张牌构建成功");
        }
        //底牌注入
        for (int i = 0; i < table.dipai.length; i++) {
            DP.addView(table.showDP[i].katu);
            Log.i("MYC", "第" + i + "张底牌构建成功");
        }


        //---------------------------界面初始化完成-----------------------------------

//        kapai vv = new kapai(52);
//
//        vv.DrawSelf(context,3,18);
//
//        spbom.addView(vv.katu);


    }


//    private void newView(int in) {
//
//        ImageView aCard = new ImageView(context);
//        FrameLayout.LayoutParams ofCard = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//        ofCard.leftMargin = 40;
//        ofCard.gravity = CENTER_VERTICAL;
//        //选牌时监听动作
//        final View.OnClickListener Click = new View.OnClickListener() {
//
//            public void onClick(View v) {
//                xuanpai(v);
//            }
//        };
//        //添加监听
//        aCard.setOnClickListener(Click);
//
//        aCard.setLayoutParams(ofCard);
//        aCard.setImageResource(in);
//
//        spbom.addView(aCard);
//    }

    //    //点击动作
//    boolean up = false;            //升起来了
//    int step = 30;                //左右间隔
//
//    public void xuanpai(View view) {
//
//        Toast.makeText(context, "调用了选牌监听", Toast.LENGTH_SHORT).show();
//
//
//        if (up) {
//            view.setTranslationY(0);
//        } else {
//            view.setTranslationY(-20);
//        }
//        up = !up;
//    }
    typep mytype;   //当前出牌的类型

    //出牌按钮
    public void text(View view) {

        Log.i("MYC", "进行出牌");

        table.player01.xuanpai();
        Log.i("MYC","打牌区xp[0]"+table.player01.xp[0].outword);

        mytype = new typep(table.player01.xp);
        mytype.setTypePro();



        if (!GameUtil.bidaxiao(up_cp, table.player01.xp, up_typep, mytype, context)) {
            Toast.makeText(context, "非法出牌,请重新出牌喵(>^ω^<)", Toast.LENGTH_SHORT).show();
            return;                                                 //退出方法
        }

        table.player01.chupai(spbom);
        ZhanShiCP(table.player01);

        //出完牌的后续工作
        this.up_cp = table.player01.cp;   //把该家的出牌记录下来
        this.up_typep = mytype;

        mytype = null;

        table.player01.xp = null;
        table.player01.cp = null;




        view.setVisibility(View.INVISIBLE);                          //按钮消失
        view.setEnabled(false);
        btn_ts.setVisibility(View.INVISIBLE);                          //按钮消失
        btn_ts.setEnabled(false);
        btn_bc.setVisibility(View.INVISIBLE);                          //按钮消失
        btn_bc.setEnabled(false);

//        playing(PSet);
//        playing(PSet);

        //如果拍都出完了,游戏结束!!!!!!!!!!
        if (table.player01.shoupai.length < 1) {
            Toast.makeText(context, "大吉大利!!!今晚吃石锅鱼!!!", Toast.LENGTH_LONG).show();
            return;
        }

        PSet = 2;
        new P2T().start();

    }

    //不出按钮
    boolean tiaoguole = false;

    public void text_bc(View btn_bc) {

        if (up_cp == null) {
            Toast.makeText(context, "第一位玩家请出牌喵(>^ω^<)", Toast.LENGTH_SHORT).show();
            return;
        }

        btn_cp.setVisibility(View.INVISIBLE);                          //按钮消失
        btn_cp.setEnabled(false);
        btn_ts.setVisibility(View.INVISIBLE);                          //按钮消失
        btn_ts.setEnabled(false);
        btn_bc.setVisibility(View.INVISIBLE);                          //按钮消失
        btn_bc.setEnabled(false);
        P1yaobuqi.setVisibility(View.VISIBLE);

        table.player01.AIcant = true;
//        tiaoguole = true;

//        this.up_cp=null;
//        this.up_typep=null;

        PSet = 2;

        new P2T().start();

    }

    //提示按钮
    public void text_ts(View btn_ts) {
        Toast.makeText(this, "真拿你没办法,就提示你一下喵(>^ω^<)", Toast.LENGTH_SHORT).show();

        table.player01.AI_xp_initi();
        table.player01.AITest(up_cp, up_typep);

        for (int i = 0; i < table.player01.shoupai.length; i++) {
            if (table.player01.shoupai[i].up) {
                table.player01.shoupai[i].katu.setTranslationY(-30); //提示功能试验
            }
        }
    }


    public void ZhanShiCP(players player) {
        switch (player.set) {
            case 1:
                Log.i("MYC", "进入了展示流程");
//                P1cp.removeAllViews();
                for (int i = 0; i < player.cp.length; i++) {
//                    Log.i("MYC", "进入了循环,cp长度为" + player.cp.length + " katu内容为" + player.cp[i].katu);

                    //重置下卡图
                    player.cp[i].show(context, i, 26, 2);

                    //把卡图加入出牌展示区域
                    P1cp.addView(player.cp[i].katu);
                }

                break;
            case 2:

                Log.i("MYC", "玩家二展示手牌");
                P2cp.removeAllViews();

                P2yaobuqi.setVisibility(View.INVISIBLE);

                try {
                    k = new Random();
                    Thread.sleep(k.nextInt(3000));
                    Log.i("MYC", "玩家二延迟了几秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < player.cp.length; i++) {
                    player.cp[i].show(context, i, 26, 2);
                    P2cp.addView(player.cp[i].katu);

                }
                P2shengyu.setText(""+table.player02.shoupai.length);

                Log.i("MYC", "玩家二展示手牌/结束");

                break;
            case 3:
                Log.i("MYC", "玩家三展示手牌");
                P3cp.removeAllViews();

                P3yaobuqi.setVisibility(View.INVISIBLE);

                try {
                    k = new Random();
                    Thread.sleep(k.nextInt(5000));
                    Log.i("MYC", "玩家三延迟了几秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < player.cp.length; i++) {
                    player.cp[i].show(context, i, 26, 2);
                    P3cp.addView(player.cp[i].katu);
                }
                P3shengyu.setText(""+table.player03.shoupai.length);

                Log.i("MYC", "玩家三展示手牌/结束");

                break;
        }

    }


    kapai[] up_cp;
    typep up_typep;
    Button btn_cp;    //出牌按钮
    Button btn_ts;    //提示按钮
    Button btn_bc;    //不出按钮
    int PSet = 1;         //第几个玩家


    //    public void playing(int set){
//
//        switch (set){
//            case 2:
//
//
//                table.player02.AITest();
//
//                table.player02.chupai();
//                ZhanShiCP(table.player02);
//
//                break;
//            case 3:
//
//
//                table.player03.AITest();
//
//                table.player03.chupai();
//                ZhanShiCP(table.player03);
//                cpButton.setVisibility(View.VISIBLE);
//                break;
//        }
//
//    }
    Random k;  //随机数  用来晃动时间

    //AI2线程
    public class P2T extends Thread {


        public void run() {
            while (true) {






                if (table.player01.AIcant && table.player03.AIcant) {   //新的一轮出牌,都初始化
                    Log.i("MYC","上下家都不要,由玩家二首先出牌");
                    table.player01.AIcant = false;
                    table.player02.AIcant = false;
                    table.player03.AIcant = false;
                    up_cp = null;
                    up_typep = null;
                }


                if (table.player02.cp == null) {
                    Log.i("MYC","玩家二的AI启动准备!!!");
                    if(table.player02.AIyao(up_cp,table.player01.AIcant,table.player02.AIcant)){
                        table.player02.AI_xp_initi();
                        table.player02.AITest(up_cp, up_typep);
                    }

                    if (!table.player02.AIcant) {   //要的起

                        table.player02.xuanpai();
                        Log.i("MYC", "玩家2的AI选牌运行结束,");
                        table.player02.chupai();

                        up_cp = table.player02.cp;   //把该家的出牌记录下来

                        typep p2_type = new typep(table.player02.cp);
                        p2_type.setTypePro();

                        up_typep = p2_type;

                        p2_type = null;

                        P2cp.post(new Runnable() {
                            public void run() {

                                ZhanShiCP(table.player02);


                                table.player02.xp = null;
                                table.player02.cp = null;


                            }
                        });

                        //如果牌都出完了,游戏结束!!!!!!!!!!
                        if (table.player02.shoupai.length < 1) {
                            Toast.makeText(context, "再接再厉!今晚回家吃吧!", Toast.LENGTH_LONG).show();
                            break;
                        }


                    } else {
                        P2cp.post(new Runnable() {
                            public void run() {


                                P2cp.removeAllViews();

                                try {
                                    k = new Random();
                                    Thread.sleep(k.nextInt(3000));
                                    Log.i("MYC", "玩家二延迟了几秒");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                P2yaobuqi.setVisibility(View.VISIBLE);

                            }
                        });
                    }

//                    if (!(table.player02.xp == null)) {


                }
                PSet = 3;
                new P3T().start();
                break;
            }
        }
    }

    //AI3线程
    public class P3T extends Thread {


        public void run() {
            while (true) {





                if (table.player01.AIcant && table.player02.AIcant) {   //新的一轮出牌,都初始化
                    Log.i("MYC","上下家都不要,由玩家三首先出牌");
                    table.player01.AIcant = false;
                    table.player02.AIcant = false;
                    table.player03.AIcant = false;
                    up_cp = null;
                    up_typep = null;
                }


                if (table.player03.cp == null) {
                    Log.i("MYC","玩家三的AI启动准备!!!");
                    if(table.player03.AIyao(up_cp,table.player01.AIcant,table.player02.AIcant)){
                        table.player03.AI_xp_initi();
                        table.player03.AITest(up_cp, up_typep);
                    }

                    if (!table.player03.AIcant) {


                        table.player03.xuanpai();
                        table.player03.chupai();

                        up_cp = table.player03.cp;   //把该家的出牌记录下来
                        typep p3_type = new typep(table.player03.cp);
                        p3_type.setTypePro();

                        up_typep = p3_type;

                        p3_type = null;

                        P2cp.post(new Runnable() {
                            public void run() {



                                ZhanShiCP(table.player03);
                                btn_cp.setVisibility(View.VISIBLE);
                                btn_cp.setEnabled(true);
                                btn_ts.setVisibility(View.VISIBLE);                          //按钮出现
                                btn_ts.setEnabled(true);
                                btn_bc.setVisibility(View.VISIBLE);                          //按钮出现
                                btn_bc.setEnabled(true);
                                P1cp.removeAllViews();
                                P1yaobuqi.setVisibility(View.INVISIBLE);

                                table.player03.xp = null;
                                table.player03.cp = null;
                            }
                        });

                        //如果牌都出完了,游戏结束!!!!!!!!!!
                        if (table.player02.shoupai.length < 1) {
                            Toast.makeText(context, "再接再厉!今晚回家吃吧!", Toast.LENGTH_LONG).show();
                            break;
                        }

                    } else {
                        P2cp.post(new Runnable() {
                            public void run() {


                                P3cp.removeAllViews();


                                try {
                                    k = new Random();
                                    Thread.sleep(k.nextInt(3000));
                                    Log.i("MYC", "玩家三延迟了几秒");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                P3yaobuqi.setVisibility(View.VISIBLE);

                                if (table.player02.AIcant && table.player03.AIcant) {   //新的一轮出牌,都初始化
                                    table.player01.AIcant = false;
                                    table.player02.AIcant = false;
                                    table.player03.AIcant = false;

                                    up_cp = null;
                                    up_typep = null;
                                }

                                btn_cp.setVisibility(View.VISIBLE);
                                btn_cp.setEnabled(true);
                                btn_ts.setVisibility(View.VISIBLE);                          //按钮出现
                                btn_ts.setEnabled(true);
                                btn_bc.setVisibility(View.VISIBLE);                          //按钮出现
                                btn_bc.setEnabled(true);
                                P1cp.removeAllViews();
                                P3cp.removeAllViews();
                                P1yaobuqi.setVisibility(View.INVISIBLE);


                            }
                        });
                    }

//                    if(!(table.player03.xp==null)) {


                }
                PSet = 1;
                break;
            }
        }
    }
}
