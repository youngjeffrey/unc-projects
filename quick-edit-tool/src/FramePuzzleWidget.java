package a8;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Iterator;



public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener{
	
	private PictureView picture_array[][];
	private Iterator<SubPicture> tile;
	private Picture white_tile;
	private int mouse_x;
	private int mouse_y;
	private int white_x;
	private int white_y;
	
	
	public FramePuzzleWidget(Picture p) {
		setLayout(new GridLayout(5,5));
		picture_array = new PictureView[5][5];
		
		
		int xOffset = 0;
		int yOffset = 0;
		white_x= 4;
		white_y = 4;
		
		//creates list of subpicture of tiles in entire picture
		ArrayList<SubPicture> tiles = new ArrayList<SubPicture>(25);
		
		for(int i = 0; i < 25; i++) {
			
			SubPicture tile = new SubPictureImpl(p, xOffset, yOffset, p.getWidth()/5, p.getHeight()/5);
			
			tiles.add(tile);
			
			xOffset += p.getWidth()/5;
			
			if(xOffset + p.getWidth()/5 > p.getWidth()) {
				yOffset += p.getHeight()/5;
				xOffset = 0;
				}
		}
		
		//iterates through tiles and adds observer
		Iterator<SubPicture> picture_tiles = tiles.iterator();
		
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				picture_array[x][y] = new PictureView(picture_tiles.next().createObservable());
				picture_array[x][y].addMouseListener(this);
				picture_array[x][y].addKeyListener(this);
			}
		}
		
		//creates white tile
		white_tile = new PictureImpl(p.getWidth()/5, p.getHeight()/5);
		for(int x = 0; x < white_tile.getWidth(); x++) {
			for(int y = 0; y < white_tile.getHeight(); y++) {
				Pixel white = new ColorPixel(1,1,1);
				white_tile.setPixel(x, y, white);
			}
		}
		
		picture_array[4][4] = new PictureView(white_tile.createObservable());
		picture_array[4][4].addMouseListener(this);
		picture_array[4][4].addKeyListener(this);
		
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				add(picture_array[x][y]);
			}
		}
		
	}
	
	
	public void moveTile(int x, int y) {
		Picture white = picture_array[white_x][white_y].getPicture();
		picture_array[white_x][white_y].setPicture(picture_array[x][y].getPicture());
		picture_array[x][y].setPicture(white.createObservable());
	}
	
	
	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode()) { 
        case KeyEvent.VK_UP:
            // handle up 
        	
        	try {
        		moveTile(white_x - 1, white_y);
        		white_x--;
        		break;
        	} catch(RuntimeException r) {
        		break;
        	}
        	
        
        case KeyEvent.VK_DOWN:
            // handle down 
        	
        	try {
        		moveTile(white_x + 1, white_y);
        		white_x++;
        		break;
        	} catch(RuntimeException r) {
        		break;
        	}
        	
        	
        case KeyEvent.VK_LEFT:
            // handle left
            
        	try {
        		moveTile(white_x, white_y - 1);
        		white_y--;
        		break;
        	}catch(RuntimeException r) {
        		break;
        	}
            
            
            
        case KeyEvent.VK_RIGHT :
            // handle right
         
        	try {
        		moveTile(white_x, white_y + 1);
        		white_y++;
        	}catch(RuntimeException r) {
        		break;
        	}
     }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				if(e.getComponent().equals(picture_array[x][y])) {
					mouse_x = x;
					mouse_y = y;
				}
			}
		}
		
		//shifts columns left of right
		if(mouse_x == white_x) {
			while(mouse_y != white_y) {
				if(mouse_y <= white_y) {
					moveTile(white_x, white_y - 1);
					white_y--;
					} else {
						moveTile(white_x, white_y + 1);
						white_y++;
					}
				}
			
			//shifts rows up or down
			} else if(mouse_y == white_y) {
				while(mouse_x != white_x) {
					if(mouse_x <= white_x) {
						moveTile(white_x - 1, white_y);
						white_x--;
					} else {
						moveTile(white_x + 1, white_y);
						white_x++;
					}
				}
			}
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
