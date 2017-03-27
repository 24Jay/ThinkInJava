package Json;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestGson_2
{
	public static void main(String[] ar) throws IOException
	{
		/***
		 * ｗｉｔｈ ｙｏｕｒ own hand
		 */
		Gson gson = new Gson();
		String json = "{\"name\":\"fanmiao\",\"id\":1,\"email\":\"126@gmail.com\"}";
		User user = new User();
		JsonReader reader = new JsonReader(new StringReader(json));
		reader.beginObject();
		while (reader.hasNext())
		{
			String s = reader.nextName();
			switch (s) {
			case "name":
				user.setName(reader.nextString());
				break;
			case "id":
				user.setId(reader.nextInt());
				break;

			case "email":
				user.setEmail(reader.nextString());
				break;
			default:
				break;
			}
		}
		reader.endObject();
		System.out.println(user);

		/***
		 * 
		 */
		gson.toJson(user, System.out);

		/*****
		 * 
		 */
		User u = new User();
		u.setBirthday(new Date());
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls().setDateFormat("yyyy-MM-dd");
		Gson gn = builder.create();
		System.out.println("\n builder=" + gn.toJson(u));

	}

}
