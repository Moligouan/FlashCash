import { generateSpark } from './spark.js'

document.addEventListener("DOMContentLoaded", () => {
    const title = document.getElementById("dynamic-header");
    const text = title.textContent; // Utilisez `title` pour récupérer le texte du h1

    // Vider le contenu du h1 pour insérer les lettres individuellement
    title.innerHTML = "";

    // Ajouter chaque lettre dans un `span` pour les cibler individuellement
    text.split("").forEach((char) => {
        const span = document.createElement("span");
        span.textContent = char;
        title.appendChild(span);
    });

    // Sélectionner deux `span` aléatoires qui ne sont pas identiques
    const letters = title.querySelectorAll("span");
    const randomIndex1 = Math.floor(Math.random() * letters.length);
    let randomIndex2;

    do {
        randomIndex2 = Math.floor(Math.random() * letters.length);
    } while (randomIndex2 === randomIndex1); // Assurez-vous qu'ils sont différents

    // Appliquer la classe spéciale à ces deux lettres
    letters[randomIndex1].classList.add("special");
    letters[randomIndex2].classList.add("special2");
});

document.addEventListener("DOMContentLoaded", () => {
    const flicker1 = (elements, className) => {
        elements.forEach((element) => {
            const flickerEffect = () => {
                let timeOn;
                let timeOff;
                
                if (Math.random() < 1 / 3) {
                    timeOn = 100; // 0.1s
                    timeOff = 100;
                } else {
                    timeOn = Math.random() * 5500 + 500; // Entre 0.5s (500ms) et 10s (10000ms)
                    timeOff = Math.random() * 600 + 100;  // Entre 0.1s (100ms) et 1s (1000ms)
                }

                // Une chance sur 5 d'intensité réduite
                if (Math.random() < 1 / 5) {
                    const reducedTime = Math.random() * 700 + 300; // Entre 0.3s et 3s
                    element.style.textShadow = `0px 0px 3px rgba(255, 255, 225, 0.5)`;
                    element.style.color =  `rgba(255, 255, 225, 0.8)`// Intensité réduite
                    setTimeout(flickerEffect, reducedTime);
                    return;
                }

                // Allumer la lumière
                element.style.textShadow = `0px 0px 6px white`;
                element.style.color = `white`;
                setTimeout(() => {
                    // Éteindre ou réduire l'intensité
                    if (Math.random() < 1 / 8) {
                        element.style.textShadow = `0px 0px 3px rgba(255, 255, 225, 0.5)`;
                        element.style.color = `rgba(255, 255, 225, 0.8)`; // Intensité réduite
                    } else {
                        element.style.textShadow = `3px 3px 5px rgba(0, 0, 0, 0.1)`;
                        element.style.color = `rgba(255, 255, 225, 0.5)`;
                        if (Math.random() < 1 / 2) {
                            generateSpark(element); // Complètement éteint
                        }
                    }
                    setTimeout(flickerEffect, timeOff);
                }, timeOn);
            };

            flickerEffect();
        });
    };

    const flicker2 = (elements, className) => {
        elements.forEach((element) => {
            const flickerEffect = () => {
                let timeOn;
                let timeOff;
                
                if (Math.random() < 1 / 6) {
                    timeOn = 100; // 0.1s
                    timeOff = 100;
                } else {
                    timeOn = Math.random() * 9500 + 500; // Entre 0.5s (500ms) et 10s (10000ms)
                    timeOff = Math.random() * 300 + 100;  // Entre 0.1s (100ms) et 1s (1000ms)
                }

                // Une chance sur 5 d'intensité réduite
                if (Math.random() < 1 / 10) {
                    const reducedTime = Math.random() * 1500 + 500; // Entre 0.3s et 3s
                    element.style.textShadow = `0px 0px 3px rgba(255, 255, 225, 0.5)`;
                    element.style.color =  `rgba(255, 255, 225, 0.8)` // Intensité réduite
                    setTimeout(flickerEffect, reducedTime);
                    return;
                }

                // Allumer la lumière
                element.style.textShadow = `0px 0px 6px white`;
                element.style.color = `white`;
                setTimeout(() => {
                    // Éteindre ou réduire l'intensité
                    if (Math.random() < 1 / 16) {
                        element.style.textShadow = `0px 0px 3px rgba(255, 255, 225, 0.5)`;
                        element.style.color =  `rgba(255, 255, 225, 0.8)` // Intensité réduite
                    } else {
                        element.style.textShadow = `3px 3px 5px rgba(0, 0, 0, 0.1)`;
                        element.style.color =  `rgba(255, 255, 225, 0.5)`;
                        if (Math.random() < 1 / 2) {
                            generateSpark(element); // Complètement éteint
                        }
                    }
                    setTimeout(flickerEffect, timeOff);
                }, timeOn);
            };

            flickerEffect();
        });
    };

    // Appliquer les effets aux éléments `special` et `special2`
    const specialElements = document.querySelectorAll(".special");
    const special2Elements = document.querySelectorAll(".special2");

    flicker1(specialElements, "special");
    flicker2(special2Elements, "special2");
});

// Ajouter l'écouteur d'événements pour les éléments avec la classe "btn"
document.querySelectorAll(".btn").forEach((btn) => {
    btn.addEventListener("mouseleave", () => {
      generateSpark(btn); // Appeler generateSpark() lors du hover
    });
  });
  