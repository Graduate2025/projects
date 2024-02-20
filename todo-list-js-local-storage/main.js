// Selecting DOM elements
const submit = document.querySelector('.submit');
const taskName = document.querySelector('[type="text"]');
const tasks = document.querySelector('.tasks');

// Function to create a new task element
function createTask(content) {
    const task = document.createElement('div');
    task.textContent = content;
    task.className = 'task';
    
    const removeButton = document.createElement('button');
    removeButton.textContent = 'Done';
    removeButton.addEventListener('click', function() {
        removeTask(content);
        task.remove();
    });
    
    task.appendChild(removeButton);
    return task;
}

// Function to remove a task
function removeTask(content) {
    const index = l.indexOf(content);
    if (index !== -1) {
        l.splice(index, 1);
        window.localStorage['tasks'] = JSON.stringify(l);
    }
}

// Load tasks from local storage on page load
const stored = window.localStorage['tasks'];
const l = stored ? JSON.parse(stored) : [];
l.forEach(task => tasks.appendChild(createTask(task)));

// Event listener for adding a new task
submit.addEventListener('click', function() {
    const taskContent = taskName.value.trim();
    if (!taskContent) return;
    
    const task = createTask(taskContent);
    tasks.appendChild(task);
    
    l.push(taskContent);
    window.localStorage['tasks'] = JSON.stringify(l);
    
    taskName.focus();
    taskName.value = '';
});
