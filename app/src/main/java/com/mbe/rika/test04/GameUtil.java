package com.mbe.rika.test04;
/**
 * @author Rika
 */

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;


import java.lang.reflect.Field;


public class GameUtil {
    private GameUtil() {
    }  //工具类构造器私有

    /**
     * author Rika
     * 2018/12/27
     */
    public static boolean bidaxiao(int updsdx, int downdsdx) {
        if (updsdx < downdsdx) {
            return true;//下家更大,可以出牌
        } else {
            return false;//下家更小,禁止出牌
        }

    }

    public static int bidaxiao(kapai upkapai, kapai downkapai) {
        if (upkapai.dsdx < downkapai.dsdx) {
            return 1;//下家更大,可以出牌
        } else {
            return 0;//下家更小,禁止出牌
        }

    }

    public static boolean bidaxiao(kapai[] up, kapai[] down, Context context) {


        typep downType = new typep(down);
        downType.setTypePro();

        if (up == null) {
            if (downType.type > 0) {
                return true;
            } else {
//                Toast.makeText(context, "非法出牌,请重新出牌喵(>^ω^<)", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            typep upType = new typep(up);
            upType.setTypePro();

            if ((upType.type != downType.type) || (downType.type == 0)) {
//                Toast.makeText(context, "非法出牌,请重新出牌喵(>^ω^<)", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (upType.value < downType.value) {
                return true;//下家更大,可以出牌
            } else {
                return false;//下家更小,禁止出牌
            }
        }


    }


    public static boolean bidaxiao(kapai[] up, kapai[] down, typep up_type, typep down_type, Context context) {


        if (up == null) {
            if (down_type.type > 0) {
                return true;
            } else {
//                Toast.makeText(context, "非法出牌,请重新出牌喵(>^ω^<)", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {

            //排除下出的是炸弹和王炸的情况
            if (down_type.type == 510) {
                return true;
            }
            if (up_type.type != 144 || up_type.type != 244 || up_type.type != 344 || up_type.type != 444 || up_type.type != 544) {
                if (down_type.type == 144 || down_type.type == 244 || down_type.type == 344 || down_type.type == 444 || down_type.type == 544) {
                    return true;
                }
            }

            //再进行正常的判断
            if ((up_type.type != down_type.type) || (down_type.type == 0)) {
//                Toast.makeText(context, "非法出牌,请重新出牌喵(>^ω^<)", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (up_type.value < down_type.value) {
                return true;//下家更大,可以出牌
            } else {
                return false;//下家更小,禁止出牌
            }
        }


    }

    /**
     * author Rika
     * 2018/12/27
     */
    //普通求和
    public static int hahaha(kapai[] txp) {
        int temp = 0;
        for (int i = 0; i < txp.length; i++) {
            temp = temp + txp[i].dsdx;
        }
        return temp;
    }

    //前n项求和
    public static int hahaha(kapai[] txp, int n) {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp = temp + txp[i].dsdx;
        }
        return temp;
    }

    //前m项-前n项的和 * n<m   (等同于kapai[n]到kapai[m-1]的和)
    public static int hahaha(kapai[] txp, int n, int m) {
        int temp = 0;
        int temp2 = 0;

        for (int i = 0; i < n; i++) {
            temp = temp + txp[i].dsdx;
        }

        for (int i = 0; i < m; i++) {
            temp2 = temp2 + txp[i].dsdx;
        }

        return temp2 - temp;
    }


    //比较牌型是否一致
    public static boolean istype(kapai[] up_cp, kapai[] cp) {

        typep up = new typep(up_cp);
        typep down = new typep(cp);

        //如果出牌不合法
        if (down.type <= 0) {
            return false;
        }

        //如果牌型相同且上家更大
        if (up.type == down.type && up.value < down.value) {
            return true;
        } else {
            //还有其他各种情况没考虑, 这个方法先放置
            return false;
        }
    }


    //出牌后移除手牌的方法

    public static kapai[] remove(kapai[] shoupai, kapai[] cp) {
        int tempL = shoupai.length - cp.length;
        kapai[] temp_sp = new kapai[tempL];
        for (int i = 0, j = 0; i < shoupai.length; i++) {
            if (shoupai[i].up) {
                shoupai[i].katu = null;
                continue;

            } else {
                temp_sp[j] = shoupai[i];
                j++;
            }


        }
        return temp_sp;
    }

    //重载出牌后移除手牌的方法(增加玩家1手牌的操作)

    public static kapai[] remove(FrameLayout frame, kapai[] shoupai, kapai[] cp) {
        int tempL = shoupai.length - cp.length;
        kapai[] temp_sp = new kapai[tempL];
        for (int i = 0, j = 0; i < shoupai.length; i++) {
            if (shoupai[i].up) {
                frame.removeView(shoupai[i].katu);
                shoupai[i].katu = null;
                continue;

            } else {
                temp_sp[j] = shoupai[i];
                temp_sp[j].NO = j;
                temp_sp[j].ofCard.leftMargin = temp_sp[j].NO * temp_sp[j].space;
                j++;
            }


        }
        return temp_sp;
    }


    //通过卡牌编号获取手牌卡图的ID
    public static int getImageId(int No, int botton) {
        String name;

        if (botton == 0) {
            name = "dppuke_" + No;   //0的时候配底牌
        } else if (botton == 1) {
            name = "sppuke_" + No;   //1的时候配手牌
        } else {
            name = "cppuke_" + No;   //2的时候配出牌
        }

        try {
            Class myR = R.mipmap.class;
            Field poke = myR.getField(name);
            poke.setAccessible(true);   //关闭检查
            return (int) poke.get(myR);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    //通用的小到大排序方法
    public static void paixu(int[] in) {
        int temp;

        for (int i = 0; i < in.length - 1; i++) {
            for (int j = 0; j < in.length - 1 - i; j++) {
                if (in[j] > in[j + 1]) {
                    temp = in[j];
                    in[j] = in[j + 1];
                    in[j + 1] = temp;
                }
            }
        }
    }

    //对arr的ixp索引机制,游标到arr[i]对应的ixp位置
    public static int getIndex(int index, int[] arr) {

        int ixp_index = 0;//游标哥
        for (int i = 0; i < index; i++) {
            ixp_index += arr[i];
        }
        return ixp_index;
    }

    //对一个int类型的数组进行遍历打印
    public static void Log_int(int[] getIn,String s) {
        String int_temp = "";
        for (int i = 0; i < getIn.length; i++) {
            int_temp = int_temp + getIn[i];
        }
        Log.i("MYC","这个数组"+s+"为:"+int_temp);

    }

}