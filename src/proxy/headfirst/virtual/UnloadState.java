package proxy.headfirst.virtual;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UnloadState implements State {
  ImageProxy imageProxy;

  public UnloadState(ImageProxy imageProxy) {
    this.imageProxy = imageProxy;
  }

  @Override
  public int getIconHeight() {
    return 600;
  }

  @Override
  public int getIconWidth() {
    return 800;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.drawString("Loading Alphabet cover , please wait...", x + 300, y + 190);
    boolean retrieving = imageProxy.getRetrieving();
    if (!retrieving) {
      imageProxy.setRetrieving(true);

      imageProxy.setRetrievalThread(new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            URL imageURL = imageProxy.getImageURL();
            imageProxy.setImageIcon(new ImageIcon(imageURL, "Alphabet"));
            c.repaint();
            imageProxy.setState(imageProxy.getLoadState());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }));

      imageProxy.getRetrievalThread().start();
    }
  }
}
