
package controllers;

import Views.PanelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Usuarios;
import models.UsuariosDAO;

public class UsuariosController implements ActionListener, MouseListener{

    private Usuarios us;
    private UsuariosDAO usDao;
    private PanelAdmin views;
    
    DefaultTableModel modelo = new DefaultTableModel();

    public UsuariosController(Usuarios us, UsuariosDAO usDao, PanelAdmin views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.btnRegistrarUser.addActionListener(this);
        this.views.TableUser.addMouseListener(this);
        listarUsuarios();
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
    public void listarUsuarios(){
        List<Usuarios> lista = usDao.ListaUsuarios();
        modelo = (DefaultTableModel) views.TableUser.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getUsuario();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getCaja();
            ob[4] = lista.get(i).getRol();
            ob[5] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableUser.setModel(modelo);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableUser){
            int fila = views.TableUser.rowAtPoint(e.getPoint());
            views.txtIdUser.setText(views.TableUser.getValueAt(fila, 0).toString());
            views.txtUsuarioUser.setText(views.TableUser.getValueAt(fila, 1).toString());
            views.txtNombreUser.setText(views.TableUser.getValueAt(fila, 2).toString());
            views.cbCajaUser.setSelectedItem(views.TableUser.getValueAt(fila, 3).toString());
            views.cbRolUser.setSelectedItem(views.TableUser.getValueAt(fila, 4).toString());
            views.txtClaveUser.setEnabled(false);
            views.btnRegistrarUser.setEnabled(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
