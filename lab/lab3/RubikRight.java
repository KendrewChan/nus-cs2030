class RubikRight extends Rubik {
  public RubikRight(Rubik rubik) {
    super(rubik);
  }

  @Override
  public RubikRight left() {
    return new RubikRight(super.rightView().left().leftView());
  }

  @Override
  public RubikRight right() {
    return new RubikRight(super.rightView().right().leftView());
  }

  @Override
  public RubikRight half() {
    return new RubikRight(super.rightView().half().leftView());
  }
}