@rem Portable drive startup file for Windows.
@rem To specify a Java installation on the portable drive
@rem on Windows, you can use the -a flag with
@rem the drive variable. For example, if Java is installed
@rem in "jdk1.13.0" at the root of the drive, you would add:
@rem  -a %~d0jdk1.13.0\bin\java.exe
@rem
@rem You can also use winconfig_portable.bat to choose the
@rem portable-drive-relative Java installation and other
@rem startup settings.

start jgrasp64 -p auto
