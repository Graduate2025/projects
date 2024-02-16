getDoctors();

let logo = document.querySelector(".logo");
logo.onclick = () => {
  logo.parentElement.classList.toggle("opened");
};

let showPatientsB = document.querySelectorAll(
  ".doctors .doctor .show-patients"
);

document.querySelector(".doctors").addEventListener("click", (e) => {
  if (e.target.className == "show-patients") {
    e.target.parentElement.classList.toggle("shown");
  } else if (e.target.parentElement.className == "show-patients") {
    e.target.parentElement.parentElement.classList.toggle("shown");
  } else if (e.target.className == "save") {
    let doctor = e.target.parentElement;
    let id = doctor.id.slice(6);
    let phone = doctor.querySelector(" .phone span").innerHTML;
    let address = doctor.querySelector(" .address span").innerHTML;
    let salary = doctor.querySelector(" .salary span").innerHTML;
    let spec = doctor.querySelector(".specialization span").innerHTML;
    let department = doctor.querySelector(" .department select").value;
    updateDoctor(id, phone, address, salary, spec, department);
  }
});

showPatientsB.forEach((e) => {
  e.onclick = () => {
    e.parentElement.classList.toggle("shown");
  };
});

async function getDoctors() {
  let r = await fetch("php/getDoctors.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let doctors = JSON.parse(await r.text());
    for (let i = 0; i < doctors.length; i++) {
      displayDoctor(doctors[i]);
    }
  }
}

function displayDoctor(doctor) {
  document.querySelector(
    ".doctors"
  ).innerHTML += `<div class="doctor" id="doctor${doctor.Staff_ID}">
  <div class="img">
  <img src='img/doctor.png'>
  </div>
  <p class="name">Dr.${doctor.Staff_Fname} ${doctor.Staff_Lname}</p>
  <p class="department info">Department: <select></select></p>
  <p class="phone info" >Phone: <span contenteditable = "true">${doctor.Staff_Phone}</span></p>
  <p class="address info" ">Address: <span contenteditable = "true">${doctor.Staff_address}</span></p>
  <p class="salary info" >Salary: <span contenteditable = "true">${doctor.Salary}</span></p>
  <p class="specialization info" >Specialization: <span contenteditable = "true">${doctor.Doctor_Specialization}</span></p>
  <div class="patients">
</div>
  <div class="show-patients">
    <i class="fa-solid fa-sort-down"></i>
    </div>
  <div class = "save">save</div>
</div>`;
  getPatients(doctor.Staff_ID);
  addDepartments(doctor.Staff_ID, doctor.Department_ID);
}

async function getPatients(doctorID) {
  let r = await fetch("php/getDoctorPatient.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `doctor=${doctorID}`,
  });
  if (r.ok) {
    let patients = JSON.parse(await r.text());
    let patientsP = document.querySelector(
      `.doctors #doctor${doctorID} .patients`
    );
    for (let i = 0; i < patients.length; i++) {
      patientsP.innerHTML += `<p>${patients[i].patient_fname} ${patients[i].patient_lname}</p>`;
    }
  }
}

async function updateDoctor(id, phone, address, salary, spec, department) {
  let b = true;
  if (typeof parseFloat(salary) != "number") {
    window.alert("Salary must be a number");
    b = false;
  }
  if (/[^0-9]/.test(phone)) {
    window.alert("phone must be a number");
    b = false;
  }
  if (b) {
    let r = await fetch("php/updateDoctor.php", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `id=${id}&phone=${phone}&address=${address}&salary=${salary}&spec=${spec}&department=${department}`,
    });
    if (r.ok) {
      alert("updated");
    }
  }
}

async function addDepartments(doctorID, department) {
  let r = await fetch("php/getDepartments.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let departments = JSON.parse(await r.text());
    let doctor = document.querySelector(`.doctors #doctor${doctorID} select`);
    for (let i = 0; i < departments.length; i++) {
      doctor.innerHTML += `<option>${departments[i].Department_id}</option>`;
    }
    doctor.value = department;
  }
}
