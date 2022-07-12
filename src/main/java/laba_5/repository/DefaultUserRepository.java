package laba_5.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteException;

import laba_2.model.Category;
import laba_2.model.Product;
import laba_5.User;


public class DefaultUserRepository
{
	private Connection con;

	public void initialization(String name)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + name);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			System.out.println("Не знайшли драйвер JDBC");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void createTableUsers()
	{
		try
		{
			String queryToCreateTable = "create table if not exists Users "
					+ "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " username TEXT ,"
					+ " password TEXT);";
			Statement statement = con.createStatement();
			statement.executeUpdate(queryToCreateTable);
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void addUser(User user)
	{
		try
		{
			String queryToInsertUser = "INSERT INTO Users "
					+ "(username , password) VALUES (? , ?)";
			PreparedStatement statement = con.prepareStatement(queryToInsertUser);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
		}
		catch (SQLiteException exception)
		{
			System.out.println("Id is not unique");
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public User getUserByLogin(String login)
	{
		try
		{
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM Users WHERE username = (?) ;");
			st.setString(1 , login);
			ResultSet res = st.executeQuery();

			Integer user_id = res.getInt("user_id");
			String username = res.getString("username");
			String password = res.getString("password");
			User user = new User(user_id ,  username , password);
			res.close();
			st.close();
			return user;
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит на вибірку даних , або запису немає.");
		}
		return null;
	}
}
