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
        private double toFeet() {
            return unit.toFeet(value);
        }
        QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }
        QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }
            double sumFeet = this.toFeet() + other.toFeet();
            double resultValue = targetUnit.fromFeet(sumFeet);
            return new QuantityLength(resultValue, targetUnit);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }
    public static void main(String[] args) {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println(a.add(b, LengthUnit.FEET));       // 2.0 FEET
        System.out.println(a.add(b, LengthUnit.INCHES));     // 24.0 INCHES
        System.out.println(a.add(b, LengthUnit.YARDS));      // ~0.667 YARDS
        QuantityLength c = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength d = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println(c.add(d, LengthUnit.YARDS));      // 3.0 YARDS
        QuantityLength e = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength f = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        System.out.println(e.add(f, LengthUnit.CENTIMETERS)); // ~5.08 CM
        QuantityLength g = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength h = new QuantityLength(-2.0, LengthUnit.FEET);
        System.out.println(g.add(h, LengthUnit.INCHES));     // 36.0 INCHES
    }
}