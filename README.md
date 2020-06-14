# Projet Application Mobile - Android Studio - CalandarPlus

>J’ai eu pour idée de concevoir une application me permettant de gérer mon temps. En effet, jusqu’alors, j’ai : une application pour visualiser mon emploi du temps de l’université, des to do list pour ne pas oublier de choses à faire, et un tableau qui me permet de faire un emploi du temps complet. J’ai alors pensé à une application me permettant de regrouper tout cela.


>J’ai pensé à utiliser une API de calendrier (Google ?) pour récupérer mon agenda de l’université. Une seconde page permettrait de noter les taches à faire, mais aussi,  optionnellement, d’y associé un temps, un état d’avancement, une ou plusieurs catégorises mais aussi d’indiquer une date limite. Lors de la création d’un nouvel événement dans l’emploi du temps, l’utilisateur pourra sélectionner une tache indiqué dans la to do list qui pré remplira l’évènement.


>Ayant un téléphone Android et ne comptant pas changer de Smartphone, je pense développer mon application à l’aide d’Android Studio.

## Fonctionnalités prévues initialement :
- Actualisation de l’emploi du temps de l’université à intervalle de temps choisit par l’utilisateur -> utilisation d’une API
- Permettre à l’utilisateur de personnaliser le thème de l’application  -> Abandonné le 13/05
- Afficher les informations voulu par l’utilisateur sur l’emploi du temps (titre, lieu, heure par exemple), puis détails lorsque l’on clique sur un évènement.
- Pouvoir noter et catégoriser les taches à faire.
- Pouvoir indiquer l’état d’avancement d’une tache.

## Suivit de projet :
### Developpement :

#### Temps de developpement actuel : entre 25 et 30 heures :

  Temps compté à partir du 7 mai (je n'y avais pas pensé avant)

    - 7/05 : 3h

    - 9/05 : 4h30

    - 10/05 : 5h30

    - 11/05 : 4h

    - 13/05 : 3h30

    - 12/06 : 1h

    - 13/06 : 1h30

    - 14/06 : 4h30

#### Détails du projet :
> Minimum SDK : API 22 Android 5.1 Lollipop : Couvre ~92.3% des appareils

- Création des fragments et des activités
- Création d'un menu de navigation
- Developpement de la navigation entre les pages
- Création et suppression de catégorie dans la toDoList


#### Outils utilisés :
- Utilisation des librairies Butterrknife et Icepick
- Utilisation des log

### Difficultés rencontrées :
> Beaucoup de mal à commencer le developpement : Premier projet Android Studio donc peu de connaissances + Pas sure de savoir par ou commencer.


> Je pensais utiliser la CalandarView proposée par Android Studio pour pouvoir afficher un calandrier hebdomadaire ou journalier.


> Gros manque de temps : Je ne voulais pas travailler dessus par petits crénaux (car je n'ai pas le temps d'avancer) mais nous avions malheureusement beaucoup de projets/tp noté à faire en parallèle (notamment notre projet de 4ème année dont la soutenance était le 18/06)

### Bibliographie :

- https://developer.android.com
- https://stackoverflow.com
- https://www.developpez.com

- https://openclassrooms.com/fr/courses/2023346-creez-des-applications-pour-android
- https://openclassrooms.com/fr/courses/4568596-construisez-une-interface-utilisateur-flexible-et-adaptative

- http://fr.charles.lescampeurs.org/android-centrer-texte-horizontalement-verticalement-textview/

- https://github.com/JakeWharton/butterknife
- https://github.com/frankiesardo/icepick
- https://abhiandroid.com/ui/calendarview
- https://github.com/ical4j

- https://www.youtube.com/watch?v=8L5sJQ44ntY *vidéo tutoriel todolist*
- https://www.youtube.com/watch?v=wzcFPFCYjFQ *vidéo tutoriel HorizontalScrollView*


#### En cours :
  - Créer la classe tache
  - Créer les layouts tache_recyclerView et cards_task
  - Completer les .java AjouterTache et Todolist
