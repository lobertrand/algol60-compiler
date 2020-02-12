# Projet de compilation (2019-2020)

Réalisation d'un compilateur pour le language Algol60 (avec ANTLR)

## Accès rapides

| Description | Chemin et lien |
|--- | --- |
| Rapport PCL1 | [`doc/PCL1_report.pdf`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/doc/PCL1_report.pdf) |
| Fichier de grammaire | [`src/main/antlr/eu/telecomnancy/Algol60.g`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/main/antlr/eu/telecomnancy/Algol60.g) |
| Programmes de test valides et invalides | [`src/test/resources`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources) |
| Programmes de démonstration PCL1 (analyse syntaxique) | [`src/test/resources/syntax/demo`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources/syntax/demo) |
| Programmes de démonstration PCL2 (analyse sémantique) | [`src/test/resources/semantics/demo`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources/semantics/demo) |

## Commandes

### Exécution du compilateur

<pre>
# En utilisant l'entrée standard
./gradlew run --console=plain -q < <i>TESTFILE</i>
# Ou en passant le chemin du fichier en paramètre
./gradlew run --console=plain -q --args="<i>TESTFILE</i>"
</pre> 

Permet de compiler le projet et d'exécuter le compilateur sur le fichier dont le chemin est spécifié par *TESTFILE*.

### Exécution des tests unitaires

<pre>./gradlew test</pre>
Permet de compiler le projet et d'exécuter tous les tests unitaires

### Création d'un jar exécutable de notre compilateur

<pre>./gradlew jar</pre>
Permet de créer un jar exécutable contenant tous les éléments nécessaires pour pouvoir être exécuté (ANTLR est inclu dedans). Ce fichier est généré à l'emplacement `build/libs/algol60_compiler.jar`

## Observations

### PCL1 (analyse syntaxique)

- Un seul type de boucle `for` (pas de `for while` ni de `for` avec plusieurs indices)
- Le `switch` n'est pas implémenté
- Il manque les réels négatifs
- Pas de mot clé `own`
- Pas de if-expression (seulement le if-statement)

### PCL2 (analyse sémantique)

- Les 3 types de boucle `for` on été implémentés
- Le `switch` a été implémenté
- Toutes les combinaisons de réels, entiers et puissances positifs ou négatifs ont été ajoutées (`-3.14`, `-3#-5`, `3.14#5`, `-3.14#-5`...)
- Les if-expression ont été implémentées
- Pas de mot clé `own`
- Nous avons implémenté des contrôles sémantiques pour tous les éléments de notre grammaire

## Membres du groupe

- Loïc Bertrand *(Chef de projet)*
- Timon Fugier
- Tony Zhou
- Zineb Ziani El Idrissi

