package com.mbe.rika.test04;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * @author Rika
 * 这是判断出牌的类  包含有判断自身类型的方法
 */
public class typep {


    int len;                    //ixp.length
    int type = 0;               //牌的种类
    int value;                  //该牌型的大小,定义出来但是还用不到
    int arr[];
    int dsdx_fst;               //第一张卡牌的大小
    boolean adddai = false;           //带的牌是否连续


    int count3 = 0;    //三张同点数的次数
    int count2 = 0;    //二张同点数的次数
    int count1 = 0;    //单牌的次数

    kapai[] ixp;

    //构造函数
    public typep(kapai[] ixp) {
        this.ixp = ixp;
        this.len = ixp.length;
        this.dsdx_fst = ixp[0].dsdx;
    }

    //重载的构造函数,传入AI对象,返回手牌分析
    players AI;

    public typep(players Ai) {

        this.AI = Ai;
        this.ixp = Ai.shoupai;

    }

    //分析AI的手牌
    public void AIDeep() {
        //先排除剩下单牌和对子的情况

        //再排除最大的牌是大小王的情况,放在最左边不动了.
        //有大小王,先移出大小王,再排序
        //排序后直接接到左边.

        //来构造arr
        findre();
//        //来排序
//        AIpaixu();

        //判断是否有王炸,炸弹
        setBaseAls();

//        //来构造超级的rearr
//        reArr();
    }


    public void setTypePro() {

        //先排除单牌和对子
        if (ixp.length == 1) {
            this.type = 111;                      //单牌
            this.value = ixp[0].dsdx;
            return;
        }
        if (ixp.length == 2) {
            if (ixp[0].dsdx == ixp[1].dsdx) {
                this.type = 122;                   //对子
                this.value = ixp[0].dsdx;
                return;
            } else if ((ixp[0].dsdx == 20) && (ixp[1].dsdx == 18)) {       //王炸
                this.type = 510;
                this.value = 510;
                return;
            } else {
                this.type = -1;
                return;
            }
        }


        //构建arr数组
        findre();

        //直接排除掉arr.length==1的情况
        if (arr.length == 1) {
            if (ixp.length == 3) {
                this.type = 133;                  //三带零
                this.value = 3 * ixp[0].dsdx;
                return;
            }
            if (ixp.length == 4) {
                this.type = 144;                   //炸弹
                this.value = 4 * ixp[0].dsdx * 100;
                return;
            } else {
                this.type = -1;
                return;
            }
        }

        //优化视觉
        paixu();

        //测试
        String arr_paixu = "";
        for (int i = 0; i < arr.length; i++) {
            arr_paixu = arr_paixu + " " + arr[i];
        }
        //测试结束

        //构建rearr
        reArr();

        Log.i("MYC", "rearr构建完成,rearr是:" + rearr);

//        //构建countX变量
//        gaofj();

        //实锤类型
        setFinely();

        bobao();
    }


