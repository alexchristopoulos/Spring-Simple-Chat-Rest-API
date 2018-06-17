package Test.DataModel;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class Channel {
	
	private String name;
	private ArrayList<User> subs;
	private HashMap<String,ArrayList<String>> userMessages;
	
	
	public ArrayList<User> getSubs()
	{
		return this.subs;
	}
	public String getName()
	{
		return this.name;
	}
	public void AddMessageToChannel(String message)
	{
		for(User u:subs)
		{
			if(this.userMessages.containsKey(u.getUserName()))
			{
				String uname = u.getUserName();
				ArrayList<String> update = this.userMessages.get(uname);
				update.add(message);
				this.userMessages.put(uname,update);
			}else
			{
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(message);
				this.userMessages.put(u.getUserName(),tmp);				
			}
		}
	}
	public Channel(String name)
	{
		this.name = name;
		subs = new ArrayList<User>();
		this.userMessages = new HashMap<String,ArrayList<String>>();
	}
	
	public ResponseEntity<String> getMessage(String username)
	{
		if(this.isSubscribed(username)==true)
		{
			if(this.userMessages.containsKey(username)==true)
			{
				ArrayList<String> messages = this.userMessages.get(username);
				if(messages.size()>0)
				{
					String response = messages.get(0);
					messages.remove(0);
					this.userMessages.put(username, messages);
					return new ResponseEntity<String>("200 Retrieval succeeded\n"+response,HttpStatus.OK);
				}else
				{
					return new ResponseEntity<String>("204 There are no messages available for this topic on this user",HttpStatus.NO_CONTENT);
				}
			}else
			{
				this.userMessages.put(username, new ArrayList<String>());
				return new ResponseEntity<String>("204 There are no messages available for this topic on this user",HttpStatus.NO_CONTENT);
			}
		}else
		{
			return new ResponseEntity<String>("404 The subscription does not exist",HttpStatus.NOT_FOUND);
		}
	}
	
	
	public void Subscribe(User u)
	{
		this.subs.add(u);
	}
	
	public boolean isSubscribed(String username)
	{
		int ok=0;
		for(User u: this.subs)
		{if(u.getUserName().equals(username)==true) {ok=1;}}
		return ok==1?true:false;
	}
	
	public void Unsubscribe(String username)
	{
		int indx=0;
		
		for(int i=0;i<this.subs.size();i++)
		{
			if(subs.get(i).getUserName().equals(username)) 
			{
			     this.userMessages.remove(subs.get(i).getUserName());
			     indx=i;break;
			}
		}
		this.subs.remove(indx);
	}
	
}
