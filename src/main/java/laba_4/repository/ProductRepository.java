package laba_4.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteException;

import laba_2.model.Category;
import laba_2.model.Product;


public class ProductRepository
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

	public void createTableProducts()
	{
		try
		{
			String queryToCreateTable = "create table if not exists Products "
					+ "(product_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " name TEXT ,"
					+ " category_id INTEGER , "
					+ " quantity INTEGER ,"
					+ " price REAL ,"
					+ " FOREIGN KEY (category_id) REFERENCES Category(category_id));";
			Statement statement = con.createStatement();
			statement.executeUpdate(queryToCreateTable);
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void createTableCategory()
	{
		try
		{
			String queryToCreateTable = "create table if not exists Category "
					+ "(category_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " name TEXT);";
			Statement statement = con.createStatement();
			statement.executeUpdate(queryToCreateTable);
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void AddProduct(Product product)
	{
		try
		{
			String queryToInsertProduct = "INSERT INTO Products "
					+ "(product_id , name, category_id , quantity , price) VALUES (? , ? , ? , ? , ?)";
			PreparedStatement statement = con.prepareStatement(queryToInsertProduct);
			statement.setInt(1, product.getId());
			statement.setString(2, product.getName());
			statement.setInt(3, product.getCategory().getId());
			statement.setInt(4, product.getQuantity());
			statement.setDouble(5, product.getPrice());
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

	public void updateProduct(Product product)
	{
		try
		{
			String queryToUpdateProduct = "UPDATE Products SET "
					+ " name = (?), category_id  = (?), quantity = (?), price = (?) WHERE product_id = (?)";
			PreparedStatement statement = con.prepareStatement(queryToUpdateProduct);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getCategory().getId());
			statement.setInt(3, product.getQuantity());
			statement.setDouble(4, product.getPrice());
			statement.setInt(5, product.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void deleteProduct(Integer productId)
	{
		try
		{
			String queryToDeleteProduct = "DELETE FROM Products WHERE product_id = (?);";
			PreparedStatement statement = con.prepareStatement(queryToDeleteProduct);
			statement.setInt(1, productId);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void AddCategory(Category category)
	{
		try
		{
			String queryToInsertCategory = "INSERT INTO Category "
					+ "(category_id , name) VALUES (? , ?)";
			PreparedStatement statement = con.prepareStatement(queryToInsertCategory);
			statement.setInt(1, category.getId());
			statement.setString(2, category.getName());
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

	public void updateCategory(Category category)
	{
		try
		{
			String queryToUpdateCategory = "UPDATE Category SET "
					+ " name = (?) WHERE category_id = (?)";
			PreparedStatement statement = con.prepareStatement(queryToUpdateCategory);
			statement.setString(1, category.getName());
			statement.setInt(2, category.getId());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void deleteCategory(Integer categoryId)
	{
		try
		{
			String queryToDeleteCategory = "DELETE FROM Category WHERE categoryId = (?)";
			PreparedStatement statement = con.prepareStatement(queryToDeleteCategory);
			statement.setInt(1, categoryId);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}

	public void showProducts()
	{
		try
		{
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery(
					"SELECT * FROM Products JOIN Category ON Products.category_id = Category.category_id;");
			while (res.next())
			{
				Integer product_id = res.getInt("product_id");
				String name = res.getString("name");
				Integer category_id = res.getInt("category_id");
				Integer quantity = res.getInt("quantity");
				Double price = res.getDouble("price");
				Product product = new Product(product_id, name, new Category(category_id), quantity, price);
				System.out.println(product);
			}
			res.close();
			st.close();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит на вибірку даних");
			e.printStackTrace();
		}
	}

	public Product getProductById(Integer id)
	{
		try
		{
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM Products JOIN Category ON Products.category_id = Category.category_id"
							+ " WHERE product_id = (?) ;");
			st.setInt(1 , id);
			ResultSet res = st.executeQuery();

			Integer product_id = res.getInt("product_id");
			String name = res.getString("name");
			Integer category_id = res.getInt("category_id");
			Integer quantity = res.getInt("quantity");
			Double price = res.getDouble("price");
			Product product = new Product(product_id, name, new Category(category_id), quantity, price);
			res.close();
			st.close();
			return product;
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит на вибірку даних , або запису немає.");
		}
		return null;
	}

	public List<Product> findProductsWithLessQuantityThan(Integer quantityParam)
	{
		List<Product> products = new ArrayList<>();
		try
		{
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM Products JOIN Category ON Products.category_id = Category.category_id"
							+ " WHERE quantity < (?);");
			st.setInt(1, quantityParam);
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				Integer product_id = res.getInt("product_id");
				String name = res.getString("name");
				Integer category_id = res.getInt("category_id");
				Integer quantity = res.getInt("quantity");
				Double price = res.getDouble("price");
				Product product = new Product(product_id, name, new Category(category_id), quantity, price);
				products.add(product);
			}
			res.close();
			st.close();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит на вибірку даних");
			e.printStackTrace();
		}
		return products;
	}

	public void showCategories()
	{
		try
		{
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Category");
			while (res.next())
			{

				Integer category_id = res.getInt("category_id");
				String name = res.getString("name");
				Category category = new Category(category_id, name);
				System.out.println(category);
			}
			res.close();
			st.close();
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит на вибірку даних");
			e.printStackTrace();
		}
	}

	public void clearDB()
	{
		try
		{
			Statement st = con.createStatement();
			st.executeUpdate("DROP TABLE Products");

			Statement st2 = con.createStatement();
			st2.executeUpdate("DROP TABLE Category");
		}
		catch (SQLException e)
		{
			System.out.println("Не вірний SQL запит");
			e.printStackTrace();
		}
	}
}
