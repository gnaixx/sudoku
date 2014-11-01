package com.mlegeb.tools;

public class GameInit {
	private final String str = "360000000004230800000004200"
			+"070460003820000014500013020"
			+"001900000007048300000000045";
	private Pint pint[][];

	public GameInit(){
		pint  = new Pint[9][9];
		getPintValue();
		getALlPintUnseNumber();	
	}
	
	public void setPintValue(int x, int y, String value){
		pint[x][y].setValue(value);
		pint[x][y].isAdd = true;
		getALlPintUnseNumber();	
	}

	public Pint[][] getPint(){
		return this.pint;
	}

	public void getPintValue(){
		for(int i=0; i<str.length(); i++){
//			System.out.println("-->"+ (i/9) + "--" + (i%9) +"--"+str.substring(i, i+1));
			String value = str.substring(i, i+1);
			int x = i/9;
			int y = i%9;
			pint[x][y] = new Pint();
			if(value.equals("0")){
				pint[x][y].setValue("");
			}else{
				pint[x][y].setValue(value);
			}
		}
	}

	public void getALlPintUnseNumber(){
		
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				this.pint[i][j].totalUnuse.clear();
				getUnseNumber(i,j);
			}
		}
	}

	public void getUnseNumber(int x, int y){
		for(int i=0; i<9; i++){
			if(i==y) continue;
			String value = this.pint[x][i].getValue();
			if(!value.equals("") &&
					(pint[x][y].totalUnuse.indexOf(value)==-1)){
				pint[x][y].addTotalUnuse(value);
			}

		}

		for(int i=0; i<9; i++){
			if(i==x) continue;
			String value = this.pint[i][y].getValue();
			if(!value.equals("") &&
					(pint[x][y].totalUnuse.indexOf(value)==-1)){
				pint[x][y].addTotalUnuse(value);
			}

		}

		int startx = (x/3)*3;
		int starty = (y/3)*3;

		for(int i=startx; i<startx+3; i++){
			for(int j=starty; j<starty+3; j++){
				if(i==x && j==y) continue;
				String value = this.pint[i][j].getValue();
				if(!value.equals("") &&
						(pint[x][y].totalUnuse.indexOf(value)==-1)){
					pint[x][y].addTotalUnuse(value);
				}
			}
		}



	}

}
