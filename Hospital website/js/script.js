getStats();

let logo = document.querySelector(".logo");
logo.onclick = () => {
  logo.parentElement.classList.toggle("opened");
};

let showDetailsB = document.querySelector(".show-details");

let details = document.querySelector(".details");
let detailsH = details.offsetHeight;
details.style.height = "0px";
showDetailsB.onclick = () => {
  if (details.offsetHeight != 0) {
    details.style.height = "0px";
    showDetailsB.innerHTML = "Show Details";
  } else {
    details.style.height = `${detailsH}px`;
    showDetailsB.innerHTML = "Hide Details";
  }
};

async function getStats() {
  let r = await fetch("php/stats.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let stats = JSON.parse(await r.text());
    for (let i = 0; i < stats.length; i++) {
      document.querySelector(
        `.details .${Object.keys(stats[i])[1]}`
      ).innerHTML = stats[i][Object.keys(stats[i])[1]];
    }
  }
}
