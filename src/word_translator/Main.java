/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package word_translator;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
//voice command
import java.io.*;
import com.sun.speech.freetts.*;
//chart
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Cris Mateo Uriarte
 */
public class Main extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Main
     */
    String imgpath = null;
    String imgpath1 = null;
    
           String imgpath2 = null;
    
    public Main() {
        initComponents();
        conn = (Connection) MySqlConnect.ConnectDB();
        start();
        CurrentDate();
        addtable_ref();
        update_ref();
        account_ref();
        inaccount_ref();
        accountlog_ref();
        userlog_ref();
        freq_ref();
    }
    
   

// public void bargraph(){
//    DefaultCategoryDataset dcd = new  DefaultCategoryDataset();
//  dcd.setValue(2000,"Amount","January");
//  dcd.setValue(1800,"Amount","February");
//  dcd.setValue(800,"Amount","March");
//   dcd.setValue(1000,"Amount","April");
//  dcd.setValue(1500,"Amount","May");
//  dcd.setValue(950,"Amount","June");
//   dcd.setValue(2300,"Amount","July");
//  dcd.setValue(500,"Amount","August");
//  dcd.setValue(900,"Amount","September");
//    JFreeChart barchart = ChartFactory.createBarChart3D("Contribution","Monthly","Amount",dcd,PlotOrientation.VERTICAL,true,true,true);
//     CategoryPlot plot = barchart.getCategoryPlot();
//    plot.setRangeGridlinePaint(Color.BLUE);
//    ChartPanel chartPanel =new ChartPanel(barchart);
//    pnl_dash.removeAll();
//    pnl_dash.add(chartPanel);
//    pnl_dash.updateUI();
//    }
//public void piechart(){
//        DefaultPieDataset pieDataset = new DefaultPieDataset();
//        pieDataset.setValue("One", new Integer(10));
//        pieDataset.setValue("Two", new Integer(20));
//        pieDataset.setValue("Three", new Integer(30));
//        pieDataset.setValue("Four", new Integer(40));
//        JFreeChart chart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, true);
//        //PiePlot P=(PiePlot)chart.getPlot();
//        CategoryPlot plot = chart.getCategoryPlot();
//        //P.setForegroundAlpha(TOP_ALIGNMENT);
////        ChartFrame frame = new ChartFrame("Pie Chart", chart);
////        frame.setVisible(true);
////        frame.setSize(450,500);
//plot.setRangeGridlinePaint(Color.BLUE);
// ChartPanel chartPanel =new ChartPanel(chart);
//    pnl_dash.removeAll();
//    pnl_dash.add(chartPanel);
//    pnl_dash.updateUI();
//        
//    }
   
