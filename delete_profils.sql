-- Désactiver les contraintes de clé étrangère temporairement
PRAGMA foreign_keys = OFF;

-- Supprimer d'abord les exercices liés aux profils
DELETE FROM exercice;

-- Supprimer les quêtes liées aux profils
DELETE FROM quete;

-- Supprimer tous les profils
DELETE FROM profil;

-- Réinitialiser les compteurs d'auto-incrémentation
DELETE FROM sqlite_sequence WHERE name IN ('exercice', 'quete', 'profil');

-- Réactiver les contraintes de clé étrangère
PRAGMA foreign_keys = ON;

-- Vérification optionnelle
SELECT COUNT(*) as nb_profils FROM profil;
SELECT COUNT(*) as nb_exercices FROM exercice;
SELECT COUNT(*) as nb_quetes FROM quete; 