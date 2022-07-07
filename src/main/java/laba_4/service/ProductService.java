package laba_4.service;

import java.util.List;

import laba_2.model.Category;
import laba_2.model.Product;
import laba_4.repository.ProductRepository;


public class ProductService
{
	ProductRepository productRepository = new ProductRepository();

	public void init(String dbName)
	{
		productRepository.initialization(dbName);
	}

	public void createTable(String name)
	{
		switch (name)
		{
			case "Product" -> productRepository.createTableProducts();
			case "Category" -> productRepository.createTableCategory();
			default -> throw new RuntimeException("Invalid name");
		}
	}

	public void addProduct(Product product)
	{
		productRepository.AddProduct(product);
	}

	public void addCategory(Category category)
	{
		productRepository.AddCategory(category);
	}

	public void showProduct()
	{
		productRepository.showProducts();
	}

	public void showCategory()
	{
		productRepository.showCategories();
	}

	public void updateProduct(Product product)
	{
		productRepository.updateProduct(product);
	}

	public void updateCategory(Category category)
	{
		productRepository.updateCategory(category);
	}

	public void deleteProduct(Integer productId)
	{
		productRepository.deleteProduct(productId);
	}

	public void updateCategory(Integer categoryId)
	{
		productRepository.deleteCategory(categoryId);
	}

	public List<Product> GetProductsWithQuantityLessThan(Integer quantity)
	{
		return productRepository.findProductsWithLessQuantityThan(quantity);
	}

	public Product getProductById(final Integer id)
	{
		Product gotProduct = productRepository.getProductById(id);
		return gotProduct;
	}

	public void clearDB()
	{
		productRepository.clearDB();
	}
}
