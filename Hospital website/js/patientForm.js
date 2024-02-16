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
  addPatient(fname, lname);
};

async function getNameSpace() {
  let r = await fetch("php/patientNameSpace.php", {
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

async function addPatient(fname, lname) {
  let r = await fetch("php/addPatient.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}&fname=${fname}&lname=${lname}`,
  });
  if (r.ok) {
    alert("Patient id = " + id);
    window.location.reload();
  }
}
