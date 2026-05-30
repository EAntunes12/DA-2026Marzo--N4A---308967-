package da.ort.obligatorioErnestoAntunes.modelo;

import java.util.List;

import da.ort.obligatorioErnestoAntunes.dto.ApuestaDTO;
import da.ort.obligatorioErnestoAntunes.dto.CarreraDTO;
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

        public double getComision(){
            return sistemaApuestas.getComision();
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
        
        public void logout(String nombre) throws UsuarioInvalidoException{
            sistemaUsuarios.logout(nombre);
        }

        public Jornada obtenerJornadaActual() {
            return sistemaJornadas.obtenerJornadaActual();
        }

        public Jornada siguienteJornada(Jornada actual) {
            return sistemaJornadas.siguienteJornada(actual);
        }

        public Jornada anteriorJornada(Jornada actual) {
            return sistemaJornadas.anteriorJornada(actual);
        }

        public List<Carrera> getCarrerasDisponibles() {
            return sistemaJornadas.getCarrerasDisponibles();
        }

        public List<Modalidad> getModalidades() {
            return sistemaApuestas.getModalidades();
        }

        public List<Apuesta> getTodasLasApuestas() {
            return sistemaJornadas.getTodasLasApuestas();
        }

        public List<Apuesta> getApuestasPorJugador(String jugador) {
            return sistemaJornadas.getApuestasPorJugador(jugador);
        }
          

        

}
