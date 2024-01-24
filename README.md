
# NYT Articles App

L'application mobile NYT Articles récupère une liste d'articles apparus dans New York Times et offre des fonctionnalités tel que la consultation des articles les plus populaires, la recherche des articles , la lecture d'un article ...

## Fonctionnalités
1. **Affichage des articles les plus populaires de cette semaine**
- L'application affiche une liste des articles les plus populaires de la semaine selon le nombre de vues ou le nombre de partages par email.

2. **Affichage des détails d'un article**
- En cliquant sur un article de la liste, l'utilisateur est redirigé vers une nouvelle page affichant des détails supplémentaires sur l'article.

3. **Recherche d'article**
- Une option de recherche est disponible, permettant à l'utilisateur de rechercher des articles spécifiques selon des mots clés.

4. **Lire l'article**
- En cliquant sur un article de la liste, l'utilisateur est redirigé vers une nouvelle page affichant le contenu de l'article.

5. **Ajout aux Favoris**
- Sur la page de détails du l'article, l'utilisateur peut appuyer sur un bouton pour ajouter l'article à sa liste de favoris.

6. **Affichage des Favoris**
- L'application dispose d'une page distincte permettant à l'utilisateur de consulter la liste de ses articles favoris.


## Architecture

L'application suit l'architecture MVVM (Modèle-Vue-VueModèle) pour une gestion propre des données et une séparation des préoccupations.

## Interfaces Graphiques

Les interfaces graphiques de l'application sont élaborées de manière intuitive et efficace grâce à l'utilisation de Jetpack Compose, une bibliothèque moderne de développement d'interfaces utilisateur pour Android.

## API
|        Lien       | Method |                                         Description                                        |
|:---------------------|:------:|:------------------------------------------------------------------------------------------|
| https://api.nytimes.com/svc/search/v2/articlesearch.json?q={type}    |  GET  | Reetourne une liste d'articles les plus populaires selon le théme de l'article ou les mots clés                                        |
| https://api.nytimes.com/svc/mostpopular/v2/{metric}/7.json        |  GET  | Retourne une liste d'articles les plus populaires selon la métrique qui peut être soit "viewed" soit "shared" soit "emailed" de cette semaine.                                       |

## Screenshots


|Home| Search Articles | Search Articles | Search Results| 
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:|
| ![Home](/Screenshots/12.jpg) | ![Search](/Screenshots/2.jpg) | ![Search Articles](/Screenshots/15.jpg) |![Search Results](/Screenshots/8.jpg) | 
|Popular Articles| Popular Articles by views| Popular Articles by shares| | Top Voted Topics | 
| ![Popular Articles](/Screenshots/10.jpg) | ![Popular Articles by views](/Screenshots/19.jpg) | ![Popular Articles by shares](/Screenshots/11.jpg)|![Top Voted Topics](/Screenshots/1.jpg)| 
|Top Voted Topics| Top Voted Topics | Erros | Erros| 
| ![Top Voted Topics](/Screenshots/14.jpg) | ![Top Voted Topics](/Screenshots/9.jpg) | ![Erros](/Screenshots/5.jpg) |![Erros](/Screenshots/16.jpg) | |




## Technologies Utilisées

- [Kotlin](https://kotlinlang.org/): Langage de programmation principal.
- [Retrofit](https://square.github.io/retrofit/): Client HTTP pour la récupération des données depuis une API.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Gestion des données et de la logique d'affichage.
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences): Stockage des données locales, notamment la liste des articles favoris.
- [Android Jetpack](https://developer.android.com/jetpack): Ensemble de bibliothèques Android recommandées pour le développement des interfaces graphiques.
