# Projet de compilation (2019-2020)

Réalisation d'un compilateur pour le language Algol60

## Accès rapides

Chemin vers le rapport PCL1 : [`doc/PCL1_report.pdf`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/doc/PCL1_report.pdf)

Chemin vers la grammaire : [`src/main/antlr/eu/telecomnancy/Algol60.g`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/main/antlr/eu/telecomnancy/Algol60.g)

Chemin vers les programmes test : [`src/test/resources`](https://gitlab.telecomnancy.univ-lorraine.fr/LoA-c.Bertrand/bertra182u/blob/master/src/test/resources)

## Compilation / exécution du compilateur

La commande `./gradlew run --console=plain -q < TESTFILE` permet de compiler le projet et d'exécuter le compilateur sur le fichier dont le chemin est spécifié par TESTFILE.

## Limitations

### PCL1

- Un seul type de boucle `for` (pas de `for while` ni de `for` avec plusieurs indices)
- Le `switch` n'est pas implémenté
- Il manque les réels négatifs
- Pas de mot clé `own`

## Membres du groupe

- Loïc Bertrand *(Chef de projet)*
- Timon Fugier
- Tony Zhou
- Zineb Ziani El Idrissi

