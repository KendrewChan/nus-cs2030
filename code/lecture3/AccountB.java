public class AccountB extends Account {
  public AccountB(int balance) {
    super(balance);
  }

  public boolean checkBalance() {
    return this.balance > 0 && this.balance < 1000;
  }
}