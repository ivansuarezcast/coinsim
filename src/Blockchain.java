import java.util.LinkedList;
import java.util.List;

public class Blockchain {
	List<Block> blockchain;
	List<Transaction> transactions;
	List<Node> nodes;

	public Blockchain(Node node) {
		blockchain = new LinkedList<Block>();
		transactions = new LinkedList<Transaction>();
		nodes = new LinkedList<Node>();
		addBlock(100);
	}

	public Block addBlock(int proof) {
		String hash;
		if (blockchain.isEmpty()) {
			hash = "1";
		} else {
			hash = hash(blockchain.get(blockchain.size() - 1));
		}
		Block block = new Block(blockchain.size() + 1, System.currentTimeMillis(), transactions, proof, hash);
		transactions = new LinkedList<Transaction>();
		blockchain.add(block);
		return block;
	}

	public int addTransaction(String sender, String recipient, int amount) {
		transactions.add(new Transaction(sender, recipient, amount));
		return blockchain.get(blockchain.size() - 1).index;
	}

	public String hash(Block block) {
		return Integer.toString(block.hashCode());
	}

	public void print() {
		for (Block b : blockchain)
			System.out.println(b);
	}

	public int proofOfWork(int lastProof) {
		int proof = 0;

		while (!validProof(lastProof, proof))
			proof += 1;
		return proof;
	}

	private boolean validProof(int lastProof, int proof) {
		String hash = Integer.toString(Integer.hashCode(((proof + lastProof))));
		if (hash.endsWith("0000000"))
			return true;
		else
			return false;
	}

	public Block getLastBlock() {
		return blockchain.get(blockchain.size() - 1);
	}
	
	public void resgisterNode(Node node) {
		nodes.add(node);
	}
	
	public boolean validChain(List <Block> blockchain) {
		Block last_block=blockchain.get(blockchain.size()-1);
		int currentIndex=1;
		
		while(currentIndex < blockchain.size()) {
			Block block = blockchain.get(currentIndex);
			if (block.hashCode()==last_block.hashCode())
				return false;
			
			if(validProof(last_block.proof, block.proof))
				return false;
			
			last_block = block;
			currentIndex++;
		}
		return true;
	}
	
	
	public boolean resolveConflicts() {
		List<Node> neighbours = nodes;
		List<Block> newChain = null;
		
		int maxLength = blockchain.size();
		
		for (Node node: neighbours) {
			List<Block> chain = node.bc.blockchain;
			int length = chain.size();
			
			if ((length > maxLength)&&(validChain(chain))){
				maxLength = length;
				newChain = chain;
			}
		}
		
		if(newChain!=null) {
			this.blockchain = newChain;
			return true;
		}
		return false;
	}
	
	
}
