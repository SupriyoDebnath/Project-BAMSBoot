package poc.debnathsupriyo.avengersportal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import poc.debnathsupriyo.avengersportal.entity.MongoSequence;

@Repository
public interface AgentSequenceRepository extends MongoRepository<MongoSequence, String> {
	
	public MongoSequence findBySeqName(String seqName);
}
