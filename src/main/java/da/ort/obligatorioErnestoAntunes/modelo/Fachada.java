package da.ort.obligatorioErnestoAntunes.modelo;

import java.time.LocalDate;
import java.util.List;

import da.ort.obligatorioErnestoAntunes.dto.ParticipacionDTO;
import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.CaballoNoValidoException;
import da.ort.obligatorioErnestoAntunes.excepciones.CarreraNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.JornadaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ModalidadNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;
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
        
        public void logout(String nombreCompleto) throws UsuarioInvalidoException{
            sistemaUsuarios.logout(nombreCompleto);
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

        public List<Carrera> getCarrerasDisponibles(){
            return sistemaJornadas.getCarrerasDisponibles();
        }

        public List<Modalidad> getModalidades() {
            return sistemaApuestas.getModalidades();
        }

        public List<Apuesta> getTodasLasApuestas() {
            return sistemaJornadas.getTodasLasApuestas();
        }

        public List<Apuesta> getApuestasPorJugador(String jugador){
            return sistemaJornadas.getApuestasPorJugador(jugador);
        }

        public List<Carrera> getCarrerasFinalizadasPorJornada(LocalDate fecha) throws JornadaNoValidaException{
            return sistemaJornadas.getCarrerasFinalizadasPorJornada(fecha);
        }

        public List<Carrera> getCarrerasDisponiblesPorJornada(LocalDate fecha) throws JornadaNoValidaException{
            return sistemaJornadas.getCarrerasDisponiblesPorJornada(fecha);
        }        

        public int getCantCarrerasJornada(LocalDate fecha) throws JornadaNoValidaException {
            return sistemaJornadas.getCantCarrerasJornada(fecha);
        }

        public void abrirCarrera(int id) throws CarreraNoValidaException{
            sistemaJornadas.abrirCarrera(id);
        }

        public Carrera buscarCarrera(int id) throws CarreraNoValidaException{
            return sistemaJornadas.buscarCarrera(id);
        }

        public void cerrarCarrera(int id) throws CarreraNoValidaException{
            sistemaJornadas.cerrarCarrera(id);
        }

        public void finalizarCarrera(int id, int nroRegistroPart) throws ParticipacionNoValidaException, CarreraNoValidaException {
            sistemaJornadas.finalizarCarrera(id, nroRegistroPart); 
        }

        public double getTotalApostadoPorJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
            return sistemaJornadas.getTotalApostadoPorJornada(fechaJornada);
        }

        public double getTotalPagadoPorJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
            return sistemaJornadas.getTotalPagadoPorJornada(fechaJornada);
        }

        public double getBalanceJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
            return sistemaJornadas.getBalanceJornada(fechaJornada);
        }

        public double getTotalComisionJornada(LocalDate fechaJornada) throws JornadaNoValidaException {
            return sistemaJornadas.getTotalComisionJornada(fechaJornada);
        }

        public double getTotalApostadoPorJugador(String nombreCompleto) {
            return sistemaJornadas.getTotalApostadoPorJugador(nombreCompleto);
        }

        public double getTotalGanadoPorJugador(String nombreCompleto) {
            return sistemaJornadas.getTotalGanadoPorJugador(nombreCompleto);
        }

        public Apuesta crearApuesta(double valorApuesta, String nombreCompleto, int nroRegistro, String modalidad, int idCarrera) throws UsuarioInvalidoException, ParticipacionNoValidaException, ModalidadNoValidaException, ApuestaNoValidaException, CarreraNoValidaException {
            Jugador jugador = (Jugador)buscarUsuario(nombreCompleto);
            Participacion part = buscarParticipacion(nroRegistro, idCarrera);
            Carrera carrera = buscarCarrera(idCarrera);
            return sistemaApuestas.crearApuesta(valorApuesta, jugador, part, modalidad, carrera);
        }

        public Usuario buscarUsuario(String nombre) throws UsuarioInvalidoException {
            return sistemaUsuarios.buscarUsuario(nombre);
        }

        public boolean buscarPassword(String pass, String nombreCompleto) throws UsuarioInvalidoException {
            return sistemaUsuarios.buscarPassword(pass, nombreCompleto);
        }

        public Apuesta buscarApuesta(int idApuesta) throws ApuestaNoValidaException{
            return sistemaJornadas.buscarApuesta(idApuesta);
        }

        public double getPremioSiEsGanadora(int idCarrera, int idApuesta) throws CarreraNoValidaException, ApuestaNoValidaException {
            Carrera c = buscarCarrera(idCarrera);
            Apuesta a = buscarApuesta(idApuesta);
            return sistemaApuestas.getPremioSiEsGanadora(c, a);
        }

        public boolean montoValido(double valorApuesta, String nombreCompleto) throws UsuarioInvalidoException {
            return sistemaUsuarios.montoValido(valorApuesta, nombreCompleto);
        }

        public Jugador buscarJugador(String nombreCompleto) throws UsuarioInvalidoException {
            return sistemaUsuarios.buscarJugador(nombreCompleto);
        }

        public Participacion buscarParticipacion(int nro, int idCarrera) throws ParticipacionNoValidaException, CarreraNoValidaException {
            Carrera c = buscarCarrera(idCarrera);
            return sistemaJornadas.buscarParticipacion(nro, c);
        }
        
        
        

        

}
