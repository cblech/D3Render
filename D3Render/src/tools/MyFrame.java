/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import game.KeyMapper;
import graphic.RenderPannel;
import javax.swing.JButton;

/**
 *
 * @author jonathan
 */
public class MyFrame extends javax.swing.JFrame {

    public KeyMapper km = new KeyMapper();
    public Debugger db;
    RenderPannel rp = new RenderPannel(this);
    int width = 1600;
    int height = 800;
    final boolean editor;

    boolean debugging = true;

    public MyFrame(boolean editor) {
        this.editor = editor;
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
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        if (debugging) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    db = new Debugger();
                }
            });
        }
        initComponents();
        add(rp);
        setResizable(false);
        if (editor) {
            setSize(width + 80, height + 26);
            setLocationRelativeTo(null);
            rp.setBounds(80, 0, width, height);
            addEditorTools();
        } else {
            setSize(width, height + 26);
            setLocationRelativeTo(null);
            rp.setBounds(0, 0, width, height);
        }

        addKeyListener(km);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEditorTools() {
        edtAddPoint = new JButton("+ Point");
        add(edtAddPoint);
        edtAddPoint.setBounds(0, 0, 80, 40);
        
        edtMkLine = new JButton("+ Line");
        add(edtMkLine);
        edtMkLine.setBounds(0, 40, 80, 40);
        
        
    }

    JButton edtAddPoint;
    JButton edtMkLine;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
