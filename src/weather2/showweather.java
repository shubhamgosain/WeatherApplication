
package weather2;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.json.JSONObject;
import sun.swing.ImageIconUIResource;





    
    

    
    
    







public class showweather extends javax.swing.JFrame {
String city;
String urlink;
 static ImageIcon backgr;
   final String DEGREE  = "\u00b0";
   
 static JSONObject obj;
 
 void background(String sky){
switch(sky)
    {
        case "Clouds": backgr=new ImageIcon(getClass().getResource("1130837.jpg"));
                        jLabel1.setIcon(backgr);
                        break;
        case "Rain":  backgr=new ImageIcon(getClass().getResource("rain-wallpaper-5.jpg"));
                        jLabel1.setIcon(backgr);
                        break;
         case "Mist": backgr=new ImageIcon(getClass().getResource("road-forest-mist-1440x900.jpg"));
                        jLabel1.setIcon(backgr);
                        break;   
         case "Haze": backgr=new ImageIcon(getClass().getResource("haze_fog_road_light_stuff_1440x900_hd-wallpaper-248462.jpg"));
                        jLabel1.setIcon(backgr);
                        break;                         
        
        default:break;
    }
     
}
 String icon(String sky)
 {
  String a="";
switch(sky)
    {
        case "scattered clouds":a="http://icons.iconarchive.com/icons/icons-land/weather/256/Snow-Occasional-icon.png"; 
                       break;
       
        case "few clouds": a="http://www.alro2ya.com/files/images/RO2YA-65708-2.png";
                        break;                
        
        case "broken clouds": a="http://icons.iconarchive.com/icons/icons-land/weather/256/Snow-Occasional-icon.png"; 
                       break;
        case "overcast clouds":  a="http://www.freeiconspng.com/uploads/weather-icon-png-26.png\n";
                        break;
         case "light rain":  a="https://cdn3.iconfinder.com/data/icons/3d-glossy/512/weather-256.png";
                        break;
        case "heavy rain":  a="https://cdn3.iconfinder.com/data/icons/3d-glossy/512/weather-256.png";
                        break;
      /*  case "heavy rain":  a="";
                        break;case "heavy rain":  a="";
                        break;case "heavy rain":  a="";
                        break;case "heavy rain":  a="";
                        break;
        */ 
         case "Mist": a="data:image/jpeg;base64,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";
                        break;   
         case "haze": a="http://www.fancyicons.com/free-icons/157/android-weather/png/256/haze_256.png";
         break;
        
        default:break;
    }
return a;
 }

 
 
 
    public showweather() {
        initComponents();
    try {
        geocoding("Delhi");
       
        JSONObject res=obj.getJSONObject("main");
    
    
    Templabel.setText(res.getInt("temp")+DEGREE+"C");
    maxtemplabel.setText(res.getInt("temp_max")+DEGREE+"C");
   
    mintemplabel.setText(res.getInt("temp_min")+DEGREE+"C");
     pressure.setText(res.getInt("pressure")+"");
    humid.setText(res.getInt("humidity")+"");
   
    res=obj.getJSONObject("sys"); 
jLabel5.setText(obj.getString("name")+" , "+res.getString("country"));
 
       
    res=obj.getJSONArray("weather").getJSONObject(0);
    String sky=res.getString("main");
        background(sky);
 
   /* switch(sky)
    {
        case "Clouds": backgr=new ImageIcon(getClass().getResource("1130837.jpg"));
                        jLabel1.setIcon(backgr);
                        break;
        case "Rain":  backgr=new ImageIcon(getClass().getResource("rain-wallpaper-5.jpg"));
                        jLabel1.setIcon(backgr);
                        break;
         case "Mist": backgr=new ImageIcon(getClass().getResource("road-forest-mist-1440x900.jpg"));
                        jLabel1.setIcon(backgr);
                        break;   
         case "Haze": backgr=new ImageIcon(getClass().getResource("haze_fog_road_light_stuff_1440x900_hd-wallpaper-248462.jpg"));
                        jLabel1.setIcon(backgr);
                        break;                         
        
        default:break;
    }
    */
    
    skylabel.setText(res.getString("description"));
    
      URL iconlink1=new URL(icon(skylabel.getText()));
     Image image=ImageIO.read(iconlink1);
jLabel2.setVisible(true);
 jLabel2.setIcon(new ImageIconUIResource(image));
    } catch (Exception ex) {
        Logger.getLogger(showweather.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label9 = new javax.swing.JLabel();
        Templabel = new javax.swing.JLabel();
        searchcity = new javax.swing.JTextField();
        label7 = new javax.swing.JLabel();
        mintemplabel = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        maxtemplabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        skylabel = new javax.swing.JLabel();
        label10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        humid = new javax.swing.JLabel();
        pressure = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/weather2/search-png-white-search-icon.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 60, 60));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 256, 256));
        jLabel2.setVisible(false);

        label9.setFont(new java.awt.Font("Adequate", 1, 24)); // NOI18N
        label9.setForeground(new java.awt.Color(255, 255, 255));
        label9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label9.setText("Pressure");
        getContentPane().add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, 150, 40));

        Templabel.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 30)); // NOI18N
        Templabel.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(Templabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 170, 50));

        searchcity.setFont(new java.awt.Font("Adequate", 1, 20)); // NOI18N
        searchcity.setOpaque(false);
        searchcity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcityActionPerformed(evt);
            }
        });
        getContentPane().add(searchcity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 310, 50));

        label7.setFont(new java.awt.Font("Adequate", 1, 18)); // NOI18N
        label7.setForeground(new java.awt.Color(255, 255, 255));
        label7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label7.setText("Min Temp");
        getContentPane().add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, 120, 40));

        mintemplabel.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        mintemplabel.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(mintemplabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 100, 40));

        label5.setFont(new java.awt.Font("Adequate", 1, 18)); // NOI18N
        label5.setForeground(new java.awt.Color(255, 255, 255));
        label5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label5.setText("Max Temp");
        getContentPane().add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 130, 30));

        maxtemplabel.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        maxtemplabel.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(maxtemplabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, 100, 40));

        jLabel5.setFont(new java.awt.Font("Adequate", 1, 42)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 480, 100));

        skylabel.setFont(new java.awt.Font("Adequate", 1, 26)); // NOI18N
        skylabel.setForeground(new java.awt.Color(255, 255, 255));
        skylabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(skylabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 440, 70));

        label10.setFont(new java.awt.Font("Adequate", 1, 24)); // NOI18N
        label10.setForeground(new java.awt.Color(255, 255, 255));
        label10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label10.setText("Humidity");
        getContentPane().add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, 140, 40));

        jLabel7.setFont(new java.awt.Font("Adequate", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Temperature");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 220, 40));

        humid.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        humid.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(humid, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, 100, 40));

        pressure.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        pressure.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pressure, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 600, 100, 40));

        jLabel1.setFont(new java.awt.Font("Adequate", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/weather2/Dark-Winter-Rain-and-Snow-Fall-Wallpaper.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 890));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static void geocoding(String addr) throws Exception
{
    // build a URL
    
    String s = "http://api.openweathermap.org/data/2.5/weather?q="+addr+"&units=metric&APPID=0e948b3ecd877b785634d8ebdd15621e";
   // s += URLEncoder.encode(addr, "UTF-8");
    URL url = new URL(s);
 
    // read from the URL
    Scanner scan = new Scanner(url.openStream());
    String str = new String();
    while (scan.hasNext())
        str += scan.nextLine();
    scan.close();
 
     obj = new JSONObject(str);
    //if (! obj.getString("status").equals("OK"))   return;
 
    // get the first result
  /*  JSONObject res=obj.getJSONObject("city");
    System.out.println(res.getString("name"));
     res = obj.getJSONArray("list").getJSONObject(0);
    JSONObject a=res.getJSONArray("weather").getJSONObject(0);
    
    System.out.println(a.getString("main")+"***********"+a.getString("description"));
    */
 
    //JSONObject loc =res.getJSONObject("geometry").getJSONObject("location");
    //System.out.println("lat: " + loc.getDouble("lat") +", lng: " + loc.getDouble("lng"));
}


    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
    try {
        city=searchcity.getText();
        geocoding(city);
        

//the data to print 

        JSONObject res=obj.getJSONObject("main");
    
    
    Templabel.setText(res.getInt("temp")+DEGREE+"C");
    maxtemplabel.setText(res.getInt("temp_max")+DEGREE+"C");
    pressure.setText(res.getInt("pressure")+"");
    humid.setText(res.getInt("humidity")+"");
    mintemplabel.setText(res.getInt("temp_min")+DEGREE+"C");
    res=obj.getJSONObject("sys"); 
jLabel5.setText(obj.getString("name")+" , "+res.getString("country"));
 
       
    res=obj.getJSONArray("weather").getJSONObject(0);
    String sky=res.getString("main");
   
       background(sky);
 
    
    
    skylabel.setText(res.getString("description"));
       URL iconlink1=new URL(icon(skylabel.getText()));
     Image image=ImageIO.read(iconlink1);
jLabel2.setVisible(true);
 jLabel2.setIcon(new ImageIconUIResource(image));
     

       
    }catch(Exception ex) {
        Logger.getLogger(showweather.class.getName()).log(Level.SEVERE, null, ex);
 

       
    }
    }//GEN-LAST:event_jLabel3MousePressed

    private void searchcityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchcityActionPerformed

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
            java.util.logging.Logger.getLogger(showweather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showweather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showweather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showweather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new showweather().setVisible(true);
           
                
    
    
    

            
            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Templabel;
    private javax.swing.JLabel humid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel maxtemplabel;
    private javax.swing.JLabel mintemplabel;
    private javax.swing.JLabel pressure;
    private javax.swing.JTextField searchcity;
    private javax.swing.JLabel skylabel;
    // End of variables declaration//GEN-END:variables
}
