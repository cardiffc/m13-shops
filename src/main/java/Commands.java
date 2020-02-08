import java.util.Arrays;

public enum Commands {
    ДОБАВИТЬ_МАГАЗИН,
    ДОБАВИТЬ_ТОВАР,
    ВЫСТАВИТЬ_ТОВАР,
    СТАТИСТИКА_ТОВАРОВ,
    help;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


}
