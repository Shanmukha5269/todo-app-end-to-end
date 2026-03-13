// use this for localhost system environment
// const API_URL = "http://localhost:8080/tasks";

// use this for docker environment
const API_URL = "http://todo-backend:8080/tasks"

window.onload = loadTasks;

function loadTasks() {

    fetch(API_URL)
        .then(res => res.json())
        .then(tasks => {

            const list = document.getElementById("taskList");
            list.innerHTML = "";

            tasks.forEach(task => {

                const li = document.createElement("li");

                li.innerHTML = `
                    <input id="title-${task.id}" value="${task.title}" />
                    <input id="desc-${task.id}" value="${task.description}" />

                    <label>
                        Completed
                        <input type="checkbox" id="comp-${task.id}" ${task.completed ? "checked" : ""}>
                    </label>

                    <button onclick="updateTask(${task.id})">Update</button>
                    <button onclick="deleteTask(${task.id})">Delete</button>
                `;

                list.appendChild(li);
            });
        });
}

function addTask() {

    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    fetch(API_URL, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            title: title,
            description: description,
            completed: false
        })

    }).then(() => {

        document.getElementById("title").value = "";
        document.getElementById("description").value = "";

        loadTasks();
    });
}

function deleteTask(id) {

    fetch(API_URL + "/" + id, {
        method: "DELETE"
    }).then(() => {
        loadTasks();
    });
}

function updateTask(id) {

    const title = document.getElementById(`title-${id}`).value;
    const description = document.getElementById(`desc-${id}`).value;
    const completed = document.getElementById(`comp-${id}`).checked;

    fetch(API_URL + "/" + id, {

        method: "PUT",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            title: title,
            description: description,
            completed: completed
        })

    }).then(() => {
        loadTasks();
    });
}