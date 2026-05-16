package da.ort.obligatorioErnestoAntunes.datosPrueba;

import java.time.LocalDate;

import da.ort.obligatorioErnestoAntunes.excepciones.ApuestaNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.ParticipacionNoValidaException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioExistenteException;
import da.ort.obligatorioErnestoAntunes.excepciones.UsuarioInvalidoException;
import da.ort.obligatorioErnestoAntunes.modelo.Administrador;
import da.ort.obligatorioErnestoAntunes.modelo.Apuesta;
import da.ort.obligatorioErnestoAntunes.modelo.Caballo;
import da.ort.obligatorioErnestoAntunes.modelo.Carrera;
import da.ort.obligatorioErnestoAntunes.modelo.Fachada;
import da.ort.obligatorioErnestoAntunes.modelo.Jornada;
import da.ort.obligatorioErnestoAntunes.modelo.Jugador;
import da.ort.obligatorioErnestoAntunes.modelo.Modalidad;
import da.ort.obligatorioErnestoAntunes.modelo.Participacion;
import da.ort.obligatorioErnestoAntunes.modelo.Simple;
import da.ort.obligatorioErnestoAntunes.modelo.Super;
import da.ort.obligatorioErnestoAntunes.modelo.Triple;

import java.time.LocalDate;
import java.util.Random;

public class DatosPrueba {

    private static Fachada f = Fachada.getInstancia();

    // JUGADORES
    private static Jugador jugador1;
    private static Jugador jugador2;
    private static Jugador jugador3;
    private static Jugador jugador4;
    private static Jugador jugador5;
    private static Jugador jugador6;
    private static Jugador jugador7;

    // MODALIDADES
    private static Modalidad simple;
    private static Modalidad triple;
    private static Modalidad superApuesta;

    // CABALLOS
    private static Caballo c1;
    private static Caballo c2;
    private static Caballo c3;
    private static Caballo c4;
    private static Caballo c5;
    private static Caballo c6;

    // JORNADAS
    private static Jornada jornadaHoy;
    private static Jornada jornadaPasada;
    private static Jornada jornadaFutura;

