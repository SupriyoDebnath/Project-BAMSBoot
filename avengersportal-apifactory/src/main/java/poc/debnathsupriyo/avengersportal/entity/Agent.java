package poc.debnathsupriyo.avengersportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Agent")
public class Agent {
	
	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String agentId;
	private String agentName;
	private String agentLocation;
	private String agentSkill;
	private String agentTeamName;
	private String agentAvatarId;
	private String agentAvatarBase64Encoded;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentLocation() {
		return agentLocation;
	}
	public void setAgentLocation(String agentLocation) {
		this.agentLocation = agentLocation;
	}
	public String getAgentSkill() {
		return agentSkill;
	}
	public void setAgentSkill(String agentSkill) {
		this.agentSkill = agentSkill;
	}
	public String getAgentTeamName() {
		return agentTeamName;
	}
	public void setAgentTeamName(String agentTeamName) {
		this.agentTeamName = agentTeamName;
	}
	public String getAgentAvatarId() {
		return agentAvatarId;
	}
	public void setAgentAvatarId(String agentAvatarId) {
		this.agentAvatarId = agentAvatarId;
	}
	public String getAgentAvatarBase64Encoded() {
		return agentAvatarBase64Encoded;
	}
	public void setAgentAvatarBase64Encoded(String agentAvatarBase64Encoded) {
		this.agentAvatarBase64Encoded = agentAvatarBase64Encoded;
	}
	
	
	
	
	
}
