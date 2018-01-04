package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurToolUI extends JPanel implements ChangeListener {

	private JSlider blur_intensity;
	private JSlider brush_size;
	
	public BlurToolUI() {
		setLayout(new BorderLayout());

		//Panel for the intensity of blurring
		JPanel blur_panel = new JPanel();
		JLabel blur_label = new JLabel("Blur: ");
		blur_panel.setLayout(new BorderLayout());
		blur_panel.add(blur_label, BorderLayout.WEST);
		blur_intensity = new JSlider(0,5,0);
		blur_intensity.addChangeListener(this);
		blur_intensity.setMajorTickSpacing(1);
		blur_intensity.setPaintTicks(true);
		blur_intensity.setPaintLabels(true);
		blur_panel.add(blur_intensity, BorderLayout.CENTER);
		
		//Panel for the size of the blur brush
		JPanel brush_size_panel = new JPanel();
		JLabel brush_label = new JLabel("Brush Size: ");
		brush_size_panel.setLayout(new BorderLayout());
		brush_size_panel.add(brush_label, BorderLayout.WEST);
		brush_size = new JSlider(0,30,5);
		brush_size.addChangeListener(this);
		brush_size.setMajorTickSpacing(5);
		brush_size.setPaintTicks(true);
		brush_size.setPaintLabels(true);
		brush_size_panel.add(brush_size, BorderLayout.CENTER);
		
		add(blur_panel, BorderLayout.NORTH);
		add(brush_size_panel, BorderLayout.CENTER);
		
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		BlurTool.setBrushSize(brush_size.getValue());
	}

	//Allows for the amount blur value to be accessed
	public int getBlurValue() {
		return blur_intensity.getValue();
	}
	
}

