package com.mycompany.login.logica;

import com.mycompany.login.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }
    
    public String validarUsuario(String usuario, String contra) {
        
        String mensaje = "";
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            if (usu.getNombreUsuario().equals(usuario)){
                if (usu.getContra().equals(contra)){
                    return mensaje = "Usuario y contraseña correcta. Bienvenido";
                } else {
                    return mensaje = "Contraseña incorrecta";
                }
            } else {
                 mensaje = "Usuario no encontrado";
            }
        }
        
        return mensaje;
    }
    
}
