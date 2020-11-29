package hotelreserv.client;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;


import hotelreserv.admin.HotelVO;

public class BusanPage extends Page{
   AreaPageDesign areaPageDesign;
   
   public BusanPage(ClientMain clientMain) {
      super(clientMain);
      areaPageDesign = new AreaPageDesign(clientMain, "부산");
      add(areaPageDesign);
      
   }
}