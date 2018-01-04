package a9;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageEditorView extends JPanel implements ActionListener{

	private JFrame main_frame;
	private PictureView frame_view;
	private ImageEditorModel model;
	private ToolChooserWidget chooser_widget;
	private JTextField url_insert;
	private JButton go;
	private JButton undo;
	private JButton undoAll;
	private JPanel tool_ui_panel;
	private JPanel tool_ui;
	private JPanel open_undo_panel;
	private JPanel imagePanel;
	private JLabel image;
	
	public ImageEditorView(JFrame main_frame, ImageEditorModel model) {
		this.main_frame = main_frame;
		this.model = model;
		
		setLayout(new BorderLayout());
		
		frame_view = new PictureView(model.getCurrent());
		
		add(frame_view, BorderLayout.CENTER);
		
		tool_ui_panel = new JPanel();
		tool_ui_panel.setLayout(new BorderLayout());
		
		open_undo_panel = new JPanel();
		open_undo_panel.setLayout(new BorderLayout());
		
		 
		url_insert = new HintTextField("  Paste the image address and press GO  ");
		
		chooser_widget = new ToolChooserWidget();
		tool_ui_panel.add(chooser_widget, BorderLayout.NORTH);
		add(tool_ui_panel, BorderLayout.EAST);
		
		go = new JButton("GO");
		//go.setPreferredSize(new Dimension(80,80));
		go.setSize(80, 40);
		go.setVisible(true);
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
		        url_insert(url_insert.getText());
		    }
			
		});
		
		undo = new JButton("Undo");
		undo.setSize(40,40);
		undo.setVisible(true);
		undo.addActionListener(this);
		
		undoAll = new JButton("Undo All");
		undoAll.setSize(40,40);
		undoAll.setVisible(true);
		undoAll.addActionListener(this);
		
		
		
		//add(url_insert, BorderLayout.CENTER);
		open_undo_panel.add(go, BorderLayout.EAST);
		open_undo_panel.add(url_insert, BorderLayout.NORTH);
		open_undo_panel.add(undo, BorderLayout.WEST);
		open_undo_panel.add(undoAll, BorderLayout.SOUTH);

		
		tool_ui_panel.add(open_undo_panel, BorderLayout.EAST);
		add(tool_ui_panel, BorderLayout.SOUTH);
		
		tool_ui = null;
	}
	
	public void url_insert(String address) {
		ImageEditor web_image = new ImageEditor();
		web_image.setUrl(address);
		try {
			web_image.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void addToolChoiceListener(ToolChoiceListener l) {
		chooser_widget.addToolChoiceListener(l);
	}
	
	public String getCurrentToolName() {
		return chooser_widget.getCurrentToolName();
	}

	public void installToolUI(JPanel ui) {
		if (tool_ui != null) {
			tool_ui_panel.remove(tool_ui);
		}
		tool_ui = ui;
		tool_ui_panel.add(tool_ui, BorderLayout.CENTER);
		validate();
		main_frame.pack();
	}
	
	@Override
	public void addMouseMotionListener(MouseMotionListener l) {
		frame_view.addMouseMotionListener(l);
	}
	
	@Override
	public void removeMouseMotionListener(MouseMotionListener l) {
		frame_view.removeMouseMotionListener(l);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		frame_view.addMouseListener(l);
	}
	
	public void removeMouseListener(MouseListener l) {
		frame_view.removeMouseListener(l);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(undo)) {
			model.undo();
		} else if(e.getSource().equals(undoAll)) {
			model.eraseAll();
		}
	}
	

}
