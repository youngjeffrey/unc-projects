package a9;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageEditor {
	public static String url = "";
	
	public static void setUrl(String address) {
		url = address;
	}
	
	public static void main(String[] args) throws IOException {
		ImageEditorModel model;
		
		Picture f = PictureImpl.readFromURL("https://s-media-cache-ak0.pinimg.com/originals/27/0a/73/270a73ed5126a5c5d501705ae6bb739e.png");

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Image Editor");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(url == "") {
			model = new ImageEditorModel(f);
		}
		else {
			model = new ImageEditorModel(PictureImpl.readFromURL(url));
		}
		
		ImageEditorView view = new ImageEditorView(main_frame, model);
 		ImageEditorController controller = new ImageEditorController(view, model);
		

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}