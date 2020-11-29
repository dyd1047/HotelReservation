package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class RegionSelection extends Page {
   JButton bt_seoul;
   JButton bt_busan;
   JPanel p_content;

   public RegionSelection(ClientMain clientMain) {
      super(clientMain);
      
      //생성
      p_content = new JPanel();
      
      //스타일
      this.setBackground(Color.WHITE);
      //BUTTON
      bt_seoul = new JButton(ImageUtil.getIcon(this.getClass(), "res/btseoul.png", 600, 800));
      bt_busan = new JButton(ImageUtil.getIcon(this.getClass(), "res/busanbt.png", 600, 800));
      
      p_content.setPreferredSize(new Dimension(1200, 800));
      p_content.setLayout(new GridLayout(1, 2));
      p_content.setBackground(Color.WHITE);
      
      //조립
      p_content.add(bt_seoul);
      p_content.add(bt_busan);
      
      setLayout(new FlowLayout());
      this.add(p_content);

      clientMain.navi[2].addActionListener((e) -> {
         clientMain.showPage(clientMain.LOGIN);
      });
      
      bt_seoul.addActionListener((e)->{
    	  clientMain.showPage(clientMain.SEOUL);
      });
      
      bt_busan.addActionListener((e)->{
    	  clientMain.showPage(clientMain.BUSAN);
      });
      
      

   }

}