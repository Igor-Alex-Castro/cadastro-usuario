 # CRUD - CADASTRO-USUÁRIO


<img src="Imagens/Imagem.jpg" alt="Exemplo imagem" width="500px">

O projeto tem como objetivo o aprendizado de conceitos fundamentais da programação web em Java, abordando temas como Servlets para manipulação de requisições (requests) e respostas (responses), além do uso de JDBC para a execução de scripts e operações com banco de dados. Trata-se de uma base essencial para quem deseja se tornar um bom programador Java web.

## Tecnologias utilizadas
**Java:** Linguagem principal utilizada no desenvolvimento da aplicação.

**JDBC:** Utilizado para realizar operações no banco de dados (SELECT, INSERT, DELETE, UPDATE).

**Oracle SQL:** Banco de dados utilizado na persistência das informações.

**JSP** (JavaServer Pages): Responsável pela camada de apresentação e interação com o usuário.

**Servlets:** Controlam o fluxo de requisições entre o front-end e o back-end.

**Bootstrap:** Framework CSS usado para estilização e responsividade das páginas.

**JavaScript:** Aplicado para interações dinâmicas, como limpeza de formulários.

**AJAX:** Permite enviar e receber dados do servidor sem recarregar a página.

**JasperReports:** Ferramenta utilizada para geração de relatórios em PDF.

**Chart.js:** Biblioteca usada para exibir gráficos dinâmicos na interface.

### Funcionalidades do Projeto.

-  **Conexão com Banco de Dados**<br>
    Estabelecimento de conexão com banco de dados utilizando JDBC.
   
-  **Execução de Scripts com JDBC**<br>
    Operações de CRUD (SELECT, INSERT, DELETE) diretamente com o banco de dados.
   <br>
-  **Login com Autenticação de Usuário**<br>
    Sistema de login com validação de credenciais no banco de dados.
   <br>
- **Controle de Funcionalidade por Perfil de Acesso**<br>
Acesso restrito às funcionalidades com base nos perfis:<br>
   - ADMIN
   - SECRETARIO
   - AUXILIAR

- **Gerenciamento de Sessão**<br>
Manutenção da sessão do usuário logado, com controle de acesso às páginas protegidas.<br>
-  **Cadastro de Usuário com Diversos Campos**<br>
Inclusão de informações completas no cadastro, incluindo:
      - **Dados básicos:** ID, NOME, EMAIL, LOGIN, SENHA

     - **Controle de acesso:** USER_ADMIN, USUARIO_ID, PERFIL

     - **Informações pessoais:** SEXO, DATA_NASCIMENTO, RENDA_MENSAL, CPF

    - **Endereço:** CEP, LOGRADOURO, BAIRRO, LOCALIDADE, UF, NUMERO
    - **Foto:** FOTOUSER, EXTENSAOFOTOUSER
 
-  **Listagem de Usuários**<br>
Exibição dos usuários cadastrados em formato de tabela.

-  **Pesquisa de Usuário**<br>
Filtro para busca de usuários com base em nome campos.

-  **Cadastro e Listagem de Telefones por Usuário**<br>
Associação de múltiplos telefones a um único usuário, com possibilidade de listar e deletar.

-  **Geração de Relatórios**<br>
Relatórios gerais de usuários.

-  **Geração de Relatórios com Filtro por Data de Nascimento**<br>
Relatórios personalizados a partir data inicial e final definidos pelo usuário.

-  **Download de Relatório em PDF com JasperReports**<br>
Exportação dos relatórios em formato PDF utilizando a biblioteca JasperReports.

-  **Geração de Gráficos com Chart.js**<br>
Visualização de dados por meio de gráficos dinâmicos e interativos.

### TELAS DO PROJETO
<p float="left">
  <img src="Imagens/Imagem2.jpg" width="230" />
  <img src="Imagens/Imagem3.jpg" width="300" /> 
  <img src="Imagens/Imagem4.jpg" width="300" />
 <img src="Imagens/Imagem5.jpg" width="285" />
 <img src="Imagens/Imagem6.jpg" width="300" />
 <img src="Imagens/Imagem7.jpg" width="300" />
 <img src="Imagens/Imagem8.jpg" width="300" />
</p>


