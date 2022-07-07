package laba_2.model;

public class Product
{
	private Integer id;
	private String name;
	private Category category;
	private Integer quantity;
	private Double price;

	public Product()
	{
	}

	public Product(final String name, final Category category, final Integer quantity, final Double price)
	{
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}

	public Product(final Integer id, final String name, final Category category, final Integer quantity,
			final Double price)
	{
		this.id = id;
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
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

	@Override
	public String toString()
	{
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", category=" + category +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}
}