    //播报牌的种类
    public void bobao() {
        switch (type) {                        //   rearr卡牌头的重复数(MAX(arr)的重复次数) ,MAX(arr)最多重复数,MIN(arr)最少重复数
            case -1:
                Log.i("MYC", "出错了!!");
                break;
            case 111:

                Log.i("MYC", "单牌!!");    //111

                break;
            case 122:
                Log.i("MYC", "对子!!");    //122

                break;
            case 133:
                Log.i("MYC", "三带零!!");

                break;
            case 131:
                Log.i("MYC", "三带一!!"); //231

                break;
            case 144:
                Log.i("MYC", "炸弹!!"); //144

            case 244:
                Log.i("MYC", "二连炸");

                break;
            case 344:
                Log.i("MYC", "三连炸");

                break;
            case 444:
                Log.i("MYC", "四连炸");

                break;
            case 544:
                Log.i("MYC", "五连炸");

                break;
            case 511:
                Log.i("MYC", "顺子!!");    //511

                break;
            case 611:
                Log.i("MYC", "6顺子!!");

                break;
            case 711:
                Log.i("MYC", "7顺子!!");

                break;
            case 811:
                Log.i("MYC", "8顺子!!");

                break;
            case 911:
                Log.i("MYC", "9顺子!!");

                break;
            case 1011:
                Log.i("MYC", "10顺子!!");

                break;
            case 1111:
                Log.i("MYC", "11顺子!!");

                break;
            case 1211:
                Log.i("MYC", "12顺子!!");

                break;
            case 132:
                Log.i("MYC", "三带一对!!"); //232

                break;
            case 322:
                Log.i("MYC", "三连对!!"); //322

                break;
            case 422:
                Log.i("MYC", "四连对!!"); //422

                break;
            case 522:
                Log.i("MYC", "五连对!!"); //522

                break;
            case 622:
                Log.i("MYC", "六连对!!"); //622

                break;
            case 722:
                Log.i("MYC", "七连对!!"); //722

                break;
            case 822:
                Log.i("MYC", "八连对!!"); //822

                break;
            case 922:
                Log.i("MYC", "九连对!!"); //922

                break;
            case 1022:
                Log.i("MYC", "十连对!!"); //1022

                break;
            case 232:
                Log.i("MYC", "二连带对!!"); //432或341

                break;
            case 332:
                Log.i("MYC", "三连带对!!"); //632或

                break;
            case 432:
                Log.i("MYC", "四连带对!!"); //832或

                break;
            case 231:
                Log.i("MYC", "二连带一!!"); //431或342

                break;
            case 331:
                Log.i("MYC", "三连带一!!"); //631或

                break;
            case 431:
                Log.i("MYC", "四连带一!!"); //831或

                break;

            default:
                Log.i("MYC", "-----------------------------------------无法判断的牌型 请咨询客服人员解决");

                break;
        }
    }


    /**
     * author Rika
     * 判断牌中重复的牌数(要先理牌)
     * 例如2,2,3,3,3这样的牌,返回2,3到arr[]
     */

    public void findre() {
        int count = 1;            //相同的牌的数量
        arr = new int[1];
        int j = 0;
        int[] a2;                  //临时数组

        //测试
        String ixp_temp = "";
        for (int i = 0; i < ixp.length; i++) {
            ixp_temp = ixp_temp + " " + ixp[i].outword;
        }
        Log.i("MYC", "findre启动,传入的ixp是" + ixp_temp);

        //测试结束

        for (int i = 1; i < ixp.length; i++) {
            if (ixp[i - 1].dsdx == ixp[i].dsdx) {
                count = count + 1;
                arr[j] = count;
//				System.out.println(ixp.length+"count"+count);
//				System.out.println("arr[j]是"+arr[j]+" "+"J是"+j);

            } else {

                arr[j] = count;

                j++;

                count = 1;


                a2 = new int[arr.length + 1];
                System.arraycopy(arr, 0, a2, 0, arr.length);
                a2[arr.length] = 1;
                arr = a2;
            }
        }
        //测试
        String test00 = "";
        for (int l = 0; l < ixp.length; l++) {
            test00 = test00 + " " + ixp[l].dsdx;
        }
        String test01 = "";
        for (int l = 0; l < arr.length; l++) {
            test01 = test01 + " " + arr[l];
        }
        Log.i("MYC", "小步骤打印ixp:" + test00);
        Log.i("MYC", "小步骤打印arr:" + test01);

    }
//
//    /**
//     * author Rika
//     * 判断有几个带,几个对,几个单牌,返回在countX
//     */
//    public void gaofj() {
//        int sum = 0;
//        int up_dai = 0;
//
//
//        for (int i = 0; i < arr.length; i++) {
//            sum = sum + arr[i];
//            if (arr[i] == 4) {
//                this.type = 4;                                                //筛选出炸弹
//                this.value = ixp[sum - 1].dsdx + 100;
////				System.out.println("type="+type);
//            }
//            if (arr[i] == 3) {
//                count3++;
//                //ixp[sum].dsdx=ixp[sum].dsdx<<5;
//                ixp[sum - 1].dai = true;
//                if (count3 == 1) {
//                    up_dai = ixp[sum - 1].dsdx;
//                    adddai = true;
//                } else {
//                    if (adddai == true && (up_dai + 1) == ixp[sum - 1].dsdx) {
//
//                    } else {
//                        adddai = false;
//                    }
//                }
//            }
//            if (arr[i] == 2) {
//                count2++;
//            }
//            if (arr[i] == 1) {
//                count1++;
//            }
//        }
//
//
//    }

