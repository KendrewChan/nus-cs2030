class RubikBack extends Rubik {
  public RubikBack(Rubik rubik) {
    super(rubik);
  }

  @Override
  public RubikBack left() {
    return new RubikBack(super.backView().left().backView());
  }

  @Override
  public RubikBack right() {
    return new RubikBack(super.backView().right().backView());
  }

  @Override
  public RubikBack half() {
    return new RubikBack(super.backView().half().backView());
  }
}