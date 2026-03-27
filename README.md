# Dog API - Testes Automatizados

Projeto de testes automatizados para a [Dog API](https://dog.ceo/dog-api/) usando Java 17, RestAssured e JUnit 5.

## Pré-requisitos

Antes de rodar o projeto, é necessário ter instalado o **Java 17+** e o **Maven 3.8+**. Abaixo estão as instruções de instalação para cada sistema operacional.

### Windows

1. Baixe e instale o JDK 17 em https://adoptium.net/ (marque a opção de configurar o JAVA_HOME durante a instalação).
2. Baixe o Maven em https://maven.apache.org/download.cgi (arquivo .zip).
3. Extraia o Maven em uma pasta, por exemplo `C:\maven`.
4. Adicione o caminho `C:\maven\bin` na variável de ambiente `PATH`:
   - Pesquise "Variáveis de Ambiente" no menu Iniciar
   - Em "Variáveis do sistema", edite `Path` e adicione o caminho da pasta `bin` do Maven
5. Abra um **novo** terminal (cmd ou PowerShell) e verifique:
   ```bash
   java -version
   mvn -version
   ```

### macOS

1. Instale o Java e o Maven usando o [Homebrew](https://brew.sh/):
   ```bash
   brew install openjdk@17
   brew install maven
   ```
2. Se necessário, adicione o Java ao PATH:
   ```bash
   echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```
3. Verifique a instalação:
   ```bash
   java -version
   mvn -version
   ```

### Linux (Ubuntu/Debian)

1. Instale o Java e o Maven via apt:
   ```bash
   sudo apt update
   sudo apt install openjdk-17-jdk maven
   ```
2. Verifique a instalação:
   ```bash
   java -version
   mvn -version
   ```

> Em todos os casos, `java -version` deve mostrar versão 17+ e `mvn -version` deve mostrar o Maven apontando para o Java correto.

## Passo a passo para rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/AbnerTKP/agitest_api.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd agitest_api
   ```

3. Execute os testes:
   ```bash
   mvn clean test
   ```
   Ao final da execução, o Maven exibe um resumo com o total de testes executados, quantos passaram e quantos falharam.

4. (Opcional) Para rodar apenas uma classe de teste específica:
   ```bash
   mvn test -Dtest=BreedsListAllTest
   mvn test -Dtest=BreedImagesTest
   mvn test -Dtest=BreedImageRandomTest
   ```

5. (Opcional) Para gerar um relatório HTML dos resultados:
   ```bash
   mvn surefire-report:report
   ```
   O relatório fica em `target/site/surefire-report.html` — basta abrir no navegador.

## O que é testado

Foram escolhidos 3 endpoints da Dog API, conforme solicitado no teste.

**GET /breeds/list/all** — Lista todas as raças
- Retorna 200 com lista de raças não vazia
- Contém raças conhecidas (bulldog, labrador, poodle)
- Retorna sub-raças corretamente (bulldog tem "french", labrador não tem sub-raças)

**GET /breed/{breed}/images** — Imagens de uma raça
- Retorna imagens válidas para uma raça existente
- URLs correspondem à raça buscada
- Retorna 404 com mensagem de erro para raça inexistente

**GET /breeds/image/random** — Imagem aleatória
- Retorna URL de imagem válida com status de sucesso
- Chamadas consecutivas retornam imagens diferentes (aleatoriedade)

## CI

O projeto possui integração contínua com GitHub Actions. A cada push na branch `main`, os testes são executados automaticamente em um ambiente Ubuntu com Java 17. O relatório HTML do Surefire é salvo como artifact na aba Actions do repositório.

Para executar manualmente: vá na aba **Actions** do repositório e clique em **Run workflow**.

## Estrutura do projeto

```
dog-api-tests/
├── pom.xml                                    # Dependências e configuração do Maven
└── src/test/java/com/dogapi/tests/
    ├── BaseTest.java                          # Configuração base (URL, logs)
    ├── BreedsListAllTest.java                 # Testes do /breeds/list/all
    ├── BreedImagesTest.java                   # Testes do /breed/{breed}/images
    └── BreedImageRandomTest.java              # Testes do /breeds/image/random
```

## Tecnologias

- **Java 17** — Linguagem
- **JUnit 5** — Framework de testes
- **RestAssured** — Testes de API REST
- **Maven** — Build e dependências


## Autor

**Abner Souza**
QA
[LinkedIn](https://linkedin.com/in/abnersouza1)