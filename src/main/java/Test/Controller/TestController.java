package Test.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Test.DataModel.*;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
    private MethodService services;
    
    @RequestMapping(value = "/{channel_name}/{username}",method = RequestMethod.GET)
    public ResponseEntity<String> getMessage(@PathVariable String channel_name,@PathVariable String username){
    	return services.GetMessage(channel_name, username);
    }//GET /<channel>/<username>
   
    @RequestMapping(value = "/{channel_name}/{username}", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> SubscriptionMethod(@PathVariable String channel_name,@PathVariable String username){
    	return services.Subscribe(channel_name,username);
    }//POST /<channel>/<username>
    
    @RequestMapping(value = "/{channel_name}/{username}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> UnsubscribeMethod(@PathVariable String channel_name,@PathVariable String username){
    	return services.Unsubscribe(channel_name,username);
    }//DELETE /<channel>/<username>
    
    @RequestMapping(value = "/{channel_name}", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> PostMessage(@PathVariable String channel_name,@RequestBody String body){
    	return services.PostMessage(channel_name,body);
    }//POST /<channel>
    
}
