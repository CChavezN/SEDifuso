/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sedifusov2;

import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author edani
 */
public class InterfazMatrizFAM extends javax.swing.JFrame {

    /**
     * Creates new form InterfazMatrizFAM
     */
    public InterfazMatrizFAM() {
        initComponents();
    }

        crear c=new crear();
    String [] ind, subconjuntos;
    int cont=-1;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Administrador de la matriz FAM");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crear", "Llenar", "Consultar", "Modificar" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("¿Que desea hacer?");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InterfazPrincipal mf = new InterfazPrincipal();
        mf.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jComboBox1.getSelectedIndex()==0)
        {
            dispose();
            crear c=new crear();
            c.setVisible(true);
        }
        
        if(jComboBox1.getSelectedIndex()==1)
        {
            try {
                RandomAccessFile indice=new RandomAccessFile("bh/indice", "r");
                for(int i=0; i<10;i++)
                {
                    indice.readChar();
                }
                long treg=indice.getFilePointer();
                int nreg=(int) (indice.length()/treg);
                System.out.println("Numero de registros del indice :"+nreg);
                this.ind=new String[nreg];
                this.subconjuntos= new String[nreg];
                String index;
                indice.seek(0);
                char temp;
                int c=0;
                while(indice.getFilePointer()<indice.length())
                {
                    index="";
                    for(int i=0; i<10;i++)
                    {
                        temp=indice.readChar();
                        if(temp!=0)
                        {
                        index+=temp;
                        }
                    }
                    this.ind[c]=index;
                    c++;
                }
                indice.close();
                llenar_matriz(1,2);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        if(jComboBox1.getSelectedIndex()==2)
        {
            String proyecto=JOptionPane.showInputDialog("¿Que valor tiene el proyecto?");
            String examen=JOptionPane.showInputDialog("¿Que valor tiene el examen?");
            String notas=JOptionPane.showInputDialog("¿Que valor tienen las notas?");
            String entusiasmo= JOptionPane.showInputDialog("Que valor tiene en el entusiasmo?");
            
            try {
                JOptionPane.showMessageDialog(null, "El resultado es: "+buscar(proyecto, examen, notas, entusiasmo));
            } catch (IOException ex) {
                System.out.println(ex.getMessage()+" error");
            }
        }
        
        if(jComboBox1.getSelectedIndex()==3)
        {
            String proyecto=JOptionPane.showInputDialog("¿Que valor tiene el proyecto?");
            String examen=JOptionPane.showInputDialog("¿Que valor tiene el examen?");
            String notas=JOptionPane.showInputDialog("¿Que valor tienen las notas?");
            String entusiasmo= JOptionPane.showInputDialog("¿Que valor tiene en el entusiasmo?");
            String nuevo= JOptionPane.showInputDialog("¿Que valor desea que tenga esa regla?");
            
            try {
                modificar(proyecto, examen, notas, entusiasmo, nuevo);
            } catch (IOException ex) {
                System.out.println(ex.getMessage()+" error");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public String buscar(String proy, String exa, String not, String entu) throws IOException
    {
        String resultado="";
        RandomAccessFile proyecto=new RandomAccessFile("bh/proyecto", "r");
        RandomAccessFile examen=new RandomAccessFile("bh/examen", "r");
        RandomAccessFile notas=new RandomAccessFile("bh/notas", "r");
        RandomAccessFile entusiasmo=new RandomAccessFile("bh/entusiasmo", "r");
        long treg=obtener_treg("proyecto");
        proyecto.seek(0);
        String variable;
        char temp;
        int min=0, max=2;
        while(proyecto.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=proyecto.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            min=proyecto.readInt();
            max=proyecto.readInt();
            for(int i=0; i<20;i++)
            {
                proyecto.readChar();
            }
            
            if(proy.equals(variable))
            {
                proyecto.seek(treg*max);
            }
        }
        
        
        examen.seek(treg*(min-1));
        while(examen.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=examen.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            min=examen.readInt();
            max=examen.readInt();
            for(int i=0; i<20;i++)
            {
                examen.readChar();
            }
            if(exa.equals(variable))
            {
                examen.seek(treg*max);
            }
        }
        
        notas.seek(treg*(min-1));
        while(notas.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=notas.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            min=notas.readInt();
            max=notas.readInt();
            for(int i=0; i<20;i++)
            {
                notas.readChar();
            }
            if(not.equals(variable))
            {
                notas.seek(treg*max);
            }
        }
        
        entusiasmo.seek(treg*(min-1));
        while(entusiasmo.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=entusiasmo.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            entusiasmo.readInt();
            entusiasmo.readInt();
            resultado="";
            for(int i=0; i<20;i++)
            {
               temp=entusiasmo.readChar();
               if(temp!=0)
               {
                   resultado+=temp;
               }
            }
            
            if(entu.equals(variable))
            {
                proyecto.seek(treg*max);
            }
        }
        proyecto.close();
        examen.close();
        notas.close();
        entusiasmo.close();
        return resultado;
    }
    
        public void modificar(String proy, String exa, String not, String entu, String nuevo) throws IOException
    {
        StringBuffer buffer=null;
        RandomAccessFile proyecto=new RandomAccessFile("bh/proyecto", "r");
        RandomAccessFile examen=new RandomAccessFile("bh/examen", "r");
        RandomAccessFile notas=new RandomAccessFile("bh/notas", "r");
        RandomAccessFile entusiasmo=new RandomAccessFile("bh/entusiasmo", "r");
        long treg=obtener_treg("proyecto");
        proyecto.seek(0);
        String variable;
        char temp;
        int min=0, max=2;
        while(proyecto.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=proyecto.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            min=proyecto.readInt();
            max=proyecto.readInt();
            for(int i=0; i<20;i++)
            {
                proyecto.readChar();
            }
            
            if(proy.equals(variable))
            {
                proyecto.seek(treg*max);
            }
        }
        
        
        examen.seek(treg*(min-1));
        while(examen.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=examen.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            min=examen.readInt();
            max=examen.readInt();
            for(int i=0; i<20;i++)
            {
                examen.readChar();
            }
            if(exa.equals(variable))
            {
                examen.seek(treg*max);
            }
        }
        
        notas.seek(treg*(min-1));
        while(notas.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=notas.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            min=notas.readInt();
            max=notas.readInt();
            for(int i=0; i<20;i++)
            {
                notas.readChar();
            }
            if(not.equals(variable))
            {
                notas.seek(treg*max);
            }
        }
        
        entusiasmo.seek(treg*(min-1));
        while(entusiasmo.getFilePointer()< treg*max)
        {
            variable="";
            for(int i=0; i<15;i++)
            {
                temp=entusiasmo.readChar();
                if(temp!=0)
                {
                    variable+=temp;
                }
            }
            entusiasmo.readInt();
            entusiasmo.readInt();
            buffer=new StringBuffer(nuevo);
            buffer.setLength(20);
            entusiasmo.writeChars(buffer.toString());
            if(entu.equals(variable))
            {
                entusiasmo.seek(treg*max);
            }
        }
        proyecto.close();
        examen.close();
        notas.close();
        entusiasmo.close();
    }
    
    public void llenar_matriz(int min, int max) throws IOException
    {
        cont++;
        StringBuffer buffer=null;
        RandomAccessFile archivo=new RandomAccessFile("bh/"+ind[cont], "rw");
        long treg=obtener_treg(ind[cont]);
        archivo.seek((min-1)*treg);
        long dmax=max*treg;
        char temp;
        while(archivo.getFilePointer()<dmax)
        {
            String sub="";
            for(int i=0; i<15; i++)
            {
                temp=archivo.readChar();
                if(temp!=0)
                {
                    sub+=temp;
                }
            }
            subconjuntos[cont]=sub;
            min=archivo.readInt();
            max=archivo.readInt();
            if(min==0 && max==0)
            {
                //Debe hacer la pregunta para llenar 
                String pregunta="Si en ";
                for(int i=0; i<ind.length; i++)
                {
                    pregunta+=ind[i]+" es "+subconjuntos[i]+" ";
                }
                String respuesta=JOptionPane.showInputDialog(pregunta);
                buffer=new StringBuffer(respuesta);
                buffer.setLength(20);
                archivo.writeChars(buffer.toString());
            }
            else
            {
                llenar_matriz(min, max);
                for(int i=0; i<20; i++)
                {
                    archivo.readChar();
                }
                cont--;
            }
        }   
    }
    
    
    
    
    public long obtener_treg(String nombre_archi) throws IOException
    {
        RandomAccessFile archivo=new RandomAccessFile("bh/"+nombre_archi, "r");
        long resultado=0;
        
        for(int i=0; i<15; i++)
        {
            archivo.readChar();
        }
        archivo.readInt();
        archivo.readInt();
        for(int i=0; i<20; i++)
        {
            archivo.readChar();
        }
        resultado=archivo.getFilePointer();
        archivo.close();
        return resultado;
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
            java.util.logging.Logger.getLogger(InterfazMatrizFAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazMatrizFAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazMatrizFAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazMatrizFAM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazMatrizFAM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
