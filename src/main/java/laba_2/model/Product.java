package laba_2.model;

public class Product
{
	private String name;
	private Category category;
	private Integer quantity;
	private Double price;

	public Product(final String name, final Category category, final Integer quantity, final Double price)
	{
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(final Category category)
	{
		this.category = category;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final Integer quantity)
	{
		this.quantity = quantity;
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(final Double price)
	{
		this.price = price;
	}
}
