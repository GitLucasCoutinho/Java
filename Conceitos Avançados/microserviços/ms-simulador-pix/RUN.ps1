# Script para rodar o Simulador PIX
# Uso: .\RUN.ps1

Write-Host "========================================" -ForegroundColor Green
Write-Host "  Iniciando ms-simulador-pix" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Compilar
Write-Host "[1/2] Compilando projeto..." -ForegroundColor Yellow
mvn clean package -DskipTests -q

if ($LASTEXITCODE -ne 0) {
    Write-Host "Erro na compilação!" -ForegroundColor Red
    exit 1
}

Write-Host "[OK] Projeto compilado com sucesso!" -ForegroundColor Green
Write-Host ""

# Rodar
Write-Host "[2/2] Rodando aplicação..." -ForegroundColor Yellow
Write-Host ""

java -jar target/ms-simulador-pix-1.0.0.jar

Write-Host ""
Write-Host "========================================" -ForegroundColor Red
Write-Host "  Aplicação encerrada" -ForegroundColor Red
Write-Host "========================================" -ForegroundColor Red

