package a9;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


public class PixelInspectorUI extends JPanel implements ActionListener{

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copy;
	private Pixel pix;
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("RGB Values: ");
		copy = new JButton("Copy color to Paint tool");
		
		copy.addActionListener(this);

		setLayout(new GridLayout(4,1));
		add(x_label);
		add(y_label);
		add(pixel_info);
		add(copy);
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText("RGB Values: " + String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
		pix = new ColorPixel(p.getRed(), p.getGreen(), p.getBlue());
		//System.out.println(p.getRed() + " " + p.getGreen() + " " + p.getBlue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(pix.getRed() + " " + pix.getGreen() + " " + pix.getBlue());
		
		int red = (int) (pix.getRed() * 100.0);
		int green = (int) (pix.getGreen() * 100.0);
		int blue = (int) (pix.getBlue() * 100.0);
		
		PaintBrushToolUI.copySlider(red, green, blue);
		//System.out.println(red + " " + green + " " + blue);
	}
	
	public Pixel getCopiedPixel() {
		return pix;
	}
}
