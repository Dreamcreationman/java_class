
/**   
 * @Title: NotePad.java 
 * @Package: cn.yank.lesson_four 
 * @Description: TODO
 * @author Yank  
 * @date Apr 13, 2019 2:31:37 PM 
 * @version 0.1 
 */


package cn.yank.lesson_four;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** 
 * @Description 
 * @author Yank
 * @date Apr 13, 2019 2:31:37 PM 
 * @version V0.1
 */

public class NotePad extends JFrame {

	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = -1217407840046136210L;
	private JPanel contentPane;
	private StringBuilder data;
	private JTextArea contentArea;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePad frame = new NotePad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NotePad() {
		
		setTitle("Notepad--untitled.txt");
		data = new StringBuilder();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 582);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (contentArea.getText().toString().length() != 0) {
					int op = JOptionPane.showConfirmDialog(null, "Do you want to save changes to untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (op) {
					case JOptionPane.YES_OPTION:
						saveFile();
						contentArea.setText(null);
						setTitle("Notepad--untitled.txt");
						break;
					case JOptionPane.NO_OPTION:
						contentArea.setText(null);
						setTitle("Notepad-untitled.txt");
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}
				}
			}
		});
		
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (contentArea.getText().toString().length() != 0) {
					int op = JOptionPane.showConfirmDialog(null, "Do you want to save changes to " + getTitle().split("--")[1]+"?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (op) {
					case JOptionPane.YES_OPTION:
						saveFile();
						break;
					case JOptionPane.NO_OPTION:
						contentArea.setText(null);
						setTitle("Notepad-untitled.txt");
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}
					
				}
				openFile();
				
			}
		});
		mnFile.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveFile();		
				
			}
		});
		mnFile.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Save As");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setMultiSelectionEnabled(false);
						int returnval = fileChooser.showSaveDialog(null);
						if (returnval == JFileChooser.APPROVE_OPTION) {
							File saveFile = fileChooser.getSelectedFile();
						
							if (!saveFile.exists()) {
								try {
									saveFile.createNewFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							try {
								FileOutputStream writer = new FileOutputStream(saveFile);
								String writeData = contentArea.getText();
								writer.write(writeData.getBytes());
								writer.close();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}catch (IOException e2) {
								// TODO: handle exception
							}
						}
							
					}
				});
		mnFile.add(mntmNewMenuItem_3);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmPageSetup = new JMenuItem("Page Setup");
		mnFile.add(mntmPageSetup);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mnFile.add(mntmPrint);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Undo");
		mnEdit.add(mntmNewMenuItem_4);
		
		JSeparator separator_2 = new JSeparator();
		mnEdit.add(separator_2);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Copy");
		mnEdit.add(mntmNewMenuItem_5);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Delete");
		mnEdit.add(mntmNewMenuItem_6);
		
		JSeparator separator_3 = new JSeparator();
		mnEdit.add(separator_3);
		
		JMenuItem mntmSearchWithBing = new JMenuItem("Search with Bing");
		mnEdit.add(mntmSearchWithBing);
		
		JMenuItem mntmFine = new JMenuItem("Find");
		mnEdit.add(mntmFine);
		
		JMenuItem mntmFindNext = new JMenuItem("Find Next");
		mnEdit.add(mntmFindNext);
		
		JMenuItem mntmReplace = new JMenuItem("Replace");
		mnEdit.add(mntmReplace);
		
		JMenuItem mntmGoto = new JMenuItem("Go To");
		mnEdit.add(mntmGoto);
		
		JSeparator separator_4 = new JSeparator();
		mnEdit.add(separator_4);
		
		JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mnEdit.add(mntmSelectAll);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Time/Date");
		mnEdit.add(mntmNewMenuItem_7);
		
		JMenu mnFormat = new JMenu("Format");
		menuBar.add(mnFormat);
		
		JCheckBox chckbxWordWrap = new JCheckBox("Word Wrap");
		mnFormat.add(chckbxWordWrap);
		
		JMenuItem mntmWarmWrap = new JMenuItem("Font");
		mnFormat.add(mntmWarmWrap);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnZoom = new JMenu("Zoom");
		mnView.add(mnZoom);
		
		JMenuItem mntmZoomIn = new JMenuItem("Zoom in");
		mnZoom.add(mntmZoomIn);
		
		JMenuItem mntmZoomOut = new JMenuItem("Zoom out");
		mnZoom.add(mntmZoomOut);
		
		JMenuItem mntmRestoreDefault = new JMenuItem("Restore Default Zoom");
		mnZoom.add(mntmRestoreDefault);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Status Bar");
		mnView.add(mntmNewMenuItem_8);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("View Help");
		mnHelp.add(mntmNewMenuItem_9);
		
		JSeparator separator_5 = new JSeparator();
		mnHelp.add(separator_5);
		
		JMenuItem mntmAboutNotepad = new JMenuItem("About Notepad");
		mnHelp.add(mntmAboutNotepad);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		contentArea = new JTextArea();
		scrollPane.setViewportView(contentArea);
	}
	
	public void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnval = fileChooser.showOpenDialog(null);
		if (returnval == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			setTitle("Notepad--"+selectedFile.getAbsolutePath());
			if (selectedFile.exists()) {
				try {
					/*FileInputStream inputStream = new FileInputStream(selectedFile);
					
					int temp = inputStream.read();
					while(temp!=-1) {
						data.append((char)temp);
						temp = inputStream.read();
					}
					inputStream.close();*/
					FileReader reader = new FileReader(selectedFile);
					int datae = reader.read();
					while (datae != -1) {
						data.append((char)datae);
						datae = reader.read();
					}
					if (data.length()  != 0) {
						contentArea.setText(data.toString());
					}
					reader.close();
				}catch (FileNotFoundException ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}
	
	public void saveFile() {
		String path = "";
		if (getTitle().split("--")[1].equals("untitled.txt")) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(false);
			int returnval = fileChooser.showSaveDialog(null);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File saveFile = fileChooser.getSelectedFile();
			
				if (!saveFile.exists()) {
					try {
						saveFile.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				path = saveFile.getAbsolutePath();
			}
		}else {
			path = getTitle().split("--")[1];
		}
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(contentArea.getText().toString().getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
