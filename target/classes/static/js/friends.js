import { generateSpark } from './spark.js'

document.addEventListener("DOMContentLoaded", () => {
    const confirmLinks = document.querySelectorAll(".confirm-link");

    confirmLinks.forEach(link => {
        link.addEventListener("click", function (event) {
            event.preventDefault(); // Empêcher la navigation immédiate
            const parent = link.closest(".amis"); // Récupérer le parent à collapse

            if (confirm("Êtes-vous sûr de vouloir supprimer cet élément ?")) {
                // Ajouter la classe pour collapse
                parent.classList.add("collapse");

                // Attendre la fin de l'animation du collapse
                setTimeout(() => {
                    // Générer des étincelles
                    generateSpark(parent);

                    // Attendre 0,5s supplémentaires pour l'effet des étincelles
                    setTimeout(() => {
                        window.location.href = link.href; // Rediriger après collapse et étincelles
                    }, 500); // 0,5s après les étincelles
                }, 500); // Correspond à la durée de la transition du collapse
            }
        });
    });
});
