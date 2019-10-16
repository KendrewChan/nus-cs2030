class Student {
    private String name;
    private String grades[];
    private Internship itns[];
    private boolean isHired;

    public Student(String name, String grades[], Internship itns[]) {
        this.name = name;
        this.grades = grades;
        this.itns = itns;
        this.isHired = false;
    }

    public boolean getIsHired() {
        return this.isHired;
    }
    
    public String getName() {
        return this.name;
    }

    public void setIsHired(boolean x) {
        this.isHired = x;
    }

    public double getGpa() {
        return Gpa.calculate(this.grades);
    }

    public double getItnsPts() {
        double pts = 0.0;
        
        if (this.itns != null) {
            for (Internship itn : this.itns) {
                pts += itn.getPts();
            }
        }

        return pts;
    }

    public double getTotalPts() {
        return getGpa() + getItnsPts();
    }

    @Override
    public String toString() {
        return String.format("\nName: %s\nGPA: %.2f\nTotal points: %.2f", this.name, this.getGpa(), this.getTotalPts());
    }
}
