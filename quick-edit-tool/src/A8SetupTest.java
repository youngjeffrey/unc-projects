package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class A8SetupTest {
	public static void main(String[] args) throws IOException {
		Picture p = A8Helper.readFromURL("http://media.breitbart.com/media/2017/11/AP_17312290678314-640x480.jpg");
		SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p, "Donald Trump and Melania in Beijing");
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(simple_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}