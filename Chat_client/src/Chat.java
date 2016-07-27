
import javax.swing.JTextPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nayra
 */
public class Chat extends javax.swing.JFrame {

    private User user;

    public Chat(User user) {
        this();
        this.user = user;
    }

    /**
     * Creates new form Chat
     */
    public Chat() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        enviarButton = new javax.swing.JToggleButton();
        privateMessageButton = new javax.swing.JButton();
        historyButton = new javax.swing.JButton();
        UsersButton = new javax.swing.JButton();
        historyButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formCloseAction(evt);
            }
        });

        textPane.setEditable(false);
        jScrollPane1.setViewportView(textPane);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        privateMessageButton.setText("Messagem Privada");
        privateMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateMessageButtonActionPerformed(evt);
            }
        });

        historyButton.setText("Histórico");
        historyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyButtonActionPerformed(evt);
            }
        });

        UsersButton.setText("Usuários");
        UsersButton.setPreferredSize(new java.awt.Dimension(97, 30));
        UsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersButtonAction(evt);
            }
        });

        historyButton1.setText("Limpar");
        historyButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(UsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(privateMessageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(historyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(historyButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enviarButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(privateMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        this.user.sendMessageToServer(this.textArea.getText());
        this.textArea.setText("");
    }//GEN-LAST:event_enviarButtonActionPerformed
    private void formCloseAction(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formCloseAction
        this.user.disconnectServer();
    }//GEN-LAST:event_formCloseAction

    private void privateMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privateMessageButtonActionPerformed
        this.textArea.setText(R.PRIVATE_MESSAGE.getValue() + R.CARACTER.getValue());
    }//GEN-LAST:event_privateMessageButtonActionPerformed

    private void usersButtonAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersButtonAction
        this.textArea.setText(R.SHOW_USERS.getValue() + R.CARACTER.getValue());
    }//GEN-LAST:event_usersButtonAction

    private void historyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyButtonActionPerformed
        this.textArea.setText(R.HISTORY.getValue() + R.CARACTER.getValue());
    }//GEN-LAST:event_historyButtonActionPerformed

    private void historyButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyButton1ActionPerformed
        this.textPane.setText("");
    }//GEN-LAST:event_historyButton1ActionPerformed

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UsersButton;
    private javax.swing.JToggleButton enviarButton;
    private javax.swing.JButton historyButton;
    private javax.swing.JButton historyButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton privateMessageButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
