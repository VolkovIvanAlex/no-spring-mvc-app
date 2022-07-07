package laba_4;

import java.util.List;

import laba_2.model.Category;
import laba_2.model.Product;
import laba_4.service.ProductService;


public class Main
{
	public static void main(String[] args)
	{
		ProductService productService = new ProductService();
		productService.init("Store");
		productService.clearDB();
		try
		{
			productService.createTable("Product");
			productService.createTable("Category");
		}
		catch (RuntimeException exception)
		{
			exception.printStackTrace();
		}
		Category category = new Category(1 , "Fruits");
		Product apple = new Product(1 , "Apple" , category , 10 , 10.0);
		Product banana = new Product(2 , "Banana" , category , 15 , 12.0);
		Product lemon = new Product(3 , "Lemon" , category , 40 , 7.0);
		productService.addCategory(category);
		productService.addProduct(apple);
		productService.addProduct(banana);

		productService.showProduct();
		productService.showCategory();

		Product newApple = new Product(1 , "New Apple" , category , 50 , 20.0);
		productService.updateProduct(newApple);
		productService.showProduct();

		Category newCategory = new Category(1 , "NewFruits");
		productService.updateCategory(newCategory);
		productService.showCategory();

		productService.addProduct(lemon);
		productService.deleteProduct(1);
		productService.showProduct();

		List<Product> products = productService.GetProductsWithQuantityLessThan(20);
		System.out.println(products);
	}
}
