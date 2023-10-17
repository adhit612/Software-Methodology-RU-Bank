package classes;

public enum CampusCode {
    ZERO {
        @Override
        public String toString() {
            return "NEW_BRUNSWICK";
        }
    },
    ONE {
        @Override
        public String toString() {
            return "NEWARK";
        }
    },
    TWO {
        @Override
        public String toString() {
            return "CAMDEN";
        }
    }
}
