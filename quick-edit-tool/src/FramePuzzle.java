package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePuzzle {
	public static void main(String[] args) throws IOException {
		Picture p = A8Helper.readFromURL("https://alaskabearviewing.com/wp-content/uploads/2015/03/Sow-Grizzly-with-3-Cubs-in-Katmai-National-Park.jpg");
		FramePuzzleWidget framePuzzle = new FramePuzzleWidget(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Frame Puzzle");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(framePuzzle, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
}
}
