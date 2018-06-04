
'use strict';
const mongoose = require('mongoose');


const departureDetailsSchema = new mongoose.Schema({
	depart:{
		type: String,
		required: true
	},
	arrive: {
		type: String,
		required: true
	},
	ferryNum: {
		type: Number,
		required: true
	},
	ferryName: {
		type: String,
		required: true,
		enum: ['Ahoy', 'Mermaid']
	},
	fare:{
		type: String,
		required: true
	}
})



const DepartureSchema = new mongoose.Schema({
    origin: {
      type: String,
      required: true,
      enum: ['BrentwoodBay', 'MillBay', 'Neverland', 'Fantasia']
    },
    destination: {
      type: String,
      required: true,
      enum: ['BrentwoodBay', 'MillBay', 'Neverland', 'Fantasia']
    },
    departureTimes: [departureDetailsSchema],
    departureDates: {
    	type: [Date],
		default: Date(),
		min: Date(),
		max: Date(new Date().setFullYear(new Date().getFullYear() + 1))
    }
});
mongoose.model('departure', DepartureSchema);

