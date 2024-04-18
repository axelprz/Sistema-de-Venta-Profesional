
package controllers;

import Views.FrmLogin;
import Views.PanelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Usuarios;
import models.UsuariosDAO;

public class LoginControllers implements ActionListener{
    private Usuarios us;
    private UsuariosDAO usDao;
    private FrmLogin views;

    public LoginControllers(Usuarios us, UsuariosDAO usDao, FrmLogin views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.btnLogin.addActionListener(this);
        this.views.btnSalir.addActionListener(this);
        this.views.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnLogin){
            if(views.txtUsuario.getText().equals("") || String.valueOf(views.txtClave.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos");
            }else{
                String usuario = views.txtUsuario.getText();
                String clave = String.valueOf(views.txtClave.getPassword());
                us = usDao.login(usuario, clave);
                if(us.getUsuario() != null){
                    PanelAdmin admin = new PanelAdmin();
                    admin.setVisible(true);
                    this.views.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrecta");
                }
            }
        }else{
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir","Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(pregunta == 0){
                System.exit(0);
            }
        }
        
    }
    
}
