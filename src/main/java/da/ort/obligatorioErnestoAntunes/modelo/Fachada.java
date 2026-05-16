package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioExistenteException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class Fachada {
    private static Fachada instancia;

        private SistemaApuestas sistemaApuestas;
        private SistemaJornadas sistemaJornadas;
        private SistemaJugadores sistemaJugadores;
        private SistemaLogin sistemaLogin;

        private Fachada() {
            this.sistemaApuestas = new SistemaApuestas(0.10); //comision del hipodromo 10%
            this.sistemaJornadas = new SistemaJornadas();
            this.sistemaJugadores = new SistemaJugadores();
            this.sistemaLogin = new SistemaLogin();
        }

        public static Fachada getInstancia() {
            if (instancia == null) {
                instancia = new Fachada();
            }
            return instancia;
        }

        public Jugador loginJugador(String nombre, String pass) throws UsuarioInvalidoException {
            return sistemaLogin.loginJugador(nombre, pass);
        }

        public Administrador loginAdmin(String nombre, String pass) throws UsuarioInvalidoException {
            return sistemaLogin.loginAdmin(nombre, pass);
        }

        public void cerrarSesion() {
            sistemaLogin.cerrarSesion();
        }

        public void agregar(Usuario u) throws UsuarioInvalidoException, UsuarioExistenteException {
            sistemaLogin.agregar(u);
        }

        

}
