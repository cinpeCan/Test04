package com.mbe.rika.test04;//package com.example.rika.mytest02.text001;
/**
 * @author Rika
 */


import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;


public class players {
    //玩家首先要会抓手牌，农民17张，地主20张。
    kapai[] shoupai;
    //来个临时变量对象
    kapai temp;
    //是否允许出牌的开关
    boolean allow = false;

    //玩家座位
    int set; //1是地主 2是地主下家 3是地主上家


    //构造函数
    public players() {
        shoupai = new kapai[17];//玩家开始摸牌
    }

    //玩家持有积分
    public Integer jf;
    //玩家持有ID
    public String name;

    //玩家要学会整理手牌,从大到小排列
    public void lipai() {
        Log.i("MYC", "开始理牌");
        for (int i = 0; i < this.shoupai.length - 1; i++) {
            for (int j = 0; j < this.shoupai.length - 1 - i; j++) {
                if (this.shoupai[j].No < this.shoupai[j + 1].No) {
                    temp = this.shoupai[j];
                    this.shoupai[j] = shoupai[j + 1];
                    this.shoupai[j + 1] = temp;
                }
            }
        }
        Log.i("MYC", "理牌完了");
    }

    //通用的大到小排序方法
    public void lipai(kapai[] temp_kapai) {
        for (int i = 0; i < temp_kapai.length - 1; i++) {
            for (int j = 0; j < temp_kapai.length - 1 - i; j++) {
                if (temp_kapai[j].No < temp_kapai[j + 1].No) {
                    temp = temp_kapai[j];
                    temp_kapai[j] = temp_kapai[j + 1];
                    temp_kapai[j + 1] = temp;
                }
            }
        }
    }

    //玩家要会抢地主
    public void qiangdz(kapai[] temp_dipai) {

        //对shoupai扩容


        kapai[] temp = new kapai[shoupai.length + 3];

        System.arraycopy(shoupai, 0, temp, 0, 17);
        System.arraycopy(temp_dipai, 0, temp, 17, 3);
        shoupai = temp;


        lipai(shoupai);

    }

    //玩家要会选牌
    kapai[] xp;

    public void xuanpai() {


        int length = 0;                            //玩家选牌的长度
        for (int i = 0; i < shoupai.length; i++) {
            if (shoupai[i].up) {
                length++;
                Log.i("MYC", "有弹起的牌:" + shoupai[i].outword);
            }
        }

        Log.i("MYC", "length是" + length);

        kapai[] temp = new kapai[length];


        for (int j = 0, k = 0; j < shoupai.length && k < length; j++) {
//            Log.i("MYC", "跟踪k的值:" + temp[k] + " k:" + k);
            if (shoupai[j].up) {
                temp[k] = shoupai[j];
//                Log.i("MYC", "-----------------选出了牌:" + temp[k] + " k:" + k);
                k++;
            }
        }
//        Log.i("MYC", "快要完成了选牌");

        xp = temp;

        //测试
        String temp_xp = "";
        for (int i = 0; i < xp.length; i++) {
            temp_xp = temp_xp + " " + xp[i].outword;
        }
        Log.i("MYC", "选出了牌" + temp_xp);
        //测试结束


        Log.i("MYC", "完成了选牌");
    }

    //玩家还要会出牌

    kapai[] cp;                    //出牌的数组
    kapai[] up_cp;                //上家出牌的类型
//    typep type;                    //本家出牌的类型


    //玩家要会出牌(构造出chupai对象),还要会从手牌中去掉出掉的牌.
    public void chupai() {

        //先进行判断
        Log.i("MYC", "开始AI的出牌进程");


        //给cp赋值
        cp = xp;
        Log.i("MYC", "可以出牌" + cp.length);

        //移除掉出了的牌
        shoupai = GameUtil.remove(shoupai, cp);
        Log.i("MYC", "手牌剩下" + shoupai.length + "张");


    }

    //重载出牌, 仅对玩家1生效, 从手牌中取出元素
    public void chupai(FrameLayout frame) {

        //先进行判断
        Log.i("MYC", "开始玩家一的出牌进程");


//        lipai();

        //出牌了
        cp = xp;
        Log.i("MYC", "可以出牌" + cp.length);

        //移除掉出了的牌
        shoupai = GameUtil.remove(frame, shoupai, cp);


        Log.i("MYC", "手牌剩下" + shoupai.length + "张");


    }

