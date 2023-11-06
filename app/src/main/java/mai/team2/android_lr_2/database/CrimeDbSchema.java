package mai.team2.android_lr_2.database;

public class CrimeDbSchema {                  // строковые константы для описания таблицы (базы данных)
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
