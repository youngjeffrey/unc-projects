package a9;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getIntensity();
	int toRGB();
	Pixel blend(Pixel pixel, double opacityLevel);
}
