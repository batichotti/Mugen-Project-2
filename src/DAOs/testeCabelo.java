package DAOs;

/**
 *
 * @author Mateus Cohuzer
 */
import java.sql.*;

public class testeCabelo {

   public static void main(String[] args) throws SQLException {

      String url = "jdbc:mysql://localhost:3306/mugen_project?zeroDateTimeBehavior=CONVERT_TO_NULL";
      String user = "matt";
      String password = "51413depressAo.";
      Connection conn = DriverManager.getConnection(url, user, password);

      String sql = "SELECT * FROM cor_cabelo";

      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      ResultSetMetaData metaData = resultSet.getMetaData();
      int numColunas = metaData.getColumnCount();
      for (int i = 1; i <= numColunas; i++) {
         String nomeColuna = metaData.getColumnName(i);
         System.out.println("Nome da coluna " + i + ": " + nomeColuna);
      }

      // Fechar os recursos
      resultSet.close();
      statement.close();
      conn.close();
   }
}