    public static void cargar() {

        try {

            cargarAdministradores();
            cargarJugadores();

            cargarModalidades();

            cargarCaballos();

            cargarJornadas();

            cargarCarrerasHoy();

            cargarCarrerasPasadas();

            cargarCarreraFutura();

            System.out.println("Datos cargados correctamente");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //------------------------------------------
    // ADMINISTRADORES
    //------------------------------------------

    private static void cargarAdministradores()
            throws  UsuarioInvalidoException, UsuarioExistenteException {

        Administrador admin1 =
                new Administrador(
                        "a1",
                        "a1",
                        "Usuario Administrador"
                );

        Administrador admin2 =
                new Administrador(
                        "admin2",
                        "admin2",
                        "Administrador 2"
                );

        f.agregarUsuario(admin1);
        f.agregarUsuario(admin2);
    }

    //------------------------------------------
    // JUGADORES
    //------------------------------------------

    private static void cargarJugadores()
            throws UsuarioInvalidoException, UsuarioExistenteException {

        jugador1 =
                new Jugador(
                        "j1",
                        "j1",
                        "Usuario Jugador",
                        2000
                );

        jugador2 =
                new Jugador(
                        "juan",
                        "123",
                        "Juan Perez",
                        50000
                );

        jugador3 =
                new Jugador(
                        "ana",
                        "123",
                        "Ana Lopez",
                        60000
                );

        jugador4 =
                new Jugador(
                        "pedro",
                        "123",
                        "Pedro Silva",
                        70000
                );

        jugador5 =
                new Jugador(
                        "lucia",
                        "123",
                        "Lucia Gomez",
                        80000
                );

        jugador6 =
                new Jugador(
                        "martin",
                        "123",
                        "Martin Diaz",
                        90000
                );

        jugador7 =
                new Jugador(
                        "sofia",
                        "123",
                        "Sofia Pereira",
                        100000
                );

        f.agregarUsuario(jugador1);
        f.agregarUsuario(jugador2);
        f.agregarUsuario(jugador3);
        f.agregarUsuario(jugador4);
        f.agregarUsuario(jugador5);
        f.agregarUsuario(jugador6);
        f.agregarUsuario(jugador7);
    }

    //------------------------------------------
    // MODALIDADES
    //------------------------------------------

    private static void cargarModalidades()
            throws Exception {

        simple = new Simple();

        triple = new Triple();

        superApuesta = new Super();

        f.agregarModalidad(simple);
        f.agregarModalidad(triple);
        f.agregarModalidad(superApuesta);
    }

    //------------------------------------------
    // CABALLOS
    //------------------------------------------

    private static void cargarCaballos()
            throws Exception {

        c1 = new Caballo("Relampago");
        c2 = new Caballo("Trueno");
        c3 = new Caballo("Pegaso");
        c4 = new Caballo("Titan");
        c5 = new Caballo("Huracan");
        c6 = new Caballo("Centella");

        f.agregarCaballos(c1);
        f.agregarCaballos(c2);
        f.agregarCaballos(c3);
        f.agregarCaballos(c4);
        f.agregarCaballos(c5);
        f.agregarCaballos(c6);
    }

    //------------------------------------------
    // JORNADAS
    //------------------------------------------

    private static void cargarJornadas()
            throws Exception {

        jornadaHoy =
                new Jornada(LocalDate.now());

        jornadaPasada =
                new Jornada(
                        LocalDate.now().minusWeeks(1)
                );

        jornadaFutura =
                new Jornada(
                        LocalDate.now().plusWeeks(1)
                );

        f.agregarJornada(jornadaHoy);
        f.agregarJornada(jornadaPasada);
        f.agregarJornada(jornadaFutura);
    }

    //------------------------------------------
    // 3 CARRERAS HOY
    //------------------------------------------

    private static void cargarCarrerasHoy()
            throws Exception {

        Carrera carrera1 =
                new Carrera(
                        1,
                        "Clasico Primavera",
                        LocalDate.now()
                );

        Carrera carrera2 =
                new Carrera(
                        2,
                        "Gran Derby",
                        LocalDate.now()
                );

        Carrera carrera3 =
                new Carrera(
                        3,
                        "Copa Hipodromo",
                        LocalDate.now()
                );

        agregarParticipantes(
                carrera1,
                c1,
                c2,
                c3
        );

        agregarParticipantes(
                carrera2,
                c3,
                c4,
                c5
        );

        agregarParticipantes(
                carrera3,
                c1,
                c5,
                c6
        );

        f.agregarCarrera(carrera1);
        f.agregarCarrera(carrera2);
        f.agregarCarrera(carrera3);
    }

    //------------------------------------------
    // CARRERAS PASADAS
    //------------------------------------------

    private static void cargarCarrerasPasadas()
            throws Exception {

        Carrera carrera4 =
                new Carrera(
                        1,
                        "Clasico Otono",
                        LocalDate.now().minusWeeks(1)
                );

        Carrera carrera5 =
                new Carrera(
                        2,
                        "Copa Invierno",
                        LocalDate.now().minusWeeks(1)
                );

        agregarParticipantes(
                carrera4,
                c1,
                c2,
                c3
        );

        agregarParticipantes(
                carrera5,
                c4,
                c5,
                c6
        );

        carrera4.abrir();
        carrera5.abrir();

        generarApuestas(carrera4);

        generarApuestas(carrera5);

        carrera4.cerrar();
        carrera5.cerrar();

        f.agregarCarrera(carrera4);
        f.agregarCarrera(carrera5);
    }

    //------------------------------------------
    // CARRERA FUTURA
    //------------------------------------------

    private static void cargarCarreraFutura()
            throws Exception {

        Carrera carrera6 =
                new Carrera(
                        1,
                        "Copa Futuro",
                        LocalDate.now().plusWeeks(1)
                );

        agregarParticipantes(
                carrera6,
                c1,
                c4,
                c6
        );

        f.agregarCarrera(carrera6);
    }

    //------------------------------------------
    // PARTICIPANTES
    //------------------------------------------

    private static void agregarParticipantes(
            Carrera carrera,
            Caballo... caballos
    ) throws ParticipacionNoValidaException{

        int numero = 1;

        for (Caballo caballo : caballos) {

            Participacion p =
                    new Participacion(
                            numero,
                            caballo
                    );

            carrera.agregarParticipacion(p);

            numero++;
        }
    }

    //------------------------------------------
    // APUESTAS RANDOM
    //------------------------------------------

    private static void generarApuestas(
            Carrera carrera
    ) throws ApuestaNoValidaException{

        Random random = new Random();

        Jugador[] jugadores = {
                jugador1,
                jugador2,
                jugador3,
                jugador4,
                jugador5,
                jugador6,
                jugador7
        };

        Modalidad[] modalidades = {
                simple,
                triple,
                superApuesta
        };

        for (Participacion p :
                carrera.getParticipaciones()) {

            int cantidad =
                    random.nextInt(11) + 10;

            for (int i = 0; i < cantidad; i++) {

                Jugador jugador =
                        jugadores[
                                random.nextInt(
                                        jugadores.length
                                )
                        ];

                Modalidad modalidad =
                        modalidades[
                                random.nextInt(
                                        modalidades.length
                                )
                        ];

                double monto =
                        (random.nextInt(20) + 1)
                                * 1000;

                Apuesta apuesta =
                        new Apuesta(
                                monto,
                                jugador,
                                p,
                                modalidad
                        );

                carrera.agregarApuesta(apuesta);
            }
        }

        carrera.recalcularDividendos();
    }
}
