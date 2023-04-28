# Rendu rentmanger Juliette Crespo MIN 1

### Contraintes sur les différentes classes
- Client : un client doit avoir 18 ans, une adresse mail qui n'existe pas dans la base de données et son nom et son prénom doivent faire au moins 3 caractères.
- Vehicle : une voiture doit avoir un constructeur et son nombre de place doit être compris entre 2 et 9.
- Reservation : une voiture ne peut pas être réservée 2 fois le même jour. Une voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur. Une voiture ne peut pas être réservée 30 jours de suite sans pause.

Lors de l'ajout d'un nouveau client ou d'une nouvelle voiture ou d'une nouvelle réservation, si les contraintes ne sont pas respectées, un pop-up java s'ouvre pour informer du problème. 
IL FAUT FERMER LE POP-UP POUR POUVOIR CONTINUE.

De plus, si un client ou un véhicule est supprimé, alors les réservations associées sont supprimées.

### Piste d'amélioration
- Lorsque les contraintes ne sont pas respectées, une notification apparaît sur la page plutôt qu'un pop-up java.
- Faire des tests sur toute fonction services Client/Vehicle/Reservation
