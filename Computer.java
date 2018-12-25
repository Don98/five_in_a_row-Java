import java.util.*;
import java.math.*;
public class Computer {
	public int a[][];
	public int PC[][];
	public int CC[][];
	int difficulty;
	int color;//1 black 2 white
	static int score;
	static int dx[]= new int[]{0,0,1,-1,1,-1,1,-1};
	static int dy[]= new int[]{1,-1,0,0,1,-1,-1,1};
	public Computer(int difficulty,int color) {
		this.difficulty=difficulty;
		this.color=color;
		a=new int[15][15];
		PC=new int[15][15];
		CC=new int[15][15];
		for (int i=0;i<15;i++) for (int j=0;j<15;j++) a[i][j]=PC[i][j]=CC[i][j]=0;
	}
	void MarkEasy(int mcnt,int ocnt,int now) {
		if (mcnt==1) score+=10;
		else if (mcnt==2&&ocnt==0) score+=400;
		else if (mcnt==2&&ocnt==1) score+=50;
		else if (mcnt==3&&ocnt==0) score+=70000;
		else if (mcnt==3&&ocnt==1) score+=300;
		else if (mcnt==4&&ocnt==0) {
			if (now==color) score+=100000;
			else score+=99999;
		}
		else if (mcnt==4&&ocnt==1) {
			if (now==color) score+=90000;
			else score+=89999;
		}
	}
	int CalcEasy(int stx,int sty,int now) {
		score=0;
		for (int i=0;i<8;i+=2) {
			int x=stx,y=sty,mcnt=0,ocnt=0;
			while (true) {
				x+=dx[i]; y+=dy[i];
				if (0<=x&&x<15&&0<=y&&y<15&&a[x][y]==now) mcnt++;
				else if (0<=x&&x<15&&0<=y&&y<15&&a[x][y]==3-now) {
					ocnt++; break;
				}
				else {
					if (x<0||x>15||y<0||y>15) ocnt++;
					break;
				}
			}
			
			x=stx; y=sty; 
			while (true) {
				x+=dx[i+1]; y+=dy[i+1];
				if (0<=x&&x<15&&0<=y&&y<15&&a[x][y]==now) mcnt++;
				else if (0<=x&&x<15&&0<=y&&y<15&&a[x][y]==3-now) {
					ocnt++; break;
				}
				else {
					if (x<0||x>15||y<0||y>15) ocnt++;
					break;
				}
			}
			MarkEasy(mcnt,ocnt,now);	
		}
		return score;
	}
	public Point EasyModel() {
		for (int x=0;x<15;x++) {
			for (int y=0;y<15;y++) {
				if (a[x][y]==0) {
					PC[x][y]=CalcEasy(x,y,3-color);
					CC[x][y]=CalcEasy(x,y,color);
				}
				else {
					PC[x][y]=CC[x][y]=0;
				}
			}
		}
		int x=8,y=8,mx=0;
		for (int i=0;i<15;i++) for (int j=0;j<15;j++) if (a[i][j]==0){
			if (PC[i][j]>mx) {
				x=i; y=j; mx=PC[x][y];
			}
			if (CC[i][j]>mx) {
				x=i; y=j; mx=CC[x][y];
			}
		}
		//System.out.println(x);
		//System.out.println(y);
		Point P=new Point(x,y);
		return P;
	}
	public Point ComputerPlay() {
		Point p=EasyModel();
		a[p.getX()][p.getY()]=color;
		/*for (int i=0;i<15;i++){
			for (int j=0;j<15;j++) System.out.format("%d ",a[i][j]); 
			System.out.println();
		}
		System.out.println();
		for (int i=0;i<15;i++){
			for (int j=0;j<15;j++) System.out.format("%d ",PC[i][j]); 
			System.out.println();
		}
		System.out.println();
		for (int i=0;i<15;i++){
			for (int j=0;j<15;j++) System.out.format("%d ",CC[i][j]); 
			System.out.println();
		}
		System.out.println();*/
		return p;
	}
	void PersonPlay(Point p) {
		a[p.getX()][p.getY()]=3-color;
	}
}
