package da.ort.obligatorioErnestoAntunes.modelo;

import java.util.ArrayList;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioExistenteException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class SistemaUsuarios {

    List<Usuario> usuarios = new ArrayList<>();

    private Usuario login(String nombre, String pass) throws UsuarioInvalidoException{
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre) && u.getPassword().equals(pass)) {
                if(u.isLogueado()){
                    throw new UsuarioInvalidoException("El usuario ya esta logueado");
                }
                u.setLogueado(true);
                return u;
            }
        }
        throw new UsuarioInvalidoException("Usuario o contraseña incorrectos.");
    }

    public Jugador loginJugador(String nombre, String pass) throws UsuarioInvalidoException{
        Usuario u = login(nombre, pass);

        if(!(u instanceof Jugador)){
            throw new UsuarioInvalidoException("El usuario no es un jugador");
        }

        return (Jugador) u;
    }

    public Administrador loginAdmin(String nombre, String pass) throws UsuarioInvalidoException{
        Usuario u = login(nombre, pass);

        if(!(u instanceof Administrador)){
            throw new UsuarioInvalidoException("El usuario no es un administrador");
        }

        return (Administrador) u;
    }

    public void cerrarSesion() {
    }

    private void existeUsuario(Usuario u) throws UsuarioExistenteException{
        for(Usuario user : usuarios){
            if(user.getNombre().equals(u.getNombre())){
                throw new UsuarioExistenteException("El usuario ya existe.");
            }
        }
    }     
        
    public void agregarUsuario(Usuario u) throws UsuarioInvalidoException, UsuarioExistenteException{
        if(u==null){ 
            throw new UsuarioInvalidoException("El usuario es null.");
        }

        u.validar();
        existeUsuario(u);

        usuarios.add(u);
    }

    public void logout(String nombreCompleto) throws UsuarioInvalidoException {
        Usuario usuario = buscarUsuario(nombreCompleto);
        // if (usuario == null) {
        //     throw new UsuarioInvalidoException("El usuario no existe.");
        // }
        if (!usuario.isLogueado()) {
            throw new UsuarioInvalidoException("El usuario no está logueado.");
        }
        usuario.setLogueado(false);
    }

    public Usuario buscarUsuario(String nombreCompleto) throws UsuarioInvalidoException{
        for(Usuario u : this.usuarios){
            if(u.getNombreCompleto().equals(nombreCompleto)){
                return u;
            }
        }

        throw new UsuarioInvalidoException("El usuario no existe.");
    }

    public boolean buscarPassword(String pass, String nombreCompleto) throws UsuarioInvalidoException {
        Jugador jugador = (Jugador) buscarUsuario(nombreCompleto);
        return jugador.getPassword().equals(pass);
    }

    public boolean montoValido(double valorApuesta, String nombreCompleto) throws UsuarioInvalidoException {
        Jugador j = (Jugador) buscarUsuario(nombreCompleto);
        if(j.getSaldo() >= valorApuesta){
            return true;
        }
        throw new UsuarioInvalidoException("El monto ingresado es mayor al saldo en la cuenta.");
    }

    public Jugador buscarJugador(String nombreCompleto) throws UsuarioInvalidoException{
        for(Usuario u : this.usuarios){
            if(u instanceof Jugador && u.getNombreCompleto().equals(nombreCompleto)){
                return (Jugador)u;
            }
        }

        throw new UsuarioInvalidoException("El usuario no existe.");
    }
}
