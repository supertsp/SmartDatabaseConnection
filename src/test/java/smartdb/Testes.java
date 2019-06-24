package smartdb;

import java.util.List;
import smartdb.SmartDbConnection;
import smartdb.SmartDbConnection;

/**
 *
 * @author tiago
 */
public class Testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SmartDbConnection.setVariablesToSqlServer(
            "jdbc:sqlserver://smartmonkeymonitoring.database.windows.net:1433;database=SmartMonkey;user=admsmart@smartmonkeymonitoring;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            "admsmart",
            "MonkeysBusiness02"
        );
        
        SmartDbConnection.setVariablesToMySql(
            "jdbc:mysql://localhost:3306/cinema",
            "root",
            ""
        );
        SmartDbConnection.addTimeZoneUtcToUrlConnection();
        
        
        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
        System.out.println(">>> QUERY WITH WILDCARDS");
        
//        //SQL SERVER
        SmartDbConnection.setCurrentDbType(SmartDbConnection.DbType.SQLServer);
//        System.out.println(SmartDbConnection.executeQueryToReturnList("SELECT * FROM Maquina"));
        System.out.println(SmartDbConnection.executeQueryToReturnList("SELECT * FROM Maquina WHERE idMaquina = ? or idMaquina = ?", 2, 3));
//        SmartDbConnection.executeQuery("SELECT * FROM Maquina WHERE idMaquina = ?", 5);
//        SmartDbConnection.executeQuery(
//                "INSERT INTO Maquina VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
//                "Novo Pc", "Zion 7Q", 128, 2048, 80, 80.5, 90.0, 3
//        );
        
        //MYSQL
        SmartDbConnection.setCurrentDbType(SmartDbConnection.DbType.MySQL);
        System.out.println(SmartDbConnection.executeQueryToReturnList("SELECT * FROM FilmeFavorito where idFilmeFavorito = ? or idFilmeFavorito = ?", 2, 4));
//        SmartDbConnection.executeQuery("select * from filmefavorito WHERE idFilmefavorito = ?", 10);
//        SmartDbConnection.executeQuery(
//                "update filmefavorito set "
//                + "   titulo = ? ,"
//                + "   genero = ?"
//                + "   WHERE idFilmeFavorito = ?",
//                "Minha Mãe é uma coisa muito...", "BR", 10                
//        );
//        SmartDbConnection.executeQuery(
//                "INSERT INTO FilmeFavorito (titulo, genero) VALUES (?, ?)",
//                "O Sombra", "Ação"
//        );
        SmartDbConnection.executeQuery("delete from filmefavorito where idFilmeFavorito = ?", 13);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
////        //SQL Server
////        "jdbc:sqlserver://smartmonkeymonitoring.database.windows.net:1433;database=SmartMonkey;user=admsmart@smartmonkeymonitoring;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
////        "admsmart"
////        "MonkeysBusiness02"
//        
//        List<Maquina> maquinas = MaquinaDAO.getAll();
//        System.out.println("Minha Conexão é:  " + SmartDbConnection.toStringStatic());
//        System.out.print("\n<LIST " + maquinas.size() + ">" + maquinas + "</LIST>\n");
//
//        
//        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
////        //MySQL
////        "com.mysql.cj.jdbc.Driver"
////        "jdbc:mysql://localhost:3306/cinema?useTimezone=true&serverTimezone=UTC"
////        "root"
////        ""
//        
//        FilmeFavoritoDAO filmeConexao = new FilmeFavoritoDAO();
//        System.out.println("Minha Conexão é:  " + SmartDbConnection.toStringStatic());        
//        System.out.println("Todos os Filmes: " + filmeConexao.listarTodos());
//        
//        
//        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
//        
//        System.out.println(">>> QUERY ALONE");
//        
////        System.out.println(
////                Connection.executeQuery(FilmeFavorito.class, "select * from filmefavorito")
////        );
//        
//        
//        int value =  (Integer)  SmartDbConnection.executeQueryToReturnObject("select idFilmeFavorito from filmefavorito where idFilmeFavorito = 2");
//        System.out.println(value);
//        
//        
//        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
//        System.out.println(">>> CHANGING DBTYPE");
//        SmartDbConnection.setCurrentDbType(SmartDbConnection.DbType.SQLServer);
//        System.out.println("<<SQL SERVER>> " + SmartDbConnection.executeQueryToReturnList(Maquina.class, "SELECT * FROM Maquina"));
//        SmartDbConnection.setCurrentDbType(SmartDbConnection.DbType.MySQL);
//        System.out.println("<<MYSQL>> " + SmartDbConnection.executeQueryToReturnList(FilmeFavorito.class, "select * from filmefavorito"));
//        
//        
//        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
//        System.out.println(">>> QUERY WITHOUT RETURN");
//        SmartDbConnection.setCurrentDbType(SmartDbConnection.DbType.SQLServer);
//        System.out.println("<<SQL SERVER>> ");
//        SmartDbConnection.executeQuery("SELECT * FROM Maquina");
//        SmartDbConnection.executeQuery(
//                "INSERT INTO Maquina VALUES"
//                + "('PC de Testes', 'Intel Testes', 16, 100.7, 90.5, 90, 70.6, null)"
//        );
//        SmartDbConnection.executeQuery(
//                "UPDATE Maquina SET	\n" +
//                "modelo = 'PC Levelho',\n" +
//                "processador = 'Cortex 7C',\n" +
//                "memoriaRam = 64,\n" +
//                "discoRigido = 2048,\n" +
//                "delimitCpu = 70,\n" +
//                "delimitRam = 70,\n" +
//                "delimitHd = 70\n" +
//                "WHERE idMaquina = 18"
//        );
//        
//        SmartDbConnection.executeQuery(
//                "DELETE FROM Maquina WHERE idMaquina = 18"
//        );
//        
//        
//        
//        
//        SmartDbConnection.setCurrentDbType(SmartDbConnection.DbType.MySQL);
//        System.out.println("<<MYSQL>> ");
//        SmartDbConnection.executeQuery("insert into filmefavorito (titulo, genero) VALUES ('UMA AVENTURA LEGO', '3D')");
//        SmartDbConnection.executeQuery("delete from filmefavorito where idFilmeFavorito = 8");
//        SmartDbConnection.executeQuery(
//                "update filmefavorito set "
//                + "   titulo = 'BEBE EM APUROS' ,"
//                + "   genero = 'COMÉDIA'"
//                + "   WHERE idFilmeFavorito = 9;"
//        );
        
        
        
        
        


        System.out.println("\n\n\n---------------------------------------------------------------------------------------------------");
    }
    
}
