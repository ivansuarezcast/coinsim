
public class Node {
	public String address = "1";
	Blockchain bc = new Blockchain(this);

	public void newTransaction(String sender, String recipient, int amount) {
		int index = bc.addTransaction(sender, recipient, amount);
		System.out.println("Transaction will be added to block: " + index);
	}

	public void mine() {
		Block lastBlock = bc.getLastBlock();
		int lastProof = lastBlock.proof;
		int proof = bc.proofOfWork(lastProof);
		newTransaction("0", "1", 1);// arreglar

		String previousHash = bc.hash(lastBlock);
		Block block = bc.addBlock(proof);

		System.out.println("New block forged");
		System.out.println("index: " + block.index);
	}

	public Blockchain getChain() {
		return bc;
	}
	
	public void register() {
		bc.resgisterNode(this);
		
		
	}
	public void resolve() {
		boolean replaced=bc.resolveConflicts();
		if(replaced)
			System.out.println("Our chain was replaced");
		else
			System.out.println("Our chain is authoritative");
	}
}