    //玩家要会展示手牌,并且加上鼠标监听


    public void drawSP(Context context) {

        int space = 40;                                //左右两张卡牌的间隔

        for (int i = 0; i < shoupai.length; i++) {

            shoupai[i].DrawSelf(context, i, space);

        }

    }

//    //获得玩家的手牌
//    public kapai[] getSP() {
//        return shoupai;
//    }


    //游戏结束时，积分会变动。

    public void jfbiandong() {

    }

    //其他
//
//    //AI选牌,出牌.   手牌均是理牌后
//
//
//    public void AI() {        //传入手牌和上一个玩家的出牌类型
//        try {
//
//            typep up_type = new typep(up_cp);
//
//
//            typep AI_typep = new typep(shoupai, true);
//
//            //如果没有上家出牌
//
//            if (up_cp == null) {                                        //如果没有上家出牌
//                for (int i = 0, sum = 0; i < AI_typep.arr.length; i++) {
//                    if (AI_typep.arr[i] == 2) {
//                        sum += AI_typep.arr[i];
//                        shoupai[sum - AI_typep.arr[i]].up = true;
//                        shoupai[sum - AI_typep.arr[i] + 1].up = true;
//                    } else {
//                        shoupai[0].up = true;
//                    }
//                }
//                //如果有上家出牌
//            } else {
//
//                //要先把arr理牌,并且纪录每个值所在的排序
//                //先把arr赋给对象e,使对象e获得arr的排序,并且获得元素在shoupai中的序号
//                //这是手牌的arrID对象
//                arrID[] e = new arrID[AI_typep.arr.length];
//
//                for (int i = 0; i < AI_typep.arr.length; i++) {
//                    e[i].value = AI_typep.arr[i];
//                    e[i].order = i;
//                    e[i].sumvalue = AI_typep.arr[i] + e[i].sumvalue;
//                }
//                //至于up_type里的arr可以直接排序掉.
//
//
////					//再进行排序
////						arrID e_temp=new arrID();//һ����ʱarrid����
////
////					for(int i=0;i<e.length-1;i++){
////						for(int j=0;j<e.length-1-i;j++){
////							if(e[j].value>e[j+1].value){
////								e_temp=e[j];
////								e[j]=e[j+1];
////								e[j+1]=e_temp;
////							}
////						}
////					}
//
//
//                //对arr依次进行类比,先比等于的
//
//                List<Integer> temp_sum = new ArrayList<Integer>();            //那么来一个链表保存解法.
//
//                for (int j = 0; j < up_type.arr.length; j++) {
//                    for (int i = 0; i < e.length; i++) {
//
//                        //把上家出牌arr与当前出牌的arr元素挨个比较.
//                        if (e[i].value > up_type.arr[j] && shoupai[e[i].sumvalue - e[i].value].dsdx > up_cp[i].dsdx) {
//                            System.out.println("当前手牌的第" + (e[i].sumvalue - e[i].value) +
//                                    "张牌的大小是" + shoupai[e[i].sumvalue - e[i].value].dsdx + "上个玩家出的牌的大小是" +
//                                    up_cp[i].dsdx);
//
//                            temp_sum.add(e[i].sumvalue - e[i].value);            //最小解
//                            break;
//
//                        }
//
//                    }
//                }
//                //出牌了
//
//                if (temp_sum.get(0) == null) {
//                    //如果无牌可出
//                    //调用无牌可出的方法
//
//
//                } else {
//                    //有牌可出则出
//                    kapai[] AI_xp = new kapai[up_cp.length];
//
//                    int sum = 0;
//
//                    for (int i = 0; i < up_type.arr.length; i++) {
//                        for (int j = 0; j < up_type.arr[i]; j++) {
//
//                            AI_xp[sum] = shoupai[temp_sum.get(i) + j];                //取出最小的一个解放入选牌数组.
//                            shoupai[temp_sum.get(i) + j].up = true;
//                            sum++;
//                        }
//                    }
//                }
//
//            }
//
//
//        } catch (Exception e) {
//
//        }
//    }
//
//    //无牌可出的方法
//    //线程退回上个玩家.清空up_cp   这要在平台里管控啊.

