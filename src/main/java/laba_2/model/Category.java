package laba_2.model;

public class Category
{
	private Integer id;
	private String name;

	public Category(final String name)
	{
		this.name = name;
	}

	public Category(final Integer id, final String name)
	{
		this.id = id;
		this.name = name;
	}

	public Category(final Integer id)
	{
		this.id = id;
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

	@Override
	public String toString()
	{
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
