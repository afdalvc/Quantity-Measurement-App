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
        static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            double feetValue = source.toFeet(value);
            return target.fromFeet(feetValue);
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
    }
    public static void main(String[] args) {
        System.out.println(QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));   // 12.0
        System.out.println(QuantityLength.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));   // 9.0
        System.out.println(QuantityLength.convert(36.0, LengthUnit.INCHES, LengthUnit.YARDS));// 1.0
        System.out.println(QuantityLength.convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES)); // ~0.393701
        System.out.println(QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES));  // 0.0
    }
}
