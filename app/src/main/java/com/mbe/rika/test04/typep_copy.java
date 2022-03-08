//package com.mbe.rika.test04;
//
//import android.util.Log;
//
///**
// *
// * @author Rika
// * 这是判断出牌的类  包含有判断自身类型的方法
// */
//public class typep_copy {
//
//
//	 int len;					//ixp.length
//	 int type=0;				//牌的种类
//	 int value;
//	 int arr[];
//	 boolean adddai;           //带的牌是否连续
//
//
//		int count3=0;	//三张同点数的次数
//		int count2=0;	//二张同点数的次数
//		int count1=0;	//单牌的次数
//
//	//构造函数
//	public typep_copy(kapai[] ixp){
//
//		findre(ixp);
////		System.out.println("1-type="+type);
//		gaofj(ixp);
////		System.out.println("2-type="+type);
//		settype(ixp);
////		System.out.println("3-type="+type);
//		bobao();
//	}
//	//构造函数 AI用的判断
//	public typep_copy(kapai[] ixp, boolean AI){
//		len=ixp.length;
//
//		findre(ixp);					//判断手牌的组成,比如3,44,666,QQ 返回1,2,3,2数组arr[]
//
//		gaofj(ixp);						//判断手牌的组成,比如3,44,666,QQ 返回单牌1,对2,带1到变量count1,count2,count3.
//
//
//	}
//
//
//	//播报牌的种类
//	public void bobao(){
//		switch (type) {						//   arr.length 卡牌点数的种类 ,MAX(arr)最多重复数,MIN(arr)最少重复数
//		case -1:
//			Log.i("MYC","出错了!!");
//			break;
//		case 1:
//			Log.i("MYC","单牌!!");	//111
//
//			break;
//		case 2:
//			Log.i("MYC","对子!!");	//122
//
//			break;
//		case 131:
//			Log.i("MYC","三带一!!"); //231
//
//			break;
//		case 4:
//			Log.i("MYC","炸弹!!"); //144
//
//			break;
//		case 5:
//			Log.i("MYC","顺子!!");	//511
//
//			break;
//		case 132:
//			Log.i("MYC","三带一对!!"); //232
//
//			break;
//		case 23:
//			Log.i("MYC","三连对!!"); //322
//
//			break;
//		case 24:
//			Log.i("MYC","四连对!!"); //422
//
//			break;
//		case 25:
//			Log.i("MYC","五连对!!"); //522
//
//			break;
//		case 26:
//			Log.i("MYC","六连对!!"); //622
//
//			break;
//		case 27:
//			Log.i("MYC","七连对!!"); //722
//
//			break;
//		case 28:
//			Log.i("MYC","八连对!!"); //822
//
//			break;
//		case 29:
//			Log.i("MYC","九连对!!"); //922
//
//			break;
//		case 20:
//			Log.i("MYC","十连对!!"); //1022
//
//			break;
//		case 232:
//			Log.i("MYC","二连带对!!"); //432或343
//
//			break;
//		case 332:
//			Log.i("MYC","三连带对!!"); //632或
//
//			break;
//		case 432:
//			Log.i("MYC","四连带对!!"); //832或
//
//			break;
//		case 231:
//			Log.i("MYC","二连带一!!"); //431或
//
//			break;
//		case 331:
//			Log.i("MYC","三连带一!!"); //631或
//
//			break;
//		case 431:
//			Log.i("MYC","四连带一!!"); //831或
//
//			break;
//		}
//	}
//
//
//
//	/**
//	 * author Rika
//	 * 判断牌中重复的牌数(要先理牌)
//	 * 例如2,2,3,3,3这样的牌,返回2,3到arr[]
//	 */
//
//	public void findre(kapai[] ixp){
//		int count=0;			//相同的牌的数量
//		arr=new int[1];
//		int j=0;
//		for(int i=1;i<ixp.length;i++){
//			if(ixp[i-1].dsdx==ixp[i].dsdx){
//				count=count+1;
//				arr[j]=count+1;
////				System.out.println(ixp.length+"count"+count);
////				System.out.println("arr[j]是"+arr[j]+" "+"J是"+j);
//
//			}else{
//				j++;
//
//				count=0;
//
//
//				int[] a2 = new int[arr.length + 1];
//				System.arraycopy(arr, 0, a2, 0, arr.length);
//				a2[arr.length] = 1;
//				arr=a2;
//				for(int l=0;l<arr.length;l++){
////					System.out.println("小步骤"+arr[l]);
//			}
//		}
//	}
////		//测试
////		for(int l=0;l<arr.length;l++){
//// 			System.out.println(arr[l]);
////		}
//	}
//
//	/**
//	 * author Rika
//	 * 判断有几个带,几个对,几个单牌,返回在countX
//	 */
//	public void gaofj(kapai[] ixp){
//		int sum=0;
//		int up_dai=0;
//
//
//		for(int i=0;i<arr.length;i++){
//			sum=sum+arr[i];
//			if(arr[i]==4){
//				this.type=4;												//筛选出炸弹
//				this.value=ixp[sum-1].dsdx+100;
////				System.out.println("type="+type);
//			}
//			if(arr[i]==3){
//				count3++;
//				//ixp[sum].dsdx=ixp[sum].dsdx<<5;
//				ixp[sum-1].dai=true;
//				if (count3==1){
//					up_dai=ixp[sum-1].dsdx;
//					adddai=true;
//				}else {
//					if (adddai==true&&(up_dai+1)==ixp[sum-1].dsdx){
//
//					}else {
//						adddai=false;
//					}
//				}
//			}
//			if(arr[i]==2){
//				count2++;
//			}
//			if(arr[i]==1){
//				count1++;
//			}
//		}
//
//
//	}
//
//
//	/**
//	 * author Rika
//	 * 判断是不是带或者飞机
//	 */
//	public void dai(kapai[] ixp){
//		int temp;
////		System.out.println("这也不是顺子!!");
////		System.out.println(count1+" "+count2+" "+count3);
////		System.out.println("arr的长度"+arr.length);
//		//判断是不是三带二
//		if(count3==count2&&arr.length==(count3+count2)){
//			//判断几个带是不是连续的
//			temp=0;
//			int first=0;		//第一个带的点数
//			int k=0;
//			int time=0;
//
//			boolean key=false;
//
//			for(;k<ixp.length;k++){
////				System.out.println("ixp是"+ixp[k].dsdx+" value"+ixp[k].value);
//				if(ixp[k].dai){
////					System.out.println("这是带母"+ixp[k].dsdx);
//					temp=temp+ixp[k].dsdx;
//					time++;
//					if(time==count3){
////						System.out.println("time="+time+" count3="+count3);
//						break;
//					}
//					if(first==0){
//					first=ixp[k].dsdx;
////					System.out.println("first="+first+" k="+k);
//					}
//				}
//			}
//
//
//
//			System.out.println("k="+k+" ixp[k].dsdx="+ixp[k].dsdx+" temp="+temp);
//			int tempc=count3;
//			if(count3==1){
//				count3=2;}
//			if(((first+ixp[k].dsdx)*count3>>1)==temp){
//				count3=tempc;
//				this.type=count3*100+32;
////				System.out.println("三带二的牌型");
//
//				this.value=first;
//
//
//			}
//		//判断是不是三带一
//		}else if(count3==count1&&arr.length==count3+count1){
////			System.out.println("这也不是三带二");
//			//判断几个带是不是连续的
//			temp=0;
//			for(int j=0;j<count3-1;j++){
////				System.out.println("ixp[2+j*3].dsdx="+ixp[2+j*3].dsdx+" ixp[5+j*3].dsdx="+ixp[5+j*3].dsdx);
//				if((ixp[2+j*3].dsdx+1)==ixp[5+j*3].dsdx){
////					System.out.println("1temp="+temp+" count3="+count3);
//					temp++;
////					System.out.println("2temp="+temp+" count3="+count3);
//				}
//			}
////			System.out.println("3temp="+temp+" count3="+count3);
//			if(temp==count3-1){
//				//返回牌型,牌大小
//				this.type=count3*100+31;
//
//				for(int k=0;k<ixp.length;k++){
//					if(ixp[k].dai){
//
//						this.value=ixp[k].dsdx;
//						break;
//					}
//				}
//			}else{
////				System.out.println("这也不是三带一"+type);
//				//type=-1;
//			}
//
//		}else{
////			System.out.println("这根本不是带!!");
//			//type=0;
//		}
//	}
//	/**
//	 * author Rika
//	 * 牌堆的大小
//	 */
//	public int sum(kapai[] ixp){
//		return GameUtil.hahaha(ixp);
//	}
//
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
//
//}
