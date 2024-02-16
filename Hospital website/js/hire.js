getNameSpace();
let id;
let logo = document.querySelector(".logo");
logo.onclick = () => {
  logo.parentElement.classList.toggle("opened");
};

let b = document.querySelector("button");
b.onclick = () => {
  let fname = document.querySelector(".fname").value;
  let lname = document.querySelector(".lname").value;
  let role = document.querySelector(".role").value;
  addStaff(fname, lname, role);
};

async function getNameSpace() {
  let r = await fetch("php/nameSpace.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let nameSpace = JSON.parse(await r.text());
    let nameSpaceA = [];
    for (let i = 0; i < nameSpace.length; i++) {
      nameSpaceA.push(nameSpace[i]);
    }
    while (true) {
      let random = Math.round(Math.random() * 1000);
      if (!nameSpaceA.includes(random)) {
        id = random;
        break;
      }
    }
  }
}

async function addStaff(fname, lname, role) {
  let r = await fetch("php/addStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}&fname=${fname}&lname=${lname}&role=${role}`,
  });
  if (r.ok) {
    window.location.reload();
  }
}
