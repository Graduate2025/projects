window.onload = getStaff;

let logo = document.querySelector(".logo");
logo.onclick = () => {
  logo.parentElement.classList.toggle("opened");
};

let controls = document.querySelector(".controls");

document.querySelector(".staff").addEventListener("click", (e) => {
  if (!e.target.parentElement.classList.contains("head")) {
    selectRow(e.target.parentElement);
  }
});

let cancel = document.querySelector(".edit-staff .options .cancel");
cancel.onclick = () => {
  document.querySelector(".edit-staff").classList.add("hide");
};

let fnameIn = document.querySelector(".edit-staff .fname");
let lnameIn = document.querySelector(".edit-staff .lname");
let phoneIn = document.querySelector(".edit-staff .phone");
let salaryIn = document.querySelector(".edit-staff .salary");
let departmentIn = document.querySelector(".edit-staff .department");
let addressIn = document.querySelector(".edit-staff .address");

let save = document.querySelector(".edit-staff .options .save");
save.onclick = () => {
  document.querySelector(".edit-staff").classList.add("hide");
  updateStaff(
    ID,
    fnameIn.value.trim(),
    lnameIn.value.trim(),
    phoneIn.value.trim(),
    salaryIn.value.trim(),
    addressIn.value.trim(),
    departmentIn.value
  );
};

let ID;
let fname;
let lname;
let phone;
let salary;
let department;
let address;

let editB = document.querySelector(".edit");
editB.onclick = () => {
  document.querySelector(".edit-staff").classList.remove("hide");
  ID = document.querySelector(".staff .row.selected .id").innerHTML;
  fname = document.querySelector(".staff .row.selected .fname").innerHTML;
  lname = document.querySelector(".staff .row.selected .lname").innerHTML;
  phone = document.querySelector(".staff .row.selected .phone").innerHTML;
  salary = document.querySelector(".staff .row.selected .salary").innerHTML;
  department = document.querySelector(
    ".staff .row.selected .department"
  ).innerHTML;
  address = document.querySelector(".staff .row.selected .address").innerHTML;

  fnameIn.value = fname;
  lnameIn.value = lname;
  phoneIn.value = phone;
  salaryIn.value = salary;
  departmentIn.innerHTML = "";
  departmentIn.innerHTML += `<option selected>${department}</option>`;
  getDepartments(department);
  addressIn.value = address;
};

let deleteB = document.querySelector(".delete");
deleteB.onclick = () => {
  deleteStaff(document.querySelector(".staff .row.selected .id").innerHTML);
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

async function getStaff() {
  let r = await fetch("php/getAllStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let staff = JSON.parse(await r.text());
    for (let i = 0; i < staff.length; i++) {
      displayStaff(staff[i]);
    }
  }
}

function displayStaff(staff) {
  let stafff = document.querySelector(".staff");
  stafff.innerHTML += `
  <div class="row">
        <div class="id cell">${staff.Staff_ID}</div>
        <div class="fname cell">${staff.Staff_Fname || ""}</div>
        <div class="lname cell">${staff.Staff_Lname || ""}</div>
        <div class="phone cell">${staff.Staff_Phone || ""}</div>
        <div class="salary cell">${staff.Salary || ""}</div>
        <div class="department cell">${staff.Department_ID || ""}</div>
        <div class="address cell">${staff.Staff_address || ""}</div>
      </div>
  `;
}

async function getDepartments(d) {
  let r = await fetch("php/getDepartments.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let departments = JSON.parse(await r.text());
    for (let i = 0; i < departments.length; i++) {
      if (departments[i].Department_id != d) {
        departmentIn.innerHTML += `<option>${departments[i].Department_id}</option>`;
      }
    }
  }
}

async function updateStaff(
  id,
  fname,
  lname,
  phone,
  salary,
  address,
  department
) {
  let r = await fetch("php/updateStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}&fname=${fname}&lname=${lname}&phone=${phone}&salary=${salary}&address=${address}&department=${department}`,
  });
  if (r.ok) {
    console.log(await r.text());
  }
  window.location.reload();
}

async function deleteStaff(id) {
  let r = await fetch("php/deleteStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}`,
  });
  window.location.reload();
}
