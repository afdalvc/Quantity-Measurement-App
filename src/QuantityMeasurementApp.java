package FeetMeasurementEquality;
class FeetMeasurementEquality {
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);
        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }

        public double fromBase(double baseValue) {
            return baseValue / toFeet;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toBase(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double base = toBase();
            double result = targetUnit.fromBase(base);
            return new QuantityLength(result, targetUnit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            double sum = this.toBase() + other.toBase();
            double result = targetUnit.fromBase(sum);
            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;
            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toBase() - other.toBase()) < 0.0001;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(feet.convertTo(LengthUnit.INCHES)); // 12 inches
        System.out.println(feet.add(inch, LengthUnit.FEET));   // 2 feet

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet3 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(yard.add(feet3, LengthUnit.YARDS)); // 2 yards

        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        System.out.println(cm.convertTo(LengthUnit.INCHES)); // ~1 inch

        System.out.println(feet.equals(inch)); // true
    }
}
