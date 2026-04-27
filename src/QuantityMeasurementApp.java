package FeetMeasurementEquality;
class  FeetMeasurementEquality {
    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }
        private double toFeet() {
            return unit.toFeet(value);
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }
    public static void main(String[] args) {

        System.out.println("1 yard vs 3 feet: " +
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(3.0, LengthUnit.FEET)));

        System.out.println("1 yard vs 36 inches: " +
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(36.0, LengthUnit.INCHES)));

        System.out.println("2 yards vs 2 yards: " +
                new QuantityLength(2.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(2.0, LengthUnit.YARDS)));

        System.out.println("2 cm vs 2 cm: " +
                new QuantityLength(2.0, LengthUnit.CENTIMETERS)
                        .equals(new QuantityLength(2.0, LengthUnit.CENTIMETERS)));

        System.out.println("1 cm vs 0.393701 inches: " +
                new QuantityLength(1.0, LengthUnit.CENTIMETERS)
                        .equals(new QuantityLength(0.393701, LengthUnit.INCHES)));
    }
}