package com.myproject.framework;

import java.io.File;

/**
 * @author Roman Onyshkiv
 */
public class GlobalVars {

    public static final SupportedBrowsers BROWSER = SupportedBrowsers.CHROME;
    public static final String PLATFORM = "MAC OS";
    public static final String ENVIRONMENT = "local";
    public static final String DEF_BROWSER = null;
    public static final String DEF_PLATFORM = null;
    public static final String DEF_ENVIRONMENT = null;

    public static String propFile = "test.properties";
    public static final String TEST_PROPERTIES = new File(propFile).getAbsolutePath();
    public static final String TEST_OUTPUT_PATH = "src/test/java/com/myproject/testOtput/";
    public static final String LOGFILE_PATH = TEST_OUTPUT_PATH + "Logs/";
    public static final String REPORT_PATH = TEST_OUTPUT_PATH + "Report/";

    public static final int TIMEOUT_MINUTE = 60;
    public static final int TIMEOUT_SECOND = 1;
    public static final int TIMEOUT_ZERO = 0;

}
