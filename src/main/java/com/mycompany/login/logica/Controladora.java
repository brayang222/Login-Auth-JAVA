package com.mycompany.login.logica;

import com.mycompany.login.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }
    
    public Usuario validarUsuario(String usuario, String contra) {
        
        //String mensaje = "";
        Usuario user = null;
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            if (usu.getNombreUsuario().equals(usuario)){
                if (usu.getContra().equals(contra)){
                   // return mensaje = "Usuario y contraseña correcta. Bienvenido";
                   user = usu;
                   return user;
                } else {
                   // return mensaje = "Contraseña incorrecta";
                   user = null;
                   return user;
                }
            } else {
                // mensaje = "Usuario no encontrado";
                 user = null;
                 // no se retorna porque cerrarria la busqueda return user;
            }
        }
        return user;
       // return mensaje;
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }

    public void crearUsuario(String user, String contra, String rol) {
        Usuario usu = new Usuario();
        usu.setNombreUsuario(user);
        usu.setContra(contra);
        
        Rol rolSelected = new Rol();
        rolSelected = this.traerRol(rol);
        
        if(rol != null) {
           usu.setUnRol(rolSelected);
        }
        
        int id = busacarIdUsuario();
        usu.setId(id + 1);
        
        controlPersis.crearUsuario(usu);
    }

    private Rol traerRol(String rol) {
        List<Rol> listaRoles = controlPersis.traerRoles();
        
        for (Rol rolEncontrado:listaRoles) {
            if (rolEncontrado.getNombreRol().equals(rol)) {
                return rolEncontrado;
            } 
        }
        return null;
    }

    private int busacarIdUsuario() {
        List<Usuario> listaUsuarios = this.traerUsuarios();
        
        Usuario usu = listaUsuarios.get(listaUsuarios.size()-1);
        return usu.getId();
    }

    public void borrarUsuario(int idUsuario) {
        controlPersis.borrarUsuario(idUsuario);
    }

    public Usuario traerUsuario(int idUsuario) {
        return controlPersis.traerUsuario(idUsuario);
    }

    public void editarUsuario(Usuario usu, String user, String contra, String rol) {
        usu.setNombreUsuario(user);
        usu.setContra(contra);
        
        Rol rolSelected = new Rol();
        rolSelected = this.traerRol(rol);
        
        if(rol != null) {
           usu.setUnRol(rolSelected);
        }
        
        controlPersis.editarUsuario(usu);
    }
    
}
