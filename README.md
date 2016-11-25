# GeoMarvelAlternative

Versão alternativa do meu projeto de LP3 para cópia livre.

Informações importantes:

1. Autenticação no Wildfly: USER: admin SENHA: !@#admin123
2. Preencher as chaves da aplicação com as suas da Marvel (https://developer.marvel.com/account) no arquivo \ModuloEJB\src\java\ejb\beans\Marvel.java
3. O sistema de coordenadas que a aplicação utiliza e o que está cadastrado na TB_POSICAO é o EPSG:900913 Google Maps Global Mercator. Conversor bom: https://epsg.io/transform
4. A parte de apresentação amigável dos dados dentro do pop-up (nome do usuário, nome do personagem e imagem do personagem) não está implementada, use sua criatividade.
5. Se for rodar a aplicação num ambiente com proxy, descomentar as linhas referentes no arquivo \ModuloEJB\src\java\ejb\beans\Marvel.java e preencher com as informações de proxy atuais.
6. Usuário para acesso do web service: USER: user SENHA: !@#user123
