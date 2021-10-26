/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Prova.java
 *
 * Created on 20-apr-2010, 21.06.55
 */
package game.graphics;

import game.Ground;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author paolo
 */
public class Frame extends javax.swing.JFrame {

    private Image[] imgTasselli;
    private Image imgRisolta;
    private boolean sceltaImage;
    private JLabel[][] tasselli;
    private Ground ground;

    private class Mouse extends MouseAdapter {

        private int i;
        private int j;

        public Mouse(int pI, int pJ) {
            this.i = pI;
            this.j = pJ;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ground.sposta(this.i, this.j);
            ridisegna();
            if (ground.isWinner()) {
                JOptionPane.showMessageDialog(rootPane, "Hai vinto!!!");
                removeMouseListenerToLabel();
            }
        }
    }

    /** Creates new form Prova */
    public Frame() {
        initComponents();
        this.ground = new Ground();
        this.initLabel();
        this.sceltaImage = false;
    }

    private void initLabel() {
        this.tasselli = new JLabel[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                this.tasselli[i][j] = new JLabel();
                this.tasselli[i][j].setBorder(new LineBorder(Color.BLACK));
                this.jPanel1.add(this.tasselli[i][j]);
            }
        }
    }

    private void addMouseListenerToLabel() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tasselli[i][j].addMouseListener(new Mouse(i, j));
            }
        }
    }

    private void removeMouseListenerToLabel() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tasselli[i][j].removeMouseListener(this.tasselli[i][j].getMouseListeners()[0]);
            }
        }
    }

    @SuppressWarnings("static-access")
    private void cutImage(boolean pDefault) {
        String path = "/game/graphics/images/def.png";
        int retval = jFileChooser1.APPROVE_OPTION - 1;
        if (!pDefault) {
            retval = this.jFileChooser1.showOpenDialog(this);
            if (retval == jFileChooser1.APPROVE_OPTION) {
                path = this.jFileChooser1.getSelectedFile().getAbsolutePath();
            }
        }
        String[] ext={"png","gif","jpeg","jpg","bmp"};
        FileNameExtensionFilter filtro= new FileNameExtensionFilter(null, ext);
        if(!filtro.accept(this.jFileChooser1.getSelectedFile())){
            pDefault=true;
            path="/game/graphics/images/def.png";
        }
        this.imgTasselli = new Image[16];
        int cont = 0;
        Image img = null;
        if (pDefault) {
            img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
        } else {
            img = Toolkit.getDefaultToolkit().getImage(path);
        }
        ReplicateScaleFilter scala = new ReplicateScaleFilter(300, 300);
        FilteredImageSource fis = new FilteredImageSource(img.getSource(), scala);
        img = createImage(fis);
        this.imgRisolta=img;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                CropImageFilter cutting = new CropImageFilter(j * 75, i * 75, 75, 75);
                fis = new FilteredImageSource(img.getSource(), cutting);
                this.imgTasselli[cont] = createImage(fis);
                cont++;
            }
        }
        if (retval == this.jFileChooser1.APPROVE_OPTION) {
            this.sceltaImage = true;
            JOptionPane.showMessageDialog(rootPane, "Immagine selezionata, premere nuovo gioco per iniziare a giocare!");
        }

    }

    public void ridisegna() {
        int[][] tavolo = ground.toMatrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tavolo[i][j] == 16) {
                    this.tasselli[i][j].setIcon(new ImageIcon(getClass().getResource("/game/graphics/images/vuoto.png")));
                } else {
                    this.tasselli[i][j].setIcon(new ImageIcon(this.imgTasselli[(tavolo[i][j]) - 1]));
                }
            }
        }
    }

    public void newGame() {
        this.ground.shuffle();
        this.ridisegna();
        this.addMouseListenerToLabel();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gioco del 15 - by Paolo Ricciuti");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(224, 14, 14));
        jPanel1.setLayout(new java.awt.GridLayout(4, 4));

        jPanel2.setBackground(new java.awt.Color(224, 14, 14));
        jPanel2.setLayout(null);

        jButton2.setText("Seleziona immagine");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(10, 10, 170, 30);

        jButton1.setText("Nuovo gioco");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(185, 10, 120, 30);

        jButton3.setText("Guarda l'immagine risolta");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(10, 50, 290, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.cutImage(false);
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!this.sceltaImage) {
            this.cutImage(true);
        }
        this.jButton3.setEnabled(true);
        this.newGame();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LookImg look= new LookImg(this.imgRisolta);
        Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
        int x=(screen.width-look.getSize().width)/2;
        int y=(screen.height-look.getSize().height)/2;
        look.setLocation(x, y);
        look.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Frame f=new Frame();
                Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
                int x=(screen.width-f.getSize().width)/2;
                int y=(screen.height-f.getSize().height)/2;
                f.setLocation(x, y);
                f.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
