public class Account {
  protected final int balance;

  public Account(int balance) {
    this.balance = balance;
  }

  public boolean checkBalance() {
    return this.balance > 0 && this.balance < 100;
  }
}