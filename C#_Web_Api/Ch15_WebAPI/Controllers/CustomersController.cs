using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Ch15_WebAPI.Controllers;
using Ch15_WebAPI.Models;
namespace Ch15_WebAPI.Controllers
{
    //[Produces("application/json")]
    [Route("api/Customers")]
    public class CustomersController : Controller
    {

        private ICustomerRepository repo;
        public CustomersController(ICustomerRepository repo)
        {
            this.repo = repo;
        }


        // GET: api/Customers/?country=[value]
        // Note that we are free to call this method whatever we like.
        // It returns a collection of Customer objects.
        [HttpGet]

        public IEnumerable<Customer> GetCustomers(string country)
        {
            if (string.IsNullOrWhiteSpace(country))
                return repo.GetAll();
            return repo.GetAll()
                .Where(customer => customer.Country.ToUpper() == country.ToUpper());
        }
        // GET: api/Customers/id-string
        // We have supplied a name for this route: GetCustomer, which we will
        // reference in the POST method below
        // Note the return type: IActionResult

        [HttpGet("{id}", Name = "GetCustomer")]
        public IActionResult GetCustomer(string id)
        {
            Customer c = repo.Find(id);
            if (c == null)
                return NotFound(); // 404
            return new ObjectResult(c); // 200 OK, and serialize the object
        }
        // POST: api/Customers
        // Note the [FromBody] attribute, which instructs the method
        // to create the Customer object from JSON in the message body

        [HttpPost]
        public IActionResult Create([FromBody] Customer c)
        {
            if (c == null)
                return BadRequest(); // 400 Bad Request
            repo.Add(c);
            // return a 201 Created with a route to get the object
            // the route will be the value of the Location header in the response.
            return CreatedAtRoute("GetCustomer", new { id = c.CustomerID.ToUpper() }, c);
        }
        // PUT: api/Customers/ALFKI

        [HttpPut("{id}")]
        public IActionResult Update(string id, [FromBody] Customer value)
        {
            if (value == null) // we couldn't deserialize the request body
                return BadRequest(); // HTTP 400
            id = id.ToUpper();
            value.CustomerID = value.CustomerID.ToUpper();
            if (value.CustomerID != id)
                return BadRequest(); // HTTP 400
            var existing = repo.Find(id);
            if (existing == null)
                return NotFound(); // HTTP 404
            repo.Update(id, value);
            return new NoContentResult();
        }
        // DELETE: api/customers/ALFKI

        [HttpDelete("{id}")]
        public IActionResult Delete(string id)
        {
            Customer existing = repo.Find(id);
            if (existing == null)
                return NotFound(); // HTTP 404
            repo.Remove(id);
            return new NoContentResult(); // 204 No Content
        }
    }
}
