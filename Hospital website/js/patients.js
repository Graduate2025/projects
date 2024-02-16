window.onload = getPatients;

let logo = document.querySelector(".logo");
logo.onclick = () => {
  logo.parentElement.classList.toggle("opened");
};

let controls = document.querySelector(".controls");

document.addEventListener("click", handleClick);

document.querySelector(".patients").addEventListener("click", (e) => {
  if (!e.target.parentElement.classList.contains("head")) {
    selectRow(e.target.parentElement);
  }
});

let phoneB = document.querySelector(".controls .phone");
phoneB.onclick = () => {
  document.querySelector(".phone-log").classList.remove("hide");
  document.querySelectorAll(".phone-log .phone").forEach((e) => {
    e.remove();
  });
  setPhones(document.querySelector(".patients .row.selected .id").innerHTML);
};

let cancelPhoneB = document.querySelector(".phone-log .cancel");
cancelPhoneB.onclick = () => {
  document.querySelector(".phone-log").classList.add("hide");
};

let cancel = document.querySelector(".edit-patient .options .cancel");
cancel.onclick = () => {
  document.querySelector(".edit-patient").classList.add("hide");
  update = {};
};

let fnameIn = document.querySelector(".edit-patient .fname");
let lnameIn = document.querySelector(".edit-patient .lname");
let birthdayIn = document.querySelector(".edit-patient .birthday");
let genderIn = document.querySelector(".edit-patient .gender");
let diseaseIn = document.querySelector(".edit-patient .disease");
let roomIn = document.querySelector(".edit-patient .room");
let addressIn = document.querySelector(".edit-patient .address");

let save = document.querySelector(".edit-patient .options .save");
save.onclick = () => {
  document.querySelector(".edit-patient").classList.add("hide");
  updatePatient(
    ID,
    fnameIn.value.trim(),
    lnameIn.value.trim(),
    birthdayIn.value.trim(),
    genderIn.value.trim(),
    diseaseIn.value.trim(),
    addressIn.value.trim(),
    roomIn.value
  );
};

let ID;
let fname;
let lname;
let birthday;
let gender;
let disease;
let room;
let address;

let deleteB = document.querySelector(".delete");
deleteB.onclick = () => {
  deletePatient();
};

let bill = document.querySelector(".bill");
bill.onclick = () => {
  getBill(document.querySelector(".patients .row.selected .id").innerHTML);
};

let addPhoneB = document.querySelector(".phone-log .options .add");
addPhoneB.onclick = () => {
  let number = prompt("Phone number");
  if (/[^0-9]/.test(number)) {
    alert("Enter a valid phone");
  } else {
    addPhone(
      document.querySelector(".patients .row.selected .id").innerHTML,
      number
    );
  }
};

let editB = document.querySelector(".edit");
editB.onclick = () => {
  document.querySelector(".edit-patient").classList.remove("hide");
  ID = document.querySelector(".patients .row.selected .id").innerHTML;
  fname = document.querySelector(".patients .row.selected .fname").innerHTML;
  lname = document.querySelector(".patients .row.selected .lname").innerHTML;
  birthday = document.querySelector(
    ".patients .row.selected .birthday"
  ).innerHTML;
  gender = document.querySelector(".patients .row.selected .gender").innerHTML;
  disease = document.querySelector(
    ".patients .row.selected .disease"
  ).innerHTML;
  room = document.querySelector(".patients .row.selected .room").innerHTML;
  address = document.querySelector(
    ".patients .row.selected .address"
  ).innerHTML;

  fnameIn.value = fname;
  lnameIn.value = lname;
  birthdayIn.value = birthday;
  genderIn.value = gender;
  diseaseIn.value = disease;
  roomIn.innerHTML = "";
  roomIn.innerHTML += `<option selected>${room}</option>`;
  getEmptyRooms();
  addressIn.value = address;
};
function selectRow(row) {
  document.querySelectorAll(".row").forEach((e) => {
    if (e != row) {
      e.classList.remove("selected");
    }
  });
  row.classList.toggle("selected");
  if (document.querySelector(".selected") != null) {
    controls.classList.remove("deactivated");
  } else {
    controls.classList.add("deactivated");
  }
}

async function getPatients() {
  let r = await fetch("php/getPatients.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let patients = JSON.parse(await r.text());
    for (let i = 0; i < patients.length; i++) {
      displayPatient(patients[i]);
    }
  }
}

function displayPatient(patient) {
  let patients = document.querySelector(".patients");
  patients.innerHTML += `
  <div class="row">
        <div class="id cell">${patient.patient_ID}</div>
        <div class="fname cell">${patient.patient_Fname || ""}</div>
        <div class="lname cell">${patient.patient_Lname || ""}</div>
        <div class="birthday cell">${patient.date_of_birth || ""}</div>
        <div class="gender cell">${patient.gender || ""}</div>
        <div class="disease cell">${patient.disease || ""}</div>
        <div class="room cell">${patient.Room_Num || ""}</div>
        <div class="address cell">${patient.address || ""}</div>
      </div>
  `;
}

async function getEmptyRooms() {
  let r = await fetch("php/emptyRooms.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let rooms = JSON.parse(await r.text());
    for (let i = 0; i < rooms.length; i++) {
      roomIn.innerHTML += `<option>${rooms[i].Room_Num}</option>`;
    }
  }
}

async function updatePatient(
  id,
  fname,
  lname,
  birth,
  gender,
  disease,
  address,
  room
) {
  let r = await fetch("php/updatePatient.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}&fname=${fname}&lname=${lname}&birth=${birth}&gender=${gender}&disease=${disease}&address=${address}&room=${room}`,
  });

  window.location.reload();
}

async function deletePatient() {
  let r = await fetch("php/deletePatient.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${
      document.querySelector(".patients .row.selected .id").innerHTML
    }`,
  });
  window.location.reload();
}

async function setPhones(id) {
  let r = await fetch("php/getPhone.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}`,
  });
  if (r.ok) {
    let phones = JSON.parse(await r.text());
    let wrap = document.querySelector(".phone-log .phone-wrapper .options");
    for (let i = 0; i < phones.length; i++) {
      wrap.insertAdjacentHTML(
        "beforeBegin",
        `<div class='phone'>
        <p>${phones[i].phone_number}</p>
        <div class='delete-number'>X</div>
        </div>`
      );
    }
  }
}

function handleClick(e) {
  e = e.target;
  if (e.classList.contains("delete-number")) {
    deleteNumber(
      document.querySelector(".patients .row.selected .id").innerHTML,
      e.parentElement.querySelector("p").innerHTML
    );
  }
}

async function deleteNumber(id, number) {
  let r = await fetch("php/deleteNumber.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}&num=${number}`,
  });
}

async function addPhone(id, number) {
  let r = await fetch("php/addNumber.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}&num=${number}`,
  });
}

async function getBill(id) {
  let r = await fetch("php/getBills.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}`,
  });
  if (r.ok) {
    let bill = JSON.parse(await r.text());
    alert("your bill is: " + bill[0].bill);
  }
}
