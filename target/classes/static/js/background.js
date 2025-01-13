document.addEventListener("DOMContentLoaded", () => {
    const bck = document.querySelector(".bck");
    const child = document.getElementById("blured");
    const numOrbs = Math.floor(Math.random() * 10) + 5;
    const palettes = [
        "--colors1",
        "--colors2",
        "--colors3",
        "--colors4",
    ];

    // Select a random palette
    const selectedPalette = palettes[Math.floor(Math.random() * palettes.length)];
    const colors = getComputedStyle(document.documentElement)
        .getPropertyValue(selectedPalette)
        .split(",")
        .map((color) => color.trim());

    const style = document.createElement("style");
    document.head.appendChild(style);

    // Get dimensions of the child element
    const childRect = child.getBoundingClientRect();
    const childWidth = childRect.width;
    const childHeight = childRect.height;

    for (let i = 0; i < numOrbs; i++) {
        const orb = document.createElement("div");
        orb.classList.add("orb");

        // Randomize size
        const size = Math.random() * 20 + 20; // 20vw to 40vw
        orb.style.width = `${size}vw`;
        orb.style.height = `${size}vw`;

        // Generate random positions within the child element's dimensions
        let top, left;
        do {
            top = Math.random() * childHeight; // Random position inside child height
            left = Math.random() * childWidth; // Random position inside child width
        } while (
            top + size * 2 > childHeight / 2.5 || // Ensure orb stays within child height
            left + size / 10 > childWidth   // Ensure orb stays within child width
        );

        orb.style.top = `${childRect.top + top}px`;
        orb.style.left = `${childRect.left + left}px`;

        // Randomize color
        orb.style.background = colors[Math.floor(Math.random() * colors.length)];

        // Generate unique keyframes for each orb
        const duration = Math.random() * 10 + 10; // 10s to 20s
        const delay = Math.random() * 5; // 0s to 5s
        const keyframeName = `moveOrb${i}`;
        const translateX = Math.random() * 200 - 100; // Random -100px to 100px
        let translateY;
        do {
            translateY = Math.random() * 200 - 100; // Random -100px to 100px
        } while (
            top + size * 2 + translateY * 2 > childHeight / 3
        );

        // Adjust movement to stay within bounds
        // function adjustMovement() {
        //   const orbRect = orb.getBoundingClientRect();
        //   if (
        //     orbRect.top + translateY < childRect.top ||
        //     orbRect.bottom + translateY * 2 > childRect.bottom / 2
        //   ) {
        //     translateY = -translateY; // Reverse vertical direction
        //   }
        // }

        style.sheet.insertRule(`
        @keyframes ${keyframeName} {
            0% {
                transform: translate(0, 0) scale(1);
            }
            50% {
                transform: translate(${translateX}%, ${translateY}%) scale(0.8);
            }
            100% {
                transform: translate(0, 0) scale(1);
            }
        }
      `);

        orb.style.animation = `${keyframeName} ${duration}s ease-in-out infinite`;
        orb.style.animationDelay = `${delay}s`;

        // orb.addEventListener("animationiteration", adjustMovement);
        bck.appendChild(orb);
    }
});
