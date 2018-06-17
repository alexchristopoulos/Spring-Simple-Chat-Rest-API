package Test.Dao;
import Test.DataModel.*;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelDao 
{
	public static HashMap<String,Channel> channels;
	static
	{
		channels = new HashMap<String,Channel>();
		channels.put("random", new Channel("random"));
		channels.put("general", new Channel("general"));
		channels.put("program", new Channel("program"));
		channels.put("software", new Channel("software"));
		channels.put("jokes", new Channel("jokes"));
	};
	
	public ResponseEntity<String> Subscribe(String channel_name,String username)
	{
		Channel tmp = channels.get(channel_name);
		if(channels.containsKey(channel_name)==true)
		{//channel exists
			if(tmp.isSubscribed(username)==true)
			{
				return new ResponseEntity<String>("202 User already a subscriber!",HttpStatus.ACCEPTED);
			}else
			{
				tmp.Subscribe(new User(username));
				channels.put(channel_name, tmp);
				return new ResponseEntity<String>("200 Subscription succeded!",HttpStatus.OK);
			}
		}else
		{//channel not exists
			return new ResponseEntity<String>("404 Channel not exists!",HttpStatus.NOT_FOUND);
		}	
	}
	
	public ResponseEntity<String> Unsubscribe(String channel_name,String username)
	{
		Channel tmp = channels.get(channel_name);
		if(channels.containsKey(channel_name)==true)
		{//channel exists
			if(tmp.isSubscribed(username)==true)
			{
				tmp.Unsubscribe(username);
				channels.put(channel_name, tmp);
				return new ResponseEntity<String>("200 Unsubscribe succeeded",HttpStatus.OK);
			}else
			{
				return new ResponseEntity<String>("404 The subscription does not exist",HttpStatus.NOT_FOUND);
			}
		}else
		{//channel not exists
			return new ResponseEntity<String>("404 The subscription does not exist",HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> GetMessage(String channel_name,String username)
	{
		if(channels.containsKey(channel_name))
		{
			Channel update = channels.get(channel_name);
			ResponseEntity<String> mes = update.getMessage(username);
			channels.put(channel_name, update);
			return mes;
		}else
		{
			return new ResponseEntity<String>("404 The subscription does not exist",HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<String> PostMessage(String channel_name,String message)
	{
		ResponseEntity<String> respond;
		if(channels.containsKey(channel_name))
		{
			Channel tmp = channels.get(channel_name);
			tmp.AddMessageToChannel(message);
			channels.put(channel_name, tmp);
			respond = new ResponseEntity<String>("200 Publish Succeeded",HttpStatus.OK);
		}else
		{
			respond = new ResponseEntity<String>("404 Channel not exists",HttpStatus.NOT_FOUND);
		}
		return respond;
	}
}
