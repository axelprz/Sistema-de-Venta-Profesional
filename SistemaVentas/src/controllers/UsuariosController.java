
package controllers;

import Views.PanelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Usuarios;
import models.UsuariosDAO;

public class UsuariosController implements ActionListener{

    private Usuarios us;
    private UsuariosDAO usDao;
    private PanelAdmin views;

    public UsuariosController(Usuarios us, UsuariosDAO usDao, PanelAdmin views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.btnRegistrarUser.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnRegistrarUser){
            if(views.txtUsuarioUser.getText().equals("") 
                    || views.txtNombreUser.getText().equals("")
                    || String.valueOf(views.txtClaveUser.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                us.setUsuario(views.txtUsuarioUser.getText());
                us.setNombre(views.txtNombreUser.getText());
                us.setClave(String.valueOf(views.txtClaveUser.getPassword()));
                us.setCaja(views.cbCajaUser.getSelectedItem().toString());
                us.setRol(views.cbRolUser.getSelectedItem().toString());
                if(usDao.registrar(us)){
                    JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario");
                }
            }
        }
    }
    
}