//   public static void main( String[ ] args ) {
//      BarChart_AWT chart = new BarChart_AWT("Car Usage Statistics", 
//         "Which car do you like?");
//      chart.pack( );        
//      RefineryUtilities.centerFrameOnScreen( chart );        
//      chart.setVisible( true ); 
//   }
    
    public static final String VOICENAME="kevin16";
    public void row_count(){
    int row = trans_table.getRowCount();
        rc.setText(String.valueOf(row));
        
        if(rc.getText().equals("0")){
        translated.setText("");
        trans_mean.setText("");
        tran_noun.setText("");
        tran_verb.setText("");
        tran_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_DEFAULT)));
        }
    }

    public void start(){
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0,0));
        Main.setBackground(new Color(0,0,0,0));
        jLabel5.setBackground(new Color(0,0,0,0));
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        user.setVisible(false);
        pass.setVisible(false);
        jButton2.setVisible(false);
        jCheckBox1.setVisible(false);
        trans_table.setTableHeader(null);
        jScrollPane12.getColumnHeader().setVisible(false);
        hint.setVisible(false);
        //jScrollPane11.setVisible(false);
    }
    public void startlogin(){
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        user.setVisible(true);
        pass.setVisible(true);
        jButton2.setVisible(true);
        jCheckBox1.setVisible(true);
        jButton1.setVisible(false);
        jButton3.setVisible(false);
    }
    public void loginshow(){
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        user.setVisible(false);
        pass.setVisible(false);
        jButton2.setVisible(false);
        jCheckBox1.setVisible(false);
        jButton1.setVisible(false);
        jButton3.setVisible(false);
        Main.setVisible(false);
    }
    public void login() {
        try {
        String sql = "SELECT * FROM access where Username =? and Password =?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, user.getText());
            pst.setString(2, pass.getText());
            
            rs = pst.executeQuery();
            if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Admin Logged In!");
            loginshow();
            system.setVisible(true);
            //system.setBackground(new Color(0,0,0,0));
            
            pst = (PreparedStatement) conn.prepareStatement("SELECT Full_Name, Level  FROM access where Username= '" + user.getText() + "' and Password= '" + pass.getText() + "' ");
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("Full_Name");
                String add2 = rs.getString("Level");
                txt_name.setText(add1);
                txt_level.setText(add2); 
                jLabel8.setText("0");
                try {
                String sql1 = "Insert into accountlog (Date, Time, Name, Username, Level, Remarks) values (?,?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, user.getText());//transaction
                pst.setString(5, txt_level.getText());//ponum
                pst.setString(6, "Log-in");//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            }
        }
            else {
            JOptionPane.showMessageDialog(null, "incorrect username or password");
            double a;
            //a = Double.parseDouble(loginatt.getText());
            a = Double.parseDouble(jLabel8.getText());
            a = a+1;
            jLabel8.setText(Double.toString(a));
            if(a==3){
                JOptionPane.showMessageDialog(null, "Warning 3 attempts occured!");
            System.exit(0);
            }
            }
    }
        catch (SQLException x) {
        JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
        }
        
        accountlog_ref();
    }
    public void CurrentDate() {//date and time to toolbar running

        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm aa");
                txt_time.setText(s.format(d));

                SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy");
                txt_date.setText(st.format(d));
            }
        })
                .start();
    }   
    public void addtable_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT ID, Word, Meaning, Filipino, Pronounce, Spanish, Image FROM word ORDER BY Word ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            word_table.setModel(DbUtils.resultSetToTableModel(rs));
            word_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void update_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT * FROM word ORDER BY Word ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            word_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void account_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT * FROM access ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            acc_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void inaccount_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT * FROM inactive ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            inacc_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void accountlog_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT * FROM accountlog ORDER BY Date DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            acclog_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void userlog_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT * FROM userlog ORDER BY Date DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            userlog_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void freq_ref() {//refresh and call for db table account_tbl
        try {
            String sql = "SELECT Word, Count, Date FROM frequent ORDER BY Count DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            freq_tbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void ne_clr(){
    ne_word.setText("");
    ne_pro.setText("");
    ne_fil.setText("");
    ne_span.setText("");
    ne_mean.setText("");
    ne_noun.setText("");
    ne_verb.setText("");
    ne_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(ne_image.getWidth(), ne_image.getHeight(), Image.SCALE_DEFAULT)));
    }
    public void up_clr(){
    up_word.setText("");
    up_fil.setText("");
    up_pro.setText("");
    up_span.setText("");
    up_mean.setText("");
    up_noun.setText("");
    up_noun.setText("");
    up_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_DEFAULT)));
    up_image.setText("");
    }
    public void acc_clr(){
    acc_id.setText("");
    acc_fn.setText("");
    acc_bd.setDate(null);
    acc_user.setText("");
    acc_pass.setText("");
    acc_lvl.setSelectedItem("--Select Level--");
    }
    public void inacc_clr(){
    inacc_id.setText("");
    inacc_fn.setText("");
    inacc_bd.setDate(null);
    inacc_user.setText("");
    inacc_pass.setText("");
    inacc_lvl.setSelectedItem("--Select Level--");
    inacc_res.setText("");
    }
    public void translation(){
        if(lang1.getSelectedItem().equals("English") && lang2.getSelectedItem().equals("English")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else{
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Word, Noun, Verb, Image  FROM word where Word = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name3 =rs.getString("Word");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb");
            
            trans_mean.setText(name);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Word"
                    + " FROM word WHERE "
                    + "Word like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("English") && lang2.getSelectedItem().equals("Filipino")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Filipino, Pronounce, Noun, Verb, Image  FROM word where Word = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name3 =rs.getString("Filipino");
            String name4 =rs.getString("Pronounce");
            String name5 =rs.getString("Noun");
            String name6 =rs.getString("Verb");
            
            trans_mean.setText(name);
            translated.setText(name3);
            tran_pro.setText(name4);
            tran_verb.setText(name5);
            tran_pro.setText(name6);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Word"
                    + " FROM word WHERE "
                    + "Word like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("English") && lang2.getSelectedItem().equals("Spanish")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Spanish, Noun, Verb, Image  FROM word where Word = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name3 =rs.getString("Spanish");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb"); 
            
            trans_mean.setText(name);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Word"
                    + " FROM word WHERE "
                    + "Word like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("Filipino") && lang2.getSelectedItem().equals("English")){
            if(trans_search.getText().equals(false)){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Word, Pronounce, Noun, Verb, Image  FROM word where Filipino = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name2 =rs.getString("Pronounce");
            String name3 =rs.getString("Word");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb"); 
            
            trans_mean.setText(name);
            tran_pro.setText(name2);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Filipino"
                    + " FROM word WHERE "
                    + "Filipino like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("Filipino") && lang2.getSelectedItem().equals("Filipino")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Filipino, Pronounce, Noun, Verb, Image  FROM word where Filipino = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name2 =rs.getString("Pronounce");
            String name3 =rs.getString("Filipino");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb");
            
            trans_mean.setText(name);
            tran_pro.setText(name2);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Filipino"
                    + " FROM word WHERE "
                    + "Filipino like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("Filipino") && lang2.getSelectedItem().equals("Spanish")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Pronounce, Spanish, Noun, Verb, Image  FROM word where Filipino = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name2 =rs.getString("pronounce");
            String name3 =rs.getString("Spanish");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb");
            
            trans_mean.setText(name);
            tran_pro.setText(name2);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Filipino"
                    + " FROM word WHERE "
                    + "Filipino like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("Spanish") && lang2.getSelectedItem().equals("English")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Word, Noun, Verb, Image  FROM word where Spanish = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name3 =rs.getString("Word");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb");
            
            trans_mean.setText(name);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Spanish"
                    + " FROM word WHERE "
                    + "Spanish like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("Spanish") && lang2.getSelectedItem().equals("Filipino")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Filipino, Pronounce, Noun, Verb, Image  FROM word where Spanish = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name2 =rs.getString("Pronounce");
            String name3 =rs.getString("Filipino");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb");
            
            trans_mean.setText(name);
            tran_pro.setText(name2);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Spanish"
                    + " FROM word WHERE "
                    + "Spanish like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            }
            row_count();
            freq_count();
        }
        else if(lang1.getSelectedItem().equals("Spanish") && lang2.getSelectedItem().equals("Spanish")){
            if(trans_search.getText().equals("")){
            trans_mean.setText("");
            translated.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            hint.setVisible(false);
            }
            else {
                hint.setVisible(true);
            try{
        
            String sql="SELECT Meaning, Spanish, Noun, Verb, Image  FROM word where Spanish = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Meaning");
            String name3 =rs.getString("Spanish");
            String name4 =rs.getString("Noun");
            String name5 =rs.getString("Verb");
            
            trans_mean.setText(name);
            translated.setText(name3);
            tran_noun.setText(name4);
            tran_verb.setText(name5);
            
            byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                tran_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            }
            try{
            String sql = "SELECT Spanish"
                    + " FROM word WHERE "
                    + "Spanish like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + trans_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
            
        }
            row_count();
            freq_count();
        }
        if(lang2.getSelectedItem().equals("Filipino")){
        try{
        
            String sql="SELECT Pronounce FROM word where Filipino = '" + (String) translated.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Pronounce");
            
            tran_pro.setText(name);
            
            }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void freq_count(){
    try{
            String sql="SELECT Word FROM frequent where Word = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Word");
            
            xxx.setText(name);
            }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
    if(translated.getText().equals("")){
    
    }
    else {
            if(xxx.getText().equals("")){
            try {
                String sql1 = "Insert into frequent (Word, Count, Date) values (?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, trans_search.getText());//Date
                pst.setString(2, "1");//POnum
                pst.setString(3, txt_date.getText());//user

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            }
            else {
            try{
            String sql="SELECT Word, Count FROM frequent where Word = '" + (String) trans_search.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Word");
            String name1 =rs.getString("Count");
            
            xxx.setText(name);
            zzz.setText(name1);
            }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
            int c;
            int a = Integer.parseInt(zzz.getText());
            
            c = a + 1;
            
            yyy.setText(Integer.toString(c));
            
            try {
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE frequent SET Count=? "
                        + " WHERE Word='" + xxx.getText() + "'");

                pst.setString(1, yyy.getText());

                pst.execute();
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } 
            }
    }
    xxx.setText("");
    yyy.setText("0");
    zzz.setText("0");
    }
    
    
            /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        Main = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        system = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bodycontent = new javax.swing.JPanel();
        newentry = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_level1 = new javax.swing.JLabel();
        txt_level2 = new javax.swing.JLabel();
        txt_level3 = new javax.swing.JLabel();
        txt_level4 = new javax.swing.JLabel();
        ne_word = new javax.swing.JTextField();
        ne_fil = new javax.swing.JTextField();
        ne_span = new javax.swing.JTextField();
        ne_mean = new javax.swing.JTextArea();
        txt_level30 = new javax.swing.JLabel();
        ne_image = new javax.swing.JLabel();
        ne_browse = new javax.swing.JButton();
        txt_level33 = new javax.swing.JLabel();
        ne_pro = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        ne_new = new javax.swing.JButton();
        ne_save = new javax.swing.JButton();
        ne_clear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        word_table = new javax.swing.JTable();
        update = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txt_level5 = new javax.swing.JLabel();
        txt_level6 = new javax.swing.JLabel();
        txt_level7 = new javax.swing.JLabel();
        txt_level8 = new javax.swing.JLabel();
        up_word = new javax.swing.JTextField();
        up_fil = new javax.swing.JTextField();
        up_span = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        up_mean = new javax.swing.JTextArea();
        up_id = new javax.swing.JLabel();
        txt_level27 = new javax.swing.JLabel();
        up_image = new javax.swing.JLabel();
        up_browse = new javax.swing.JButton();
        txt_level34 = new javax.swing.JLabel();
        up_pro = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        word_table1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        up_new = new javax.swing.JButton();
        up_update = new javax.swing.JButton();
        up_clear = new javax.swing.JButton();
        up_word1 = new javax.swing.JTextField();
        translate = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        trans_search = new javax.swing.JTextField();
        txt_level9 = new javax.swing.JLabel();
        translated = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        trans_mean = new javax.swing.JTextArea();
        txt_level10 = new javax.swing.JLabel();
        txt_level11 = new javax.swing.JLabel();
        lang1 = new javax.swing.JComboBox<>();
        lang2 = new javax.swing.JComboBox<>();
        txt_level12 = new javax.swing.JLabel();
        txt_level13 = new javax.swing.JLabel();
        hint = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        trans_table = new javax.swing.JTable();
        txt_level32 = new javax.swing.JLabel();
        tran_image = new javax.swing.JLabel();
        xxx = new javax.swing.JLabel();
        zzz = new javax.swing.JLabel();
        yyy = new javax.swing.JLabel();
        rc = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tran_pro = new javax.swing.JLabel();
        account = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txt_level14 = new javax.swing.JLabel();
        acc_fn = new javax.swing.JTextField();
        txt_level15 = new javax.swing.JLabel();
        txt_level16 = new javax.swing.JLabel();
        acc_user = new javax.swing.JTextField();
        txt_level17 = new javax.swing.JLabel();
        acc_pass = new javax.swing.JTextField();
        txt_level18 = new javax.swing.JLabel();
        acc_lvl = new javax.swing.JComboBox<>();
        acc_id = new javax.swing.JLabel();
        acc_reason = new javax.swing.JLabel();
        acc_bd = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        acc_table = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        acc_save = new javax.swing.JButton();
        acc_update = new javax.swing.JButton();
        acc_inactive = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        acclog_table = new javax.swing.JTable();
        acclog_search = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        userlog_table = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        userlog_search = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        inacc_table = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        txt_level19 = new javax.swing.JLabel();
        inacc_fn = new javax.swing.JTextField();
        txt_level20 = new javax.swing.JLabel();
        txt_level21 = new javax.swing.JLabel();
        inacc_user = new javax.swing.JTextField();
        txt_level22 = new javax.swing.JLabel();
        inacc_pass = new javax.swing.JTextField();
        txt_level23 = new javax.swing.JLabel();
        inacc_lvl = new javax.swing.JComboBox<>();
        inacc_id = new javax.swing.JLabel();
        txt_level24 = new javax.swing.JLabel();
        inacc_res = new javax.swing.JTextField();
        inacc_active = new javax.swing.JButton();
        inacc_bd = new com.toedter.calendar.JDateChooser();
        dashboard = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        freq_tbl = new javax.swing.JTable();
        pnl_dash = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        txt_level28 = new javax.swing.JLabel();
        ne_noun = new javax.swing.JTextField();
        ne_verb = new javax.swing.JTextField();
        txt_level29 = new javax.swing.JLabel();
        txt_level25 = new javax.swing.JLabel();
        up_noun = new javax.swing.JTextField();
        up_verb = new javax.swing.JTextField();
        txt_level26 = new javax.swing.JLabel();
        txt_level31 = new javax.swing.JLabel();
        tran_noun = new javax.swing.JTextField();
        Verb = new javax.swing.JLabel();
        tran_verb = new javax.swing.JTextField();
        footer = new javax.swing.JPanel();
        txt_time = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_level = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_name = new javax.swing.JLabel();
        txt_date = new javax.swing.JLabel();
        leftmenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        men_acc = new javax.swing.JLabel();
        men_dash = new javax.swing.JLabel();
        men_ne = new javax.swing.JLabel();
        men_update = new javax.swing.JLabel();
        men_trans = new javax.swing.JLabel();
        info = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCheckBox2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.CardLayout());

        Main.setLayout(null);

        jCheckBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/openeye.png"))); // NOI18N
        jCheckBox1.setOpaque(false);
        jCheckBox1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/image/closeeye.png"))); // NOI18N
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        Main.add(jCheckBox1);
        jCheckBox1.setBounds(650, 320, 50, 40);

        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.png"))); // NOI18N
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });
        Main.add(jButton2);
        jButton2.setBounds(450, 360, 120, 50);

        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        Main.add(pass);
        pass.setBounds(450, 320, 180, 30);

        jLabel3.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        Main.add(jLabel3);
        jLabel3.setBounds(310, 330, 90, 18);

        user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userKeyPressed(evt);
            }
        });
        Main.add(user);
        user.setBounds(450, 280, 180, 30);

        jLabel2.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User Name:");
        Main.add(jLabel2);
        jLabel2.setBounds(310, 290, 90, 18);

        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exitbtn.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Main.add(jButton3);
        jButton3.setBounds(550, 560, 120, 50);

        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.png"))); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Main.add(jButton1);
        jButton1.setBounds(390, 560, 120, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/main.png"))); // NOI18N
        Main.add(jLabel1);
        jLabel1.setBounds(150, 20, 747, 650);

        jLabel8.setText("0");
        Main.add(jLabel8);
        jLabel8.setBounds(510, 120, 6, 14);

        jPanel1.add(Main, "card2");

        system.setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(204, 204, 255));
        header.setLayout(null);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/domino.gif"))); // NOI18N
        header.add(jLabel7);
        jLabel7.setBounds(710, 10, 180, 120);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exitbtn.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        header.add(jLabel6);
        jLabel6.setBounds(970, 10, 50, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minimize.png"))); // NOI18N
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        header.add(jLabel5);
        jLabel5.setBounds(910, 10, 50, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/headerrr.png"))); // NOI18N
        header.add(jLabel4);
        jLabel4.setBounds(0, 0, 1030, 120);

        bodycontent.setBackground(new java.awt.Color(255, 255, 255));
        bodycontent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bodycontent.setLayout(new java.awt.CardLayout());

        newentry.setBackground(new java.awt.Color(102, 102, 102));

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ADD NEW WORD");

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "INPUT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_level1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level1.setForeground(new java.awt.Color(255, 255, 255));
        txt_level1.setText("Pronounce:");
        jPanel3.add(txt_level1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txt_level2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level2.setForeground(new java.awt.Color(255, 255, 255));
        txt_level2.setText("Main Word:");
        jPanel3.add(txt_level2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txt_level3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level3.setForeground(new java.awt.Color(255, 255, 255));
        txt_level3.setText("Spanish:");
        jPanel3.add(txt_level3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txt_level4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level4.setForeground(new java.awt.Color(255, 255, 255));
        txt_level4.setText("Meaning:");
        jPanel3.add(txt_level4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        ne_word.setEditable(false);
        ne_word.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        ne_word.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ne_wordKeyReleased(evt);
            }
        });
        jPanel3.add(ne_word, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 190, -1));

        ne_fil.setEditable(false);
        ne_fil.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jPanel3.add(ne_fil, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 190, -1));

        ne_span.setEditable(false);
        ne_span.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jPanel3.add(ne_span, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 190, -1));

        ne_mean.setEditable(false);
        ne_mean.setColumns(20);
        ne_mean.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        ne_mean.setRows(5);
        jPanel3.add(ne_mean, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 190, 40));

        txt_level30.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level30.setForeground(new java.awt.Color(255, 255, 255));
        txt_level30.setText("Image:");
        jPanel3.add(txt_level30, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 271, 111, -1));

        ne_image.setBackground(new java.awt.Color(255, 255, 255));
        ne_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/unknown.png"))); // NOI18N
        ne_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(ne_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 271, 190, 107));

        ne_browse.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        ne_browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browse.png"))); // NOI18N
        ne_browse.setText("Browse");
        ne_browse.setEnabled(false);
        ne_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ne_browseActionPerformed(evt);
            }
        });
        jPanel3.add(ne_browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 120, 50));

        txt_level33.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level33.setForeground(new java.awt.Color(255, 255, 255));
        txt_level33.setText("Filipino:");
        jPanel3.add(txt_level33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        ne_pro.setEditable(false);
        ne_pro.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jPanel3.add(ne_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 190, -1));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "OPTION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        ne_new.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        ne_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        ne_new.setText("New");
        ne_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ne_newActionPerformed(evt);
            }
        });

        ne_save.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        ne_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        ne_save.setText("Save");
        ne_save.setEnabled(false);
        ne_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ne_saveActionPerformed(evt);
            }
        });

        ne_clear.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        ne_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        ne_clear.setText("Clear");
        ne_clear.setEnabled(false);
        ne_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ne_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(ne_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ne_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ne_clear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(ne_new, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ne_save, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ne_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "DATA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        word_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(word_table);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout newentryLayout = new javax.swing.GroupLayout(newentry);
        newentry.setLayout(newentryLayout);
        newentryLayout.setHorizontalGroup(
            newentryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newentryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newentryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newentryLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(newentryLayout.createSequentialGroup()
                        .addGroup(newentryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        newentryLayout.setVerticalGroup(
            newentryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newentryLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newentryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newentryLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bodycontent.add(newentry, "card3");

        update.setBackground(new java.awt.Color(102, 102, 102));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("UPDATE");

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "INPUT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_level5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level5.setForeground(new java.awt.Color(255, 255, 255));
        txt_level5.setText("Filipino:");
        jPanel6.add(txt_level5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 65, -1, -1));

        txt_level6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level6.setForeground(new java.awt.Color(255, 255, 255));
        txt_level6.setText("Main Word:");
        jPanel6.add(txt_level6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 27, -1, -1));

        txt_level7.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level7.setForeground(new java.awt.Color(255, 255, 255));
        txt_level7.setText("Spanish:");
        jPanel6.add(txt_level7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txt_level8.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level8.setForeground(new java.awt.Color(255, 255, 255));
        txt_level8.setText("Meaning:");
        jPanel6.add(txt_level8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        up_word.setEditable(false);
        up_word.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel6.add(up_word, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 27, 190, -1));

        up_fil.setEditable(false);
        up_fil.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel6.add(up_fil, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 65, 190, -1));

        up_span.setEditable(false);
        up_span.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel6.add(up_span, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 190, -1));

        up_mean.setEditable(false);
        up_mean.setColumns(20);
        up_mean.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        up_mean.setRows(5);
        jScrollPane4.setViewportView(up_mean);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 190, 59));

        up_id.setForeground(new java.awt.Color(102, 102, 102));
        up_id.setText("id");
        jPanel6.add(up_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 184, 24, -1));

        txt_level27.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level27.setForeground(new java.awt.Color(255, 255, 255));
        txt_level27.setText("Image:");
        jPanel6.add(txt_level27, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 271, 111, -1));

        up_image.setBackground(new java.awt.Color(255, 255, 255));
        up_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        up_image.setText("No Image");
        up_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.add(up_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 271, 190, 101));

        up_browse.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        up_browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browse.png"))); // NOI18N
        up_browse.setText("Browse");
        up_browse.setEnabled(false);
        up_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_browseActionPerformed(evt);
            }
        });
        jPanel6.add(up_browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 322, -1, 50));

        txt_level34.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level34.setForeground(new java.awt.Color(255, 255, 255));
        txt_level34.setText("Pronounce:");
        jPanel6.add(txt_level34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        up_pro.setEditable(false);
        up_pro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jPanel6.add(up_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 190, -1));

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "DATA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        word_table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        word_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                word_table1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(word_table1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "OPTION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        up_new.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        up_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        up_new.setText("New");
        up_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_newActionPerformed(evt);
            }
        });

        up_update.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        up_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update_btn.png"))); // NOI18N
        up_update.setText("Update");
        up_update.setEnabled(false);
        up_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_updateActionPerformed(evt);
            }
        });

        up_clear.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        up_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        up_clear.setText("Clear");
        up_clear.setEnabled(false);
        up_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(up_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up_clear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(up_new, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(up_update, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(up_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        up_word1.setFont(new java.awt.Font("Bodoni MT Black", 0, 12)); // NOI18N
        up_word1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                up_word1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update);
        update.setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(359, 359, 359)
                        .addComponent(up_word1))
                    .addGroup(updateLayout.createSequentialGroup()
                        .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(up_word1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bodycontent.add(update, "card4");

        translate.setBackground(new java.awt.Color(102, 102, 102));

        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("TRANSLATE");

        jPanel9.setBackground(new java.awt.Color(102, 102, 102));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        trans_search.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        trans_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                trans_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                trans_searchKeyReleased(evt);
            }
        });

        txt_level9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level9.setForeground(new java.awt.Color(255, 255, 255));
        txt_level9.setText("Meaning");

        translated.setEditable(false);
        translated.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        trans_mean.setEditable(false);
        trans_mean.setColumns(20);
        trans_mean.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        trans_mean.setRows(5);
        jScrollPane6.setViewportView(trans_mean);

        txt_level10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level10.setForeground(new java.awt.Color(255, 255, 255));
        txt_level10.setText("Main Word:");

        txt_level11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level11.setForeground(new java.awt.Color(255, 255, 255));
        txt_level11.setText("Translate");

        lang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Filipino", "Spanish" }));
        lang1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                lang1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        lang2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Filipino", "Spanish" }));

        txt_level12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level12.setForeground(new java.awt.Color(255, 255, 255));
        txt_level12.setText("Choose Language");

        txt_level13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level13.setForeground(new java.awt.Color(255, 255, 255));
        txt_level13.setText("Choose Language");

        jScrollPane12.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane12.setBorder(null);
        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        trans_table.setBackground(new java.awt.Color(102, 102, 102));
        trans_table.setForeground(new java.awt.Color(255, 255, 255));
        trans_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        trans_table.setOpaque(false);
        trans_table.setSelectionForeground(new java.awt.Color(102, 102, 102));
        trans_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trans_tableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(trans_table);

        javax.swing.GroupLayout hintLayout = new javax.swing.GroupLayout(hint);
        hint.setLayout(hintLayout);
        hintLayout.setHorizontalGroup(
            hintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        hintLayout.setVerticalGroup(
            hintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        txt_level32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level32.setForeground(new java.awt.Color(255, 255, 255));
        txt_level32.setText("Sample Image");

        tran_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        zzz.setForeground(new java.awt.Color(102, 102, 102));
        zzz.setText("0");

        yyy.setForeground(new java.awt.Color(102, 102, 102));
        yyy.setText("0");

        rc.setForeground(new java.awt.Color(102, 102, 102));
        rc.setText("0");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/audio.png"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/audio.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/audio.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        tran_pro.setText("jLabel21");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(hint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lang1, javax.swing.GroupLayout.Alignment.LEADING, 0, 217, Short.MAX_VALUE)
                                .addComponent(xxx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(trans_search, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(130, 130, 130))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_level10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_level12, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(zzz, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(yyy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(rc)))
                            .addComponent(tran_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_level32, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tran_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addComponent(translated, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_level13, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lang2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_level9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20)))
                    .addComponent(txt_level11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_level12)
                            .addComponent(txt_level13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lang1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_level10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(trans_search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lang2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_level11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(translated, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_level9)
                            .addComponent(rc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addComponent(txt_level32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(tran_pro)
                        .addGap(38, 38, 38)
                        .addComponent(zzz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xxx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(yyy)
                                .addGap(0, 16, Short.MAX_VALUE))))
                    .addComponent(tran_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout translateLayout = new javax.swing.GroupLayout(translate);
        translate.setLayout(translateLayout);
        translateLayout.setHorizontalGroup(
            translateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(translateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(translateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(translateLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        translateLayout.setVerticalGroup(
            translateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(translateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bodycontent.add(translate, "card5");

        account.setBackground(new java.awt.Color(102, 102, 102));

        jLabel19.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("ACCOUNT");

        jPanel10.setBackground(new java.awt.Color(102, 102, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(102, 102, 102));

        jPanel14.setBackground(new java.awt.Color(102, 102, 102));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "DETAIL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_level14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level14.setForeground(new java.awt.Color(255, 255, 255));
        txt_level14.setText("Full Name");

        acc_fn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txt_level15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level15.setForeground(new java.awt.Color(255, 255, 255));
        txt_level15.setText("Birth Date");

        txt_level16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level16.setForeground(new java.awt.Color(255, 255, 255));
        txt_level16.setText("Username");

        acc_user.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txt_level17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level17.setForeground(new java.awt.Color(255, 255, 255));
        txt_level17.setText("Password");

        acc_pass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txt_level18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level18.setForeground(new java.awt.Color(255, 255, 255));
        txt_level18.setText("Level");

        acc_lvl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        acc_lvl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Level--", "Admin", "User" }));

        acc_id.setForeground(new java.awt.Color(102, 102, 102));
        acc_id.setText("accid");

        acc_reason.setForeground(new java.awt.Color(102, 102, 102));
        acc_reason.setText("jLabel12");

        acc_bd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acc_fn)
                    .addComponent(acc_user)
                    .addComponent(acc_pass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acc_lvl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txt_level14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acc_id, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_level16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level17, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level18, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txt_level15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(acc_reason)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(acc_bd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_level14)
                    .addComponent(acc_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_fn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_level15)
                    .addComponent(acc_reason))
                .addGap(7, 7, 7)
                .addComponent(acc_bd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_level16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_user, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_level17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_level18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "LIST", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        acc_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        acc_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(acc_table);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(102, 102, 102));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "OPTION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        acc_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        acc_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_saveActionPerformed(evt);
            }
        });

        acc_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update_btn.png"))); // NOI18N
        acc_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_updateActionPerformed(evt);
            }
        });

        acc_inactive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        acc_inactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_inactiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acc_save, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(acc_update, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(acc_inactive, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(acc_inactive, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(acc_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(acc_save)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Account Configure", jPanel11);

        jPanel12.setBackground(new java.awt.Color(102, 102, 102));

        jPanel20.setBackground(new java.awt.Color(102, 102, 102));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "LOG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        acclog_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(acclog_table);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        acclog_search.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        acclog_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                acclog_searchKeyReleased(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acclog_search, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acclog_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("Account Log", jPanel12);

        jPanel13.setBackground(new java.awt.Color(102, 102, 102));

        jPanel21.setBackground(new java.awt.Color(102, 102, 102));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "LOG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        userlog_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(userlog_table);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search1.png"))); // NOI18N

        userlog_search.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        userlog_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userlog_searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userlog_search, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userlog_search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("User Log", jPanel13);

        jPanel17.setBackground(new java.awt.Color(102, 102, 102));

        jPanel18.setBackground(new java.awt.Color(102, 102, 102));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "LIST", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        inacc_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        inacc_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inacc_tableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(inacc_table);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(102, 102, 102));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "DETAIL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_level19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level19.setForeground(new java.awt.Color(255, 255, 255));
        txt_level19.setText("Full Name");

        inacc_fn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inacc_fn.setEnabled(false);
        inacc_fn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inacc_fnActionPerformed(evt);
            }
        });

        txt_level20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level20.setForeground(new java.awt.Color(255, 255, 255));
        txt_level20.setText("Birth Date");

        txt_level21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level21.setForeground(new java.awt.Color(255, 255, 255));
        txt_level21.setText("Username");

        inacc_user.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inacc_user.setEnabled(false);

        txt_level22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level22.setForeground(new java.awt.Color(255, 255, 255));
        txt_level22.setText("Password");

        inacc_pass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inacc_pass.setEnabled(false);

        txt_level23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level23.setForeground(new java.awt.Color(255, 255, 255));
        txt_level23.setText("Level");

        inacc_lvl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inacc_lvl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Level--", "Admin", "User" }));
        inacc_lvl.setEnabled(false);

        inacc_id.setForeground(new java.awt.Color(102, 102, 102));
        inacc_id.setText("accid");

        txt_level24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_level24.setForeground(new java.awt.Color(255, 255, 255));
        txt_level24.setText("Reason");

        inacc_res.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inacc_res.setEnabled(false);

        inacc_active.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reactive.png"))); // NOI18N
        inacc_active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inacc_activeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inacc_fn)
                    .addComponent(inacc_user)
                    .addComponent(inacc_pass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inacc_lvl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(txt_level19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(inacc_id, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inacc_res)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(inacc_active, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_level20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level21, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level22, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level23, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level24, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(inacc_bd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_level19)
                    .addComponent(inacc_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inacc_fn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_level20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inacc_bd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_level21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inacc_user, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_level22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inacc_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_level23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inacc_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_level24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inacc_res, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inacc_active)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inactive Account", jPanel17);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout accountLayout = new javax.swing.GroupLayout(account);
        account.setLayout(accountLayout);
        accountLayout.setHorizontalGroup(
            accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(accountLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        accountLayout.setVerticalGroup(
            accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bodycontent.add(account, "card6");

        dashboard.setBackground(new java.awt.Color(102, 102, 102));
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("DASHBOARD");
        dashboard.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 283, -1));

        jButton4.setText("Pie Chart");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        dashboard.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 142, 30));

        jPanel22.setBackground(new java.awt.Color(102, 102, 102));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Frequent Translate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel22.setForeground(new java.awt.Color(255, 255, 255));

        freq_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(freq_tbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(freq_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dashboard.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 780, 190));

        pnl_dash.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_dashLayout = new javax.swing.GroupLayout(pnl_dash);
        pnl_dash.setLayout(pnl_dashLayout);
        pnl_dashLayout.setHorizontalGroup(
            pnl_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        pnl_dashLayout.setVerticalGroup(
            pnl_dashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        dashboard.add(pnl_dash, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 770, 270));

        bodycontent.add(dashboard, "card2");

        jPanel23.setBackground(new java.awt.Color(0, 102, 102));

        txt_level28.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level28.setForeground(new java.awt.Color(255, 255, 255));
        txt_level28.setText("Noun:");

        ne_noun.setEditable(false);
        ne_noun.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N

        ne_verb.setEditable(false);
        ne_verb.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N

        txt_level29.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level29.setForeground(new java.awt.Color(255, 255, 255));
        txt_level29.setText("Verb:");

        txt_level25.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level25.setForeground(new java.awt.Color(255, 255, 255));
        txt_level25.setText("Noun:");

        up_noun.setEditable(false);
        up_noun.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        up_verb.setEditable(false);
        up_verb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txt_level26.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_level26.setForeground(new java.awt.Color(255, 255, 255));
        txt_level26.setText("Verb:");

        txt_level31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_level31.setForeground(new java.awt.Color(255, 255, 255));
        txt_level31.setText("Noun");

        tran_noun.setEditable(false);
        tran_noun.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        Verb.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Verb.setForeground(new java.awt.Color(255, 255, 255));
        Verb.setText("Verb");

        tran_verb.setEditable(false);
        tran_verb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txt_level28, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(ne_noun, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txt_level29, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(ne_verb, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txt_level25, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(up_noun, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txt_level26, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(up_verb, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tran_noun, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tran_verb, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_level31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Verb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(165, 165, 165))))
                .addContainerGap(454, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_level28))
                    .addComponent(ne_noun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_level29))
                    .addComponent(ne_verb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_level25)
                    .addComponent(up_noun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_level26)
                    .addComponent(up_verb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(txt_level31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tran_noun, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Verb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tran_verb, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        bodycontent.add(jPanel23, "card7");

        footer.setBackground(new java.awt.Color(102, 102, 102));
        footer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_time.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        txt_time.setForeground(new java.awt.Color(255, 255, 255));
        txt_time.setText("Time");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/time.png"))); // NOI18N

        txt_level.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        txt_level.setForeground(new java.awt.Color(255, 255, 255));
        txt_level.setText("Level");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N

        txt_name.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(255, 255, 255));
        txt_name.setText("Name");

        txt_date.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        txt_date.setForeground(new java.awt.Color(255, 255, 255));
        txt_date.setText("Date");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(txt_level, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_level, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9)
                    .addGroup(footerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );

        leftmenu.setBackground(new java.awt.Color(51, 51, 51));
        leftmenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setLayout(new java.awt.CardLayout());

        menu.setBackground(new java.awt.Color(51, 51, 51));

        men_acc.setBackground(new java.awt.Color(51, 51, 51));
        men_acc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/account.png"))); // NOI18N
        men_acc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(204, 255, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        men_acc.setOpaque(true);
        men_acc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_accMouseClicked(evt);
            }
        });

        men_dash.setBackground(new java.awt.Color(51, 51, 51));
        men_dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard.png"))); // NOI18N
        men_dash.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(204, 255, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        men_dash.setOpaque(true);
        men_dash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_dashMouseClicked(evt);
            }
        });

        men_ne.setBackground(new java.awt.Color(51, 51, 51));
        men_ne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/newentry.png"))); // NOI18N
        men_ne.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(204, 255, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        men_ne.setOpaque(true);
        men_ne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_neMouseClicked(evt);
            }
        });

        men_update.setBackground(new java.awt.Color(51, 51, 51));
        men_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        men_update.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(204, 255, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        men_update.setOpaque(true);
        men_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_updateMouseClicked(evt);
            }
        });

        men_trans.setBackground(new java.awt.Color(51, 51, 51));
        men_trans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/translate.png"))); // NOI18N
        men_trans.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(204, 255, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255)));
        men_trans.setOpaque(true);
        men_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_transMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(men_ne))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(men_dash)
                            .addComponent(men_acc)
                            .addComponent(men_update)
                            .addComponent(men_trans))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(men_dash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_trans)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(men_ne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_acc)
                .addContainerGap())
        );

        jPanel2.add(menu, "card3");

        info.setBackground(new java.awt.Color(51, 51, 51));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(info, "card2");

        jCheckBox2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Information");
        jCheckBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/info.png"))); // NOI18N
        jCheckBox2.setOpaque(false);
        jCheckBox2.setRolloverEnabled(false);
        jCheckBox2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/image/menu.png"))); // NOI18N
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout leftmenuLayout = new javax.swing.GroupLayout(leftmenu);
        leftmenu.setLayout(leftmenuLayout);
        leftmenuLayout.setHorizontalGroup(
            leftmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftmenuLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftmenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        leftmenuLayout.setVerticalGroup(
            leftmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftmenuLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout systemLayout = new javax.swing.GroupLayout(system);
        system.setLayout(systemLayout);
        systemLayout.setHorizontalGroup(
            systemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(systemLayout.createSequentialGroup()
                .addComponent(leftmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodycontent, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        systemLayout.setVerticalGroup(
            systemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(systemLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(systemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(leftmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodycontent, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(system, "card3");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if (jCheckBox1.isSelected()) {
      pass.setEchoChar((char)0); //password = JPasswordField
   } else {
      pass.setEchoChar('*');
   }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        startlogin();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        login();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
    try {
                String sql1 = "Insert into accountlog (Date, Time, Name, Username, Level, Remarks) values (?,?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, user.getText());//transaction
                pst.setString(5, txt_level.getText());//ponum
                pst.setString(6, "Log-out");//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
        
        system.setVisible(false);
        Main.setVisible(true);
        start();
        jButton1.setVisible(true);
        jButton3.setVisible(true);
        txt_level.setText("Level");
        txt_name.setText("Name");
        user.setText("");
        pass.setText("");
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
            //piechart();
        }
    }//GEN-LAST:event_userKeyPressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
            //piechart();
        }
    }//GEN-LAST:event_passKeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
            //piechart();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setOpaque(false);
        jLabel5.setBackground(new Color(192, 215, 252, 100));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setBackground(new Color(0, 0, 0, 0));
        jLabel5.setOpaque(true);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        
        if (jCheckBox2.isSelected()) {
      jCheckBox2.setText("Menu");
        menu.setVisible(false);
        info.setVisible(true);
   } else {
      jCheckBox2.setText("Information");
        menu.setVisible(true);
        info.setVisible(false);
   }
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void ne_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ne_newActionPerformed
        ne_clr();
        ne_save.setEnabled(true);
        ne_new.setEnabled(false);
        ne_clear.setEnabled(true);
        ne_browse.setEnabled(true);
        
        ne_word.setEditable(true);
        ne_mean.setEditable(true);
        ne_fil.setEditable(true);
        ne_pro.setEditable(true);
        ne_span.setEditable(true);
        ne_noun.setEditable(true);
        ne_verb.setEditable(true);
    }//GEN-LAST:event_ne_newActionPerformed

    private void ne_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ne_saveActionPerformed
       if (imgpath != null){
        try {
            String sql = "Insert into word (Word, Meaning, Filipino, Pronounce, Spanish, Noun, Verb, Image) values (?,?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            InputStream img = new FileInputStream(new File(imgpath));
            pst.setString(1, ne_word.getText());
            pst.setString(2, ne_mean.getText());
            pst.setString(3, ne_fil.getText());
            pst.setString(4, ne_pro.getText());
            pst.setString(5, ne_span.getText());
            pst.setString(6, ne_noun.getText());
            pst.setString(7, ne_verb.getText());
            pst.setBlob(8, img);
            pst.execute();
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Add Entry");//transaction
                pst.setString(5, ne_word.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
            addtable_ref();
            userlog_ref();
            ne_clr();
            update_ref();
            
            ne_save.setEnabled(false);
            ne_clear.setEnabled(false);
            ne_new.setEnabled(true);
            ne_browse.setEnabled(true);
            
            ne_word.setEditable(false);
            ne_mean.setEditable(false);
            ne_fil.setEditable(false);
            ne_span.setEditable(false);
            ne_noun.setEditable(false);
            ne_noun.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       }
       else {
           try {
            String sql = "Insert into word (Word, Meaning, Filipino, Pronounce, Spanish, Noun, Verb) values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, ne_word.getText());
            pst.setString(2, ne_mean.getText());
            pst.setString(3, ne_fil.getText());
            pst.setString(4, ne_pro.getText());
            pst.setString(5, ne_span.getText());
            pst.setString(6, ne_noun.getText());
            pst.setString(7, ne_verb.getText());
            //pst.setString(7, "insert image");
            pst.execute();
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Add Entry");//transaction
                pst.setString(5, ne_word.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
            addtable_ref();
            userlog_ref();
            ne_clr();
            update_ref();
            
            ne_save.setEnabled(false);
            ne_clear.setEnabled(false);
            ne_new.setEnabled(true);
            ne_browse.setEnabled(true);
            
            ne_word.setEditable(false);
            ne_mean.setEditable(false);
            ne_fil.setEditable(false);
            ne_pro.setEditable(false);
            ne_span.setEditable(false);
            ne_noun.setEditable(false);
            ne_noun.setEditable(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
           
           //JOptionPane.showMessageDialog(null,"No Image Selected");
       }
        imgpath = null;
    }//GEN-LAST:event_ne_saveActionPerformed

    private void ne_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ne_clearActionPerformed
        ne_clr();
    }//GEN-LAST:event_ne_clearActionPerformed

    private void men_dashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_dashMouseClicked
        dashboard.setVisible(true);
        newentry.setVisible(false);
        update.setVisible(false);
        translate.setVisible(false);
        account.setVisible(false);
        ne_clr();
        up_clr();
        men_dash.setBackground(new Color(218, 228, 230,100));//click
        men_ne.setBackground(new Color(51, 51, 51,225));
        men_update.setBackground(new Color(51, 51, 51,225));
        men_trans.setBackground(new Color(51, 51, 51,225));
        men_acc.setBackground(new Color(51, 51, 51,225));
        freq_ref();
        //piechart();
    }//GEN-LAST:event_men_dashMouseClicked

    private void men_neMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_neMouseClicked
        dashboard.setVisible(false);
        newentry.setVisible(true);
        update.setVisible(false);
        translate.setVisible(false);
        account.setVisible(false);
        ne_clr();
        up_clr();
        addtable_ref();
        men_dash.setBackground(new Color(51, 51, 51,225));
        men_ne.setBackground(new Color(218, 228, 230,100));//click
        men_update.setBackground(new Color(51, 51, 51,225));
        men_trans.setBackground(new Color(51, 51, 51,225));
        men_acc.setBackground(new Color(51, 51, 51,225));
    }//GEN-LAST:event_men_neMouseClicked

    private void men_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_updateMouseClicked
        dashboard.setVisible(false);
        newentry.setVisible(false);
        update.setVisible(true);
        translate.setVisible(false);
        account.setVisible(false);
        ne_clr();
        up_clr();
        update_ref();
        men_dash.setBackground(new Color(51, 51, 51,225));
        men_ne.setBackground(new Color(51, 51, 51,225));
        men_update.setBackground(new Color(218, 228, 230,100));//click
        men_trans.setBackground(new Color(51, 51, 51,225));
        men_acc.setBackground(new Color(51, 51, 51,225));
    }//GEN-LAST:event_men_updateMouseClicked

    private void men_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_transMouseClicked
        dashboard.setVisible(false);
        newentry.setVisible(false);
        update.setVisible(false);
        translate.setVisible(true);
        account.setVisible(false);
        ne_clr();
        up_clr();
        men_dash.setBackground(new Color(51, 51, 51,225));
        men_ne.setBackground(new Color(51, 51, 51,225));
        men_update.setBackground(new Color(51, 51, 51,225));
        men_trans.setBackground(new Color(218, 228, 230,100));//click
        men_acc.setBackground(new Color(51, 51, 51,225));
    }//GEN-LAST:event_men_transMouseClicked

    private void men_accMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_accMouseClicked
        dashboard.setVisible(false);
        newentry.setVisible(false);
        update.setVisible(false);
        translate.setVisible(false);
        account.setVisible(true);
        ne_clr();
        up_clr();
        men_dash.setBackground(new Color(51, 51, 51,225));
        men_ne.setBackground(new Color(51, 51, 51,225));
        men_update.setBackground(new Color(51, 51, 51,225));
        men_trans.setBackground(new Color(51, 51, 51,225));
        men_acc.setBackground(new Color(218, 228, 230,100));//click
    }//GEN-LAST:event_men_accMouseClicked

    private void up_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_newActionPerformed
        up_new.setEnabled(false);
        up_update.setEnabled(true);
        up_clear.setEnabled(true);
        up_browse.setEnabled(true);
        
//        up_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_DEFAULT)));
//        up_image.setText("No Image");
        
        up_word.setEditable(true);
        up_pro.setEditable(true);
        up_mean.setEditable(true);
        up_fil.setEditable(true);
        up_span.setEditable(true);
        up_noun.setEditable(true);
        up_verb.setEditable(true);
    }//GEN-LAST:event_up_newActionPerformed

    private void up_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_updateActionPerformed
   if (up_image.getText().equals("No Image")){
    
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE word SET Word=?, Meaning=?, "
                    + "Filipino=?, Pronounce=?, Spanish=?, Noun=?, Verb=? WHERE ID='" + up_id.getText() + "'");
            pst.setString(1, up_word.getText());
            pst.setString(2, up_mean.getText());
            pst.setString(3, up_fil.getText());
            pst.setString(4, up_pro.getText());
            pst.setString(5, up_span.getText());
            pst.setString(6, up_noun.getText());
            pst.setString(7, up_verb.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Update Entry");//transaction
                pst.setString(5, up_word.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
            update_ref();
            userlog_ref();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    
        ne_clr();
        up_clr();
        up_update.setEnabled(false);
        up_clear.setEnabled(false);
        up_new.setEnabled(true);
        up_word.setEditable(false);
        up_mean.setEditable(false);
        up_fil.setEditable(false);
        up_span.setEditable(false);
       
   }else{
   
   if (imgpath1 != null){    
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE word SET Word=?, Meaning=?, "
                    + "Filipino=?, Pronounce=?, Spanish=?, Noun=?, Verb=?, Image=? WHERE ID='" + up_id.getText() + "'");
            InputStream img = new FileInputStream(new File(imgpath1));
            pst.setString(1, up_word.getText());
            pst.setString(2, up_mean.getText());
            pst.setString(3, up_fil.getText());
            pst.setString(4, up_pro.getText());
            pst.setString(5, up_span.getText());
            pst.setString(6, up_noun.getText());
            pst.setString(7, up_verb.getText());
            pst.setBlob(8, img);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Update Entry");//transaction
                pst.setString(5, up_word.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
            update_ref();
            userlog_ref();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    else{
            JOptionPane.showMessageDialog(null,"Please Check Entry!!!");
        }
        imgpath1=null;
        
        ne_clr();
        up_clr();
        up_update.setEnabled(false);
        up_clear.setEnabled(false);
        up_new.setEnabled(true);
        up_word.setEditable(false);
        up_mean.setEditable(false);
        up_fil.setEditable(false);
        up_span.setEditable(false);
   
       
       
       
   }// end of if else statement
        
    }//GEN-LAST:event_up_updateActionPerformed

    private void up_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_clearActionPerformed
        up_clr();
    }//GEN-LAST:event_up_clearActionPerformed

    private void word_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_word_table1MouseClicked
        int z = word_table1.getSelectedRow();

            TableModel model = (TableModel)word_table1.getModel();
          up_id.setText(model.getValueAt(z, 0).toString());
          up_word.setText(model.getValueAt(z, 1).toString());
          up_mean.setText(model.getValueAt(z, 2).toString());
          up_fil.setText(model.getValueAt(z, 3).toString());
          up_pro.setText(model.getValueAt(z, 4).toString());
          up_span.setText(model.getValueAt(z, 5).toString());
          up_noun.setText(model.getValueAt(z, 6).toString());
          up_verb.setText(model.getValueAt(z, 7).toString());

          try{

            String sql="SELECT Image "
            + "  FROM word where ID = '" + (String) up_id.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if(rs.next()){

                byte[] img = rs.getBytes("Image");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                up_image.setIcon(newImage);
                up_image.setText("");
            }else{
            up_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_DEFAULT)));
            up_image.setText("No Image");
            }
        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
             up_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_DEFAULT)));
            up_image.setText("No Image"); up_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_DEFAULT)));
            up_image.setText("No Image");
        }
    }//GEN-LAST:event_word_table1MouseClicked

    private void ne_wordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ne_wordKeyReleased
        try {
            String sql = "SELECT ID, Word, Meaning, Filipino, Pronounce, Spanish, Image FROM word WHERE "
                    + "Word like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + ne_word.getText() + "%");


            rs = (ResultSet) pst.executeQuery();
            word_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_ne_wordKeyReleased

    private void trans_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trans_searchKeyReleased
        translation();
    }//GEN-LAST:event_trans_searchKeyReleased

    private void acc_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_saveActionPerformed
        try {
            String sql = "Insert into access (Full_Name, Birthdate, Username, Password, Level) values (?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, acc_fn.getText());
            pst.setString(2, ((JTextField)acc_bd.getDateEditor().getUiComponent()).getText());
            pst.setString(3, acc_user.getText());
            pst.setString(4, acc_pass.getText());
            pst.setString(5, (String) acc_lvl.getSelectedItem());

            pst.execute();
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "New Account");//transaction
                pst.setString(5, acc_user.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
            account_ref();
            userlog_ref();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        acc_clr();
    }//GEN-LAST:event_acc_saveActionPerformed

    private void acc_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_tableMouseClicked
        int z = acc_table.getSelectedRow();

            TableModel model = (TableModel)acc_table.getModel();
          acc_id.setText(model.getValueAt(z, 0).toString());
          acc_fn.setText(model.getValueAt(z, 1).toString());
          ((JTextField)acc_bd.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 2).toString());
          acc_user.setText(model.getValueAt(z, 3).toString());
          acc_pass.setText(model.getValueAt(z, 4).toString());
          acc_lvl.setSelectedItem(model.getValueAt(z, 5).toString());
    }//GEN-LAST:event_acc_tableMouseClicked

    private void acc_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_updateActionPerformed
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE access SET Full_Name=?, Birthdate=?, Username=?, Password=?, Level=? WHERE ID='" + acc_id.getText() + "'");
            pst.setString(1, acc_fn.getText());
            pst.setString(2, ((JTextField)acc_bd.getDateEditor().getUiComponent()).getText());
            pst.setString(3, acc_user.getText());
            pst.setString(4, acc_pass.getText());
            pst.setString(5, (String) acc_lvl.getSelectedItem());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Update Account");//transaction
                pst.setString(5, acc_user.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
            account_ref();
            userlog_ref();
            
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        acc_clr();
    }//GEN-LAST:event_acc_updateActionPerformed

    private void inacc_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inacc_tableMouseClicked
        int z = inacc_table.getSelectedRow();

            TableModel model = (TableModel)inacc_table.getModel();
          inacc_id.setText(model.getValueAt(z, 0).toString());
          inacc_fn.setText(model.getValueAt(z, 1).toString());
          ((JTextField)inacc_bd.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 2).toString());
          inacc_user.setText(model.getValueAt(z, 3).toString());
          inacc_pass.setText(model.getValueAt(z, 4).toString());
          inacc_lvl.setSelectedItem(model.getValueAt(z, 5).toString());
          inacc_res.setText(model.getValueAt(z, 6).toString());
    }//GEN-LAST:event_inacc_tableMouseClicked

    private void inacc_activeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inacc_activeActionPerformed
        try {
            String sql = "Insert into access (ID, Full_Name, Birthdate, Username, Password, Level) values (?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, inacc_id.getText());//Date
            pst.setString(2, inacc_fn.getText());//Date
            pst.setString(3, ((JTextField)inacc_bd.getDateEditor().getUiComponent()).getText());
            pst.setString(4, inacc_user.getText());//POnum
            pst.setString(5, inacc_pass.getText());//POnum
            pst.setString(6, (String) inacc_lvl.getSelectedItem());//Establishment 

            pst.execute();
            
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Re-active Account");//transaction
                pst.setString(5, inacc_user.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

         try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM inactive  WHERE ID = '" + inacc_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "User Account Successfully Active!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        account_ref();
        userlog_ref();
        inaccount_ref();
    }//GEN-LAST:event_inacc_activeActionPerformed

    private void acc_inactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_inactiveActionPerformed
        String res = JOptionPane.showInputDialog(this, "State Reason");
                acc_reason.setText(res);

        try {
            String sql = "Insert into inactive (ID, Full_Name, Birthdate, Username, Password, Level, Reason) values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, acc_id.getText());//Date
            pst.setString(2, acc_fn.getText());//Date
            pst.setString(3, ((JTextField)acc_bd.getDateEditor().getUiComponent()).getText());
            pst.setString(4, acc_user.getText());//POnum
            pst.setString(5, acc_pass.getText());//POnum
            pst.setString(6, (String) acc_lvl.getSelectedItem());//Establishment 
            pst.setString(7, acc_reason.getText());//POnum
            //Date_return

            pst.execute();
                
                try {
                String sql1 = "Insert into userlog (Date, Time, Name, Remarks, Entry) values (?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);

                pst.setString(1, txt_date.getText());//Date
                pst.setString(2, txt_time.getText());//POnum
                pst.setString(3, txt_name.getText());//user
                pst.setString(4, "Inactive Account");//transaction
                pst.setString(5, acc_user.getText());//ponum

                pst.execute();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

         try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM access  WHERE ID = '" + acc_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "User Account Successfully Inactive!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         account_ref();
        inaccount_ref();
        userlog_ref();
        acc_clr();
 
    }//GEN-LAST:event_acc_inactiveActionPerformed

    private void acclog_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acclog_searchKeyReleased
        try {
            String sql = "SELECT *"
                    + " FROM accountlog WHERE "
                    + "Date like ? or Time like ? or Name like ? or  Username like ? or  Level like ? or  Remarks like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + acclog_search.getText() + "%");
            pst.setString(2, "%" + acclog_search.getText() + "%");
            pst.setString(3, "%" + acclog_search.getText() + "%");
            pst.setString(4, "%" + acclog_search.getText() + "%");
            pst.setString(5, "%" + acclog_search.getText() + "%");
            pst.setString(6, "%" + acclog_search.getText() + "%");


            rs = (ResultSet) pst.executeQuery();
            acclog_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_acclog_searchKeyReleased

    private void userlog_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userlog_searchKeyReleased
        try {
            String sql = "SELECT *"
                    + " FROM userlog WHERE "
                    + "Date like ? or Time like ? or Name like ? or Remarks like ? or Entry like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + userlog_search.getText() + "%");
            pst.setString(2, "%" + userlog_search.getText() + "%");
            pst.setString(3, "%" + userlog_search.getText() + "%");
            pst.setString(4, "%" + userlog_search.getText() + "%");
            pst.setString(5, "%" + userlog_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            userlog_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_userlog_searchKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //piechart();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void lang1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_lang1PopupMenuWillBecomeInvisible
        if(lang1.getSelectedItem().equals("English")){
            try {
            String sql = "SELECT Word FROM word ORDER BY Word ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            trans_table.setTableHeader(null);

            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
        }
        else if(lang1.getSelectedItem().equals("Filipino")){
            try {
            String sql = "SELECT Filipino FROM word ORDER BY Filipino ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            trans_table.setTableHeader(null);

            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
        }
        else if(lang1.getSelectedItem().equals("Spanish")){
            try {
            String sql = "SELECT Spanish FROM word ORDER BY Spanish ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            trans_table.setModel(DbUtils.resultSetToTableModel(rs));
            trans_table.setTableHeader(null);

            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_lang1PopupMenuWillBecomeInvisible

    private void trans_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trans_tableMouseClicked
        int z = trans_table.getSelectedRow();

            TableModel model = (TableModel)trans_table.getModel();
          trans_search.setText(model.getValueAt(z, 0).toString());
          
          translation();
    }//GEN-LAST:event_trans_tableMouseClicked

    private void up_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_browseActionPerformed
        JFileChooser file = new JFileChooser(jFileChooser2  .getCurrentDirectory());
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image*","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            // image.setIcon(ResizeImage(path,null));
            up_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(up_image.getWidth(), up_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath1 = path;
            up_image.setText("");

        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
            up_image.setText("No Image");
        }
    }//GEN-LAST:event_up_browseActionPerformed

    private void ne_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ne_browseActionPerformed
        JFileChooser file = new JFileChooser(jFileChooser1.getCurrentDirectory());
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image*","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            // image.setIcon(ResizeImage(path,null));
            ne_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(ne_image.getWidth(), ne_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;
            //String path = selectedgetAbsolutePath();

        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_ne_browseActionPerformed

    private void trans_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trans_searchKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            translated.setText("");
            trans_mean.setText("");
            tran_noun.setText("");
            tran_verb.setText("");
            tran_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(tran_image.getWidth(), tran_image.getHeight(), Image.SCALE_DEFAULT)));
        }
    }//GEN-LAST:event_trans_searchKeyPressed

    private void up_word1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_up_word1KeyReleased
        try {
            String sql = "SELECT *"
                    + " FROM word WHERE "
                    + "Word like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + up_word1.getText() + "%");


            rs = (ResultSet) pst.executeQuery();
            word_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_up_word1KeyReleased

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
           Voice voice;
            VoiceManager vm = VoiceManager.getInstance();
            voice = vm.getVoice(VOICENAME);
            voice.allocate();
            
            if(lang1.getSelectedItem().equals("Filipino")){
            try{
             voice.speak(tran_pro.getText());
             }catch(Exception e){
             }
            }
            else{
            try{
             voice.speak(trans_search.getText());
             }catch(Exception e){
             }
            }     
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        
         Voice voice;
            VoiceManager vm = VoiceManager.getInstance();
            voice = vm.getVoice(VOICENAME);
            voice.allocate();
            
            if(lang2.getSelectedItem().equals("Filipino")){
            try{
             voice.speak(tran_pro.getText());
             }catch(Exception e){
             }
            }
            else{
            try{
             voice.speak(translated.getText());
             }catch(Exception e){
             }
            }   
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
          Voice voice;
            VoiceManager vm = VoiceManager.getInstance();
            voice = vm.getVoice(VOICENAME);
            voice.allocate();
            try{
      voice.speak(trans_mean.getText());
     }catch(Exception e){
     
     }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void inacc_fnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inacc_fnActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_inacc_fnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Main;
    private javax.swing.JLabel Verb;
    private com.toedter.calendar.JDateChooser acc_bd;
    private javax.swing.JTextField acc_fn;
    private javax.swing.JLabel acc_id;
    private javax.swing.JButton acc_inactive;
    private javax.swing.JComboBox<String> acc_lvl;
    private javax.swing.JTextField acc_pass;
    private javax.swing.JLabel acc_reason;
    private javax.swing.JButton acc_save;
    private javax.swing.JTable acc_table;
    private javax.swing.JButton acc_update;
    private javax.swing.JTextField acc_user;
    private javax.swing.JTextField acclog_search;
    private javax.swing.JTable acclog_table;
    private javax.swing.JPanel account;
    private javax.swing.JPanel bodycontent;
    private javax.swing.JPanel dashboard;
    private javax.swing.JPanel footer;
    private javax.swing.JTable freq_tbl;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hint;
    private javax.swing.JButton inacc_active;
    private com.toedter.calendar.JDateChooser inacc_bd;
    private javax.swing.JTextField inacc_fn;
    private javax.swing.JLabel inacc_id;
    private javax.swing.JComboBox<String> inacc_lvl;
    private javax.swing.JTextField inacc_pass;
    private javax.swing.JTextField inacc_res;
    private javax.swing.JTable inacc_table;
    private javax.swing.JTextField inacc_user;
    private javax.swing.JPanel info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> lang1;
    private javax.swing.JComboBox<String> lang2;
    private javax.swing.JPanel leftmenu;
    private javax.swing.JLabel men_acc;
    private javax.swing.JLabel men_dash;
    private javax.swing.JLabel men_ne;
    private javax.swing.JLabel men_trans;
    private javax.swing.JLabel men_update;
    private javax.swing.JPanel menu;
    private javax.swing.JButton ne_browse;
    private javax.swing.JButton ne_clear;
    private javax.swing.JTextField ne_fil;
    private javax.swing.JLabel ne_image;
    private javax.swing.JTextArea ne_mean;
    private javax.swing.JButton ne_new;
    private javax.swing.JTextField ne_noun;
    private javax.swing.JTextField ne_pro;
    private javax.swing.JButton ne_save;
    private javax.swing.JTextField ne_span;
    private javax.swing.JTextField ne_verb;
    private javax.swing.JTextField ne_word;
    private javax.swing.JPanel newentry;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel pnl_dash;
    private javax.swing.JLabel rc;
    private javax.swing.JPanel system;
    private javax.swing.JLabel tran_image;
    private javax.swing.JTextField tran_noun;
    private javax.swing.JLabel tran_pro;
    private javax.swing.JTextField tran_verb;
    private javax.swing.JTextArea trans_mean;
    private javax.swing.JTextField trans_search;
    private javax.swing.JTable trans_table;
    private javax.swing.JPanel translate;
    private javax.swing.JTextField translated;
    private javax.swing.JLabel txt_date;
    private javax.swing.JLabel txt_level;
    private javax.swing.JLabel txt_level1;
    private javax.swing.JLabel txt_level10;
    private javax.swing.JLabel txt_level11;
    private javax.swing.JLabel txt_level12;
    private javax.swing.JLabel txt_level13;
    private javax.swing.JLabel txt_level14;
    private javax.swing.JLabel txt_level15;
    private javax.swing.JLabel txt_level16;
    private javax.swing.JLabel txt_level17;
    private javax.swing.JLabel txt_level18;
    private javax.swing.JLabel txt_level19;
    private javax.swing.JLabel txt_level2;
    private javax.swing.JLabel txt_level20;
    private javax.swing.JLabel txt_level21;
    private javax.swing.JLabel txt_level22;
    private javax.swing.JLabel txt_level23;
    private javax.swing.JLabel txt_level24;
    private javax.swing.JLabel txt_level25;
    private javax.swing.JLabel txt_level26;
    private javax.swing.JLabel txt_level27;
    private javax.swing.JLabel txt_level28;
    private javax.swing.JLabel txt_level29;
    private javax.swing.JLabel txt_level3;
    private javax.swing.JLabel txt_level30;
    private javax.swing.JLabel txt_level31;
    private javax.swing.JLabel txt_level32;
    private javax.swing.JLabel txt_level33;
    private javax.swing.JLabel txt_level34;
    private javax.swing.JLabel txt_level4;
    private javax.swing.JLabel txt_level5;
    private javax.swing.JLabel txt_level6;
    private javax.swing.JLabel txt_level7;
    private javax.swing.JLabel txt_level8;
    private javax.swing.JLabel txt_level9;
    private javax.swing.JLabel txt_name;
    private javax.swing.JLabel txt_time;
    private javax.swing.JButton up_browse;
    private javax.swing.JButton up_clear;
    private javax.swing.JTextField up_fil;
    private javax.swing.JLabel up_id;
    private javax.swing.JLabel up_image;
    private javax.swing.JTextArea up_mean;
    private javax.swing.JButton up_new;
    private javax.swing.JTextField up_noun;
    private javax.swing.JTextField up_pro;
    private javax.swing.JTextField up_span;
    private javax.swing.JButton up_update;
    private javax.swing.JTextField up_verb;
    private javax.swing.JTextField up_word;
    private javax.swing.JTextField up_word1;
    private javax.swing.JPanel update;
    private javax.swing.JTextField user;
    private javax.swing.JTextField userlog_search;
    private javax.swing.JTable userlog_table;
    private javax.swing.JTable word_table;
    private javax.swing.JTable word_table1;
    private javax.swing.JLabel xxx;
    private javax.swing.JLabel yyy;
    private javax.swing.JLabel zzz;
    // End of variables declaration//GEN-END:variables
}
