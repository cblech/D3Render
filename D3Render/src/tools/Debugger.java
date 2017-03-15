package tools;

/**
 *
 * @author luebeck.jonathan
 */
public class Debugger extends javax.swing.JFrame {

//    JLabel jLabel1;
//    JLabel jLabel2;
//    JLabel jLabel3;
//    JLabel jLabel4;
//    
//    JLabel lblFps;
//    JLabel lblJump;
//    JLabel lblRight;
//    JLabel lblLeft;
    /**
     * Creates new form Debugger
     */
    public Debugger() {
        startup();

//        jLabel1 = new JLabel();
//        lblFps = new JLabel();
//        
//        
//        jLabel1.setText("FPS: ");
//        getContentPane().add(jLabel1);
//        jLabel2.setText("Jump: ");
//        getContentPane().add(jLabel2);
//        jLabel3.setText("Right: ");
//        getContentPane().add(jLabel3);
//        jLabel4.setText("Left: ");
//        getContentPane().add(jLabel4);
//
//        lblFps.setText("jLabel2");
//        getContentPane().add(lblFps);
//        lblJump.setText("jLabel2");
//        getContentPane().add(lblJump);
//        lblRight.setText("jLabel2");
//        getContentPane().add(lblRight);
//        lblLeft.setText("jLabel2");
//        getContentPane().add(lblLeft);

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblFps = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblJump = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblLeft = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRight = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFocusable(false);
        getContentPane().setLayout(new java.awt.GridLayout(4, 2, 20, 20));

        jLabel1.setText("FPS: ");
        getContentPane().add(jLabel1);

        lblFps.setText("jLabel5");
        getContentPane().add(lblFps);

        jLabel2.setText("Jump:");
        getContentPane().add(jLabel2);

        lblJump.setText("jLabel10");
        getContentPane().add(lblJump);

        jLabel3.setText("Left:");
        getContentPane().add(jLabel3);

        lblLeft.setText("jLabel11");
        getContentPane().add(lblLeft);

        jLabel4.setText("Right:");
        getContentPane().add(jLabel4);

        lblRight.setText("jLabel12");
        getContentPane().add(lblRight);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void startup() {
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
            java.util.logging.Logger.getLogger(Debugger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Debugger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Debugger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Debugger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Display the form */
        setVisible(true);
    }

    public void setFps(int i) {
        if (lblFps != null) {
            lblFps.setText(String.valueOf(i));
        }
    }
    public void setJump(boolean b) {
        if (lblJump != null) {
            lblJump.setText(String.valueOf(b));
        }
    }
    public void setRight(boolean b) {
        if (lblRight != null) {
            lblRight.setText(String.valueOf(b));
        }
    }
    public void setLeft(boolean b) {
        if (lblLeft != null) {
            lblLeft.setText(String.valueOf(b));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblFps;
    private javax.swing.JLabel lblJump;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblRight;
    // End of variables declaration//GEN-END:variables
}
