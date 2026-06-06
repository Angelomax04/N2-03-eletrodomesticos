@echo off
REM Script para executar a aplicação JavaFX de Controle de Eletrodomésticos
setlocal enabledelayedexpansion

REM Configurar JAVA_HOME automaticamente (sem precisar definir manualmente)
REM Estratégia:
REM 1) Se JAVA_HOME já estiver definido, mantém.
REM 2) Senão, tenta derivar de onde o 'java.exe' está no PATH.
if "%JAVA_HOME%"=="" (
    for /f "delims=" %%J in ('where java 2^>nul') do (
        set "_JAVA_EXE=%%J"
        goto :haveJavaExe
    )
    goto :noJava

    :haveJavaExe
    REM Remove o \\bin\\java.exe para obter o diretório do JDK/JRE.
    set "JAVA_HOME=%_JAVA_EXE%"
    set "JAVA_HOME=%JAVA_HOME:\\bin\\java.exe=%"
)

REM Ajustar PATH se conseguimos JAVA_HOME
if not "%JAVA_HOME%"=="" (
    if exist "%JAVA_HOME%\\bin\\java.exe" (
        set "PATH=%JAVA_HOME%\\bin;%PATH%"
    )
)

:noJava


echo.
echo ====================================
echo Controle de Eletrodomésticos v1.0
echo ====================================
echo.

cd /d "%~dp0"

echo Compilando o projeto...
call mvnw.cmd clean compile

if errorlevel 1 (
    echo.
    echo ERRO na compilação!
    pause
    exit /b 1
)

echo.
echo Iniciando aplicação...
echo.

call mvnw.cmd javafx:run

pause