    //AI的初始化
    typep AI_type;  //牌型
    boolean AIcant = false;   //true时为要不起

    public void AI_xp_initi() {
        //分析AI的手牌
        AI_type = new typep(this);
        AI_type.AIDeep();
    }


    //检查是否有炸弹,有炸就出炸的方法
    private boolean open = true;  //一个是否检索出炸的开关.   关闭表示有其他玩家出炸,在其他方法中进行处理,这里要变为false,跳过判断.

    private void wanan(typep AItype) {
        int temp;
        if (open) {
            if (AItype.ZhaNum > 0) {
                for (int i = AItype.arr.length - 1; i >= 0; i--) {
                    if (AItype.arr[i] == 4) {
                        temp = GameUtil.getIndex(i, AItype.arr);                           //---------------出炸弹
                        this.shoupai[temp].up = true;
                        this.shoupai[temp + 1].up = true;
                        this.shoupai[temp + 2].up = true;
                        this.shoupai[temp + 3].up = true;

                        AIcant = false;
                        return;
                    }
                }
            }
        }

        if (AItype.king) {                                                                      //---------出王炸

            this.shoupai[0].up = true;
            this.shoupai[1].up = true;
            AIcant = false;
            return;
        }
    }

    //控制出指定<typep.type> tip01: arr应为排序后的    tip02:该typep要能出,前提判断处理在方法外,其中手牌不够情况在此方法内会处理   tip03:sum是rearr部分的牌数
    //dsdx_fst是指出牌时首位的大小,一般定为3就好.
    public void AI_xp_SpointType(int type, Integer arr_int, int sum_in, int dsdx_fst) {

        int rearr = type / 100;
        int len = 0;
        int sum = sum_in;

        if (sum > this.shoupai.length) {
            AIcant=true;
            return;
        }


        //把Integer的arr_int转为数组
        String arr_string = arr_int.toString();
        int arr[] = new int[arr_string.length()];
        for (int i = 0; i < arr.length; i++) {
            Character ch = arr_string.charAt(i);
            arr[i] = Integer.parseInt(ch.toString());
            len = len + arr[i];
        }

        //测试
        GameUtil.Log_int(arr, ",转化后的int数组");
        //测试结束


//        int arr_num_AI;         //AI_type,arr部分转为多位数    变量
//        int arr_num_Point = 0;    //pointType的重复部分,arr转为多位数   常量

//        int sum = 0;    //pointType.rearr的重复部分共有几张牌   常量

        int temp_index; //指向手牌的坐标

        Log.i("MYC", "需要出的牌型是" + type);

//        arr_value = AI_type.arr[AI_type.arr.length - 1];    //赋值 手牌的arr的末位值
//        temp_index = GameUtil.getIndex(AI_type.arr.length - 1, AI_type.arr);      //手牌arr的尾数 作为选牌对象

        Log.i("MYC", "打印出指定牌型的rearr:" + rearr);
//        for (int i = 0; i < rearr; i++) { //算出指定的牌型,rearr的部分共有多少张
//            Log.i("MYC", "打印出指定牌型的arr[" + i + "]:" + arr[i]);
//            sum = sum + arr[i];
//        }

        Log.i("MYC", "打印出指定牌型的牌数sum:" + sum);

//        for (int k = 0, j = 1; k < pointType.rearr; j = j * 10, k++) {  //把arr数组的第一个rearr部分转变为一个int类型的数值. 这个可以封装进typep
//            arr_num_Point = arr_num_Point + pointType.arr[0] * j;
//        }
//
//        Log.i("MYC", "打印出arr_num_Point:" + arr_num_Point);

        for (int i = AI_type.arr.length - rearr; i >= 0; i--) {   //遍历的起始点定为AI_type.arr.length - pointType.rearr,从手牌右端向左遍历

            //如果手牌中该起始牌的点数小于等于上家出牌的起始点数,直接continue
            if (this.shoupai[GameUtil.getIndex(i, AI_type.arr)].dsdx < dsdx_fst) {
                continue;
            }


//            arr_num_AI = 0; //每一次手牌中局部遍历的int值存入这个变量
//            for (int k = pointType.rearr - 1, j = 1; k >= 0; j = j * 10, k--) {  //每一次的遍历,都要求出该段的int类型数值
//                arr_num_AI = arr_num_AI + AI_type.arr[i + k] * j;
//            }

            //测试-打印出手牌的arr
            String arrToStr = "";
            for (int j = 0; j < AI_type.arr.length; j++) {
                arrToStr = arrToStr + AI_type.arr[j];
            }
            Log.i("MYC", "该手牌的arr是" + arrToStr);
            //测试结束

            //测试-打印出手牌
            String SPToStr = "";
            for (int j = 0; j < this.shoupai.length; j++) {
                SPToStr = SPToStr + " " + this.shoupai[j].outword;
            }
            Log.i("MYC", "该手牌是" + SPToStr);
            //测试结束


//            Log.i("MYC", "指定的rearr是" + pointType.rearr + ",手牌arr中该段的int是" + arr_num_AI + ",这是手牌arr第" + i + "项作为起始的数组");

            //定义个临时布尔变量myTemp,如果手牌局部均大于等于上家出牌,返回true,如果有任何一个小于了返回false.
            boolean myTemp = true;
            for (int j = 0; j < rearr; j++) {
                if (this.AI_type.arr[i + j] < arr[j]) {
                    myTemp = false;
                }
            }

//            if (arr_num_AI >= arr_num_Point) {  //如果该段的int值,大于上家牌型的int值
            if (myTemp) {  //如果该段的int值,大于上家牌型的int值
                temp_index = GameUtil.getIndex(i, AI_type.arr);


//                Log.i("MYC", "手牌的arr_num_AI" + arr_num_AI + "大于等于了指定arr_num_Point" + arr_num_Point + ",该arr坐标在手牌的第" + temp_index + "项,此时arr项指针i是" + i + ",int是" + arr_num_AI);
//                for (int j = 0; j < sum; j++) {
//                    Log.i("MYC", "选出连续的部分出牌" + (temp_index + j));
//                    this.shoupai[temp_index + j].up = true;                                         //-----------选出连续的部分出牌
//                }

                for (int j = 0; j < rearr; j++) {
                    for (int k = 0; k < arr[0]; k++) {
                        this.shoupai[GameUtil.getIndex(i + j, AI_type.arr) + k].up = true;      //----------选出连续部分的牌
                    }
                }

                if (len > sum) {
                    Log.i("MYC", "该指定出牌仍有尾巴未处理,尾巴还有" + (len - sum) + "张牌");

                    for (int j = 0; j < arr.length; j++) {
                        if (arr[j] < arr[0]) {

                            for (int k = AI_type.arr.length - 1; k >= 0; k--) {

                                if (AI_type.arr[k] == arr[j]) {
                                    for (int l = 0; l < arr[j]; l++) {

                                        shoupai[GameUtil.getIndex(k, AI_type.arr) + l].up = true;        //-----------选出后续部分出牌

                                    }

                                }
                            }
                        }
                    }
                }

                //构造出临时牌型temp,判断是否存在(type>0), 不存在就continue
                kapai[] temp = new kapai[len];

                //测试

                Log.i("MYC", "这次尝试的牌型是" + arr_string + "得到的len是" + len + ",当前手牌剩余:" + this.shoupai.length);

                String temp_toString = "";

                for (int j = 0, k = 0; j < this.shoupai.length; j++) {
                    if (this.shoupai[j].up) {
                        temp[k] = this.shoupai[j];
                        temp_toString = temp_toString + " " + temp[k].outword;
                        k++;
                    }
                }
                Log.i("MYC", "//构造出临时牌型temp,判断是否type相等, 此时的temp牌为" + temp_toString);
                //测试结束

                typep temp_type = new typep(temp);
                temp_type.setTypePro();
                if (temp_type.type != type) {
                    for (int j = 0; j < this.shoupai.length; j++) {
                        if (this.shoupai[j].up) {
                            this.shoupai[j].up = false;
                        }
                    }
                    continue;
                }

                return;
            }

        }
        AIcant = true;
        return;

    }


