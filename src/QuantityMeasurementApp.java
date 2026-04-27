package FeetMeasurementEquality;
class FeetMeasurementEquality {
    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);
        private final double toFeet;
        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }
        double toFeet(double value) {
            return value * toFeet;
        }
        double fromFeet(double feetValue) {
            return feetValue / toFeet;
        }
    }
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }
        double convertTo(LengthUnit targetUnit) {
            double feetValue = unit.toFeet(value);
            return targetUnit.fromFeet(feetValue);
        }
        QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Null value");
            }
            double thisFeet = this.unit.toFeet(this.value);
            double otherFeet = other.unit.toFeet(other.value);
            double sumFeet = thisFeet + otherFeet;
            double resultValue = this.unit.fromFeet(sumFeet);
            return new QuantityLength(resultValue, this.unit);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(
                    this.unit.toFeet(this.value),
                    other.unit.toFeet(other.value)
            ) == 0;
        }
        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }
    public static void main(String[] args) {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println(a.add(b)); // Quantity(2.0, FEET)
        QuantityLength c = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength d = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println(c.add(d)); // Quantity(24.0, INCHES)
        QuantityLength e = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength f = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println(e.add(f)); // Quantity(2.0, YARDS)
        QuantityLength g = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength h = new QuantityLength(1.0, LengthUnit.INCHES);
        System.out.println(g.add(h)); // Quantity(~5.08, CENTIMETERS)
    }
}