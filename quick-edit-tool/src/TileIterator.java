package a8;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileIterator implements Iterator<SubPicture>{

	Picture _p;
	int _tile_width;
	int _tile_height;
	int x;
	int y;
	int total_x;
	int total_y;
	int a = 0;
	int b = 0;
	
	public TileIterator(Picture p, int tile_width, int tile_height) {
		_p = p;
		_tile_width = tile_width;
		_tile_height = tile_height;
		x = 0;
		y = 0;
		total_x = _p.getWidth() / _tile_width;
		total_y = _p.getHeight() / _tile_height;
	}
	
	public boolean hasNext() {
		
		return  y <= _p.getHeight() - _tile_height;
	}

	public SubPicture next() {
		if(!hasNext()) {
			throw new NoSuchElementException("Last element has already been traversed");
		}
		SubPicture sub = new SubPictureImpl(_p, x , y, _tile_width, _tile_height);
			
			x+=_tile_width;
			if(x + _tile_width > _p.getWidth()) {
				y+=_tile_height;
				x = 0;
			}
			
		
		/* if(a < total_x) {
				x+=_tile_width;
				a++;
		} else {
			a = 0;
			if(b < total_y) {
				y+=_tile_height;
				x = 0;
				b++;
			}
		} */
		
		return sub;
	}

}
