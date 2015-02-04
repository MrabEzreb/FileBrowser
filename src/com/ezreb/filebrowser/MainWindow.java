package com.ezreb.filebrowser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.JLabel;

import com.ezreb.filebrowser.images.ImageLoader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class MainWindow extends JDialog {

	private final JScrollPane contentPanel = new JScrollPane();
	private JTextField FileNameField;
	private JLabel lblFileName;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainWindow dialog = new MainWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MainWindow() {
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.setLayout(new ScrollPaneLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setViewportView(this.view);
		view.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.RIGHT);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				lblFileName = new JLabel("File:");
				lblFileName.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(lblFileName);
			}
			{
				FileNameField = new JTextField();
				lblFileName.setLabelFor(FileNameField);
				FileNameField.setMinimumSize(new Dimension(100, 20));
				FileNameField.setSize(new Dimension(100, 0));
				FileNameField.setPreferredSize(new Dimension(100, 20));
				FileNameField.setBounds(new Rectangle(0, 0, 100, 0));
				buttonPane.add(FileNameField);
				FileNameField.setColumns(25);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MainWindow.this.dispose();
						MainWindow.this.done = true;
						MainWindow.this.setVisible(false);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.finalFile = null;
						MainWindow.this.done = true;
						MainWindow.this.setVisible(false);
						MainWindow.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel quickAccessPanel = new JPanel();
			quickAccessPanel.setPreferredSize(new Dimension(90, 233));
			//panel.setPreferredSize(new Dimension(85, 205));
			System.out.println();
			getContentPane().add(quickAccessPanel, BorderLayout.WEST);
			GridBagLayout gbl_quickAccessPanel = new GridBagLayout();
			gbl_quickAccessPanel.columnWidths = new int[] {5, 90, 5};
			gbl_quickAccessPanel.rowHeights = new int[] {16, 50, 50, 50, 50, 16};
			gbl_quickAccessPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
			gbl_quickAccessPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			quickAccessPanel.setLayout(gbl_quickAccessPanel);
			{
				JButton btnDesktop = new JButton("Desktop");
				btnDesktop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.currentDir = new File(System.getProperty("user.home")+"\\Desktop");
						MainWindow.this.updateListing();
					}
				});
				btnDesktop.setSize(new Dimension(80, 50));
				btnDesktop.setMargin(new Insets(2, 10, 2, 10));
				btnDesktop.setPreferredSize(new Dimension(80, 50));
				GridBagConstraints gbc_btnDesktop = new GridBagConstraints();
				gbc_btnDesktop.fill = GridBagConstraints.BOTH;
				gbc_btnDesktop.insets = new Insets(0, 5, 5, 5);
				gbc_btnDesktop.gridx = 1;
				gbc_btnDesktop.gridy = 1;
				quickAccessPanel.add(btnDesktop, gbc_btnDesktop);
			}
			{
				JButton btnAppdataroaming = new JButton("AppData");
				btnAppdataroaming.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.currentDir = new File(System.getProperty("user.home")+"\\AppData\\Roaming");
						MainWindow.this.updateListing();
					}
				});
				btnAppdataroaming.setMargin(new Insets(2, 10, 2, 10));
				btnAppdataroaming.setPreferredSize(new Dimension(80, 50));
				GridBagConstraints gbc_btnAppdataroaming = new GridBagConstraints();
				gbc_btnAppdataroaming.fill = GridBagConstraints.BOTH;
				gbc_btnAppdataroaming.insets = new Insets(0, 5, 5, 5);
				gbc_btnAppdataroaming.gridx = 1;
				gbc_btnAppdataroaming.gridy = 2;
				quickAccessPanel.add(btnAppdataroaming, gbc_btnAppdataroaming);
			}
			{
				JButton btnEzrebFolder = new JButton("Ezreb");
				btnEzrebFolder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.currentDir = new File(System.getProperty("user.home")+"\\AppData\\Roaming\\Ezreb");
						MainWindow.this.updateListing();
					}
				});
				btnEzrebFolder.setMargin(new Insets(2, 10, 2, 10));
				btnEzrebFolder.setPreferredSize(new Dimension(80, 50));
				GridBagConstraints gbc_btnEzrebFolder = new GridBagConstraints();
				gbc_btnEzrebFolder.fill = GridBagConstraints.BOTH;
				gbc_btnEzrebFolder.insets = new Insets(0, 5, 5, 5);
				gbc_btnEzrebFolder.gridx = 1;
				gbc_btnEzrebFolder.gridy = 3;
				quickAccessPanel.add(btnEzrebFolder, gbc_btnEzrebFolder);
			}
			{
				JButton btnTest = new JButton("C:\\");
				btnTest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainWindow.this.currentDir = new File("C:\\");
						MainWindow.this.updateListing();
					}
				});
				btnTest.setSize(new Dimension(80, 50));
				btnTest.setPreferredSize(new Dimension(80, 50));
				GridBagConstraints gbc_btnTest = new GridBagConstraints();
				gbc_btnTest.fill = GridBagConstraints.BOTH;
				gbc_btnTest.insets = new Insets(0, 5, 5, 5);
				gbc_btnTest.gridx = 1;
				gbc_btnTest.gridy = 4;
				quickAccessPanel.add(btnTest, gbc_btnTest);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblFolderPath = new JLabel("Folder Path:");
				panel.add(lblFolderPath);
			}
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(25);
			}
			{
				JButton btnUpLevel = new JButton("");
				btnUpLevel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MainWindow.this.currentDir = MainWindow.this.currentDir.getParentFile();
						MainWindow.this.updateListing();
					}
				});
				panel.add(btnUpLevel);
				btnUpLevel.setIcon(new ImageIcon(ImageLoader.ARROW_UP));
				btnUpLevel.setToolTipText("Go Up One Level");
				btnUpLevel.setPreferredSize(new Dimension(23, 23));
			}
		}
	}
	public JPanel view = new JPanel();
	public File[] files;
	public File[] folder;
	public File currentDir;
	public int xleft = 5;
	public int xright = 165;
	public int ystart = 2;
	public int yend = 20;
	public int yadd = 20;
	public boolean done = false;
	public File finalFile;
	public void updateListing() {
		try {
			this.FileNameField.setText(this.finalFile.getName());
		} catch(NullPointerException npe) {
			
		}
		this.textField.setText(this.currentDir.getAbsolutePath());
		this.folder = this.currentDir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		this.files = this.currentDir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
		});
		this.view.removeAll();
		this.view.setSize(325, 20*(folder.length+files.length));
		double length = Math.ceil(((double) folder.length+(double) files.length)/2);
		this.view.setPreferredSize(new Dimension(325, (int) (20*length)));
		boolean hasFirst = false;
		int ylevel = ystart;
		for (File file : folder) {
			if(hasFirst == true) {
				hasFirst = false;
				FileEntry fe = new FileEntry(file.getName(), file);
				fe.setLocation(xright, ylevel);
				this.view.add(fe);
				ylevel = ylevel + this.yadd;
			} else if(hasFirst == false) {
				hasFirst = true;
				FileEntry fe = new FileEntry(file.getName(), file);
				fe.setLocation(xleft, ylevel);
				this.view.add(fe);
			}
		}
		for (File file : files) {
			if(hasFirst == true) {
				hasFirst = false;
				FileEntry fe = new FileEntry(file.getName(), file);
				fe.setLocation(xright, ylevel);
				this.view.add(fe);
				ylevel = ylevel + this.yadd;
			} else if(hasFirst == false) {
				hasFirst = true;
				FileEntry fe = new FileEntry(file.getName(), file);
				fe.setLocation(xleft, ylevel);
				this.view.add(fe);
			}
		}
	}
	public JTextField getTextField() {
		return textField;
	}
	public File getSelectedFile() {
		while(this.done == false) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return finalFile;
	}
	public void setSelected(FileEntry fe) {
		Component[] cs = this.view.getComponents();
		for (Component component : cs) {
			try {
				((FileEntry) component).setSelected(false);
			} catch(ClassCastException c) {
				System.out.println("Error: Not a file entry.");
			}
		}
		fe.setSelected(true);
		fe.setColoro();
		this.updateListing();
	}

}

