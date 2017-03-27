package Json;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class User
{
	String name;

	int id;

	Date birthday;
	/***
	 * 保证即便字符串中的是"email_address","emailAddress",也可以解析出来<br>
	 */
	@SerializedName(value = "email", alternate =
		{ "email_address", "emailAddress" })
	String email;

	public User(String name, int id, String email, Date dat)
	{
		this.name = name;
		this.id = id;
		this.email = email;
		this.birthday = dat;
	}

	public User()
	{
		this(null, 0, null,null);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String toString()
	{
		return "[name=" + name + ", id=" + id + " email=" + email + "]";
	}
}
