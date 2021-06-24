Turma: 3ª

Aluno: Ana Alice Gomes Soares - Número: 1

Aluno: Arthur Alves de Souza Costa - Número: 3

Como rodar o programa:

> Como rodar no Eclipse

1. Baixe o arquivo .zip e extraia ele para uma pasta

2. Abra o Eclipse e importe o pacote

    2.1. Vá em File > Import > General > Projects from Folder or Archive
    
    2.2. Clique em Finish
    
    2.3. Clique em Directory e selecione a pasta root (pasta onde está o Main.java)

3. Compile e rode o programa

    3.1. Abra o arquivo Main.java na pasta root, clique com o botão direito, vá em Run as > Java Application

OBS: o projeto não foi codificado no Eclipse, então se houver algum erro de compatibilidade, siga os passos abaixo

> Como rodar no terminal

1. Baixe o arquivo .zip e extraia ele para uma pasta

2. Abra um terminal de sua preferência (ex: CMD, Bash, Powershell)
    OBS: o terminal precisa ter permissão para executar código. Se o terminal em que você estiver
    não for autorizado, abra-o como Administrador

3. Navegue para o diretório root, para onde você extraiu os arquivos (pasta onde está o Main.java)

    Considere que você abriu o terminal no diretório C:\Users\SeuUsuario
    e que você extraiu a pasta do arquivo .zip para a sua área de trabalho

    Comando: 
    
    ``` shell
    cd Desktop/jogo-da-forca
    ```

4. Compile o código com o comando:  

``` shell
javac Main.java
```

5. Execute o programa com o comando: java Main

    OBS: certifique-se de ter uma versão igual compatível com a versão que utilizamos: jdk-11.0.11.9-hotspot
        certifique-se de que o Java esteja acessível nas suas variáveis de ambiente

    Se a compilação resultar em um erro: 
    
    ``` shell
    'javac' is not recognized as an internal or external command
    ```
    
    ou 
    
    ``` shell
    'java' is not recognized as an internal or external command
    ```

    veja o artigo: https://confluence.atlassian.com/confbr1/configurando-a-variavel-java_home-no-windows-933709538.html
