window.onload = function(){
    
    function populate(elementID) {
        console.log(elementID);
		if (elementID === "name") {
            console.log("name clicked");
			document.getElementById("category").innerHTML = "Name";
			document.getElementById("person-details").innerHTML = "Manny Pasquale";
			
		} else if (elementID === "email") {
            console.log("email clicked");
            document.getElementById("category").innerHTML = "Email";
			document.getElementById("person-details").innerHTML = "mannypasquale@gmail.com";
			

		} else if (elementID === "birthday") {
			document.getElementById("category").innerHTML = "Birthday";
			document.getElementById("person-details").innerHTML = "10/10/1990";
		} else if (elementID === "address"){
			document.getElementById("category").innerHTML = "Address";
			document.getElementById("person-details").innerHTML = "123 Fake st.";
		} else if ( elementID == "phone-number"){
            document.getElementById("category").innerHTML = "Phone Number";
            document.getElementById("person-details").innerHTML = "250-xxx-xxxx";
        } else if ( elementID == "password"){
            document.getElementById("category").innerHTML = "password";
            document.getElementById("person-details").innerHTML = "password";
        }
    }
    
	let el1 = document.getElementById("name");
    el1.addEventListener("mouseover" , () => {
        populate("name");
    });
    
    //el1.onclick = populate("name");

	let el2 = document.getElementById("email");
	el2.addEventListener("mouseover", () => {
        populate("email");
    });



	let el3 = document.getElementById("birthday");
	el3.addEventListener("mouseover", () => {
        populate("birthday");
    });



	let el4 = document.getElementById("address");
	el4.addEventListener("mouseover", () => {
        populate("address");
    });

    let el5 = document.getElementById("phone-number");
	el5.addEventListener("mouseover", () => {
        populate("phone-number");
    });
    let el6 = document.getElementById("password");
	el6.addEventListener("mouseover", () => {
        window.alert("You a pussy Z");
    });
    
    let el69 = document.getElementById("click-me");
    el69.addEventListener("click", () => {
	window.alert("You still a pussy Z");
});

}
