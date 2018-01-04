package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurTool implements Tool {

	private BlurToolUI ui;
	private ImageEditorModel model;
	private static int brush_size;
	
	public BlurTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurToolUI();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBlurValue(), brush_size);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBlurValue(), brush_size);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Blur Tool";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}
	
	//Allows for the brush size to be set for blur tool
	public static void setBrushSize(int x) {
		brush_size = x;
	}
}
