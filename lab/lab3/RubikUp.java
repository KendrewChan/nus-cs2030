class RubikUp extends Rubik {
  public RubikUp(Rubik rubik) {
    super(rubik);
  }

  @Override
  public RubikUp left() {
    return new RubikUp(super.upView().left().downView());
  }

  @Override
  public RubikUp right() {
    return new RubikUp(super.upView().right().downView());
  }

  @Override
  public RubikUp half() {
    return new RubikUp(super.upView().half().downView());
  }
}