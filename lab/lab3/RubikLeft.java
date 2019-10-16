class RubikLeft extends Rubik {
  public RubikLeft(Rubik rubik) {
    super(rubik);
  }

  @Override
  public RubikLeft left() {
    return new RubikLeft(super.leftView().left().rightView());
  }

  @Override
  public RubikLeft right() {
    return new RubikLeft(super.leftView().right().rightView());
  }

  @Override
  public RubikLeft half() {
    return new RubikLeft(super.leftView().half().rightView());
  }
}