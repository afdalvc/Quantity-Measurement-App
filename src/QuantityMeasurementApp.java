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
        public double fromBase(double base) {
            return base / toFeet;
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
        private double baseValue() {
            return unit.toBase(value);
        }
        public QuantityLength convertTo(LengthUnit target) {
            return new QuantityLength(target.fromBase(baseValue()), target);
        }
        public QuantityLength add(QuantityLength other, LengthUnit target) {
            double sum = this.baseValue() + other.baseValue();
            return new QuantityLength(target.fromBase(sum), target);
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof QuantityLength)) return false;
            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.baseValue() - other.baseValue()) < 0.0001;
        }
        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }
    public enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);
        private final double toKg;
        WeightUnit(double toKg) {
            this.toKg = toKg;
        }
        public double toBase(double value) {
            return value * toKg;
        }
        public double fromBase(double base) {
            return base / toKg;
        }
    }
    public static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;
        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }
        private double baseValue() {
            return unit.toBase(value);
        }
        public QuantityWeight convertTo(WeightUnit target) {
            return new QuantityWeight(target.fromBase(baseValue()), target);
        }
        public boolean equals(Object obj) {
            if (!(obj instanceof QuantityWeight)) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Math.abs(this.baseValue() - other.baseValue()) < 0.0001;
        }
        public QuantityWeight add(QuantityWeight other, WeightUnit target) {
            double sum = this.baseValue() + other.baseValue();
            return new QuantityWeight(target.fromBase(sum), target);
        }
        @Override
        public String toString() {
            return "Weight(" + value + ", " + unit + ")";
        }
    }
    public static void main(String[] args) {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println(l1.convertTo(LengthUnit.INCHES)); // 12
        System.out.println(l1.add(l2, LengthUnit.FEET));      // 2 feet
        System.out.println(l1.equals(l2));
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println(w1.equals(w2));                    // true
        System.out.println(w1.convertTo(WeightUnit.GRAM));    // 1000 g
        QuantityWeight w3 = new QuantityWeight(2.0, WeightUnit.POUND);
        System.out.println(w3.convertTo(WeightUnit.KILOGRAM));
        QuantityWeight sum = w1.add(w2, WeightUnit.KILOGRAM);
        System.out.println(sum); // 2 kg
    }
}