/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author MdMahadiHasan
 */
public class CropImage2 extends javax.swing.JFrame {

    /**
     * Creates new form CropImage2
     */
    final JFileChooser fc;
    int drag_status = 0, c1,c2,c3, c4;
    int w, h;
    String filepath = "C:\\Users\\MdMahadiHasan\\Pictures\\Screenshots\\Screenshot (1).png";

    public CropImage2() {
        initComponents();

        fc = new JFileChooser();

        addB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int returnVal = fc.showOpenDialog(CropImage2.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        addImageToJPanel(file);
                    } catch (IOException ex) {
                        Logger.getLogger(CropImage2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                }

            }

        });
        saveB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                String filename = JOptionPane.showInputDialog("Enter File Name.");
                if (filename == null) {
                    System.out.println("The user canceled");
                }else{
                    try {
                        createCropImage(c1, c2, w, h,filename);
                    } catch (AWTException ex) {
                        Logger.getLogger(CropImage2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(CropImage2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void addImageToJPanel(File file) throws IOException {
        setSize(1000, 700);
        setVisible(true);
        Image image = ImageIO.read(file);
        Image scaledImage = image.getScaledInstance(jPanel1.getWidth(), jPanel1.getHeight(), Image.SCALE_SMOOTH);
        ImagePanel im = new ImagePanel(scaledImage);
        jPanel1.add(im);
        jPanel1.setSize(700, 700);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                repaint();
                c1 = arg0.getXOnScreen();
                c2 = arg0.getYOnScreen();
            }
            @Override
            public void mouseReleased(MouseEvent arg0) {
                repaint();
                if (drag_status == 1) {
                    c3 = arg0.getXOnScreen();
                    c4 = arg0.getYOnScreen();
                    try {
                        draggedScreen();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            

            @Override
            public void mouseDragged(MouseEvent arg0) {
                repaint();
                drag_status = 1;
                c3 = arg0.getXOnScreen();
                c4 = arg0.getYOnScreen();
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {

            }
        });
    }
    
    public void draggedScreen() throws Exception {
        w = c1 - c3;
        h = c2 - c4;
        w = w * -1;
        h = h * -1;
        
        //createCropImage(c1,c2, w, h);
        
    }
    
    public void createCropImage(int c1,int c2,int w,int h,String newFileanme) throws AWTException, IOException{
        Robot robot = new Robot();
        BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2, w, h));
        File save_path = new File(newFileanme+".bmp");
        ImageIO.write(img, "JPG", save_path);
        System.out.println("Cropped image saved successfully.");
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        int w = c1 - c3;
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        if (w < 0) {
            w = w * -1;
        }
        g.drawRect(c1, c2, w, h);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addB = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        saveB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addB.setText("Add");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        saveB.setText("Save");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addB)
                .addGap(254, 254, 254)
                .addComponent(saveB)
                .addContainerGap(358, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addB)
                    .addComponent(saveB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CropImage2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CropImage2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CropImage2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CropImage2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CropImage2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addB;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton saveB;
    // End of variables declaration//GEN-END:variables
}
