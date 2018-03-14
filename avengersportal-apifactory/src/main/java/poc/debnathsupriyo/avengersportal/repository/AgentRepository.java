package poc.debnathsupriyo.avengersportal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import poc.debnathsupriyo.avengersportal.entity.Agent;

@Repository
public interface AgentRepository extends MongoRepository<Agent, String> {
	
	public Agent findByAgentId(String agentId);
}
