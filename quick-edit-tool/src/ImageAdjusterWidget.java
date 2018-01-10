package a8;

import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterWidget extends JPanel implements ChangeListener{
	
	private PictureView picture_view;
	private Picture _p;
	private JSlider blur_slider;
	private JSlider saturation_slider;
	private JSlider brightness_slider;
	private Picture modified;
	private Picture blurry_picture;
	private Picture saturated_picture;
	private Picture brightening_picture;
	
	public ImageAdjusterWidget(Picture p) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		picture_view = new PictureView(p.createObservable());
		
		add(picture_view);
		_p = p;
		
		//adds the blur slider
		blur_slider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		blur_slider.addChangeListener(this);
		blur_slider.setMajorTickSpacing(1);
		blur_slider.setPaintTicks(true);
		blur_slider.setPaintLabels(true);
		add(new Label("Blur"));
		add(blur_slider);
		
		//adds the saturation slider
		saturation_slider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		saturation_slider.addChangeListener(this);
		saturation_slider.setMajorTickSpacing(25);
		saturation_slider.setPaintTicks(true);
		saturation_slider.setPaintLabels(true);
		add(new Label("Saturation"));
		add(saturation_slider);
		
		//adds the brightness slider
		brightness_slider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		brightness_slider.addChangeListener(this);
		brightness_slider.setMajorTickSpacing(25);
		brightness_slider.setPaintTicks(true);
		brightness_slider.setPaintLabels(true);
		add(new Label("Brightness"));
		add(brightness_slider);		
		
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			
			//calls blur helper method
			blurry_picture= blurring(_p, blur_slider.getValue());
			saturated_picture = saturating(blurry_picture, saturation_slider.getValue());
			brightening_picture = brightening(saturated_picture, brightness_slider.getValue());
			
		    picture_view.setPicture(brightening_picture.createObservable());
		}
		
			
	}
	
	//helper method for blur slider
	public Picture blurring(Picture p, int value) {
		
		Picture blurry_pic = new PictureImpl(p.getWidth(), p.getHeight());
		
		//maps through the entire picture passed as parameter
		for(int x = 0; x < p.getWidth(); x++) {
			for(int y = 0; y < p.getHeight(); y++) {

				double red_sum = 0;
				double blue_sum = 0;
				double green_sum = 0;
				int total = 0;

				double red_average = 0;
				double blue_average = 0;
				double green_average = 0;

				//maps through selected region based on blur slider value
				for(int a = x - value; a <= x + value; a++) {
					for(int b = y - value; b <= y + value; b++) {
						try {
							//tallies up color values to get average
							red_sum += p.getPixel(a, b).getRed();
							blue_sum += p.getPixel(a, b).getBlue();
							green_sum += p.getPixel(a, b).getGreen();
							total++;
						}
						catch(RuntimeException e){
							continue;
						}
					}
				}

				//calculates the average
				red_average = red_sum/total;
				blue_average = blue_sum/total;
				green_average = green_sum/total;

				//creates new pixel with the average, sets pixel to the picture at the point
				Pixel pix = new ColorPixel(red_average, green_average, blue_average);
				picture_view.getPicture().setPixel(x, y, pix);
				blurry_pic.setPixel(x, y, pix);
			}
		}
		
		return blurry_pic;
	}
	
	//helper method for saturation
	public Picture saturating(Picture p, int value) {
		
		Picture saturated_pic = new PictureImpl(p.getWidth(), p.getHeight());

		
		//maps through the entire picture passed through parameter		
		if(value == 0) {
			return p;
		}
		
		double saturated_red=0;
		double saturated_blue =0;
		double saturated_green=0;
		double b=0;
		
		
		if(value < 0 && value >= -100) {
			
			for(int x = 0; x < p.getWidth(); x++) {
				for(int y = 0; y < p.getHeight(); y++) {
					
					//gets brightness of original pixel
					b = p.getPixel(x, y).getIntensity();
					
					saturated_red = p.getPixel(x, y).getRed() * (1.0 + 
							(value/100.0)) - (b * value/100.0);
					saturated_blue = p.getPixel(x, y).getBlue() * (1.0 + 
							(value/100.0)) - (b * value/100.0);
					saturated_green = p.getPixel(x, y).getGreen() * (1.0 + 
							(value/100.0)) - (b * value/100.0);
					
					//System.out.println(saturated_red);
					
					
					Pixel pix = new ColorPixel(saturated_red, saturated_green, saturated_blue);
					//picture_view.getPicture().setPixel(x, y, pix);
					saturated_pic.setPixel(x, y, pix);
			}
		}
		
		//return saturated_pic;
			
		}else {
			
		//	Picture saturated_pic = new PictureImpl(p.getWidth(), p.getHeight());
			
			for(int x = 0; x < p.getWidth(); x++) {
				for(int y = 0; y < p.getHeight(); y++) {
					
					//gets maximum component of three colors in a pixel
					double red = p.getPixel(x, y).getRed();
					double blue = p.getPixel(x, y).getBlue();
					double green = p.getPixel(x, y).getGreen();
					double max = Math.max(red, blue);
					double a = Math.max(max, green);
					
					saturated_red = p.getPixel(x, y).getRed() * ((a + 
							(1.0 - a) * (value / 100.0))) / a;
					saturated_blue = p.getPixel(x, y).getBlue() * ((a + 
							(1.0 - a) * (value / 100.0))) / a;
					saturated_green = p.getPixel(x, y).getGreen() * ((a + 
							(1.0 - a) * (value / 100.0))) / a;
					
					Pixel pix = new ColorPixel(saturated_red, saturated_green, saturated_blue);
					//picture_view.getPicture().setPixel(x, y, pix);
					saturated_pic.setPixel(x, y, pix);
				}
		}
			
		//return saturated_pic;
		}
		
		return saturated_pic;
		}
	
	//helper method for brightness
	public Picture brightening(Picture p, int value) {
		
		Picture brightening_pic = new PictureImpl(p.getWidth(), p.getHeight());
		
		double adjusted_decline = Math.abs(value / 100.0);
		double weight=0;
		double redAvg=0;
		double blueAvg=0;
		double greenAvg=0;
		
		if(value == 0) {
			return p;
		}
		
		
		if(value < 0) {
			for(int x = 0; x < p.getWidth(); x++) {
				for(int y = 0; y < p.getHeight(); y++) {
										
					Pixel black = new ColorPixel(0.0, 0.0, 0.0);
					weight = 1.0 - adjusted_decline;
					
					//darkens the color based on selected value
					redAvg = (black.getRed()*(1.0 - weight) + p.getPixel(x, y).getRed() * weight);
					blueAvg = (black.getBlue()*(1.0 - weight) + p.getPixel(x, y).getBlue() * weight);
					greenAvg = (black.getGreen()*(1.0 - weight) + p.getPixel(x, y).getGreen() * weight);
					
//					Pixel pixelate = p.getPixel(x, y);
//					redAvg = (pixelate.getRed() * (1.0 - adjusted_decline));
//					blueAvg = (pixelate.getBlue() * (1.0 - adjusted_decline));
//					greenAvg = (pixelate.getGreen() * (1.0 - adjusted_decline));
										
					Pixel pix = new ColorPixel(redAvg, greenAvg, blueAvg);
					
					brightening_pic.setPixel(x, y, pix);
					//picture_view.getPicture().setPixel(x, y, pix);
					}
			}
			
		} else {
			for(int x = 0; x < p.getWidth(); x++) {
				for(int y = 0; y < p.getHeight(); y++) {
										
					Pixel black = new ColorPixel(1.0, 1.0, 1.0);
					weight = 1.0 - adjusted_decline;
					
					//brightens the color based on selected value
					redAvg = (black.getRed()*(1.0 - weight) + p.getPixel(x, y).getRed() * weight);
					blueAvg = (black.getBlue()*(1.0 - weight) + p.getPixel(x, y).getBlue() * weight);
					greenAvg = (black.getGreen()*(1.0 - weight) + p.getPixel(x, y).getGreen() * weight);
					
					Pixel pix = new ColorPixel(redAvg, greenAvg, blueAvg);
					
					brightening_pic.setPixel(x, y, pix);
					//picture_view.getPicture().setPixel(x, y, pix);				
					}
			}
		}
		return brightening_pic;
		
	}
	
}
