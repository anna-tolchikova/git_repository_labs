package by.bsuir.forlabs.utilits;

import org.apache.log4j.Logger;

public class ExceptionsPrintWrapper {

    private static final Logger log = Logger.getLogger(ExceptionsPrintWrapper.class);

    public static void printException(Throwable ex){
        Throwable tmp = ex;

        if (tmp.getCause() != null) {
            do {
                tmp = tmp.getCause();
            } while (tmp.getCause() != null);
        }

        log.error("--------------------");
        log.error(tmp);
        for (int i = 0; i < tmp.getStackTrace().length - 5; ++i){
            log.error(tmp.getStackTrace()[i]);
        }
        log.error("--------------------");
    }
}
