public class AccountA extends Account {
  public AccountA(int balance) {
    super(balance);
  }

  public boolean checkBalance() {
    return this.balance > 0 && this.balance < 10;
  }
}