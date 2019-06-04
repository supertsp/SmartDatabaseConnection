package smartdb;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tiago
 */
public class LeituraDAO {

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

    public static List<Leitura> getAll() {
        initConnection();

        try {
            List<Leitura> lista = jdbcTemplate.query(
                    "SELECT * FROM Leitura",
                    new BeanPropertyRowMapper(Leitura.class)
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
            Integer idAvaliable = jdbcTemplate.queryForObject(
                    "SELECT ISNULL(MAX(idLeitura) + 1, 0) FROM Leitura",
                    Integer.class
            );
            return idAvaliable;
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
            return null;
        }
    }

    public static Leitura get(int idProcurado) {
        initConnection();

        try {
            Leitura registro = jdbcTemplate.queryForObject(
                    "SELECT * FROM Leitura where idLeitura = ?",
                    new BeanPropertyRowMapper<Leitura>(Leitura.class),
                    idProcurado
            );

            return registro;
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
            return null;
        }
    }

    public static void add(Leitura novoRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "SET IDENTITY_INSERT Leitura ON \n"
                    + "INSERT INTO Leitura (idLeitura, porcProcessador, porcRam, porcHd, dataLeitura) "
                    + "VALUES (?, ?, ?, ?, ?)\n"
                    + "SET IDENTITY_INSERT Leitura OFF",
                    novoRegistro.getIdLeitura(),
                    novoRegistro.getPorcProcessador(),
                    novoRegistro.getPorcRam(),
                    novoRegistro.getPorcHd(),
                    novoRegistro.getDataLeitura()
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void addWithFk(Leitura novoRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "SET IDENTITY_INSERT Leitura ON \n"
                    + "INSERT INTO Leitura (idLeitura, porcProcessador, porcRam, porcHd, dataLeitura, idMaquina) "
                    + "VALUES (?, ?, ?, ?, ?, ?)\n"
                    + "SET IDENTITY_INSERT Leitura OFF",
                    novoRegistro.getIdLeitura(),
                    novoRegistro.getPorcProcessador(),
                    novoRegistro.getPorcRam(),
                    novoRegistro.getPorcHd(),
                    novoRegistro.getDataLeitura(),
                    novoRegistro.getIdMaquina()
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void remove(int idProcurado) {
        initConnection();

        try {
            jdbcTemplate.execute(
                    "DELETE FROM Leitura WHERE idLeitura = " + idProcurado
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void update(int idProcurado, Leitura updateRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "UPDATE Leitura SET\n"
                    + "porcProcessador = ?,\n"
                    + "porcRam = ?,\n"
                    + "porcHd = ?,\n"
                    + "dataLeitura = ?\n"
                    + "WHERE idLeitura = ?",
                    updateRegistro.getPorcProcessador(),
                    updateRegistro.getPorcRam(),
                    updateRegistro.getPorcHd(),
                    updateRegistro.getDataLeitura(),
                    idProcurado
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

    public static void updateWithFk(int idProcurado, Leitura updateRegistro) {
        initConnection();

        try {
            jdbcTemplate.update(
                    "UPDATE Leitura SET\n"
                    + "porcProcessador = ?,\n"
                    + "porcRam = ?,\n"
                    + "porcHd = ?,\n"
                    + "dataLeitura = ?,\n"
                    + "idMaquina = ?\n"
                    + "WHERE idLeitura = ?",
                    updateRegistro.getPorcProcessador(),
                    updateRegistro.getPorcRam(),
                    updateRegistro.getPorcHd(),
                    updateRegistro.getDataLeitura(),
                    updateRegistro.getIdMaquina(),
                    idProcurado
            );
        } catch (Exception e) {
            System.out.println("Erro de query SQL");
        }
    }

}
