package smartdb;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tiago
 */
public class MaquinaDAO {

    private static JdbcTemplate jdbcTemplate;

    private static void initConnection() {
        if (jdbcTemplate == null) {
            Connection.setVariablesToSqlServer(
                    "jdbc:sqlserver://smartmonkeymonitoring.database.windows.net:1433;database=SmartMonkey;user=admsmart@smartmonkeymonitoring;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                    "admsmart",
                    "MonkeysBusiness02"
            );
            jdbcTemplate = Connection.getJdbcTemplate();
        }
    }

    public static List<Maquina> getAll() {
        initConnection();

        try {
            List<Maquina> lista = jdbcTemplate.query(
                    "SELECT * FROM Maquina",
                    new BeanPropertyRowMapper(Maquina.class)
            );
            return lista;
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
            return null;
        }
    }

    public static Integer getAvaliableId() {
        initConnection();

        try {
            int idAvaliable = jdbcTemplate.queryForObject(
                    "SELECT ISNULL(MAX(idMaquina) + 1, 0) FROM Maquina",
                    Integer.class
            );
            return idAvaliable;
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
            return null;
        }
    }

    public static Maquina get(int idProcurado) {
        initConnection();

        try {
            Maquina registro = jdbcTemplate.queryForObject(
                    "SELECT * FROM Maquina where idMaquina = ?",
                    new BeanPropertyRowMapper<Maquina>(Maquina.class),
                    idProcurado
            );

            return registro;

        } catch (Exception e) {
            System.out.println("Erro de query SQL");
            return null;
        }
    }

    public static void add(Maquina novoRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "SET IDENTITY_INSERT Maquina ON \n"
                    + "INSERT INTO Maquina (idMaquina, modelo, processador, memoriaRam, discoRigido, delimitCpu, delimitRam, delimitHd) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n"
                    + "SET IDENTITY_INSERT Maquina OFF",
                    novoRegistro.getIdMaquina(),
                    novoRegistro.getModelo(),
                    novoRegistro.getProcessador(),
                    novoRegistro.getMemoriaRam(),
                    novoRegistro.getDiscoRigido(),
                    novoRegistro.getDelimitCpu(),
                    novoRegistro.getDelimitRam(),
                    novoRegistro.getDelimitHd()
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void addWithFk(Maquina novoRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "SET IDENTITY_INSERT Maquina ON \n"
                    + "INSERT INTO Maquina (idMaquina, modelo, processador, memoriaRam, discoRigido, delimitCpu, delimitRam, delimitHd, idInstituicao) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)\n"
                    + "SET IDENTITY_INSERT Maquina OFF",
                    novoRegistro.getIdMaquina(),
                    novoRegistro.getModelo(),
                    novoRegistro.getProcessador(),
                    novoRegistro.getMemoriaRam(),
                    novoRegistro.getDiscoRigido(),
                    novoRegistro.getDelimitCpu(),
                    novoRegistro.getDelimitRam(),
                    novoRegistro.getDelimitHd(),
                    novoRegistro.getIdInstituicao()
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void remove(int idProcurado) {
        initConnection();

        try {
            jdbcTemplate.execute(
                    "DELETE FROM Maquina WHERE idMaquina = " + idProcurado
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void update(int idProcurado, Maquina updateRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "UPDATE Maquina SET\n"
                    + "modelo = ?,\n"
                    + "processador = ?,\n"
                    + "memoriaRam = ?,\n"
                    + "discoRigido = ?,\n"
                    + "delimitCpu = ?,\n"
                    + "delimitRam = ?,\n"
                    + "delimitHd = ?\n"
                    + "WHERE idMaquina = ?",
                    updateRegistro.getModelo(),
                    updateRegistro.getProcessador(),
                    updateRegistro.getMemoriaRam(),
                    updateRegistro.getDiscoRigido(),
                    updateRegistro.getDelimitCpu(),
                    updateRegistro.getDelimitRam(),
                    updateRegistro.getDelimitHd(),
                    idProcurado
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void updateWithFk(int idProcurado, Maquina updateRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "UPDATE Maquina SET\n"
                    + "modelo = ?,\n"
                    + "processador = ?,\n"
                    + "memoriaRam = ?,\n"
                    + "discoRigido = ?,\n"
                    + "delimitCpu = ?,\n"
                    + "delimitRam = ?,\n"
                    + "delimitHd = ?,\n"
                    + "idInstituicao = ?\n"
                    + "WHERE idMaquina = ?",
                    updateRegistro.getModelo(),
                    updateRegistro.getProcessador(),
                    updateRegistro.getMemoriaRam(),
                    updateRegistro.getDiscoRigido(),
                    updateRegistro.getDelimitCpu(),
                    updateRegistro.getDelimitRam(),
                    updateRegistro.getDelimitHd(),
                    updateRegistro.getIdInstituicao(),
                    idProcurado
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

}
