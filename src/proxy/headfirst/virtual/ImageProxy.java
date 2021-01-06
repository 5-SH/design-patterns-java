package proxy.headfirst.virtual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageProxy implements Icon {
  ImageIcon imageIcon;
  URL imageURL;
  Thread retrievalThread;
  boolean retrieving = false;

  public ImageProxy(URL url) { imageURL = url; }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    if (imageIcon != null) {
      imageIcon.paintIcon(c, g, x, y);
    } else {
      g.drawString("Loading CD cover , please wait...", x + 300, y + 190);
      if (!retrieving) {
        retrieving = true;

        retrievalThread = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              imageIcon = new ImageIcon(imageURL, "Alphabet");
              c.repaint();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });

        retrievalThread.start();
      }
    }
  }

  @Override
  public int getIconWidth() {
    if (imageIcon != null) {
      return imageIcon.getIconWidth();
    }

    return 800;
  }

  @Override
  public int getIconHeight() {
    if (imageIcon != null) {
      return imageIcon.getIconHeight();
    }
    return 600;
  }
}
