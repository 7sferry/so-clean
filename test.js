/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

let register = (username) => fetch("http://localhost:8003/api/users/register", {
	method: "POST",
	headers: { "Content-Type": "application/json" },
	body: JSON.stringify({ username: username, birthDate: "2000-01-01" })
})
		.then(res => res.json())
		.then(data => console.log(data))
		.catch(err => console.error(err));

let get = (input) => fetch(`http://localhost:8003/api/users/detail?username=${input}`, {
	method: "GET",
})
		.then(res => res.json())
		.then(data => console.log(data))
		.catch(err => console.error(err));

get("charlie");
