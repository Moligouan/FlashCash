const passwordInput = document.getElementById("InputPassword");
const cSpe = document.querySelector(".cSpe");
const cMaj = document.querySelector(".cMaj");
const cMin = document.querySelector(".cMin");
const cNum = document.querySelector(".cNum");
const c8 = document.querySelector(".c8");

passwordInput.addEventListener("input", () => {
    const value = passwordInput.value;

    // Vérifications
    const hasSpecial = /[^A-Za-z0-9]/.test(value); // Caractère spécial
    const hasUpper = /[A-Z]/.test(value);          // Majuscule
    const hasLower = /[a-z]/.test(value);          // Minuscule
    const hasNumber = /[0-9]/.test(value);         // Numérique
    const hasLength = value.length >= 8;          // 8 caractères

    // Mettre à jour les classes et les voyants
    updateIndicator(cSpe, hasSpecial);
    updateIndicator(cMaj, hasUpper);
    updateIndicator(cMin, hasLower);
    updateIndicator(cNum, hasNumber);
    updateIndicator(c8, hasLength);
});

function updateIndicator(element, condition) {
    if (condition) {
        element.classList.add("success"); // Ajoute la classe 'success' pour passer au vert
    } else {
        element.classList.remove("success"); // Supprime la classe 'success' pour repasser au rouge
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const inputPassword = document.getElementById("InputPassword");
    const mdpRegexElements = document.querySelectorAll(".MdpMore");

    inputPassword.addEventListener("focus", () => {
        mdpRegexElements.forEach(mdpRegex => {
            // Étape 1 : afficher l'élément
            mdpRegex.style.display = "block";

            // Étape 2 : ajouter la classe pour la transition d'opacité
            setTimeout(() => {
                mdpRegex.classList.add("visible");
            }, 10); // Délai pour permettre à `display: block` d'être appliqué
        });
    });

    inputPassword.addEventListener("blur", () => {
        mdpRegexElements.forEach(mdpRegex => {
            // Étape 1 : retirer la classe pour la transition d'opacité
            mdpRegex.classList.remove("visible");

            // Étape 2 : masquer l'élément après la transition
            setTimeout(() => {
                mdpRegex.style.display = "none";
            }, 300); // Correspond à la durée de la transition CSS
        });
    });
});
// Barre de Progression

document.addEventListener("DOMContentLoaded", () => {
    const inputPassword = document.getElementById("InputPassword");
    const strengthBar = document.getElementById("strengthBar");
    const strengthLabel = document.getElementById("strengthLabel");

    inputPassword.addEventListener("input", () => {
        const password = inputPassword.value;
        const strength = calculateStrength(password);

        // Mettre à jour la largeur de la barre de force
        strengthBar.value = strength;

        // Supprimer les classes existantes pour la barre et le label
        strengthBar.classList.remove("weak", "medium", "strong", "very-strong");
        strengthLabel.classList.remove("weak", "medium", "strong", "very-strong");

        // Ajouter les classes appropriées et le texte du label
        if (strength <= 25) {
            strengthBar.classList.add("weak");
            strengthLabel.classList.add("weak");
            strengthLabel.textContent = "Faible";
        } else if (strength <= 50) {
            strengthBar.classList.add("medium");
            strengthLabel.classList.add("medium");
            strengthLabel.textContent = "Moyenne";
        } else if (strength <= 75) {
            strengthBar.classList.add("strong");
            strengthLabel.classList.add("strong");
            strengthLabel.textContent = "Forte";
        } else {
            strengthBar.classList.add("very-strong");
            strengthLabel.classList.add("very-strong");
            strengthLabel.textContent = "Très Forte";
        }
    });

    function calculateStrength(password) {
        let strength = 0;

        if (password.length >= 12) strength += 25; 
        if (password.length >= 16) strength += 25; 
        const specialCharCount = (password.match(/[^A-Za-z0-9]/g) || []).length;
        if (specialCharCount >= 2) strength += 20;
        const repeatedCharCount = password.length - new Set(password).size;
        if (repeatedCharCount > 2) strength -= 10;
        const sequences = ["123", "abc", "password"];
        for (const seq of sequences) {
            if (password.toLowerCase().includes(seq)) {
                strength -= 10;
                break;
            }
        }
        if (/[A-Z]/.test(password) && /[a-z]/.test(password)) {
            strength += 15;
        }
        return Math.min(Math.max(strength, 0), 100);
    }
});


