# Projet de compilation (2019-2020)

**Niveau atteint sur le projet : 4** *(avec quelques fonctionnalités partiellement implémentées, précisées dans la partie "PCL2 (génération de code)".)*

Réalisation d'un compilateur pour le language Algol60.

## Accès rapides

| Description | Chemin et lien |
|--- | --- |
| Rapport PCL1 | [`doc/PCL1_report.pdf`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/doc/PCL1_report.pdf) |
| Rapport PCL2 | [`doc/PCL2_report.pdf`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/doc/PCL2_report.pdf) |
| Fichier de grammaire | [`src/main/antlr/eu/telecomnancy/Algol60.g`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/main/antlr/eu/telecomnancy/Algol60.g) |
| Programmes de démonstration PCL1 (analyse syntaxique) | [`src/test/resources/syntax/demo`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources/syntax/demo) |
| Programmes de démonstration PCL2 (analyse sémantique) | [`src/test/resources/semantics/demo`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources/semantics/demo) |
| Programmes de démonstration PCL2 (génération de code) | [`src/test/resources/codegen/demo`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources/codegen/demo) |

## Commandes

### Exécution rapide d'un programme Algol60

Compiler et exécuter un programme Algol60 :
<pre>
# -r pour exécuter le programme automatiquement
./gradlew run --args="<i>prog.alg -r</i>"
</pre>

Afficher l'aide :
<pre>
./gradlew run --args="-h"
</pre>

### Exécution des tests unitaires

<pre>./gradlew test</pre>
Permet de compiler le projet et d'exécuter tous les tests unitaires

### Création d'un jar exécutable du compilateur

<pre>./gradlew jar</pre>
Permet de créer un jar exécutable contenant tous les éléments nécessaires pour pouvoir être exécuté (ANTLR est inclu dedans). Ce fichier est généré à l'emplacement `build/libs/algol60_compiler.jar`

**Exécution du jar :**

Générer le code assembleur :

<pre>
java -jar build/libs/algol60_compiler.jar <i>prog.alg</i>
</pre> 

Afficher l'aide :
<pre>
java -jar build/libs/algol60_compiler.jar -h
</pre>

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

### PCL2 (génération de code)

Non (ou partiellement) implémentés :

- Passage de tableaux en paramètre de fonction (notre stockage des tableaux en pile n'a pas une taille constante)
- `goto` sur un indice de `switch` correspondant à une étiquette non locale (pas de dépilement d'environnement dans ce cas précis). *(Les `goto` classiques sont quant à eux entièrement fonctionnels.)*
- Passage de paramètres par nom et gestion des réels (bonus)
- Gestion des entrées clavier (non demandé)
- Mot clé `own`

## Membres du groupe

- Loïc Bertrand *(Chef de projet)*
- Timon Fugier
- Tony Zhou
- Zineb Ziani El Idrissi

