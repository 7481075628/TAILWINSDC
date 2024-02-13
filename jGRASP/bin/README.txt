Use jgrasp.exe to start jGRASP on Windows systems.

Note to computer lab administrators: if you have installed on Windows by
unzipping rather than using the exe installer, you should use jgrasp64.exe on
64 bit systems.

On Linux/UNIX, depending on what you used to unzip the installation, you may
need to do   chmod -R 775 jgrasp   from the command line at the directory
containing the installation. This will mark all the executables as executable
(and everything else, but that doesn't matter).

On Linux/UNIX, use the  jgrasp  script in this directory to start jGRASP.
"java" must be on your system path for the script to work. You can run
create_linux_launcher.sh  in this directory to create a desktop launcher for
jGRASP.

On Linux/UNIX, the first time you compile or run jGRASP will try to build
native executables used to connect to external programs. If that fails,
running external programs will still work, but with potential I/O buffering
(line buffering for Java applications, block buffering for C or C++
applications). For instructions on how to build the native exeuctables
manually, go to the "src" directory in this installation and follow the
instructions in the "README.txt" file there.

For running from a portable drive on Windows, use   jgrasp_portable.bat  . See
the comments in   jgrasp_portable.bat for more instructions on running from a
portable drive.
