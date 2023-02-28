/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BD.GestionProductoBD;
import BD.GestionUsuarioBD;
import BD.GestionVentasBD;
import entidades.Producto;
import entidades.Productos;
import entidades.Usuario;
import entidades.Usuarios;
import entidades.Venta;
import entidades.Ventas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author damm
 */
public class TPVGUI extends javax.swing.JFrame {

    GestionProductoBD conexionProductos;
    GestionUsuarioBD conexionUsuario;
    GestionVentasBD conexionVentas;
    Productos listadoProductos;
    Usuarios listadoUsuarios;
    Ventas ventas;
    DefaultListModel modeloJListProductos;
    DefaultListModel modeloJListUsuarios;
    DefaultListModel modeloJListVenta;
    Usuario user;
    Double precioProducto;
    Double precioTotal;
    /**
     * Creates new form TPVGUI
     *
     * @param usuario
     */
    public TPVGUI(Usuario usuario) {
        conexionProductos = new GestionProductoBD("localhost", "root", "", "tpv", 3306);
        conexionUsuario = new GestionUsuarioBD("localhost", "root", "", "tpv", 3306);
        conexionVentas = new GestionVentasBD("localhost", "root", "", "tpv", 3306);
        modeloJListProductos = new DefaultListModel();
        modeloJListUsuarios = new DefaultListModel();
        modeloJListVenta = new DefaultListModel();
        initComponents();
        listadoProductos = conexionProductos.listarProductos();
        listadoUsuarios = conexionUsuario.listarUsuarios();
        ventas = conexionVentas.listarTodasVentas();
        cargarProductos();
        cargarProductosAdmin();
        cargarUsuarios();
        user = usuario;
        
        //Si el usuario no es admin bloquea la ventana "Administración"
        if (usuario.getRol().equals("vendedor")) {
            this.jTabbedPane1.setEnabledAt(1, false);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        TabTPV = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProductosTPV = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        btn7 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btnComa = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnBorrarProd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        TabAdmin = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listUsuarios = new javax.swing.JList<>();
        btnAñadirUsu = new javax.swing.JButton();
        btnModificarUsu = new javax.swing.JButton();
        btnEliminarUsu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listProductos = new javax.swing.JList<>();
        btnAñadirProd = new javax.swing.JButton();
        btnModificarProd = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listProductosTPV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listProductosTPV.setModel(this.modeloJListVenta);
        jScrollPane1.setViewportView(listProductosTPV);

        btn7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn7.setText("7");

        btn1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn1.setText("1");

        btnComa.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnComa.setText(",");

        btn8.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn8.setText("8");

        btn4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn4.setText("4");

        btn9.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn9.setText("9");

        btnTerminar.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnTerminar.setText("+");

        btn5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn5.setText("5");

        btn6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn6.setText("6");

        btn2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn2.setText("2");

        btn3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn3.setText("3");

        btn0.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn0.setText("0");

        btnC.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnC.setText("C");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnBorrarProd.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnBorrarProd.setText("-");
        btnBorrarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnComa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnComa, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));
        jPanel2.add(jScrollPaneProductos);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("TOTAL:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout TabTPVLayout = new javax.swing.GroupLayout(TabTPV);
        TabTPV.setLayout(TabTPVLayout);
        TabTPVLayout.setHorizontalGroup(
            TabTPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabTPVLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(TabTPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TabTPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1))
                    .addGroup(TabTPVLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TabTPVLayout.setVerticalGroup(
            TabTPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabTPVLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(TabTPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TabTPVLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabTPVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TPV", TabTPV);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        listUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listUsuarios.setModel(this.modeloJListUsuarios);
        jScrollPane2.setViewportView(listUsuarios);

        btnAñadirUsu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAñadirUsu.setText("Añadir");
        btnAñadirUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirUsuActionPerformed(evt);
            }
        });

        btnModificarUsu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnModificarUsu.setText("Modificar");
        btnModificarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUsuActionPerformed(evt);
            }
        });

        btnEliminarUsu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEliminarUsu.setText("Eliminar");
        btnEliminarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnAñadirUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnModificarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnEliminarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnAñadirUsu)
                .addGap(41, 41, 41)
                .addComponent(btnModificarUsu)
                .addGap(36, 36, 36)
                .addComponent(btnEliminarUsu)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        listProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listProductos.setModel(this.modeloJListProductos);
        jScrollPane3.setViewportView(listProductos);

        btnAñadirProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAñadirProd.setText("Añadir");
        btnAñadirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirProdActionPerformed(evt);
            }
        });

        btnModificarProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnModificarProd.setText("Modificar");
        btnModificarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProdActionPerformed(evt);
            }
        });

        btnEliminarProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEliminarProd.setText("Eliminar");
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadirProd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(123, 123, 123))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnAñadirProd)
                .addGap(40, 40, 40)
                .addComponent(btnModificarProd)
                .addGap(36, 36, 36)
                .addComponent(btnEliminarProd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabAdminLayout = new javax.swing.GroupLayout(TabAdmin);
        TabAdmin.setLayout(TabAdminLayout);
        TabAdminLayout.setHorizontalGroup(
            TabAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabAdminLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        TabAdminLayout.setVerticalGroup(
            TabAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Administración", TabAdmin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUsuActionPerformed
        if (this.listUsuarios.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Error, hay que seleccionar un usuario");
        } else {
            int pos = this.listUsuarios.getSelectedIndex();
            ModificarUsuario modificarUsuario = new ModificarUsuario(this, true, listadoUsuarios.getUsuario(pos));
            modificarUsuario.setVisible(true);
            cargarUsuarios();
        }
    }//GEN-LAST:event_btnModificarUsuActionPerformed

    private void btnAñadirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirProdActionPerformed
        AñadirProducto añadirProducto = new AñadirProducto(this, true);
        añadirProducto.setVisible(true);
        cargarProductos();
        cargarProductosAdmin();
    }//GEN-LAST:event_btnAñadirProdActionPerformed

    private void btnAñadirUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirUsuActionPerformed
        AñadirUsuario añadirUsuario = new AñadirUsuario(this, true);
        añadirUsuario.setVisible(true);
        cargarUsuarios();
    }//GEN-LAST:event_btnAñadirUsuActionPerformed

    private void btnModificarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProdActionPerformed
        if (this.listProductos.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Error, hay que seleccionar un usuario");
        } else {
            int pos = this.listProductos.getSelectedIndex();
            ModificarProducto modificarProducto = new ModificarProducto(this, true, listadoProductos.getProducto(pos));
            modificarProducto.setVisible(true);
            cargarProductos();
            cargarProductosAdmin();
        }
    }//GEN-LAST:event_btnModificarProdActionPerformed

    private void btnEliminarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuActionPerformed
        if (this.listUsuarios.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Error, hay que seleccionar un usuario");
        } else {
            int pos = this.listUsuarios.getSelectedIndex();
            if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                    "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                conexionUsuario.borrarUsuario(listadoUsuarios.getUsuario(pos));
                modeloJListUsuarios.remove(pos);
            }
        }
    }//GEN-LAST:event_btnEliminarUsuActionPerformed

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        if (this.listProductos.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Error, hay que seleccionar un producto");
        } else {
            int pos = this.listProductos.getSelectedIndex();
            if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                    "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                conexionProductos.borrarProducto(listadoProductos.getProducto(pos));
                modeloJListProductos.remove(pos);
            }
        }
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        this.modeloJListVenta.removeAllElements();
        this.txtTotal.setText("");
    }//GEN-LAST:event_btnCActionPerformed

    private void btnBorrarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarProdActionPerformed
        if (this.listProductosTPV.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Error, hay que seleccionar un producto");
        } else {
            int pos = this.listProductosTPV.getSelectedIndex();
                modeloJListVenta.remove(pos);
                precioTotal -= precioProducto;
                txtTotal.setText(String.valueOf(precioTotal));
            }
    }//GEN-LAST:event_btnBorrarProdActionPerformed

    private void cargarProductos() {
        Productos productos = conexionProductos.listarProductos();
        int x = 10;
        int y = 10;
        int total = 130;

        this.jScrollPaneProductos.removeAll();
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.getProducto(i);
            JButton boton = new JButton(producto.getNombre());
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Venta venta = new Venta(producto, user);
                    modeloJListVenta.addElement(producto.getNombre() + " Cantidad: ");
                    precioProducto = producto.getPvp();
                    precioTotal += producto.getPvp();
                    txtTotal.setText(String.valueOf(precioTotal));
                }
            });
            boton.setBounds(x, y, 100, 100);
            boton.setText("");
            boton.setVisible(true);
            boton.setIcon(conexionProductos.getFotoProducto(producto.getIdProducto()));
            this.jScrollPaneProductos.add(boton);
            JLabel nombre = new JLabel(producto.getNombre());
            nombre.setText(producto.getNombre());
            nombre.setBounds(x, y + 100, 100, 15);
            nombre.setVisible(true);
            nombre.setHorizontalAlignment(SwingConstants.CENTER);
            nombre.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.jScrollPaneProductos.add(nombre);
            if (x < 300) {
                x = x + 100;
            } else {
                x = 10;
                y = y + 115;
                total = total + 130;
            }
        }
        jScrollPaneProductos.setPreferredSize(new Dimension(400, total));
        jScrollPaneProductos.updateUI();
    }

    private void cargarProductosAdmin() {
        //Recoger datos de listadoDptos y cargarlos en modeloJListDptos
        for (int i = 0; i < listadoProductos.size(); i++) {
            modeloJListProductos.addElement("Producto: " + listadoProductos.getProducto(i).getNombre() + " | Cantidad: " + listadoProductos.getProducto(i).getStock());
        }
    }

    private void cargarUsuarios() {
        //Recoger datos de listadoDptos y cargarlos en modeloJListDptos
        for (int i = 0; i < listadoUsuarios.size(); i++) {
            modeloJListUsuarios.addElement("Usuario: " + listadoUsuarios.getUsuario(i).getNombreUsuario() + " | Rol: " + listadoUsuarios.getUsuario(i).getRol());
        }
    }

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
            java.util.logging.Logger.getLogger(TPVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TPVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TPVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TPVGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TPVGUI(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TabAdmin;
    private javax.swing.JPanel TabTPV;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAñadirProd;
    private javax.swing.JButton btnAñadirUsu;
    private javax.swing.JButton btnBorrarProd;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnComa;
    private javax.swing.JButton btnEliminarProd;
    private javax.swing.JButton btnEliminarUsu;
    private javax.swing.JButton btnModificarProd;
    private javax.swing.JButton btnModificarUsu;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> listProductos;
    private javax.swing.JList<String> listProductosTPV;
    private javax.swing.JList<String> listUsuarios;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
