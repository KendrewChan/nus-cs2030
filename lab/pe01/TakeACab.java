class TakeACab extends Service {
    public TakeACab() {
        super(33, 0, "TakeACab");
    }

    @Override
    public int computeFare(Request request) {
        return super.computeFare(request) + 200;
    }
}
