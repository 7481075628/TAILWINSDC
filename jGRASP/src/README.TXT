The optional native parts of jGRASP are provided in binary form for 32 and 64
bit Windows and macOS. If you are using one of those systems, you do not need
to build these sources.

Building the native parts of jGRASP is not required for nearly complete
functionality on any system. If you are building non-Java programs though, I/O
will likely be block-buffered if the native parts are not built. The native
wedge will also make killing user programs (using the "End" button in the
jGRASP Run I/O window) more reliable. Without this, you may sometimes need to
kill runaway programs or processes launched by user programs using OS tools
(such as "kill" from the command line). The native startup shell allows you to
pop up files in an already running instance of jGRASP as
   jGRASP somefile
on systems that have X Windows, and by clicking on associated files if your OS
supports that.

To build the native executables on Linux, UNIX, and other UNIX-like systems,
run:
   ./configure
followed by:
   ./Make.sh

Linux users: if the startup shell doesn't build and you don't have X headers,
you may need to
  sudo apt-get install libxt-dev
You may also need to get an XT development package.

Report problems to grasp_AT_eng_DOT_auburn_DOT_edu .
