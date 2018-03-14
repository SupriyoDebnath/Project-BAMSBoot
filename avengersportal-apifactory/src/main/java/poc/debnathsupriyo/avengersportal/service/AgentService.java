package poc.debnathsupriyo.avengersportal.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import poc.debnathsupriyo.avengersportal.entity.Agent;
import poc.debnathsupriyo.avengersportal.entity.MongoSequence;
import poc.debnathsupriyo.avengersportal.repository.AgentRepository;
import poc.debnathsupriyo.avengersportal.repository.AgentSequenceRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private AgentSequenceRepository agentSequenceRepository;
	
	public String getAgentCount() {
		
		long agentCount = agentRepository.count();
		ObjectMapper responseBody = new ObjectMapper();
		ObjectNode root = responseBody.createObjectNode();
		root.put("ProfileCount", agentCount);
		return root.toString();
	}
	
	public String insertAgent(Agent agent) {
		
		String agentId = this.getNextAgentId();
		agent.setAgentId(agentId);
		ObjectMapper responseBody = new ObjectMapper();
		ObjectNode root = responseBody.createObjectNode();
		agentId = agentRepository.insert(agent).getAgentId();
		root.put("AgentId", agentId);
		return root.toString();
	}
	
	public Agent findAgentByAgentId(String agentId) {
		Agent agent =  agentRepository.findByAgentId(agentId);
		agent.setAgentAvatarBase64Encoded(this.getBase64EncodedAvatar(agent.getAgentAvatarId()));
		return agent;
	}
	
	
	private synchronized String getNextAgentId() {
		
		MongoSequence lastSequence = agentSequenceRepository.findBySeqName("AgentSequence");
		int lastId = Integer.parseInt(lastSequence.getSeqValue());
		int newId = lastId + 1;
		MongoSequence newSequence = lastSequence;
		newSequence.setSeqValue(String.valueOf(newId));
		agentSequenceRepository.save(newSequence);
		
		String agentId = "AGENT"+String.format("%03d", newId);
		return agentId;
	}
	
	private String getBase64EncodedAvatar(String avatarId) {
		
		String requestURL = "http://10.0.0.230:8080/avengersportal-webartifacts/webhook/profileAvatar?avatarId={avatarId}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("avatarId", avatarId);
		
		RestTemplate requestTemplate = new RestTemplate();
		String responseBody = requestTemplate.getForObject(requestURL, String.class, param);
		String imageString = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(responseBody);
			imageString = root.get("AvatarName").textValue();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageString;
	}
}





