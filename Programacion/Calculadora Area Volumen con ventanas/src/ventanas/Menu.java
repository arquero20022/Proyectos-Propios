
package ventanas;

import calculadoraareavolumen.cubo;
import javax.swing.ImageIcon;


public class Menu extends javax.swing.JFrame {
       
    
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon icono = new ImageIcon("src/imagenes/area.png");
        this.setIconImage(icono.getImage());
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaMenu = new javax.swing.JLabel();
        botonCuadrado = new javax.swing.JButton();
        botonCilindro = new javax.swing.JButton();
        botonEsfera = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaMenu.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        etiquetaMenu.setForeground(new java.awt.Color(0, 153, 51));
        etiquetaMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaMenu.setText("Calculadora Areas y Volumenes");
        etiquetaMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        botonCuadrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cubo.png"))); // NOI18N
        botonCuadrado.setBorderPainted(false);
        botonCuadrado.setContentAreaFilled(false);
        botonCuadrado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCuadrado.setFocusable(false);
        botonCuadrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCuadradoActionPerformed(evt);
            }
        });

        botonCilindro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cilindro.png"))); // NOI18N
        botonCilindro.setBorderPainted(false);
        botonCilindro.setContentAreaFilled(false);
        botonCilindro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCilindro.setFocusable(false);
        botonCilindro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCilindroActionPerformed(evt);
            }
        });

        botonEsfera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/esfera.png"))); // NOI18N
        botonEsfera.setBorderPainted(false);
        botonEsfera.setContentAreaFilled(false);
        botonEsfera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEsfera.setFocusable(false);
        botonEsfera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEsferaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(botonCuadrado)
                        .addGap(143, 143, 143)
                        .addComponent(botonCilindro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEsfera))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(etiquetaMenu)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(etiquetaMenu)
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCilindro)
                    .addComponent(botonEsfera, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonCuadrado))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonCuadradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCuadradoActionPerformed
       cuadradoFrame ventana = new cuadradoFrame();
       ventana.setVisible(true);
       this.setVisible(false);
       
       //codigo
       
       
       
    }//GEN-LAST:event_botonCuadradoActionPerformed

    private void botonCilindroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCilindroActionPerformed
   
       cilindroFrame ventana = new cilindroFrame();
       ventana.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_botonCilindroActionPerformed

    private void botonEsferaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEsferaActionPerformed
        // TODO add your handling code here:
        esferaFrame ventana = new esferaFrame();
       ventana.setVisible(true);
       this.setVisible(false);
    
    }//GEN-LAST:event_botonEsferaActionPerformed

    
 
    
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCilindro;
    private javax.swing.JButton botonCuadrado;
    private javax.swing.JButton botonEsfera;
    private javax.swing.JLabel etiquetaMenu;
    // End of variables declaration//GEN-END:variables
}
