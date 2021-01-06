package proxy.headfirst.virtual;

import java.awt.*;

public interface State {
  public int getIconHeight();
  public int getIconWidth();
  public void paintIcon(Component c, Graphics g, int x, int y);
}
