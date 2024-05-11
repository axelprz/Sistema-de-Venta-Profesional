
package controllers;

import Views.PanelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Clientes;
import models.ClientesDAO;

public class ClientesController implements ActionListener{
    private Clientes cl;
    private ClientesDAO clDao;
    private PanelAdmin views;

    public ClientesController(Clientes cl, ClientesDAO clDao, PanelAdmin views) {
        this.cl = cl;
        this.clDao = clDao;
        this.views = views;
        this.views.btnRegistrarCliente.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == views.btnRegistrarCliente){
            if(views.txtNombreCliente.getText().equals("")
               || views.txtTelefonoCliente.getText().equals("")
               ||views.txtDireccionCliente.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                cl.setNombre(views.txtNombreCliente.getText());
                cl.setTelefono(views.txtTelefonoCliente.getText());
                cl.setDireccion(views.txtDireccionCliente.getText());
                if(clDao.registrar(cl)){
                    JOptionPane.showMessageDialog(null, "Cliente registrado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar");
                }
            }
        }
    }
    
}
