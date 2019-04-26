
/**   
 * @Title: CaculatorApplication.java 
 * @Package: cn.yank.lesson_three 
 * @Description: TODO
 * @author Yank  
 * @date 2019年3月30日 上午9:20:14 
 * @version 0.1 
 */


package cn.yank.lesson_three;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.MatteBorder;

/** 
 * @Description 
 * @author Yank
 * @date 2019年3月30日 上午9:20:14 
 * @version V0.1
 */

public class CaculatorApplication {

	private JFrame frame;
	private JTextField currentOperationText;
	private JTextField performedOpertation;
	private StringBuilder previousExpression;
	private StringBuilder currentExpression;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaculatorApplication window = new CaculatorApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CaculatorApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		previousExpression = new StringBuilder();
		currentExpression = new StringBuilder();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.controlShadow);
		frame.setTitle("\u8BA1\u7B97\u5668");
		frame.setBounds(100, 100, 518, 728);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.controlShadow);
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u67E5\u770B");
		menu.setBackground(SystemColor.controlShadow);
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6807\u51C6\u578B");
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u79D1\u5B66\u578B");
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u7A0B\u5E8F\u5458");
		menu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u7EDF\u8BA1\u4FE1\u606F");
		menu.add(menuItem_3);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u5386\u53F2\u8BB0\u5F55");
		menu.add(mntmNewMenuItem);
		
		JMenuItem menuItem_4 = new JMenuItem("\u6570\u5B57\u5206\u7EC4");
		menu.add(menuItem_4);
		
		JSeparator separator_1 = new JSeparator();
		menu.add(separator_1);
		
		JMenuItem menuItem_5 = new JMenuItem("\u57FA\u672C");
		menu.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("\u5355\u4F4D\u8F6C\u6362");
		menu.add(menuItem_6);
		
		
		JMenuItem menuItem_7 = new JMenuItem("\u65E5\u671F\u8BA1\u7B97");
		menu.add(menuItem_7);
		
		JMenu menu_3 = new JMenu("\u5DE5\u4F5C\u8868");
		menu.add(menu_3); 
		
		
		JMenuItem menuItem_10 = new JMenuItem("\u62B5\u62BC");
		menu_3.add(menuItem_10);
		
		JMenuItem menuItem_11 = new JMenuItem("\u6C7D\u8F66\u79DF\u8D41");
		menu_3.add(menuItem_11);
		
		JMenuItem menuItem_12 = new JMenuItem("\u6CB9\u8017");
		menu_3.add(menuItem_12);
		
		JMenuItem menuItem_13 = new JMenuItem("\u6CB9\u8017");
		menu_3.add(menuItem_13);
		
		JMenu editMenu = new JMenu("\u7F16\u8F91");
		menuBar.add(editMenu);
		
		JMenuItem menuItem_8 = new JMenuItem("\u590D\u5236");
		editMenu.add(menuItem_8);
		
		JMenuItem menuItem_9 = new JMenuItem("\u7C98\u8D34");
		editMenu.add(menuItem_9);
		
		JSeparator separator_2 = new JSeparator();
		editMenu.add(separator_2);
		
		JMenu menu_4 = new JMenu("\u5386\u53F2\u8BB0\u5F55");
		editMenu.add(menu_4);
		
		JMenuItem menuItem_14 = new JMenuItem("\u590D\u5236\u5386\u53F2\u8BB0\u5F55");
		menu_4.add(menuItem_14);
		
		JMenuItem menuItem_15 = new JMenuItem("\u7F16\u8F91");
		menu_4.add(menuItem_15);
		
		JMenuItem menuItem_16 = new JMenuItem("\u53D6\u6D88\u7F16\u8F91");
		menu_4.add(menuItem_16);
		
		JMenuItem menuItem_17 = new JMenuItem("\u6E05\u9664");
		menu_4.add(menuItem_17);
		
		JMenu menu_1 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_18 = new JMenuItem("\u67E5\u770B\u5E2E\u52A9");
		menu_1.add(menuItem_18);
		
		JMenuItem menuItem_19 = new JMenuItem("\u5173\u4E8E\u8BA1\u7B97\u5668");
		menu_1.add(menuItem_19);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 175, 480, 480);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{96, 96, 96, 96, 96, 0};
		gbl_panel.rowHeights = new int[]{80, 80, 80, 80, 80, 80, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnMc = new JButton("MC");
		btnMc.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMc.setForeground(Color.BLACK);
		btnMc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnMc = new GridBagConstraints();
		gbc_btnMc.fill = GridBagConstraints.BOTH;
		gbc_btnMc.insets = new Insets(0, 0, 5, 5);
		gbc_btnMc.gridx = 0;
		gbc_btnMc.gridy = 0;
		panel.add(btnMc, gbc_btnMc);
		
		JButton btnMr = new JButton("MR");
		btnMr.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMr.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnMr = new GridBagConstraints();
		gbc_btnMr.fill = GridBagConstraints.BOTH;
		gbc_btnMr.insets = new Insets(0, 0, 5, 5);
		gbc_btnMr.gridx = 1;
		gbc_btnMr.gridy = 0;
		panel.add(btnMr, gbc_btnMr);
		
		JButton btnMs = new JButton("MS");
		btnMs.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMs.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnMs = new GridBagConstraints();
		gbc_btnMs.fill = GridBagConstraints.BOTH;
		gbc_btnMs.insets = new Insets(0, 0, 5, 5);
		gbc_btnMs.gridx = 2;
		gbc_btnMs.gridy = 0;
		panel.add(btnMs, gbc_btnMs);
		
		JButton btnMAdd = new JButton("M+");
		btnMAdd.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMAdd.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnMAdd = new GridBagConstraints();
		gbc_btnMAdd.fill = GridBagConstraints.BOTH;
		gbc_btnMAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnMAdd.gridx = 3;
		gbc_btnMAdd.gridy = 0;
		panel.add(btnMAdd, gbc_btnMAdd);
		
		JButton btnMMinus = new JButton("M-");
		btnMMinus.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMMinus.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnMMinus = new GridBagConstraints();
		gbc_btnMMinus.fill = GridBagConstraints.BOTH;
		gbc_btnMMinus.insets = new Insets(0, 0, 5, 0);
		gbc_btnMMinus.gridx = 4;
		gbc_btnMMinus.gridy = 0;
		panel.add(btnMMinus, gbc_btnMMinus);
		
		JButton btnDel = new JButton("DEL");
		btnDel.setFont(new Font("仿宋", Font.PLAIN, 23));
		btnDel.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDel = new GridBagConstraints();
		gbc_btnDel.fill = GridBagConstraints.BOTH;
		gbc_btnDel.insets = new Insets(0, 0, 5, 5);
		gbc_btnDel.gridx = 0;
		gbc_btnDel.gridy = 1;
		panel.add(btnDel, gbc_btnDel);
		
		JButton btnCe = new JButton("CE");
		btnCe.setFont(new Font("宋体", Font.PLAIN, 23));
		btnCe.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnCe = new GridBagConstraints();
		gbc_btnCe.fill = GridBagConstraints.BOTH;
		gbc_btnCe.insets = new Insets(0, 0, 5, 5);
		gbc_btnCe.gridx = 1;
		gbc_btnCe.gridy = 1;
		panel.add(btnCe, gbc_btnCe);
		
		JButton btnC = new JButton("C");
		btnC.setFont(new Font("宋体", Font.PLAIN, 23));
		btnC.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnC = new GridBagConstraints();
		gbc_btnC.fill = GridBagConstraints.BOTH;
		gbc_btnC.insets = new Insets(0, 0, 5, 5);
		gbc_btnC.gridx = 2;
		gbc_btnC.gridy = 1;
		panel.add(btnC, gbc_btnC);
		
		JButton btnAddMinus = new JButton("\u00B1");
		btnAddMinus.setFont(new Font("宋体", Font.PLAIN, 23));
		btnAddMinus.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnAddMinus = new GridBagConstraints();
		gbc_btnAddMinus.fill = GridBagConstraints.BOTH;
		gbc_btnAddMinus.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddMinus.gridx = 3;
		gbc_btnAddMinus.gridy = 1;
		panel.add(btnAddMinus, gbc_btnAddMinus);
		
		JButton btnSqrt = new JButton("\u221A");
		btnSqrt.setFont(new Font("宋体", Font.PLAIN, 23));
		btnSqrt.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnSqrt = new GridBagConstraints();
		gbc_btnSqrt.fill = GridBagConstraints.BOTH;
		gbc_btnSqrt.insets = new Insets(0, 0, 5, 0);
		gbc_btnSqrt.gridx = 4;
		gbc_btnSqrt.gridy = 1;
		panel.add(btnSqrt, gbc_btnSqrt);
		
		JButton buttonSeven = new JButton("7");
		buttonSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("7");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonSeven.setForeground(Color.BLACK);
		buttonSeven.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonSeven = new GridBagConstraints();
		gbc_buttonSeven.fill = GridBagConstraints.BOTH;
		gbc_buttonSeven.insets = new Insets(0, 0, 5, 5);
		gbc_buttonSeven.gridx = 0;
		gbc_buttonSeven.gridy = 2;
		panel.add(buttonSeven, gbc_buttonSeven);
		
		JButton buttonEight = new JButton("8");
		buttonEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("8");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonEight.setForeground(Color.BLACK);
		buttonEight.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonEight = new GridBagConstraints();
		gbc_buttonEight.fill = GridBagConstraints.BOTH;
		gbc_buttonEight.insets = new Insets(0, 0, 5, 5);
		gbc_buttonEight.gridx = 1;
		gbc_buttonEight.gridy = 2;
		panel.add(buttonEight, gbc_buttonEight);
		
		JButton buttonNine = new JButton("9");
		buttonNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("9");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonNine.setForeground(Color.BLACK);
		buttonNine.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonNine = new GridBagConstraints();
		gbc_buttonNine.fill = GridBagConstraints.BOTH;
		gbc_buttonNine.insets = new Insets(0, 0, 5, 5);
		gbc_buttonNine.gridx = 2;
		gbc_buttonNine.gridy = 2;
		panel.add(buttonNine, gbc_buttonNine);
		
		JButton btnDivide = new JButton("/");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isPun = isPuntuation(currentExpression.charAt(currentExpression.length()-1));
				if (isPun) {
					currentExpression = new StringBuilder(currentExpression.substring(0, currentExpression.length()-2)).append("/");			
				}else {
					currentExpression.append("/");
				}
				performedOpertation.setText(currentExpression.toString());
				currentOperationText.setText(null);
			}
		});
		btnDivide.setFont(new Font("宋体", Font.PLAIN, 23));
		btnDivide.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDivide = new GridBagConstraints();
		gbc_btnDivide.fill = GridBagConstraints.BOTH;
		gbc_btnDivide.insets = new Insets(0, 0, 5, 5);
		gbc_btnDivide.gridx = 3;
		gbc_btnDivide.gridy = 2;
		panel.add(btnDivide, gbc_btnDivide);
		
		JButton button = new JButton("%");
		button.setFont(new Font("宋体", Font.PLAIN, 23));
		button.setForeground(Color.BLACK);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 4;
		gbc_button.gridy = 2;
		panel.add(button, gbc_button);
		
		JButton buttonFour = new JButton("4");
		buttonFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("4");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonFour.setForeground(Color.BLACK);
		buttonFour.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonFour = new GridBagConstraints();
		gbc_buttonFour.fill = GridBagConstraints.BOTH;
		gbc_buttonFour.insets = new Insets(0, 0, 5, 5);
		gbc_buttonFour.gridx = 0;
		gbc_buttonFour.gridy = 3;
		panel.add(buttonFour, gbc_buttonFour);
		
		JButton buttonFive = new JButton("5");
		buttonFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("5");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonFive.setForeground(Color.BLACK);
		buttonFive.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonFive = new GridBagConstraints();
		gbc_buttonFive.fill = GridBagConstraints.BOTH;
		gbc_buttonFive.insets = new Insets(0, 0, 5, 5);
		gbc_buttonFive.gridx = 1;
		gbc_buttonFive.gridy = 3;
		panel.add(buttonFive, gbc_buttonFive);
		
		JButton buttonSix = new JButton("6");
		buttonSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("6");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonSix.setForeground(Color.BLACK);
		buttonSix.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonSix = new GridBagConstraints();
		gbc_buttonSix.fill = GridBagConstraints.BOTH;
		gbc_buttonSix.insets = new Insets(0, 0, 5, 5);
		gbc_buttonSix.gridx = 2;
		gbc_buttonSix.gridy = 3;
		panel.add(buttonSix, gbc_buttonSix);
		
		JButton btnMultiply = new JButton("*");
		btnMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isPun = isPuntuation(currentExpression.charAt(currentExpression.length()-1));
				if (isPun) {
					currentExpression = new StringBuilder(currentExpression.substring(0, currentExpression.length()-2)).append("*");			
				}else {
					currentExpression.append("*");
				}
				performedOpertation.setText(currentExpression.toString());
				currentOperationText.setText(null);
			}
		});
		btnMultiply.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMultiply.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnMultiply = new GridBagConstraints();
		gbc_btnMultiply.fill = GridBagConstraints.BOTH;
		gbc_btnMultiply.insets = new Insets(0, 0, 5, 5);
		gbc_btnMultiply.gridx = 3;
		gbc_btnMultiply.gridy = 3;
		panel.add(btnMultiply, gbc_btnMultiply);
		
		JButton btnOneDivideX = new JButton("1/x");
		btnOneDivideX.setFont(new Font("宋体", Font.PLAIN, 23));
		btnOneDivideX.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnOneDivideX = new GridBagConstraints();
		gbc_btnOneDivideX.fill = GridBagConstraints.BOTH;
		gbc_btnOneDivideX.insets = new Insets(0, 0, 5, 0); 
		gbc_btnOneDivideX.gridx = 4;
		gbc_btnOneDivideX.gridy = 3;
		panel.add(btnOneDivideX, gbc_btnOneDivideX);
		
		JButton buttonOne = new JButton("1");
		buttonOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("1");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonOne.setForeground(Color.BLACK);
		buttonOne.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonOne = new GridBagConstraints();
		gbc_buttonOne.fill = GridBagConstraints.BOTH;
		gbc_buttonOne.insets = new Insets(0, 0, 5, 5);
		gbc_buttonOne.gridx = 0;
		gbc_buttonOne.gridy = 4;
		panel.add(buttonOne, gbc_buttonOne);
		
		JButton buttonTwo = new JButton("2");
		buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("2");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonTwo.setForeground(Color.BLACK);
		buttonTwo.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonTwo = new GridBagConstraints();
		gbc_buttonTwo.fill = GridBagConstraints.BOTH;
		gbc_buttonTwo.insets = new Insets(0, 0, 5, 5);
		gbc_buttonTwo.gridx = 1;
		gbc_buttonTwo.gridy = 4;
		panel.add(buttonTwo, gbc_buttonTwo);
		
		JButton buttonThree = new JButton("3");
		buttonThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("3");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonThree.setForeground(Color.BLACK);
		buttonThree.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonThree = new GridBagConstraints();
		gbc_buttonThree.fill = GridBagConstraints.BOTH;
		gbc_buttonThree.insets = new Insets(0, 0, 5, 5);
		gbc_buttonThree.gridx = 2;
		gbc_buttonThree.gridy = 4;
		panel.add(buttonThree, gbc_buttonThree);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isPun = isPuntuation(currentExpression.charAt(currentExpression.length()-1));
				if (isPun) {
					currentExpression = new StringBuilder(currentExpression.substring(0, currentExpression.length()-2)).append("-");			
				}else {
					currentExpression.append("-");
				}
				performedOpertation.setText(currentExpression.toString());
				currentOperationText.setText(null);
			}
		});
		btnMinus.setFont(new Font("宋体", Font.PLAIN, 23));
		btnMinus.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnMinus = new GridBagConstraints();
		gbc_btnMinus.fill = GridBagConstraints.BOTH;
		gbc_btnMinus.insets = new Insets(0, 0, 5, 5);
		gbc_btnMinus.gridx = 3;
		gbc_btnMinus.gridy = 4;
		panel.add(btnMinus, gbc_btnMinus);
		
		JButton buttonZero = new JButton("0");
		buttonZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentExpression.append("0");
				currentOperationText.setText(currentExpression.toString());
			}
		});
		buttonZero.setForeground(Color.BLACK);
		buttonZero.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_buttonZero = new GridBagConstraints();
		gbc_buttonZero.gridwidth = 2;
		gbc_buttonZero.fill = GridBagConstraints.BOTH;
		gbc_buttonZero.insets = new Insets(0, 0, 0, 5);
		gbc_buttonZero.gridx = 0;
		gbc_buttonZero.gridy = 5;
		panel.add(buttonZero, gbc_buttonZero);
		
		JButton btnPoint = new JButton(".");
		btnPoint.setForeground(Color.BLACK);
		btnPoint.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_btnPoint = new GridBagConstraints();
		gbc_btnPoint.fill = GridBagConstraints.BOTH;
		gbc_btnPoint.insets = new Insets(0, 0, 0, 5);
		gbc_btnPoint.gridx = 2;
		gbc_btnPoint.gridy = 5;
		panel.add(btnPoint, gbc_btnPoint);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isPun = isPuntuation(currentExpression.charAt(currentExpression.length()-1));
				if (isPun) {
					currentExpression = new StringBuilder(currentExpression.substring(0, currentExpression.length()-2)).append("+");			
				}else {
					currentExpression.append("+");
				}
				performedOpertation.setText(currentExpression.toString());
				currentOperationText.setText(null);
			}
		});
		btnPlus.setFont(new Font("宋体", Font.PLAIN, 23));
		btnPlus.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnPlus = new GridBagConstraints();
		gbc_btnPlus.fill = GridBagConstraints.BOTH;
		gbc_btnPlus.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlus.gridx = 3;
		gbc_btnPlus.gridy = 5;
		panel.add(btnPlus, gbc_btnPlus);
		
		JButton btnEqual = new JButton("=");
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					performedOpertation.setText(currentExpression.toString());
					currentOperationText.setText(getResult(currentExpression.toString()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					performedOpertation.setText(currentExpression.toString());
					currentOperationText.setText("您的表达式存在数学错误");
					e1.printStackTrace();
				}
			}
		});
		btnEqual.setFont(new Font("宋体", Font.PLAIN, 23));
		btnEqual.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnEqual = new GridBagConstraints();
		gbc_btnEqual.gridheight = 2;
		gbc_btnEqual.fill = GridBagConstraints.BOTH;
		gbc_btnEqual.gridx = 4;
		gbc_btnEqual.gridy = 4;
		panel.add(btnEqual, gbc_btnEqual);
		
		currentOperationText = new JTextField();
		currentOperationText.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		currentOperationText.setForeground(Color.BLACK);
		currentOperationText.setFont(new Font("宋体", Font.PLAIN, 30));
		currentOperationText.setHorizontalAlignment(SwingConstants.RIGHT);
		currentOperationText.setEditable(false);
		currentOperationText.setBounds(10, 61, 480, 104);
		frame.getContentPane().add(currentOperationText);
		currentOperationText.setColumns(10);
		
		performedOpertation = new JTextField();
		performedOpertation.setHorizontalAlignment(SwingConstants.RIGHT);
		performedOpertation.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		performedOpertation.setEditable(false);
		performedOpertation.setBounds(10, 10, 480, 52);
		frame.getContentPane().add(performedOpertation);
		performedOpertation.setColumns(10);
	
	}
	
	public boolean isPuntuation (char ch) {
		if (ch == '+' && ch == '-' && ch == '*' && ch == '/') {
			return true;
		}
		return false;
	}
	
	public static String getResult(String expr) throws Exception {
        System.out.println("计算"+expr);
        /*数字栈*/
        Stack<Double> number = new Stack<Double>(); 
        /*符号栈*/
        Stack<String> operator = new Stack<String>();
        operator.push(null);// 在栈顶压人一个null，配合它的优先级，目的是减少下面程序的判断

        /* 将expr打散为运算数和运算符 */
        Pattern p = Pattern.compile("(?<!\\d)-?\\d+(\\.\\d+)?|[+\\-*/()]");// 这个正则为匹配表达式中的数字或运算符
        Matcher m = p.matcher(expr);
        while(m.find()) {
            String temp = m.group();
            if(temp.matches("[+\\-*/()]")) {//遇到符号
                if(temp.equals("(")) {//遇到左括号，直接入符号栈
                    operator.push(temp);
                    System.out.println("符号栈更新："+operator);
                }else if(temp.equals(")")){//遇到右括号，"符号栈弹栈取栈顶符号b，数字栈弹栈取栈顶数字a1，数字栈弹栈取栈顶数字a2，计算a2 b a1 ,将结果压入数字栈"，重复引号步骤至取栈顶为左括号，将左括号弹出
                    String b = null;
                    while(!(b = operator.pop()).equals("(")) {
                        System.out.println("符号栈更新："+operator);
                        double a1 = number.pop();
                        double a2 = number.pop();
                        System.out.println("数字栈更新："+number);
                        System.out.println("计算"+a2+b+a1);
                        number.push(doubleCal(a2, a1, b.charAt(0)));
                        System.out.println("数字栈更新："+number);
                    }
                    System.out.println("符号栈更新："+operator);
                }else {//遇到运算符，满足该运算符的优先级大于栈顶元素的优先级压栈；否则计算后压栈
                    while(getPriority(temp) <= getPriority(operator.peek())) {
                        double a1 = number.pop();
                        double a2 = number.pop();
                        String b = operator.pop();
                        System.out.println("符号栈更新："+operator);
                        System.out.println("数字栈更新："+number);
                        System.out.println("计算"+a2+b+a1);
                        number.push(doubleCal(a2, a1, b.charAt(0)));
                        System.out.println("数字栈更新："+number);
                    }
                    operator.push(temp);
                    System.out.println("符号栈更新："+operator);
                }
            }else {//遇到数字，直接压入数字栈
                number.push(Double.valueOf(temp));
                System.out.println("数字栈更新："+number);
            }
        }

        while(operator.peek()!=null) {//遍历结束后，符号栈数字栈依次弹栈计算，并将结果压入数字栈
            double a1 = number.pop();
            double a2 = number.pop();
            String b = operator.pop();
            System.out.println("符号栈更新："+operator);
            System.out.println("数字栈更新："+number);
            System.out.println("计算"+a2+b+a1);
            number.push(doubleCal(a2, a1, b.charAt(0)));
            System.out.println("数字栈更新："+number);
        }
        return number.pop()+"";
    }
	
	private static int getPriority(String s) throws Exception {
        if(s==null) return 0;
        switch(s) {
        case "(":return 1;
        case "+":;
        case "-":return 2;
        case "*":;
        case "/":return 3;
        default:break;
        }
        throw new Exception("illegal operator!");
    }
	
	private static double doubleCal(double a1, double a2, char operator) throws Exception {
        switch (operator) {
        case '+':
            return a1 + a2;
        case '-':
            return a1 - a2;
        case '*':
            return a1 * a2;
        case '/':
            return a1 / a2;
        default:
            break;
        }
        throw new Exception("illegal operator!");
    }
}
