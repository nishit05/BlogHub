function statesel() {
		var x = document.getElementById("state").value;
		var i;
		switch (x) {
		case "Maharashtra": {
			var city = [ "Mumbai", "Pune", "Nagpur", "Nashik" ];
			dispcity(city);
			break;
		}

		case "Gujarat": {
			city = [ "Ahmedabad", "Surat", "Bhavnagar", "Rajkot", "Vadodra" ];
			dispcity(city);
			break;
		}

		case "Kerala": {
			city = [ "Kochi", "Trivandrum", "Kozhikode", "Kottayam" ];
			dispcity(city);
			break;
		}

		case "Karnataka": {
			city = [ "Bengaluru", "Mysore", "Mangalore", "Hubli" ];
			dispcity(city);
			break;
		}

		case "Rajasthan": {
			city = [ "Jaipur", "Jodhpur", "Udaipur", "Bikaner", "Kota" ];
			dispcity(city);
			break;
		}

		default:
			{
			city = [""];
			dispcity(city);
			break;
			}
			
		}
		function dispcity(arr) {
			var y = document.getElementById("city");
			y.length = 0;
			y.options[0] = new Option("Select City", "");
			for (i = 0; i < city.length; i++) {
				y.options[y.length] = new Option(arr[i], arr[i]);
			}
		}
	}