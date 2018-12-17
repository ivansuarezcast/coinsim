import java.util.List;

public class Block {
	
	int index;
	double timestamp;
	List <Transaction>transactions;
	int proof;
	String previousBlockHash;
	public Block(int index, long timestamp, List<Transaction> transactions, int proof, String hash) {
		// TODO Auto-generated constructor stub
		this.index=index;
		this.timestamp=timestamp;
		this.transactions=transactions;
		this.proof=proof;
		this.previousBlockHash=hash;
	}
	
	@Override
	public String toString(){
		return "Index: "+index+" ; Timestamp: "+timestamp+" ; Previous Block Hash: "+previousBlockHash;
	}
	
}
