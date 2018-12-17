

public class Transaction {
	Client sender, recipient;
	int amount;
	public Transaction(Client sender, Client recipient, int amount) {
		this.sender=sender;
		this.recipient=recipient;
		this.amount=amount;
	}
	
}
