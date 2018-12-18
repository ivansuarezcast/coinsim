
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Blockchain bc = new Blockchain();
		//bc.print();
		
		Node node = new Node();
		for(int i = 0;i<10;i++)
			node.mine();
		node.bc.print();
		node.newTransaction("1", "2", 155);
	}

}
