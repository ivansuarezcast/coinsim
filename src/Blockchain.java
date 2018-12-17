import java.util.LinkedList;
import java.util.List;

public class Blockchain {
	List <Block> blockchain;
	List <Transaction> transactions;
	public Blockchain(){
		blockchain = new LinkedList<Block>();
		transactions = new LinkedList<Transaction>();
		addBlock(100);
	}
	
	private void addBlock(int proof){
		String hash;
		if(blockchain.isEmpty()){
			hash="1";
		}else{
			hash=hash(blockchain.get(blockchain.size()-1));
		}
		Block block = new Block(blockchain.size()+1, System.currentTimeMillis(),transactions, proof, hash);
		transactions = new LinkedList<Transaction>();
		blockchain.add(block);
	}
	
	private int addTransaction(Client sender, Client recipient, int amount){
		transactions.add(new Transaction(sender, recipient, amount));
		return blockchain.get(blockchain.size()-1).index;
	}
	
	private String hash(Block block){
		return Integer.toString(block.hashCode());
		}
	
	
	public void print(){
		for(Block b:blockchain)
			System.out.println(b);
	}
	
	private int proofOfWork(int lastProof){
		int proof = 0;
		
		while (!validProof(lastProof, proof))
			proof+=1;
		return proof;
	}
	
	private boolean validProof(int lastProof, int proof){
		String hash = Integer.toString(Integer.hashCode(((proof+lastProof))));
		if(hash.endsWith("0000"))
			return true;
		else
			return false;
	}
}
