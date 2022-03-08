package com.mbe.rika.test04;//package com.example.rika.mytest02.text001;

import android.content.Context;
import android.util.Log;

/**
 * @author Rika
 */


public class pingtai {
    public kazu newkazu;
    public players player01;
    public players player02;
    public players player03;
    public kapai[] dipai;//底牌3张

//
//    //获取Players对象
//    public players getPlayer(int n) {
//
//        if (n == 1) {
//            return player01;
//        } else if (n == 2) {
//            return player02;
//        } else if (n == 3) {
//            return player03;
//        } else {
//            return player01;
//        }
//
//    }

    //平台要会指挥流程
    public pingtai() {
        //实例化卡组
        newkazu = new kazu();
        //洗牌
        newkazu.xipai();
        newkazu.xipai();
        newkazu.xipai();

        newkazu.dayin();
        //实例化3个玩家对象
        player01 = new players();
        player01.set=1;
        player02 = new players();//玩家下家
        player02.set=2;
        player03 = new players();//玩家上家
        player03.set=3;

        //发牌给三个玩家
        fapai();    //包含了理牌功能


        //展示底牌
        newkazu.dayin(dipai);


        //玩家抢地主,并自动理牌
        player01.qiangdz(dipai);

        Log.i("MYC","这是地主的牌 测试开始!!!");
        newkazu.dayin(player01.shoupai);
        Log.i("MYC","地主共有" + player01.shoupai.length);



        //画出玩家手牌
//		player01.drawSP(g);  以通过paint实现

        //打牌
        //获取玩家操作 传入数组对象xp中.     通过xp的长度,判断玩家出牌类型    已实现

//		1.地主先出牌
        //提示出牌, 并添加计时器(计时器变为可见)
//			P1A=true;
//			new P1().start();


//
//
//
//


        //积分(欢乐豆)操作

        //提示玩家

        //下一局准备挂起
    }

    //平台要会发牌   发到players对象的shoupai数组对象里
    public void fapai() {
        //直接发到players手上.
        System.arraycopy(newkazu.kapai, 0, player01.shoupai, 0, 17);            //发给第一个玩家
        System.arraycopy(newkazu.kapai, 17, player02.shoupai, 0, 17);            //发给第二个玩家
        System.arraycopy(newkazu.kapai, 34, player03.shoupai, 0, 17);            //发给第三个玩家
        dipai = new kapai[3];                                               //实例化底牌
        System.arraycopy(newkazu.kapai, 51, dipai, 0, 3);                        //配置底牌
        //测试发牌效果
        Log.i("MYC", "开始发牌 这是第一个玩家");
        newkazu.dayin(player01.shoupai);

        Log.i("MYC", "这是第二个玩家");
        newkazu.dayin(player02.shoupai);

        Log.i("MYC", "这是第三个玩家");
        newkazu.dayin(player03.shoupai);

        Log.i("MYC", "这是底牌");
        newkazu.dayin(dipai);

        //手牌要按大小顺序排列
        player01.lipai();
        player02.lipai();
        player03.lipai();
        //测试玩家理牌效果
        Log.i("MYC", "开始理牌  这是第一个玩家");
        newkazu.dayin(player01.shoupai);

        Log.i("MYC", "是第二个玩家");
        newkazu.dayin(player02.shoupai);

        Log.i("MYC", "这是第三个玩家");
        newkazu.dayin(player03.shoupai);

        Log.i("MYC", "这是底牌");
        newkazu.dayin(dipai);


    }

    //平台要会发起提示
    public void tishi(String tishi) {
        System.out.println(tishi);
    }


    kapai[] showDP=new kapai[3];
    public void drawDP(Context context) {

        int space = 100;                                //左右两张卡牌的间隔

        for (int i = 0; i < dipai.length; i++) {
            showDP[i]=new kapai(dipai[i].value);
            showDP[i].show(context,i,space,0);

        }

    }


//    //算出屏幕中心点的方法
//    int mid_X;
//    int mid_Y;
//
//    public void mid(int back_X, int back_Y) {
//        mid_X = back_X >> 1;
//        mid_Y = (back_Y >> 1) + 8;
//        System.out.println("mid_X,mid_Y" + mid_X + " " + mid_Y);
//
//    }

//    //三个玩家的出牌线程
//
//	 boolean P1A=false;
//	 boolean P2A=false;
//	 boolean P3A=false;
//
//
//	 Timer textF=new Timer();
//
//
//    //player01的线程
//	 	class P1 extends Thread{
//	 		public void run(){
//	 			while (P1A) {
//
//	 				textF.text.setVisible(true);
//
//	 				System.out.println("---------------这是玩家01出的回合 剩余"+textF.endT+"秒---------------");
//		 			try {
//
//			 			Thread.sleep(1000);
//			 			textF.endT--;
//
//		 				if(!(textF.endT.intValue()>0)){
//
//		 					player02.up_cp=player01.cp;		//把出牌内容递给下家
//
//		 					P1A=false;
//		 		 			textF.text.setVisible(false);
//		 					textF.endT=5;
//		 					P2A=true;
//		 					new P2().start();
//		 				}
//
//	 				} catch (InterruptedException e) {
//	 					e.printStackTrace();
//	 				}
//	 			}
//	 		}
//	 	}
//
//
//	 	//player02的线程
//	 	class P2 extends Thread{
//	 		public void run(){
//	 			while (P2A) {
//
//	 				textF.text.setVisible(true);
//	 				System.out.println("---------------这是玩家02出的回合 剩余"+textF.endT+"秒---------------");
//		 			try {
//
//			 			Thread.sleep(1000);
//			 			textF.endT--;
//
//
//
//
//
//		 				if(!(textF.endT.intValue()>0)){
//
//				 			player02.AI();
//				 			player02.action();
//
//		 					player03.up_cp=player02.cp;		//把出牌内容递给下家
//
//		 					P2A=false;
//		 		 			textF.text.setVisible(false);
//		 					textF.endT=5;
//		 					P3A=true;
//		 					new P3().start();
//		 				}
//
//	 				} catch (InterruptedException e) {
//	 					e.printStackTrace();
//	 				}
//	 			}
//	 		}
//	 	}
//	 	//player03的线程
//	 	class P3 extends Thread{
//	 		public void run(){
//	 			while (P3A) {
//
//	 				textF.text.setVisible(true);
//	 				System.out.println("---------------这是玩家03出的回合 剩余"+textF.endT+"秒---------------");
//		 			try {
//
//			 			Thread.sleep(1000);
//			 			textF.endT--;
//
//
//
//		 				if(!(textF.endT.intValue()>0)){
//
//				 			player03.AI();
//				 			player03.action();
//
//		 					player01.up_cp=player03.cp;		//把出牌内容递给下家
//
//		 					P3A=false;
//		 		 			textF.text.setVisible(false);
//		 					textF.endT=5;
//		 					P1A=true;
//		 					new P1().start();
//		 				}
//
//	 				} catch (InterruptedException e) {
//	 					e.printStackTrace();
//	 				}
//	 			}
//	 		}
//	 	}


}
