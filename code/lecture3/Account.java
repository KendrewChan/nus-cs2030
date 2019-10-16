public class Account {
  protected final int balance;

  public Account(int balance) {
    this.balance = balance;
  }

  public boolean checkBalance() {
    assert(this.balance > 0 && this.balance < 100);

    return true;
  }
}