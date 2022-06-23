package laba_2.model;

import java.util.ArrayList;
import java.util.List;


public class Store
{
	private List<Product> products = new ArrayList<>();

	public Store(final List<Product> products)
	{
		this.products = products;
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public void setProducts(final List<Product> products)
	{
		this.products = products;
	}
}
