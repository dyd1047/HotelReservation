package hotelreserv.client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import common.image.ImageUtil;



public class RegistMember extends Page{
   JPanel p_content;
   JButton bt_logo;
   JTextField t_mid;
   JPasswordField t_pass;
   JTextField t_name;
   JTextField t_phone;
   JTextField t_email;
   JButton bt_regist;
   JButton bt_cancel;
   
   JLabel la_mid;
   JLabel la_pass;
   JLabel la_name;
   JLabel la_phone;
   JLabel la_email;
   
   public RegistMember(ClientMain clientMain) {
      super(clientMain);
      
      //생성
      p_content = new JPanel();
      
      bt_logo = new JButton(ImageUtil.getIcon(this.getClass(), "res/logo_white.png", 120, 50));
      t_mid = new JTextField();
      t_pass = new JPasswordField();
      t_name = new JTextField();
      t_phone = new JTextField();
      t_email = new JTextField();
      bt_regist = new JButton("회원가입");
      bt_cancel = new JButton("취소");
      
      la_mid = new JLabel("아이디");
      la_pass = new JLabel("비밀번호");
      la_name = new JLabel("이름");
      la_phone = new JLabel("연락처");
      la_email = new JLabel("이메일");
      
      //스타일
      this.setBackground(Color.WHITE);
      p_content.setPreferredSize(new Dimension(450,700));
      p_content.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "REGIST", 0, 0,
            new Font("맑은 고딕", Font.BOLD, 20), Color.BLACK));
      p_content.setBackground(Color.WHITE);
      
      //회원정보 입력 관련
      Dimension d = new Dimension(400,50);
      t_mid.setPreferredSize(d);
      t_mid.setBorder(new LineBorder(Color.BLACK, 2));
      t_mid.setFont(new Font("Verdana", Font.BOLD, 16));
      
      t_pass.setPreferredSize(d);
      t_pass.setBorder(new LineBorder(Color.BLACK, 2));
      t_pass.setFont(new Font("Verdana", Font.BOLD, 16));
      
      t_name.setPreferredSize(d);
      t_name.setBorder(new LineBorder(Color.BLACK, 2));
      t_name.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      t_phone.setPreferredSize(d);
      t_phone.setBorder(new LineBorder(Color.BLACK, 2));
      t_phone.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      t_email.setPreferredSize(d);
      t_email.setBorder(new LineBorder(Color.BLACK, 2));
      t_email.setFont(new Font("Verdana", Font.BOLD, 16));
      
      la_mid.setPreferredSize(new Dimension(400, 50));
      la_mid.setHorizontalAlignment(JLabel.LEFT);
      la_mid.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      la_pass.setPreferredSize(new Dimension(400, 50));
      la_pass.setHorizontalAlignment(JLabel.LEFT);
      la_pass.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      la_name.setPreferredSize(new Dimension(400, 50));
      la_name.setHorizontalAlignment(JLabel.LEFT);
      la_name.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      la_phone.setPreferredSize(new Dimension(400, 50));
      la_phone.setHorizontalAlignment(JLabel.LEFT);
      la_phone.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      la_email.setPreferredSize(new Dimension(400, 50));
      la_email.setHorizontalAlignment(JLabel.LEFT);
      la_email.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      //Button
      bt_logo.setPreferredSize(new Dimension(400, 50));
      bt_logo.setBorderPainted(false);
      bt_logo.setContentAreaFilled(false);
      bt_logo.setFocusPainted(false);
      bt_logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      bt_regist.setPreferredSize(new Dimension(200, 50));
      bt_regist.setBackground(Color.BLACK);
      bt_regist.setForeground(Color.WHITE);
      bt_regist.setFocusPainted(false);
      bt_regist.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      bt_regist.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      bt_cancel.setPreferredSize(new Dimension(200, 50));
      bt_cancel.setBackground(Color.BLACK);
      bt_cancel.setForeground(Color.WHITE);
      bt_cancel.setFocusPainted(false);
      bt_cancel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      bt_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
      p_content.add(bt_logo);
      p_content.add(la_mid);
      p_content.add(t_mid);
      p_content.add(la_pass);
      p_content.add(t_pass);
      p_content.add(la_name);
      p_content.add(t_name);
      p_content.add(la_phone);
      p_content.add(t_phone);
      p_content.add(la_email);
      p_content.add(t_email);
      p_content.add(bt_regist);
      p_content.add(bt_cancel);
      
      add(p_content);
      
      bt_logo.addActionListener((e) -> {
         clientMain.showPage(clientMain.START);
      });
      
      bt_regist.addActionListener((e)->{

         if(checkId(t_mid.getText())) {
            JOptionPane.showMessageDialog(RegistMember.this, "중복된 아이디입니다.\n다른 아이디 사용바람");
         }else {
            //메서드 호출하기 전에 ,VO값을 채워야한다
            HotelMember vo = new HotelMember();
            vo.setMid(t_mid.getText());
            vo.setEncryptionPass(new String(t_pass.getPassword()));
            vo.setName(t_name.getText());
            vo.setPhone(t_phone.getText());
            vo.setEmail(t_email.getText());
            int result = regist(vo);
            if(result == 0) {
               JOptionPane.showMessageDialog(RegistMember.this, "등록실패");
            }else {
               JOptionPane.showMessageDialog(RegistMember.this, "회원가입 성공");
               clientMain.showPage(clientMain.LOGIN);
               
            }
            
         }
         
         
      });
      
      bt_cancel.addActionListener((e)->{
         clientMain.showPage(clientMain.START);
      });
   
   }
   
   
   //회원존재 여부 체크
      public boolean checkId(String mid) {
         PreparedStatement pstmt=null;
         ResultSet rs = null;
         boolean flag = false;
         
         //회원테이블에 중복된 아이디가 있는지 여부에 대한 쿼리
         //반환값이 true가 나오면, 회원가입 진행시키면 안됨
         String sql = "select *from hotel_member where mid=?";
         
         try {
            pstmt = clientMain.getCon().prepareStatement(sql);
            pstmt.setString(1,mid);
            rs=pstmt.executeQuery();
            
            flag=rs.next(); //레코드가 존재하면 true, 아니면 false가 대입됨
            
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            clientMain.getDbManager().close(pstmt,rs);
         }
         return flag;
         
      }
      
      public int regist(HotelMember hotelMember) {
         PreparedStatement pstmt=null;
         int result =0;
         
         String sql = "insert into hotel_member(member_id,mid,pass,name,phone,email)";
         sql+=" values(seq_hotel_member.nextval,?,?,?,?,?)";

         try {
            pstmt = clientMain.getCon().prepareStatement(sql);
            
            //바인드변수 대입
            pstmt.setString(1, hotelMember.getMid());
            pstmt.setString(2, hotelMember.getEncryptionPass());
            pstmt.setString(3, hotelMember.getName());
            pstmt.setString(4, hotelMember.getPhone());
            pstmt.setString(5, hotelMember.getEmail());
            
            result = pstmt.executeUpdate(); //쿼리 수행
            
            
         } catch (SQLException e) {
            e.printStackTrace();
            
         }finally {
            clientMain.getDbManager().close(pstmt);
         }
         
            return result;   
               
      }

}