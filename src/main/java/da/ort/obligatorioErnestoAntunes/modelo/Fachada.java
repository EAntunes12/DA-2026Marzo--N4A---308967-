package da.ort.obligatorioErnestoAntunes.modelo;

import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioExistenteException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;

public class Fachada {
    private static Fachada instancia;

        private SistemaApuestas sistemaApuestas;
        private SistemaJornadas sistemaJornadas;
        private SistemaUsuarios sistemaUsuarios;

        private Fachada() {
            this.sistemaApuestas = new SistemaApuestas(0.10); //comision del hipodromo 10%
            this.sistemaJornadas = new SistemaJornadas();
            this.sistemaUsuarios = new SistemaUsuarios();
        }

        public static Fachada getInstancia() {
            if (instancia == null) {
                instancia = new Fachada();
            }
            return instancia;
        }

        public Jugador loginJugador(String nombre, String pass) throws UsuarioInvalidoException {
            return sistemaUsuarios.loginJugador(nombre, pass);
        }

        public Administrador loginAdmin(String nombre, String pass) throws UsuarioInvalidoException {
            return sistemaUsuarios.loginAdmin(nombre, pass);
        }

        public void cerrarSesion() {
            sistemaUsuarios.cerrarSesion();
        }

        public void agregarUsuario(Usuario u) throws UsuarioInvalidoException, UsuarioExistenteException {
            sistemaUsuarios.agregarUsuario(u);
        }

        public void agregarModalidad(Modalidad m) throws ModalidadNoValidaException {
            sistemaApuestas.agregarModalidad(m);
        }

        public void agregarCaballos(Caballo c) throws CaballoNoValidoException {
            sistemaApuestas.agregarCaballos(c);
        }

        public void agregarJornada(Jornada j) throws JornadaNoValidaException {
            sistemaJornadas.agregarJornada(j);
        }

        public void agregarCarrera(Carrera c) throws CarreraNoValidaException {
            sistemaJornadas.agregarCarrera(c);
        }

        

}
