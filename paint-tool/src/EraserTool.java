package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class EraserTool implements Tool{
	private EraserToolUI ui;
	private ImageEditorModel model;
	private static int brush_size = 5;
	
	public EraserTool(ImageEditorModel model) {
		this.model = model;
		ui = new EraserToolUI();
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		model.eraseAt(e.getX(), e.getY(), brush_size);
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
		model.eraseAt(e.getX(), e.getY(), brush_size);
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return "Eraser Tool";
	}

	public JPanel getUI() {
		return ui;
	}
	
	public static void setSize(int n) {
		brush_size = n;
	}
}
