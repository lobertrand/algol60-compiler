# Projet de compilation (2019-2020)

**Niveau atteint sur le projet : 4** *(avec quelques fonctionnalités partiellement implémentées, précisions dans la partie [**PCL2 (génération de code)**](#pcl2-génération-de-code)).*

Réalisation d'un compilateur pour le language Algol60.

## Accès rapides

| Description | Chemin et lien |
|--- | --- |
| Rapport PCL1 | [`doc/PCL1_report.pdf`](doc/PCL1_report.pdf) |
| Rapport PCL2 | [`doc/PCL2_report.pdf`](doc/PCL2_report.pdf) |
| Fichier de grammaire | [`src/main/antlr/eu/telecomnancy/Algol60.g`](src/main/antlr/eu/telecomnancy/Algol60.g) |
| Programmes de démonstration PCL1 (analyse syntaxique) | [`src/test/resources/syntax/demo`](src/test/resources/syntax/demo) |
| Programmes de démonstration PCL2 (analyse sémantique) | [`src/test/resources/semantics/demo`](src/test/resources/semantics/demo) |
| Programmes de démonstration PCL2 (génération de code) | [`src/test/resources/codegen/demo`](src/test/resources/codegen/demo) |

## Commandes

### Exécuter avec Gradle

<pre>
# Compiler et exécuter un programme (-r exécute le programme automatiquement)
./gradlew run --args="<i>prog.alg -r</i>"

# Afficher l'aide
./gradlew run --args="-h"

# Exécution des tests unitaires
./gradlew test
</pre>

### Créer un jar exécutable

<pre>
# Créer un jar : build/libs/algol60_compiler.jar
./gradlew jar

# Compiler et exécuter un programme (-r exécute le programme automatiquement)
java -jar build/libs/algol60_compiler.jar <i>prog.alg</i> -r

# Afficher l'aide
java -jar build/libs/algol60_compiler.jar -h
</pre>

## Étapes du projet

### PCL1 (analyse syntaxique)

- Un seul type de boucle `for` (pas de `for while` ni de `for` avec plusieurs indices)
- Le `switch` n'est pas implémenté
- Il manque les réels négatifs
- Pas de mot clé `own`
- Pas de if-expression (seulement le if-statement)

### PCL2 (analyse sémantique)

- Les 3 types de boucle `for` on été implémentés
- Le `switch` a été implémenté
- Notation scientifique complétée (`-3.14`, `-3#-5`, `3.14#5`, `-3.14#-5`...)
- Les if-expression ont été implémentées
- Pas de mot clé `own`
- Nous avons implémenté des contrôles sémantiques pour tous les éléments de notre grammaire

### PCL2 (génération de code)

- L'ordre opératoire a été revu, il est maintenant opérationnel.
- Seuls les tableaux de **dimension** 1 peuvent être passés en paramètre d'une procédure *(nos tableaux occupent une taille variable en pile selon leur **dimension**)*
- Les `goto` sur un indice de `switch` correspondant à une étiquette non locale ne sont pas gérés (pas de dépilement d'environnement dans ce cas précis). *(Les `goto` classiques sont quant à eux entièrement fonctionnels, étiquettes locales et non locales.)*
- Toujours pas de mot clé `own`
- Pas de passage de paramètres par nom et gestion des réels *(bonus)*
- Pas de gestion des entrées clavier *(non demandé)*

## Autres informations

### Table des opérateurs de notre grammaire

| Op.   | Description         | Op.  | Description        |
|:-----:|---------------------|:----:|--------------------|
| `+`   | Addition            | `-`  | Soustraction       |
| `*`   | Multiplication      | `**` | Puissance          |
| `/`   | Division            | `//` | Division entière   | 
| `#`   | Notation scientif.  | `~`  | Non logique        |
| `<=>` | Équivalence         | `=>` | Implication        |
| `\/`  | Ou logique          | `/\ `| Et logique         |
| `<`   | Inférieur scrict    | `>`  | Supérieur strict   |
| `<=`  | Inférieur ou égal   | `>=` | Supérieur ou égal  |
| `=`   | Égalité             | `<>` | Non-égalité        |

*Notes :*
- La notation scientifique s'écrit `3#8` et ne supporte que des constantes entière et réelles.
- Toute constante réelle est transformée en entier par un arrondi à l'entier le plus proche
- La division réelle est implémentée comme une division entière

### Membres du groupe

- Loïc Bertrand *(Chef de projet)*
- Timon Fugier
- Tony Zhou
- Zineb Ziani El Idrissi

