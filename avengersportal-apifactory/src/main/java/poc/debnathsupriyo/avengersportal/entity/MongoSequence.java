package poc.debnathsupriyo.avengersportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SequenceFactory")
public class MongoSequence {
	
	@Id
	private String id;
	@Indexed
	private String seqName;
	private String seqValue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public String getSeqValue() {
		return seqValue;
	}
	public void setSeqValue(String seqValue) {
		this.seqValue = seqValue;
	}
	
	
	
}
