package ar.com.ada.maven.utils;

import java.util.ArrayList;
import java.util.List;

public class Paginator {
    private static final String OPTION_FIRTS = "[I" + Ansi.RESET + "nicio]";
    private static final String OPTION_PREVIOUS = "[A" + Ansi.RESET + "nterior]";
    private static final String OPTION_NEXT = "[S" + Ansi.RESET + "iguiente]";
    private static final String OPTION_LAST = "[U" + Ansi.RESET + "ltimo]";
    private static final String OPTION_EXIT = "[Q" + Ansi.RESET + " para salir]";

    public static final String EDITH = "[E" + Ansi.RESET + "ditar]";
    public static final String DELETE = "[E" + Ansi.RESET + "liminar]";
    public static final String SELECT = "[E" + Ansi.RESET + "legir]";

    public static List<String> buildPaginator(int currentPage, int totalPages) {
        List<String> pages = new ArrayList<>();

        pages.add(OPTION_FIRTS);
        pages.add(OPTION_NEXT);

        for (int i = 1; i <= totalPages; i++) {
            if (i == currentPage + 1)
                pages.add("[" + i + "]" + Ansi.RESET);
            else
                pages.add("[" + i + "]");
        }

        pages.add(OPTION_PREVIOUS);
        pages.add(OPTION_LAST);
        pages.add(OPTION_EXIT);

        return pages;
    }
}
