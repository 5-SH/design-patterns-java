package proxy.headfirst.virtual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

public class ImageProxyTestDrive {
  ImageComponent imageComponent;
  JFrame frame = new JFrame("CD Cover Viewer");
  JMenuBar menuBar;
  JMenu menu;
  Hashtable cds = new Hashtable();

  public static void main(String[] args) throws Exception {
    ImageProxyTestDrive testDrive = new ImageProxyTestDrive();
  }

  public ImageProxyTestDrive() throws Exception {
    cds.put("Alphabet A", "http://localhost:3000/A.jpg");
    cds.put("Alphabet B", "http://localhost:3000/B.jpg");
    cds.put("Alphabet C", "http://localhost:3000/C.jpg");
    cds.put("Alphabet D", "http://localhost:3000/D.jpg");
    cds.put("Alphabet E", "http://localhost:3000/E.jpg");

    URL initialURL = new URL((String) cds.get("Alphabet A"));
    menuBar = new JMenuBar();
    menu = new JMenu("Favorite alphabets");
    menuBar.add(menu);
    frame.setJMenuBar(menuBar);

    for (Enumeration e = cds.keys(); e.hasMoreElements();) {
      String name = (String) e.nextElement();
      JMenuItem menuItem = new JMenuItem(name);
      menu.add(menuItem);
      menuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          imageComponent.setIcon(new ImageProxy(getAlphabetUrl(event.getActionCommand())));
          frame.repaint();
        }
      });
    }

    Icon icon = new ImageProxy(initialURL);
    imageComponent = new ImageComponent(icon);
    frame.getContentPane().add(imageComponent);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setVisible(true);
  }

  URL getAlphabetUrl(String name) {
    try {
      return new URL((String) cds.get(name));
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
