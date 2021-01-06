package proxy.headfirst.virtual;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageProxy implements Icon {
  ImageIcon imageIcon;
  URL imageURL;
  Thread retrievalThread;
  boolean retrieving = false;

  State state;
  State unloadState;
  State loadState;

  public ImageProxy(URL url) {
    imageURL = url;
    this.unloadState = new UnloadState(this);
    this.loadState = new LoadState(this);

    this.state = unloadState;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    state.paintIcon(c, g, x, y);
  }

  @Override
  public int getIconWidth() {
    return state.getIconWidth();
  }

  @Override
  public int getIconHeight() {
    return state.getIconHeight();
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return this.state;
  }

  public State getLoadState() {
    return loadState;
  }

  public ImageIcon getImageIcon() {
    return this.imageIcon;
  }

  public void setRetrieving(boolean retrieving) {
    this.retrieving = retrieving;
  }

  public boolean getRetrieving() {
    return this.retrieving;
  }

  public void setRetrievalThread(Thread retrievalThread) {
    this.retrievalThread = retrievalThread;
  }

  public Thread getRetrievalThread() {
    return retrievalThread;
  }

  public URL getImageURL() {
    return imageURL;
  }

  public void setImageIcon(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
  }
}
