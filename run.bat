@echo off
REM Script para executar a aplicação JavaFX de Controle de Eletrodomésticos
setlocal enabledelayedexpansion

REM Configurar JAVA_HOME
set "JAVA_HOME=C:\Users\Max\.jdks\corretto-25.0.2"
set "PATH=%JAVA_HOME%\bin;%PATH%"

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

