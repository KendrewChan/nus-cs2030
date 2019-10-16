class RubikDown extends Rubik {
  public RubikDown(Rubik rubik) {
    super(rubik);
  }

  @Override
  public RubikDown left() {
    return new RubikDown(super.downView().left().upView());
  }

  @Override
  public RubikDown right() {
    return new RubikDown(super.downView().right().upView());
  }

  @Override
  public RubikDown half() {
    return new RubikDown(super.downView().half().upView());
  }
}