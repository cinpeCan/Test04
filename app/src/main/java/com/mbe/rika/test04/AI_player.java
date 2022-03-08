package com.mbe.rika.test04;

/**
 * AI玩家
 */
public class AI_player extends players{

	int P;				//值1玩家下家右边, 值2玩家上家左边
	
	public AI_player(int P){
		//判断下玩家所处的位置
		this.P=P;
		
		shoupai=new kapai[17];//玩家开始摸牌
		
	}

	//AI出牌跳过比大小的环节直接出牌了.
	//1.进入cp数组   2.画出cp数组	3.加入up_cp数组 4.从手牌删除
	
	public void action(){
		xuanpai();	//选牌传入xp中
		//开始搞坐标问题
		int Loc_X;						//卡牌移动后的X坐标
		int Loc_Y;						//卡牌移动后的Y坐标
		int wid=20;						//卡牌的水平间距
		//确定移动后的坐标
		if(P==2){
		Loc_X=(334-((((xp.length-1)*wid)+105)>>1));							//105是卡牌的宽
		}else if(P==1){
		Loc_X=(1000-((((xp.length-1)*wid)+105)>>1));						//105是卡牌的宽
		}else{Loc_X=500;}
//		System.out.println("---------------Loc_X��"+Loc_X);
		Loc_Y=(750>>1)-75;																//75是卡牌的高
//		System.out.println("Loc_X,Loc_Y"+Loc_X+" "+Loc_X);

		//事件:移动玩家0的卡牌
		//缓慢移动

		//修正位置
			for(int i=0;i<xp.length;i++){
				xp[i].zb_X=Loc_X+i*wid;
				xp[i].zb_Y=Loc_Y;
			}
		
//			chupai(xp);
	}
	
}
