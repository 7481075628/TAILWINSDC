SJe0
SJi0
SJz0
SJf0
SJj0
SJA0
SJg0javac %<IF> %<CLASSES_DIR> -d %<REL_CLASSES_DIR> %<ENDIF> %<IF> %<CHARSET> -encoding %<CHARSET> %<ENDIF> %D -g %D %<JFLAGS> %<FLAGS> %<K>@%<K>%<REL_FILE>
SJk0%<PACKAGE_PATH>
SJB0GNU
SJh0
SJl0
SJC0
SJp0java %S -ea %S %<JFLAGS> %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJr0%<MAIN_PACKAGE_CLASSPATH>
SJD0Java_Stack
SJw0java %S -ea %S %<JFLAGS> %<FLAGS2> -Xnoagent -Djava.compiler=NONE -Xdebug -Xrunjdwp:transport=%<TRANSPORT>,suspend=y,server=y %<MAIN_CLASS> %<ARGS>
SJy0%<MAIN_PACKAGE_CLASSPATH>
SJF0Java_Stack
SJ10javadoc %<IF> %<CHARSET> -encoding %<CHARSET> %<ENDIF> %<IF> %<JAVADOC_ROOT> -link %<JAVADOC_ROOT> %<ENDIF> -docencoding UTF-8 -charset UTF-8 %I -nonavbar -notree -noindex -nohelp -nodeprecatedlist %I -d %<REL_DOC_DEST> %<JFLAGS> %<FLAGS> %<K>@%<K>%<REL_FILE>
SJ40%<DEF_SRC_DIR>
SJ50GNU
SJI0
SJJ0
SJS0
SJU0JGRASP_MAIN_BOUNDS=%<CONTROL_SHELL_BOUNDS>\012CLASSPATH=+%;%<EXTENSION_CLASSPATHS>\012CLASSPATH+=%<JGRASP_CLASSPATHS>%;\012CLASSPATH+=.%;\012CLASSPATH+=%<TARGET_CLASSPATHS>%;\012Compile +Document\012CLASSPATH+=%<SRC_CLASSPATHS>%;\012All\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Debug\012CLASSPATH=+%;%<INJECTION_CLASSPATHS>\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full Java SE, not just the JRE, installed.\\nJava is available from https://www.oracle.com/java/technologies .\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
SPATH+=%<TARGET_CLASSPATHS>%;\012Compile +Document\012CLASSPATH+=%<SRC_CLASSPATHS>%;\012All\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Debug +Debug_Applet\012CLASSPATH=+%;%<INJECTION_CLASSPATHS>\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full JDK, not just the JRE, installed.\\nThe JDK is available from https://www.oracle.com/technetwork/java/index.html.\012Run_Applet +Debug_Applet\012ADD_APPLETVIEWER_CLASSPATH==Y\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            SJe0
SJi0
SJz0
SJf0
SJj0
SJA0
SJg0javac %<IF> %<CLASSES_DIR> -d %<REL_CLASSES_DIR> %<ENDIF> %D -g %D %<FLAGS> %<REL_FILE>
SJk0%<PACKAGE_PATH>
SJB0f1-(\\S(?:\\s*\\S)*):(\\d+):.*
SJh0
SJl0
SJC0
SJp0java %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJr0%<MAIN_PACKAGE_CLASSPATH>
SJD0cu1-\\s*at (\\S+)\\.[^.]+\\(([^:]+):(\\d+)\\)
SJs0appletviewer %<FLAGS2> %<html_file>
SJu0%<MAIN_PACKAGE_CLASSPATH>
SJE0cu1-\\s*at (\\S+)\\.[^.]+\\(([^:]+):(\\d+)\\)
SJw0java %<FLAGS2> -Xnoagent -Djava.compiler=NONE -Xdebug -Xrunjdwp:transport=%<TRANSPORT>,suspend=y,server=y %<MAIN_CLASS> %<ARGS>
SJy0%<MAIN_PACKAGE_CLASSPATH>
SJF0cu1-\\s*(?:at|\\[\\d+\\]) (\\S+)\\.[^.]+\\(([^:]+):(\\d+)SJe0
SJi0
SJz0
SJf0
SJj0
SJA0
SJg0javac %<IF> %<CLASSES_DIR> -d %<REL_CLASSES_DIR> %<ENDIF> %<IF> %<CHARSET> -encoding %<CHARSET> %<ENDIF> %D -g %D %<JFLAGS> %<FLAGS> %<K>@%<K>%<REL_FILE>
SJk0%<PACKAGE_PATH>
SJB0GNU
SJh0
SJl0
SJC0
SJp0java %S -ea %S %<JFLAGS> %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJr0%<MAIN_PACKAGE_CLASSPATH>
SJD0Java_Stack
SJw0jdb %S -connect "com.sun.jdi.CommandLineLaunch:options=-ea" %S %<JFLAGS> %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJy0%<MAIN_PACKAGE_CLASSPATH>
SJF0Java_Stack_or_jdb
SJ10javadoc %<IF> %<CHARSET> -encoding %<CHARSET> %<ENDIF> %<IF> %<JAVADOC_ROOT> -link %<JAVADOC_ROOT> %<ENDIF> -docencoding UTF-8 -charset UTF-8 %I -nonavbar -notree -noindex -nohelp -nodeprecatedlist %I -d %<REL_DOC_DEST> %<JFLAGS> %<FLAGS> %<K>@%<K>%<REL_FILE>
SJ40%<DEF_SRC_DIR>
SJ50GNU
SJI0
SJJ0
SJS0
SJU0JGRASP_MAIN_BOUNDS=%<CONTROL_SHELL_BOUNDS>\012CLASSPATH=+%;%<EXTENSION_CLASSPATHS>\012CLASSPATH+=%<JGRASP_CLASSPATHS>%;\012CLASSPATH+=.%;\012CLASSPATH+=%<TARGET_CLASSPATHS>%;\012Compile +Document\012CLASSPATH+=%<SRC_CLASSPATHS>%;\012All\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Debug\012CLASSPATH=+%;%<INJECTION_CLASSPATHS>\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full Java SE, not just the JRE, installed.\\nJava is available from https://www.oracle.com/java/technologies .\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
\\nJava is available from https://www.oracle.com/java/technologies .\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
om/technetwork/java/index.html.\012Run_Applet +Debug_Applet\012ADD_APPLETVIEWER_CLASSPATH==Y\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
(([^:]+):(\\d+)\\)
SJV0
SJY0
SJZ0
SJ10javadoc %I -nonavbar -notree -noindex -nohelp -nodeprecatedlist %I -d %<REL_DOC_DEST> %<FLAGS> %K@%K%<REL_FILE>
SJ40%<PACKAGE_PATH>
SJ50f1-(\\S(?:\\s*\\S)*):(\\d+):.*
SJI0
SJJ0
SJS0If the JDK was found when starting jGRASP, the JDK "javac"\012compiler will be used as the compiler. Otherwise, the first "javac"\012on the PATH (if any) will be used.\012
SJU0CLASSPATH=+%;%<EXTENSION_CLASSPATHS>\012CLASSPATH+=%<JGRASP_CLASSPATHS>%;\012CLASSPATH+=.%;\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full JDK, not just the JRE, installed.\\nThe JDK is available from java.sun.com.\012Run_Applet +Debug_Applet\012ADD_APPLETVIEWER_CLASSPATH==Y\012
SJ60
SJ70
SJ80
BJd01
BJf01
                                                                                     SJe0
SJi0
SJz0
SJf0
SJj0
SJA0
SJg0javac %<IF> %<CLASSES_DIR> -d %<REL_CLASSES_DIR> %<ENDIF> %<IF> %<CHARSET> -encoding %<CHARSET> %<ENDIF> %D -g %D %<JFLAGS> %<FLAGS> %<K>@%<K>%<REL_FILE>
SJk0%<PACKAGE_PATH>
SJB0GNU
SJh0
SJl0
SJC0
SJp0java %S -ea %S %<JFLAGS> %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJr0%<MAIN_PACKAGE_CLASSPATH>
SJD0Java_Stack
SJw0java %S -ea %S %<JFLAGS> %<FLAGS2> -Xnoagent -Djava.compiler=NONE -Xdebug -Xrunjdwp:transport=%<TRANSPORT>,suspend=y,server=y %<MAIN_CLASS> %<ARGS>
SJy0%<MAIN_PACKAGE_CLASSPATH>
SJF0Java_Stack
SJ10javadoc %<IF> %<CHARSET> -encoding %<CHARSET> %<ENDIF> %<IF> %<JAVADOC_ROOT> -link %<JAVADOC_ROOT> %<ENDIF> -docencoding UTF-8 -charset UTF-8 %I -nonavbar -notree -noindex -nohelp -nodeprecatedlist %I -d %<REL_DOC_DEST> %<JFLAGS> %<FLAGS> %<K>@%<K>%<REL_FILE>
SJ40%<DEF_SRC_DIR>
SJ50GNU
SJI0
SJJ0
SJS0
SJU0JGRASP_MAIN_BOUNDS=%<CONTROL_SHELL_BOUNDS>\012CLASSPATH=+%;%<EXTENSION_CLASSPATHS>\012CLASSPATH+=%<JGRASP_CLASSPATHS>%;\012CLASSPATH+=%<VIEWER_CLASSPATHS>%;\012CLASSPATH+=.%;\012CLASSPATH+=%<TARGET_CLASSPATHS>%;\012Compile +Document\012CLASSPATH+=%<SRC_CLASSPATHS>%;\012All\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Debug\012CLASSPATH=+%;%<INJECTION_CLASSPATHS>\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full Java SE, not just the JRE, installed.\\nJava is available from https://www.oracle.com/java/technologies .\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
IEWER_CLASSPATHS>%;\012CLASSPATH+=.%;\012CLASSPATH+=%<TARGET_CLASSPATHS>%;\012Compile +Document\012CLASSPATH+=%<SRC_CLASSPATHS>%;\012All\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Debug +Debug_Applet\012CLASSPATH=+%;%<INJECTION_CLASSPATHS>\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full JDK, not just the JRE, installed.\\nThe JDK is available from https://www.oracle.com/technetwork/java/index.html.\012Run_Applet +Debug_Applet\012ADD_APPLETVIEWER_CLASSPATH==Y\012Document\012CLASSPATH=+%;%<TOOL_CLASSPATHS>\012\012
SJ60
SJ70
SJ80
BJd01
BJf01
BJi01
BJj01
BJk01
BJl01
S> %K@%K%<REL_FILE>
SJ40%<PACKAGE_PATH>
SJ50f1-(\\S(?:\\s*\\S)*):(\\d+):.*
SJI0
SJJ0
SJS0Environment for JUnit test files.\012
SJU0CLASSPATH=+%;%<EXTENSION_CLASSPATHS>\012CLASSPATH+=%<JGRASP_CLASSPATHS>%;\012CLASSPATH+=.%;\012PATH+=%<JAVA_BIN_DIR>%;\012PATH+=%<JGRASP_PATHS>%;\012Compile\012NOT_FOUND_MESSAGE==Make sure you have the full JDK, not just the JRE, installed.\\nThe JDK is available from java.sun.com.\012Run_Applet +Debug_Applet\012ADD_APPLETVIEWER_CLASSPATH==Y\012
SJ60
SJ70
SJ80
BJd01
BJf01
 +E +T1 %<FLAGS> %<REL_FILE>
SJk0%<PACKAGE_PATH>
SJB0f1234-(\\S(?:\\s*\\S)*):(\\d+):(\\d+):(\\d+):(\\d+):.*
SJh0jikes -nowrite +E +T1 %<FLAGS> %<REL_FILE>
SJl0%<PACKAGE_PATH>
SJC0f1234-(\\S(?:\\s*\\S)*):(\\d+):(\\d+):(\\d+):(\\d+):.*
SJp0java %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJr0%<MAIN_PACKAGE_CLASSPATH>
SJD0cu1-\\s*at (\\S+)\\.[^.]+\\(([^:]+):(\\d+)\\)
SJs0appletviewer %<FLAGS2> %<html_file>
SJu0%<MAIN_PACKAGE_CLASSPATH>
SJE0cu1-\\s*at (\\S+)\\.[^.]+\\(([^:]+):(\\d+)\\)
SJw0jdb %<FLAGS2> %<MAIN_CLASS> %<ARGS>
SJy0%<MAIN_PACKAGE_CLASSPATH>
SJF0cu1-\\s*(?:at|\\[\\d+\\]) (\\S+)\\.[^.]+\\(([^:]+):(\\d+)\\)
SJV0
SJY0
SJZ0
SJ10javadoc %I -nonavbar -notree -noindex -nohelp -nodeprecatedlist %I -d %<REL_DOC_DEST> %<FLAGSSJe0
SJi0
SJz0
SJf0
SJj0
SJA0
SJg0javac %<IF> %<CLASSES_DIR> -d %<REL_CLASSES_DIR> %<ENDIF> %D -g %D %<FLAGS> %<RE