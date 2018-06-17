package Test.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import Test.Dao.ChannelDao;

@Service
public class MethodService {
	
	@Autowired
	private ChannelDao channeldao;
	public MethodService(){ }
	
	
	public ResponseEntity<String> Subscribe(String channel_name,String username)
	{
		return channeldao.Subscribe(channel_name, username);
	}
	public ResponseEntity<String> Unsubscribe(String channel_name,String username)
	{
		return channeldao.Unsubscribe(channel_name, username);
	}
	public ResponseEntity<String> GetMessage(String channel_name,String username)
	{
		return channeldao.GetMessage(channel_name, username);
	}
	public ResponseEntity<String> PostMessage(String channel_name,String message)
	{
		return channeldao.PostMessage(channel_name, message);
	}
}
