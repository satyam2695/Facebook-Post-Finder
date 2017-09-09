package webcrawler;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class crawler extends javax.swing.JFrame {
    public String s;
    public String inputword;
    public Document d;
    public boolean test=false;
    public crawler() {
        initComponents();
    }
Url_generator g; 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Abhijeetk\\Desktop\\webcrawler\\WEBCRAWLER.gif")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setText("Enter URL :");

        jTextField1.setText("www.thapar.edu");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Crawl !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("Search For Any Keyword :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton4.setText("SEARCH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jTextField2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         s=jTextField1.getText();
         try {
            crawler();
            //System.out.print(s);
        } catch (Exception ex) {
            Logger.getLogger(crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
       
        
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         inputword=jTextField2.getText();
         if(test==false)
             jLabel4.setText("Crawl First");
         else if(searchword())
         {
             String c="Word Found Matching";
             //jTextArea1.append(c);
             jLabel4.setText(c);
            // jTextArea1.append("\n");
             //updateTextArea(c);
         }
            // System.out.println("Word Found Matching");
             else
         {
             String d="Not found";
             jLabel4.setText(d);
             //jTextArea1.append("\n");
            // updateTextArea(d);
         }
    }//GEN-LAST:event_jButton4ActionPerformed
public void crawler()
{
             String url=geturl();
             Searcher s=new Searcher();
             Filterdomain domain=new Filterdomain(Searcher.hash(url),url);
             Domain dom=new Domain (domain.getDhash(),domain.getDurl(),domain);
             s.hash(url);
            HashSet<String> tags=new HashSet<String>();
           try
           {
              d = Jsoup.connect(dom.getDurl()).get();
                     Page_html webpage=new Page_html(d.html(),dom,new Date());
              Frontpage page=new Frontpage("",dom,new Date());
               Elements reference = d.select("a");
            for (Element iter:reference)
            {   String temp=iter.attr("href");
                temp=Handler_html.Url(temp,domain);
                //updateTextArea(temp);
                tags.add(temp);
        //      System.out.println(temp);
             }
        } catch (IOException ex) {
            Logger.getLogger(Url_generator.class.getName()).log(Level.SEVERE, null, ex);
        }
           catch (Exception ex) {
                     Logger.getLogger(Url_generator.class.getName()).log(Level.SEVERE, null, ex);
                 }
        for (String  itr:tags ){
             /*for(int c=0;c<5;c++)
             {
               Vector<Object> table = new Vector<Object>();
             table.add(itr);
             model.addRow(table);  
             }*/
             
             //System.out.println(10);
             
               //updateTextArea("A");
             jTextArea1.append(itr);
             jTextArea1.append("\n");
             //System.out.println(itr);
         }
         //System.out.println("Number of pages visited :"+tags.size());
         String a="Number of pages visited :"+tags.size();
         if(tags.size()>0)
             test=true;
         jTextArea1.append(a);
             //jTextArea1.append("\n");
         //updateTextArea(a);
        // updateTextArea("\n");
         //System.out.println("----------------------------------------------------");
         jTextArea1.append("\n----------------------------------------------------\n");
         //System.out.println("Want to search for the word? Press y /n");
        // String b="Want to search for the word? Press y /n";
         //jTextArea1.append(b);
             //jTextArea1.append("\n");
         //updateTextArea(b);
          /* Scanner read=new Scanner(System.in);
         String check=read.nextLine();
         if(check.equals("y"))
         { inputword=read.nextLine();  
         if(searchword())
         {
             String c="Word Found Matching";
             jTextArea1.append(c);
            // jTextArea1.append("\n");
             //updateTextArea(c);
         }
            // System.out.println("Word Found Matching");
             else
         {
             String d="Not found";
             jTextArea1.append(d);
             //jTextArea1.append("\n");
            // updateTextArea(d);
         }
             //System.out.println("Not found");
         } 
         if (check=="n")
             return;*/
      
}
public boolean searchword()
    { 
        // Defensive coding
        if(this.d == null)
        {
            //System.out.println("ERROR! Crawl first");
            //String w="ERROR! Crawl first";
            //jLabel4.setText(w);
            //jTextArea1.append(w);
            // jTextArea1.append("\n");
            //updateTextArea(w);
            return false;
        }
        else
   
        {
        //System.out.println(" Searching word " + inputword+ "...");
            //String r=" Searching word " + inputword+ "...";
            //jTextArea1.append(r);
             //jTextArea1.append("\n");
            //updateTextArea(r);
        String body = this.d.body().text();
        return body.toUpperCase().contains(inputword.toUpperCase());
    }
        }
    public  String geturl()
    {
     /*   Scanner read=new Scanner(System.in);
       String inputurl=read.nextLine();*/
             //.String  inputurl="http://www.jsoup.org"; 
        
        String inputurl=s;
        //System.out.print(c.s);
             if (inputurl.startsWith("www"))
                 inputurl="http://"+inputurl;
              return inputurl;
    }
    public void updateTextArea(final String text) {
  SwingUtilities.invokeLater(new Runnable() {
    public void run() {
      jTextArea1.append(text);
    }
  });
}
 
private void redirectSystemStreams() {
  OutputStream out = new OutputStream() {
    @Override
    public void write(int b) throws IOException {
      updateTextArea(String.valueOf((char) b));
    }
 
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
      updateTextArea(new String(b, off, len));
    }
 
    @Override
    public void write(byte[] b) throws IOException {
      write(b, 0, b.length);
    }
  };
 
  System.setOut(new PrintStream(out, true));
  System.setErr(new PrintStream(out, true));
}
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
            java.util.logging.Logger.getLogger(crawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crawler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crawler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}