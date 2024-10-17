@echo off
setlocal

set DIR=%~dp0
set DIR=%DIR:~0,-1%
set MAVEN_OPTS=%MAVEN_OPTS% -Dmaven.multiModuleProjectDirectory=%DIR%

set ERROR_CODE=0
set MAVEN_CMD_LINE_ARGS=%*
if NOT "%MAVEN_CMD_LINE_ARGS%"=="" (
  echo [INFO] Executing: %MAVEN_CMD_LINE_ARGS%
) else (
  echo [INFO] No command line arguments found
)

set MAVEN_CMD_LINE_ARGS=
"%DIR%\mvnw" %MAVEN_CMD_LINE_ARGS% || set ERROR_CODE=1

endlocal & exit /b %ERROR_CODE%