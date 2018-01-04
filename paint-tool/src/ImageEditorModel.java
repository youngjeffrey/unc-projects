package a9;

import java.util.List;
import java.util.ArrayList;

public class ImageEditorModel {

	private Picture original;
	private static ObservablePicture current;
	private static List<Picture> steps = new ArrayList<Picture>();;
	private static int pos;
	
	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();		
		steps.add(original);
		pos = 1;
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendObservable();;
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					
					double weight = PaintBrushToolUI.getOpacityLevel();
					
					//System.out.println(current.getPixel(xpos, ypos).getRed() + " " + current.getPixel(xpos, ypos).getGreen()
							//+ " " + current.getPixel(xpos, ypos).getBlue());
					
					double op_red = brushColor.getRed() * (1.0 - weight) + current.getPixel(xpos, ypos).getRed() * (weight);
					double op_green = brushColor.getGreen() * (1.0 - weight) + current.getPixel(xpos, ypos).getGreen() * (weight);
					double op_blue = brushColor.getBlue() * (1.0 - weight) + current.getPixel(xpos, ypos).getBlue() * (weight);
					
					//System.out.println(op_red + " " + op_green
							//+ " " + op_blue);
					
					//System.out.println(" ");
					
					Pixel pix = new ColorPixel(op_red, op_green, op_blue);
					current.setPixel(xpos, ypos, pix);

				}
			}
		}
		
		current.resumeObservable();
		steps.add(current.copy().createObservable());
		pos++;
		
	}
	
	public void eraseAt(int x, int y, int brush_size) {
		current.suspendObservable();
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				Pixel pix = new ColorPixel(1.0,1.0,1.0);
				current.setPixel(xpos, ypos, pix);
			}
			}
		
		current.resumeObservable();
		steps.add(current.copy().createObservable());
		pos++;
	}

	
	public void blurAt(int y, int x, int blur_value, int brush_size){
		current.suspendObservable();;
		
		for (int i=x-brush_size+1; i <=x+brush_size-1; i++) {
			for (int j=y-brush_size+1; j <=y+brush_size-1; j++) {
				
				//Calculates average colors over an area
				double totalRed = 0;
				double totalGreen = 0;
				double totalBlue = 0;
				double total = 0;
				for (int b = i - blur_value; b <= i + blur_value; b++) {
					for (int a = j - blur_value; a <= j + blur_value; a++) {
						try{
							totalRed += current.getPixel(a,b).getRed();
							totalGreen += current.getPixel(a,b).getGreen();
							totalBlue += current.getPixel(a,b).getBlue();
							total++;
						}
						catch(RuntimeException g){
							continue;
						}
					}
				}
				
				
				
				Pixel pix = new ColorPixel(totalRed/total, totalGreen/total, totalBlue/total);
				current.setPixel(j,i,pix);
			}
		}
		
		current.resumeObservable();
		steps.add(current.copy().createObservable());
		pos++;
		
	}
	
	public void undo() {
		current.suspendObservable();
		
		for(int i = 0; i < current.getWidth(); i++) {
			for(int j = 0; j < current.getHeight(); j++) {
				if(pos - 50 > 0) {
					current.setPixel(i, j, steps.get(pos - 50).getPixel(i, j));
				} else {
					current.setPixel(i, j, steps.get(0).getPixel(i, j));
				}
			}
		}
		
		pos-=50;
		
		if(pos<=0) {
			steps.clear();
			steps.add(current.copy());
			pos=1;
		}
		
		current.resumeObservable();
		
	}
	
	public void eraseAll() {
		current.suspendObservable();
		
		for(int i = 0; i < current.getWidth(); i++) {
			for(int j = 0; j < current.getHeight(); j++) {
				current.setPixel(i, j, original.getPixel(i, j));
			}
		}
		
		pos = 1;
		steps.clear();
		steps.add(current.copy());
		
		current.resumeObservable();
	}
	
}
