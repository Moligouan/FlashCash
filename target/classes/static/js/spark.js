const config = {
  colors: ["#FFFFFF", "#E5F3FF", "#CCE5FF", "#99CCFF", "#66B2FF", "#3399FF", "#007BFF"],
  sizes: [4, 6, 8], // Diameter in px
  gravitation: 0.2,
  airResistance: 0.98,
  shrink: 0.1
};

export function generateSpark(element) {
  const rect = element.getBoundingClientRect();
  const sparksCount = Math.floor(Math.random() * 4) + 2; // Between 2 and 5 sparks

  for (let i = 0; i < sparksCount; i++) {
    const spark = document.createElement("div");
    spark.className = "spark";

    const size = config.sizes[Math.floor(Math.random() * config.sizes.length)];
    spark.style.width = `${size}px`;
    spark.style.height = `${size}px`;

    // Random position within the element's bounds
    spark.style.left = `${rect.left + window.scrollX + Math.random() * rect.width}px`;
    spark.style.top = `${rect.top + window.scrollY + Math.random() * rect.height}px`;
    spark.style.backgroundColor = config.colors[Math.floor(Math.random() * config.colors.length)];

    // Add the glow effect
    const glow = document.createElement("div");
    glow.className = "spark-glow";
    spark.appendChild(glow);

    document.body.appendChild(spark);

    // Animate spark with physics-like behavior
    animateSpark(spark);
  }
}

function animateSpark(spark) {
  let speedX = (Math.random() - 0.5) * 4; // Random horizontal speed
  let speedY = (Math.random() - 1) * 4; // Random vertical speed
  const shrink = config.shrink;

  function animate() {
    const currentWidth = parseFloat(spark.style.width);

    // Update position and size
    spark.style.left = `${parseFloat(spark.style.left) + speedX}px`;
    spark.style.top = `${parseFloat(spark.style.top) + speedY}px`;
    spark.style.width = `${Math.max(currentWidth - shrink, 0)}px`;
    spark.style.height = `${Math.max(currentWidth - shrink, 0)}px`;

    speedY += config.gravitation; // Apply gravitation
    speedX *= config.airResistance; // Apply air resistance

    if (currentWidth <= 0) {
      spark.remove(); // Remove spark when it disappears
    } else {
      requestAnimationFrame(animate);
    }
  }

  animate();
}