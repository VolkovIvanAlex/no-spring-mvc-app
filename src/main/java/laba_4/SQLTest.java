package laba_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTest
{
    private Connection con;
        
    private void initialization(String name){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + name);
            PreparedStatement st = con.prepareStatement("create table if not exists 'test' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text);");
            int result = st.executeUpdate();
        }catch(ClassNotFoundException e){
            System.out.println("Не знайшли драйвер JDBC");
            e.printStackTrace();
            System.exit(0);
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }     
    }
    
    private void insertTestData(String name){
        try{
            PreparedStatement statement = con.prepareStatement("INSERT INTO test(name) VALUES (?)");
            //statement.setInt(1, 1);
            statement.setString(1, name);

            int result = statement.executeUpdate();

            statement.close();
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит на вставку");
            e.printStackTrace();
        }
    }
    
    private void showAllData(){
        try{
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM test");
            while (res.next()) {
                String name = res.getString("name");
                System.out.println (res.getShort("id")+" "+name);
            }
            res.close();
            st.close();
        }catch(SQLException e){
            System.out.println("Не вірний SQL запит на вибірку даних");
            e.printStackTrace();
        }
    }

	private void updateName(final String name , final int id)
	{
		try
		{
			PreparedStatement statement = con.prepareStatement("UPDATE test SET name = (?) WHERE id = (?) ;");
			statement.setString(1 , name);
			statement.setInt(2 , id);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
    
    public static void main(String[] args){
        SQLTest sqlTest = new SQLTest();
        sqlTest.initialization("HelloDB");
        sqlTest.insertTestData("SuperMAKAKA");
        sqlTest.insertTestData("NewMAKAKA");
		sqlTest.updateName("newName" , 1);
        sqlTest.showAllData();
    }
}
