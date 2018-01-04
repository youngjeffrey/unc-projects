package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EraserToolUI extends JPanel implements ChangeListener{

	private JSlider eraser_slider;
	private PictureView color_preview;

	
	public EraserToolUI() {
		setLayout(new BorderLayout());
		
		JPanel eraser_panel = new JPanel();
		JLabel eraser_label = new JLabel("Erase: ");
		eraser_panel.setLayout(new BorderLayout());
		eraser_panel.add(eraser_label, BorderLayout.WEST);
		
		eraser_slider = new JSlider(0,15,0);
		eraser_slider.addChangeListener(this);
		//eraser_slider.setMajorTickSpacing(1);
		//eraser_slider.setPaintTicks(true);
		//eraser_slider.setPaintLabels(true);
		eraser_panel.add(eraser_slider, BorderLayout.CENTER);
		
		add(eraser_panel, BorderLayout.NORTH);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		EraserTool.setSize(eraser_slider.getValue());
		Pixel p = new ColorPixel(1.0, 1.0, 1.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.suspendObservable();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeObservable();
	}

}
