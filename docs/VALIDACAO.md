# Validação realizada

Data: 17/09/2026.

- As seis classes de produção foram compiladas com javac 21.0.12.1, usando release 17.
- A demonstração Main executou corretamente para televisão e rádio.
- Todos os fontes de produção e teste foram compilados com Eclipse Compiler for Java (ECJ 3.40.0), em modo Java 17.
- JUnit Platform Console 1.11.4 / Jupiter 5.11.4 executou **15 testes: 15 aprovados, zero falhas, zero ignorados**.
- A imagem PNG foi gerada a partir do PlantUML 1.2025.2 e inspecionada visualmente.

## Limitação do ambiente de validação

O comando Maven `clean verify` foi tentado com Maven 3.9.9, mas o javac encontrou `java.nio.file.AccessDeniedException` ao resolver o caminho de um JAR de dependência no ambiente restrito do Windows. Portanto, o ciclo completo Maven **não foi validado com sucesso neste ambiente**. A compilação integral e os testes foram verificados separadamente com ECJ e o executor oficial do JUnit.

Os comandos padrão para executar em uma instalação local com acesso normal aos arquivos continuam sendo `mvn clean verify` e `java -jar target/bridge-controle-remoto-1.0.0.jar`, conforme o README.

Não foram criados commits nem publicado um repositório remoto. A data limite de entrega não foi informada.
