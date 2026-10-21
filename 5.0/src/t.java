import com.thera.thermfw.base.Crypto2;
import com.thera.thermfw.base.Crypto3;
import com.thera.thermfw.plexweb.Trace;

public class t {

	public static void main(String[] args) {
		try {
			String pwd = Crypto3.decryptDenied("5A4BCD142946406CFB907B0C5C580037");
			System.out.println(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
