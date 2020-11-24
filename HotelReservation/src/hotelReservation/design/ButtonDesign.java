package hotelReservation.design;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class ButtonDesign extends JButton{
   public ButtonDesign(String str, Color bgColor, Color fColor, int width, int height, boolean bdPaint, boolean contentFiled, boolean focus) {
      super(str);
      this.setBackground(bgColor);
      this.setForeground(fColor);
      this.setPreferredSize(new Dimension(width, height));
      this.setBorderPainted(bdPaint);
      this.setContentAreaFilled(contentFiled);
      this.setFocusPainted(focus);
   }
}