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
					String s[]= {"�ٽ� �õ�", "�ð� ����"};
					int o=JOptionPane.showOptionDialog(null, "�����忡 ������ ���� �ð� Ȯ�ο� �����߽��ϴ�.\n��� �ڵ� : "+Except.except(e), "�ð� Ȯ�� �ߴ�", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, s, s[0]);
					if(o==1) return;
				}
			}
		}
	}
}
