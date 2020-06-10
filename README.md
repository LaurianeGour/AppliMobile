# Projet Application Mobile - Android Studio - CalandarPlus

>J’ai eu pour idée de concevoir une application me permettant de gérer mon temps. En effet, jusqu’alors, j’ai : une application pour visualiser mon emploi du temps de l’université, des to do list pour ne pas oublier de choses à faire, et un tableau qui me permet de faire un emploi du temps complet. J’ai alors pensé à une application me permettant de regrouper tout cela.


>J’ai pensé à utiliser une API de calendrier (Google ?) pour récupérer mon agenda de l’université. Une seconde page permettrait de noter les taches à faire, mais aussi,  optionnellement, d’y associé un temps, un état d’avancement, une ou plusieurs catégorises mais aussi d’indiquer une date limite. Lors de la création d’un nouvel événement dans l’emploi du temps, l’utilisateur pourra sélectionner une tache indiqué dans la to do list qui pré remplira l’évènement.


>Ayant un téléphone Android et ne comptant pas changer de Smartphone, je pense développer mon application à l’aide d’Android Studio.

## Fonctionnalités prévues :
- Actualisation de l’emploi du temps de l’université à intervalle de temps choisit par l’utilisateur -> utilisation d’une API
- Permettre à l’utilisateur de personnaliser le thème de l’application  -> Abandonné le 13/05
- Afficher les informations voulu par l’utilisateur sur l’emploi du temps (titre, lieu, heure par exemple), puis détails lorsque l’on clique sur un évènement.
- Pouvoir noter et catégoriser les taches à faire.
- Pouvoir indiquer l’état d’avancement d’une tache.

## Suivit de projet :
#### Developpement

>Temps de developpement actuel : entre 22 et 25 heures

> Minimum SDK : API 22 Android 5.1 Lollipop : Couvre ~92.3% des appareils

- Création des fragments et des activités
- Création d'un menu de navigation
- Developpement de la navigation entre les pages


#### Outils utilisés :
- Utilisation des librairies Butterrknife et Icepick
- Utilisation des log


## Bibliographie :

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
