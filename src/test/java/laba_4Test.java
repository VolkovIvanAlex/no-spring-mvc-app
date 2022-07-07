import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import laba_2.model.Category;
import laba_2.model.Product;
import laba_4.service.ProductService;


public class laba_4Test
{
	static ProductService productService;
	@BeforeClass
	public static void init()
	{
		productService = new ProductService();
		productService.init("Store");
		productService.clearDB();
	}

	@Test
	public void jdbcTest()
	{
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
		productService.addCategory(category);
		productService.addProduct(apple);
		productService.addProduct(banana);

		Assert.assertEquals(2, productService.GetProductsWithQuantityLessThan(20).size());

		Product newApple = new Product(1 , "New Apple" , category , 50 , 20.0);
		productService.updateProduct(newApple);

		Product gotProduct = productService.getProductById(1);
		Assert.assertEquals("New Apple", gotProduct.getName());

		productService.deleteProduct(1);
		Assert.assertNull(productService.getProductById(1));

		Assert.assertEquals(1, productService.GetProductsWithQuantityLessThan(20).size());

	}
}
