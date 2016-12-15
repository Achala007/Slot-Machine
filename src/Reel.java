package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Reel extends JFrame {

	private static int countWinn;
	private static int countLoss;
	private static double timeCount;
	private static int Avgcredit;

	public static double getTimeCount() {
		return timeCount;
	}

	public static void setTimeCount(double timeCount) {
		Reel.timeCount = timeCount;
	}

	public static int getAvgcredit() {
		return Avgcredit;
	}

	public static void setAvgcredit(int avgcredit) {
		Avgcredit = avgcredit;
	}

	symbol obj = new symbol();

	public static int getCountWinn() {
		return countWinn;
	}

	public static void setCountWinn(int countWinn) {
		Reel.countWinn = countWinn;
	}

	public static int getCountLoss() {
		return countLoss;
	}

	public static void setCountLoss(int countLoss) {
		Reel.countLoss = countLoss;
	}

	public ArrayList<ImageIcon> img = obj.images;
	private JLabel lblimg1, lblimg2, lblimg3, lblcredit, lblbet, getcreditArea, getBetArea;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6;
	private int reel1 = 1, reel2 = 2, reel3 = 3;

	Container contentPane;

	int countCoin = 10;// stating number of add coins in the begin
	int betCoin = 1;// starting number of bet coins in the begining
	int betMax;
	int countNumWinn = 0;// number of winn times in the game
	int countNumloss = 0;// number of loss time in the game
	int countTimes = 0;// number of time game play

	Thread t1;// thread that use for first image
	Thread t2;// thread that use for second image
	Thread t3;// thread that use for third image

	Random r = new Random();
	Reel num;// object that pass statistics to the statistic window

	int value;// value of the symbols
	int avgCredit = 0;// sum of bet credits in a game
	int avgCredit1 = 0;// sum of win bet credits
	int avgCredit2 = 0;// sum of loss bet credits
	int clickCount;
	int Credit = 0;

	public Reel() {

		setLayout(new GridLayout(5, 5, 5, 5));

		// resizing window doesn't affect the gui when using this layout
		contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		contentPane.setBackground(Color.black);// set contentpane color

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(15, 15, 10, 10);

		obj.SetImage();

		lblimg1 = new JLabel();// label that set image 1 in the reel
		lblimg1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.yellow));
		lblimg1.addMouseListener(new click());// it wait the first thread until
												// it start

		lblimg2 = new JLabel();// label that set image 2 in the reel
		lblimg2.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.yellow));
		lblimg2.addMouseListener(new click());// it wait the second thread until
												// it start

		lblimg3 = new JLabel();// label that set image 1 in the reel
		lblimg3.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.yellow));
		lblimg3.addMouseListener(new click());// it wait the third thread until
												// it start

		lblimg1.setIcon(img.get(reel1));// starting image of first label in
										// begin
		lblimg2.setIcon(img.get(reel2));// starting image of second label in
										// begin
		lblimg3.setIcon(img.get(reel3));// starting image of third label in
										// begin

		gbc.gridx = 0;// x axis in lblimg1
		gbc.gridy = 0;// y axis in lblimg1
		contentPane.add(lblimg1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		contentPane.add(lblimg2, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		contentPane.add(lblimg3, gbc);

		btn1 = new JButton();
		btn1.setBackground(Color.black);
		btn1.setIcon(new ImageIcon("src\\GUI\\spin.gif"));
		btn1.setToolTipText("Click to spin the reels!");
		btn1.setBorder(BorderFactory.createEmptyBorder());
		btn1.setPreferredSize(new Dimension(50, 60));

		// spin button processes
		btn1.addActionListener(new spinner1());
		btn1.addActionListener(new spinner2());
		btn1.addActionListener(new spinner3());
		btn2 = new JButton("Bet One");

		// bet one button action
		btn2.addActionListener(new betCoin());
		btn3 = new JButton("Bet Max");

		// betmax button procees
		btn3.addActionListener(new betCoinMax());
		btn4 = new JButton("Reset");

		// reset button process
		btn4.addActionListener(new Reset());
		btn5 = new JButton("Add Coin");

		// add coin button process
		btn5.addActionListener(new addOneCoin());
		btn6 = new JButton("Statistics");

		// statistics button process
		btn6.addActionListener(new Stats());
		gbc.gridx = 1;
		gbc.gridy = 1;

		contentPane.add(btn1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		contentPane.add(btn2, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		contentPane.add(btn3, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		contentPane.add(btn4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPane.add(btn5, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		contentPane.add(btn6, gbc);

		lblcredit = new JLabel("Credit Area");
		lblcredit.setForeground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 6;

		contentPane.add(lblcredit, gbc);

		getcreditArea = new JLabel("10");
		getcreditArea.setForeground(Color.white);
		gbc.gridx = 1;
		gbc.gridy = 6;

		contentPane.add(getcreditArea, gbc);
		lblbet = new JLabel("Bet Area");
		lblbet.setForeground(Color.white);

		gbc.gridx = 0;
		gbc.gridy = 7;

		contentPane.add(lblbet, gbc);

		getBetArea = new JLabel("0");
		getBetArea.setForeground(Color.white);
		gbc.gridx = 1;
		gbc.gridy = 7;

		contentPane.add(getBetArea, gbc);

	}

	class addOneCoin implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			String count1 = getcreditArea.getText();
			int countInt = Integer.parseInt(count1);

			if (!getBetArea.getText().isEmpty()) {

				String bet = getBetArea.getText();
				int betInt = Integer.parseInt(bet);

				countInt++;

			} else {
				countInt++;

			}
			String count = String.valueOf(countInt);

			getcreditArea.setText(count);
		}

	}

	class betCoin implements ActionListener {
		int countAll;

		public void actionPerformed(ActionEvent event1) {
			String countAvailable;
			String countAddcoin = getcreditArea.getText();
			int countCoin = Integer.parseInt(countAddcoin);
			if (countCoin <= 0) {

				JOptionPane.showMessageDialog(null, "Please add coins first", "Alert", JOptionPane.INFORMATION_MESSAGE);

			} else {
				if (getBetArea.getText().isEmpty()) {
					countAvailable = String.valueOf(betCoin++);

				} else {
					int countInt = Integer.parseInt(getBetArea.getText());
					int w = Integer.parseInt(getcreditArea.getText());
					int y = Integer.parseInt(getBetArea.getText());
					int x = w - y;
					countAll = ++countInt;

					if (getcreditArea.getText().equals("0")) {
						JOptionPane.showMessageDialog(null, "Please add coins first", "Alert",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

					}
					// betCoin++;
					countAvailable = String.valueOf(countAll);

				}
				getBetArea.setText(countAvailable);
				String betArea = getBetArea.getText();

				int betInt = Integer.parseInt(betArea);
				if (!betArea.isEmpty() && countCoin > 0) {

					int credit = countCoin - 1;
					if (credit >= 0) {
						String credit1 = String.valueOf(credit);
						getcreditArea.setText(credit1);
					}
				}
			}

		}

	}

	class betCoinMax implements ActionListener {
		String count;
		int bet;

		public void actionPerformed(ActionEvent event) {
			String creditArea = getcreditArea.getText();
			int crInt = Integer.parseInt(creditArea);
			if (getBetArea.getText().isEmpty()) {
				bet = 3;

			} else {
				int countInt = Integer.parseInt(getBetArea.getText());
				bet = countInt + 3;

			}
			int betting = Integer.parseInt(getBetArea.getText());
			int Total = crInt - 3;
			if (Total >= 0) {
				int Total1 = crInt - 3;

				getcreditArea.setText(String.valueOf(Total1));
				count = String.valueOf(bet);
			} else {
				JOptionPane.showMessageDialog(null, "ERROR", "Please add coins first", JOptionPane.INFORMATION_MESSAGE);

			}
			getBetArea.setText(count);

		}
	}

	class Reset implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getcreditArea.setText("10");
			getBetArea.setText("0");
			Statistics ss = new Statistics(num);
			ss.statisReset();

		}

	}

	public class spinner1 implements Runnable, ActionListener {

		@Override
		public void run() {
			reel1();

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			clickCount = 0;
			if (getBetArea.getText().isEmpty() || getBetArea.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "You haven't bet !", "Alert", JOptionPane.INFORMATION_MESSAGE);

			} else {

				t1 = new Thread(new spinner1());

				t1.start();

			}
		}
	}

	public void reel1() {
		int num = 0;
		for (int i = 0; i < 1000; i++) {

			num = r.nextInt(6);
			lblimg1.setIcon(img.get(num));
			try {
				t1.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public class spinner2 implements Runnable, ActionListener {

		@Override
		public void run() {
			reel2();

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (getBetArea.getText().isEmpty() || getBetArea.getText().equals("0")) {

			} else {

				t2 = new Thread(new spinner2());

				t2.start();

			}
		}
	}

	public void reel2() {
		int num = 0;
		for (int i = 0; i < 1000; i++) {

			num = r.nextInt(6);
			lblimg2.setIcon(img.get(num));
			try {
				t2.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public class spinner3 implements Runnable, ActionListener {

		@Override
		public void run() {
			reel3();

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (getBetArea.getText().isEmpty() || getBetArea.getText().equals("0")) {

			} else {

				t3 = new Thread(new spinner3());

				t3.start();

			}
		}
	}

	public void reel3() {
		int num = 0;
		for (int i = 0; i < 1000; i++) {

			num = r.nextInt(6);
			lblimg3.setIcon(img.get(num));
			try {
				t3.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	class click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			try {
				synchronized (t1) {
					t1.stop();
				}
				synchronized (t2) {
					t2.stop();
				}
				synchronized (t3) {
					t3.stop();
				}
				if (clickCount == 0) {
					compare();
				}
				clickCount++;
			} catch (Exception ex) {
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	public Reel compare() {

		int count = 1;
		boolean flag = false;
		boolean flag2 = false;
		if (lblimg1.getIcon() == lblimg2.getIcon()) {
			flag = true;
			for (int i = 0; i < 6; i++) {
				if (lblimg1.getIcon() == img.get(i)) {
					switch (i) {
					case 0: {
						value = 7;
					}
						break;
					case 1: {
						value = 6;
					}
						break;
					case 2: {
						value = 5;
					}
						break;
					case 3: {
						value = 4;
					}
						break;
					case 4: {
						value = 3;
					}
						break;
					case 5: {
						value = 2;
					}
						break;

					}
				}
			}

		} else if (lblimg1.getIcon() == lblimg3.getIcon()) {
			flag = true;

			for (int i = 0; i < 6; i++) {
				if (lblimg1.getIcon() == img.get(i)) {
					switch (i) {
					case 0: {
						value = 7;
					}
						break;
					case 1: {
						value = 6;
					}
						break;
					case 2: {
						value = 5;
					}
						break;
					case 3: {
						value = 4;
					}
						break;
					case 4: {
						value = 3;
					}
						break;
					case 5: {
						value = 2;
					}
						break;

					}
				}
			}

		} else if (lblimg2.getIcon() == lblimg3.getIcon()) {
			flag = true;

			for (int i = 0; i < 6; i++) {
				if (lblimg2.getIcon() == img.get(i)) {
					switch (i) {
					case 0: {
						value = 7;
					}
						break;
					case 1: {
						value = 6;
					}
						break;
					case 2: {
						value = 5;
					}
						break;
					case 3: {
						value = 4;
					}
						break;
					case 4: {
						value = 3;
					}
						break;
					case 5: {
						value = 2;
					}
						break;

					}
				}
			}

		} else if (lblimg2.getIcon() == lblimg3.getIcon() && lblimg1.getIcon() == lblimg3.getIcon()) {
			flag2 = true;

			for (int i = 0; i < 6; i++) {
				if (lblimg1.getIcon() == img.get(i)) {
					switch (i) {
					case 0: {
						value = 7;
					}
						break;
					case 1: {
						value = 6;
					}
						break;
					case 2: {
						value = 5;
					}
						break;
					case 3: {
						value = 4;
					}
						break;
					case 4: {
						value = 3;
					}
						break;
					case 5: {
						value = 2;
					}
						break;

					}
				}
			}

		}

		if (flag) {
			if (flag2) {
				countNumWinn++;
				Reel.setCountWinn(countNumWinn);
				countTimes++;
				Credit = Integer.parseInt(getBetArea.getText());
				avgCredit1 = avgCredit1 + Credit;
				JOptionPane.showMessageDialog(null, "You have Won the match", "congratulation",
						JOptionPane.INFORMATION_MESSAGE);
				int credits = Integer.parseInt(getcreditArea.getText());
				int bet = Integer.parseInt(getBetArea.getText());
				int Total = credits + bet * value;
				String Tot = String.valueOf(Total);
				getcreditArea.setText(Tot);
				getBetArea.setText("0");

			} else {
				JOptionPane.showMessageDialog(null, "You have won a free spin", "Nice Try",
						JOptionPane.INFORMATION_MESSAGE);
				getcreditArea.setText(String.valueOf(Integer.parseInt(getcreditArea.getText())+Integer.parseInt(getBetArea.getText())));
				getBetArea.setText("0");
			}
		} else {
			countNumloss++;
			Reel.setCountLoss(countNumloss);
			countTimes++;
			Credit = Integer.parseInt(getBetArea.getText());
			avgCredit2 = avgCredit2 - Credit;

			JOptionPane.showMessageDialog(null, "You have lost the match", "Bad Luck", JOptionPane.INFORMATION_MESSAGE);
			getBetArea.setText("0");

		}
		avgCredit = avgCredit2 + avgCredit1;
		Reel.setAvgcredit(avgCredit);
		Reel.setTimeCount(countTimes);

		return num;

	}

	class Stats implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new Statistics(num);
		}
	}
}