@SuppressWarnings("serial")
class FileEntry extends JToggleButton {

	public FileEntry(String name, File f) {
		this.name = name;
		if(f.isDirectory()) {
			this.im = ImageLoader.FOLDER;
		} else if(name.endsWith(".txt")) {
			this.im = ImageLoader.TEXT;
		} else if(name.endsWith(".lua") || name.endsWith(".java")) {
			this.im = ImageLoader.SCRIPT;
		} else {
			this.im = ImageLoader.FILE;
		}
		setText(name);
		setIcon(new ImageIcon(this.im));
		setMargin(new Insets(0, 0, 0, 0));
		setHorizontalAlignment(SwingConstants.LEFT);
		setBorderPainted(false);
		setPreferredSize(new Dimension(155, 18));
		setSize(new Dimension(155, 18));
		setBackground(new Color(0, 0, 0, 0));
		setToolTipText(name);
		setOpaque(false);
		this.f = f;
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					((MainWindow) FileEntry.this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).finalFile = FileEntry.this.f;
				} catch(ClassCastException c) {
					c.printStackTrace();
				}
				((MainWindow) FileEntry.this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).setSelected(FileEntry.this);
			}
		});
		addMouseListener(new MouseAdapter(){
			@Override
		    public void mousePressed(MouseEvent e){
		    	System.out.println(e.getClickCount());
		        if(e.getClickCount()==2){
		        	System.out.println("already");
		        	FileEntry fe = FileEntry.this;
		        	MainWindow mw = ((MainWindow) FileEntry.this.getParent().getParent().getParent().getParent().getParent().getParent().getParent());
					if(fe.f.isDirectory()) {
						mw.currentDir = fe.f;
					} else if(fe.f.isFile()) {
						mw.dispose();
						mw.done = true;
						mw.setVisible(false);
					}
		        }
		    }
		});
	}
	public boolean isAlreadyOneClick = false;
	public void setColoro() {
		System.out.println("yes");
		this.isAlreadyOneClick = true;
	}
	public String name;
	public Image im;
	public File f;
}