    //对出牌的项目进行排序
    public void paixu() {

        Log.i("MYC", "----------------------进入了排序");

        if (arr.length < 2) {
            return;
        }

        int temp;
        kapai mtemp;
        int sum = 0;


//        //测试用变量
//        String test00;
//        String test01;
//
//        //测试
//        test00 = "";
//        for (int l = 0; l < ixp.length; l++) {
//            test00 = test00 + " " + ixp[l].dsdx;
//        }
//        test01 = "";
//        for (int l = 0; l < arr.length; l++) {
//            test01 = test01 + " " + arr[l];
//        }
//        Log.i("MYC", "排序最内层ixp:" + test00);
//        Log.i("MYC", "排序最内层arr:" + test01);
//        //测试完毕


        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] < arr[j + 1]) {

                    temp = arr[j];

                    for (int count = 0; count < arr[j]; count++) {


                        sum = 0;
                        for (int p = 0; p < j; p++) {
                            sum += arr[p];
                        }


                        mtemp = ixp[sum + count];
                        ixp[sum + count] = ixp[sum + arr[j] + arr[j + 1] - 1 - count];
                        ixp[sum + arr[j] + arr[j + 1] - 1 - count] = mtemp;


                    }

                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }

            }
        }

//        //测试
//        test00 = "";
//        for (int l = 0; l < ixp.length; l++) {
//            test00 = test00 + " " + ixp[l].dsdx;
//        }
//        test01 = "";
//        for (int l = 0; l < arr.length; l++) {
//            test01 = test01 + " " + arr[l];
//        }
//        Log.i("MYC", "排序结束后ixp:" + test00);
//        Log.i("MYC", "排序结束后arr:" + test01);
//        //测试完毕
//
//        Log.i("MYC", "--------------------排序完了");
    }

    //对AI的手牌进行排序分析

    //是否有王炸
    int DanNum; //有几个单牌
    int DuiNum; //有几个对子
    int DaiNum; //有几个带
    Map Shunzi;     //有没有顺子
    Map ContiDui;   //有没有连对
    Map ContiDai;   //有没有连带
    int ZhaNum;    //有几个炸弹
    Map ContiZha;   //有没有连炸
    boolean king = false;    //是否有王炸

    //进行判断
    int q = 0, w = 0, e = 0, r = 0;

    private void setBaseAls() {


        if (arr.length >= 2) {
            if (ixp[0].dsdx == 20 && ixp[1].dsdx == 18) {
                king = true;
            }
        }

        if (king) {
            for (int i = 2; i < arr.length; i++) {
                switch (arr[i]) {
                    case 1:
                        DanNum++;
//                        DanNum.put(q, ixp[GameUtil.getIndex(i, arr)].dsdx);   //------单牌来了
//                        q++;
                        break;
                    case 2:
                        DuiNum++;
//                        DuiNum.put(w, ixp[GameUtil.getIndex(i, arr)].dsdx);   //-------对来了
//                        w++;
                        break;
                    case 3:
                        DaiNum++;
//                        DaiNum.put(e, ixp[GameUtil.getIndex(i, arr)].dsdx);   //-------带来了
//                        e++;
                        break;
                    case 4:
                        ZhaNum++;
//                        ZhaNum.put(r, ixp[GameUtil.getIndex(i, arr)].dsdx);   //-------炸来了
//                        r++;
                        break;
                }

            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                switch (arr[i]) {
                    case 1:
                        DanNum++;
//                        DanNum.put(q, ixp[GameUtil.getIndex(i, arr)].dsdx);   //------单牌来了
//                        q++;
                        break;
                    case 2:
                        DuiNum++;
//                        DuiNum.put(w, ixp[GameUtil.getIndex(i, arr)].dsdx);   //-------对来了
//                        w++;
                        break;
                    case 3:
                        DaiNum++;
//                        DaiNum.put(e, ixp[GameUtil.getIndex(i, arr)].dsdx);   //-------带来了
//                        e++;
                        break;
                    case 4:
                        ZhaNum++;
//                        ZhaNum.put(r, ixp[GameUtil.getIndex(i, arr)].dsdx);   //-------炸来了
//                        r++;
                        break;
                }

            }
        }
    }
    //判断结束

    public void AIpaixu() {

        Log.i("MYC", "----------------------进入了排序");

        if (arr.length < 2) {
            Log.i("MYC", "手牌arr.length小于2,退出排序");
            return;
        }

        int temp;
        kapai mtemp;
        int sum = 0;


        //测试用变量
        String test00;
        String test01;

        //测试
        AI.bobao();

        test00 = "";
        for (int l = 0; l < ixp.length; l++) {
            test00 = test00 + " " + ixp[l].dsdx;
        }
        test01 = "";
        for (int l = 0; l < arr.length; l++) {
            test01 = test01 + " " + arr[l];
        }
        Log.i("MYC", "排序最内层ixp:" + test00);
        Log.i("MYC", "排序最内层arr:" + test01);
        //测试完毕


        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //不对大小王的位置进行排序
                if (j < 2) {
                    if (ixp[0].dsdx == 20 && ixp[1].dsdx == 18) {
                        king = true;
                        continue;
                    }
                }

                //进行正常排序
                if (arr[j] < arr[j + 1]) {

                    temp = arr[j];

                    for (int count = 0; count < arr[j]; count++) {


                        sum = 0;
                        for (int p = 0; p < j; p++) {
                            sum += arr[p];
                        }


                        mtemp = ixp[sum + count];
                        ixp[sum + count] = ixp[sum + arr[j] + arr[j + 1] - 1 - count];
                        ixp[sum + arr[j] + arr[j + 1] - 1 - count] = mtemp;


                    }

                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }

            }
        }

        //测试
        AI.bobao();

        test00 = "";
        for (int l = 0; l < ixp.length; l++) {
            test00 = test00 + " " + ixp[l].dsdx;
        }
        test01 = "";
        for (int l = 0; l < arr.length; l++) {
            test01 = test01 + " " + arr[l];
        }
        Log.i("MYC", "排序结束后ixp:" + test00);
        Log.i("MYC", "排序结束后arr:" + test01);
        //测试完毕

        Log.i("MYC", "--------------------排序完了");
    }

    //对arr的重复,再次进行重复的判断,并且返回重复部分是否连续.
    int rearr = 0;

    public void reArr() {

        if (arr.length < 2) {
            rearr = 1;
            return;
        }
        if (arr[0] == arr[1]) {
            rearr = 1;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] == arr[i]) {

                    rearr = rearr + 1;

                } else {

                    return;

                }
            }

            Log.i("MYC", "rearr=" + rearr);


        } else {

            rearr = 1;

        }


    }


    //   arr.length 卡牌点数的种类 ,MAX(arr)最多重复数,MIN(arr)最少重复数
    public void settype() {
        int arr_temp[];
        arr_temp = arr;
        GameUtil.paixu(arr_temp);
        if (count3 == 0 || adddai) {
            this.type = arr.length * 100 + arr_temp[arr_temp.length - 1] * 10 + arr_temp[0];
        } else {
            this.type = -1;
        }
    }

    public void setFinely() {
        if (rearr == 1) {
            if (arr.length > 2) {
                this.type = -1;                                   //排除情况一
                return;
            } else if (arr[0] == 3) {                                             //这情况下arr.length肯定是2, 那只能是带.
                if (arr[1] == 1) {
                    this.type = 131;
                    this.value = ixp[0].dsdx + 35;                      //---------------------------三带一
                    return;
                } else if (arr[1] == 2) {
                    this.type = 132;
                    this.value = ixp[0].dsdx + 35;                      //---------------------------三带二
                    return;
                } else {
                    this.type = -1;                               //排除情况34
                    return;
                }
            } else {
                this.type = -1;                                   //排除情况二
                return;
            }
        }

        if (rearr > 1) {
            switch (arr[0]) {
                case 1:                                         //选出顺子
                    if (rearr < 5 || (ixp.length != rearr)) {     //排除少于五张的顺子
                        this.type = -1;
                        return;
                    }
                    for (int i = 1; i < ixp.length; i++) {
                        if (ixp[i - 1].dsdx - 1 != ixp[i].dsdx) {
                            this.type = -1;               //排除不连续的顺子
                            return;
                        }
                    }
                    this.type = rearr * 100 + 11;                                           //--------------------顺子
                    this.value = ixp[0].dsdx + 50 + (rearr - 5) * 15;
                    return;

//                    Log.i("Error","case 1 is no return");
                case 2:                  //选出连对
                    if (rearr < 3) {                            //排除少于3连的连对
                        this.type = -1;
                        return;
                    }

                    for (int i = 0; i < arr.length; i++) {     //排除不是由对子组成的
                        if (arr[i] != 2) {
                            this.type = -1;
                            return;
                        }
                    }

                    for (int i = 2; i < ixp.length; i = i + 2) {             //排除不连续的连对
                        if ((ixp[i - 2].dsdx - 1) != ixp[i].dsdx) {
                            this.type = -1;
                            return;
                        }
                    }

                    this.type = rearr * 100 + 22;                                                 //------------------连对
                    this.value = ixp[0].dsdx + 160 + (rearr - 3) * 15;
                    return;


                case 3:                    //选出连带
                    if (arr.length != (rearr * 2)) {      //排除牌数不符的
                        this.type = -1;
                        return;
                    }
                    for (int i = rearr; i < arr.length - 1; i++) {
                        if (arr[i] != arr[i + 1]) {
                            this.type = -1;             //排除飞机带的牌数量不同的
                            return;
                        }
                    }
                    for (int i = 3; i < 3 * rearr; i = i + 3) {       //排除飞机不连续的
                        if ((ixp[i - 3].dsdx - 1) != ixp[i].dsdx) {
                            this.type = -1;
                            return;
                        }
                    }
                    if (arr[rearr] == 1) {
                        this.type = rearr * 100 + 31;                                             //-------------------------连续三带一
                        this.value = ixp[0].dsdx + 300 + (rearr - 2) * 15;
                        return;
                    }
                    if (arr[rearr] == 2) {
                        this.type = rearr * 100 + 32;                                             //-------------------------连续三带二
                        this.value = ixp[0].dsdx + 300 + (rearr - 2) * 15;
                        return;
                    }
                    Log.i("Error", "case 3 is no return");
                case 4:
                    if (arr.length != rearr) {      //排除长度不对的
                        this.type = -1;
                        return;
                    }
                    for (int i = 4; i < ixp.length; i = i + 4) {        //排除不连续的
                        if ((ixp[i - 4].dsdx - 1) != ixp[i].dsdx) {
                            this.type = -1;
                            return;
                        }
                    }
                    this.type = rearr * 100 + 44;                                                 //--------------------------连续炸弹
                    this.value = ixp[0].dsdx + 450 + (rearr - 1) * 15;
                    return;
            }


        }
    }

    //来 判断一下牌堆的连续性
    private void Conti() {

    }

//
//	/**
//	 * author Rika
//	 * 判断玩家牌型的 总 方法   *要先理牌
//	 */
//	public void settype(kapai[] ixp){
//
//		//牌堆的点数的和,赋值给temp2
//
//		if(ixp.length==1){
//			type=1;//单牌!!
//			this.value=ixp[0].dsdx;
//
//		}else if(ixp.length==2&&arr.length==1){
////			System.out.println("这不是单牌!!);
//			type=2;//对子!!
//			this.value=ixp[0].dsdx;
//		}else if(ixp.length==4){
////			System.out.println("这也不是对子!!");
//			//if(arr.length==1){
//			//type=4;//炸弹!!}
//
//
//		}else if(ixp.length==count1){
////			System.out.println("这也不是炸弹!!");
//			type=5;//一条龙!!
//		}else if(ixp.length==(arr.length<<1)&&arr.length>2){
////			System.out.println("这是连对!!");
//			type=20+arr.length;
//		}
//
//		System.out.println("31-type="+type);
//
//		dai(ixp);
//
//		System.out.println("32-type="+type);
//
//		if(type==0){
////			System.out.println("这什么都不是!!");
//			type=-1;
//		}
//
//	}

}
