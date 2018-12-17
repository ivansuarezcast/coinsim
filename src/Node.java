
public class Node {
	public String address="1";
	Blockchain bc = new Blockchain();
	public void newTransaction(Client sender, Client recipient, int amount) {
		int index = bc.addTransaction(sender, recipient, amount);
		System.out.println("Transaction will be added to block: "+index);
	}
	public void mine() {
		Block lastBlock = bc.getLastBlock();
		int lastProof = lastBlock.proof;
		int proof = bc.proofOfWork(lastProof);
		newTransaction(new Client(), new Client(), 1);//arreglar
		
		String previousHash=bc.hash(lastBlock);
		Block block=bc.addBlock(proof);
		
		System.out.println("New block forged");
		System.out.println("index: "+block.index);
	}
	public Blockchain getChain() {return bc;}
}