    //控制出指定<typep> tip01:该pointType不能是王炸    tip02:该typep要能出,前提判断处理在方法外    tip03:reArr>1时
    public void AI_xp_pointType(typep pointType) {


        if (pointType.rearr <= 1) {
            return;
        }
//        int arr_num_AI;         //AI_type,arr部分转为多位数    变量
//        int arr_num_Point = 0;    //pointType的重复部分,arr转为多位数   常量
        int sum = 0;    //pointType.rearr的重复部分共有几张牌   常量

        int temp_index; //指向手牌的坐标

        Log.i("MYC", "需要出的牌型是" + pointType.type);

//        arr_value = AI_type.arr[AI_type.arr.length - 1];    //赋值 手牌的arr的末位值
//        temp_index = GameUtil.getIndex(AI_type.arr.length - 1, AI_type.arr);      //手牌arr的尾数 作为选牌对象

        Log.i("MYC", "打印出指定牌型的rearr:" + pointType.rearr);
        for (int i = 0; i < pointType.rearr; i++) { //算出指定的牌型,rearr的部分共有多少张
            Log.i("MYC", "打印出指定牌型的arr[" + i + "]:" + pointType.arr[i]);
            sum = sum + pointType.arr[i];
        }

        Log.i("MYC", "打印出指定牌型的牌数sum:" + sum);

//        for (int k = 0, j = 1; k < pointType.rearr; j = j * 10, k++) {  //把arr数组的第一个rearr部分转变为一个int类型的数值. 这个可以封装进typep
//            arr_num_Point = arr_num_Point + pointType.arr[0] * j;
//        }
//
//        Log.i("MYC", "打印出arr_num_Point:" + arr_num_Point);

        for (int i = AI_type.arr.length - pointType.rearr; i >= 0; i--) {   //遍历的起始点定为AI_type.arr.length - pointType.rearr,从手牌右端向左遍历

            //如果手牌中该起始牌的点数小于等于上家出牌的起始点数,直接continue
            if ((this.shoupai[GameUtil.getIndex(i, AI_type.arr)].dsdx) <= (pointType.dsdx_fst)) {
                continue;
            }


//            arr_num_AI = 0; //每一次手牌中局部遍历的int值存入这个变量
//            for (int k = pointType.rearr - 1, j = 1; k >= 0; j = j * 10, k--) {  //每一次的遍历,都要求出该段的int类型数值
//                arr_num_AI = arr_num_AI + AI_type.arr[i + k] * j;
//            }

            //测试-打印出手牌的arr
            String arrToStr = "";
            for (int j = 0; j < AI_type.arr.length; j++) {
                arrToStr = arrToStr + AI_type.arr[j];
            }
            Log.i("MYC", "该手牌的arr是" + arrToStr);
            //测试结束

            //测试-打印出手牌
            String SPToStr = "";
            for (int j = 0; j < this.shoupai.length; j++) {
                SPToStr = SPToStr + " " + this.shoupai[j].outword;
            }
            Log.i("MYC", "该手牌是" + SPToStr);
            //测试结束


//            Log.i("MYC", "指定的rearr是" + pointType.rearr + ",手牌arr中该段的int是" + arr_num_AI + ",这是手牌arr第" + i + "项作为起始的数组");

            //定义个临时布尔变量myTemp,如果手牌局部均大于等于上家出牌,返回true,如果有任何一个小于了返回false.
            boolean myTemp = true;
            for (int j = 0; j < pointType.rearr; j++) {
                if (this.AI_type.arr[i + j] < pointType.arr[j]) {
                    myTemp = false;
                }
            }

//            if (arr_num_AI >= arr_num_Point) {  //如果该段的int值,大于上家牌型的int值
            if (myTemp) {  //如果该段的int值,大于上家牌型的int值
                temp_index = GameUtil.getIndex(i, AI_type.arr);


//                Log.i("MYC", "手牌的arr_num_AI" + arr_num_AI + "大于等于了指定arr_num_Point" + arr_num_Point + ",该arr坐标在手牌的第" + temp_index + "项,此时arr项指针i是" + i + ",int是" + arr_num_AI);
//                for (int j = 0; j < sum; j++) {
//                    Log.i("MYC", "选出连续的部分出牌" + (temp_index + j));
//                    this.shoupai[temp_index + j].up = true;                                         //-----------选出连续的部分出牌
//                }

                for (int j = 0; j < pointType.rearr; j++) {
                    for (int k = 0; k < pointType.arr[0]; k++) {
                        this.shoupai[GameUtil.getIndex(i + j, AI_type.arr) + k].up = true;
                    }
                }

                if (pointType.len > sum) {
                    Log.i("MYC", "该指定出牌仍有尾巴未处理,尾巴还有" + (pointType.len - sum) + "张牌");

                    for (int j = 0; j < pointType.arr.length; j++) {
                        if (pointType.arr[j] < pointType.arr[0]) {

                            for (int k = AI_type.arr.length - 1; k >= 0; k--) {

                                if (AI_type.arr[k] == pointType.arr[j]) {
                                    for (int l = 0; l < pointType.arr[j]; l++) {

                                        shoupai[GameUtil.getIndex(k, AI_type.arr) + l].up = true;        //-----------选出后续部分出牌

                                    }

                                }
                            }
                        }
                    }
                }

                //构造出临时牌型temp,判断是否存在(type>0), 不存在就continue
                kapai[] temp = new kapai[pointType.len];

                //测试
                String temp_toString = "";

                for (int j = 0, k = 0; j < this.shoupai.length; j++) {
                    if (this.shoupai[j].up) {
                        temp[k] = this.shoupai[j];
                        temp_toString = temp_toString + " " + temp[k].outword;
                        k++;
                    }
                }
                Log.i("MYC", "//构造出临时牌型temp,判断是否type相等, 此时的temp牌为" + temp_toString);
                //测试结束

                typep temp_type = new typep(temp);
                temp_type.setTypePro();
                if (temp_type.type != pointType.type) {
                    for (int j = 0; j < this.shoupai.length; j++) {
                        if (this.shoupai[j].up) {
                            this.shoupai[j].up = false;
                        }
                    }
                    continue;
                }

                return;
            }

        }
        AIcant = true;
        return;

    }


