
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class SlotMachineGUI extends JPanel {

	private JFrame frmFrame;
	private JPanel pnlReels, pnlReel1, pnlReel2, pnlReel3, pnlButtons, pnllbls;
	private JButton btnAddCoin, btnSpin, btnBetOne, btnBetMax, btnReset, btnStats;
	private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;	
	private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();

	public void createForm() {

		frmFrame = new JFrame();
		frmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmFrame.setTitle("Slot Machine");
		frmFrame.setResizable(false);
		frmFrame.setLayout(new FlowLayout());
		frmFrame.setSize(600, 300);
		frmFrame.setLocationRelativeTo(null);
		frmFrame.setVisible(true);

		pnlReels = new JPanel();
		pnlReels.setSize(600, 100);
		pnlReels.setBackground(new Color(255, 255, 255));
		frmFrame.add(pnlReels);

		pnlReel1 = new JPanel();
		pnlReel1.setBackground(new Color(255, 215, 0));
		pnlReel1.setPreferredSize(new Dimension(100, 100));
		pnlReels.add(pnlReel1);

		pnlReel2 = new JPanel();
		pnlReel2.setBackground(new Color(255, 216, 0));
		pnlReel2.setPreferredSize(new Dimension(100, 100));
		pnlReels.add(pnlReel2);

		pnlReel3 = new JPanel();
		pnlReel3.setBackground(new java.awt.Color(255, 215, 0));
		pnlReel3.setPreferredSize(new Dimension(100, 100));
		pnlReels.add(pnlReel3);

		pnlButtons = new JPanel();
		pnlButtons.setSize(400, 100);
		pnlButtons.setBackground(new Color(255, 255, 255));
		pnlButtons.setLayout(new GridLayout(1, 4, 5, 6));
		frmFrame.add(pnlButtons);

		btnSpin = new JButton();
		btnSpin.setBackground(new Color(0, 128, 255));
		btnSpin.setText("Spin");
		btnSpin.setToolTipText("Click to spin the reels!");
		pnlButtons.add(btnSpin);
		btnSpin.setVisible(true);
		// btnSpin.addActionListener(new SpinHandler());

		btnAddCoin = new JButton();
		btnAddCoin.setBackground(new Color(0, 128, 255));
		btnAddCoin.setText("Add coins");
		pnlButtons.add(btnAddCoin);
		btnAddCoin.setVisible(true);
		// btnAddCoin.addActionListener(new BuyCreditsHandler());

		btnBetOne = new JButton();
		btnBetOne.setBackground(new Color(0, 128, 255));
		btnBetOne.setText("Bet One");
		pnlButtons.add(btnBetOne);
		btnAddCoin.setVisible(true);
		// btnAddCoin.addActionListener(new BuyCreditsHandler());

		btnBetMax = new JButton();
		btnBetMax.setBackground(new Color(0, 128, 255));
		btnBetMax.setText("Bet Max");
		pnlButtons.add(btnBetMax);
		btnBetMax.setVisible(true);
		// btnAddCoin.addActionListener(new BuyCreditsHandler());

		btnReset = new JButton();
		btnReset.setBackground(new Color(0, 128, 255));
		btnReset.setText("Reset");
		pnlButtons.add(btnReset);
		btnReset.setVisible(true);
		// btnAddCoin.addActionListener(new BuyCreditsHandler());

		btnStats = new JButton();
		btnStats.setBackground(new Color(0, 128, 255));
		btnStats.setText("Statistics");
		pnlButtons.add(btnStats);
		btnStats.setVisible(true);
		// btnAddCoin.addActionListener(new BuyCreditsHandler());

		pnllbls = new JPanel();
		pnllbls.setSize(400, 100);
		pnllbls.setBackground(new Color(255, 255, 255));
		pnllbls.setLayout(new GridLayout(3, 2, 6, 8));
		frmFrame.add(pnllbls);

		lbl1 = new JLabel();
		lbl1.setBackground(new Color(255, 255, 255));
		lbl1.setText("Credits betting on this game : ");
		pnllbls.add(lbl1);
		lbl1.setVisible(true);

		lbl2 = new JLabel();
		lbl2.setBackground(new Color(255, 255, 255));
		lbl2.setText("?");
		pnllbls.add(lbl2);
		lbl2.setVisible(true);

		lbl3 = new JLabel();
		lbl3.setBackground(new Color(255, 255, 0));
		lbl3.setText("Result : ");
		pnllbls.add(lbl3);
		lbl3.setVisible(true);

		lbl4 = new JLabel();
		lbl4.setBackground(new Color(255, 255, 0));
		lbl4.setText("?");
		pnllbls.add(lbl4);
		lbl4.setVisible(true);

		lbl5 = new JLabel();
		lbl5.setBackground(new Color(255, 255, 255));
		lbl5.setText("Available credits : ");
		pnllbls.add(lbl5);
		lbl5.setVisible(true);

		lbl6 = new JLabel();
		lbl6.setBackground(new Color(255, 255, 255));
		lbl6.setText("?");
		pnllbls.add(lbl6);
		lbl6.setVisible(true);

	}

	public static void main(String[] args) {
		SlotMachineGUI cw = new SlotMachineGUI();
		cw.createForm();

	}

}
