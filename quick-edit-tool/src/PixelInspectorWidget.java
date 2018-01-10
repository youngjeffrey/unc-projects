package a8;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorWidget extends JPanel implements MouseListener{

	private PictureView picture_view;
	private TextField x;
	private TextField y;
	private TextField red;
	private TextField green;
	private TextField blue;
	private TextField brightness;
	
	
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	public PixelInspectorWidget(Picture p) {
		
		setLayout(new FlowLayout());
		
		picture_view = new PictureView(p.createObservable());
		picture_view.addMouseListener(this);
		
		//adds a textfield for the X value of the picture
		Label x_coordinate = new Label("X: ");
		add(x_coordinate);
		x = new TextField(5);
		x.setEditable(false);
		add(x);
		
		//adds a textfield for the Y value of the picture
		Label y_coordinate = new Label("Y: ");
		add(y_coordinate);
		y = new TextField(5);
		y.setEditable(false);
		add(y);
		
		//adds a textfield for the red intensity
		Label _red = new Label("Red: ");
		add(_red);
		red = new TextField(5);
		red.setEditable(false);
		add(red);
		
		//adds a textfield for the green intensity
		Label _green = new Label("Green: ");
		add(_green);	
		green = new TextField(5);
		green.setEditable(false);
		add(green);
		
		//adds a textfield for the blue intensity
		Label _blue = new Label("Blue: ");
		add(_blue);	
		blue = new TextField(5);
		blue.setEditable(false);
		add(blue);
		
		//adds a textfield for the brightness intensity
		Label _brightness = new Label("Brightness: ");
		add(_brightness);	
		brightness = new TextField(5);
		brightness.setEditable(false);
		add(brightness);
		
		add(picture_view);

	}

	public void mouseClicked(MouseEvent e) {
		
		//retrieves x coordinate value from mouseclick
		x.setText("" + e.getX());
		
		//retrieves y coordinate value from mouseclick
		y.setText("" + e.getY());
		
		//retrieves red intensity value from mouseclick
		red.setText("" + df2.format(picture_view.getPicture().getPixel(e.getX(), e.getY()).getRed()));
		
		//retrieves blue intensity value from mouseclick
		blue.setText("" + df2.format(picture_view.getPicture().getPixel(e.getX(), e.getY()).getBlue()));
		
		//retrieves green intensity value from mouseclick
		green.setText("" + df2.format(picture_view.getPicture().getPixel(e.getX(), e.getY()).getGreen()));
		
		//retrives brightness intensity value from mouseclick
		brightness.setText("" + df2.format(picture_view.getPicture().getPixel(e.getX(), e.getY()).getIntensity()));

		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
