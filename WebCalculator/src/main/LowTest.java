package main;

import java.util.Arrays;

public class LowTest {
	
	
	public static void main(String []args){
		org.jenetics.util.Concurrency.setConcurrency(0);
		
		double[] X={10.09162702159676};

		
		Lower l =new Lower();
		System.out.println("Lower�����Ÿ����ʼ��ֵ��"+l.getToUpPub());
/*		System.out.println("Lower��allNub��ʼֵ��"+l.getAllNub());
		System.out.println("Lower��pubNub��ʼֵ��"+l.getPubNub());
		l.setlEquation("X1-3*Y1");
		l.setCons("X1+2*Y1>=10;X1-2*Y1<=6;2*X1-Y1<=21;X1+2*Y1<=38;-X1+2*Y1<=18");
		System.out.println("ȷ�Ϲ�ʽ�Ƿ񴫵��²㣺"+l.getlEquation());
		*/
		l.setlEquation("-X1-2*Y1");
		l.setCons("X1-2*Y1<=4;2*X1-Y1<=24;3*X1+4*Y1<=96;X1+7*Y1<=126;-4*X1+5*Y1<=65;X1+4*Y1>=8");
		
		l.setX(X);
		l.LCal();
		System.out.println("�����Lower�����Ÿ��巣ֵ��"+l.getToUpPub());
/*		System.out.println("�����Lower��allNubֵ��"+l.getAllNub());
		System.out.println("�����Lower��pubNub��ʼֵ��"+l.getPubNub());*/
		double[] Y=l.getBestY();
		System.out.println("�ٴ�ȷ������ֵ��"+Arrays.toString(Y));
		
	}
}
