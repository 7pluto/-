package model;

import javax.swing.ImageIcon;

import util.ValuesListUtil;

public class ImageList {

	private Class<?> c;
	private ImageIcon imageButtonExit0 = null;
	private ImageIcon imageButtonExit1 = null;
	private ImageIcon imageButtonExitGray = null;
	private ImageIcon imageButtonExitDialog = null;
	private ImageIcon imageButtonMinimize0 = null;
	private ImageIcon imageButtonMinimize1 = null;

	
	public ImageList(Class<?> c) {
		this.c = c;
		define();
	}


	private void define() {
		imageButtonExit0 = new ImageIcon(c.getResource(ValuesListUtil.IMAGEBUTTONEXITURL0));
		imageButtonExit1 = new ImageIcon(c.getResource(ValuesListUtil.IMAGEBUTTONEXITURL1));
		imageButtonExitGray = new ImageIcon(c.getResource(ValuesListUtil.IMAGEBUTTONEXITGRAYURL));
		imageButtonExitDialog = new ImageIcon(c.getResource(ValuesListUtil.IMAGEBUTTONEXITDIALOGURL));
		imageButtonMinimize0 = new ImageIcon(c.getResource(ValuesListUtil.IMAGEBUTTONMINIMIZEURL0));
		imageButtonMinimize1 = new ImageIcon(c.getResource(ValuesListUtil.IMAGEBUTTONMINIMIZEURL1));
	}


	public ImageIcon getImageButtonExitDialog() {
		return imageButtonExitDialog;
	}


	public ImageIcon getImageButtonExit0() {
		return imageButtonExit0;
	}


	public ImageIcon getImageButtonExit1() {
		return imageButtonExit1;
	}


	public ImageIcon getImageButtonExitGray() {
		return imageButtonExitGray;
	}



	public ImageIcon getImageButtonMinimize0() {
		return imageButtonMinimize0;
	}


	public ImageIcon getImageButtonMinimize1() {
		return imageButtonMinimize1;
	}

}