    // 首发情况下的自动选牌
    public void AI_xp_auto() {
        int arr_value;
        int temp_index;

        Log.i("MYC", "--------------------------------------------------------------------------");
        AI_xp_SpointType(522, 22222, 10, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");
        AIcant=false;
        AI_xp_SpointType(422, 2222, 8, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");
        AIcant=false;
        AI_xp_SpointType(322, 222, 6, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");
        AIcant=false;
        AI_xp_SpointType(811, 11111111, 8, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");
        AIcant=false;
        AI_xp_SpointType(711, 1111111, 7, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");
        AIcant=false;
        AI_xp_SpointType(611, 111111, 6, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");
        AIcant=false;
        AI_xp_SpointType(511, 11111, 5, 3);
        if (!AIcant) {
            return;
        }

        Log.i("MYC", "--------------------------------------------------------------------------");

        AIcant = false;

        arr_value = AI_type.arr[AI_type.arr.length - 1];    //赋值 arr的最右边的值
        temp_index = GameUtil.getIndex(AI_type.arr.length - 1, AI_type.arr);      //arr的尾数对应的在手牌中的索引位置 作为选牌对象

        Log.i("MYC", "----------------新的一轮---------------,首发出牌,准备开始up");


        //如果末尾arr是3,那么要出带,多出一张牌. 不是的话直接出.
        if (arr_value == 3) {
            if (this.shoupai.length == 3) { //如果只剩下3张牌,那么直接出了.
                for (int i = 0; i < arr_value; i++) {

                    this.shoupai[temp_index + i].up = true;

                }
            } else {
                for (int i = 0; i < arr_value; i++) {

                    this.shoupai[temp_index + i].up = true;

                }
                this.shoupai[temp_index - 1].up = true;
            }

        } else {
            for (int i = 0; i < arr_value; i++) {

                this.shoupai[temp_index + i].up = true;

            }
        }

        Log.i("MYC", "up结束");
//            this.shoupai[this.shoupai.length-1].up=true;
//            this.xp[0]=this.shoupai[this.shoupai.length-1];

        return;

    }

    // 首发情况下的超级自动选牌
    public void AI_xp_Sauto() {
        int arr_value;
        int temp_index;


        arr_value = AI_type.arr[AI_type.arr.length - 1];    //赋值 arr的最右边的值
        temp_index = GameUtil.getIndex(AI_type.arr.length - 1, AI_type.arr);      //arr的尾数对应的在手牌中的索引位置 作为选牌对象

        Log.i("MYC", "----------------新的一轮---------------,首发出牌,准备开始up");


        //如果末尾arr是3,那么要出带,多出一张牌. 不是的话直接出.
        if (arr_value == 3) {
            if (this.shoupai.length == 3) { //如果只剩下3张牌,那么直接出了.
                for (int i = 0; i < arr_value; i++) {

                    this.shoupai[temp_index + i].up = true;

                }
            } else {
                for (int i = 0; i < arr_value; i++) {

                    this.shoupai[temp_index + i].up = true;

                }
                this.shoupai[temp_index - 1].up = true;
            }

        } else {
            for (int i = 0; i < arr_value; i++) {

                this.shoupai[temp_index + i].up = true;

            }
        }

        Log.i("MYC", "up结束");
//            this.shoupai[this.shoupai.length-1].up=true;
//            this.xp[0]=this.shoupai[this.shoupai.length-1];

        return;

    }

    public void AI_xp_First(kapai[] up_cp, typep up_type) {
        int temp;   //临时变量,存储游标
        //先应付上家出单张,对子,炸弹的情况
        if (up_type.type == 111) {    //应付单牌
            Log.i("MYC", "上家出了单牌,我开始选牌");
            for (int i = shoupai.length - 1; i >= 0; i--) {
                if (GameUtil.bidaxiao(up_cp[0].dsdx, shoupai[i].dsdx)) {

                    this.shoupai[i].up = true;                                                    //--------------------反馈单牌
                    Log.i("MYC", "上家出了单牌" + up_cp[0].dsdx + ",我接了单牌" + shoupai[i].dsdx);
                    return;
                }
            }
            AIcant = true;                                                                          //*******************要不起
            return;
        }
        int sum = 0;    //游标
        if (up_cp.length == 2) {    //应付对子和王炸
            //先排除手牌少于2的情况
            if (this.shoupai.length < 2) {
                AIcant = true;
                return;
            }


            //再排除上家出的是王炸
            if (up_type.type == 510) {
                Log.i("MYC", "上家出了王炸,我接不动啦");
                AIcant = true;                                                                     //**********************要不起
                return;

            } else {
                Log.i("MYC", "上家出了对子,我也要接对子");

                //强行出对子
                Log.i("MYC", "准备强行出对子");
                for (int i = AI_type.arr.length - 1; i >= 0; i--) {

                    if (AI_type.arr[i] >= 2) {
                        temp = GameUtil.getIndex(i, AI_type.arr);
                        if (GameUtil.bidaxiao(up_cp[0].dsdx, shoupai[temp].dsdx)) {

                            this.shoupai[temp].up = true;                                           //---------------反馈对子
                            this.shoupai[temp + 1].up = true;
                            Log.i("MYC", "强行出对子结束");
                            return;
                        }

                    }

                }


                //如果没有
                AIcant = true;                                                                        //***************************要不起
                return;
            }


        }

        //如果上家出的炸弹
        if (up_type.type == 144) {
            for (int i = 0; i < AI_type.arr.length; i++) {
                if (AI_type.arr[i] == 4) {
                    if (this.shoupai[GameUtil.getIndex(i, AI_type.arr)].dsdx > up_type.dsdx_fst) {       //-----------------------------出炸弹
                        temp = GameUtil.getIndex(i, AI_type.arr);
                        this.shoupai[temp].up = true;
                        this.shoupai[temp + 1].up = true;
                        this.shoupai[temp + 2].up = true;
                        this.shoupai[temp + 3].up = true;
                        AIcant = false;
                        open = false;
                        return;

                    }
                }
            }
            AIcant = true;
            return;
        }


        if (up_type.type == 131 || up_type.type == 132) {
            //先排除手牌为3且刚刚好大于上家的情况
            if (shoupai.length < up_cp.length) {
                if (AI_type.arr[0] == 3) {
                    if (shoupai[0].dsdx > up_type.dsdx_fst) {
                        for (int i = 0; i < shoupai.length; i++) {
                            shoupai[i].up = true;                                                         //------------手牌有带可出但是牌数不够时,出带
                        }

                        return;
                    } else {
                        AIcant = true;
                        return;
                    }
                } else {
                    AIcant = true;
                    return;
                }
            }


            for (int i = AI_type.arr.length - 1; i >= 0; i--) {
                if (AI_type.arr[i] == 3) {
                    if (GameUtil.bidaxiao(up_cp[0].dsdx, this.shoupai[GameUtil.getIndex(i, AI_type.arr)].dsdx)) {

//                        if (AI_type.DanNum > 0)         //有没有单牌都这样,因为是简单电脑嘛.

                        temp = GameUtil.getIndex(i, AI_type.arr);
                        this.shoupai[temp].up = true;
                        this.shoupai[temp + 1].up = true;
                        this.shoupai[temp + 2].up = true;

                        if (up_type.type == 131) {
                            for (int j = 0; j < AI_type.arr.length; j++) {
                                if(AI_type.arr[j]==1){
                                    this.shoupai[GameUtil.getIndex(j,AI_type.arr)].up=true;         //--------------------出三带一
                                    return;
                                }
                            }
                            for (int j = 0; j < this.shoupai.length; j++) {
                                if(!this.shoupai[j].up){
                                    this.shoupai[j].up=true;
                                    return;
                                }
                            }

                        } else {

                            for (int j = AI_type.arr.length - 1; j >= 0; j++) {
                                if(AI_type.arr[j]>=2){
                                    this.shoupai[GameUtil.getIndex(j,AI_type.arr)].up = true;                      //-------------出三带二
                                    this.shoupai[GameUtil.getIndex(j,AI_type.arr)+1].up = true;
                                    return;
                                }
                            }

                        }




                    }
                }

            }


            for (int i = 0; i < this.shoupai.length; i++) {
                this.shoupai[i].up=false;
            }
            AIcant = true;
            return;

        }
    }

    //电脑(简单的) set=2


    public void AITest(kapai[] up_cp, typep up_type) {

        //如果没有上家出牌

        Log.i("MYC", "----------------------进入AI---------------------");

        AIcant = false;   //先初始化
        open = true;    //炸弹开关也初始化

        if (up_cp == null) {

            Log.i("MYC", "该玩家首发出牌");

            AI_xp_auto();

            return;
        }


        //最先应付牌少于应出牌的情况
        if (up_cp.length > 3) {
            if (up_type.arr[0] == 3) {
            } else if (up_cp.length > this.shoupai.length) {
                AIcant = true;
                return;
            }
        }

        //应付rearr==1的情况
        AI_xp_First(up_cp, up_type);

        //应付rearr>1的情况
        AI_xp_pointType(up_type);

        //王炸判定
        if (!AIcant) {
            return;
        }
        Log.i("MYC", "准备进行王炸判定,当前的king值为" + AI_type.king);
        wanan(this.AI_type);

    }

    //智能的处理不应要的局面   true时要,false时不要
    public boolean AIyao(kapai[] up_cp, boolean P1_AIcant, boolean P2_AIcant) {

        if (up_cp != null) {
            if (set == 2 && P1_AIcant) {
                AIcant = true;
                return false;
            }

            if (set == 3 && up_cp[0].dsdx > 12 && !P2_AIcant) {
                AIcant = true;
                return false;
            }
        }
        return true;


    }

//    public void ZhanShiCP(Context context, FrameLayout Pncp) {
//
//        Pncp.removeAllViews();
//
//        for (int i = 0; i < this.cp.length; i++) {
//            Log.i("MYC", "进入了循环,cp长度为" + this.cp.length + " katu内容为" + this.cp[i].katu);
//
//            //重置下卡图
//            this.cp[i].show(context, i, 40, 2);
//
//            Pncp.addView(this.cp[i].katu);
//
//
//        }
//        this.xp = null;
//        this.cp = null;
//
//    }


    //后台播报手牌的方法
    public void bobao() {
        String temp = "";
        for (int i = 0; i < shoupai.length; i++) {
            temp = temp + shoupai[i].outnum + " ";
        }
        Log.i("MYC", "AI玩家" + this.set + "的手牌是" + temp);
    }
}
