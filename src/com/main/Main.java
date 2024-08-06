import org.apache.log4j.Logger;

import java.sql.*;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    public static final String CREATE = "DROP TABLE IF EXISTS ANIMALES ; " +
            "CREATE TABLE ANIMALES (ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "NOMBRE VARCHAR(50) NOT NULL ,TIPO VARCHAR(50) NOT NULL)";

    public static final String INSERT = "INSERT INTO ANIMALES VALUES " +
            "(DEFAULT , 'FIRULAIS ', 'PERRO') ,(DEFAULT , 'SOLOVINO ', 'GATO'), " +
            "(DEFAULT , 'FRIJOL ', 'PERRO') ,(DEFAULT , 'MAXI ', 'CONEJO'), (DEFAULT , 'SAYAYIN ', 'GATO') ";

public static final String SELECT_ALL = "SELECT * FROM ANIMALES ";


public static void main(String[] args) {
        Connection connection = null;
        try{
            connection=getConnection();
            Statement statement = connection.createStatement();
            statement.execute(CREATE);

            statement.execute(INSERT);

            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                logger.info("animal : "+ resultSet.getInt(1)+" "+
                        resultSet.getString(2)+
                        ""+resultSet.getString(3));
            }
    }
        catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.info(e.getMessage());
            }
        }
    }
    private static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./Clase8","sa","sa");

        }
}