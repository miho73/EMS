package main;

import java.util.Calendar;

import javax.swing.JOptionPane;

import exception.Except;

public class Time {
	private static synchronized void setT() {
		Calendar c = Calendar.getInstance();
		y=c.get(Calendar.YEAR);
		M=c.get(Calendar.MONTH)+1;
		d=c.get(Calendar.DATE);
		h=c.get(Calendar.HOUR_OF_DAY);
		m=c.get(Calendar.MINUTE);
		s=c.get(Calendar.SECOND);
		ms=c.get(Calendar.MILLISECOND);
	}
	private static time t = new time();
	public static void start() {
		t.start();
	}
	public static int d, h, m, s, ms, y, M, unit;
	public static class time extends Thread {
		@Override
		public void run() {
			while(true) {
				setT();
				try {Thread.sleep(10);}catch(Exception e) {
					String s[]= {"다시 시도", "시계 정지"};
					int o=JOptionPane.showOptionDialog(null, "스레드에 오류가 생겨 시간 확인에 실패했습니다.\n기술 코드 : "+Except.except(e), "시간 확인 중단", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, s, s[0]);
					if(o==1) return;
				}
			}
		}
	}
}
