package proxy.headfirst.virtual;

import java.awt.*;

public class LoadState implements State {
  ImageProxy imageProxy;

  public LoadState(ImageProxy imageProxy) {
    this.imageProxy = imageProxy;
  }

  @Override
  public int getIconHeight() {
    return imageProxy.getImageIcon().getIconHeight();
  }

  @Override
  public int getIconWidth() {
    return imageProxy.getImageIcon().getIconWidth();
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    imageProxy.getImageIcon().paintIcon(c, g, x, y);
  }
}
