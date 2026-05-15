/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

let register = () => fetch("http://localhost:8003/api/users/register", {
	method: "POST",
	headers: { "Content-Type": "application/json" },
	body: JSON.stringify({ username: "bob", password: "secret12" })
})
		.then(res => res.json())
		.then(data => console.log(data))
		.catch(err => console.error(err));

let get = () => fetch("http://localhost:8003/api/users/detail?username=alice", {
	method: "GET",
})
		.then(res => res.json())
		.then(data => console.log(data))
		.catch(err => console.error(err));

get();
