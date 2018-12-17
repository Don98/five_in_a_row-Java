import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class IOUtil
{
	public static void writeObject(Object obj ,OutputStream os)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			oos.flush();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static Object readObject(InputStream is)
	{
		Object obj = null;
		try
		{
			ObjectInputStream ois = new ObjectInputStream(is);
			obj = ois.readObject();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return obj;
	}
}