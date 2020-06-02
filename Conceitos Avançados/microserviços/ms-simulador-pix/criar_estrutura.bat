@echo off
REM Script para mover arquivos de teste para src/test/java

REM Define a raiz do projeto
set PROJECT_DIR=%~dp0

REM Caminho atual dos testes (onde estão misturados com o código principal)
set MAIN_DIR=%PROJECT_DIR%src\main\java\com\ocooldev\pix\ms_simulador_pix

REM Caminho destino dos testes
set TEST_DIR=%PROJECT_DIR%src\test\java\com\ocooldev\pix\ms_simulador_pix

REM Cria a estrutura de pastas de teste se não existir
if not exist "%TEST_DIR%" mkdir "%TEST_DIR%"
if not exist "%TEST_DIR%\auth" mkdir "%TEST_DIR%\auth"
if not exist "%TEST_DIR%\controller" mkdir "%TEST_DIR%\controller"
if not exist "%TEST_DIR%\service" mkdir "%TEST_DIR%\service"
if not exist "%TEST_DIR%\security" mkdir "%TEST_DIR%\security"

REM Move os arquivos de teste para src/test/java
move "%MAIN_DIR%\auth\AuthControllerTest.java" "%TEST_DIR%\auth\"
move "%MAIN_DIR%\controller\PixControllerTest.java" "%TEST_DIR%\controller\"
move "%MAIN_DIR%\service\PixServiceTest.java" "%TEST_DIR%\service\"
move "%MAIN_DIR%\security\JwtUtilTest.java" "%TEST_DIR%\security\"

echo.
echo ==== Arquivos de teste movidos com sucesso para src/test/java ====
pause