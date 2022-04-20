# dirty-chat
chat fait en une aprem

## serveur de chat a les fonctionnalités simples suivantes:

- un socket tcp part client 
- 1er message initialise le pseudo du client
- chaque message est réemit en broadcast a tout les autre client
- log des message
- 2 type de message :
  - soit des messages que le client veut diffuser
  - commande
- Les commandes que le client peut envoyer au serveur sont
  - /hist *print les log*
  - /quit *met fin a la session*
