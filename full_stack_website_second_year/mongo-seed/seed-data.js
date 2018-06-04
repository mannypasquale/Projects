db.departures.save({
	origin: 'BrentwoodBay',
	destination: 'MillBay',
	departureTimes: [{
		_id: ObjectId(),
		depart: '6:00am',
		arrive: '7:00am',
		ferryNum: 1,
		ferryName: 'Ahoy',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '8:00am',
		arrive: '9:00am',
		ferryNum: 3,
		ferryName: 'Mermaid',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '10:00am',
		arrive: '11:00am',
		ferryNum: 5,
		ferryName: 'Ahoy',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '12:00pm',
		arrive: '1:00pm',
		ferryNum: 7,
		ferryName: 'Mermaid',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '2:00pm',
		arrive: '3:00pm',
		ferryNum: 9,
		ferryName: 'Ahoy',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '4:00pm',
		arrive: '5:00pm',
		ferryNum: 11,
		ferryName: 'Mermaid',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '6:00pm',
		arrive: '7:00pm',
		ferryNum: 13,
		ferryName: 'Ahoy',
		fare: '$40'
	}],
	departureDates: [
		new Date ('December 12, 2017'),
		new Date ('December 13, 2017'),
		new Date ('December 14, 2017')
	]
});

db.departures.save({
	origin: 'MillBay',
	destination: 'BrentwoodBay',
	departureTimes: [{
		_id: ObjectId(),
		depart: '6:00am',
		arrive: '7:00am',
		ferryNum: 2,
		ferryName: 'Mermaid',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '8:00am',
		arrive: '9:00am',
		ferryNum: 4,
		ferryName: 'Ahoy',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '10:00am',
		arrive: '11:00am',
		ferryNum: 6,
		ferryName: 'Mermaid',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '12:00pm',
		arrive: '1:00pm',
		ferryNum: 8,
		ferryName: 'Ahoy',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '2:00pm',
		arrive: '3:00pm',
		ferryNum: 10,
		ferryName: 'Mermaid',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '4:00pm',
		arrive: '5:00pm',
		ferryNum: 12,
		ferryName: 'Ahoy',
		fare: '$40'
	},{
		_id: ObjectId(),
		depart: '6:00pm',
		arrive: '7:00pm',
		ferryNum: 14,
		ferryName: 'Mermaid',
		fare: '$40'
	}],
	departureDates: [
		new Date ('December 12, 2017'),
		new Date ('December 13, 2017'),
		new Date ('December 14, 2017')
	]
});

db.departures.save({
	origin: 'BrentwoodBay',
	destination: 'Neverland',
	departureTimes: [{
		_id: ObjectId(),
		depart: 'When Peters ready',
		arrive: 'Who knows',
		ferryNum: 0,
		ferryName: 'Jolly Roger',
		fare: '$0'

	}],
	departureDates: [
		new Date()
		]
});
db.departures.save({
	origin: 'MillBay',
	destination: 'Fantasia',
	departureTimes: [{
		_id: ObjectId(),
		depart: 'When least expected',
		arrive: 'Who knows',
		ferryNum: 69,
		ferryName: 'The Nothing',
		fare: '$0'

	}],
	departureDates: [
		new Date()
		]
});