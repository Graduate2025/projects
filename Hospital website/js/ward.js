getRooms();

let logo = document.querySelector(".logo");
logo.onclick = () => {
  logo.parentElement.classList.toggle("opened");
};

let cancel = document.querySelector(".edit-staff .controls .cancel");
cancel.onclick = () => {
  document.querySelector(".edit-staff").classList.add("closed");
};

let added = [];
let roomsID = [];

document.addEventListener("click", openEdit);

document.querySelector(".wards").addEventListener("click", handleClick);

document
  .querySelector(".edit-staff .wrapper")
  .addEventListener("click", switchRooms);

document.querySelector(".add .room").onclick = () => {
  let no = prompt("Room number: ");
  let Dno;
  if (roomsID.includes(parseInt(no))) {
    alert("Room ID already exists");
  } else {
    Dno = prompt("Department number: ");
    if (!added.includes(parseInt(Dno))) {
      alert("Department doesn't exist");
    } else {
      addRoom(no, Dno);
    }
  }
};
document.querySelector(".add .department").onclick = () => {
  let name = prompt("Department name: ");
  let Dno = prompt("Department number: ");
  if (added.includes(parseInt(Dno))) {
    alert("Department already exist");
  } else {
    addDepartment(Dno, name);
  }
};

async function getRooms() {
  let r = await fetch("php/getRooms.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: ``,
  });
  if (r.ok) {
    let rooms = JSON.parse(await r.text());
    for (let i = 0; i < rooms.length; i++) {
      roomsID.push(rooms[i].room_num);
      if (!added.includes(rooms[i].department_id)) {
        document.querySelector(
          ".wards"
        ).innerHTML += `<h2 class="ward-name">${rooms[i].d_name}</h2>
        <div class="delete-department" dep="${rooms[i].department_id}"">Delete Department</div>
        <div class="ward cont" id=dep${rooms[i].department_id}>
        </div>`;
        added.push(rooms[i].department_id);
      }
      if (rooms[i].room_num == null) {
        return;
      }
      document.querySelector(
        `.wards #dep${rooms[i].department_id}`
      ).innerHTML += `<div class="room" id="room${rooms[i].room_num}">
      <h3>${rooms[i].room_num}</h3>
      <p class="patients-show">Patient</p>
      <div class="patients info">
        <p>${rooms[i].name}</p>
      </div>
      <p class="staff-show">Staff</p>
      <div class="staff info">
      </div>
      <div class='manage-staff'>Manage staff</div>
      <div class='delete-room'>Delete room</div>
    </div>`;
      getStaff(rooms[i].room_num);
    }
  }
}

async function getStaff(room) {
  let r = await fetch("php/getRoomStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `room=${room}`,
  });
  if (r.ok) {
    let staff = JSON.parse(await r.text());
    for (let i = 0; i < staff.length; i++) {
      document.querySelector(
        `#room${room} .staff`
      ).innerHTML += `<p>${staff[i].name}</p>`;
    }
  }
}

function handleClick(e) {
  e = e.target;
  if (e.classList.contains("delete-room")) {
    deleteRoom(e.parentElement.id.slice(4));
  } else if (e.classList.contains("delete-department")) {
    deleteDepartment(e.getAttribute("dep"));
  }
}

function switchRooms(e) {
  e = e.target;
  if (e.classList.contains("staff")) {
    if (e.parentElement.classList.contains("in-room")) {
      document.querySelector(".edit-staff .out-room").appendChild(e);
    } else {
      document.querySelector(".edit-staff .in-room").appendChild(e);
    }
  }
}

function openEdit(e) {
  if (e.target.classList.contains("manage-staff")) {
    document.querySelector(".edit-staff .in-room").innerHTML = "";
    document.querySelector(".edit-staff .out-room").innerHTML = "";
    document.querySelector(".edit-staff").classList.remove("closed");
    let id = e.target.parentElement.id.slice(4);
    getWorkingStaff(id);
    let save = document.querySelector(".edit-staff .controls .save");
    save.onclick = () => {
      updateStaff(id);
    };
  }
}

async function getWorkingStaff(room) {
  let r = await fetch("php/getRoomStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `room=${room}`,
  });
  if (r.ok) {
    let staff = JSON.parse(await r.text());
    for (let i = 0; i < staff.length; i++) {
      document.querySelector(
        ".edit-staff .in-room"
      ).innerHTML += `<p class="staff">${staff[i].staff_id}</p>`;
    }
  }

  let rr = await fetch("php/getStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `room=${room}`,
  });
  if (rr.ok) {
    let staff = JSON.parse(await rr.text());
    for (let i = 0; i < staff.length; i++) {
      document.querySelector(
        ".edit-staff .out-room"
      ).innerHTML += `<p class="staff">${staff[i].staff_id}</p>`;
    }
  }
}

async function updateStaff(room) {
  let in_room = document.querySelectorAll(".edit-staff .in-room .staff");
  let ids = [];
  for (let i = 0; i < in_room.length; i++) {
    ids.push(in_room[i].innerHTML);
  }
  let r = await fetch("php/updateRoomStaff.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `room=${room}&staff=${ids}`,
  });
  if (r.ok) {
    window.location.reload();
  }
}

async function addRoom(no, Dno) {
  let r = await fetch("php/addRoom.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `room=${no}&dep=${Dno}`,
  });
  window.location.reload();
}

async function addDepartment(Dno, name) {
  let r = await fetch("php/addDepartment.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `dep=${Dno}&name=${name}`,
  });
  window.location.reload();
}

async function deleteDepartment(id) {
  let r = await fetch("php/deleteDepartment.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}`,
  });
  if (r.ok) {
    console.log(await r.text());
  }
}

async function deleteRoom(id) {
  let r = await fetch("php/deleteRoom.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `id=${id}`,
  });
}
