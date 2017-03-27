package Json;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class TestGson_1
{
	public static void main(String[] ar)
	{
		Gson gson = new Gson();

		/***
		 * 把字符串转为对象
		 */
		int i = gson.fromJson("100", int.class);
		double d = gson.fromJson("\"99.99\"", double.class);
		boolean b = gson.fromJson("true", boolean.class);
		String str = gson.fromJson("String", String.class);
		System.out.println("i=" + i + " d=" + d + " b=" + b + " str=" + str);

		/***
		 * 即便字符串中的是"email_address","emailAddress",也可以解析出来<br>
		 */
		String string = "[{\"name\":\"fanmiao\",\"id\":1,\"email_address\":\"126@gmail.com\"},{\"name\":\"miaomiao\",\"id\":2,\"emailAddress\":\"jay126@gmail.com\"}]";
		User[] user = gson.fromJson(string, User[].class);
		System.out.println(Arrays.toString(user));

		String jay = gson.toJson(new User("zjamgkoe", 24, "24zhangjie@gmail.com"));
		System.out.println(jay);

		/***
		 * 使用数组
		 */
		String jsonArray = "[\"Java\",\"Android\",\"Linux\"]";
		String[] ss = gson.fromJson(jsonArray, String[].class);
		for (String s : ss)
		{
			System.out.print(s + "\t");
		}
		/***
		 * 使用泛型
		 */
		Type type = new TypeToken<List<String>>()
		{
		}.getType();
		List<String> list = gson.fromJson(jsonArray, new TypeToken<List<String>>()
		{
		}.getType());

		System.out.println("\nUse generic type: ");
		for (String s : list)
			System.out.print(s + "\t");

	}
}

