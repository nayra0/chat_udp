# chat_udp

Projetos criados como requisito para a disciplina de Redes I, onde foi implementado um chat utilizando o protocolo UDP na arquitetura Cliente/Servidor.

Os projetos foram criados utilizando a linguagem Java com a IDE netbeans, como ser visto pelos arquivos de configuração.

O chat_server representa a implementação do servidor, que fica ouvindo a porta '8080' da máquina(localhost). O servidor mantém o gerenciamento do usuários logados e envia 'mensagens' conforme requisitado pelos usuários.

O chat_client tem a implementação de um chat gráfico. O usuário fornece um 'username' e informações sobre a conexão, 'host' e 'port'. Após logado o usuário poderá:

* enviar mensagens para todos os usuários logados
* obter a lista dos usuários logados
* enviar mensagem privada para um usuário específico
* obter o histórico das mensagens
