# Auteurs

- Victor Badin
- Arthur Mermet

# Projet Génie Logiciel

L’objectif de ce travail est de réaliser l’analyse, la conception et l’implémentation d’une application
correspondant aà un cahier des charges donné.

# Build et exécution

## Lancer le programme avec Maven

```bash
git clone git@github.com:arthurrrm/gl-info5.git
cd gl-info5/gl/
mvn clean compile exec:java
```

## Lancer le programme avec l'IDE

Ouvrir le projet dans votre IDE favori (IntelliJ, Eclipse, ...), puis lancer la classe `src.App`.

## Manuel utilisateur (concise et exhaustif)

### Connexion
- Saisir un nom d’utilisateur existant ou un nouveau (exemple Alice ou MJ1 chargés depuis `gl/src/main/resources/demo`).
- Valider pour accéder au tableau de bord.

### Tableau de bord
- Contenu principal: liste de vos personnages (et, si vous êtes MJ, ceux dont vous êtes MJ) et la liste des parties accessibles.
- Sélection d’un personnage: active certaines actions selon votre rôle.

Actions (barre du haut):
- Créer personnage: ouvre le formulaire de création. Renseigner nom, date de naissance, profession, univers, biographie initiale, et le MJ proposé. Le personnage apparaît en “Nouvelle proposition” dans la file du MJ ciblé.
- Proposer partie: ouvre la création de partie (titre, univers, situation initiale, lieu, date). La partie est listée au centre une fois créée.
- Gérer les demandes MJ: ouvre la page des demandes à traiter pour vous en tant que MJ (nouveaux personnages ou demandes de changement de MJ vous visant).
- Demandes transfert: ouvre la page listant les demandes de transfert de propriété qui vous sont adressées (vous deviendriez propriétaire du personnage si vous acceptez).
- Se déconnecter: retour à l’écran de connexion.

Actions (zone sous la liste des personnages):
- Voir biographie: affiche la biographie du personnage sélectionné (lecture; si vous êtes propriétaire, accès complet).
- Changer de MJ: demande un nouveau MJ pour le personnage sélectionné. Conditions: vous devez être propriétaire du personnage et le personnage doit déjà avoir un MJ.
- Demander transfert: propose de transférer la propriété du personnage sélectionné à un autre joueur. Condition: vous devez être propriétaire du personnage.

Etats et libellés des personnages:
- Actif: personnage validé et jouable.
- [Nouvelle proposition]: en attente d’un MJ initial (dans la file du MJ proposé).
- [Demande de transfert]: en attente d’un changement de MJ (file du MJ visé).
- [Demande de transfert de propriétaire]: en attente d’un transfert de propriété (file du joueur visé).

### Gérer les demandes MJ (en tant que MJ)
- Liste: affiche les personnages pour lesquels vous êtes le MJ proposé et qui sont en attente (création ou changement de MJ).
- Consulter la bio: ouvre la biographie du personnage sélectionné en lecture.
- Accepter: valide la demande (devient votre personnage en tant que MJ, ou confirme le changement de MJ).
- Refuser: refuse la demande. Pour une nouvelle création, le personnage est supprimé; pour un changement de MJ, la demande est simplement annulée.
- Retour: revient au tableau de bord.

### Gérer les demandes de transfert de propriété (en tant que joueur ciblé)
- Accès: bouton “Demandes transfert” depuis le tableau de bord.
- Accepter transfert: vous devenez propriétaire du personnage; l’état redevient Actif.
- Refuser transfert: la demande est annulée; l’ancien propriétaire reste inchangé.
- Retour: revient au tableau de bord.

### Création de personnage
- Champs: Nom, Date de naissance, Profession, Univers, Biographie initiale, MJ proposé (choix parmi les utilisateurs).
- Validation: le personnage apparaît chez vous et dans la file “Gérer les demandes MJ” du MJ proposé.

### Création de partie
- Champs: Titre, Univers, Situation initiale, Lieu, Date.
- Validation: la partie s’ajoute à la section centrale du tableau de bord avec son statut.

### Raccourcis visuels
- La section centrale liste vos parties (titre, univers, statut). Cliquer sur une partie pour l’ouvrir.
- Les libellés entre crochets à côté des personnages indiquent les demandes en attente (MJ ou transfert de propriété).
