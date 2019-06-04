package smartdb;

import smartdb.Leitura;
import smartdb.LeituraDAO;
import smartdb.Maquina;
import smartdb.MaquinaDAO;
import java.util.List;

/**
 *
 * @author tiago
 */
public class Testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //TESTES NA CLASSE LEITURA
        
//        Leitura testeL = new Leitura(20.5, 70.8, 60.5, "2019-12-31");
//        Leitura testeL = new Leitura(20.5, 70.8, 60.5);
//        System.out.print(testeL);
        
//        testeL.salvarDados();
//        System.out.print(testeL);
//        
//        testeL.setPorcHd(15.5);
//        testeL.salvarDados();
//        System.out.print(testeL);
//        
//        testeL.setIdMaquina(1);
//        System.out.print(testeL);
//        
//        testeL.removerDados();
//        System.out.print(testeL);
//        
                
        //TESTES NA CLASSE MAQUINA
        
//        Maquina testeM = new Maquina("Server 500", "Xenon 123", 8, 1024.1024, 70.5, 80.0, 75.0);
//        System.out.print(testeM);
//        
//        testeM.salvarDados();
//        System.out.print(testeM);
//        
//        testeM.setDelimitCpu(10.75);
//        testeM.salvarDados();
//        System.out.print(testeM);
//        
//        testeM.setIdInstituicao(1);
//        testeM.salvarDados();
//        System.out.print(testeM);
//        
////        testeM.removerDados();
//        
//        List<Maquina> maquinas = MaquinaDAO.getAll();
//        System.out.print("\n<LIST " + maquinas.size() + ">" + maquinas + "</LIST>\n");
//        
//        Maquina m = MaquinaDAO.get(1);
//        System.out.println(m);
    

//        //SQL Server
//        "jdbc:sqlserver://smartmonkeymonitoring.database.windows.net:1433;database=SmartMonkey;user=admsmart@smartmonkeymonitoring;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
//        "admsmart"
//        "MonkeysBusiness02"
        
//        //MySQL
//        "com.mysql.cj.jdbc.Driver"
//        "jdbc:mysql://localhost:3306/cinema?useTimezone=true&serverTimezone=UTC"
//        "root"
//        ""
        

//        List<Leitura> leituras = LeituraDAO.getAll();
//        System.out.print("\n<LIST " + leituras.size() + ">" + leituras + "</LIST>\n");
//        
//        System.out.println("\n\n\n");
        
        FilmeFavoritoDAO filmeConexao = new FilmeFavoritoDAO();
        List<FilmeFavorito> filmes = filmeConexao.listarTodos();
        System.out.println("Todos os Filmes: " + filmes);
        
    }
    
}
