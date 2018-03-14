package poc.debnathsupriyo.avengersportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import poc.debnathsupriyo.avengersportal.entity.Agent;
import poc.debnathsupriyo.avengersportal.service.AgentService;

@RestController
@RequestMapping("/avengersportal-apifactory/microservices/Agent")
public class AgentController {
	
	@Autowired
	private AgentService agentService;
	
	@RequestMapping(method = RequestMethod.HEAD)
	@ResponseStatus(code = HttpStatus.OK)
	public void pingMicroService() {
		
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProfileCount() {
		return agentService.getAgentCount();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{agentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Agent getProfile(@PathVariable String agentId) {
		return agentService.findAgentByAgentId(agentId);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveProfile(@RequestBody Agent agent) {
		return agentService.insertAgent(agent);
		
	}
	
	

}
